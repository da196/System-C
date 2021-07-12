package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.Designation;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.Leave;
import tz.go.tcra.hrms.dto.LeaveBalance;
import tz.go.tcra.hrms.dto.LeaveSettings;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflow;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.dto.leave.EmployeeLeaveBalance;
import tz.go.tcra.hrms.services.IDesignationServices;
import tz.go.tcra.hrms.services.IEmployeeServices;
import tz.go.tcra.hrms.services.ILeaveServices;
import tz.go.tcra.hrms.services.ILeaveSettingsServices;
import tz.go.tcra.hrms.services.leave.ILeaveCommutationWorkflowService;
import tz.go.tcra.hrms.services.leave.ILeaveRecallWorkflowService;
import tz.go.tcra.hrms.services.leave.ILeaveWorkflowService;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;

@Controller
public class LeaveController {

	@Autowired
	private ILeaveServices leaveServices;
	@Autowired
	private IEmployeeServices employeeServices;
	@Autowired
	private ILeaveSettingsServices leaveSettingsServices;
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private ILeaveWorkflowService leaveWorkflowService;
	@Autowired
	private IDesignationServices designationServices;
	@Autowired
	private ILeaveCommutationWorkflowService leaveCommutationWorkflowService;
	@Autowired
	private ILeaveRecallWorkflowService leaveRecallWorkflowService;
	
	private static final Logger logger = Logger.getLogger(LeaveController.class); // log4j

	

	
	@RequestMapping(value = "/leave-application.htm")
	public String LeaveApplication() {
		return "leave/leave-application";
	}
	
	//Retrieve employees JSON
	@RequestMapping(value = "/get-employees-listAjax")
	public @ResponseBody Object GetEmployeeList() {
		final ResponseEntity<Employee[]> employeeResult = employeeServices.GetAllEmployeesLessDetails();
		if(employeeResult.getBody() != null) {
			final List<Employee> employees = Arrays.asList(employeeResult.getBody());
			return employees;
		}
		return null;
	}
	
	//Retrieve Leave Types JSON
	@RequestMapping(value = "/get-leave-typesAjax")
	public @ResponseBody Object GetLeaveTypesList() {
		final ResponseEntity<LeaveSettings[]> leaveTypeResult = leaveSettingsServices.GetLeaveTypes();
		if(leaveTypeResult.getBody() != null) {
			final List<LeaveSettings> leaveTypes = Arrays.asList(leaveTypeResult.getBody());
			return leaveTypes;
		}
		return null;
	}
	
	//Retrieve employees details by ID JSON
	@RequestMapping(value = "/get-employeesDetailsByIdAjax/{id}")
	public @ResponseBody Object GetEmployeeDetailsById(@PathVariable("id") int id) {
		final ResponseEntity<Employee> employeeResult = employeeServices.GetEmployeeDetailsById(id);
		if(employeeResult.getBody() != null) {
			final Employee employee = employeeResult.getBody();
			return employee;
		}
		return null;
	}
	
	//Retrieve leave balances by employee Id
	@RequestMapping(value = "/get-leaveBalancesByEmployeeIdAjax/{id}")
	public @ResponseBody Object GetLeaveBalancesByEmployeeId(@PathVariable("id") int id) {
		final ResponseEntity<EmployeeLeaveBalance> leaveResult = leaveServices.LeaveBalancesByEmployeeId(id);
		if(leaveResult.getBody() != null) {
			final List<LeaveBalance> balances = leaveResult.getBody().getLeaveBalancelist();
			return balances;
		}
		return null;
	}
	
	//Get if employee is on leave
	@RequestMapping(value = "/get-ifEmployeeIsOnAnnualLeave/{id}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Object GetIfEmployeeIsOnAnnualLeave(@PathVariable("id") int id) {
		final ResponseEntity<Leave> onLeaveResult = leaveServices.CheckIfOnLeave(id);
		if(onLeaveResult.getBody() != null) {
			final Leave onLeave = onLeaveResult.getBody();
			return onLeave;
		}
		return null;
	}
	
	//Get employee leave history
	@RequestMapping(value = "/get-leaveHistoryByEmployeeIdAjax/{id}")
	public @ResponseBody Object GetLeaveHistoryByEmployeeId(@PathVariable("id") int id) {
		final ResponseEntity<Leave[]> historyResult = leaveServices.GetLeaveApplicationsByEmployeeId(id);
		if(historyResult.getBody() != null) {
			final List<Leave> leaveHistory = Arrays.asList(historyResult.getBody());
			return leaveHistory;
		}
		return null;
	}
	
	//Retrieve all leaves by Ajax
	@RequestMapping(value = "/get-leavesByAjax")
	public @ResponseBody Object GetAllLeavesByAjax() {
		final ResponseEntity<Leave[]> leavesByAjax = leaveServices.GetAllLeavesApplications();
		if(leavesByAjax.getBody() != null) {
			final List<Leave> ajaxLeaves = Arrays.asList(leavesByAjax.getBody());
			return ajaxLeaves;
		}
		return null;
	}
	
	//Leave reports
	@RequestMapping(value = "/leave-reports.htm")
	public String RenderLeaveReportPage() {
		return "leave/leave-reports";
	}
	
	//Pending Leaves reports
	@RequestMapping(value = "/pending-leaves.htm")
	public String PendingLeavesReport() {
		return "leave/reports/pending-leaves";
	}
	
	//Retrieve pending leaves by Ajax
	@RequestMapping(value = "/get-pending-leavesByAjax")
	public @ResponseBody Object GetPendingLeavesByAjax() {
		final ResponseEntity<Leave[]> result = leaveServices.GetPendingLeaves();
		if(result.getBody() != null) {
			final List<Leave> pendingLeaves = Arrays.asList(result.getBody());
			return pendingLeaves;
		}
		return null;
	}
	
	//All employees on leaves
	@RequestMapping(value = "/employees-on-leaves.htm")
	public String EmployeesOnLeaves() {
		return "leave/reports/employees-on-leaves";
	}
	
	//Retrieve employees on leaves by Ajax
	@RequestMapping(value = "/get-employees-on-leavesByAjax")
	public @ResponseBody Object GetEmployeesOnLeavesByAjax() {
		final ResponseEntity<Leave[]> result = leaveServices.GetAllLeavesApplications();
		if(result.getBody() != null) {
			final List<Leave> employeesOnLeaves = Arrays.asList(result.getBody());
			return employeesOnLeaves;
		}
		return null;
	}
	
	//Rejected leaves leaves
	@RequestMapping(value = "/rejected-leaves.htm")
	public String RejectedLeaves() {
		return "leave/reports/rejected-leaves";
	}
	
	//Retrieve rejected leaves by Ajax
	@RequestMapping(value = "/get-rejected-leavesByAjax")
	public @ResponseBody Object GetRejectedLeavesByAjax() {
		final ResponseEntity<Leave[]> result = leaveServices.GetRejectedLeaves();
		if(result.getBody() != null) {
			final List<Leave> rejectedLeaves = Arrays.asList(result.getBody());
			return rejectedLeaves;
		}
		return null;
	}
	
	@RequestMapping(value = "leaves.htm")
	public ModelAndView EmployeeLeaveDashboard() {
		ResponseEntity<Leave[]> leaveResult = null;
		if(leaveServices != null) {
			leaveResult = leaveServices.GetAllLeavesApplications();
		}
		
		List<Leave> leaveApplications = null;
		if(leaveResult.getBody() != null) {
			leaveApplications = Arrays.asList(leaveResult.getBody());
		}
		
		ModelAndView mv = new ModelAndView("leave/leaves");
		mv.addObject("leaveApplications", leaveApplications);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/employee-leave-dashboard.htm/{id}", method = RequestMethod.GET)
	public ModelAndView LeavesIndex(@PathVariable("id") int id) {
		if(id > 0) {
			ResponseEntity<Leave[]> result = leaveServices.GetLeaveApplicationsByEmployeeId(id);
			final ResponseEntity<Employee> employeeResult = employeeServices.GetEmployeeDetailsById(id);
			
			List<Leave> employeeLeaveApplications = null;
			Employee employeeDetails = employeeResult.getBody();
			
			  if(result.getBody() != null) { 
				  employeeLeaveApplications = Arrays.asList(result.getBody()); 
				  }			 
			
			ModelAndView mv = new ModelAndView("leave/employee-leave-dashboard");		
			mv.addObject("employeeLeaveApplications", employeeLeaveApplications);
			mv.addObject("employeeDetails", employeeDetails);
			return mv;
		}
		return null;
	}
	
	
	@RequestMapping(value = "/create-leave-request.htm", method = RequestMethod.POST)
	public String SubmitLeaveRequest(@ModelAttribute("leaveRequest") @RequestBody Leave leaveRequest) {
		if(leaveRequest == null) {
			return "redirect:/null-values-error.htm";
		}
		try {
			//Setting Requester ID
			int requestedby = authenticationFacade.getUser().getEmpId();
			leaveRequest.setRequestedby(requestedby);
			ResponseEntity<Leave> result = leaveServices.RequestLeave(leaveRequest);
			if(result.getStatusCodeValue()!=200) {
				if(result.getStatusCodeValue() == 208) {
					return "redirect:/duplicate-values-error.htm";
				}else if(result.getStatusCodeValue() == 400) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 401) {
					return "redirect:/not-authorized-error.htm";
				}else if(result.getStatusCodeValue() == 404) {
					return "redirect:/not-found.htm";
				}else if(result.getStatusCodeValue() == 503) {
					return "redirect:/service-unavailable.htm";
				}else {
					return "redirect:/error.htm";
				}
			}			
			// Success? redirect to Contracts
			return "redirect:/leaves.htm";
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			return "redirect:/bad-request.htm";
		}
	}
	
	@RequestMapping(value = "/approve-leave-request/{id}", method = RequestMethod.POST)
	public String ApproveLeaveRequest(@PathVariable("id") int id) {
		int status = 1;
		int approverid = authenticationFacade.getUser().getEmpId();

		Leave leaveApproval = new Leave();
		leaveApproval.setApproverid(approverid);
		leaveApproval.setStatus(status);
		leaveApproval.setLeaveid(id);
		
		ResponseEntity<Void> result = leaveServices.ApproveLeaveRequest(id, approverid, status);
		if(result.getStatusCodeValue()!=200) {
			System.out.println(result.getStatusCodeValue());
			if(result.getStatusCodeValue() == 400) {
				return "redirect:/bad-request.htm";
			}else if(result.getStatusCodeValue() == 401) {
				return "redirect:/not-authorized-error.htm";
			}else if(result.getStatusCodeValue() == 404) {
				return "redirect:/not-found.htm";
			}else if(result.getStatusCodeValue() == 412) {
				return "redirect:/bad-request.htm";
			}else if(result.getStatusCodeValue() == 500) {
				return "redirect:/bad-request.htm";
			}else if(result.getStatusCodeValue() == 503) {
				return "redirect:/service-unavailable.htm";
			}else {
				return "redirect:/error.htm";
			}
		}			
		// Success? redirect to Contracts
		return "redirect:/leaves.htm";
		
	}
	
	// ******************************** Leave Workflow    ******************************************

		@RequestMapping(value = "/leave-workflow.htm", method = RequestMethod.GET)
		public ModelAndView TrainingWorkflow() {

			List<TrainingApprovalWorkflow> trainingWorkflow = null;
			logger.info("Training Report");

			if (leaveWorkflowService != null) {
				final String authToken = authenticationFacade.getAuthenticationToken();
				final ResponseEntity<TrainingApprovalWorkflow[]> _trainingWorkflow = leaveWorkflowService
						.GetAllLeaveWorkflow(authToken);

				if (_trainingWorkflow != null && _trainingWorkflow.getBody() != null) {
					trainingWorkflow = Arrays.asList(_trainingWorkflow.getBody());
				}
			}

			ModelAndView mv = new ModelAndView("leave/leave-workflow");
			mv.addObject("trainingWorkflow", trainingWorkflow);
			return mv;
		}

		// Add Training workflow using Ajax

		@RequestMapping(value = "/v1/leave-workflow-add", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> addTrainingWorkflow(
				@Validated @RequestBody HrmsTrainingApprovalWorkflow addTrainingWorkflow) {

			logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflow));

			try {

				if (addTrainingWorkflow == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				final String authToken = authenticationFacade.getAuthenticationToken();
				ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveWorkflowService.CreateLeaveWorkflow(authToken,
						addTrainingWorkflow);

				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// Retrieve Currency JSON
		@RequestMapping(value = "/get-leave-supervisor-designation-listAjax")
		public @ResponseBody Object GetSupervisorDesignationList() {

//			final String authToken = authenticationFacade.getAuthenticationToken();	
			final ResponseEntity<Designation[]> listDesignation = designationServices.GetSupervisor();

			if (listDesignation.getBody() != null) {
				final List<Designation> _listDesignation = Arrays.asList(listDesignation.getBody());
				return _listDesignation;
			}
			return null;
		}

		// View Training Workflow
		@RequestMapping(value = "/v1/leave-workflow-view-details/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> GetTraningWorkflowByID(@PathVariable("id") int id) {
			logger.info("Training Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<TrainingApprovalWorkflow> response = leaveWorkflowService.GetLeaveWorkflowByID(authToken, id);
				if (response != null) {
					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<TrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@RequestMapping(value = "/v1/leave-workflow-delete/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> DeleteTraningWorkflow(@PathVariable("id") int id) {
			logger.info("Training Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveWorkflowService.DeleteLeaveWorkflow(authToken, id);
				if (response != null) {
					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// Add Training workflow using Ajax

			@RequestMapping(value = "/v1/leave-workflow-update/{id}", method = RequestMethod.POST, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
			public @ResponseBody ResponseEntity<?> updateTrainingWorkflow(@PathVariable("id") int id,
					@Validated @RequestBody HrmsTrainingApprovalWorkflow addTrainingWorkflow) {

				logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflow));

				try {

					if (addTrainingWorkflow == null) {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
					final String authToken = authenticationFacade.getAuthenticationToken();
					ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveWorkflowService.UpdateLeaveWorkflow(authToken,
							addTrainingWorkflow, id);
					if (response == null) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					int code = response.getStatusCodeValue();
					if (HttpStatusCodes.EXISTS == code) {
						// read retrieved data for already reported payroll
						return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(),
								HttpStatus.ALREADY_REPORTED);
					} else if (HttpStatusCodes.NOT_FOUND == code) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
						return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
					} else {
						logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
						return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
					}
				} catch (Exception ex) {
					System.out.print(ex.toString());
					ex.printStackTrace();
					logger.error(String.format("Exception is {%s} ", ex.toString()));
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		// *************************************** Leave Workflow Step ***********************************************************
		// **********************************

		// Training Workflow Step
		@RequestMapping(value = "/leave-workflow-step.htm", method = RequestMethod.GET)
		public ModelAndView TrainingWorkflowStep() {
			List<TrainingApprovalWorkflowStep> trainingWorkflowStep = null;

			logger.info("Training Report");

			if (leaveWorkflowService != null) {
				final String authToken = authenticationFacade.getAuthenticationToken();

				final ResponseEntity<TrainingApprovalWorkflowStep[]> _trainingWorkflowStep = leaveWorkflowService
						.GetAllLeaveWorkflowStep(authToken);
				if (_trainingWorkflowStep != null && _trainingWorkflowStep.getBody() != null) {
					trainingWorkflowStep = Arrays.asList(_trainingWorkflowStep.getBody());
				}
			}
			ModelAndView mv = new ModelAndView("leave/leave-workflow-step");
			mv.addObject("trainingWorkflowStep", trainingWorkflowStep);
			return mv;
		}

		// Retrieve Training Workflow Name
		@RequestMapping(value = "/get-leave-workflow-name-listAjax")
		public @ResponseBody Object GetWorkflowName() {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<TrainingApprovalWorkflow[]> listType = leaveWorkflowService.GetAllLeaveWorkflow(authToken);
			if (listType.getBody() != null) {
				final List<TrainingApprovalWorkflow> _listType = Arrays.asList(listType.getBody());
				return _listType;
			}
			return null;
		}

		// Add Training Workflow Step using Ajax
		@RequestMapping(value = "/v1/add-leave-workflow-step", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> addLeaveWorkflowStep(
				@Validated @RequestBody HrmsTrainingApprovalWorkflowStep addTrainingWorkflowStep) {

			logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflowStep));

			try {

				if (addTrainingWorkflowStep == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				final String authToken = authenticationFacade.getAuthenticationToken();
				ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveWorkflowService
						.CreateLeaveWorkflowStep(authToken, addTrainingWorkflowStep);

				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@RequestMapping(value = "/v1/getLeaveByID/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> GetLeaveWorkflowStep(@PathVariable("id") int id) {
			System.out.println("imepitaaaaaaaaaaaaaaaaaaaaaaaaaaaa -----> " + id);
//			logger.info("Training Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<TrainingApprovalWorkflowStep> response = leaveWorkflowService.GetLeaveWorkflowStepByID(authToken, id);
				if (response != null) {
//					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<TrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				ex.printStackTrace();
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
//				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				ex.printStackTrace();
//				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@RequestMapping(value = "/v1/leave-workflow-step-delete/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		
		public @ResponseBody ResponseEntity<?> DeleteLeaveWorkflowStep(@PathVariable("id") int id) {
			logger.info("Training Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveWorkflowService
						.DeleteLeaveWorkflowStep(authToken, id);
				if (response != null) {
					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@RequestMapping(value = "/v1/update-leave-workflow-step/{id}", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> updateLeaveWorkflowStep(@PathVariable("id") int id,
				@Validated @RequestBody HrmsTrainingApprovalWorkflowStep addTrainingWorkflow) {
			System.out.println("uPDATE lEAVE id ===> " + id);
			logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflow));

			try {

				if (addTrainingWorkflow == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				final String authToken = authenticationFacade.getAuthenticationToken();
				ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveWorkflowService
						.UpdateLeaveWorkflowStep(authToken, addTrainingWorkflow, id);
				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		//************************************ End Leave Workflow Step ********************************************

		
		

		// ******************************** Leave Commutation Workflow ******************************************

		@RequestMapping(value = "/leave-commutation-workflow.htm", method = RequestMethod.GET)
		public ModelAndView LeaveCommutationWorkflow() {
			List<TrainingApprovalWorkflow> workflow = null;
			logger.info("Leave Commutation Workflow");
			if (leaveCommutationWorkflowService != null) {
				final String authToken = authenticationFacade.getAuthenticationToken();
				final ResponseEntity<TrainingApprovalWorkflow[]> _workflow = 
						leaveCommutationWorkflowService.GetAllLeaveWorkflow(authToken);
				if (_workflow != null && _workflow.getBody() != null) {
					workflow = Arrays.asList(_workflow.getBody());
				}
			}
			ModelAndView mv = new ModelAndView("leave/leave-commutation-workflow");
			mv.addObject("workflow", workflow);
			return mv;
		}

		// Add Training workflow using Ajax

		@RequestMapping(value = "/v1/leave-commutation-workflow-add", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> addLeaveCommutationWorkflow(
				@Validated @RequestBody HrmsTrainingApprovalWorkflow addTrainingWorkflow) {

			logger.info(String.format("Leave data passed is {%s} ", addTrainingWorkflow));

			try {

				if (addTrainingWorkflow == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				final String authToken = authenticationFacade.getAuthenticationToken();
				ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveCommutationWorkflowService
						.CreateLeaveWorkflow(authToken, addTrainingWorkflow);
				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// View Training Workflow
		@RequestMapping(value = "/v1/leave-commutation-workflow-view-details/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> GetLeaveCommutationWorkflowByID(@PathVariable("id") int id) {
			logger.info("Leave Commutation Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<TrainingApprovalWorkflow> response = leaveCommutationWorkflowService
						.GetLeaveWorkflowByID(authToken, id);
				if (response != null) {
					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<TrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@RequestMapping(value = "/v1/leave-commutation-workflow-delete/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> DeleteLeaveCommutationWorkflow(@PathVariable("id") int id) {
			logger.info("Training Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveCommutationWorkflowService
						.DeleteLeaveWorkflow(authToken, id);
				if (response != null) {
					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// Add Training workflow using Ajax

			@RequestMapping(value = "/v1/leave-commutation-workflow-update/{id}", method = RequestMethod.POST, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
			public @ResponseBody ResponseEntity<?> updateLeaveCommutationWorkflow(@PathVariable("id") int id,
					@Validated @RequestBody HrmsTrainingApprovalWorkflow addTrainingWorkflow) {

				logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflow));

				try {

					if (addTrainingWorkflow == null) {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
					final String authToken = authenticationFacade.getAuthenticationToken();
					ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveCommutationWorkflowService
							.UpdateLeaveWorkflow(authToken, addTrainingWorkflow, id);
					if (response == null) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					int code = response.getStatusCodeValue();
					if (HttpStatusCodes.EXISTS == code) {
						// read retrieved data for already reported payroll
						return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(),
								HttpStatus.ALREADY_REPORTED);
					} else if (HttpStatusCodes.NOT_FOUND == code) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
						return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
					} else {
						logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
						return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
					}
				} catch (Exception ex) {
					System.out.print(ex.toString());
					ex.printStackTrace();
					logger.error(String.format("Exception is {%s} ", ex.toString()));
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		// *************************************** Leave Commutation Workflow Step ***********************************************************
	

		// Training Workflow Step
		@RequestMapping(value = "/leave-commutation-workflow-step.htm", method = RequestMethod.GET)
		public ModelAndView LeaveCommutationWorkflowStep() {
		
			List<TrainingApprovalWorkflowStep> workflowStep = null;

			logger.info("Leave Commuatation workflow Step");

			if (leaveCommutationWorkflowService != null) {
				final String authToken = authenticationFacade.getAuthenticationToken();
				
				final ResponseEntity<TrainingApprovalWorkflowStep[]> _workflowStep = leaveCommutationWorkflowService.GetAllLeaveWorkflowStep(authToken);			

				if (_workflowStep != null && _workflowStep.getBody() != null) {
					workflowStep = Arrays.asList(_workflowStep.getBody());
				}
			}

			ModelAndView mv = new ModelAndView("leave/leave-commutation-workflow-step");
			mv.addObject("workflowStep", workflowStep);
			return mv;
		}

		// Retrieve Training Workflow Name
		@RequestMapping(value = "/get-leave-commutation-workflow-name-listAjax")
		public @ResponseBody Object GetLeaveCommutationWorkflowName() {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<TrainingApprovalWorkflow[]> listType = leaveCommutationWorkflowService.GetAllLeaveWorkflow(authToken);
			if (listType.getBody() != null) {
				final List<TrainingApprovalWorkflow> _listType = Arrays.asList(listType.getBody());
				return _listType;
			}
			return null;
		}

		// Add Training Workflow Step using Ajax
		@RequestMapping(value = "/v1/leave-commutation-workflow-step", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> addTrainingWorkflowStep(
				@Validated @RequestBody HrmsTrainingApprovalWorkflowStep addTrainingWorkflowStep) {

			logger.info(String.format("Leave Commutation data passed is {%s} ", addTrainingWorkflowStep));

			try {

				if (addTrainingWorkflowStep == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				final String authToken = authenticationFacade.getAuthenticationToken();
				ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveCommutationWorkflowService.CreateLeaveWorkflowStep(authToken, addTrainingWorkflowStep);

				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Leave Commutation Successfully Added {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@RequestMapping(value = "/v1/leave-commutation-workflow-step-byID/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> GetLeaveCommutationWorkflowStep(@PathVariable("id") int id) {
			logger.info("Training Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<TrainingApprovalWorkflowStep> response = leaveCommutationWorkflowService.GetLeaveWorkflowStepByID(authToken, id);
				if (response != null) {
					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<TrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@RequestMapping(value = "/v1/update-leave-commutation-workflow-step/{id}", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> updateLeaveCommutationWorkflowStep(@PathVariable("id") int id,
				@Validated @RequestBody HrmsTrainingApprovalWorkflowStep addTrainingWorkflow) {

			logger.info(String.format("Leave Commutation data passed is {%s} ", addTrainingWorkflow));

			try {

				if (addTrainingWorkflow == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				final String authToken = authenticationFacade.getAuthenticationToken();
				ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveCommutationWorkflowService.UpdateLeaveWorkflowStep(authToken, addTrainingWorkflow, id);
				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@RequestMapping(value = "/v1/leave-commutation-workflow-step-delete/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> DeleteLeaveCommutationWorkflowStep(@PathVariable("id") int id) {
			logger.info("Training Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveCommutationWorkflowService.DeleteLeaveWorkflowStep(authToken, id);
				if (response != null) {
					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		
		//************************************ End Training Workflow Step ********************************************
		
		// ******************************** Leave Recall Workflow ******************************************

		@RequestMapping(value = "/leave-recall-workflow.htm", method = RequestMethod.GET)
		public ModelAndView LeaveRecallWorkflow() {
			List<TrainingApprovalWorkflow> workflow = null;
			logger.info("Leave Recall Workflow");
			if (leaveRecallWorkflowService != null) {
				final String authToken = authenticationFacade.getAuthenticationToken();
				final ResponseEntity<TrainingApprovalWorkflow[]> _workflow = leaveRecallWorkflowService.GetAllLeaveRecallWorkflow(authToken);

				if (_workflow != null && _workflow.getBody() != null) {
					workflow = Arrays.asList(_workflow.getBody());
				}
			}
			ModelAndView mv = new ModelAndView("leave/leave-recall-workflow");
			mv.addObject("workflow", workflow);
			return mv;
		}
		
		@RequestMapping(value = "/v1/leave-recall-workflow-add", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> addLeaveRecallWorkflow(
				@Validated @RequestBody HrmsTrainingApprovalWorkflow addTrainingWorkflow) {

			logger.info(String.format("Leave Recall data passed is {%s} ", addTrainingWorkflow));

			try {

				if (addTrainingWorkflow == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				final String authToken = authenticationFacade.getAuthenticationToken();
				ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveRecallWorkflowService.CreateLeaveRecallWorkflow(authToken, addTrainingWorkflow);
				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Leave Recall Successfully Added {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// View Training Workflow
		@RequestMapping(value = "/v1/leave-recall-workflow-view-details/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> GetLeaveRecallWorkflowByID(@PathVariable("id") int id) {
			logger.info("Leave Recall Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<TrainingApprovalWorkflow> response = leaveRecallWorkflowService.GetLeaveRecallWorkflowByID(authToken, id);
				if (response != null) {
					logger.info(String.format("View Recall Details {%s}", response.getBody()));
					return new ResponseEntity<TrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@RequestMapping(value = "/v1/leave-recall-workflow-delete/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> DeleteLeaveRecallWorkflow(@PathVariable("id") int id) {
			logger.info("Training Detail ID =[" + id + "]");
			try {
				final String authToken = authenticationFacade.getAuthenticationToken();
				// create
				final ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveRecallWorkflowService.DeleteLeaveRecallWorkflow(authToken, id);
				if (response != null) {
					logger.info(String.format("View Training Details {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				HttpStatus status = ex.getStatusCode();
				int code = status.value();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				if (HttpStatusCodes.EXISTS == code) {
					return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception ex) {
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// Add Training workflow using Ajax

			@RequestMapping(value = "/v1/leave-recall-workflow-update/{id}", method = RequestMethod.POST, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
			public @ResponseBody ResponseEntity<?> updateLeaveRecallWorkflow(@PathVariable("id") int id,
					@Validated @RequestBody HrmsTrainingApprovalWorkflow addTrainingWorkflow) {

				logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflow));

				try {

					if (addTrainingWorkflow == null) {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
					final String authToken = authenticationFacade.getAuthenticationToken();
					ResponseEntity<HrmsTrainingApprovalWorkflow> response = leaveRecallWorkflowService.UpdateLeaveRecallWorkflow(authToken, addTrainingWorkflow, id);
					if (response == null) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					int code = response.getStatusCodeValue();
					if (HttpStatusCodes.EXISTS == code) {
						// read retrieved data for already reported payroll
						return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(),
								HttpStatus.ALREADY_REPORTED);
					} else if (HttpStatusCodes.NOT_FOUND == code) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
						return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
					} else {
						logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
						return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
					}
				} catch (Exception ex) {
					System.out.print(ex.toString());
					ex.printStackTrace();
					logger.error(String.format("Exception is {%s} ", ex.toString()));
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

			
			// *************************************** Leave Recall Workflow Step ***********************************************************

			@RequestMapping(value = "/leave-recall-workflow-step.htm", method = RequestMethod.GET)
			public ModelAndView LeaveRecallWorkflowStep() {
			
				List<TrainingApprovalWorkflowStep> workflowStep = null;

				logger.info("Leave Recall workflow Step");

				if (leaveRecallWorkflowService != null) {
					final String authToken = authenticationFacade.getAuthenticationToken();
					
					final ResponseEntity<TrainingApprovalWorkflowStep[]> _workflowStep = leaveRecallWorkflowService.GetAllLeaveRecallWorkflowStep(authToken);

					if (_workflowStep != null && _workflowStep.getBody() != null) {
						workflowStep = Arrays.asList(_workflowStep.getBody());
					}
				}

				ModelAndView mv = new ModelAndView("leave/leave-recall-workflow-step");
				mv.addObject("workflowStep", workflowStep);
				return mv;
			}

			// Retrieve Leave Recall Workflow Name
			@RequestMapping(value = "/get-leave-recall-workflow-name-listAjax")
			public @ResponseBody Object GetLeaveRecallWorkflowName() {
				final String authToken = authenticationFacade.getAuthenticationToken();
				final ResponseEntity<TrainingApprovalWorkflow[]> listType = leaveRecallWorkflowService.GetAllLeaveRecallWorkflow(authToken);
				if (listType.getBody() != null) {
					final List<TrainingApprovalWorkflow> _listType = Arrays.asList(listType.getBody());
					return _listType;
				}
				return null;
			}

			// Add Training Workflow Step using Ajax
			@RequestMapping(value = "/v1/leave-recall-workflow-step", method = RequestMethod.POST, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
			public @ResponseBody ResponseEntity<?> addLeaveRecallWorkflowStep(
					@Validated @RequestBody HrmsTrainingApprovalWorkflowStep addTrainingWorkflowStep) {

				logger.info(String.format("Leave Recall data passed is {%s} ", addTrainingWorkflowStep));

				try {

					if (addTrainingWorkflowStep == null) {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
					final String authToken = authenticationFacade.getAuthenticationToken();
					ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveRecallWorkflowService.CreateLeaveRecallWorkflowStep(authToken, addTrainingWorkflowStep);

					if (response == null) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					int code = response.getStatusCodeValue();
					if (HttpStatusCodes.EXISTS == code) {
						// read retrieved data for already reported payroll
						return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(),
								HttpStatus.ALREADY_REPORTED);
					} else if (HttpStatusCodes.NOT_FOUND == code) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
						return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
					} else {
						logger.info(String.format("Leave Recall Successfully Added {%s}", response.getBody()));
						return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
					}
				} catch (Exception ex) {
					System.out.print(ex.toString());
					ex.printStackTrace();
					logger.error(String.format("Exception is {%s} ", ex.toString()));
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			@RequestMapping(value = "/v1/leave-recall-workflow-step-byID/{id}", method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
			public @ResponseBody ResponseEntity<?> GetLeaveRecallWorkflowStep(@PathVariable("id") int id) {
				logger.info("Leave Recall Detail ID =[" + id + "]");
				try {
					final String authToken = authenticationFacade.getAuthenticationToken();
					// create
					final ResponseEntity<TrainingApprovalWorkflowStep> response = leaveRecallWorkflowService.GetLeaveRecallWorkflowStepByID(authToken, id);
					if (response != null) {
						logger.info(String.format("View Leave Recall Details {%s}", response.getBody()));
						return new ResponseEntity<TrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
					}
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} catch (HttpClientErrorException ex) {
					HttpStatus status = ex.getStatusCode();
					int code = status.value();
					logger.info(String.format("Status {%d} is not equal to 200", code));
					if (HttpStatusCodes.EXISTS == code) {
						return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
								HttpStatus.ALREADY_REPORTED);
					} else if (HttpStatusCodes.NOT_FOUND == code) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
						return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
					}
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (Exception ex) {
					ex.printStackTrace();
					logger.error(String.format("Exception is {%s} ", ex.toString()));
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			@RequestMapping(value = "/v1/update-leave-recall-workflow-step/{id}", method = RequestMethod.POST, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
			public @ResponseBody ResponseEntity<?> updateLeaveRecallWorkflowStep(@PathVariable("id") int id,
					@Validated @RequestBody HrmsTrainingApprovalWorkflowStep addTrainingWorkflow) {

				logger.info(String.format("Leave Recall data passed is {%s} ", addTrainingWorkflow));

				try {

					if (addTrainingWorkflow == null) {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
					final String authToken = authenticationFacade.getAuthenticationToken();
					ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveRecallWorkflowService.UpdateLeaveRecallWorkflowStep(authToken, addTrainingWorkflow, id);

					if (response == null) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					int code = response.getStatusCodeValue();
					if (HttpStatusCodes.EXISTS == code) {
						// read retrieved data for already reported payroll
						return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(),
								HttpStatus.ALREADY_REPORTED);
					} else if (HttpStatusCodes.NOT_FOUND == code) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
						return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
					} else {
						logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
						return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
					}
				} catch (Exception ex) {
					System.out.print(ex.toString());
					ex.printStackTrace();
					logger.error(String.format("Exception is {%s} ", ex.toString()));
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

			@RequestMapping(value = "/v1/leave-recall-workflow-step-delete/{id}", method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
			public @ResponseBody ResponseEntity<?> DeleteLeaveRecallWorkflowStep(@PathVariable("id") int id) {
				logger.info("Leave Recall Detail ID =[" + id + "]");
				try {
					final String authToken = authenticationFacade.getAuthenticationToken();
					// create
					final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = leaveRecallWorkflowService.DeleteLeaveRecallWorkflowStep(authToken, id);
					if (response != null) {
						logger.info(String.format("View Leave Recall Details {%s}", response.getBody()));
						return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
					}
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} catch (HttpClientErrorException ex) {
					ex.printStackTrace();
					HttpStatus status = ex.getStatusCode();
					int code = status.value();
					logger.info(String.format("Status {%d} is not equal to 200", code));
					if (HttpStatusCodes.EXISTS == code) {
						return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
								HttpStatus.ALREADY_REPORTED);
					} else if (HttpStatusCodes.NOT_FOUND == code) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
						return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
					}
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (Exception ex) {
					ex.printStackTrace();
					logger.error(String.format("Exception is {%s} ", ex.toString()));
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			

			
			//************************************ End Training Workflow Step ********************************************
}

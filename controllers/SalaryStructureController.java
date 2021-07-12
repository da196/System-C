package tz.go.tcra.hrms.controllers;

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

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.GeneralSettings;
import tz.go.tcra.hrms.dto.SalaryStructure;
import tz.go.tcra.hrms.services.ISalaryStructureServices;

@Controller
public class SalaryStructureController {
	
	@Autowired
	private ISalaryStructureServices structureServices;
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	private final Logger logger = Logger.getLogger(SalaryScaleController.class);

	@RequestMapping(value = "/structure-create.htm", method = RequestMethod.POST)
	public String createStructure(@ModelAttribute("salStructure") @RequestBody SalaryStructure salaryStructure) {
		if(salaryStructure == null ) {
			return "redirect:/null-values-error.htm";
		}
		try{
			ResponseEntity<SalaryStructure> result = structureServices.Create(salaryStructure);
			if(result.getStatusCodeValue() != 200) {
				System.out.println(result.getStatusCodeValue());
				return "redirect:/error.htm";
			}
			return "redirect:/salary-scales.htm";
		}catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}
		
	}
	
//	//Retrieve employees salary structure
//	@RequestMapping(value = "/edit-salary-employee-structure.htm/{id}", method = RequestMethod.POST)
//	public String EditEmployeeSalaryStructure(@PathVariable("id") int id) {
//		if(id>0) {
//			try {
//				int assignedbyId = authenticationFacade.getUser().getEmpId();
//				SalaryStructure structure = new SalaryStructure();
//				structure.setAssignedbyId(assignedbyId);
//				ResponseEntity<SalaryStructure> result = structureServices.EditEmployeeSalaryStructure(id);
//				if (result.getStatusCodeValue() != 200) {
//					System.out.println(result.getStatusCodeValue());
//					if(result.getStatusCodeValue() == 400) {
//						return "redirect:/bad-request.htm";
//					}else if(result.getStatusCodeValue() == 401) {
//						return "redirect:/not-authorized-error.htm";
//					}else if(result.getStatusCodeValue() == 404) {
//						return "redirect:/not-found.htm";
//					}else if(result.getStatusCodeValue() == 412) {
//						return "redirect:/bad-request.htm";
//					}else if(result.getStatusCodeValue() == 500) {
//						return "redirect:/bad-request.htm";
//					}else if(result.getStatusCodeValue() == 503) {
//						return "redirect:/service-unavailable.htm";
//					}else {
//						return "redirect:/error.htm";
//					}
//				}
//			} catch (Exception e) {
//				System.out.print(e.toString());
//				e.printStackTrace();
//				return "redirect:/bad-request.htm";
//			}		
//			
//		}
//		return null;
//	}
	
	//Retrieve employees salary structure JSON
	@RequestMapping(value = "/get-employees-salaryStructureAjax/{id}")
	public @ResponseBody Object GetEmployeeSalaryStructureAjax(@PathVariable("id") int id) {
		final ResponseEntity<SalaryStructure> employeeSalStructuresResult = structureServices.GetSalaryStructureByEmployeeId(id);
		if(employeeSalStructuresResult.getBody() != null) {
			final SalaryStructure salaryStructure = employeeSalStructuresResult.getBody();
			return salaryStructure;
		}
		return null;
	}
	
	//Retrieve employees salary structure JSON
	@RequestMapping(value = "/get-salary-structurebyIdAjax/{id}")
	public @ResponseBody Object GetSalaryStructureByIdAjax(@PathVariable("id") int id) {
		final ResponseEntity<SalaryStructure> SalaryStructuresResult = structureServices.GetSalaryStructureById(id);
		if(SalaryStructuresResult.getBody() != null) {
			final SalaryStructure salStructure = SalaryStructuresResult.getBody();
			return salStructure;
		}
		return null;
	}
	
	// *************************************** Salary Structure ******************************************************
	@RequestMapping(value = "/v1/salary-structure-by-id/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetSalaryStructureByID(@PathVariable("id") int id) {
		
		logger.info("salary structure Detail ID =[" + id + "]");
		try {
			final ResponseEntity<SalaryStructure> response = structureServices.GetSalaryStructureById(id);
			if (response != null) {
				logger.info(String.format("salary structure Details {%s}", response.getBody()));
				return new ResponseEntity<SalaryStructure>(response.getBody(), HttpStatus.OK);
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
	
	@RequestMapping(value = "/v1/salary-structure-delete/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> DeleteSalaryStructureByID(@PathVariable("id") int id) {
		
		logger.info("salary structure Detail ID =[" + id + "]");
		try {
			final ResponseEntity<SalaryStructure> response = structureServices.DeleteSalaryStructureById(id);
			if (response != null) {
				logger.info(String.format("salary structure Details {%s}", response.getBody()));
				return new ResponseEntity<SalaryStructure>(response.getBody(), HttpStatus.OK);
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
	
	@RequestMapping(value = "/v1/update-salary-structure/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> updateSalaryNotch(@PathVariable("id") int id,
			@Validated @RequestBody SalaryStructure salaryStructure) {
		 int empId = authenticationFacade.getUser().getEmpId();
		try {
			salaryStructure.setId(id);
			salaryStructure.setNotchId(salaryStructure.getNotchId());
			salaryStructure.setScaleId(salaryStructure.getScaleId());
			salaryStructure.setAssignedbyId(empId);
			
			logger.info(String.format("Salary Structure update data passed is {%s} ", salaryStructure));
			ResponseEntity<SalaryStructure> response = structureServices.EditSalaryStructure(salaryStructure, id);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<SalaryStructure>(response.getBody(),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Salary Structure Successfully Updated {%s}", response.getBody()));
				return new ResponseEntity<SalaryStructure>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// *************************************** Employee Salary Structure ******************************************************
	
	@RequestMapping(value = "/v1/add-employee-salary-structure", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> addEmployeeSalaryStructure(@Validated @RequestBody SalaryStructure salaryStructure) {
		try {
			logger.info(String.format("Before {%s} ", salaryStructure));
			salaryStructure.setSalarystructureId(salaryStructure.getSalarystructureId());
			salaryStructure.setEmployeeid(salaryStructure.getEmployeeid());
			salaryStructure.setScaleId(salaryStructure.getScaleId());
			salaryStructure.setCurrencyId(salaryStructure.getCurrencyId());
			salaryStructure.setAssignedbyId(authenticationFacade.getUser().getEmpId());
			
			logger.info(String.format("Add Employee Salary Structure data passed is {%s} ", salaryStructure));
			ResponseEntity<SalaryStructure> response = structureServices.AddEmployeeSalaryStructure(salaryStructure);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<SalaryStructure>(response.getBody(),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Employee Salary Structure Successfully Updated {%s}", response.getBody()));
				return new ResponseEntity<SalaryStructure>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/employee-salary-structure-by-id/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetEmployeeSalaryStructureByID(@PathVariable("id") int id) {
		
		logger.info("Employee salary structure Detail ID =[" + id + "]");
		try {
			final ResponseEntity<SalaryStructure> response = structureServices.GetEmployeeSalaryStructureById(id);
			if (response != null) {
				logger.info(String.format("salary structure Details {%s}", response.getBody()));
				return new ResponseEntity<SalaryStructure>(response.getBody(), HttpStatus.OK);
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
	
	@RequestMapping(value = "/v1/employee-salary-structure-delete/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> DeleteEmployeeSalaryStructureByID(@PathVariable("id") int id) {
		
		logger.info("Employee salary structure Detail ID =[" + id + "]");
		try {
			final ResponseEntity<SalaryStructure> response = structureServices.DeleteEmployeeSalaryStructure(id);
			if (response != null) {
				logger.info(String.format("Employee salary structure Details {%s}", response.getBody()));
				return new ResponseEntity<SalaryStructure>(response.getBody(), HttpStatus.OK);
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
	
	@RequestMapping(value = "/v1/update-employee-salary-structure/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> updateEmployeeSalaryStructure(@PathVariable("id") int id,
			@Validated @RequestBody SalaryStructure salaryStructure) {
		try {
			
			salaryStructure.setId(id);
			salaryStructure.setEmployeeid(salaryStructure.getEmployeeid());
			salaryStructure.setScaleId(salaryStructure.getScaleId());
			salaryStructure.setCurrencyId(salaryStructure.getCurrencyId());
			salaryStructure.setAssignedbyId(authenticationFacade.getUser().getEmpId());
		
			
			logger.info(String.format("Employee Salary Structure update data passed is {%s} ", salaryStructure));
			ResponseEntity<SalaryStructure> response = structureServices.EditEmployeeSalaryStructure(salaryStructure, id);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<SalaryStructure>(response.getBody(),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Employee Salary Structure Successfully Updated {%s}", response.getBody()));
				return new ResponseEntity<SalaryStructure>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

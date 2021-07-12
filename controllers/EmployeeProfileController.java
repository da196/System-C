package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.dto.Bank;
import tz.go.tcra.hrms.dto.BankBranch;
import tz.go.tcra.hrms.dto.City;
import tz.go.tcra.hrms.dto.Currency;
import tz.go.tcra.hrms.dto.Designation;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.EmployeeAddress;
import tz.go.tcra.hrms.dto.EmployeeBankAccount;
import tz.go.tcra.hrms.dto.EmployeeCertification;
import tz.go.tcra.hrms.dto.EmployeeEducation;
import tz.go.tcra.hrms.dto.EmployeeEmploymentHistory;
import tz.go.tcra.hrms.dto.EmployeeProfile;
import tz.go.tcra.hrms.dto.EmployeeRelative;
import tz.go.tcra.hrms.dto.EmployeeShortCourse;
import tz.go.tcra.hrms.dto.EmploymentCategory;
import tz.go.tcra.hrms.dto.EmploymentStatus;
import tz.go.tcra.hrms.dto.Gender;
import tz.go.tcra.hrms.dto.MaritalStatus;
import tz.go.tcra.hrms.dto.Nationality;
import tz.go.tcra.hrms.dto.OrganizationOffices;
import tz.go.tcra.hrms.dto.Religion;
import tz.go.tcra.hrms.dto.SalaryStructure;
import tz.go.tcra.hrms.dto.Salutation;
import tz.go.tcra.hrms.dto.Unit;
import tz.go.tcra.hrms.services.IBankBranchServices;
import tz.go.tcra.hrms.services.IBankServices;
import tz.go.tcra.hrms.services.ICityServices;
import tz.go.tcra.hrms.services.IDesignationServices;
import tz.go.tcra.hrms.services.IEmployeeBankAccountServices;
import tz.go.tcra.hrms.services.IEmployeeProfileServices;
import tz.go.tcra.hrms.services.IEmploymentCategoryServices;
import tz.go.tcra.hrms.services.IEmploymentStatusServices;
import tz.go.tcra.hrms.services.IGenderServices;
import tz.go.tcra.hrms.services.IMaritalStatusServices;
import tz.go.tcra.hrms.services.INationalityServices;
import tz.go.tcra.hrms.services.IOrganizationOfficesServices;
import tz.go.tcra.hrms.services.IReligionServices;
import tz.go.tcra.hrms.services.ISalaryStructureServices;
import tz.go.tcra.hrms.services.ISalutationServices;
import tz.go.tcra.hrms.services.IUnitServices;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

@Controller
public class EmployeeProfileController {

	@Autowired
	private IEmployeeProfileServices profileServices;
	@Autowired
	private ICityServices cityServices;
	@Autowired
	private ISalutationServices sServices; // Salutation Services
	@Autowired
	private INationalityServices nationalitiesServices; // Countries(Nationality) Services
	@Autowired
	private IMaritalStatusServices maritalServices; // MaritalStatus Services
	@Autowired
	private IGenderServices gServices; // Genders Services
	@Autowired
	private IReligionServices religionServices; // Religion Services
	@Autowired
	private IDesignationServices desgnServices; // Designation Services
	@Autowired
	private IUnitServices unitServices; // Units Services
	@Autowired
	private IEmploymentStatusServices empStatusServices; // Employment Status
	@Autowired
	private IEmploymentCategoryServices empCategoryServices; // Employment Category
	@Autowired
	private IOrganizationOfficesServices organizationServices; // Duty stations/offices
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private IEmployeeBankAccountServices bankAccountServices;
	@Autowired
	private IBankServices banks;
	@Autowired
	private IBankBranchServices branchServices;
	@Autowired
	private ISalaryStructureServices structureServices;

	@RequestMapping(value = "/employee-profile.htm/{id}", method = RequestMethod.GET)
	public ModelAndView EmployeeProfileIndex(@PathVariable("id") int id) {
		if (profileServices != null) {
			if (id > 0) {
				ResponseEntity<EmployeeProfile> result = profileServices.GetProfileDetailsById(id);
				ResponseEntity<City[]> cityResult = cityServices.GetAll();
				ResponseEntity<Salutation[]> salutes = (ResponseEntity<Salutation[]>) sServices.GetAll();
				ResponseEntity<Nationality[]> nationalityResult = nationalitiesServices.GetAll();
				ResponseEntity<MaritalStatus[]> mStatus = maritalServices.GetAll();
				ResponseEntity<Gender[]> gndr = gServices.GetAll();
				ResponseEntity<Religion[]> relg = religionServices.GetAll();
				ResponseEntity<Designation[]> desgn = desgnServices.GetAll();
				ResponseEntity<Unit[]> unit = unitServices.GetAll();
				ResponseEntity<Unit[]> sectionResult = unitServices.GetSection();
				ResponseEntity<EmploymentStatus[]> empStatus = empStatusServices.GetAll();
				ResponseEntity<EmploymentCategory[]> empCategory = empCategoryServices.GetAll();
				ResponseEntity<Designation[]> supervisorResult = desgnServices.GetSupervisor();
				ResponseEntity<OrganizationOffices[]> officeResult = organizationServices.GetOrganizationOffice();
				ResponseEntity<EmployeeBankAccount[]> bankResults = bankAccountServices.GetAll(id);
				ResponseEntity<Bank[]> bankListResult = banks.GetAll();
				ResponseEntity<BankBranch[]> branchResults = branchServices.GetAll();
				ResponseEntity<SalaryStructure[]> employeeStructuresResult = structureServices.GetAll();
				ResponseEntity<Currency[]> currencyResult = structureServices.GetAllCurrency();
				if (result != null) {
					final EmployeeProfile profile = result.getBody();

					List<EmployeeRelative> relatives = result.getBody().getEmployeeRelativeResponselist();
					List<EmployeeEmploymentHistory> employments = result.getBody()
							.getHrmsEmployeeEmploymentHistoryResponseByEmpIdlist();
					List<EmployeeShortCourse> shortCourses = result.getBody().getHrmsEmployeeShortCoursesRlist();
					List<EmployeeEducation> educations = result.getBody().getEmployeeEducationResponseV2list();
					List<EmployeeCertification> certifications = result.getBody()
							.getHrmsEmployeeCertificationResponselist();
					List<EmployeeAddress> addresses = result.getBody().getHrmsEmployeeAddressContactResponselist();
					List<Salutation> salutations = Arrays.asList(salutes.getBody());
					List<Nationality> nationalities = Arrays.asList(nationalityResult.getBody());
					List<MaritalStatus> maritalStatuses = Arrays.asList(mStatus.getBody());
					List<Gender> genders = Arrays.asList(gndr.getBody());
					List<Religion> religions = Arrays.asList(relg.getBody());
					List<Designation> designations = Arrays.asList(desgn.getBody());
					List<Unit> units = Arrays.asList(unit.getBody());
					List<Unit> sections = Arrays.asList(sectionResult.getBody());
					List<EmploymentStatus> empStatuses = Arrays.asList(empStatus.getBody());
					List<EmploymentCategory> empCategories = Arrays.asList(empCategory.getBody());
					List<Designation> supervisors = Arrays.asList(supervisorResult.getBody());
					List<OrganizationOffices> offices = Arrays.asList(officeResult.getBody());
					List<EmployeeBankAccount> banks = Arrays.asList(bankResults.getBody());
					List<Bank> bankList = Arrays.asList(bankListResult.getBody());
					City[] cities = cityResult.getBody();
					List<BankBranch> branches = Arrays.asList(branchResults.getBody());
					List<SalaryStructure> employeesSalStructures = Arrays.asList(employeeStructuresResult.getBody());
					List<Currency> currencies = Arrays.asList(currencyResult.getBody());
					// retrieve current address
					EmployeeAddress addressCurrent = null;
					if (addresses != null && !addresses.isEmpty()) {
						for (EmployeeAddress item : addresses) {
							if (item != null && item.getAddresstypeid() == 1) {
								// current address
								addressCurrent = item;
								break;
							}
						}
					}
					
					//Pass today date to view
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					LocalDateTime now = LocalDateTime.now();
					String toDate = dtf.format(now);
					
					//Pass logged in user id to view
					int userId = authenticationFacade.getUser().getId();

					// bind to view
					ModelAndView mv = new ModelAndView("hr-base/employee-profile");
					mv.addObject("details", profile.getEmployeeResponse());
					mv.addObject("addressCurrent", addressCurrent);
					mv.addObject("educations", educations);
					mv.addObject("certifications", certifications);
					mv.addObject("employments", employments);
					mv.addObject("relatives", relatives);
					mv.addObject("shortCourses", shortCourses);
					mv.addObject("cities", cities);
					mv.addObject("salutations", salutations);
					mv.addObject("nationalities", nationalities);
					mv.addObject("maritalStatuses", maritalStatuses);
					mv.addObject("genders", genders);
					mv.addObject("religions", religions);
					mv.addObject("designations", designations);
					mv.addObject("units", units);
					mv.addObject("sections", sections);
					mv.addObject("empStatuses", empStatuses);
					mv.addObject("empCategories", empCategories);
					mv.addObject("supervisors", supervisors);
					mv.addObject("offices", offices);
					mv.addObject("toDate", toDate);
					mv.addObject("userId", userId);
					mv.addObject("banks", banks);
					mv.addObject("bankList", bankList);
					mv.addObject("branches", branches);
					mv.addObject("employeesSalStructures", employeesSalStructures);
					mv.addObject("currencies", currencies);
					return mv;
				}
			}
		}
		return null; // error page
	}

	@RequestMapping(value = "/create-contact-information.htm", method = RequestMethod.POST)
	public String CreateEmployeeAddresses(@ModelAttribute("contactInformation") @RequestBody EmployeeAddress address) {
		if (profileServices != null) {
			if (address.getContactphoneprimary().isEmpty()) {
				return "redirect:/null-values-error.htm";
			}
			try {
				ResponseEntity<EmployeeAddress> result = profileServices.createAddress(address);
				int id = address.getEmployeeid();
				if (result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/employee-profile.htm/" + id;
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}

	@RequestMapping(value = "/edit-contact-information.htm", method = RequestMethod.POST)
	public String EditEmployeeAddresses(
			@ModelAttribute("editContactInformation") @RequestBody EmployeeAddress address) {
		if (profileServices != null) {
			if (address.getContactphoneprimary().isEmpty()) {
				return "redirect:/null-values-error.htm";
			}
			try {
				ResponseEntity<EmployeeAddress> result = profileServices.editAddress(address);
				int id = address.getEmployeeid();
				if (result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/employee-profile.htm/" + id;
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}

	@RequestMapping(value = "/update-employee-information.htm", method = RequestMethod.POST)
	public String EditEmployeeInformation(@ModelAttribute("updateEmployeeInformation") @RequestBody Employee employeeInfo) {
		if (profileServices != null) {
			if (employeeInfo == null) {
				return "redirect:/null-values-error.htm";
			}
			try {
				ResponseEntity<Employee> result = profileServices.updateEmployeeInfo(employeeInfo);
				int id = employeeInfo.getId();
				if (result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/employee-profile.htm/" + id;
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/approve-education.htm/{employee_id}/{certificate_id}", method = RequestMethod.GET)
	public String ApproveEducation(@PathVariable("employee_id") int employee_id,@PathVariable("certificate_id") int certificate_id) {
		if(employee_id>0 && certificate_id>0) {
			try {
				int approverEmployeeid = authenticationFacade.getUser().getEmpId();
				// body
				EmployeeEducation employeeEducation = new EmployeeEducation();
				employeeEducation.setId(certificate_id);
				employeeEducation.setApproverEmployeeid(approverEmployeeid);
				//employeeEducation.setComment("Approved");
				
				ResponseEntity<Integer> result = profileServices.ApproveEducation(1,employeeEducation);
				if (result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/employee-profile.htm/" + employee_id;
				
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/reject-education.htm/{employee_id}/{certificate_id}", method = RequestMethod.POST)
	public String RejectEducation(@ModelAttribute("rejectEducation") EmployeeEducation employeeEducation, @PathVariable("employee_id") int employee_id,@PathVariable("certificate_id") int certificate_id ) {
		if(employee_id>0 && certificate_id>0) {
			try {
				int approverEmployeeid = authenticationFacade.getUser().getEmpId();
				// body
				employeeEducation.setId(certificate_id);
				employeeEducation.setApproverEmployeeid(approverEmployeeid);
				
				ResponseEntity<Integer> result = profileServices.ApproveEducation(-1,employeeEducation);
				if (result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/employee-profile.htm/" + employee_id;
				
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/approve-short-course.htm/{employee_id}/{course_id}", method = RequestMethod.GET)
	public String ApproveShortCourse(@PathVariable("employee_id") int employee_id,@PathVariable("course_id") int course_id) {
		if(employee_id>0 && course_id>0) {
			try {
				int approverEmployeeid = authenticationFacade.getUser().getEmpId();
				// body
				EmployeeShortCourse shortCourse = new EmployeeShortCourse();
				shortCourse.setId(course_id);
				shortCourse.setApproverEmployeeid(approverEmployeeid);
				//employeeEducation.setComment("Approved");
				
				ResponseEntity<Integer> result = profileServices.ApproveShortCourse(1, shortCourse);
				if (result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/employee-profile.htm/" + employee_id;
				
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/reject-short-course.htm/{employee_id}/{course_id}", method = RequestMethod.POST)
	public String RejectShortCourse(@ModelAttribute("rejectShortCourse") EmployeeShortCourse shortCourse, @PathVariable("employee_id") int employee_id,@PathVariable("course_id") int course_id ) {
		if(employee_id>0 && course_id>0) {
			try {
				int approverEmployeeid = authenticationFacade.getUser().getEmpId();
				// body
				shortCourse.setId(course_id);
				shortCourse.setApproverEmployeeid(approverEmployeeid);
				
				ResponseEntity<Integer> result = profileServices.RejectShortCourse(-1, shortCourse);
				if (result.getStatusCodeValue() != 200) {
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
				return "redirect:/employee-profile.htm/" + employee_id;
				
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/approve-certification.htm/{employee_id}/{certification_id}", method = RequestMethod.GET)
	public String ApproveCertification(@PathVariable("employee_id") int employee_id,@PathVariable("certification_id") int certification_id) {
		if(employee_id>0 && certification_id>0) {
			try {
				int approverEmployeeid = authenticationFacade.getUser().getEmpId();
				// body
				EmployeeCertification certificate = new EmployeeCertification();
				certificate.setId(certification_id);
				certificate.setApproverEmployeeid(approverEmployeeid);
				//employeeEducation.setComment("Approved");
				
				ResponseEntity<Integer> result = profileServices.ApproveCertificate(1, certificate);
				if (result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/employee-profile.htm/" + employee_id;
				
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}
	 
	@RequestMapping(value = "/reject-certification.htm/{employee_id}/{certification_id}", method = RequestMethod.POST)
	public String RejectCertification(@ModelAttribute("rejectCertification") EmployeeCertification certificate, @PathVariable("employee_id") int employee_id,@PathVariable("certification_id") int certification_id ) {
		if(employee_id>0 && certification_id>0) {
			try {
				int approverEmployeeid = authenticationFacade.getUser().getEmpId();
				// body
				certificate.setId(certification_id);
				certificate.setApproverEmployeeid(approverEmployeeid);
				
				ResponseEntity<Integer> result = profileServices.RejectCertificate(-1, certificate);
				if (result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/employee-profile.htm/" + employee_id;
				
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/update-salary-details.htm", method = RequestMethod.POST)
	public String UpdateSalaryDetails(@ModelAttribute("employeeSalaryDetails") @RequestBody SalaryStructure employeeSalaryStructure) {
		if (profileServices != null) {
			if (employeeSalaryStructure == null) {
				return "redirect:/null-values-error.htm";
			}
			try {
				int assignedbyId = authenticationFacade.getUser().getEmpId();
				employeeSalaryStructure.setAssignedbyId(assignedbyId);
				ResponseEntity<SalaryStructure> result = structureServices.AddEmployeeSalaryStructure(employeeSalaryStructure);				
				int id = employeeSalaryStructure.getEmployeeid();
				if (result.getStatusCodeValue() != 200) {
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
				return "redirect:/employee-profile.htm/" + id;
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
				return "redirect:/error.htm";
			}
		}
		return null;
	}
	
	//Retrieve bank branches by Ajax
	@RequestMapping(value = "/get-bank-branches-by-Ajax/{id}")
	public @ResponseBody Object GetBankBranchesByAjax(@PathVariable("id") int id) {
		final ResponseEntity<BankBranch[]> result = branchServices.GetBranchesByBankId(id);
		if(result.getBody() != null) {
			final List<BankBranch> branches = Arrays.asList(result.getBody());
			return branches;
		}
		return null;
	}

}

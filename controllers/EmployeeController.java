package tz.go.tcra.hrms.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import tz.go.tcra.hrms.dto.Designation;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.EmploymentCategory;
import tz.go.tcra.hrms.dto.EmploymentStatus;
import tz.go.tcra.hrms.dto.Gender;
import tz.go.tcra.hrms.dto.GeneralSettings;
import tz.go.tcra.hrms.dto.MaritalStatus;
import tz.go.tcra.hrms.dto.OrganizationOffices;
import tz.go.tcra.hrms.dto.Religion;
import tz.go.tcra.hrms.dto.Salutation;
import tz.go.tcra.hrms.dto.Unit;
import tz.go.tcra.hrms.services.IDesignationServices;
import tz.go.tcra.hrms.services.IEmployeeServices;
import tz.go.tcra.hrms.services.IEmploymentCategoryServices;
import tz.go.tcra.hrms.services.IEmploymentStatusServices;
import tz.go.tcra.hrms.services.IGenderServices;
import tz.go.tcra.hrms.services.IGeneralSettingsServices;
import tz.go.tcra.hrms.services.IMaritalStatusServices;
import tz.go.tcra.hrms.services.IOrganizationOfficesServices;
import tz.go.tcra.hrms.services.IReligionServices;
import tz.go.tcra.hrms.services.ISalutationServices;
import tz.go.tcra.hrms.services.IUnitServices;

@Controller
//@SessionAttributes("user")
public class EmployeeController {
	
	@Autowired
	private IEmployeeServices eServices; //employee Services	
	@Autowired
	private ISalutationServices sServices; //Salutation Services
	@Autowired
	private IMaritalStatusServices maritalServices; //MaritalStatus Services
	@Autowired
	private IGenderServices gServices; //Genders Services
	@Autowired
	private IReligionServices religionServices; //Religion Services
	@Autowired
	private IDesignationServices desgnServices; //Designation Services
	@Autowired
	private IUnitServices unitServices; //Units Services
	@Autowired
	private IEmploymentStatusServices empStatusServices; //Employment Status
	@Autowired
	private IEmploymentCategoryServices empCategoryServices; //Employment Category
	@Autowired
	private IOrganizationOfficesServices organizationServices; //Duty stations/offices
	@Autowired
	private IEmployeeServices employeeServices;
	@Autowired
	private IGeneralSettingsServices generalServices;
	
	/*
	 * Retrieving employees and passing
	 * response to the view
	 */
	@RequestMapping(value = "/employees.htm", method = RequestMethod.GET)
	public ModelAndView GetAll(HttpSession session) {	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		String toDate =dtf.format(now);
				
		ModelAndView mv = new ModelAndView("hr-base/employees");
		mv.addObject("toDate", toDate);
				
		return mv;
	}
	
	@RequestMapping(value = "/employeesXXX.htm", method = RequestMethod.GET)
	public String RenderallEmployeesDatatable(HttpSession session) {
		return "hr-base/employees";
	}
	
	@RequestMapping(value = "/add-new-employee.htm")
	public String RenderAddNewEmployeeForm() {
		return "hr-base/new-employee-form";
	}
	
	
	@RequestMapping(value = "/employee-create.htm", method = RequestMethod.POST)
	public String Create(@ModelAttribute("employee")  @RequestBody Employee employee) {
		if(employee == null || employee.getFirstName().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		try {
			ResponseEntity<Employee> result = eServices.Create(employee);			
			if(result.getStatusCodeValue()!=200) {				
				if(result.getStatusCodeValue() == 208) {
					return "redirect:/employee-duplicate-values-error.htm";
				}else if(result.getStatusCodeValue() == 400) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 401) {
					return "redirect:/not-authorized-error.htm";
				}else if(result.getStatusCodeValue() == 404) {
					return "redirect:/not-found.htm";
				}else {
					return "redirect:/error.htm";
				}

			}			
			// Success? redirect to employees
			return "redirect:/employees.htm";
		}catch(Exception ex) 
		{			
			System.out.print(ex.toString());
			ex.printStackTrace();
			return "redirect:/bad-request.htm";
		}
	}	

	//Retrieve employees more details JSON
	@RequestMapping(value = "/get-all-employees-detailed-byAjax")
	public @ResponseBody Object GetallEmployeeListDetailed() {
		final ResponseEntity<Employee[]> result = employeeServices.GetAll();
		if(result.getBody() != null) {
			final List<Employee> allEmployees = Arrays.asList(result.getBody());
			return allEmployees;
		}
		return null;
	}
	
	//Retrieve employees for data-table
	@RequestMapping(value = "/get-all-employees-for-datatable-byAjax")
	public @ResponseBody Object GetallEmployeeListForDatatable() {
		final ResponseEntity<Employee[]> result = employeeServices.GetAllEmployeesForDatatable();
		if(result.getBody() != null) {
			final List<Employee> allEmployees = Arrays.asList(result.getBody());
			return allEmployees;
		}
		return null;
	}
	
	//Retrieve employees less detailed JSON
	@RequestMapping(value = "/v1/employees.json")
	public @ResponseBody Object GetEmployeeList() {
		final ResponseEntity<Employee[]> employeeResult = employeeServices.GetAllEmployeesLessDetails();
		if(employeeResult.getBody() != null) {
			final List<Employee> employees = Arrays.asList(employeeResult.getBody());
			return employees;
		}
		return null;
	}
	
	//Retrieve Salutations JSON
	@RequestMapping(value = "/get-salutations-byAjax")
	public @ResponseBody Object GetSalutationByajax() {
		final ResponseEntity<Salutation[]> result = sServices.GetAll();
		if(result.getBody() != null) {
			final List<Salutation> salutations = Arrays.asList(result.getBody());
			return salutations;
		}
		return null;
	}
	
	//Retrieve Gender JSON
	@RequestMapping(value = "/get-gender-byAjax")
	public @ResponseBody Object GetGenderByajax() {
		final ResponseEntity<Gender[]> result = gServices.GetAll();
		if(result.getBody() != null) {
			final List<Gender> genders = Arrays.asList(result.getBody());
			return genders;
		}
		return null;
	}
	
	//Retrieve Religion JSON
	@RequestMapping(value = "/get-religion-byAjax")
	public @ResponseBody Object GetReligionByajax() {
		final ResponseEntity<Religion[]> result = religionServices.GetAll();
		if(result.getBody() != null) {
			final List<Religion> religion = Arrays.asList(result.getBody());
			return religion;
		}
		return null;
	}
	
	//Retrieve Marital status JSON
	@RequestMapping(value = "/get-marital-sattus-byAjax")
	public @ResponseBody Object GetMaritalStatusByajax() {
		final ResponseEntity<MaritalStatus[]> result = maritalServices.GetAll();
		if(result.getBody() != null) {
			final List<MaritalStatus> maritalstatuses = Arrays.asList(result.getBody());
			return maritalstatuses;
		}
		return null;
	}
	
	//Retrieve Designations JSON
	@RequestMapping(value = "/get-designations-byAjax")
	public @ResponseBody Object GetDesignationsByajax() {
		final ResponseEntity<Designation[]> result = desgnServices.GetAll();
		if(result.getBody() != null) {
			final List<Designation> designations = Arrays.asList(result.getBody());
			return designations;
		}
		return null;
	}
	
	//Get Section Ajax
	@RequestMapping(value = "/get-all-sectionByAjax")
	public @ResponseBody Object GetSectionsByAjax() {
		final ResponseEntity<Unit[]> result = unitServices.GetSection();
		if(result.getBody() != null) {
			final List<Unit> sections = Arrays.asList(result.getBody());
			return sections;
		}
		return null;
	}
	
	//Get employment status Ajax
	@RequestMapping(value = "/get-employment-statusAjax")
	public @ResponseBody Object GetEmploymentStatusAjax() {
		final ResponseEntity<EmploymentStatus[]> result = empStatusServices.GetAll();
		if(result.getBody() != null) {
			final List<EmploymentStatus> employmentStatuses = Arrays.asList(result.getBody());
			return employmentStatuses;
		}
		return null;
	}
	
	//Get contract type Ajax
	@RequestMapping(value = "/get-contract-typesAjax")
	public @ResponseBody Object GetContractTypesAjax() {
		final ResponseEntity<EmploymentCategory[]> result = empCategoryServices.GetAll();
		if(result.getBody() != null) {
			final List<EmploymentCategory> employmentCategories = Arrays.asList(result.getBody());
			return employmentCategories;
		}
		return null;
	}
	
	//Get supervisor Ajax
	@RequestMapping(value = "/get-supervisorAjax")
	public @ResponseBody Object GetSupervisorAjax() {
		final ResponseEntity<Designation[]> result = desgnServices.GetSupervisor();
		if(result.getBody() != null) {
			final List<Designation> supervisors = Arrays.asList(result.getBody());
			return supervisors;
		}
		return null;
	}
	
	//Get Duty stations Ajax
	@RequestMapping(value = "/get-duty-stationsAjax")
	public @ResponseBody Object GetDutyStationsAjax() {
		final ResponseEntity<OrganizationOffices[]> result = organizationServices.GetOrganizationOffice();
		if(result.getBody() != null) {
			final List<OrganizationOffices> dutyStations = Arrays.asList(result.getBody());
			return dutyStations;
		}
		return null;
	}
	
	//Get child unit from parent id
	@RequestMapping(value = "/get-child-unitAjax/{id}")
	public @ResponseBody Object GetChildUnitByParentAjax(@PathVariable("id") int id) {
		final ResponseEntity<Unit[]> result = unitServices.GetChildUnitFromParent(id);
		if(result.getBody() != null) {
			final List<Unit> childUnits = Arrays.asList(result.getBody());
			return childUnits;
		}
		return null;
	}
	
	//Get employment status reasons
	@RequestMapping(value = "/get-employment-status-reasonsAjax")
	public @ResponseBody Object GetEmployemntstatusReasonsAjax() {
		final ResponseEntity<GeneralSettings[]> result = generalServices.GetAllEmploymentStatusReasons();
		if(result.getBody() != null) {
			final List<GeneralSettings> reasons = Arrays.asList(result.getBody());
			return reasons;
		}
		return null;
	}

}

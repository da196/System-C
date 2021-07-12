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
import tz.go.tcra.hrms.dto.Allowance;
import tz.go.tcra.hrms.dto.AllowanceType;
import tz.go.tcra.hrms.dto.Currency;
import tz.go.tcra.hrms.dto.Designation;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.EmploymentCategory;
import tz.go.tcra.hrms.dto.GeneralSettings;
import tz.go.tcra.hrms.dto.SalaryScale;
import tz.go.tcra.hrms.dto.SalaryStructure;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.services.IAllowanceServices;
import tz.go.tcra.hrms.services.IDesignationServices;
import tz.go.tcra.hrms.services.IEmployeeServices;
import tz.go.tcra.hrms.services.IGeneralSettingsServices;
import tz.go.tcra.hrms.services.ISalaryScaleServices;
import tz.go.tcra.hrms.services.ISalaryStructureServices;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;

@Controller
public class SalaryScaleController {
	
	@Autowired
	private ISalaryScaleServices salaryServices;
	@Autowired
	private IGeneralSettingsServices settingsServices;
	@Autowired
	private ISalaryStructureServices structureServices;
	@Autowired
	private IAllowanceServices allowanceServices;
	@Autowired
	private IEmployeeServices employeeServices;
	@Autowired IDesignationServices designationServices;

	
	private static final Logger logger = Logger.getLogger(SalaryScaleController.class);

	
	@RequestMapping(value = "/salary-scales.htm", method = RequestMethod.GET)
	public ModelAndView scalesIndex() {
		ResponseEntity<SalaryScale[]> result = null;
		ResponseEntity<GeneralSettings[]> nResult = null;
		ResponseEntity<SalaryStructure[]> sResult = null;
		ResponseEntity<Allowance[]> allowanceResult = null;
		
		ResponseEntity<AllowanceType[]> allowanceTypeResult = null;
		ResponseEntity<Designation[]> designationResult = null;
		
		ResponseEntity<SalaryStructure[]> employeeStructuresResult = structureServices.GetAll();
		ResponseEntity<SalaryStructure[]> salStructuresDetails = structureServices.GetemployeeSalaryStructuresMoreDetails();
		ResponseEntity<Currency[]> currencyResult = structureServices.GetAllCurrency();
		ResponseEntity<Employee[]> empResult = employeeServices.GetAllEmployeesLessDetails();
		ResponseEntity<EmploymentCategory[]> employmentCategoryResult = structureServices.GetEmployementCategory();
		
		if(salaryServices != null) {
			result = salaryServices.GetAll();
			nResult = settingsServices.GetNotches();
			sResult = structureServices.GetAll();
			allowanceResult = allowanceServices.GetAllowanceDetails();
			allowanceTypeResult = allowanceServices.GetAllowanceTypes();	
			designationResult = designationServices.GetAll();
		}

		List<SalaryScale> salScales = null;
		List<GeneralSettings> notches = null;
		List<SalaryStructure> salStructures = null;
		List<Allowance> allowances = null;
		List<AllowanceType> allowancetypes = null;
		List<Designation> designation = null;
		
		List<SalaryStructure> employeesSalStructures = Arrays.asList(employeeStructuresResult.getBody());
		List<SalaryStructure> structuresDetails = Arrays.asList(salStructuresDetails.getBody());
		List<Currency> currencies = Arrays.asList(currencyResult.getBody());
		List<Employee> employees = Arrays.asList(empResult.getBody());
		List<EmploymentCategory> employmentCategory = Arrays.asList(employmentCategoryResult.getBody());
		
		if(result.getBody() != null) {
			salScales = Arrays.asList(result.getBody());
			notches = Arrays.asList(nResult.getBody());
			salStructures = Arrays.asList(sResult.getBody());
			allowances = Arrays.asList(allowanceResult.getBody());
			allowancetypes = Arrays.asList(allowanceTypeResult.getBody());
			designation = Arrays.asList(designationResult.getBody());
		}
		
		ModelAndView mv = new ModelAndView("hr-base/salary-scales");
		mv.addObject("salScales", salScales);
		mv.addObject("notches", notches);
		mv.addObject("salStructures", salStructures);
		mv.addObject("allowances", allowances);
		mv.addObject("allowancetypes", allowancetypes);
		mv.addObject("employeesSalStructures", employeesSalStructures);
		mv.addObject("structuresDetails", structuresDetails);
		mv.addObject("currencies", currencies);
		mv.addObject("employees", employees);
		mv.addObject("designation", designation);
		mv.addObject("employmentCategory", employmentCategory);
		return mv;
	}
	

	
	

	// **************** Salary Scale ***********************************
	
	@RequestMapping(value = "/salScale-create.htm", method = RequestMethod.POST)
	public String createScale(@ModelAttribute("salScale") @RequestBody SalaryScale salaryScale) {
		
		logger.info(String.format("Salary Scale Data {%s} ", salaryScale));
		if(salaryScale == null || salaryScale.getAbbreviation().isEmpty() || salaryScale.getName().isEmpty()) {
			return "redirect:/error.htm";
		}
		try{
			ResponseEntity<SalaryScale> result = salaryServices.Create(salaryScale);
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
	
	@RequestMapping(value = "/v1/salary-scale-by-id/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetSalaryScaleById(@PathVariable("id") int id) {
		
		logger.info("salary scale Detail ID =[" + id + "]");
		try {
			final ResponseEntity<SalaryScale> response = salaryServices.getSalaryScaleByID(id);
			if (response != null) {
				logger.info(String.format("salary scale Details {%s}", response.getBody()));
				return new ResponseEntity<SalaryScale>(response.getBody(), HttpStatus.OK);
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
	
	@RequestMapping(value = "/v1/salary-scale-delete/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> DeletesalaryScale(@PathVariable("id") int id) {
		logger.info("SalaryScale Detail ID =[" + id + "]");
		try {
			// delete
			final ResponseEntity<SalaryScale> response = salaryServices.deleteSalaryScale(id);
			if (response != null) {
				logger.info(String.format("View SalaryScale Details {%s}", response.getBody()));
				return new ResponseEntity<SalaryScale>(response.getBody(), HttpStatus.OK);
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
	
	@RequestMapping(value = "/v1/update-salary-scale/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> updateSalaryScale(@PathVariable("id") int id,
			@Validated @RequestBody SalaryScale salaryScale) {

		logger.info(String.format("Salary Scale data passed is {%s} ", salaryScale));

		try {

			if (salaryScale == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			ResponseEntity<SalaryScale> response = salaryServices.updateSalaryScale(salaryScale, id);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<SalaryScale>(response.getBody(),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Salary Scale Successfully Added {%s}", response.getBody()));
				return new ResponseEntity<SalaryScale>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// *************** End Salary Scale ***********************************
	
	// ****************** Salary Notch ************************************
	@RequestMapping(value = "/notch-create.htm", method = RequestMethod.POST)
	public String createNotch(@ModelAttribute("notches") @RequestBody GeneralSettings settings) {
		if(settings == null || settings.getAbbreviation().isEmpty() || settings.getName().isEmpty()) {
			return "redirect:/error.htm";
		}
		try{
			ResponseEntity<GeneralSettings> result = settingsServices.AddNotch(settings);
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
	
	@RequestMapping(value = "/v1/salary-notch-by-id/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetSalaryNotchById(@PathVariable("id") int id) {
		
		logger.info("salary notch Detail ID =[" + id + "]");
		try {
			final ResponseEntity<GeneralSettings> response = settingsServices.getSalaryscaleNotch(id);
			if (response != null) {
				logger.info(String.format("salary notch Details {%s}", response.getBody()));
				return new ResponseEntity<GeneralSettings>(response.getBody(), HttpStatus.OK);
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
	
	@RequestMapping(value = "/v1/salary-notch-delete/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> DeletesalaryNotch(@PathVariable("id") int id) {
		logger.info("Salary Notch Detail ID =[" + id + "]");
		try {
			// delete
			final ResponseEntity<GeneralSettings> response = settingsServices.deleteSalaryscaleNotch(id);
			if (response != null) {
				logger.info(String.format("View Salary Notch Details {%s}", response.getBody()));
				return new ResponseEntity<GeneralSettings>(response.getBody(), HttpStatus.OK);
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
	
	
	@RequestMapping(value = "/v1/update-notch-scale/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> updateSalaryNotch(@PathVariable("id") int id,
			@Validated @RequestBody GeneralSettings generalSettings) {
		logger.info(String.format("Salary Scale data passed is {%s} ", generalSettings));

		try {

			if (generalSettings == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			ResponseEntity<GeneralSettings> response = settingsServices.updateSalaryNotch(generalSettings, id);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<GeneralSettings>(response.getBody(),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Salary Notch Successfully Updated {%s}", response.getBody()));
				return new ResponseEntity<GeneralSettings>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// ****************** End Salary Notch ************************************
	

	
}

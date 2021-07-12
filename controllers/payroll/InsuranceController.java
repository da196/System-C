package tz.go.tcra.hrms.controllers.payroll;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.payroll.EmployeeInsurance;
import tz.go.tcra.hrms.services.payroll.IEmployeeInsuranceServices;
import tz.go.tcra.hrms.utils.DateUtils;

@Controller
public class InsuranceController {
	private static final Logger logger = Logger.getLogger(InsuranceController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private IEmployeeInsuranceServices employeeInsuranceServices;

	@RequestMapping(value = "/payroll-employee-health-insurance.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// insurances
		List<EmployeeInsurance> _insurances = null;
		if (employeeInsuranceServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<EmployeeInsurance[]> insurances = employeeInsuranceServices.GetAll(authToken);
			if (insurances != null && insurances.getBody() != null) {
				_insurances = Arrays.asList(insurances.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-contribution-mandatory-insurance-health");
		// add model data
		mv.addObject("insurances", _insurances);

		return mv;
	}

	@RequestMapping(value = "/payroll-employee-add-health-insurance.htm", method = RequestMethod.GET)
	public ModelAndView add() {
		// initialize

		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-contribution-mandatory-insurance-health-add");
		// add model data
		// mv.addObject("loans", _loans);

		return mv;
	}

	// --------------------------------------------------
	// - Ajax Calls
	// --------------------------------------------------
	@RequestMapping(value = "/v1/payroll-employee-insurance-add", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> Add(@Validated @RequestBody EmployeeInsurance insurance) {
		logger.info(String.format("Insurance passed is {%s} ", insurance));
		if (insurance == null || insurance.getEmployeeid() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// date to database format
			final String joiningDate = insurance.getDatestart();
			final String joining = DateUtils.toYYYYMMDD(joiningDate);
			insurance.setDatestart(joining);
			final ResponseEntity<EmployeeInsurance> result = employeeInsuranceServices.Create(authToken, insurance);

			int code = result.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<EmployeeInsurance>(result.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/v1/payroll-employee-insurance-update/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> Update(@PathVariable("id") int id,
			@Validated @RequestBody EmployeeInsurance insurance) {
		logger.info(String.format("Contribution passed is {%s} ", insurance));
		if (insurance == null || insurance.getEmployeeid() <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// date to database format
			final String joiningDate = insurance.getDatestart();
			final String joining = DateUtils.toYYYYMMDD(joiningDate);
			insurance.setDatestart(joining);
			final ResponseEntity<EmployeeInsurance> result = employeeInsuranceServices.Update(authToken, id, insurance);

			int code = result.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Contribution status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<EmployeeInsurance>(result.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

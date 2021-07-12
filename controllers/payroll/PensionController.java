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
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.payroll.EmployeePension;
import tz.go.tcra.hrms.dto.payroll.EmployeePension;
import tz.go.tcra.hrms.dto.payroll.Loan;
import tz.go.tcra.hrms.dto.payroll.LoanFrequency;
import tz.go.tcra.hrms.dto.payroll.LoanLender;
import tz.go.tcra.hrms.dto.payroll.LoanType;
import tz.go.tcra.hrms.services.payroll.IEmployeePensionServices;
import tz.go.tcra.hrms.utils.DateUtils;

@Controller
public class PensionController {
	private static final Logger logger = Logger.getLogger(PensionController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private IEmployeePensionServices employeePensionServices;

	@RequestMapping(value = "/payroll-employee-pension-fund.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// pensions
		List<EmployeePension> _pensions = null;
		if (employeePensionServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<EmployeePension[]> pensions = employeePensionServices.GetAll(authToken);
			if (pensions != null && pensions.getBody() != null) {
				_pensions = Arrays.asList(pensions.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-contribution-mandatory-pension");
		// add model data
		mv.addObject("pensions", _pensions);

		return mv;
	}

	@RequestMapping(value = "/payroll-employee-add-pension-fund.htm", method = RequestMethod.GET)
	public ModelAndView add() {
		// initialize
		// loan

		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-contribution-mandatory-pension-add");
		// add model data
		// mv.addObject("loans", _loans);

		return mv;
	}

	// --------------------------------------------------
	// - Ajax Calls
	// --------------------------------------------------
	@RequestMapping(value = "/v1/payroll-employee-pension-add", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> Add(@Validated @RequestBody EmployeePension pension) {
		logger.info(String.format("Contribution passed is {%s} ", pension));
		if (pension == null || pension.getEmployeeid() <= 0) {
			logger.info("Contribution is null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// date to database format
			final String joiningDate = pension.getDatestart();
			final String joining = DateUtils.toYYYYMMDD(joiningDate);
			pension.setDatestart(joining);
			final ResponseEntity<EmployeePension> result = employeePensionServices.Create(authToken,
					pension);

			int code = result.getStatusCodeValue();
			logger.info(String.format("Contribution status {%d} is not equal to 200", result.getStatusCodeValue()));
			if (HttpStatusCodes.EXISTS == code) {
				logger.info(String.format("Contribution status code is {%d} already exists", code));
				return new ResponseEntity<>(String.format("Contribution status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
				// return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				logger.info(String.format("Contribution status code is {%d} not found", code));
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Contribution status code is {%d} OK", code));
				return new ResponseEntity<EmployeePension>(result.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/payroll-employee-pension-update/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> Update(@PathVariable("id")int id,@Validated @RequestBody EmployeePension pension) {
		logger.info(String.format("Contribution passed is {%s} ", pension));
		if (pension == null || pension.getEmployeeid() <= 0) {
			logger.info("Contribution is null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// date to database format
			final String joiningDate = pension.getDatestart();
			final String joining = DateUtils.toYYYYMMDD(joiningDate);
			pension.setDatestart(joining);
			final ResponseEntity<EmployeePension> result = employeePensionServices.Update(authToken,id,pension);

			int code = result.getStatusCodeValue();
			logger.info(String.format("Contribution status {%d} is not equal to 200", result.getStatusCodeValue()));
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
				return new ResponseEntity<EmployeePension>(result.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

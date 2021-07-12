package tz.go.tcra.hrms.controllers.payroll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.payroll.LoanFrequency;
import tz.go.tcra.hrms.dto.payroll.LoanLender;
import tz.go.tcra.hrms.dto.payroll.LoanType;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.common.ApiError;
import tz.go.tcra.hrms.dto.payroll.EmployeePension;
import tz.go.tcra.hrms.dto.payroll.Loan;
import tz.go.tcra.hrms.dto.payroll.LoanHeslb;
import tz.go.tcra.hrms.services.IEmployeeServices;
import tz.go.tcra.hrms.services.payroll.ILoanFrequencyServices;
import tz.go.tcra.hrms.services.payroll.ILoanHeslbServices;
import tz.go.tcra.hrms.services.payroll.ILoanLenderServices;
import tz.go.tcra.hrms.services.payroll.ILoanServices;
import tz.go.tcra.hrms.services.payroll.LoanTypeServices;
import tz.go.tcra.hrms.utils.DateUtils;

@Controller
public class LoanHeslbController {
	private static final Logger logger = Logger.getLogger(LoanHeslbController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private ILoanServices loanServices;
	@Autowired
	private ILoanHeslbServices loanHeslbServices;

	// --------------------------------------------------
	// - Ajax Calls
	// --------------------------------------------------
	@RequestMapping(value = "/v1/payroll-loan-heslb-add", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> AddLoanHeslb(@RequestBody LoanHeslb loan) {
		logger.info("Loan Detail HESLB =[" + loan.toString() + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<LoanHeslb> response = loanHeslbServices.Create(authToken, loan);
			if (response != null) {
				int code = response.getStatusCodeValue();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				return new ResponseEntity<LoanHeslb>(response.getBody(), HttpStatus.OK);
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
		}catch (Exception ex) {
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/v1/payroll-loan-heslb-update/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> updateLoanHeslb(@PathVariable("id") int id,
			@RequestBody LoanHeslb loan) {
		logger.info("Loan Detail HESLB =[" + loan.toString() + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// update
			final ResponseEntity<LoanHeslb> response = loanHeslbServices.Update(authToken, id, loan);
			if (response != null) {
				int code = response.getStatusCodeValue();
				logger.info(String.format("Status {%d} is not equal to 200", code));
				return new ResponseEntity<LoanHeslb>(response.getBody(), HttpStatus.OK);
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
		}catch (Exception ex) {
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/v1/payroll-loan-heslb-by-loan/{loanid}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetLoanHeslb(@PathVariable("loanid") int loanid) {
		logger.info("Loan Detail HESLB =[" + loanid + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<LoanHeslb> response = loanHeslbServices.GetByLoan(authToken, loanid);
			if (response != null) {
				return new ResponseEntity<LoanHeslb>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (HttpClientErrorException ex) {
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
		}catch (Exception ex) {
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

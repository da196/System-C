package tz.go.tcra.hrms.controllers.payroll;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.payroll.LoanFrequency;
import tz.go.tcra.hrms.dto.payroll.LoanLender;
import tz.go.tcra.hrms.dto.payroll.LoanType;
import tz.go.tcra.hrms.dto.Employee;
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
public class LoanController {
	private static final Logger logger = Logger.getLogger(LoanController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private ILoanServices loanServices;
	@Autowired
	private ILoanHeslbServices loanHeslbServices;
	@Autowired
	private LoanTypeServices loanTypeServices;
	@Autowired
	private ILoanFrequencyServices loanFrequencyServices;
	@Autowired
	private ILoanLenderServices loanLenderServices;
	@Autowired
	private IEmployeeServices employeeServices;

	@RequestMapping(value = "/payroll-loans.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// loan
		List<Loan> _loans = null;
		if (loanServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<Loan[]> loans = loanServices.GetAll(authToken);
			if (loans != null && loans.getBody() != null) {
				_loans = Arrays.asList(loans.getBody());
			}
		}
		// loan types
		List<LoanType> _loanTypes = null;
		if (loanTypeServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanType[]> loanTypes = loanTypeServices.GetAll(authToken);
			if (loanTypes != null && loanTypes.getBody() != null) {
				_loanTypes = Arrays.asList(loanTypes.getBody());
			}
		}
		// loan frequency
		List<LoanFrequency> _loanFrequency = null;
		if (loanTypeServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanFrequency[]> loanFrequency = loanFrequencyServices.GetAll(authToken);
			if (loanFrequency != null && loanFrequency.getBody() != null) {
				_loanFrequency = Arrays.asList(loanFrequency.getBody());
			}
		}
		// loan frequency
		List<LoanLender> _loanLender = null;
		if (loanLenderServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanLender[]> loanLender = loanLenderServices.GetAll(authToken);
			if (loanLender != null && loanLender.getBody() != null) {
				_loanLender = Arrays.asList(loanLender.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-loans");
		// add model data
		mv.addObject("loans", _loans);
		mv.addObject("loanTypes", _loanTypes);
		mv.addObject("loanFrequency", _loanFrequency);
		mv.addObject("loanLender", _loanLender);

		return mv;
	}

	@RequestMapping(value = "/payroll-add-loan.htm", method = RequestMethod.GET)
	public ModelAndView add() {
		// initialize
		// employees
		List<Employee> _employees = null;
		if (employeeServices != null) {
			final ResponseEntity<Employee[]> employees = employeeServices.GetAllEmployeesLessDetails();
			if (employees != null && employees.getBody() != null) {
				_employees = Arrays.asList(employees.getBody());
			}
		}

		// loan types
		List<LoanType> _loanTypes = null;
		if (loanTypeServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanType[]> loanTypes = loanTypeServices.GetAll(authToken);
			if (loanTypes != null && loanTypes.getBody() != null) {
				_loanTypes = Arrays.asList(loanTypes.getBody());
			}
		}

		// loan frequency
		List<LoanFrequency> _loanFrequency = null;
		if (loanTypeServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanFrequency[]> loanFrequency = loanFrequencyServices.GetAll(authToken);
			if (loanFrequency != null && loanFrequency.getBody() != null) {
				_loanFrequency = Arrays.asList(loanFrequency.getBody());
			}
		}
		// loan frequency
		List<LoanLender> _loanLender = null;
		if (loanLenderServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanLender[]> loanLender = loanLenderServices.GetAll(authToken);
			if (loanLender != null && loanLender.getBody() != null) {
				_loanLender = Arrays.asList(loanLender.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-loans-add");
		// add model data
		mv.addObject("employees", _employees);
		mv.addObject("loanTypes", _loanTypes);
		mv.addObject("loanFrequency", _loanFrequency);
		mv.addObject("loanLender", _loanLender);

		return mv;
	}

	@RequestMapping(value = "/payroll-add-loan.htm", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> Add(@RequestBody Loan loan) {
		if (loan == null) {
			logger.info("Loan is null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Loan is "+loan.toString());
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// parse loan
			double rate = loan.getInterestrate() / 100;
			loan.setInterestrate(rate);
			// parse dates
			// dd-mm-yyyy to yyyy-mm-dd
			try {
				final String dateIssued = DateUtils.toYYYYMMDD(loan.getDateissued());
				final String dateStartRepayment = DateUtils.toYYYYMMDD(loan.getDaterepaymentstart());
				loan.setDateissued(dateIssued);
				loan.setDaterepaymentstart(dateStartRepayment);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Exception is "+e.toString());
			}
			// create
			final ResponseEntity<Loan> response = loanServices.Create(authToken, loan);			
			int code = response.getStatusCodeValue();
			logger.info(String.format("Contribution status {%d} is not equal to 200",code));
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
				return new ResponseEntity<Loan>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

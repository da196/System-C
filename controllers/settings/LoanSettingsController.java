package tz.go.tcra.hrms.controllers.settings;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.dto.payroll.LoanFrequency;
import tz.go.tcra.hrms.dto.payroll.LoanLender;
import tz.go.tcra.hrms.dto.payroll.LoanType;
import tz.go.tcra.hrms.services.payroll.ILoanFrequencyServices;
import tz.go.tcra.hrms.services.payroll.ILoanLenderServices;
import tz.go.tcra.hrms.services.payroll.LoanTypeServices;

@Controller
public class LoanSettingsController {
	private static final Logger logger = Logger.getLogger(LoanSettingsController.class); // log4j
	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	@Autowired
	private LoanTypeServices loanTypeServices;
	@Autowired
	private ILoanFrequencyServices loanFrequencyServices;
	@Autowired
	private ILoanLenderServices loanLenderServices;
	
	@RequestMapping(value = "/payroll-loan-settings.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// loan types
		List<LoanType> _loanTypes = null;
		if(loanTypeServices!=null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanType[]> loanTypes = loanTypeServices.GetAll(authToken);
			if(loanTypes!=null && loanTypes.getBody()!=null) {
				_loanTypes = Arrays.asList(loanTypes.getBody());
			}
		}
		// loan frequency
		List<LoanFrequency> _loanFrequency = null;
		if(loanTypeServices!=null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanFrequency[]> loanFrequency = loanFrequencyServices.GetAll(authToken);
			if(loanFrequency!=null && loanFrequency.getBody()!=null) {
				_loanFrequency = Arrays.asList(loanFrequency.getBody());
			}
		}
		// loan frequency
		List<LoanLender> _loanLender = null;
		if(loanLenderServices!=null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<LoanLender[]> loanLender = loanLenderServices.GetAll(authToken);
			if(loanLender!=null && loanLender.getBody()!=null) {
				_loanLender = Arrays.asList(loanLender.getBody());
			}
		}
		
		ModelAndView mv = new ModelAndView("payroll-settings/payroll-loan-settings");
		// add model data
		mv.addObject("loanTypes", _loanTypes);
		mv.addObject("loanFrequency", _loanFrequency);
		mv.addObject("loanLender", _loanLender);
		
		return mv;		
	}
}

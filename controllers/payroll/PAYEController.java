package tz.go.tcra.hrms.controllers.payroll;

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

@Controller
public class PAYEController {
	private static final Logger logger = Logger.getLogger(PAYEController.class); // log4j
	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	
	@RequestMapping(value = "/payroll-paye.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// loan
		
		
		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-tax-paye");
		// add model data
		//mv.addObject("loans", _loans);
		
		return mv;		
	}
	
	@RequestMapping(value = "/payroll-add-paye.htm", method = RequestMethod.GET)
	public ModelAndView add() {
		// initialize
		// loan
		
		
		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-tax-paye-add");
		// add model data
		//mv.addObject("loans", _loans);
		
		return mv;		
	}

}

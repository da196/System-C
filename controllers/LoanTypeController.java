package tz.go.tcra.hrms.controllers;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.dto.payroll.LoanType;
import tz.go.tcra.hrms.services.payroll.LoanTypeServices;

@Controller
public class LoanTypeController {
	private static final Logger logger = Logger.getLogger(LoanTypeController.class); // log4j
	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	@Autowired
	private LoanTypeServices loanTypeServices;
	
	@RequestMapping(value = "/loan-type-by-lender.html/{lender_id}", method = RequestMethod.GET)
	public @ResponseBody Object GetByLender(@PathVariable("lender_id")int lender_id, 
			HttpServletRequest request, Model model, 
			RedirectAttributes redirectAttributes) {
		logger.info("Vendor id is "+lender_id);
		if(lender_id<=0) { return null; } 
		final String authToken = authenticationFacade.getAuthenticationToken(); //final
		final ResponseEntity<LoanType[]> response = loanTypeServices.GetAllByVendor(authToken, lender_id); 
		
		return response !=null ? Arrays.asList(response.getBody()) : null; 
	}
}

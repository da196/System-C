package tz.go.tcra.hrms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tz.go.tcra.hrms.auth.AuthenticationFacade;
import tz.go.tcra.hrms.dto.EmployeeBankAccount;
import tz.go.tcra.hrms.services.IEmployeeBankAccountServices;

@Controller
public class EmployeeBankAccountController {

	@Autowired
	private IEmployeeBankAccountServices bankAccountServices;
	@Autowired
	private AuthenticationFacade authenticationFacade;
	
	@RequestMapping(value = "/add-bank-account.htm", method = RequestMethod.POST)
	public String AddBankAccount(@ModelAttribute("employeeBankAccount") @RequestBody EmployeeBankAccount bankAccount) {

		if(bankAccount == null || bankAccount.getEmployeeid() < 1 || bankAccount.getBankid() < 1 || bankAccount.getAccountname().isEmpty() || bankAccount.getAccountnumber().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		try {
			int id = bankAccount.getEmployeeid();
			int createdbyuserid = authenticationFacade.getUser().getEmpId();
			bankAccount.setCreatedbyuserid(createdbyuserid);
			ResponseEntity<EmployeeBankAccount> result = bankAccountServices.AddBankAccount(bankAccount);
			if(result.getStatusCodeValue()!=200) {
				if(result.getStatusCodeValue() == 208) {
					return "redirect:/duplicate-values-error.htm";
				}
				else if(result.getStatusCodeValue() == 400) {
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
}

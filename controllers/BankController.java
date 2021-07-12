package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tz.go.tcra.hrms.dto.Bank;
import tz.go.tcra.hrms.dto.BankBranch;
import tz.go.tcra.hrms.services.IBankBranchServices;
import tz.go.tcra.hrms.services.IBankServices;

@Controller
public class BankController {

	@Autowired
	private IBankServices bankServices;
	@Autowired
	private IBankBranchServices branchServices;
	
	@RequestMapping(value = "/banks.htm", method = RequestMethod.GET)
	public ModelAndView BanksIndex() {
			
		ResponseEntity<Bank[]> result = null;
		ResponseEntity<BankBranch[]> branchResult = branchServices.GetAll();
		if(bankServices != null) {
			result = bankServices.GetAll();
		}
		
		List<Bank> banks = null;
		List<BankBranch> branches = Arrays.asList(branchResult.getBody());
		if(result.getBody() != null) {
			banks = Arrays.asList(result.getBody());
		}
		
		ModelAndView mv = new ModelAndView("hr-base/banks");
		mv.addObject("banks", banks);
		mv.addObject("branches", branches);
		
		return mv;

	}
	
	@RequestMapping(value = "/create-bank.htm", method = RequestMethod.POST)
	public String CreateBank(@ModelAttribute("createBank") @RequestBody Bank bank) {
			if(bank == null || bank.getName().isEmpty()) {
				return "redirect:/null-values-error.htm";
			}
			
			try {
				ResponseEntity<Bank> result = bankServices.Create(bank);
				if(result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/banks.htm";
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				return "redirect:/error.htm";
			}
	}
	
	@RequestMapping(value = "/delete-bank.htm/{id}", method = RequestMethod.GET)
	public String deleteBank(@PathVariable("id") int id) {
			if(id > 0) {
				ResponseEntity<Bank> result = bankServices.Delete(id);
				if(result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/banks.htm";
			}
			return null;
	}
	
	@RequestMapping(value = "/create-branch.htm", method = RequestMethod.POST)
	public String CreateBranch(@ModelAttribute("createBranch") @RequestBody BankBranch branch) {
			if(branch == null || branch.getName().isEmpty() || branch.getBankid() < 1 || branch.getSortcode().isEmpty()) {
				return "redirect:/null-values-error.htm";
			}
			
			try {
				ResponseEntity<BankBranch> result = branchServices.AddBranch(branch);
				if(result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/banks.htm";
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				return "redirect:/error.htm";
			}
	}
	
	@RequestMapping(value = "/delete-branch.htm/{id}", method = RequestMethod.GET)
	public String deleteBranch(@PathVariable("id") int id) {
			if(id > 0) {
				ResponseEntity<BankBranch> result = branchServices.Delete(id);
				if(result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/banks.htm";
			}
			return null;
	}
}

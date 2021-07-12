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

import tz.go.tcra.hrms.dto.SocialSecurity;
import tz.go.tcra.hrms.services.ISocialSecurityServices;

@Controller
public class SocialSecurityController {

	@Autowired
	private ISocialSecurityServices socialSecurityServices;
	
	@RequestMapping(value = "/social-security.htm", method = RequestMethod.GET)
	public ModelAndView SocialSecurityIndex() {
			
		ResponseEntity<SocialSecurity[]> result = null;
		if(socialSecurityServices != null) {
			result = socialSecurityServices.GetAll();
		}
		
		List<SocialSecurity> socialSecurities = null;
		if(result.getBody() != null) {
			socialSecurities = Arrays.asList(result.getBody());
		}
		
		ModelAndView mv = new ModelAndView("hr-base/social-security");
		mv.addObject("socialSecurities", socialSecurities);
		
		return mv;

	}
	
	@RequestMapping(value = "/create-social-security-fund.htm", method = RequestMethod.POST)
	public String CreateBank(@ModelAttribute("createSecurityFund") @RequestBody SocialSecurity socialSecurity) {
			if(socialSecurity == null || socialSecurity.getName().isEmpty()) {
				return "redirect:/null-values-error.htm";
			}
			
			try {
				ResponseEntity<SocialSecurity> result = socialSecurityServices.Create(socialSecurity);
				if(result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/social-security.htm";
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				return "redirect:/error.htm";
			}
	}
	
	@RequestMapping(value = "/delete-security-fund.htm/{id}", method = RequestMethod.GET)
	public String deleteFund(@PathVariable("id") int id) {
			if(id > 0) {
				ResponseEntity<SocialSecurity> result = socialSecurityServices.Delete(id);
				if(result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/social-security.htm";
			}
			return null;
	}
}

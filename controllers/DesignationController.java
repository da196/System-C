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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.dto.BankBranch;
import tz.go.tcra.hrms.dto.Designation;
import tz.go.tcra.hrms.services.IDesignationServices;

@Controller
public class DesignationController {
	
	@Autowired
	private IDesignationServices dServices;
	
	/**
	 * Returns All Designations and Seniority Views.
	 */
	//@SuppressWarnings("unchecked")
	@RequestMapping(value = "/designations.htm", method = RequestMethod.GET)
	public ModelAndView GetAll() {
		//Data Retrieval
		ResponseEntity<Designation[]> result = null;
		if(dServices != null) {
			result = (ResponseEntity<Designation[]>) dServices.GetAll();
		}
		int status = result.getStatusCodeValue();
		System.out.println(status);
		
		List<Designation> designations = null;
		if(result.getBody()!=null) {
			designations = Arrays.asList(result.getBody());
		}	
		
		ModelAndView mv = new ModelAndView("hr-base/designations");
		mv.addObject("designations", designations);
				
		return mv;
	}
	
	/*
	 * Create new Designation
	 * 
	 */
	@RequestMapping(value = "/designation-create.htm", method = RequestMethod.POST)
	public String Create(@ModelAttribute("designation") @RequestBody Designation designation) {
		if(designation == null || designation.getName().isEmpty() || designation.getAbbreviation().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		try {
				ResponseEntity<Designation> result = dServices.Create(designation);
				if(result.getStatusCodeValue()!=200) {
					// process error
				}	
				// Ok? redirect to Contracts
				return "redirect:/designations.htm";
		}catch(Exception ex) 
		{			
			System.out.print(ex.toString());
			ex.printStackTrace();
			return "redirect:/error.htm";
		}
	}
	
	/**
	 * Delete Designation by id.
	 */
	@RequestMapping(value = "/delete-designation.htm/{id}", method = RequestMethod.GET)
	public String deleteDesignation(@PathVariable("id") int id) {
			if(id > 0) {
				ResponseEntity<Designation> result = dServices.Delete(id);
				if(result.getStatusCodeValue() != 200) {
					return "redirect:/error.htm";
				}
				return "redirect:/designations.htm";
			}
			return null;
	}
	
	//Get designation by ID
	@RequestMapping(value = "/get-designation-by-IdAjax/{id}")
	public @ResponseBody Object GetDesignationByIDAjax(@PathVariable("id") int id) {
		if(id > 0) {
			final ResponseEntity<Designation> result = dServices.GetDesignationById(id);
		}
		return null;
	}
	
	//Update designation by Ajax
	@RequestMapping(value = "/update-designation-by-Ajax/{id}")
	public @ResponseBody Object UpdateDesignationByAjax(@PathVariable("id") int id) {
		if(id > 0) {
			final ResponseEntity<Designation> result = dServices.UpdateDesignation(id);
		}
		return null;
	}

}

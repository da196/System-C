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

import tz.go.tcra.hrms.dto.EmploymentCategory;
import tz.go.tcra.hrms.dto.EmploymentStatus;
import tz.go.tcra.hrms.services.IEmploymentCategoryServices;
import tz.go.tcra.hrms.services.IEmploymentStatusServices;

@Controller
public class EmploymentStatusController {
	
	@Autowired
	private IEmploymentStatusServices empStsServices;
	@Autowired
	private IEmploymentCategoryServices empCategoryServices;
	
	@RequestMapping(value = "/employment-status.htm", method = RequestMethod.GET)
	public ModelAndView statusIndex() {
		ResponseEntity<EmploymentStatus[]> result= null;
		ResponseEntity<EmploymentCategory[]> catResult = null;
		if(empStsServices != null) {
			result = (ResponseEntity<EmploymentStatus[]>) empStsServices.GetAll();
			catResult = (ResponseEntity<EmploymentCategory[]>) empCategoryServices.GetAll();
		}
		
		List<EmploymentStatus> empStatuses= null;
		List<EmploymentCategory> empCategories= null;
		if(result.getBody() != null) {
			empStatuses = Arrays.asList(result.getBody());
			empCategories = Arrays.asList(catResult.getBody());
		}
		
		ModelAndView mv = new ModelAndView("hr-base/employment-status");
		mv.addObject("empStatuses", empStatuses);
		mv.addObject("empCategories", empCategories);
		
		return mv;
	}
	
	@RequestMapping(value = "/create-employment-status.htm", method = RequestMethod.POST)
	public String CreateEmployementStatus(@ModelAttribute("newEmployemntStatus") @RequestBody EmploymentStatus status) {
		if(empStsServices != null) {
				try {
					if(status == null || status.getName().isEmpty()) {
						return "redirect:/null-values-error.htm";
					}
					ResponseEntity<EmploymentStatus> result = empStsServices.Create(status);
					if(result.getStatusCodeValue() != 200) {
						return "redirect:/error.htm";
					}
					return "redirect:/employment-status.htm";
				} catch (Exception e) {
					System.out.print(e.toString());
					e.printStackTrace();
					return "redirect:/error.htm";
				}
		}
		return null;
	}
	
	@RequestMapping(value = "/delete-employment-status.htm/{id}", method = RequestMethod.GET)
	public String DeleteEmployementStatus(@PathVariable("id") int id) {
		if(id>0) {
			ResponseEntity<EmploymentStatus> result = empStsServices.Delete(id);
			if(result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/employment-status.htm";
		}
		return null;
	}
	
	@RequestMapping(value = "/create-employment-category.htm", method = RequestMethod.POST)
	public String CreateEmployementCategory(@ModelAttribute("newEmployemntCategory") @RequestBody EmploymentCategory employmentCategory) {
		if(empCategoryServices != null) {
				try {
					if(employmentCategory == null || employmentCategory.getName().isEmpty()) {
						return "redirect:/null-values-error.htm";
					}
					ResponseEntity<EmploymentCategory> result = empCategoryServices.Create(employmentCategory);
					if(result.getStatusCodeValue() != 200) {
						return "redirect:/error.htm";
					}
					return "redirect:/employment-status.htm";
				} catch (Exception e) {
					System.out.print(e.toString());
					e.printStackTrace();
					return "redirect:/error.htm";
				}
		}
		return null;
	}
	
	@RequestMapping(value = "/delete-employment-category.htm/{id}", method = RequestMethod.GET)
	public String DeleteEmployementCategory(@PathVariable("id") int id) {
		if(id>0) {
			ResponseEntity<EmploymentCategory> result = empCategoryServices.Delete(id);
			if(result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/employment-status.htm";
		}
		return null;
	}

}

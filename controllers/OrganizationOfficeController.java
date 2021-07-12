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
import tz.go.tcra.hrms.dto.City;
import tz.go.tcra.hrms.dto.GeneralSettings;
import tz.go.tcra.hrms.dto.OrganizationOffices;
import tz.go.tcra.hrms.services.ICityServices;
import tz.go.tcra.hrms.services.IOrganizationOfficesServices;

@Controller
public class OrganizationOfficeController {
	
	@Autowired
	private IOrganizationOfficesServices organizationServices;
	@Autowired
	private ICityServices cityServices;
	
	@RequestMapping(value = "/organization-offices.htm", method = RequestMethod.GET)
	public ModelAndView GetOrganizationOffices() {
		ResponseEntity<OrganizationOffices[]> result = null;
		ResponseEntity<GeneralSettings[]> categoryResult = organizationServices.GetOrganizationCategory();
		ResponseEntity<City[]> cityResult = cityServices.GetAll();
		
		if(organizationServices != null) {
			result = organizationServices.GetOrganizationOffice();
		}
		
		List<OrganizationOffices> offices = null;
		List<GeneralSettings> categories = Arrays.asList(categoryResult.getBody());
		List<City> cities = Arrays.asList(cityResult.getBody());
		
		if(result.getBody() != null) {
			offices = Arrays.asList(result.getBody());
		}
		
		ModelAndView mv = new ModelAndView("hr-base/organization-offices");
		mv.addObject("offices", offices);
		mv.addObject("categories", categories);
		mv.addObject("cities", cities);
		
		return mv;
	}
	
	@RequestMapping(value = "/create-orgnanization-office.htm", method = RequestMethod.POST)
	public String CreateOrganizationOffice(@ModelAttribute("newOrganizationOffice") @RequestBody OrganizationOffices office) {
		if(organizationServices != null) {
				try {
					if(office == null || office.getName().isEmpty() || office.getOfficetypeid() < 1 || office.getCityid() < 1) {
						return "redirect:/null-values-error.htm";
					}
					ResponseEntity<OrganizationOffices> result = organizationServices.CreateOffice(office);
					if(result.getStatusCodeValue() != 200) {
						return "redirect:/error.htm";
					}
					return "redirect:/organization-offices.htm";
				} catch (Exception e) {
					System.out.print(e.toString());
					e.printStackTrace();
					return "redirect:/error.htm";
				}
		}
		return null;
	}
	
	@RequestMapping(value = "/delete-organization-office.htm/{id}", method = RequestMethod.GET)
	public String DeleteRelativeCategory(@PathVariable("id") int id) {
		if(id>0) {
			ResponseEntity<OrganizationOffices> result = organizationServices.DeleteOffice(id);
			if(result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/organization-offices.htm";
		}
		return null;
	}

}

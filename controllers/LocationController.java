package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.City;
import tz.go.tcra.hrms.dto.Location;
import tz.go.tcra.hrms.services.ICityServices;
import tz.go.tcra.hrms.services.ILocationServices;

@Controller
public class LocationController {

	@Autowired
	private ILocationServices locationServices;
	@Autowired
	private ICityServices cityServices;
	
	/**
	 * Returns Countries.
	 */
	@RequestMapping(value = "/locations.htm", method = RequestMethod.GET)
	public ModelAndView GetAll() {		
		ResponseEntity<Location[]> result = null;
		ResponseEntity<City[]> cResult = null;
		if(locationServices!=null) {
			result = (ResponseEntity<Location[]>) locationServices.GetAll();
			cResult = (ResponseEntity<City[]>) cityServices.GetAll();
		}
		
		List<Location> locations = null;
		List<City> cities = null;
		if(result.getBody() != null) {
			locations = Arrays.asList(result.getBody());
			cities = Arrays.asList(cResult.getBody());
		}
		
		// 2. pass result to the view
		ModelAndView mv = new ModelAndView("hr-base/locations");
		mv.addObject("locations", locations);
		mv.addObject("cities", cities);
		
		return mv;
	}
	
	/**
	 * Create country.
	 */	
	@RequestMapping(value = "/country-create.htm", method = RequestMethod.POST)
	public String Create(@ModelAttribute("country") Location country) {
		if(country==null || country.getName().isEmpty()) {
			return "redirect:/error.htm";
		}		
		// invoke add country service
		ResponseEntity<Location> result = locationServices.Create(country);
		if(result==null) {
			return "redirect:/error.htm";
		}
		// check http status
		HttpStatus status = result.getStatusCode();
		if(status.value()==HttpStatusCodes.NOT_FOUND) {
			return "redirect:/error.htm";
		}
		else if(status.value()==HttpStatusCodes.NOT_AUTHORIZED) {
			return "redirect:/error.htm";
		}
		// redirect to countries
		return "redirect:/countries.htm";
		
		// debug
		/*ModelAndView mv = new ModelAndView("hr-base/country");
		mv.addObject("code", country.getCodeName());
		mv.addObject("name", country.getCountryName());
		return mv;*/
	}
	
	/**
	 * Delete country.
	 */	
	@RequestMapping(value = "/country-delete.htm", method = RequestMethod.DELETE)
	public String DeleteCountry(int id) {
		//return "redirect:/countries.htm";
		return null;
	}
	

}

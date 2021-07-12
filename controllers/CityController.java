package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tz.go.tcra.hrms.dto.City;
import tz.go.tcra.hrms.services.ICityServices;

@Controller
public class CityController {
	
	@Autowired
	private ICityServices cityServices;
	
	/**
	 * Returns Countries.
	 */
	@RequestMapping(value = "/cities.htm", method = RequestMethod.GET)
	public ModelAndView GetAll() {		
		ResponseEntity<City[]> result = null;		
		if(cityServices!=null) {
			result = (ResponseEntity<City[]>) cityServices.GetAll();	
		}
		
		List<City> cities = null;
		if(result.getBody() != null) {
			cities = Arrays.asList(result.getBody());
		}
		
		// 2. pass result to the view
		ModelAndView mv = new ModelAndView("hr-base/locations");
		mv.addObject("cities", cities);
		
		return mv;
	}

}

package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tz.go.tcra.hrms.dto.Nationality;
import tz.go.tcra.hrms.services.INationalityServices;

@Controller
public class NationalityController {
	
	@Autowired
	private INationalityServices nationalityServices;
	
	/**
	 * Returns Countries.
	 */
	@RequestMapping(value = "/nationalities.htm", method = RequestMethod.GET)
	public ModelAndView GetAll() {		
		ResponseEntity<Nationality[]> result = null;		
		if(nationalityServices!=null) {
			result = (ResponseEntity<Nationality[]>) nationalityServices.GetAll();	
		}
		
		List<Nationality> nationalities = null;
		if(result.getBody() != null) {
			nationalities = Arrays.asList(result.getBody());
		}
		
		// 2. pass result to the view
		ModelAndView mv = new ModelAndView("hr-base/nationalities");
		mv.addObject("nationalities", nationalities);
		
		return mv;
	}

}

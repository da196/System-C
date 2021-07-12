package tz.go.tcra.hrms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	@RequestMapping(value = "/error.htm")
	public ModelAndView Error() {		
		// 2. pass result view
		ModelAndView mv = new ModelAndView("common/error");		
		return mv;
	}
	
	@RequestMapping(value = "/null-values-error.htm")
	public ModelAndView NullValueError() {		
		// 2. pass result view
		ModelAndView mv = new ModelAndView("common/null-values-error");		
		return mv;
	}
	
	@RequestMapping(value = "/employee-duplicate-values-error.htm")
	public ModelAndView DuplicateValueError() {		
		// 2. pass result view
		ModelAndView mv = new ModelAndView("common/employee-duplicate-values-error");		
		return mv;
	}
	
	@RequestMapping(value = "/duplicate-values-error.htm")
	public ModelAndView ModelDuplicateValueError() {		
		// 2. pass result view
		ModelAndView mv = new ModelAndView("common/duplicate-values-error");		
		return mv;
	}
	
	@RequestMapping(value = "/not-authorized-error.htm")
	public ModelAndView NotAuthorizedError() {		
		// 2. pass result view
		ModelAndView mv = new ModelAndView("common/not-authorized-error");		
		return mv;
	}
	
	@RequestMapping(value = "/not-found.htm")
	public ModelAndView NotFoundError() {		
		// 2. pass result view
		ModelAndView mv = new ModelAndView("common/not-found");		
		return mv;
	}
	
	@RequestMapping(value = "/bad-request.htm")
	public ModelAndView BadRequest() {		
		// 2. pass result view
		ModelAndView mv = new ModelAndView("common/bad-request");		
		return mv;
	}
	
	@RequestMapping(value = "/service-unavailable.htm")
	public ModelAndView ServiceUnavailable() {		
		// 2. pass result view
		ModelAndView mv = new ModelAndView("common/service-unavailable");		
		return mv;
	}
	

}

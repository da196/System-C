package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.dto.EducationLevel;
import tz.go.tcra.hrms.dto.EmployeeStatistics;
import tz.go.tcra.hrms.services.IEducationLevelServices;
import tz.go.tcra.hrms.services.IEmployeeStatisticsServices;

@Controller
public class AppController {
	@Autowired
	private IEmployeeStatisticsServices empStatServices;
	@Autowired
	private IEducationLevelServices eduServices;
	
	/**
	 * Returns index.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "dashboard";
	}
	
	/*
	 * Dashboard grid total number
	 */
	@RequestMapping(value = "/dashboard.htm", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		//Grid statistics
		EmployeeStatistics typeT =null;
		EmployeeStatistics typeM =null;
		EmployeeStatistics typeF =null;
		ResponseEntity<EmployeeStatistics> responseT = empStatServices.GetByType(0);
		ResponseEntity<EmployeeStatistics> responseM = empStatServices.GetByType(1);
		ResponseEntity<EmployeeStatistics> responseF = empStatServices.GetByType(2);
		typeT = responseT.getBody();
		typeM = responseM.getBody();
		typeF = responseF.getBody();
		
		//Units/Departments Distributions
		ResponseEntity<EmployeeStatistics[]> depts = empStatServices.GetUnitDistribution();	
		
		//Education Levels
		ResponseEntity<EducationLevel[]> edu = eduServices.GetAll();
		
		List<EmployeeStatistics> departments = Arrays.asList(depts.getBody());
		List<EducationLevel> eduLevels = Arrays.asList(edu.getBody());
		
		
		
		// Pass objects result to view
		ModelAndView mv = new ModelAndView("/dashboard");
		mv.addObject("typeT", typeT);
		mv.addObject("typeM", typeM);
		mv.addObject("typeF", typeF);
		mv.addObject("departments", departments);
		mv.addObject("eduLevels", eduLevels);
		
		return mv;
	}
	
	/**
	 * Returns Login Page.
	 */
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
}

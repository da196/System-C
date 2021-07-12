package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tz.go.tcra.hrms.dto.Country;
import tz.go.tcra.hrms.dto.EmployeeRelative;
import tz.go.tcra.hrms.dto.Nationality;
import tz.go.tcra.hrms.dto.RelativeCategory;
import tz.go.tcra.hrms.services.IEmployeeRelativesServices;

@Controller
public class EmployeeRelativesController {
	
	@Autowired
	private IEmployeeRelativesServices relativeServices;
	
	//Post/Add employee relative by ajax
	@RequestMapping(value = "/add-relative-employeeAjax", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody Object GetAddEmployeeRelative(@RequestBody EmployeeRelative relative) {
		final ResponseEntity<EmployeeRelative> result = relativeServices.AddRelative(relative);
		if (result.getStatusCodeValue() != 200) {
			if(result.getStatusCodeValue() == 208) {
				return "redirect:/duplicate-values-error.htm";
			}else if(result.getStatusCodeValue() == 400) {
				return "redirect:/bad-request.htm";
			}else if(result.getStatusCodeValue() == 401) {
				return "redirect:/not-authorized-error.htm";
			}else if(result.getStatusCodeValue() == 404) {
				return "redirect:/not-found.htm";
			}else if(result.getStatusCodeValue() == 412) {
				return "redirect:/bad-request.htm";
			}else if(result.getStatusCodeValue() == 500) {
				return "redirect:/bad-request.htm";
			}else if(result.getStatusCodeValue() == 503) {
				return "redirect:/service-unavailable.htm";
			}else {
				return "redirect:/error.htm";
			}
		}
		return result.getStatusCodeValue();
	}
	
	//Get countries
	@RequestMapping(value = "/get-countriesAjax")
	public @ResponseBody Object GetCountriesAjax() {
		final ResponseEntity<Country[]> result = relativeServices.GetAllCountries();
		if(result.getBody() != null) {
			final List<Country> countries = Arrays.asList(result.getBody());
			return countries;
		}
		return result.getStatusCodeValue();
	}
	
	//Get Nationalities
	@RequestMapping(value = "/get-nationalitiesAjax")
	public @ResponseBody Object GetNationalitiesAjax() {
		final ResponseEntity<Nationality[]> result = relativeServices.GetAllNationalities();
		if(result.getBody() != null) {
			final List<Nationality> nationalities = Arrays.asList(result.getBody());
			return nationalities;
		}
		return result.getStatusCodeValue();
	}
	
	//Get Relative categories
	@RequestMapping(value = "/get-relative-categoriesAjax")
	public @ResponseBody Object GetRelativeCategoriesAjax() {
		final ResponseEntity<RelativeCategory[]> result = relativeServices.GetRelativeCategory();
		if(result.getBody() != null) {
			final List<RelativeCategory> categories = Arrays.asList(result.getBody());
			return categories;
		}
		return result.getStatusCodeValue();
	}

}

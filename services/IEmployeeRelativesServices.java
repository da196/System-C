package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.Country;
import tz.go.tcra.hrms.dto.EmployeeRelative;
import tz.go.tcra.hrms.dto.Nationality;
import tz.go.tcra.hrms.dto.RelativeCategory;

@Component
public interface IEmployeeRelativesServices {

	//Add employee relative
	ResponseEntity<EmployeeRelative> AddRelative(EmployeeRelative relative);
	
	//Get Countries
	ResponseEntity<Country[]> GetAllCountries();
	
	//Get all Nationalities
	ResponseEntity<Nationality[]> GetAllNationalities();
	
	//Get relative categories
	ResponseEntity<RelativeCategory[]> GetRelativeCategory();
}

package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Gender;

@Component
public interface IGenderServices {
	
	//Get genders
	ResponseEntity<Gender[]> GetAll();
	
	//Save Gender
	ResponseEntity<Gender> Create(Gender gender);

}

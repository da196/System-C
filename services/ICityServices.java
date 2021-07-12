package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.City;

@Component
public interface ICityServices {
	
	// get all countries
	ResponseEntity<City[]> GetAll();

}

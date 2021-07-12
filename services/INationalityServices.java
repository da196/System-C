package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Nationality;

@Component
public interface INationalityServices {
	
	// get all countries
	ResponseEntity<Nationality[]> GetAll();	
	
	// create country
	ResponseEntity<Nationality> Create(Nationality nationality);
	
	//Delete Country
	void deleteNationality(Long id);

}

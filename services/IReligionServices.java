package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Religion;

@Component
public interface IReligionServices {

	//Get religions
	ResponseEntity<Religion[]> GetAll();
	
	//Save Religion
	ResponseEntity<Religion> Create(Religion religion);
}

package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Location;

@Component
public interface ILocationServices {
	// get all countries
	ResponseEntity<Location[]> GetAll();	
	
	// create country
	ResponseEntity<Location> Create(Location location);
	
	//Delete Country
	void deleteLocation(Long id);
}

package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import tz.go.tcra.hrms.dto.Salutation;

public interface ISalutationServices {
	
	//Get all salutations
	ResponseEntity<Salutation[]> GetAll();

}

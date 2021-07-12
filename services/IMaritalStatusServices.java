package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.MaritalStatus;

@Component
public interface IMaritalStatusServices {
	//Get marital status
	ResponseEntity<MaritalStatus[]> GetAll();
	
	//Save marital status
	ResponseEntity<MaritalStatus> Create(MaritalStatus marital_status);

}

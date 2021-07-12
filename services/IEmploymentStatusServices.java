package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.EmploymentStatus;

@Component
public interface IEmploymentStatusServices {

	//Get EmploymentStatuses
	ResponseEntity<EmploymentStatus[]> GetAll();
	
	//Create Employment status
	ResponseEntity<EmploymentStatus> Create(EmploymentStatus status);
	
	//Delete employment status
	ResponseEntity<EmploymentStatus> Delete(int id);
	
}

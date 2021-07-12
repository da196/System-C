package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Designation;

@Component
public interface IDesignationServices {
	
	//Object to get/retrieve all countries;
	ResponseEntity<Designation[]> GetAll();
	
	// create designation
	ResponseEntity<Designation> Create(Designation designation);
	
	//Delete
	ResponseEntity<Designation> Delete(int id);
	
	//Get Supervisors
	ResponseEntity<Designation[]> GetSupervisor();
	
	//Get Designation by id
	ResponseEntity<Designation> GetDesignationById(int id);
	
	//Update designation
	ResponseEntity<Designation> UpdateDesignation(int id);

}

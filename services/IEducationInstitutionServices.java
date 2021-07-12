package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.EducationInstitution;

@Component
public interface IEducationInstitutionServices {

	//all institutions
	ResponseEntity<EducationInstitution[]> GetAll();
	
	//New Institution
	ResponseEntity<EducationInstitution> Create(EducationInstitution institution);
	
}

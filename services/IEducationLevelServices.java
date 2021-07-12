package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.EducationLevel;

@Component
public interface IEducationLevelServices {
	
	//All levels
	ResponseEntity<EducationLevel[]> GetAll();
	
	//New Education level
	ResponseEntity<EducationLevel> Create(EducationLevel level);

}

package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.EmploymentCategory;

@Component
public interface IEmploymentCategoryServices {
	//Get EmploymentCategories
	ResponseEntity<EmploymentCategory[]> GetAll();
	
	//Add new employment category
	ResponseEntity<EmploymentCategory> Create(EmploymentCategory employmentCategory);
	
	//Delete employment category
	ResponseEntity<EmploymentCategory> Delete(int id);

}

package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.SalaryScale;

@Component
public interface ISalaryScaleServices {

	//Salary Scales
	ResponseEntity<SalaryScale[]> GetAll();
	
	//Post Scale
	ResponseEntity<SalaryScale> Create(SalaryScale salaryScale);	
	ResponseEntity<SalaryScale> getSalaryScaleByID(int id);
	ResponseEntity<SalaryScale> deleteSalaryScale(int id);
	ResponseEntity<SalaryScale> updateSalaryScale(SalaryScale salaryScale, int id);
	
}

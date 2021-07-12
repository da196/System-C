package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.InsuranceType;

@Component
public interface IInsuranceTypeServices {
	ResponseEntity<InsuranceType[]> GetAll(String authToken);
	ResponseEntity<InsuranceType> Get(String authToken,int id);
	ResponseEntity<InsuranceType> Create(String authToken,InsuranceType type);
	ResponseEntity<InsuranceType> Update(String authToken,int id,InsuranceType type);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

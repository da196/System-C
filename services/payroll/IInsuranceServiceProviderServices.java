package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.InsuranceServiceProvider;

@Component
public interface IInsuranceServiceProviderServices {
	ResponseEntity<InsuranceServiceProvider[]> GetAll(String authToken);
	ResponseEntity<InsuranceServiceProvider[]> GetAllByEmployee(String authToken,int employeeId);
	ResponseEntity<InsuranceServiceProvider> Get(String authToken,int id);
	ResponseEntity<InsuranceServiceProvider> Create(String authToken,InsuranceServiceProvider provider);
	ResponseEntity<InsuranceServiceProvider> Update(String authToken,int id,InsuranceServiceProvider provider);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

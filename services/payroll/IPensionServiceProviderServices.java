package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.PensionServiceProvider;

@Component
public interface IPensionServiceProviderServices {
	ResponseEntity<PensionServiceProvider[]> GetAll(String authToken);
	ResponseEntity<PensionServiceProvider[]> GetAllByEmployee(String authToken,int employeeId);
	ResponseEntity<PensionServiceProvider> Get(String authToken,int id);
	ResponseEntity<PensionServiceProvider> Create(String authToken,PensionServiceProvider provider);
	ResponseEntity<PensionServiceProvider> Update(String authToken,int id,PensionServiceProvider provider);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

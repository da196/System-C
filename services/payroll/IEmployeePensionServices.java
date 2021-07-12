package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.EmployeePension;

@Component
public interface IEmployeePensionServices {
	ResponseEntity<EmployeePension[]> GetAll(String authToken);
	ResponseEntity<EmployeePension> Get(String authToken,int id);
	ResponseEntity<EmployeePension> Create(String authToken,EmployeePension pension);
	ResponseEntity<EmployeePension> Update(String authToken,int id,EmployeePension pension);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

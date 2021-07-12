package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.EmployeeInsurance;

@Component
public interface IEmployeeInsuranceServices {
	ResponseEntity<EmployeeInsurance[]> GetAll(String authToken);
	ResponseEntity<EmployeeInsurance> Get(String authToken,int id);
	ResponseEntity<EmployeeInsurance> Create(String authToken,EmployeeInsurance insurance);
	ResponseEntity<EmployeeInsurance> Update(String authToken,int id,EmployeeInsurance insurance);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

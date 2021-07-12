package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.PayrollType;

@Component
public interface IPayrollTypeServices {
	ResponseEntity<PayrollType[]> GetAll(String authToken);
	ResponseEntity<PayrollType> Get(String authToken,int id);
	ResponseEntity<PayrollType> Create(String authToken,PayrollType type);
	ResponseEntity<PayrollType> Update(String authToken,int id,PayrollType type);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

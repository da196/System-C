package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.LoanFrequency;

@Component
public interface ILoanFrequencyServices {
	ResponseEntity<LoanFrequency[]> GetAll(String authToken);
	ResponseEntity<LoanFrequency> Get(String authToken,int id);
	ResponseEntity<LoanFrequency> Create(String authToken,LoanFrequency frquency);
	ResponseEntity<LoanFrequency> Update(String authToken,int id,LoanFrequency frquency);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

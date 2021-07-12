package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.LoanLender;

@Component
public interface ILoanLenderServices {
	ResponseEntity<LoanLender[]> GetAll(String authToken);
	ResponseEntity<LoanLender> Get(String authToken,int id);
	ResponseEntity<LoanLender> Create(String authToken,LoanLender lender);
	ResponseEntity<LoanLender> Update(String authToken,int id,LoanLender lender);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.LoanHeslb;

@Component
public interface ILoanHeslbServices {
	ResponseEntity<LoanHeslb[]> GetAll(String authToken);
	ResponseEntity<LoanHeslb> Get(String authToken,int id);
	ResponseEntity<LoanHeslb> GetByLoan(String authToken,int loanId);
	// create
	ResponseEntity<LoanHeslb> Create(String authToken,LoanHeslb loan);
	// update
	ResponseEntity<LoanHeslb> Update(String authToken,int id,LoanHeslb loan);
	// delete
	ResponseEntity<Integer> Delete(String authToken,int id);
}

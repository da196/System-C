package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.LoanType;

@Component
public interface ILoanTypeServices {
	ResponseEntity<LoanType[]> GetAll(String authToken);
	ResponseEntity<LoanType> Get(String authToken,int id);
	ResponseEntity<LoanType> Create(String authToken,LoanType type);
	ResponseEntity<LoanType> Update(String authToken,int id,LoanType type);
	ResponseEntity<Integer> Delete(String authToken,int id);
	ResponseEntity<LoanType[]> GetAllByVendor(String authToken, int lenderid);
}

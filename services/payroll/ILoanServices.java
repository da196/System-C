package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.Loan;

@Component
public interface ILoanServices {
	ResponseEntity<Loan[]> GetAll(String authToken);
	ResponseEntity<Loan[]> GetAllByEmployee(String authToken,int employeeId);
	ResponseEntity<Loan[]> GetAllByUnit(String authToken,int unitId);
	ResponseEntity<Loan[]> GetAllByUnitType(String authToken,int unitTypeId);
	ResponseEntity<Loan[]> GetAllByStatus(String authToken,int status);
	ResponseEntity<Loan[]> GetAllByDateRange(String authToken,int dateMin,int dateMax);
	ResponseEntity<Loan> Get(String authToken,int id);
	// create
	ResponseEntity<Loan> Create(String authToken,Loan loan);
	// update
	ResponseEntity<Loan> Update(String authToken,int id,Loan loan);
	// delete
	ResponseEntity<Integer> Delete(String authToken,int id);
}

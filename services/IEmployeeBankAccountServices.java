package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.EmployeeBankAccount;

@Component
public interface IEmployeeBankAccountServices {

	//Get employee bank accounts
	ResponseEntity<EmployeeBankAccount[]> GetAll(int empId);
	
	//Add employee bank accounts
	ResponseEntity<EmployeeBankAccount> AddBankAccount(EmployeeBankAccount bankAccount);
}

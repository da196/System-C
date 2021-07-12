package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.Bank;

@Component
public interface IBankServices {

	//Get all banks
	ResponseEntity<Bank[]> GetAll();
	
	//Add New Bank
	ResponseEntity<Bank> Create(Bank bank);
	
	//Delete bank
	ResponseEntity<Bank> Delete(int id);
}

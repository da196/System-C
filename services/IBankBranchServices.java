package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.BankBranch;

@Component
public interface IBankBranchServices {

	//Get bank branches
	ResponseEntity<BankBranch[]> GetAll();
	
	//Add bank branch
	ResponseEntity<BankBranch> AddBranch(BankBranch branch);
	
	//Delete Branch
	ResponseEntity<BankBranch> Delete(int id);
	
	//Get bank Branches by bank id
	ResponseEntity<BankBranch[]> GetBranchesByBankId(int id);
	
}

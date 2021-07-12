package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Allowance;
import tz.go.tcra.hrms.dto.AllowanceType;

@Component
public interface IAllowanceServices {
	
	//CRUD Allowances
	ResponseEntity<Allowance[]> GetAllowance();
	ResponseEntity<Allowance[]> GetAllowanceDetails();
	ResponseEntity<Allowance> AddAllowance(Allowance allowance);
	ResponseEntity<Allowance> getAllowanceeByID(int id);
	ResponseEntity<Allowance> deleteAllowance(int id);
	ResponseEntity<Allowance> updateAllowance(Allowance allowanceType, int id);


	//CRUD Allowance type
	ResponseEntity<AllowanceType[]> GetAllowanceTypes();
	ResponseEntity<Allowance> AddAllowanceType(Allowance allowanceType);
	ResponseEntity<Allowance> getAllowanceTypeByID(int id);
	ResponseEntity<Allowance> deleteAllowancetype(int id);
	ResponseEntity<Allowance> updateAllowanceType(Allowance allowanceType, int id);

}

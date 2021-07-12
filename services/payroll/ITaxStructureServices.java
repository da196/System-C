package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.TaxStructure;

@Component
public interface ITaxStructureServices {
	ResponseEntity<TaxStructure[]> GetAll(String authToken);
	ResponseEntity<TaxStructure> Get(String authToken,int id);
	ResponseEntity<TaxStructure> GetByAmount(String authToken,double amount);
	ResponseEntity<TaxStructure> Create(String authToken,TaxStructure structure);
	ResponseEntity<TaxStructure> Update(String authToken,int id,TaxStructure structure);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

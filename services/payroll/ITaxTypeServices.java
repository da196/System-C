package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.TaxType;

@Component
public interface ITaxTypeServices {
	ResponseEntity<TaxType[]> GetAll(String authToken);
	ResponseEntity<TaxType> Get(String authToken,int id);
	ResponseEntity<TaxType> Create(String authToken,TaxType type);
	ResponseEntity<TaxType> Update(String authToken,int id,TaxType type);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

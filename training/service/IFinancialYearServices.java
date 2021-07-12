package tz.go.tcra.hrms.training.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.training.entity.FinancialYear;



@Component
public interface IFinancialYearServices {
	ResponseEntity<FinancialYear[]> GetAll(String authToken);
}

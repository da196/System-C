package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Unit;

@Component
public interface IUnitServices {
	//Get Units
	ResponseEntity<Unit[]> GetAll();
	
	//Save Units
	ResponseEntity<Unit> Create(Unit unit);
	
	//Get Sections
	ResponseEntity<Unit[]> GetSection();
	
	//Get all Directorates and units
	ResponseEntity<Unit[]> GetDirAndUnits();
	
	//Get child department from provided parent id
	ResponseEntity<Unit[]> GetChildUnitFromParent(int id);
}

package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.EmployeeStatistics;

@Component
public interface IEmployeeStatisticsServices {

	ResponseEntity<EmployeeStatistics> GetByType(int id);
	
	ResponseEntity<EmployeeStatistics[]> GetUnitDistribution();
}

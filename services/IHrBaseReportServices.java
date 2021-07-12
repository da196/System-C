package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.HrBaseReport;

@Component
public interface IHrBaseReportServices {
	
	//Get employee distributions by directorate
	ResponseEntity<HrBaseReport> GetEmployeeDistributionByDirectorate();
	
	//Get age average per directorate
	ResponseEntity<HrBaseReport> GetageAveragePerDirectorate();
	
	//Age Distribution Top Staff
	ResponseEntity<HrBaseReport> AgeDistributionTopStaff();
	
	//Employee distribution by location
	ResponseEntity<HrBaseReport> EmployeeDistributionByLocation();
	
	//Get Top/Senior staff counter per gender
	ResponseEntity<HrBaseReport> TopPositionsGenderDistribution();
	
	//Get number of staff by age
	ResponseEntity<HrBaseReport> GetNumberOfStaffByAgeGroups();
	
	//Staff Distribution By AgeGroups Per Directorates
	ResponseEntity<HrBaseReport> StaffDistributionByAgeGroupsPerDirectorates();
	
	//Get employees by employment exit statuses
	ResponseEntity<HrBaseReport> EmployeesByEmployemntExitStatus();

}

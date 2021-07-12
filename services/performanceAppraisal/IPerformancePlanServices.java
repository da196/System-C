package tz.go.tcra.hrms.services.performanceAppraisal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.performanceAppraisal.PerformancePlan;
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformancePlanSummary;

@Component
public interface IPerformancePlanServices {
	
	//Adding new performance plan
	ResponseEntity<PerformancePlan> AddNewPerfomancePlan(PerformancePlan plan);
	
	//Retrieve all performance plans
	ResponseEntity<PerformancePlan[]> GetAllPerformancePlans();
	
	//Retrieve compiled List of Goals, Objectives and Targets by Plan ID
	ResponseEntity<PerformancePlanSummary> GetCompiledList(int id);
	
	//Delete/deactivate performance plan
	ResponseEntity<PerformancePlan> DeletePlan(int id);

}

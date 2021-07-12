package tz.go.tcra.hrms.services.performanceAppraisal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceTargets;

@Component
public interface IPerformanceTargetServices {

	//Get all performance targets
	ResponseEntity<PerformanceTargets[]> GetAllTargets();
	
	//Add new target by objective id
	ResponseEntity<PerformanceTargets> AddNewTargets(PerformanceTargets target);
	
	//Get performance targets by objective ID
	ResponseEntity<PerformanceTargets[]> GetTargetsByObjectiveId(int id);
	
	//Edit strategic target
	ResponseEntity<PerformanceTargets> EditStrategicTarget(int id);
	
	//Delete strategic target
	ResponseEntity<PerformanceTargets> DeleteStrategicTarget(int id);
}

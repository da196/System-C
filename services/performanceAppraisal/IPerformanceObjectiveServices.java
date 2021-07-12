package tz.go.tcra.hrms.services.performanceAppraisal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceObjective;

@Component
public interface IPerformanceObjectiveServices {

	//Add performance objective
	ResponseEntity<PerformanceObjective> AddNewPerformanceObjective(PerformanceObjective objective);
	
	//Retrieve (GET) all performance objectives
	ResponseEntity<PerformanceObjective[]> GetAllObjectives();
	
	//Get Objectives by goal ID
	ResponseEntity<PerformanceObjective[]> GetObjectivesByGoalId(int id);
	
	//Edit strategic objectives
	ResponseEntity<PerformanceObjective> EditStrategicObjective(int id);
	
	//Delete strategic objective
	ResponseEntity<PerformanceObjective> DeleteStrategicObjective(int id);
	
	//Get objective by objective ID
	ResponseEntity<PerformanceObjective> GetObjectiveByObjectiveId(int id);
}

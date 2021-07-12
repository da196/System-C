package tz.go.tcra.hrms.services.performanceAppraisal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceGoal;

@Component
public interface IPerformanceGoalServices {

	//Add performance goal
	ResponseEntity<PerformanceGoal> AddNewPerformanceGoal(PerformanceGoal goal);
	
	//Retrieve all performance goals
	ResponseEntity<PerformanceGoal[]> GetAll();
	
	//Get performance goals by plan ID
	ResponseEntity<PerformanceGoal[]> GetGoalByPlanId(int id);
	
	//Edit performance goal
	ResponseEntity<PerformanceGoal> EditStrategicGoal(int id);
	
	//Delete performance Goal
	ResponseEntity<PerformanceGoal> DeleteStrategicGoal(int id);
	
	//Get strategic goal by goal Id
	ResponseEntity<PerformanceGoal> GetGoalByGoalId(int id);
}

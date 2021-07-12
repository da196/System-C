package tz.go.tcra.hrms.services.appraisalImplementationPlan;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationActivity;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationActivityResponsible;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationOutcome;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationOutput;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationPlan;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationTarget;

@Component
public interface IImplementationPlanServices {
	
	//Add new objective outcome
	ResponseEntity<ImplementationOutcome> AddNewOutcome(ImplementationOutcome outcome);
	
	//Retrieve all objective outcome
	ResponseEntity<ImplementationOutcome[]> GetAllOutcomes();
	
	//Add new outcome activity
	ResponseEntity<ImplementationActivity> AddNewActivity(ImplementationActivity activity);
	
	//Retrieve all outcome activity
	ResponseEntity<ImplementationActivity[]> GetAllActivities();
	
	//Add activity output
	ResponseEntity<ImplementationOutput> AddNewOutput(ImplementationOutput output);
	
	//Retrieve all activity output
	ResponseEntity<ImplementationOutput[]> GetAllOutputs();
	
	//Add output target
	ResponseEntity<ImplementationTarget> AddNewTarget(ImplementationTarget target);
	
	//Retrieve all targets
	ResponseEntity<ImplementationTarget[]> GetAllTargets();
	
	//Add responsible
	ResponseEntity<ImplementationActivityResponsible> AddResponsible(ImplementationActivityResponsible responsible);
	
	//Retrieve all activities/targets responsible
	ResponseEntity<ImplementationActivityResponsible[]> GetAllResponsible();
	
	//Get compiled list of implementation plan
	ResponseEntity<ImplementationPlan> GetCompiledImplementationPlanList(int planID, int goalID, int objectiveID);

}

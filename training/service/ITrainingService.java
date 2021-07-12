package tz.go.tcra.hrms.training.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.TrainingApprovalWorkflow;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.dto.TrainingResponse;
import tz.go.tcra.hrms.dto.TrainingResponseUpdate;
import tz.go.tcra.hrms.training.entity.HrmsTraining;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.training.entity.HrmsTrainingCategory;
import tz.go.tcra.hrms.training.entity.HrmsTrainingInitiator;
import tz.go.tcra.hrms.training.entity.HrmsTrainingSponsor;
import tz.go.tcra.hrms.training.entity.HrmsTrainingType;

@Component
public interface ITrainingService {

	ResponseEntity<HrmsTrainingInitiator[]> GetTrainingInitiator(String authToken);
	ResponseEntity<HrmsTrainingSponsor[]> GetTrainingSponsor(String authToken);
	ResponseEntity<HrmsTrainingType[]> GetTrainingType(String authToken);
	ResponseEntity<TrainingResponse[]> GetTrainingCurrentFinancialYear(String authToken);
	ResponseEntity<TrainingResponse> GetTrainingDetails(String authToken, int trainingId);
	ResponseEntity<HrmsTraining> CreateTraining(String authToken, HrmsTraining hrmsTraining);
	
	ResponseEntity<HrmsTraining> UpdateTraining(String authToken, HrmsTraining hrmsTraining, int trainingId);
	
	ResponseEntity<HrmsTraining> DeleteTraining(String authToken, int trainingId);
	
	ResponseEntity<TrainingResponse[]> GetTrainingReport(String authToken, int quaterid , int categoryid, int financialyearid);
	
	//Workflow 
	ResponseEntity<HrmsTrainingApprovalWorkflow> CreateTrainingWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow);
	ResponseEntity<TrainingApprovalWorkflow[]> GetAllTrainingWorkflow(String authToken);
	ResponseEntity<TrainingApprovalWorkflow> GetTrainingWorkflowByID(String authToken, int workflowID);
	ResponseEntity<HrmsTrainingApprovalWorkflow> UpdateTrainingWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow, int workflowID);
	ResponseEntity<HrmsTrainingApprovalWorkflow> DeleteTrainingWorkflow(String authToken, int workflowID);
	
	//Workflow Step
	ResponseEntity<HrmsTrainingApprovalWorkflowStep> CreateTrainingWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep hrmsTrainingApprovalWorkflowStep);
	ResponseEntity<TrainingApprovalWorkflowStep[]> GetAllTrainingWorkflowStep(String authToken);
	ResponseEntity<TrainingApprovalWorkflowStep> GetTrainingWorkflowStepByID(String authToken, int id);
	ResponseEntity<HrmsTrainingApprovalWorkflowStep> UpdateTrainingWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep hrmsTrainingApprovalWorkflowStep, int workflowStepID);
	ResponseEntity<HrmsTrainingApprovalWorkflowStep> DeleteTrainingWorkflowStep(String authToken, int workflowStepID);
	
	// Training Category
	
	ResponseEntity<HrmsTrainingCategory[]> GetTrainingCategory(String authToken);
	ResponseEntity<HrmsTrainingCategory> CreateTrainingCategory(String authToken, HrmsTrainingCategory hrmsTrainingCategory);
	ResponseEntity<HrmsTrainingCategory> UpdateTrainingCategory(String authToken, HrmsTrainingCategory hrmsTrainingCategory, int id);
	ResponseEntity<HrmsTrainingCategory> DeleteTrainingCategory(String authToken, int id);
	ResponseEntity<HrmsTrainingCategory> GetTrainingCategoryByID(String authToken, int id);
	
}

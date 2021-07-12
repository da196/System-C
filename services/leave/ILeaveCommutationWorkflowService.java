package tz.go.tcra.hrms.services.leave;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.TrainingApprovalWorkflow;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.dto.leave.HrmsLeaveApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;
@Component
public interface ILeaveCommutationWorkflowService {

	//Workflow 
		ResponseEntity<HrmsTrainingApprovalWorkflow> CreateLeaveWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow);
		ResponseEntity<TrainingApprovalWorkflow[]> GetAllLeaveWorkflow(String authToken);
		ResponseEntity<TrainingApprovalWorkflow> GetLeaveWorkflowByID(String authToken, int workflowID);
		ResponseEntity<HrmsTrainingApprovalWorkflow> UpdateLeaveWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow, int workflowID);
		ResponseEntity<HrmsTrainingApprovalWorkflow> DeleteLeaveWorkflow(String authToken, int workflowID);
		
		//Workflow Step
		ResponseEntity<HrmsTrainingApprovalWorkflowStep> CreateLeaveWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep hrmsTrainingApprovalWorkflowStep);
		ResponseEntity<TrainingApprovalWorkflowStep[]> GetAllLeaveWorkflowStep(String authToken);
		ResponseEntity<TrainingApprovalWorkflowStep> GetLeaveWorkflowStepByID(String authToken, int id);
		ResponseEntity<HrmsTrainingApprovalWorkflowStep> UpdateLeaveWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep hrmsTrainingApprovalWorkflowStep, int workflowStepID);
		ResponseEntity<HrmsTrainingApprovalWorkflowStep> DeleteLeaveWorkflowStep(String authToken, int workflowStepID);
		
}

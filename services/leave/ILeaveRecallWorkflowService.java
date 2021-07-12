package tz.go.tcra.hrms.services.leave;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.TrainingApprovalWorkflow;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.dto.leave.HrmsLeaveApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;
@Component
public interface ILeaveRecallWorkflowService {

	//Workflow 
			ResponseEntity<HrmsTrainingApprovalWorkflow> CreateLeaveRecallWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow);
			ResponseEntity<TrainingApprovalWorkflow[]> GetAllLeaveRecallWorkflow(String authToken);
			ResponseEntity<TrainingApprovalWorkflow> GetLeaveRecallWorkflowByID(String authToken, int workflowID);
			ResponseEntity<HrmsTrainingApprovalWorkflow> UpdateLeaveRecallWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow, int workflowID);
			ResponseEntity<HrmsTrainingApprovalWorkflow> DeleteLeaveRecallWorkflow(String authToken, int workflowID);
			
			//Workflow Step
			ResponseEntity<HrmsTrainingApprovalWorkflowStep> CreateLeaveRecallWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep hrmsTrainingApprovalWorkflowStep);
			ResponseEntity<TrainingApprovalWorkflowStep[]> GetAllLeaveRecallWorkflowStep(String authToken);
			ResponseEntity<TrainingApprovalWorkflowStep> GetLeaveRecallWorkflowStepByID(String authToken, int id);
			ResponseEntity<HrmsTrainingApprovalWorkflowStep> UpdateLeaveRecallWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep hrmsTrainingApprovalWorkflowStep, int workflowStepID);
			ResponseEntity<HrmsTrainingApprovalWorkflowStep> DeleteLeaveRecallWorkflowStep(String authToken, int workflowStepID);
		
}

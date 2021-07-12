package tz.go.tcra.hrms.services.leave;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflow;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.dto.leave.HrmsLeaveApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.training.entity.HrmsTrainingCategory;
import tz.go.tcra.hrms.training.service.TrainingService;

@Service
public class LeaveWorkflowService implements ILeaveWorkflowService{

	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger logger = Logger.getLogger(LeaveWorkflowService.class); // log4j
	
	
	//****************************** Training Workflow Step ************************************
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflowStep> CreateLeaveWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep HhmsTrainingApprovalWorkflowStep) {
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflowStep> request = new HttpEntity<HrmsTrainingApprovalWorkflowStep> (HhmsTrainingApprovalWorkflowStep,headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflowStep/addLeaveWorkFlowStep", 
					  HttpMethod.POST, 
					  request, 
					  HrmsTrainingApprovalWorkflowStep.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<TrainingApprovalWorkflowStep[]> GetAllLeaveWorkflowStep(String authToken) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingApprovalWorkflowStep> request = new HttpEntity<TrainingApprovalWorkflowStep> (headers);		
			final ResponseEntity<TrainingApprovalWorkflowStep[]> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflowStep/getAllLeaveWorkFlowStep" , 
					  HttpMethod.GET, 
					  request, 
					  TrainingApprovalWorkflowStep[].class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<TrainingApprovalWorkflowStep> GetLeaveWorkflowStepByID(String authToken, int id) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingApprovalWorkflowStep> request = new HttpEntity<TrainingApprovalWorkflowStep> (headers);		
			final ResponseEntity<TrainingApprovalWorkflowStep> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflowStep/getLeaveWorkFlowStepById/"+ id , 
					  HttpMethod.GET, 
					  request, 
					  TrainingApprovalWorkflowStep.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflowStep> UpdateLeaveWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep hrmsTrainingApprovalWorkflowStep, int id){
		logger.info("Start consuming the endpoint");
		System.out.println("Start consuming the endpoint WITH ID ===> " + id);
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflowStep> request = new HttpEntity<HrmsTrainingApprovalWorkflowStep> (hrmsTrainingApprovalWorkflowStep,headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflowStep/updateLeaveWorkFlowStep/" + id, 
					  HttpMethod.PUT, 
					  request, 
					  HrmsTrainingApprovalWorkflowStep.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflowStep> DeleteLeaveWorkflowStep(String authToken, int workflowStepID) {
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflowStep> request = new HttpEntity<HrmsTrainingApprovalWorkflowStep> (headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflowStep/deleteLeaveWorkFlowStep/" + workflowStepID, 
					  HttpMethod.DELETE, 
					  request, 
					  HrmsTrainingApprovalWorkflowStep.class);			
			return response;
		}
		return null;
	}
	
	
	//****************************** Training Workflow ************************************
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflow> CreateLeaveWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsLeaveApprovalWorkflow) {
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflow> request = new HttpEntity<HrmsTrainingApprovalWorkflow> (hrmsLeaveApprovalWorkflow,headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflow> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflow/addLeaveWorkFlow", 
					  HttpMethod.POST, 
					  request, 
					  HrmsTrainingApprovalWorkflow.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<TrainingApprovalWorkflow[]> GetAllLeaveWorkflow(String authToken) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingApprovalWorkflow> request = new HttpEntity<TrainingApprovalWorkflow> (headers);		
			final ResponseEntity<TrainingApprovalWorkflow[]> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflow/getAllLeaveWorkFlow" , 
					  HttpMethod.GET, 
					  request, 
					  TrainingApprovalWorkflow[].class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<TrainingApprovalWorkflow> GetLeaveWorkflowByID(String authToken, int workflowID) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingApprovalWorkflow> request = new HttpEntity<TrainingApprovalWorkflow> (headers);		
			final ResponseEntity<TrainingApprovalWorkflow> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflow/getLeaveWorkFlowById/"+ workflowID , 
					  HttpMethod.GET, 
					  request, 
					  TrainingApprovalWorkflow.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflow> UpdateLeaveWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow, int workflowID){
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflow> request = new HttpEntity<HrmsTrainingApprovalWorkflow> (hrmsTrainingApprovalWorkflow,headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflow> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflow/updateLeaveWorkFlow/" + workflowID, 
					  HttpMethod.PUT, 
					  request, 
					  HrmsTrainingApprovalWorkflow.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflow> DeleteLeaveWorkflow(String authToken, int workflowID) {
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflow> request = new HttpEntity<HrmsTrainingApprovalWorkflow> (headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflow> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/leaveApprovalWorkflow/deleteLeaveWorkFlow/" + workflowID, 
					  HttpMethod.DELETE, 
					  request, 
					  HrmsTrainingApprovalWorkflow.class);			
			return response;
		}
		return null;
	}
	
	// ****************************** End Training Workflow **************************
}

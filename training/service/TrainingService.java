package tz.go.tcra.hrms.training.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import tz.go.tcra.hrms.controllers.TrainingController;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflow;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.dto.TrainingResponse;
import tz.go.tcra.hrms.dto.TrainingResponseUpdate;
import tz.go.tcra.hrms.dto.payroll.LoanHeslb;
import tz.go.tcra.hrms.training.entity.HrmsTraining;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.training.entity.HrmsTrainingCategory;
import tz.go.tcra.hrms.training.entity.HrmsTrainingInitiator;
import tz.go.tcra.hrms.training.entity.HrmsTrainingSponsor;
import tz.go.tcra.hrms.training.entity.HrmsTrainingType;

@Service
public class TrainingService implements ITrainingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger logger = Logger.getLogger(TrainingService.class); // log4j
	
	@Override
	public ResponseEntity<HrmsTrainingCategory[]> GetTrainingCategory(String authToken) {
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingCategory> request = new HttpEntity<HrmsTrainingCategory> (headers);
			final ResponseEntity<HrmsTrainingCategory[]> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/trainingCategory/getAllTrainingCategory", 
					  HttpMethod.GET, 
					  request, 
					  HrmsTrainingCategory[].class);	
			
			System.out.println(response.getStatusCode());
			return response;
	
	}
	
	@Override
	public ResponseEntity<HrmsTrainingInitiator[]> GetTrainingInitiator(String authToken) {
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingInitiator> request = new HttpEntity<HrmsTrainingInitiator> (headers);
			final ResponseEntity<HrmsTrainingInitiator[]> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/trainingInitiator/getAllTrainingInitiator", 
					  HttpMethod.GET, 
					  request, 
					  HrmsTrainingInitiator[].class);			
			return response;
	
	}
	
	@Override
	public ResponseEntity<HrmsTrainingSponsor[]> GetTrainingSponsor(String authToken) {
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingSponsor> request = new HttpEntity<HrmsTrainingSponsor> (headers);
			final ResponseEntity<HrmsTrainingSponsor[]> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/trainingSponsor/getAllTrainingSponsor", 
					  HttpMethod.GET, 
					  request, 
					  HrmsTrainingSponsor[].class);			
			return response;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingType[]> GetTrainingType(String authToken) {
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingType> request = new HttpEntity<HrmsTrainingType> (headers);
			final ResponseEntity<HrmsTrainingType[]> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/trainingType/getAllTrainingType", 
					  HttpMethod.GET, 
					  request, 
					  HrmsTrainingType[].class);			
			return response;
	
	}
	
	@Override
	public ResponseEntity<TrainingResponse[]> GetTrainingCurrentFinancialYear(String authToken) {
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingResponse> request = new HttpEntity<TrainingResponse> (headers);
			final ResponseEntity<TrainingResponse[]> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/training/getTrainingCurrentYear", 
					  HttpMethod.GET, 
					  request, 
					  TrainingResponse[].class);			
			return response;
	
	}
	
	@Override
	public ResponseEntity<HrmsTraining> CreateTraining(String authToken, HrmsTraining hrmsTraining) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTraining> request = new HttpEntity<HrmsTraining> (hrmsTraining,headers);		
			final ResponseEntity<HrmsTraining> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/training/addTraining", 
					  HttpMethod.POST, 
					  request, 
					  HrmsTraining.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<TrainingResponse> GetTrainingDetails(String authToken, int trainingId) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingResponse> request = new HttpEntity<TrainingResponse> (headers);		
			final ResponseEntity<TrainingResponse> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/training/getTrainingById/"+ trainingId , 
					  HttpMethod.GET, 
					  request, 
					  TrainingResponse.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTraining> UpdateTraining(String authToken, HrmsTraining hrmsTraining, int trainingId ) {
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTraining> request = new HttpEntity<HrmsTraining> (hrmsTraining,headers);		
			final ResponseEntity<HrmsTraining> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/training/updateTraining/" + trainingId, 
					  HttpMethod.PUT, 
					  request, 
					  HrmsTraining.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTraining> DeleteTraining(String authToken, int trainingId ) {
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTraining> request = new HttpEntity<HrmsTraining> (headers);		
			final ResponseEntity<HrmsTraining> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/training/deleteTraining/" + trainingId, 
					  HttpMethod.DELETE, 
					  request, 
					  HrmsTraining.class);			
			return response;
		}
		return null;
	}
	
	// Get Training Report
	@Override
	public ResponseEntity<TrainingResponse[]> GetTrainingReport(String authToken, int quaterid, int categoryid, int financialyearid) {
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingResponse> request = new HttpEntity<TrainingResponse> (headers);
			final ResponseEntity<TrainingResponse[]> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/training/getHrmsTrainingByFinancialYearAndQuaterId/" + financialyearid + "/" + quaterid + "/" + categoryid,
					  HttpMethod.GET, 
					  request, 
					  TrainingResponse[].class);			
			return response;
	
	}
	
	//****************************** Training Workflow Step ************************************
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflowStep> CreateTrainingWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep HhmsTrainingApprovalWorkflowStep) {
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflowStep> request = new HttpEntity<HrmsTrainingApprovalWorkflowStep> (HhmsTrainingApprovalWorkflowStep,headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflowStep/addTrainingWorkflowStep", 
					  HttpMethod.POST, 
					  request, 
					  HrmsTrainingApprovalWorkflowStep.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<TrainingApprovalWorkflowStep[]> GetAllTrainingWorkflowStep(String authToken) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingApprovalWorkflowStep> request = new HttpEntity<TrainingApprovalWorkflowStep> (headers);		
			final ResponseEntity<TrainingApprovalWorkflowStep[]> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflowStep/getAllTrainingApprovalWorkflowStep" , 
					  HttpMethod.GET, 
					  request, 
					  TrainingApprovalWorkflowStep[].class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<TrainingApprovalWorkflowStep> GetTrainingWorkflowStepByID(String authToken, int id) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingApprovalWorkflowStep> request = new HttpEntity<TrainingApprovalWorkflowStep> (headers);		
			final ResponseEntity<TrainingApprovalWorkflowStep> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflowStep/getTrainingApprovalWorkflowStepById/"+ id , 
					  HttpMethod.GET, 
					  request, 
					  TrainingApprovalWorkflowStep.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflowStep> UpdateTrainingWorkflowStep(String authToken, HrmsTrainingApprovalWorkflowStep hrmsTrainingApprovalWorkflowStep, int workflowStepID){
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflowStep> request = new HttpEntity<HrmsTrainingApprovalWorkflowStep> (hrmsTrainingApprovalWorkflowStep,headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflowStep/updateTrainingWorkflowStep/" + workflowStepID, 
					  HttpMethod.PUT, 
					  request, 
					  HrmsTrainingApprovalWorkflowStep.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflowStep> DeleteTrainingWorkflowStep(String authToken, int workflowStepID) {
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflowStep> request = new HttpEntity<HrmsTrainingApprovalWorkflowStep> (headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflowStep/deleteTrainingWorkflowStep/" + workflowStepID, 
					  HttpMethod.DELETE, 
					  request, 
					  HrmsTrainingApprovalWorkflowStep.class);			
			return response;
		}
		return null;
	}
	
	
	//****************************** Training Workflow ************************************
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflow> CreateTrainingWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow) {
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflow> request = new HttpEntity<HrmsTrainingApprovalWorkflow> (hrmsTrainingApprovalWorkflow,headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflow> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflow/addTrainingApprovalWorkflow", 
					  HttpMethod.POST, 
					  request, 
					  HrmsTrainingApprovalWorkflow.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<TrainingApprovalWorkflow[]> GetAllTrainingWorkflow(String authToken) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingApprovalWorkflow> request = new HttpEntity<TrainingApprovalWorkflow> (headers);		
			final ResponseEntity<TrainingApprovalWorkflow[]> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflow/getAllTrainingApprovalWorkflow" , 
					  HttpMethod.GET, 
					  request, 
					  TrainingApprovalWorkflow[].class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<TrainingApprovalWorkflow> GetTrainingWorkflowByID(String authToken, int workflowID) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TrainingApprovalWorkflow> request = new HttpEntity<TrainingApprovalWorkflow> (headers);		
			final ResponseEntity<TrainingApprovalWorkflow> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflow/getTrainingWorkflowById/"+ workflowID , 
					  HttpMethod.GET, 
					  request, 
					  TrainingApprovalWorkflow.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflow> UpdateTrainingWorkflow(String authToken, HrmsTrainingApprovalWorkflow hrmsTrainingApprovalWorkflow, int workflowID){
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflow> request = new HttpEntity<HrmsTrainingApprovalWorkflow> (hrmsTrainingApprovalWorkflow,headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflow> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflow/updateTrainingWorkflow/" + workflowID, 
					  HttpMethod.PUT, 
					  request, 
					  HrmsTrainingApprovalWorkflow.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingApprovalWorkflow> DeleteTrainingWorkflow(String authToken, int workflowID) {
		logger.info("Start consuming the endpoint");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingApprovalWorkflow> request = new HttpEntity<HrmsTrainingApprovalWorkflow> (headers);		
			final ResponseEntity<HrmsTrainingApprovalWorkflow> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingApprovalWorkflow/deleteTrainingWorkflow/" + workflowID, 
					  HttpMethod.DELETE, 
					  request, 
					  HrmsTrainingApprovalWorkflow.class);			
			return response;
		}
		return null;
	}
	
	// ****************************** End Training Workflow **************************
	
	//// Training Category
	@Override
	public ResponseEntity<HrmsTrainingCategory> CreateTrainingCategory(String authToken, HrmsTrainingCategory hrmsTrainingCategory) {
//		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingCategory> request = new HttpEntity<HrmsTrainingCategory> (hrmsTrainingCategory, headers);		
			final ResponseEntity<HrmsTrainingCategory> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingCategory/addTrainingCategory", 
					  HttpMethod.POST, 
					  request, 
					  HrmsTrainingCategory.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingCategory> GetTrainingCategoryByID(String authToken, int id) {
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingCategory> request = new HttpEntity<HrmsTrainingCategory> (headers);
			final ResponseEntity<HrmsTrainingCategory> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/trainingCategory/getTrainingCategoryById/" + id, 
					  HttpMethod.GET, 
					  request, 
					  HrmsTrainingCategory.class);			
			return response;
	
	}
	
	@Override
	public ResponseEntity<HrmsTrainingCategory> UpdateTrainingCategory(String authToken, HrmsTrainingCategory hrmsTrainingCategory, int id) {
		
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingCategory> request = new HttpEntity<HrmsTrainingCategory> (hrmsTrainingCategory,headers);		
			final ResponseEntity<HrmsTrainingCategory> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingCategory/updateTrainingCategory/" + id , 
					  HttpMethod.PUT, 
					  request, 
					  HrmsTrainingCategory.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HrmsTrainingCategory> DeleteTrainingCategory(String authToken, int id){
		
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HrmsTrainingCategory> request = new HttpEntity<HrmsTrainingCategory> (headers);		
			final ResponseEntity<HrmsTrainingCategory> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/trainingCategory/deleteTrainingCategory/" + id , 
					  HttpMethod.DELETE, 
					  request, 
					  HrmsTrainingCategory.class);			
			return response;
		}
		return null;
	}
	
}

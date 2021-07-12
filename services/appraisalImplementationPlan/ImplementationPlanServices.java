package tz.go.tcra.hrms.services.appraisalImplementationPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationActivity;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationActivityResponsible;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationOutcome;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationOutput;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationPlan;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationTarget;

@Component
public class ImplementationPlanServices implements IImplementationPlanServices {
	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<ImplementationOutcome> AddNewOutcome(ImplementationOutcome outcome) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationOutcome> request = new HttpEntity<ImplementationOutcome>(outcome, headers);
			ResponseEntity<ImplementationOutcome> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcome/addPerformanceObjectiveOutcome", 
												  HttpMethod.POST, 
												  request, 
												  ImplementationOutcome.class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationOutcome[]> GetAllOutcomes() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationOutcome> request = new HttpEntity<ImplementationOutcome>(headers);
			ResponseEntity<ImplementationOutcome[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcome/getAllPerformanceObjectiveOutcome", 
												  HttpMethod.GET, 
												  request, 
												  ImplementationOutcome[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationActivity> AddNewActivity(ImplementationActivity activity) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationActivity> request = new HttpEntity<ImplementationActivity>(activity, headers);
			ResponseEntity<ImplementationActivity> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcomeActivity/addPerformanceObjectiveOutcomeActivity", 
												  HttpMethod.POST, 
												  request, 
												  ImplementationActivity.class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationActivity[]> GetAllActivities() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationActivity> request = new HttpEntity<ImplementationActivity>(headers);
			ResponseEntity<ImplementationActivity[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcomeActivity/getAllPerformanceObjectiveOutcomeActivity", 
												  HttpMethod.GET, 
												  request, 
												  ImplementationActivity[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationOutput> AddNewOutput(ImplementationOutput output) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationOutput> request = new HttpEntity<ImplementationOutput>(output, headers);
			ResponseEntity<ImplementationOutput> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcomeActivityOutput/addPerformanceObjectiveOutcomeActivityOutput", 
												  HttpMethod.POST, 
												  request, 
												  ImplementationOutput.class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationOutput[]> GetAllOutputs() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Object> request = new HttpEntity<Object>(headers);
			ResponseEntity<ImplementationOutput[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcomeActivityOutput/getAllPerformanceObjectiveOutcomeActivityOutput", 
												  HttpMethod.GET, 
												  request, 
												  ImplementationOutput[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationTarget> AddNewTarget(ImplementationTarget target) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationTarget> request = new HttpEntity<ImplementationTarget>(target, headers);
			ResponseEntity<ImplementationTarget> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcomeActivityOutputTarget/addPerformanceObjectiveOutcomeActivityOutputTarget", 
												  HttpMethod.POST, 
												  request, 
												  ImplementationTarget.class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationTarget[]> GetAllTargets() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationTarget> request = new HttpEntity<ImplementationTarget>(headers);
			ResponseEntity<ImplementationTarget[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcomeActivityOutputTarget/getAllPerformanceObjectiveOutcomeActivityOutputTarget", 
												  HttpMethod.GET, 
												  request, 
												  ImplementationTarget[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationActivityResponsible> AddResponsible(
			ImplementationActivityResponsible responsible) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationActivityResponsible> request = new HttpEntity<ImplementationActivityResponsible>(responsible, headers);
			ResponseEntity<ImplementationActivityResponsible> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcomeActivityOutputResponsible/addPerformanceObjectiveOutcomeActivityOutputResponsible", 
												  HttpMethod.POST, 
												  request, 
												  ImplementationActivityResponsible.class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationActivityResponsible[]> GetAllResponsible() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationActivityResponsible> request = new HttpEntity<ImplementationActivityResponsible>(headers);
			ResponseEntity<ImplementationActivityResponsible[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveOutcomeActivityOutputResponsible/getAllPerformanceObjectiveOutcomeActivityOutputResponsible", 
												  HttpMethod.GET, 
												  request, 
												  ImplementationActivityResponsible[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ImplementationPlan> GetCompiledImplementationPlanList(int planID, int goalID,
			int objectiveID) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<ImplementationPlan> request = new HttpEntity<ImplementationPlan>(headers);
			ResponseEntity<ImplementationPlan> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceStrategicPlanGlobal/getPerformanceStrategicPlanGlobalByIdPlanIdGoalIdAndObjectiveId/"+planID+"/"+goalID+"/"+objectiveID+"/", 
												  HttpMethod.GET, 
												  request, 
												  ImplementationPlan.class);		
			return response;
		}
		return null;
	}

}

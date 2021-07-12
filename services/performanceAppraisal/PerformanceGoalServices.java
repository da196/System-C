package tz.go.tcra.hrms.services.performanceAppraisal;

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
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceGoal;

@Component
public class PerformanceGoalServices implements IPerformanceGoalServices {
	
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<PerformanceGoal> AddNewPerformanceGoal(PerformanceGoal goal) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceGoal> request = new HttpEntity<PerformanceGoal>(goal, headers);	  		
			ResponseEntity<PerformanceGoal> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceGoal/addPerformanceGoal", 
												  HttpMethod.POST, 
												  request, 
												  PerformanceGoal.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceGoal[]> GetAll() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceGoal> request = new HttpEntity<PerformanceGoal>(headers);	  		
			ResponseEntity<PerformanceGoal[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceGoal/getAllPerformanceGoal", 
												  HttpMethod.GET, 
												  request, 
												  PerformanceGoal[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceGoal[]> GetGoalByPlanId(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceGoal> request = new HttpEntity<PerformanceGoal>(headers);	  		
			ResponseEntity<PerformanceGoal[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceGoal/getAllPerformanceGoalByPlanId/"+id, 
												  HttpMethod.GET, 
												  request, 
												  PerformanceGoal[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceGoal> EditStrategicGoal(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceGoal> request = new HttpEntity<PerformanceGoal>(headers);	  		
			ResponseEntity<PerformanceGoal> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceGoal/updatePerformanceGoal/"+id, 
												  HttpMethod.PUT, 
												  request, 
												  PerformanceGoal.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceGoal> DeleteStrategicGoal(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceGoal> request = new HttpEntity<PerformanceGoal>(headers);	  		
			ResponseEntity<PerformanceGoal> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceGoal/deletePerformanceGoal/"+id, 
												  HttpMethod.DELETE, 
												  request, 
												  PerformanceGoal.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceGoal> GetGoalByGoalId(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceGoal> request = new HttpEntity<PerformanceGoal>(headers);	  		
			ResponseEntity<PerformanceGoal> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceGoal/getPerformanceGoalById/"+id, 
												  HttpMethod.GET, 
												  request, 
												  PerformanceGoal.class);
			
			return response;
		}
		return null;
	}

}

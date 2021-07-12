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
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceObjective;

@Component
public class PerformanceObjectiveServices implements IPerformanceObjectiveServices {
	
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<PerformanceObjective> AddNewPerformanceObjective(PerformanceObjective objective) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceObjective> request = new HttpEntity<PerformanceObjective>(objective, headers);	  		
			ResponseEntity<PerformanceObjective> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjective/addPerformanceObjective", 
												  HttpMethod.POST, 
												  request, 
												  PerformanceObjective.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceObjective[]> GetAllObjectives() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceObjective> request = new HttpEntity<PerformanceObjective>(headers);	  		
			ResponseEntity<PerformanceObjective[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjective/getAllPerformanceObjective", 
												  HttpMethod.GET, 
												  request, 
												  PerformanceObjective[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceObjective[]> GetObjectivesByGoalId(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceObjective> request = new HttpEntity<PerformanceObjective>(headers);	  		
			ResponseEntity<PerformanceObjective[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjective/getAllPerformanceObjectiveByGoalId/"+id, 
												  HttpMethod.GET, 
												  request, 
												  PerformanceObjective[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceObjective> EditStrategicObjective(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceObjective> request = new HttpEntity<PerformanceObjective>(headers);	  		
			ResponseEntity<PerformanceObjective> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjective/updatePerformanceObjective/"+id, 
												  HttpMethod.PUT, 
												  request, 
												  PerformanceObjective.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceObjective> DeleteStrategicObjective(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceObjective> request = new HttpEntity<PerformanceObjective>(headers);	  		
			ResponseEntity<PerformanceObjective> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjective/deletePerformanceObjective/"+id, 
												  HttpMethod.DELETE, 
												  request, 
												  PerformanceObjective.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceObjective> GetObjectiveByObjectiveId(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceObjective> request = new HttpEntity<PerformanceObjective>(headers);	  		
			ResponseEntity<PerformanceObjective> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjective/getPerformanceObjectiveById/"+id, 
												  HttpMethod.GET, 
												  request, 
												  PerformanceObjective.class);
			
			return response;
		}
		return null;
	}

}

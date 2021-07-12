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
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceTargets;

@Component
public class PerformanceTargetServices implements IPerformanceTargetServices {
	
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<PerformanceTargets[]> GetAllTargets() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceTargets> request = new HttpEntity<PerformanceTargets>(headers);	  		
			ResponseEntity<PerformanceTargets[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveTarget/getAllPerformanceObjectiveTarget", 
												  HttpMethod.GET, 
												  request, 
												  PerformanceTargets[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceTargets> AddNewTargets(PerformanceTargets target) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceTargets> request = new HttpEntity<PerformanceTargets>(target, headers);	  		
			ResponseEntity<PerformanceTargets> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveTarget/addPerformanceObjectiveTarget", 
												  HttpMethod.POST, 
												  request, 
												  PerformanceTargets.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceTargets[]> GetTargetsByObjectiveId(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceTargets> request = new HttpEntity<PerformanceTargets>(headers);	  		
			ResponseEntity<PerformanceTargets[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveTarget/getAllPerformanceObjectiveTargetByObjectiveId/"+id, 
												  HttpMethod.POST, 
												  request, 
												  PerformanceTargets[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceTargets> EditStrategicTarget(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceTargets> request = new HttpEntity<PerformanceTargets>(headers);	  		
			ResponseEntity<PerformanceTargets> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveTarget/updatePerformanceObjectiveTarget/"+id, 
												  HttpMethod.PUT, 
												  request, 
												  PerformanceTargets.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformanceTargets> DeleteStrategicTarget(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformanceTargets> request = new HttpEntity<PerformanceTargets>(headers);	  		
			ResponseEntity<PerformanceTargets> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceObjectiveTarget/deletePerformanceObjectiveTarget/"+id, 
												  HttpMethod.DELETE, 
												  request, 
												  PerformanceTargets.class);
			
			return response;
		}
		return null;
	}

}

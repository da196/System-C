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
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformancePlan;
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformancePlanSummary;

@Component
public class PerformancePlanServices implements IPerformancePlanServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<PerformancePlan> AddNewPerfomancePlan(PerformancePlan plan) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformancePlan> request = new HttpEntity<PerformancePlan>(plan, headers);	  		
			ResponseEntity<PerformancePlan> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performancePlan/addPerformancePlan", 
												  HttpMethod.POST, 
												  request, 
												  PerformancePlan.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformancePlan[]> GetAllPerformancePlans() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformancePlan> request = new HttpEntity<PerformancePlan>(headers);	  		
			ResponseEntity<PerformancePlan[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performancePlan/getAllPerformancePlan", 
												  HttpMethod.GET, 
												  request, 
												  PerformancePlan[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformancePlanSummary> GetCompiledList(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformancePlanSummary> request = new HttpEntity<PerformancePlanSummary>(headers);	  		
			ResponseEntity<PerformancePlanSummary> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performanceStrategicPlanGlobal/getPerformanceStrategicPlanSummaryByPlanId/"+id, 
												  HttpMethod.GET, 
												  request, 
												  PerformancePlanSummary.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PerformancePlan> DeletePlan(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<PerformancePlan> request = new HttpEntity<PerformancePlan>(headers);	  		
			ResponseEntity<PerformancePlan> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/performancePlan/deletePerformancePlan/"+id, 
												  HttpMethod.DELETE, 
												  request, 
												  PerformancePlan.class);
			
			return response;
		}
		return null;
	}

}

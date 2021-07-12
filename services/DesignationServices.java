package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.Designation;

@Component
public class DesignationServices implements IDesignationServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<Designation[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
		HttpEntity<Designation> request = new HttpEntity<Designation>(headers);	  		
		ResponseEntity<Designation[]> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/designation/getAllDesignation", 
											  HttpMethod.GET, 
											  request, 
											  Designation[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<Designation> Create(Designation designation) {
		// Creating designation
		if(designation != null) {	
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		
			HttpEntity<Designation> request = new HttpEntity<>(designation,headers);
			ResponseEntity<Designation> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/designation/addDesignation", 
												  HttpMethod.POST, 
												  request, 
												  Designation.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Designation> Delete(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		
			HttpEntity<Designation> request = new HttpEntity<Designation>(headers);
			ResponseEntity<Designation> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/designation/deleteDesignation/"+id, 
										HttpMethod.DELETE, 
										request, 
										Designation.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Designation[]> GetSupervisor() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		  
			HttpEntity<Designation> request = new HttpEntity<Designation>(headers);	  		
			ResponseEntity<Designation[]> response = restTemplate.exchange(
												  AppConstants.BASE_URL+"/v1/designation/getAllSupervisorDesignation", 
												  HttpMethod.GET, 
												  request, 
												  Designation[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Designation> UpdateDesignation(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		
			HttpEntity<Designation> request = new HttpEntity<Designation>(headers);
			ResponseEntity<Designation> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/designation/updateDesignation/"+id, 
										HttpMethod.PUT, 
										request, 
										Designation.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Designation> GetDesignationById(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);		
			HttpEntity<Designation> request = new HttpEntity<Designation>(headers);
			ResponseEntity<Designation> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/designation/getDesignation/"+id, 
										HttpMethod.GET, 
										request, 
										Designation.class);
			return response;
		}
		return null;
	}




}

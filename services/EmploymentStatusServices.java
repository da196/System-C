package tz.go.tcra.hrms.services;

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
import tz.go.tcra.hrms.dto.EmploymentStatus;

@Component
public class EmploymentStatusServices implements IEmploymentStatusServices{
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmploymentStatus[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders header = HttpHeader.setHttpHeader(authToken);
		HttpEntity<EmploymentStatus> request = new HttpEntity<EmploymentStatus>(header);
		ResponseEntity<EmploymentStatus[]> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/employmentStatus/getAllEmploymentStatus", 
										HttpMethod.GET, 
										request, 
										EmploymentStatus[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<EmploymentStatus> Create(EmploymentStatus status) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmploymentStatus> request = new HttpEntity<EmploymentStatus>(status, header);
			ResponseEntity<EmploymentStatus> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/employmentStatus/addEmploymentStatus", 
											HttpMethod.POST, 
											request, 
											EmploymentStatus.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmploymentStatus> Delete(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmploymentStatus> request = new HttpEntity<EmploymentStatus>(header);
			ResponseEntity<EmploymentStatus> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/employmentStatus/deleteEmploymentStatus/"+id, 
											HttpMethod.DELETE, 
											request, 
											EmploymentStatus.class);
			
			return response;
		}
		return null;
	}

}

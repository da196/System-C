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
import tz.go.tcra.hrms.dto.EmploymentCategory;

@Component
public class EmploymentCategoryServices implements IEmploymentCategoryServices{
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmploymentCategory[]> GetAll() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmploymentCategory> request = new HttpEntity<EmploymentCategory>(header);
			ResponseEntity<EmploymentCategory[]> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/employmentCategory/getAllEmploymentCategory", 
												HttpMethod.GET, 
												request, 
												EmploymentCategory[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmploymentCategory> Create(EmploymentCategory employmentCategory) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmploymentCategory> request = new HttpEntity<EmploymentCategory>(employmentCategory, header);
			ResponseEntity<EmploymentCategory> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/employmentCategory/addEmploymentCategory", 
												HttpMethod.POST, 
												request, 
												EmploymentCategory.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmploymentCategory> Delete(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmploymentCategory> request = new HttpEntity<EmploymentCategory>(header);
			ResponseEntity<EmploymentCategory> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/employmentCategory/deleteEmploymentCategory/"+id, 
												HttpMethod.DELETE, 
												request, 
												EmploymentCategory.class);
			
			return response;
		}
		return null;
	}

}

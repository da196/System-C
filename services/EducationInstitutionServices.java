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
import tz.go.tcra.hrms.dto.EducationInstitution;

@Component
public class EducationInstitutionServices implements IEducationInstitutionServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EducationInstitution[]> GetAll() {
		if(restTemplate != null) {
			// token
			authToken = authenticationFacade.getAuthenticationToken();
			// headers
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EducationInstitution> request = new HttpEntity<EducationInstitution>(headers);
			ResponseEntity<EducationInstitution[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/educatinstitution/getAllEducationinstitutionDetailed", 
												  HttpMethod.GET, 
												  request, 
												  EducationInstitution[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EducationInstitution> Create(EducationInstitution institution) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EducationInstitution> request = new HttpEntity<EducationInstitution>(institution, headers);
			ResponseEntity<EducationInstitution> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/educatinstitution/addEducationinstitution", 
												  HttpMethod.POST, 
												  request, 
												  EducationInstitution.class);		
			return response;
		}
		return null;
	}
	
	

}

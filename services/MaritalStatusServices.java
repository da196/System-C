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
import tz.go.tcra.hrms.dto.MaritalStatus;

@Component
public class MaritalStatusServices implements IMaritalStatusServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<MaritalStatus[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<MaritalStatus> request = new HttpEntity<MaritalStatus> (headers);
		ResponseEntity<MaritalStatus[]> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/maritalStatus/getAllMaritalStatus", 
											HttpMethod.GET, 
											request, 
											MaritalStatus[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<MaritalStatus> Create(MaritalStatus marital_status) {
		// TODO Auto-generated method stub
		return null;
	}

}

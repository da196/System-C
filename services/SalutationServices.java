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
import tz.go.tcra.hrms.dto.Salutation;

@Component
public class SalutationServices implements ISalutationServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<Salutation[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Salutation> request = new HttpEntity<Salutation> (headers);
		ResponseEntity<Salutation[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/salutation/getAllSalutation", 
												  HttpMethod.GET, 
												  request, 
												  Salutation[].class);
		return response;
	}

}

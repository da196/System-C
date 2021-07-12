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
import tz.go.tcra.hrms.dto.Gender;
import tz.go.tcra.hrms.services.IGenderServices;

@Component
public class GenderServices implements IGenderServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;

	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public ResponseEntity<Gender[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Gender> request = new HttpEntity<Gender> (headers);
		ResponseEntity<Gender[]> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/gender/getAllgender", 
											HttpMethod.GET, 
											request, 
											Gender[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<Gender> Create(Gender gender) {
		// TODO Auto-generated method stub
		return null;
	}


}

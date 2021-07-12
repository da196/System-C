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
import tz.go.tcra.hrms.dto.Religion;

@Component
public class ReligionServices implements IReligionServices{
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;


	@Override
	public ResponseEntity<Religion[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders header = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Religion> request = new HttpEntity<Religion>(header);
		ResponseEntity<Religion[]> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/employeeReligion/getAllEmployeeReligion", 
											HttpMethod.GET, 
											request, 
											Religion[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<Religion> Create(Religion religion) {
		// TODO Auto-generated method stub
		return null;
	}

}

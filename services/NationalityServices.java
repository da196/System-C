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
import tz.go.tcra.hrms.dto.Nationality;

@Component
public class NationalityServices implements INationalityServices {
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<Nationality[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Nationality> request = new HttpEntity<Nationality> (headers);		
		ResponseEntity<Nationality[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/nationality/getAllNationality", 
				  HttpMethod.GET, 
				  request, 
				  Nationality[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<Nationality> Create(Nationality nationality) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNationality(Long id) {
		// TODO Auto-generated method stub
		
	}

}

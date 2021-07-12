package tz.go.tcra.hrms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.dto.UserX;

@Component
public class UserAuthenticationServices implements IUserAuthenticationServices{
	
	//Rest Template
	@Autowired
	private RestTemplate restTemplate;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	

	@Override
	public ResponseEntity<UserX[]> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserX> CreateRequest(UserX user) {		
		if(user != null) {
			if(restTemplate != null) {
				String url = AppConstants.BASE_URL+"/v1/authController/authenticate";
				HttpEntity<UserX> request = new HttpEntity<UserX>(user);
				ResponseEntity<UserX> response = restTemplate.exchange(url , HttpMethod.POST, request, UserX.class);
				if(response!=null) {
					System.out.println(response);
					return response;
				}
			}
		}
		return null;
	}

}

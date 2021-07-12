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
import tz.go.tcra.hrms.dto.SocialSecurity;

@Component
public class SocialSecurityServices implements ISocialSecurityServices {
	
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;
	

	@Override
	public ResponseEntity<SocialSecurity[]> GetAll() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SocialSecurity> request = new HttpEntity<SocialSecurity>(headers);
			ResponseEntity<SocialSecurity[]> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/socialSecuritySchemeServiceProvider/getAllSocialSecuritySchemeServiceProvider", 
										    HttpMethod.GET, 
										    request, 
										    SocialSecurity[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SocialSecurity> Create(SocialSecurity socialSecurity) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SocialSecurity> request = new HttpEntity<SocialSecurity>(socialSecurity, headers);
			ResponseEntity<SocialSecurity> response = restTemplate.exchange(
										   AppConstants.BASE_URL+"/v1/socialSecuritySchemeServiceProvider/addSocialSecuritySchemeServiceProvider", 
										   HttpMethod.POST, 
										   request, 
										   SocialSecurity.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SocialSecurity> Delete(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SocialSecurity> request = new HttpEntity<SocialSecurity>(headers);
			ResponseEntity<SocialSecurity> response = restTemplate.exchange(
										   AppConstants.BASE_URL+"/v1/socialSecuritySchemeServiceProvider/deleteSocialSecuritySchemeServiceProvider/"+id, 
										   HttpMethod.DELETE, 
										   request, 
										   SocialSecurity.class);
			
			return response;
		}
		return null;
	}

}

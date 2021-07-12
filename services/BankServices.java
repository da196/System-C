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
import tz.go.tcra.hrms.dto.Bank;

@Component
public class BankServices implements IBankServices {

	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<Bank[]> GetAll() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Bank> request = new HttpEntity<Bank>(headers);
			ResponseEntity<Bank[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/bank/getAllBank", 
											  HttpMethod.GET, 
											  request, 
											  Bank[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Bank> Create(Bank bank) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Bank> request = new HttpEntity<Bank>(bank, headers);
			ResponseEntity<Bank> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/bank/addBank", 
											  HttpMethod.POST, 
											  request, 
											  Bank.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Bank> Delete(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Bank> request = new HttpEntity<Bank>(headers);
			ResponseEntity<Bank> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/bank/deleteBank/"+id, 
											  HttpMethod.DELETE, 
											  request, 
											  Bank.class);
			
			return response;
		}
		return null;
	}

}

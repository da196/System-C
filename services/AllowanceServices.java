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
import tz.go.tcra.hrms.dto.Allowance;
import tz.go.tcra.hrms.dto.AllowanceType;

@Component
public class AllowanceServices implements IAllowanceServices {
	
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	// ****************************** CRUD Allowance ************************************** 
	@Override
	public ResponseEntity<Allowance[]> GetAllowance() {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (headers);		
		ResponseEntity<Allowance[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowance/getAllAllowance", 
				  HttpMethod.GET, 
				  request, 
				  Allowance[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<Allowance[]> GetAllowanceDetails() {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (headers);		
		ResponseEntity<Allowance[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowance/getAllAllowanceDetailed", 
				  HttpMethod.GET, 
				  request, 
				  Allowance[].class);
		
		return response;
	}
	
	@Override
	public ResponseEntity<Allowance> AddAllowance(Allowance allowance) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (allowance, headers);		
		ResponseEntity<Allowance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowance/addAllowance", 
				  HttpMethod.POST, 
				  request, 
				  Allowance.class);
		
		return response;
	}

	@Override
	public ResponseEntity<Allowance> getAllowanceeByID(int id) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (headers);		
		ResponseEntity<Allowance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowance/getAllowance/" + id, 
				  HttpMethod.GET, 
				  request, 
				  Allowance.class);
		
		return response;
	}
	
	@Override
	public ResponseEntity<Allowance> deleteAllowance(int id) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (headers);		
		ResponseEntity<Allowance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowance/deleteAllowance/" + id, 
				  HttpMethod.DELETE, 
				  request, 
				  Allowance.class);
		
		return response;
	}
	
	@Override
	public ResponseEntity<Allowance> updateAllowance(Allowance allowance, int id) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (allowance, headers);		
		ResponseEntity<Allowance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowance/updateAllowance/" + id, 
				  HttpMethod.PUT, 
				  request, 
				  Allowance.class);
		
		return response;
	}
	
	// **************************** CRUD Allowance Type **************************************
	@Override
	public ResponseEntity<Allowance> AddAllowanceType(Allowance allowanceType) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (allowanceType, headers);		
		ResponseEntity<Allowance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowancetype/addAllowanceType", 
				  HttpMethod.POST, 
				  request, 
				  Allowance.class);
		
		return response;
	}
	
	@Override
	public ResponseEntity<AllowanceType[]> GetAllowanceTypes() {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<AllowanceType> request = new HttpEntity<AllowanceType> (headers);		
		ResponseEntity<AllowanceType[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowancetype/listAllowancetype", 
				  HttpMethod.GET, 
				  request, 
				  AllowanceType[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<Allowance> getAllowanceTypeByID(int id) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (headers);		
		ResponseEntity<Allowance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowancetype/getAllowancetype/" + id, 
				  HttpMethod.GET, 
				  request, 
				  Allowance.class);
		
		return response;
	}
	
	@Override
	public ResponseEntity<Allowance> deleteAllowancetype(int id) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (headers);		
		ResponseEntity<Allowance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowancetype/deleteAllowancetype/" + id, 
				  HttpMethod.DELETE, 
				  request, 
				  Allowance.class);
		
		return response;
	}
	
	@Override
	public ResponseEntity<Allowance> updateAllowanceType(Allowance allowanceType, int id) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Allowance> request = new HttpEntity<Allowance> (allowanceType, headers);		
		ResponseEntity<Allowance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/allowancetype/updateAllowanceType/" +  id, 
				  HttpMethod.PUT, 
				  request, 
				  Allowance.class);
		
		return response;
	}
}

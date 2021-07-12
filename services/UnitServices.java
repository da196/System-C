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
import tz.go.tcra.hrms.dto.Unit;

@Component
public class UnitServices implements IUnitServices{
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;

	@Autowired
	private RestTemplate restTemplate;
	

	@Override
	public ResponseEntity<Unit[]> GetAll() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Unit> request = new HttpEntity<Unit>(header);
			ResponseEntity<Unit[]> response = restTemplate.exchange(
								AppConstants.BASE_URL+"/v1/orginisationUnit/getAllOrginisationUnit", 
								HttpMethod.GET, 
								request, 
								Unit[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Unit> Create(Unit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Unit[]> GetSection() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Unit> request = new HttpEntity<Unit>(header);
			ResponseEntity<Unit[]> response = restTemplate.exchange(
								AppConstants.BASE_URL+"/v1/orginisationUnit/getAllSections", 
								HttpMethod.GET, 
								request, 
								Unit[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Unit[]> GetDirAndUnits() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Unit> request = new HttpEntity<Unit>(header);
			ResponseEntity<Unit[]> response = restTemplate.exchange(
								AppConstants.BASE_URL+"/v1/orginisationUnit/getAllDepartmentsAndUnits", 
								HttpMethod.GET, 
								request, 
								Unit[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Unit[]> GetChildUnitFromParent(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders header = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Unit> request = new HttpEntity<Unit>(header);
			ResponseEntity<Unit[]> response = restTemplate.exchange(
								AppConstants.BASE_URL+"/v1/orginisationUnit/getAllChildUnitByParentId/"+id, 
								HttpMethod.GET, 
								request, 
								Unit[].class);
			
			return response;
		}
		return null;
	}

}

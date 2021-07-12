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
import tz.go.tcra.hrms.dto.Country;
import tz.go.tcra.hrms.dto.EmployeeRelative;
import tz.go.tcra.hrms.dto.Nationality;
import tz.go.tcra.hrms.dto.RelativeCategory;

@Component
public class EmployeeRelativesServices implements IEmployeeRelativesServices {
	
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmployeeRelative> AddRelative(EmployeeRelative relative) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeRelative> request = new HttpEntity<EmployeeRelative>(relative, headers);
			ResponseEntity<EmployeeRelative> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/employeeRelative/addEmployeeRelative", 
									  HttpMethod.POST, 
									  request, 
									  EmployeeRelative.class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Country[]> GetAllCountries() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Country> request = new HttpEntity<Country>(headers);
			ResponseEntity<Country[]> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/locationCountry/getAllLocationCountry", 
									  HttpMethod.GET, 
									  request, 
									  Country[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Nationality[]> GetAllNationalities() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Nationality> request = new HttpEntity<Nationality>(headers);
			ResponseEntity<Nationality[]> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/nationality/getAllNationality", 
									  HttpMethod.GET, 
									  request, 
									  Nationality[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<RelativeCategory[]> GetRelativeCategory() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<RelativeCategory> request = new HttpEntity<RelativeCategory>(headers);
			ResponseEntity<RelativeCategory[]> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/relativeCategory/getAllRelativeCategory", 
									  HttpMethod.GET, 
									  request, 
									  RelativeCategory[].class);		
			return response;
		}
		return null;
	}

	
}

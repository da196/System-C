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
import tz.go.tcra.hrms.dto.GeneralSettings;

@Component
public class GeneralSettingsServices implements IGeneralSettingsServices {
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<GeneralSettings[]> GetNotches() {
		if(restTemplate != null)
		{
			// token
			authToken = authenticationFacade.getAuthenticationToken();
			// headers
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/salaryscalenotch/getAllSalaryscalenotch", 
					  HttpMethod.GET, 
					  request, 
					  GeneralSettings[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings> AddNotch(GeneralSettings settings) {
		if(restTemplate != null) {
			// token
			authToken = authenticationFacade.getAuthenticationToken();
			// headers
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (settings, headers);		
			ResponseEntity<GeneralSettings> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/salaryscalenotch/addSalaryscalenotch", 
					  HttpMethod.POST, 
					  request, 
					  GeneralSettings.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings[]> GetInstitutionTypes() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/educationInstitutionType/getAllEducationInstitutionType", 
					  HttpMethod.GET, 
					  request, 
					  GeneralSettings[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings[]> GetInstitutionCategory() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/educationInstCategory/getAllEducationInstCategory", 
					  HttpMethod.GET, 
					  request, 
					  GeneralSettings[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings[]> GetRelativeCategories() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings[]> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/relativeCategory/getAllRelativeCategory", 
											  HttpMethod.GET, 
											  request, 
											  GeneralSettings[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings[]> GetReligions() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings[]> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/employeeReligion/getAllEmployeeReligion", 
											  HttpMethod.GET, 
											  request, 
											  GeneralSettings[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings> CreateRelativeCategory(GeneralSettings relativeCategory) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (relativeCategory, headers);		
			ResponseEntity<GeneralSettings> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/relativeCategory/addRelativeCategory", 
											  HttpMethod.POST, 
											  request, 
											  GeneralSettings.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings> DeleteRelativeCategory(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/relativeCategory/deleteRelativeCategory/"+id, 
											  HttpMethod.DELETE, 
											  request, 
											  GeneralSettings.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings> CreateReligion(GeneralSettings religion) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (religion, headers);		
			ResponseEntity<GeneralSettings> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/employeeReligion/addEmployeeReligion", 
											  HttpMethod.POST, 
											  request, 
											  GeneralSettings.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings> DeleteReligion(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/employeeReligion/deleteEmployeeReligion/"+id, 
											  HttpMethod.DELETE, 
											  request, 
											  GeneralSettings.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<GeneralSettings[]> GetAllEmploymentStatusReasons() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings[]> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/employmentStatusReason/getAllEmploymentStatusReason", 
											  HttpMethod.GET, 
											  request, 
											  GeneralSettings[].class);
			
			return response;
		}
		return null;
	}


	@Override
	public ResponseEntity<GeneralSettings> getSalaryscaleNotch(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/salaryscalenotch/getSalaryscalenotch/"+id, 
											  HttpMethod.GET, 
											  request, 
											  GeneralSettings.class);
			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<GeneralSettings> deleteSalaryscaleNotch(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (headers);		
			ResponseEntity<GeneralSettings> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/salaryscalenotch/deleteSalaryscalenotch/"+id, 
											  HttpMethod.DELETE, 
											  request, 
											  GeneralSettings.class);
			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<GeneralSettings>  updateSalaryNotch(GeneralSettings generalSettings, int id) {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings> (generalSettings, headers);		
		ResponseEntity<GeneralSettings> response = restTemplate.exchange(
				  AppConstants.BASE_URL+"/v1/salaryscalenotch/updateSalaryscalenotch/" + id, 
				  HttpMethod.PUT, 
				  request, 
				  GeneralSettings.class);
		
		return response;
	}
}

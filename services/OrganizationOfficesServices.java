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
import tz.go.tcra.hrms.dto.OrganizationOffices;

@Component
public class OrganizationOfficesServices implements IOrganizationOfficesServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<GeneralSettings[]> GetOrganizationCategory() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<GeneralSettings> request = new HttpEntity<GeneralSettings>(headers);
			ResponseEntity<GeneralSettings[]> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/organisationOfficeCategory/getAllOrganisationOfficeCategory", 
											HttpMethod.GET, 
											request, 
											GeneralSettings[].class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<OrganizationOffices[]> GetOrganizationOffice() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<OrganizationOffices> request = new HttpEntity<OrganizationOffices>(headers);
			ResponseEntity<OrganizationOffices[]> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/organisationOffice/getAllOrganisationOffices", 
											HttpMethod.GET, 
											request, 
											OrganizationOffices[].class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<OrganizationOffices> CreateOffice(OrganizationOffices office) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<OrganizationOffices> request = new HttpEntity<OrganizationOffices>(office, headers);
			ResponseEntity<OrganizationOffices> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/organisationOffice/addOrganisationOffice", 
											HttpMethod.POST, 
											request, 
											OrganizationOffices.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<OrganizationOffices> DeleteOffice(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<OrganizationOffices> request = new HttpEntity<OrganizationOffices>(headers);
			ResponseEntity<OrganizationOffices> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/organisationOffice/deleteOrganisationOffice/"+id, 
											HttpMethod.DELETE, 
											request, 
											OrganizationOffices.class);
			return response;
		}
		return null;
	}

}

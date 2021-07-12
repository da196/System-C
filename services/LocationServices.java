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
import tz.go.tcra.hrms.dto.Location;

@Component
public class LocationServices implements ILocationServices{
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;
	

	@Override
	public ResponseEntity<Location[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Location> request = new HttpEntity<Location> (headers);		
		ResponseEntity<Location[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/locationCountry/getAllLocationCountry", 
				  HttpMethod.GET, 
				  request, 
				  Location[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<Location> Create(Location location) {
		if(location!=null) {	
			// token
			authToken = authenticationFacade.getAuthenticationToken();
			// headers
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Location> request = new HttpEntity<> (location, headers);		
			ResponseEntity<Location> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/locationCountry/addLocationCountry", 
					  HttpMethod.POST, 
					  request, 
					  Location.class);
			
			return response;
		}
		return null;
	}

	@Override
	public void deleteLocation(Long id) {
		// Delete location
		restTemplate.delete(AppConstants.BASE_URL+"/v1/country/"+id);		
	}

}

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
import tz.go.tcra.hrms.dto.LeaveSettings;

@Component
public class LeaveSettingsServices implements ILeaveSettingsServices {
	
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<LeaveSettings[]> GetLeaveTypes() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<LeaveSettings> request = new HttpEntity<LeaveSettings>(headers);
			ResponseEntity<LeaveSettings[]> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/leaveType/getAllLeaveType", 
										HttpMethod.GET, 
										request, 
										LeaveSettings[].class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LeaveSettings> AddLeaveType(LeaveSettings leave) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<LeaveSettings> request = new HttpEntity<LeaveSettings>(leave, headers);
			ResponseEntity<LeaveSettings> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/leaveType/addLeaveType", 
										HttpMethod.POST, 
										request, 
										LeaveSettings.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LeaveSettings> DeleteLeaveType(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<LeaveSettings> request = new HttpEntity<LeaveSettings>(headers);
			ResponseEntity<LeaveSettings> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/leaveType/deleteLeaveType/"+id, 
										HttpMethod.DELETE, 
										request, 
										LeaveSettings.class);
			return response;
		}
		return null;
	}

}

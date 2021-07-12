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
import tz.go.tcra.hrms.dto.EmployeeStatistics;

@Component
public class EmployeeStatisticsServices implements IEmployeeStatisticsServices{
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmployeeStatistics> GetByType(int id) {
		if(restTemplate != null) {
			// token
			authToken = authenticationFacade.getAuthenticationToken();
			// headers
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeStatistics> request = new HttpEntity<EmployeeStatistics>(headers);
			ResponseEntity<EmployeeStatistics> response = restTemplate.exchange(
															AppConstants.BASE_URL+"/v1/employeeStatistics/getEmployeeCount/"+id, 
															HttpMethod.GET, 
															request, 
															EmployeeStatistics.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeStatistics[]> GetUnitDistribution() {
		if(restTemplate != null) {
			// token
			authToken = authenticationFacade.getAuthenticationToken();
			// headers
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeStatistics> request = new HttpEntity<EmployeeStatistics>(headers);
			ResponseEntity<EmployeeStatistics[]> response = restTemplate.exchange(
															AppConstants.BASE_URL+"/v1/employeeStatistics/getUnitDetails", 
															HttpMethod.GET, 
															request, 
															EmployeeStatistics[].class);
			return response;
		}
		return null;
	}

}

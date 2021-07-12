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
import tz.go.tcra.hrms.dto.Employee;

@Component
public class EmployeeServices implements IEmployeeServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	// constructor
	public EmployeeServices() {}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<Employee[]> GetAll() {	
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Employee> request = new HttpEntity<Employee>(headers);
		ResponseEntity<Employee[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/hrmsEmployee/getAllEmployee", 
											  HttpMethod.GET, 
											  request, 
											  Employee[].class);		
		return response;
	}
	
	@Override
	public ResponseEntity<Employee[]> GetAllEmployeesForDatatable() {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<Employee> request = new HttpEntity<Employee>(headers);
		ResponseEntity<Employee[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/hrmsEmployee/getAllEmployeeV2", 
											  HttpMethod.GET, 
											  request, 
											  Employee[].class);		
		return response;
	}
	
	@Override
	public ResponseEntity<Employee> Create(Employee employee) {
		if(employee != null) {
			// token
			authToken = authenticationFacade.getAuthenticationToken();
			// headers
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
			ResponseEntity<Employee> response = 
					restTemplate.exchange(AppConstants.BASE_URL+"/v1/hrmsEmployee/addEmployee", 
					  HttpMethod.POST, 
					  request, 
					  Employee.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Employee[]> GetAllEmployeesLessDetails() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Employee> request = new HttpEntity<Employee>(headers);
			ResponseEntity<Employee[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/hrmsEmployee/getallemployeelessdetails", 
												  HttpMethod.GET, 
												  request, 
												  Employee[].class);		
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Employee> GetEmployeeDetailsById(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Employee> request = new HttpEntity<Employee>(headers);
			ResponseEntity<Employee> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/hrmsEmployee/getEmployee/"+id, 
									  HttpMethod.GET, 
									  request, 
									  Employee.class);		
			return response;
		}
		return null;
	}

}

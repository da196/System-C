package tz.go.tcra.hrms.services.payroll;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.payroll.EmployeePension;
import tz.go.tcra.hrms.dto.payroll.Loan;

@Service
public class EmployeePensionServices implements IEmployeePensionServices {
	private static final Logger logger = Logger.getLogger(EmployeePensionServices.class); // log4j

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmployeePension[]> GetAll(String authToken) {
		logger.info("Get all employee pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeePension> request = new HttpEntity<EmployeePension> (headers);		
			final ResponseEntity<EmployeePension[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/employeeSocialSecurityScheme/getAllEmployeeSocialSecurityScheme", 
					  HttpMethod.GET, 
					  request, 
					  EmployeePension[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeePension> Get(String authToken, int id) {
		logger.info("Get employee pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeePension> request = new HttpEntity<EmployeePension> (headers);		
			final ResponseEntity<EmployeePension> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/employeeSocialSecurityScheme/getEmployeeSocialSecuritySchemeById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  EmployeePension.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeePension> Create(String authToken, EmployeePension pension) {
		logger.info("Create employee pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeePension> request = new HttpEntity<EmployeePension> (pension,headers);		
			final ResponseEntity<EmployeePension> response = restTemplate.exchange(AppConstants.BASE_URL + "/v1/employeeSocialSecurityScheme/addEmployeeSocialSecurityScheme", 
					  HttpMethod.POST, 
					  request, 
					  EmployeePension.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeePension> Update(String authToken, int id, EmployeePension pension) {
		logger.info("Update employee pension");
		if(!StringUtils.isEmpty(authToken)) {		
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeePension> request = new HttpEntity<EmployeePension> (pension,headers);
			final ResponseEntity<EmployeePension> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/employeeSocialSecurityScheme/updateEmployeeSocialSecurityScheme/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  EmployeePension.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete employee pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Loan> request = new HttpEntity<Loan> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/employeeSocialSecurityScheme/deleteEmployeeSocialSecurityScheme/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

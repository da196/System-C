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
import tz.go.tcra.hrms.dto.payroll.EmployeeInsurance;

@Service
public class EmployeeInsuranceServices implements IEmployeeInsuranceServices {
	private static final Logger logger = Logger.getLogger(EmployeeInsuranceServices.class); // log4j

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmployeeInsurance[]> GetAll(String authToken) {
		logger.info("Get all employee insurance");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeeInsurance> request = new HttpEntity<EmployeeInsurance> (headers);		
			final ResponseEntity<EmployeeInsurance[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/employeeInsurance/getAllEmployeeInsurance", 
					  HttpMethod.GET, 
					  request, 
					  EmployeeInsurance[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeInsurance> Get(String authToken, int id) {
		logger.info("Get employee insurance");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeeInsurance> request = new HttpEntity<EmployeeInsurance> (headers);		
			final ResponseEntity<EmployeeInsurance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/employeeInsurance/getEmployeeInsuranceById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  EmployeeInsurance.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeInsurance> Create(String authToken, EmployeeInsurance insurance) {
		logger.info(String.format("Create employee insurance = {%s} ", insurance));
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeeInsurance> request = new HttpEntity<EmployeeInsurance> (insurance,headers);		
			final ResponseEntity<EmployeeInsurance> response = restTemplate.exchange(AppConstants.BASE_URL + "/v1/employeeInsurance/addEmployeeInsurance", 
					  HttpMethod.POST, 
					  request, 
					  EmployeeInsurance.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeInsurance> Update(String authToken, int id, EmployeeInsurance insurance) {
		logger.info("Update employee insurance");
		if(!StringUtils.isEmpty(authToken)) {		
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeeInsurance> request = new HttpEntity<EmployeeInsurance> (insurance,headers);
			final ResponseEntity<EmployeeInsurance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/employeeInsurance/updateEmployeeInsurance/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  EmployeeInsurance.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete employee insurance");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<EmployeeInsurance> request = new HttpEntity<EmployeeInsurance> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/employeeInsurance/deleteEmployeeInsurance/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

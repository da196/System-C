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
import tz.go.tcra.hrms.dto.payroll.LoanFrequency;
import tz.go.tcra.hrms.dto.payroll.LoanType;

@Service
public class LoanFrequencyServices implements ILoanFrequencyServices {
	private static final Logger logger = Logger.getLogger(LoanFrequencyServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<LoanFrequency[]> GetAll(String authToken) {
		logger.info("Get all loan frequency");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanFrequency> request = new HttpEntity<LoanFrequency> (headers);		
			final ResponseEntity<LoanFrequency[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanFrequency/getAllPayrollLoanFrequency", 
					  HttpMethod.GET, 
					  request, 
					  LoanFrequency[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanFrequency> Get(String authToken, int id) {
		logger.info("Get loan frequency");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanFrequency> request = new HttpEntity<LoanFrequency> (headers);		
			final ResponseEntity<LoanFrequency> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanFrequency/getPayrollLoanFrequencyById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  LoanFrequency.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanFrequency> Create(String authToken, LoanFrequency frquency) {
		logger.info("Create loan frequency");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanFrequency> request = new HttpEntity<LoanFrequency> (frquency,headers);		
			final ResponseEntity<LoanFrequency> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanFrequency/addPayrollLoanFrequency", 
					  HttpMethod.POST, 
					  request, 
					  LoanFrequency.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanFrequency> Update(String authToken, int id, LoanFrequency frquency) {
		logger.info("Update loan frequency");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanFrequency> request = new HttpEntity<LoanFrequency> (frquency,headers);		
			final ResponseEntity<LoanFrequency> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanFrequency/updatePayrollLoanFrequency/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  LoanFrequency.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete loan type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanType> request = new HttpEntity<LoanType> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanFrequency/deletePayrollLoanFrequency/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

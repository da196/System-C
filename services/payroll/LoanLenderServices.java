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
import tz.go.tcra.hrms.dto.payroll.LoanLender;
import tz.go.tcra.hrms.dto.payroll.LoanType;
import tz.go.tcra.hrms.dto.payroll.LoanLender;
import tz.go.tcra.hrms.dto.payroll.LoanLender;
import tz.go.tcra.hrms.dto.payroll.LoanLender;
import tz.go.tcra.hrms.dto.payroll.LoanLender;

@Service
public class LoanLenderServices implements ILoanLenderServices {
	private static final Logger logger = Logger.getLogger(LoanLenderServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<LoanLender[]> GetAll(String authToken) {
		logger.info("Get all loan lenders");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanLender> request = new HttpEntity<LoanLender> (headers);		
			final ResponseEntity<LoanLender[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanLender/getAllPayrollLoanLender", 
					  HttpMethod.GET, 
					  request, 
					  LoanLender[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanLender> Get(String authToken, int id) {
		logger.info("Get loan lender");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanLender> request = new HttpEntity<LoanLender> (headers);		
			final ResponseEntity<LoanLender> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanLender/getPayrollLoanLenderById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  LoanLender.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanLender> Create(String authToken, LoanLender lender) {
		logger.info("Create loan lender");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanLender> request = new HttpEntity<LoanLender> (lender,headers);		
			final ResponseEntity<LoanLender> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanLender/addPayrollLoanLender", 
					  HttpMethod.POST, 
					  request, 
					  LoanLender.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanLender> Update(String authToken, int id, LoanLender lender) {
		logger.info("Update loan lender");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanLender> request = new HttpEntity<LoanLender> (lender,headers);		
			final ResponseEntity<LoanLender> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanLender/updatePayrollLoanLender/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  LoanLender.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete loan lender");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanLender> request = new HttpEntity<LoanLender> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanLender/deletePayrollLoanLender/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

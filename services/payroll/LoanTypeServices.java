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
import tz.go.tcra.hrms.dto.payroll.Loan;
import tz.go.tcra.hrms.dto.payroll.LoanType;

@Service
public class LoanTypeServices implements ILoanTypeServices {
	private static final Logger logger = Logger.getLogger(LoanTypeServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<LoanType[]> GetAll(String authToken) {
		logger.info("Get all loan types");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanType> request = new HttpEntity<LoanType> (headers);		
			final ResponseEntity<LoanType[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanType/getAllPayrollLoanType", 
					  HttpMethod.GET, 
					  request, 
					  LoanType[].class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<LoanType> Get(String authToken,int id) {
		logger.info("Get loan type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanType> request = new HttpEntity<LoanType> (headers);		
			final ResponseEntity<LoanType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanType/getPayrollLoanTypeById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  LoanType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanType> Create(String authToken, LoanType type) {
		logger.info("Create loan type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanType> request = new HttpEntity<LoanType> (type,headers);		
			final ResponseEntity<LoanType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanType/addPayrollLoanType", 
					  HttpMethod.POST, 
					  request, 
					  LoanType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanType> Update(String authToken, int id, LoanType type) {
		logger.info("Update loan type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanType> request = new HttpEntity<LoanType> (type,headers);		
			final ResponseEntity<LoanType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanType/updatePayrollLoanType/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  LoanType.class);			
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
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanType/deletePayrollLoanType/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanType[]> GetAllByVendor(String authToken, int lenderid) {
		logger.info("Get all loan types");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanType> request = new HttpEntity<LoanType> (headers);		
			final ResponseEntity<LoanType[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollLoanType/getPayrollLoanTypeByLenderId/"+lenderid, 
					  HttpMethod.GET, 
					  request, 
					  LoanType[].class);			
			return response;
		}
		return null;
	}

}

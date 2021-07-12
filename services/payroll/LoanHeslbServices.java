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
import tz.go.tcra.hrms.dto.payroll.LoanHeslb;

@Service
public class LoanHeslbServices implements ILoanHeslbServices {
	private static final Logger logger = Logger.getLogger(LoanHeslbServices.class); // log4j

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<LoanHeslb[]> GetAll(String authToken) {
		logger.info("Get all loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanHeslb> request = new HttpEntity<LoanHeslb> (headers);		
			final ResponseEntity<LoanHeslb[]> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollEmployeeLoanDetailsHeslb/getAllPayrollEmployeeLoanDetailsHeslb", 
					  HttpMethod.GET, 
					  request, 
					  LoanHeslb[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanHeslb> Get(String authToken, int id) {
		logger.info("Get loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanHeslb> request = new HttpEntity<LoanHeslb> (headers);		
			final ResponseEntity<LoanHeslb> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollEmployeeLoanDetailsHeslb/getPayrollEmployeeLoanDetailsHeslbById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  LoanHeslb.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanHeslb> GetByLoan(String authToken, int loanId) {
		logger.info("Get loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanHeslb> request = new HttpEntity<LoanHeslb> (headers);		
			final ResponseEntity<LoanHeslb> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollEmployeeLoanDetailsHeslb/getPayrollEmployeeLoanDetailsHeslbByLoanId/"+loanId, 
					  HttpMethod.GET, 
					  request, 
					  LoanHeslb.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanHeslb> Create(String authToken, LoanHeslb loan) {
		logger.info("Create loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanHeslb> request = new HttpEntity<LoanHeslb> (loan,headers);		
			final ResponseEntity<LoanHeslb> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/payrollEmployeeLoanDetailsHeslb/addPayrollEmployeeLoanDetailsHeslb", 
					  HttpMethod.POST, 
					  request, 
					  LoanHeslb.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<LoanHeslb> Update(String authToken, int id, LoanHeslb loan) {
		logger.info("Update loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanHeslb> request = new HttpEntity<LoanHeslb> (loan,headers);		
			final ResponseEntity<LoanHeslb> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/payrollEmployeeLoanDetailsHeslb/updatePayrollEmployeeLoanDetailsHeslb/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  LoanHeslb.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete loan heslb");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanHeslb> request = new HttpEntity<LoanHeslb> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/payrollEmployeeLoanDetailsHeslb/deletePayrollEmployeeLoanDetailsHeslb/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

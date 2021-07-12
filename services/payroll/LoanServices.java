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

@Service
public class LoanServices implements ILoanServices {
	private static final Logger logger = Logger.getLogger(LoanServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<Loan[]> GetAll(String authToken) {
		logger.info("Get all loan");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Loan> request = new HttpEntity<Loan> (headers);		
			final ResponseEntity<Loan[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollEmployeeLoan/getAllPayrollEmployeeLoan", 
					  HttpMethod.GET, 
					  request, 
					  Loan[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Loan[]> GetAllByEmployee(String authToken, int employeeId) {
		logger.info("Get all loan by employee id");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Loan> request = new HttpEntity<Loan> (headers);		
			final ResponseEntity<Loan[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollEmployeeLoan/getPayrollEmployeeLoanByEmpId/"+employeeId, 
					  HttpMethod.GET, 
					  request, 
					  Loan[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Loan[]> GetAllByUnit(String authToken, int unitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Loan[]> GetAllByUnitType(String authToken, int unitTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Loan[]> GetAllByStatus(String authToken, int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Loan[]> GetAllByDateRange(String authToken, int dateMin, int dateMax) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Loan> Get(String authToken, int id) {
		logger.info("Get loan");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Loan> request = new HttpEntity<Loan> (headers);		
			final ResponseEntity<Loan> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollEmployeeLoan/getPayrollEmployeeLoanById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  Loan.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Loan> Create(String authToken, Loan loan) {
		logger.info("Create loan");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Loan> request = new HttpEntity<Loan> (loan,headers);		
			final ResponseEntity<Loan> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollEmployeeLoan/addPayrollEmployeeLoan", 
					  HttpMethod.POST, 
					  request, 
					  Loan.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Loan> Update(String authToken, int id, Loan loan) {
		logger.info("Update loan");
		if(!StringUtils.isEmpty(authToken)) {		
			final int durationAdjust = 0;
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Loan> request = new HttpEntity<Loan> (loan,headers);
			final ResponseEntity<Loan> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollEmployeeLoan/updatePayrollEmployeeLoan/"+id+"/"+durationAdjust , 
					  HttpMethod.PUT, 
					  request, 
					  Loan.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete loan");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Loan> request = new HttpEntity<Loan> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollEmployeeLoan/deletePayrollEmployeeLoan/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}


}

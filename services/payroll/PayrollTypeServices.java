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
import tz.go.tcra.hrms.dto.payroll.PayrollType;

@Service
public class PayrollTypeServices implements IPayrollTypeServices {
	private static final Logger logger = Logger.getLogger(PayrollTypeServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<PayrollType[]> GetAll(String authToken) {
		logger.info("Get all payroll types");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PayrollType> request = new HttpEntity<PayrollType> (headers);		
			final ResponseEntity<PayrollType[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollType/getAllPayrollType", 
					  HttpMethod.GET, 
					  request, 
					  PayrollType[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PayrollType> Get(String authToken, int id) {
		logger.info("Get payroll type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PayrollType> request = new HttpEntity<PayrollType> (headers);		
			final ResponseEntity<PayrollType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollType/getPayrollTypeById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  PayrollType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PayrollType> Create(String authToken, PayrollType type) {
		logger.info("Create payroll type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PayrollType> request = new HttpEntity<PayrollType> (type,headers);		
			final ResponseEntity<PayrollType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollType/addPayrollType", 
					  HttpMethod.POST, 
					  request, 
					  PayrollType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PayrollType> Update(String authToken, int id, PayrollType type) {
		logger.info("Update payroll type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PayrollType> request = new HttpEntity<PayrollType> (type,headers);		
			final ResponseEntity<PayrollType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollType/updatePayrollType/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  PayrollType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete payroll type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PayrollType> request = new HttpEntity<PayrollType> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollType/deletePayrollType/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

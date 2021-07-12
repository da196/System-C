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
import tz.go.tcra.hrms.dto.payroll.TaxType;

@Service
public class TaxTypeServices implements ITaxTypeServices {
	private static final Logger logger = Logger.getLogger(TaxTypeServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<TaxType[]> GetAll(String authToken) {
		logger.info("Get all tax types");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxType> request = new HttpEntity<TaxType> (headers);		
			final ResponseEntity<TaxType[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxType/getAllPayrollTaxType", 
					  HttpMethod.GET, 
					  request, 
					  TaxType[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<TaxType> Get(String authToken, int id) {
		logger.info("Get loan type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxType> request = new HttpEntity<TaxType> (headers);		
			final ResponseEntity<TaxType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxType/getPayrollTaxTypeById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  TaxType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<TaxType> Create(String authToken, TaxType type) {
		logger.info("Create tax type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxType> request = new HttpEntity<TaxType> (type,headers);		
			final ResponseEntity<TaxType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxType/addPayrollTaxType", 
					  HttpMethod.POST, 
					  request, 
					  TaxType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<TaxType> Update(String authToken, int id, TaxType type) {
		logger.info("Update tax type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxType> request = new HttpEntity<TaxType> (type,headers);		
			final ResponseEntity<TaxType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxType/updatePayrollTaxType/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  TaxType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete tax type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxType> request = new HttpEntity<TaxType> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxType/deletePayrollTaxType/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

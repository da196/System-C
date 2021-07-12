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
import tz.go.tcra.hrms.dto.payroll.TaxStructure;

@Service
public class TaxStructureServices implements ITaxStructureServices {
	private static final Logger logger = Logger.getLogger(TaxStructureServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<TaxStructure[]> GetAll(String authToken) {
		logger.info("Get all tax structure");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxStructure> request = new HttpEntity<TaxStructure> (headers);		
			final ResponseEntity<TaxStructure[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxStructure/getAllPayrollTaxStructure", 
					  HttpMethod.GET, 
					  request, 
					  TaxStructure[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<TaxStructure> Get(String authToken, int id) {
		logger.info("Get tax structure");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxStructure> request = new HttpEntity<TaxStructure> (headers);		
			final ResponseEntity<TaxStructure> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxStructure/getPayrollTaxStructureById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  TaxStructure.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<TaxStructure> GetByAmount(String authToken, double amount) {
		logger.info("Get tax structure");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxStructure> request = new HttpEntity<TaxStructure> (headers);		
			final ResponseEntity<TaxStructure> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxStructure/getPayrollTaxStructureByAmount/"+amount, 
					  HttpMethod.GET, 
					  request, 
					  TaxStructure.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<TaxStructure> Create(String authToken, TaxStructure structure) {
		logger.info("Create tax structure");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxStructure> request = new HttpEntity<TaxStructure> (structure,headers);		
			final ResponseEntity<TaxStructure> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxStructure/addPayrollTaxStructure", 
					  HttpMethod.POST, 
					  request, 
					  TaxStructure.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<TaxStructure> Update(String authToken, int id, TaxStructure structure) {
		logger.info("Update tax structure");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxStructure> request = new HttpEntity<TaxStructure> (structure,headers);		
			final ResponseEntity<TaxStructure> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxStructure/updatePayrollTaxStructure/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  TaxStructure.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete tax structure");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<TaxStructure> request = new HttpEntity<TaxStructure> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollTaxStructure/deletePayrollTaxStructure/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

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
import tz.go.tcra.hrms.dto.payroll.InsuranceServiceProvider;

@Service
public class InsuranceServiceProviderServices implements IInsuranceServiceProviderServices {
	private static final Logger logger = Logger.getLogger(InsuranceServiceProviderServices.class); // log4j

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<InsuranceServiceProvider[]> GetAll(String authToken) {
		logger.info("Get all insurance service providers");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceServiceProvider> request = new HttpEntity<InsuranceServiceProvider> (headers);		
			final ResponseEntity<InsuranceServiceProvider[]> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/insuranceProvider/getAllInsuranceProvider", 
					  HttpMethod.GET, 
					  request, 
					  InsuranceServiceProvider[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<InsuranceServiceProvider[]> GetAllByEmployee(String authToken, int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<InsuranceServiceProvider> Get(String authToken, int id) {
		logger.info("Get all insurance service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceServiceProvider> request = new HttpEntity<InsuranceServiceProvider> (headers);		
			final ResponseEntity<InsuranceServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/insuranceProvider/getInsuranceProvider/"+id, 
					  HttpMethod.GET, 
					  request, 
					  InsuranceServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<InsuranceServiceProvider> Create(String authToken, InsuranceServiceProvider provider) {
		logger.info("Create insurance service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceServiceProvider> request = new HttpEntity<InsuranceServiceProvider> (provider,headers);		
			final ResponseEntity<InsuranceServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/insuranceProvider/addInsuranceProvider", 
					  HttpMethod.POST, 
					  request, 
					  InsuranceServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<InsuranceServiceProvider> Update(String authToken, int id, InsuranceServiceProvider provider) {
		logger.info("Update insurance service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceServiceProvider> request = new HttpEntity<InsuranceServiceProvider> (provider,headers);		
			final ResponseEntity<InsuranceServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/insuranceProvider/updateInsuranceProvider/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  InsuranceServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete insurance service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceServiceProvider> request = new HttpEntity<InsuranceServiceProvider> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/insuranceProvider/deleteInsuranceProvider/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

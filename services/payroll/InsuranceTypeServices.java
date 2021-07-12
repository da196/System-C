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
import tz.go.tcra.hrms.dto.payroll.InsuranceType;
import tz.go.tcra.hrms.dto.payroll.InsuranceType;
import tz.go.tcra.hrms.dto.payroll.InsuranceType;
import tz.go.tcra.hrms.dto.payroll.InsuranceType;
import tz.go.tcra.hrms.dto.payroll.InsuranceType;
import tz.go.tcra.hrms.dto.payroll.InsuranceType;

@Service
public class InsuranceTypeServices implements IInsuranceTypeServices {
	private static final Logger logger = Logger.getLogger(InsuranceTypeServices.class); // log4j

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<InsuranceType[]> GetAll(String authToken) {
		logger.info("Get all insurance types");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceType> request = new HttpEntity<InsuranceType> (headers);		
			final ResponseEntity<InsuranceType[]> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/insuranceType/getAllInsuranceType", 
					  HttpMethod.GET, 
					  request, 
					  InsuranceType[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<InsuranceType> Get(String authToken, int id) {
		logger.info("Get insurance type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceType> request = new HttpEntity<InsuranceType> (headers);		
			final ResponseEntity<InsuranceType> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/insuranceType/getInsuranceTypeById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  InsuranceType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<InsuranceType> Create(String authToken, InsuranceType type) {
		logger.info("Create insurance type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceType> request = new HttpEntity<InsuranceType> (type,headers);		
			final ResponseEntity<InsuranceType> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/insuranceType/addInsuranceType", 
					  HttpMethod.POST, 
					  request, 
					  InsuranceType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<InsuranceType> Update(String authToken, int id, InsuranceType type) {
		logger.info("Update insurance type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceType> request = new HttpEntity<InsuranceType> (type,headers);		
			final ResponseEntity<InsuranceType> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/insuranceType/updateInsuranceType/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  InsuranceType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete insurance type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<InsuranceType> request = new HttpEntity<InsuranceType> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL + 
					"/v1/insuranceType/deleteInsuranceType/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

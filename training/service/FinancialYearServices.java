package tz.go.tcra.hrms.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.training.entity.FinancialYear;



@Service
public class FinancialYearServices implements IFinancialYearServices {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<FinancialYear[]> GetAll(String authToken) {
		// headers
		final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		final HttpEntity<FinancialYear> request = new HttpEntity<FinancialYear>(headers);
		final ResponseEntity<FinancialYear[]> response = restTemplate.exchange(
				AppConstants.BASE_URL + "/v1/performanceFinancialYear/getAllPerformanceFinancialYear", 
				HttpMethod.GET, request, FinancialYear[].class);
		return response;
	}

}

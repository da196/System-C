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
import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryInsurance;

@Service
public class ContributionMandatoryInsuranceServices implements IContributionMandatoryInsuranceServices{
	private static final Logger logger = Logger.getLogger(ContributionMandatoryInsuranceServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<ContributionMandatoryInsurance[]> GetAll(String authToken) {
		logger.info("Get all contribution - insurance");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryInsurance> request = new HttpEntity<ContributionMandatoryInsurance>(headers);		
			final ResponseEntity<ContributionMandatoryInsurance[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatoryInsurance/getAllPayrollContributionMandatoryInsurance", 
					  HttpMethod.GET, 
					  request, 
					  ContributionMandatoryInsurance[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryInsurance> Get(String authToken, int id) {
		logger.info("Get contribution - insurance");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryInsurance> request = new HttpEntity<ContributionMandatoryInsurance> (headers);		
			final ResponseEntity<ContributionMandatoryInsurance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatoryInsurance/getPayrollContributionMandatoryInsuranceById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  ContributionMandatoryInsurance.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryInsurance> GetByAmount(String authToken, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryInsurance> Create(String authToken,
			ContributionMandatoryInsurance contribution) {
		logger.info("Create contribution - insurance");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryInsurance> request = new HttpEntity<ContributionMandatoryInsurance> (contribution,headers);		
			final ResponseEntity<ContributionMandatoryInsurance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatoryInsurance/addPayrollContributionMandatoryInsurance", 
					  HttpMethod.POST, 
					  request, 
					  ContributionMandatoryInsurance.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryInsurance> Update(String authToken, int id,
			ContributionMandatoryInsurance contribution) {
		logger.info("Update contribution - insurance");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryInsurance> request = new HttpEntity<ContributionMandatoryInsurance> (contribution,headers);		
			final ResponseEntity<ContributionMandatoryInsurance> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatoryInsurance/updatePayrollContributionMandatoryInsurance/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  ContributionMandatoryInsurance.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete contribution - insurance");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryInsurance> request = new HttpEntity<ContributionMandatoryInsurance> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatoryInsurance/deletePayrollContributionMandatoryInsurance/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

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
import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryPension;

@Service
public class ContributionMandatoryPensionServices implements IContributionMandatoryPensionServices {
	private static final Logger logger = Logger.getLogger(ContributionMandatoryPensionServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<ContributionMandatoryPension[]> GetAll(String authToken) {
		logger.info("Get all contribution - pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryPension> request = new HttpEntity<ContributionMandatoryPension>(headers);		
			final ResponseEntity<ContributionMandatoryPension[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatorySocialSecurityScheme/getAllPayrollContributionMandatorySocialSecurityScheme", 
					  HttpMethod.GET, 
					  request, 
					  ContributionMandatoryPension[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryPension> Get(String authToken, int id) {
		logger.info("Get contribution - pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryPension> request = new HttpEntity<ContributionMandatoryPension> (headers);		
			final ResponseEntity<ContributionMandatoryPension> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatorySocialSecurityScheme/getPayrollContributionMandatorySocialSecuritySchemeById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  ContributionMandatoryPension.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryPension> GetByAmount(String authToken, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryPension> Create(String authToken,
			ContributionMandatoryPension contribution) {
		logger.info("Create contribution - pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryPension> request = new HttpEntity<ContributionMandatoryPension> (contribution,headers);		
			final ResponseEntity<ContributionMandatoryPension> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatorySocialSecurityScheme/addPayrollContributionMandatorySocialSecurityScheme", 
					  HttpMethod.POST, 
					  request, 
					  ContributionMandatoryPension.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryPension> Update(String authToken, int id,
			ContributionMandatoryPension contribution) {
		logger.info("Update contribution - pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryPension> request = new HttpEntity<ContributionMandatoryPension> (contribution,headers);		
			final ResponseEntity<ContributionMandatoryPension> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatorySocialSecurityScheme/updatePayrollContributionMandatorySocialSecurityScheme/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  ContributionMandatoryPension.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete contribution - pension");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryInsurance> request = new HttpEntity<ContributionMandatoryInsurance> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionMandatorySocialSecurityScheme/deletePayrollContributionMandatorySocialSecurityScheme/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

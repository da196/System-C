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
import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryType;
import tz.go.tcra.hrms.dto.payroll.LoanType;

@Service
public class ContributionMandatoryTypeServices implements IContributionMandatoryTypeServices {
	private static final Logger logger = Logger.getLogger(ContributionMandatoryTypeServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<ContributionMandatoryType[]> GetAll(String authToken) {
		logger.info("Get all mandatory contribution types");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryType> request = new HttpEntity<ContributionMandatoryType> (headers);		
			final ResponseEntity<ContributionMandatoryType[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeMandatory/getAllPayrollContributionTypeMandatory", 
					  HttpMethod.GET, 
					  request, 
					  ContributionMandatoryType[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryType> Get(String authToken, int id) {
		logger.info("Get mandatory contribution type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryType> request = new HttpEntity<ContributionMandatoryType> (headers);		
			final ResponseEntity<ContributionMandatoryType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeMandatory/getPayrollContributionTypeMandatoryById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  ContributionMandatoryType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryType> Create(String authToken, ContributionMandatoryType type) {
		logger.info("Create mandatory contribution type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryType> request = new HttpEntity<ContributionMandatoryType> (type,headers);		
			final ResponseEntity<ContributionMandatoryType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeMandatory/addPayrollContributionTypeMandatory", 
					  HttpMethod.POST, 
					  request, 
					  ContributionMandatoryType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionMandatoryType> Update(String authToken, int id, ContributionMandatoryType type) {
		logger.info("Update mandatory contribution type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionMandatoryType> request = new HttpEntity<ContributionMandatoryType> (type,headers);		
			final ResponseEntity<ContributionMandatoryType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeMandatory/updatePayrollContributionTypeMandatory/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  ContributionMandatoryType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete mandatory contribution type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<LoanType> request = new HttpEntity<LoanType> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeMandatory/deletePayrollContributionTypeMandatory/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

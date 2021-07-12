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
import tz.go.tcra.hrms.dto.payroll.ContributionVoluntary;

@Service
public class ContributionVoluntaryServices implements IContributionVoluntaryServices {
	private static final Logger logger = Logger.getLogger(ContributionVoluntaryServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<ContributionVoluntary[]> GetAll(String authToken) {
		logger.info("Get all voluntary contribution");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntary> request = new HttpEntity<ContributionVoluntary> (headers);		
			final ResponseEntity<ContributionVoluntary[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntary/getAllPayrollContributionVoluntary", 
					  HttpMethod.GET, 
					  request, 
					  ContributionVoluntary[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntary[]> GetAllByEmployee(String authToken, int employeeId) {
		logger.info("Get all voluntary contribution by employee");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntary> request = new HttpEntity<ContributionVoluntary> (headers);		
			final ResponseEntity<ContributionVoluntary[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntary/getPayrollContributionVoluntaryByEmpId/" +employeeId, 
					  HttpMethod.GET, 
					  request, 
					  ContributionVoluntary[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntary> Get(String authToken, int id) {
		logger.info("Get voluntary contribution by id");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntary> request = new HttpEntity<ContributionVoluntary> (headers);		
			final ResponseEntity<ContributionVoluntary> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntary/getPayrollContributionVoluntaryById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  ContributionVoluntary.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntary> Create(String authToken, ContributionVoluntary contribution) {
		logger.info("Create voluntary contribution");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntary> request = new HttpEntity<ContributionVoluntary> (contribution,headers);		
			final ResponseEntity<ContributionVoluntary> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntary/addPayrollContributionVoluntary", 
					  HttpMethod.POST, 
					  request, 
					  ContributionVoluntary.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntary> Update(String authToken, int id, ContributionVoluntary contribution) {
		logger.info("Update voluntary contribution");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntary> request = new HttpEntity<ContributionVoluntary> (contribution,headers);		
			final ResponseEntity<ContributionVoluntary> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntary/updatePayrollContributionVoluntary/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  ContributionVoluntary.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete voluntary contribution");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntary> request = new HttpEntity<ContributionVoluntary> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntary/deletePayrollContributionVoluntary/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

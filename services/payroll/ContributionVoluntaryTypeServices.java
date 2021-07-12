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
import tz.go.tcra.hrms.dto.payroll.ContributionVoluntaryType;

@Service
public class ContributionVoluntaryTypeServices implements IContributionVoluntaryTypeServices {
	private static final Logger logger = Logger.getLogger(ContributionVoluntaryTypeServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<ContributionVoluntaryType[]> GetAll(String authToken) {
		logger.info("Get all voluntary contribution types");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryType> request = new HttpEntity<ContributionVoluntaryType> (headers);		
			final ResponseEntity<ContributionVoluntaryType[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeVoluntary/getAllPayrollContributionTypeVoluntary", 
					  HttpMethod.GET, 
					  request, 
					  ContributionVoluntaryType[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntaryType> Get(String authToken, int id) {
		logger.info("Get voluntary contribution type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryType> request = new HttpEntity<ContributionVoluntaryType> (headers);		
			final ResponseEntity<ContributionVoluntaryType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeVoluntary/getPayrollContributionTypeVoluntaryById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  ContributionVoluntaryType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntaryType> Create(String authToken, ContributionVoluntaryType type) {
		logger.info("Create voluntary contribution type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryType> request = new HttpEntity<ContributionVoluntaryType> (type,headers);		
			final ResponseEntity<ContributionVoluntaryType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeVoluntary/addPayrollContributionTypeVoluntary", 
					  HttpMethod.POST, 
					  request, 
					  ContributionVoluntaryType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntaryType> Update(String authToken, int id, ContributionVoluntaryType type) {
		logger.info("Update voluntary contribution type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryType> request = new HttpEntity<ContributionVoluntaryType> (type,headers);		
			final ResponseEntity<ContributionVoluntaryType> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeVoluntary/updatePayrollContributionTypeVoluntary/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  ContributionVoluntaryType.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete voluntary contribution type");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryType> request = new HttpEntity<ContributionVoluntaryType> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeVoluntary/deletePayrollContributionTypeVoluntary/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntaryType[]> GetAllByProvider(String authToken, int providerid) {
		logger.info("Get all voluntary contribution types");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryType> request = new HttpEntity<ContributionVoluntaryType> (headers);		
			final ResponseEntity<ContributionVoluntaryType[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionTypeVoluntary/getContributionTypeVoluntaryByServiceProviderId/"+providerid, 
					  HttpMethod.GET, 
					  request, 
					  ContributionVoluntaryType[].class);			
			return response;
		}
		return null;
	}

}

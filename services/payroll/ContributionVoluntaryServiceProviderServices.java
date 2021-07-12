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
import tz.go.tcra.hrms.dto.payroll.ContributionVoluntaryServiceProvider;

@Service
public class ContributionVoluntaryServiceProviderServices implements IContributionVoluntaryServiceProviderServices {
	private static final Logger logger = Logger.getLogger(ContributionVoluntaryServiceProviderServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<ContributionVoluntaryServiceProvider[]> GetAll(String authToken) {
		logger.info("Get all voluntary contribution service providers");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryServiceProvider> request = new HttpEntity<ContributionVoluntaryServiceProvider> (headers);		
			final ResponseEntity<ContributionVoluntaryServiceProvider[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntaryServiceProvider/getAllContributionVoluntaryServiceProvider", 
					  HttpMethod.GET, 
					  request, 
					  ContributionVoluntaryServiceProvider[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntaryServiceProvider[]> GetAllByEmployee(String authToken, int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntaryServiceProvider> Get(String authToken, int id) {
		logger.info("Get all voluntary contribution service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryServiceProvider> request = new HttpEntity<ContributionVoluntaryServiceProvider> (headers);		
			final ResponseEntity<ContributionVoluntaryServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntaryServiceProvider/getContributionVoluntaryServiceProviderById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  ContributionVoluntaryServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntaryServiceProvider> Create(String authToken,
			ContributionVoluntaryServiceProvider provider) {
		logger.info("Create voluntary contribution service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryServiceProvider> request = new HttpEntity<ContributionVoluntaryServiceProvider> (provider,headers);		
			final ResponseEntity<ContributionVoluntaryServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/payrollContributionVoluntaryServiceProvider/addContributionVoluntaryServiceProvider", 
					  HttpMethod.POST, 
					  request, 
					  ContributionVoluntaryServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<ContributionVoluntaryServiceProvider> Update(String authToken, int id,
			ContributionVoluntaryServiceProvider provider) {
		logger.info("Update voluntary contribution service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryServiceProvider> request = new HttpEntity<ContributionVoluntaryServiceProvider> (provider,headers);		
			final ResponseEntity<ContributionVoluntaryServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollContributionVoluntaryServiceProvider/updateContributionVoluntaryServiceProvider/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  ContributionVoluntaryServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete voluntary contribution service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<ContributionVoluntaryServiceProvider> request = new HttpEntity<ContributionVoluntaryServiceProvider> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/payrollContributionVoluntaryServiceProvider/deleteContributionVoluntaryServiceProvider/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

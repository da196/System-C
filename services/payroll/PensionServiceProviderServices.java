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
import tz.go.tcra.hrms.dto.payroll.PensionServiceProvider;

@Service
public class PensionServiceProviderServices implements IPensionServiceProviderServices {
	private static final Logger logger = Logger.getLogger(PensionServiceProviderServices.class); // log4j

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<PensionServiceProvider[]> GetAll(String authToken) {
		logger.info("Get all pension service providers");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PensionServiceProvider> request = new HttpEntity<PensionServiceProvider> (headers);		
			final ResponseEntity<PensionServiceProvider[]> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/socialSecuritySchemeServiceProvider/getAllSocialSecuritySchemeServiceProvider", 
					  HttpMethod.GET, 
					  request, 
					  PensionServiceProvider[].class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PensionServiceProvider[]> GetAllByEmployee(String authToken, int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PensionServiceProvider> Get(String authToken, int id) {
		logger.info("Get all pension service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PensionServiceProvider> request = new HttpEntity<PensionServiceProvider> (headers);		
			final ResponseEntity<PensionServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/socialSecuritySchemeServiceProvider/getSocialSecuritySchemeServiceProviderById/"+id, 
					  HttpMethod.GET, 
					  request, 
					  PensionServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PensionServiceProvider> Create(String authToken, PensionServiceProvider provider) {
		logger.info("Create pension service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PensionServiceProvider> request = new HttpEntity<PensionServiceProvider> (provider,headers);		
			final ResponseEntity<PensionServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/socialSecuritySchemeServiceProvider/addSocialSecuritySchemeServiceProvider", 
					  HttpMethod.POST, 
					  request, 
					  PensionServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<PensionServiceProvider> Update(String authToken, int id, PensionServiceProvider provider) {
		logger.info("Update pension service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PensionServiceProvider> request = new HttpEntity<PensionServiceProvider> (provider,headers);		
			final ResponseEntity<PensionServiceProvider> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/socialSecuritySchemeServiceProvider/updateSocialSecuritySchemeServiceProvider/"+id, 
					  HttpMethod.PUT, 
					  request, 
					  PensionServiceProvider.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> Delete(String authToken, int id) {
		logger.info("Delete pension service provider");
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PensionServiceProvider> request = new HttpEntity<PensionServiceProvider> (headers);		
			final ResponseEntity<Integer> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/socialSecuritySchemeServiceProvider/deleteSocialSecuritySchemeServiceProvider/"+id, 
					  HttpMethod.DELETE, 
					  request, 
					  Integer.class);			
			return response;
		}
		return null;
	}

}

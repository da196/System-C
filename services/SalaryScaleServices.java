package tz.go.tcra.hrms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.SalaryScale;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;

@Component
public class SalaryScaleServices implements ISalaryScaleServices {
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<SalaryScale[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<SalaryScale> request = new HttpEntity<SalaryScale> (headers);		
		ResponseEntity<SalaryScale[]> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/salaryScale/getAllSalaryScale", 
				  HttpMethod.GET, 
				  request, 
				  SalaryScale[].class);
		
		return response;
	}

	@Override
	public ResponseEntity<SalaryScale> Create(SalaryScale salaryScale) {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<SalaryScale> request = new HttpEntity<SalaryScale> (salaryScale, headers);		
		ResponseEntity<SalaryScale> response = restTemplate.exchange(AppConstants.BASE_URL+"/v1/salaryScale/addSalaryScale", 
				  HttpMethod.POST, 
				  request, 
				  SalaryScale.class);
		
		return response;
	}

	@Override
	public ResponseEntity<SalaryScale> getSalaryScaleByID(int id) {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<SalaryScale> request = new HttpEntity<SalaryScale> (headers);		
		ResponseEntity<SalaryScale> response = restTemplate.exchange(
				  AppConstants.BASE_URL+"/v1/salaryScale/getSalaryScale/" + id, 
				  HttpMethod.GET, 
				  request, 
				  SalaryScale.class);
		
		return response;
	}
	
	@Override
	public ResponseEntity<SalaryScale> deleteSalaryScale(int id) {
		authToken = authenticationFacade.getAuthenticationToken();
		if(!StringUtils.isEmpty(authToken)) {
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<SalaryScale> request = new HttpEntity<SalaryScale> (headers);		
			final ResponseEntity<SalaryScale> response = restTemplate.exchange(AppConstants.BASE_URL+
					"/v1/salaryScale/deleteSalaryScale/" + id, 
					  HttpMethod.DELETE, 
					  request, 
					  SalaryScale.class);			
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<SalaryScale>  updateSalaryScale(SalaryScale salaryScale, int id) {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<SalaryScale> request = new HttpEntity<SalaryScale> (salaryScale, headers);		
		ResponseEntity<SalaryScale> response = restTemplate.exchange(
				  AppConstants.BASE_URL+"/v1/salaryScale/updateSalaryScale/" + id, 
				  HttpMethod.PUT, 
				  request, 
				  SalaryScale.class);
		
		return response;
	}
}

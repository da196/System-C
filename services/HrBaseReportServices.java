package tz.go.tcra.hrms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.HrBaseReport;

@Component
public class HrBaseReportServices implements IHrBaseReportServices {
	
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<HrBaseReport> GetEmployeeDistributionByDirectorate() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<HrBaseReport> request = new HttpEntity<HrBaseReport>(headers);
			ResponseEntity<HrBaseReport> response = restTemplate.exchange(
										  AppConstants.BASE_URL+"/v1/employeeReports/getHeadCountPerDirectorate", 
										  HttpMethod.GET, 
										  request, 
										  HrBaseReport.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<HrBaseReport> GetageAveragePerDirectorate() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<HrBaseReport> request = new HttpEntity<HrBaseReport>(headers);
			ResponseEntity<HrBaseReport> response = restTemplate.exchange(
										  AppConstants.BASE_URL+"/v1/employeeReports/getAgeAveragePerDirectorate", 
										  HttpMethod.GET, 
										  request, 
										  HrBaseReport.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<HrBaseReport> AgeDistributionTopStaff() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<HrBaseReport> request = new HttpEntity<HrBaseReport>(headers);
			ResponseEntity<HrBaseReport> response = restTemplate.exchange(
										  AppConstants.BASE_URL+"/v1/employeeReports/getAgeDistributionTopStaff", 
										  HttpMethod.GET, 
										  request, 
										  HrBaseReport.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<HrBaseReport> EmployeeDistributionByLocation() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<HrBaseReport> request = new HttpEntity<HrBaseReport>(headers);
			ResponseEntity<HrBaseReport> response = restTemplate.exchange(
										  AppConstants.BASE_URL+"/v1/employeeReports/getHeadCountPerLocationAndGender", 
										  HttpMethod.GET, 
										  request, 
										  HrBaseReport.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<HrBaseReport> TopPositionsGenderDistribution() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<HrBaseReport> request = new HttpEntity<HrBaseReport>(headers);
			ResponseEntity<HrBaseReport> response = restTemplate.exchange(
										  AppConstants.BASE_URL+"/v1/employeeReports/getTopStaffCountPerGender", 
										  HttpMethod.GET, 
										  request, 
										  HrBaseReport.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<HrBaseReport> GetNumberOfStaffByAgeGroups() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<HrBaseReport> request = new HttpEntity<HrBaseReport>(headers);
			ResponseEntity<HrBaseReport> response = restTemplate.exchange(
										  AppConstants.BASE_URL+"/v1/employeeReports/getStaffDistributionByAge", 
										  HttpMethod.GET, 
										  request, 
										  HrBaseReport.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<HrBaseReport> StaffDistributionByAgeGroupsPerDirectorates() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<HrBaseReport> request = new HttpEntity<HrBaseReport>(headers);
			ResponseEntity<HrBaseReport> response = restTemplate.exchange(
										  AppConstants.BASE_URL+"/v1/employeeReports/getStaffDistributionByAgeAndDirectorate", 
										  HttpMethod.GET, 
										  request, 
										  HrBaseReport.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<HrBaseReport> EmployeesByEmployemntExitStatus() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<HrBaseReport> request = new HttpEntity<HrBaseReport>(headers);
			ResponseEntity<HrBaseReport> response = restTemplate.exchange(
										  AppConstants.BASE_URL+"/v1/employeeReports/getReasonForEmployeesExit", 
										  HttpMethod.GET, 
										  request, 
										  HrBaseReport.class);			
			return response;
		}
		return null;
	}

}

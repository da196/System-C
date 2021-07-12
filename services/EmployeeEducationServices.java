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
import tz.go.tcra.hrms.dto.CertificationCategory;
import tz.go.tcra.hrms.dto.EmployeeCertification;
import tz.go.tcra.hrms.dto.EmployeeEducation;
import tz.go.tcra.hrms.dto.EmployeeShortCourse;

@Component
public class EmployeeEducationServices implements IEmployeeEducationServices {
	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmployeeEducation> AddEducation(EmployeeEducation education) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeEducation> request = new HttpEntity<EmployeeEducation> (education, headers);		
			ResponseEntity<EmployeeEducation> response = restTemplate.exchange(
					AppConstants.BASE_URL+"/v1/employeeEducation/addEmployeeEducation", 
					  HttpMethod.POST, 
					  request, 
					  EmployeeEducation.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeShortCourse> AddShortCourse(EmployeeShortCourse shortCourse) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeShortCourse> request = new HttpEntity<EmployeeShortCourse> (shortCourse, headers);		
			ResponseEntity<EmployeeShortCourse> response = restTemplate.exchange(
					AppConstants.BASE_URL+"/v1/EmployeeShortCourses/addEmployeeShortCourse", 
					  HttpMethod.POST, 
					  request, 
					  EmployeeShortCourse.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeCertification> AddCertification(EmployeeCertification certification) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeCertification> request = new HttpEntity<EmployeeCertification> (certification, headers);		
			ResponseEntity<EmployeeCertification> response = restTemplate.exchange(
					AppConstants.BASE_URL+"/v1/employeeCertification/addEmployeeCertification", 
					  HttpMethod.POST, 
					  request, 
					  EmployeeCertification.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<CertificationCategory[]> GetCertificationCategory() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<CertificationCategory> request = new HttpEntity<CertificationCategory> (headers);		
			ResponseEntity<CertificationCategory[]> response = restTemplate.exchange(
					AppConstants.BASE_URL+"/v1/certificationCategory/getAllCertificationCategory", 
					  HttpMethod.GET, 
					  request, 
					  CertificationCategory[].class);
			
			return response;
		}
		return null;
	}

}

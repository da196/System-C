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
import tz.go.tcra.hrms.dto.EducationCourse;
import tz.go.tcra.hrms.dto.EmployeeCertification;
import tz.go.tcra.hrms.dto.EmployeeEducation;
import tz.go.tcra.hrms.dto.EmployeeShortCourse;

@Component
public class EducationCourseServices implements IEducationCourseServices{
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EducationCourse[]> GetAll() {
		if(restTemplate != null) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<EducationCourse> request = new HttpEntity<EducationCourse>(headers);
		ResponseEntity<EducationCourse[]> response = restTemplate.exchange(
													AppConstants.BASE_URL+"/v1/educationcourse/getAllEducationcourseDetailed", 
													HttpMethod.GET, 
													request, 
													EducationCourse[].class);
		return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EducationCourse> Create(EducationCourse course) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EducationCourse> request = new HttpEntity<EducationCourse>(course, headers);
			ResponseEntity<EducationCourse> response = restTemplate.exchange(
													AppConstants.BASE_URL+"/v1/educationcourse/addEducationcourse", 
													HttpMethod.POST, 
													request, 
													EducationCourse.class);
			return response;
			}
			return null;
	}

	@Override
	public ResponseEntity<EmployeeEducation[]> GetAllEducationCertificates() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeEducation> request = new HttpEntity<>(headers);
			ResponseEntity<EmployeeEducation[]> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/employeeEducation/getAllEmployeeEducation", 
												HttpMethod.GET, 
												request, 
												EmployeeEducation[].class);
			return response;
			}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeEducation[]> GetUnApprovedEducations() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeEducation> request = new HttpEntity<>(headers);
			ResponseEntity<EmployeeEducation[]> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/employeeEducation/getAllEmployeeEducationNonApproved", 
												HttpMethod.GET, 
												request, 
												EmployeeEducation[].class);
			return response;
			}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeShortCourse[]> GetAllShortCourses() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeShortCourse> request = new HttpEntity<>(headers);
			ResponseEntity<EmployeeShortCourse[]> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/EmployeeShortCourses/listEmployeeShortCourses", 
												HttpMethod.GET, 
												request, 
												EmployeeShortCourse[].class);
			return response;
			}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeShortCourse[]> GetNonApprovedShortCourses() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeShortCourse> request = new HttpEntity<>(headers);
			ResponseEntity<EmployeeShortCourse[]> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/EmployeeShortCourses/listEmployeeShortCoursesNonApproved", 
												HttpMethod.GET, 
												request, 
												EmployeeShortCourse[].class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeCertification[]> GetAllCertification() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeCertification> request = new HttpEntity<>(headers);
			ResponseEntity<EmployeeCertification[]> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/employeeCertification/getAllEmployeeCertification", 
												HttpMethod.GET, 
												request, 
												EmployeeCertification[].class);
			return response;
			}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeCertification[]> GetNonApprovedCertification() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeCertification> request = new HttpEntity<>(headers);
			ResponseEntity<EmployeeCertification[]> response = restTemplate.exchange(
												AppConstants.BASE_URL+"/v1/employeeCertification/getAllEmployeeCertificationNonApproved", 
												HttpMethod.GET, 
												request, 
												EmployeeCertification[].class);
			return response;
		}
		return null;
	}

}

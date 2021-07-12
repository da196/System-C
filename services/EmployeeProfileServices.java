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
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.EmployeeAddress;
import tz.go.tcra.hrms.dto.EmployeeCertification;
import tz.go.tcra.hrms.dto.EmployeeEducation;
import tz.go.tcra.hrms.dto.EmployeeProfile;
import tz.go.tcra.hrms.dto.EmployeeShortCourse;

@Component
public class EmployeeProfileServices implements IEmployeeProfileServices {
	//Access Token Configurations.	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	
	private String authToken;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmployeeProfile> GetProfileDetailsById(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeProfile> request = new HttpEntity<EmployeeProfile>(headers);
			ResponseEntity<EmployeeProfile> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/hrmsEmployee/getEmployeeDetailsById/"+id, 
											HttpMethod.GET, 
											request, 
											EmployeeProfile.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeAddress> createAddress(EmployeeAddress address) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeAddress> request = new HttpEntity<EmployeeAddress>(address, headers);
			ResponseEntity<EmployeeAddress> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/employeeAddress/addEmployeeAddress", 
											HttpMethod.POST, 
											request, 
											EmployeeAddress.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeAddress> editAddress(EmployeeAddress address) {
		if(restTemplate != null) {
			int aid = address.getAdressid();
			int cid = address.getContactid();
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeAddress> request = new HttpEntity<EmployeeAddress>(address, headers);
			ResponseEntity<EmployeeAddress> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/employeeAddress/updateEmployeeAddress/"+aid+"/"+cid, 
											HttpMethod.PUT, 
											request, 
											EmployeeAddress.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Employee> updateEmployeeInfo(Employee employeeInfo) {
		if(restTemplate != null) {
			int id = employeeInfo.getId();
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Employee> request = new HttpEntity<Employee>(employeeInfo, headers);
			ResponseEntity<Employee> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/hrmsEmployee/updateEmployee/"+id, 
									HttpMethod.PUT, 
									request, 
									Employee.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> ApproveEducation(int status,EmployeeEducation employeeEducation) {
		if(restTemplate != null) {
			//int id = authenticationFacade.getUser().getEmpId();
			int id = employeeEducation.getId();
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeEducation> request = new HttpEntity<EmployeeEducation>(employeeEducation, headers);
			ResponseEntity<Integer> response = restTemplate.exchange(
											AppConstants.BASE_URL+"/v1/employeeEducation/approveOrejectEmployeeEducation/"+id+"/"+status, 
											HttpMethod.PUT, 
											request, 
											Integer.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> RejectEducation(int status, EmployeeEducation employeeEducation) {
		if(restTemplate != null) {
			int id = employeeEducation.getId();
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeEducation> request = new HttpEntity<EmployeeEducation>(employeeEducation, headers);
			ResponseEntity<Integer> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/employeeEducation/approveOrejectEmployeeEducation/"+id+"/"+status, 
									HttpMethod.PUT, 
									request, 
									Integer.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> ApproveShortCourse(int status, EmployeeShortCourse shortCourse) {
		if(restTemplate != null) {
			int id = shortCourse.getId();
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeShortCourse> request = new HttpEntity<EmployeeShortCourse>(shortCourse, headers);
			ResponseEntity<Integer> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/EmployeeShortCourses/approveOrRejectEmployeeShortCourses/"+id+"/"+status, 
									HttpMethod.PUT, 
									request, 
									Integer.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> RejectShortCourse(int status, EmployeeShortCourse shortCourse) {
		if(restTemplate != null) {
			int id = shortCourse.getId();
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeShortCourse> request = new HttpEntity<EmployeeShortCourse>(shortCourse, headers);
			ResponseEntity<Integer> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/EmployeeShortCourses/approveOrRejectEmployeeShortCourses/"+id+"/"+status, 
									HttpMethod.PUT, 
									request, 
									Integer.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> ApproveCertificate(int status, EmployeeCertification certificate) {
		if(restTemplate != null) {
			int id = certificate.getId();
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeCertification> request = new HttpEntity<EmployeeCertification>(certificate, headers);
			ResponseEntity<Integer> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/employeeCertification/approveOrRejectEmployeeCertification/"+id+"/"+status, 
									HttpMethod.PUT, 
									request, 
									Integer.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Integer> RejectCertificate(int status, EmployeeCertification certificate) {
		if(restTemplate != null) {
			int id = certificate.getId();
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeCertification> request = new HttpEntity<EmployeeCertification>(certificate, headers);
			ResponseEntity<Integer> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/employeeCertification/approveOrRejectEmployeeCertification/"+id+"/"+status, 
									HttpMethod.PUT, 
									request, 
									Integer.class);
			return response;
		}
		return null;
	}


}

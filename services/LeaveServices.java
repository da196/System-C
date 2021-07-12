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
import tz.go.tcra.hrms.dto.Leave;
import tz.go.tcra.hrms.dto.leave.EmployeeLeaveBalance;

@Component
public class LeaveServices implements ILeaveServices {
	
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<Leave[]> GetAllLeavesApplications() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Leave> request = new HttpEntity<Leave>(headers);
			ResponseEntity<Leave[]> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/leaveApplications/getAllLeaveApplications", 
									HttpMethod.GET, 
									request, 
									Leave[].class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Leave> RequestLeave(Leave leaveRequest) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Leave> request = new HttpEntity<Leave>(leaveRequest, headers);
			ResponseEntity<Leave> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/leaveApplications/applyLeave", 
									HttpMethod.POST, 
									request, 
									Leave.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Leave[]> GetLeaveApplicationsByEmployeeId(int id) {
		if(restTemplate != null){
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Leave> request = new HttpEntity<Leave>(headers);
			ResponseEntity<Leave[]> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/leaveApplications/getAllLeaveApplicationsByEmpId/"+id, 
									HttpMethod.GET, 
									request, 
									Leave[].class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Void> ApproveLeaveRequest(int leaveid, int approverid, int status) {
		if(restTemplate != null){
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Leave> request = new HttpEntity<Leave>(null,headers);
			ResponseEntity<Void> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/leaveApplications/approveLeave/"+leaveid+"/"+approverid+"/"+status+"/null", 
									HttpMethod.PUT, 
									request, 
									Void.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeLeaveBalance> LeaveBalancesByEmployeeId(int empid) {
		if(restTemplate != null){
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeLeaveBalance> request = new HttpEntity<EmployeeLeaveBalance>(headers);
			ResponseEntity<EmployeeLeaveBalance> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/HrmsLeaveBalance/getLeaveBalanceByEmpId/"+empid, 
									HttpMethod.GET, 
									request, 
									EmployeeLeaveBalance.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Leave> CheckIfOnLeave(int empid) {
		if(restTemplate != null){
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<?> request = new HttpEntity<>(headers);
			ResponseEntity<Leave> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/leaveApplications/verifyLeaverequestEligibility/"+empid, 
									HttpMethod.GET, 
									request, 
									Leave.class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Leave[]> GetPendingLeaves() {
		if(restTemplate != null){
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Leave> request = new HttpEntity<Leave>(headers);
			ResponseEntity<Leave[]> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/leaveApplications/getAllLeaveApplicationsNotApproved", 
									HttpMethod.GET, 
									request, 
									Leave[].class);
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Leave[]> GetRejectedLeaves() {
		if(restTemplate != null){
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Leave> request = new HttpEntity<Leave>(headers);
			ResponseEntity<Leave[]> response = restTemplate.exchange(
									AppConstants.BASE_URL+"/v1/leaveApplications/getAllLeaveApplicationsRejected", 
									HttpMethod.GET, 
									request, 
									Leave[].class);
			return response;
		}
		return null;
	}

}

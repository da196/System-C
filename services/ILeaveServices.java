package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.Leave;
import tz.go.tcra.hrms.dto.leave.EmployeeLeaveBalance;

@Component
public interface ILeaveServices {

	//Get all leave applications
	ResponseEntity<Leave[]> GetAllLeavesApplications();
	
	//Back Office leave request
	ResponseEntity<Leave> RequestLeave(Leave leaveRequest);
	
	//Get Leaves by employee ID
	ResponseEntity<Leave[]> GetLeaveApplicationsByEmployeeId(int id);
	
	//Approve Leave request
	ResponseEntity<Void> ApproveLeaveRequest(int leaveid, int approverid, int status);
	
	//Get Leave balances by employee id
	ResponseEntity<EmployeeLeaveBalance> LeaveBalancesByEmployeeId(int empid);
	
	//Get if employee on annual leave or not
	ResponseEntity<Leave> CheckIfOnLeave(int empid);
	
	//Get pending leaves
	ResponseEntity<Leave[]> GetPendingLeaves();
	
	//Get pending leaves
	ResponseEntity<Leave[]> GetRejectedLeaves();
	
}

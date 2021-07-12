package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.EmployeeAddress;
import tz.go.tcra.hrms.dto.EmployeeCertification;
import tz.go.tcra.hrms.dto.EmployeeEducation;
import tz.go.tcra.hrms.dto.EmployeeProfile;
import tz.go.tcra.hrms.dto.EmployeeShortCourse;

@Component
public interface IEmployeeProfileServices {

	//Get employee information/Details/Profile
	ResponseEntity<EmployeeProfile> GetProfileDetailsById(int id);
	
	//Submit employee contact details
	ResponseEntity<EmployeeAddress> createAddress(EmployeeAddress address);
	
	//Edit employee contact details
	ResponseEntity<EmployeeAddress> editAddress(EmployeeAddress address);
	
	//Update/Edit employee information
	ResponseEntity<Employee> updateEmployeeInfo(Employee employeeInfo);
	
	//Approve education certification
	ResponseEntity<Integer> ApproveEducation(int status,EmployeeEducation employeeEducation);
	
	//Reject education certification
	ResponseEntity<Integer> RejectEducation(int status,EmployeeEducation employeeEducation);
	
	//Approve short course
	ResponseEntity<Integer> ApproveShortCourse(int status,EmployeeShortCourse shortCourse);
	
	//Reject short course
	ResponseEntity<Integer> RejectShortCourse(int status,EmployeeShortCourse shortCourse);
	
	//Approve Certificate
	ResponseEntity<Integer> ApproveCertificate(int status,EmployeeCertification certificate);
	
	//Reject Certificate
	ResponseEntity<Integer> RejectCertificate(int status,EmployeeCertification certificate);
}

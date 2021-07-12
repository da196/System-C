package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.CertificationCategory;
import tz.go.tcra.hrms.dto.EmployeeCertification;
import tz.go.tcra.hrms.dto.EmployeeEducation;
import tz.go.tcra.hrms.dto.EmployeeShortCourse;

@Component
public interface IEmployeeEducationServices {

	//Get All Employees Educations
	//ResponseEntity<EmployeeEducation> GetAll();
	
	//Add Employee education
	ResponseEntity<EmployeeEducation> AddEducation(EmployeeEducation education);
	
	//Add short courses
	ResponseEntity<EmployeeShortCourse> AddShortCourse(EmployeeShortCourse shortCourse);
	
	//Add Certification
	ResponseEntity<EmployeeCertification> AddCertification(EmployeeCertification certification);
	
	//Get certification category
	ResponseEntity<CertificationCategory[]> GetCertificationCategory();
}

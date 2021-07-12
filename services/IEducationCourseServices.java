package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.EducationCourse;
import tz.go.tcra.hrms.dto.EmployeeCertification;
import tz.go.tcra.hrms.dto.EmployeeEducation;
import tz.go.tcra.hrms.dto.EmployeeShortCourse;

@Component
public interface IEducationCourseServices {
	//Get Courses
	ResponseEntity<EducationCourse[]> GetAll();
	
	//New Education course
	ResponseEntity<EducationCourse> Create(EducationCourse course);
	
	//Get all education certificates
	ResponseEntity<EmployeeEducation[]> GetAllEducationCertificates();
	
	//Get non approved employee educations
	ResponseEntity<EmployeeEducation[]> GetUnApprovedEducations();
	
	//Get all short courses
	ResponseEntity<EmployeeShortCourse[]> GetAllShortCourses();
	
	//Get non approved short courses
	ResponseEntity<EmployeeShortCourse[]> GetNonApprovedShortCourses();
	
	//Get all certifications
	ResponseEntity<EmployeeCertification[]> GetAllCertification();
	
	//Get non approved certifications
	ResponseEntity<EmployeeCertification[]> GetNonApprovedCertification();

}

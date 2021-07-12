package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.Employee;

@Component
public interface IEmployeeServices {
	//Get all employees
	ResponseEntity<Employee[]> GetAll();
	
	//Get all employees for data-table
	ResponseEntity<Employee[]> GetAllEmployeesForDatatable();
	
	//Get all employees less details
	ResponseEntity<Employee[]> GetAllEmployeesLessDetails();
	
	//Create new employee
	ResponseEntity<Employee> Create(Employee employee);
	
	//Get employee details by id
	ResponseEntity<Employee> GetEmployeeDetailsById(int id);

}

package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.Currency;
import tz.go.tcra.hrms.dto.EmploymentCategory;
import tz.go.tcra.hrms.dto.SalaryStructure;

@Component
public interface ISalaryStructureServices {
	
	//Get Salary structures
	ResponseEntity<SalaryStructure[]> GetAll();
	
	//Add salary structure
	ResponseEntity<SalaryStructure> Create(SalaryStructure salaryStructure);

	//Get employee salary structures more details
	ResponseEntity<SalaryStructure[]> GetemployeeSalaryStructuresMoreDetails();

	//get salary structure by ID
	ResponseEntity<SalaryStructure> GetSalaryStructureById(int id);
	
	//Delete salary structure by ID
	ResponseEntity<SalaryStructure> DeleteSalaryStructureById(int id);
	ResponseEntity<SalaryStructure> EditSalaryStructure(SalaryStructure salaryStructure, int id);
	
	
	// CRUD Employee Salary Structure
	ResponseEntity<SalaryStructure> AddEmployeeSalaryStructure(SalaryStructure employeeSalaryStructure);
	ResponseEntity<SalaryStructure> EditEmployeeSalaryStructure(SalaryStructure employeeSalaryStructure, int id);
	ResponseEntity<SalaryStructure> GetSalaryStructureByEmployeeId(int id);
	ResponseEntity<SalaryStructure> DeleteEmployeeSalaryStructure(int id);
	ResponseEntity<SalaryStructure[]> GetEmployeesSalaryStructure();
	ResponseEntity<Currency[]> GetAllCurrency();
	
	ResponseEntity<SalaryStructure> GetEmployeeSalaryStructureById(int id);
	
	ResponseEntity<EmploymentCategory[]> GetEmployementCategory();
	
}

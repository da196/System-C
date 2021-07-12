package tz.go.tcra.hrms.dto;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryStructure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int basicSalary;
	private int basicsalaryMax;
	private int basicsalaryMin;
	private int id;
	private int notchId;
	private int scaleId;
	private String description;
	private String scalename;
	private String notch;
	
	//Salary Structure
	private int approvedbyId;
	private int assignedbyId;
	private int currencyId;
	private String descriptionApproved;
	private String descriptionAssigned;
	private int employeeid;
	private int salarystructureId;	
	private String salaryScale;
	private String employeefullname;
	private String currency;

}

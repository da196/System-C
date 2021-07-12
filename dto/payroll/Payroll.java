package tz.go.tcra.hrms.dto.payroll;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payroll implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PayrollEmployee> payrollResponselist;
	// total
	private Double totalamountdeduction;
	private Double totalamountdeductionloan;
	private Double totalamountdeductionloaneducationheslb;
	private Double totalamountdeductionloaneducationother;
	private Double totalamountdeductionloanother;
	private Double totalamountdeductionloansaccos;
	private Double totalamountdeductionloansalaryadvance;
	private Double totalamountdeductionmandatory;
	private Double totalamountdeductionmandatoryEmployer;
	private Double totalamountdeductionmandatoryInsurance;
	private Double totalamountdeductionmandatorypension;
	private Double totalamountdeductionvoluntary;
	private Double totalamountsalaryallowance;
	private Double totalamountsalaryallowancehousing;
	private Double totalamountsalaryallowancetransport;
	private Double totalamountsalarybasic;
	private Double totalamountsalarygross;
	private Double totalamountsalarynet;
	private Double totalamountsalaryservantAndHospitality;
	private Double totalamounttax;
	private Double totalamounttaxable;
	private Double totalamounttaxother;
	private Double totalamounttaxpaye;
	private Double amountincomeother = 0.0; // later
	private Double totalamountTcraSaccos;
	private Double totalamountPostanaSimuSaccos;
	private Double totalamountotherincome;
	// transient
	private String datepay;
}

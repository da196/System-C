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
public class PayrollEmployee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int year;
	private int month;
	private int day;
	private String designation;
	private int designationid;
	private String dutystationcity;
	private int dutystationcityid;
	private int employeeid;
	private String fullName;
	private String office;
	private int officeid;
	private String officetype;
	private int officetypeid;
	private String payrolltype;
	private int payrolltypeid;
	// amount
	private Double amountdeduction;
	private Double amountdeductionloan;
	private Double amountdeductionmandatory;
	private Double amountdeductionmandatoryEmployer;
	private Double amountdeductionvoluntary;
	private Double amountsalaryallowance;
	private Double amountsalarybasic;
	private Double amountsalarygross;
	private Double amountsalarynet;
	private Double amounttax;
	private Double amounttaxable;
	private Double amounttaxother;
	private Double amounttaxpaye;	
	private Double amountTcraSaccos;
	private Double amountPostanaSimuSaccos;
	private Double amountotherincome;
	private String currency;
	private int currencyid;
	private String datepay;
	// misc
	private int active;
	private int approved;
	// allowance
	private List<PayrollEmployeeAllowance> allowancelist;
	// loan
	private List<PayrollEmployeeDeductionLoan> deductionLoanlist;
	// - mandatory
	// insurance
	private List<PayrollEmployeeDeductionInsurance> deductionMandatoryInsurancelist;
	// pension
	private List<PayrollEmployeeDeductionPension> deductionMandatoryPensionlist;
	// - voluntary
	private List<PayrollEmployeeDeductionVoluntary> deductionVoluntarylist;
}

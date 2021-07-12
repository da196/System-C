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
public class Journal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// date
	private String payrolldate;
	private String payday;
	private int year;
	private int month;
	// allowance
    private List<PayrollAllowance> allowancelist;
    // loan
 	private List<PayrollDeductionLoan> deductionloanlist;
 	// - voluntary
 	private List<PayrollDeductionVoluntary> deductionvoluntarylist;
 	// pay
	private double basicpay;
	private double credittotal;
	private double debttotal;
	private double miscelaneouspay;
	private double netpay;
	private double otherincome;
	private double psssfemployerpay;
	private double psssfpay;
	private double zssfemployerpay;
	private double zssfpay;
	private double payepay;
}

package tz.go.tcra.hrms.dto.payroll;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double amountdebt = 0;
	private double amountloanbalance;
	private double amountprincipal = 0;
	private double amountoutstanding = 0;;
	private double amountpenalty = 0;;
	private double amountpaid = 0;;
	private double interestrate = 0;
	private double duration;
	private String dateissued;
	private String daterepaymentstart;
	private String daterepaymentend;
	private int status = 0;;
	// fk
	private int loantypeid;
	private String loantype;
	private int loanfrequencyid;
	private String loanfrequency;
	private String lenderid;
	private String lender;
	private String currencyid;
	private String currency;
	private int employeeid;
	private String employeeFullname;
	// common
	private int approved;
	private int active;
}

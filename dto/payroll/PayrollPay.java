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
public class PayrollPay implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double basicSalaryTotal;
	private double employeeTotal;
	private double grossSalaryTotal;
	private double insuranceEmployerTotal;
	private double loanTotal;
	private double netSalaryTotal;
	private double pensionEmployeeTotal;
	private double pensionEmployerTotal;
	private double taxTotal;
	// date
	private int id;
	private int year;
	private int month;
}

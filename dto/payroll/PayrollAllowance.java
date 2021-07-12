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
public class PayrollAllowance implements Serializable{
	private static final long serialVersionUID = 1L;
	private int allowancetypeCode;
	private String allowancetype;
	private String amountsalaryallowance;
}

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
public class LoanType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int code;
	private String name;
	private String abbreviation;
	private String description;
	private int isinternalsource;
	private int active;
	private int approved;
	private int lenderid;
	private String lender;
	private double amountmax;
	private double amountmin;
	private double durationmax;
	private double interestrate;
	private int restrictioncode;
	
	public LoanType(int id,String name) {
		setId(id);
		setName(name);
	}
}

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
public class Allowance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int code;
	private String name;
	private String description;
	private String active;
	private String abbreviation;
	private int allowancetypeid;
	private int amount;
	private int designationid;
	private int employmentcategoryid;
	private int salarystructureid;

	
	private String allowancetypename;
	private String currency;
	private int currencyid;
	private String designationname;
	private String employmentcategoryname;
	private String salarystructurename;



}

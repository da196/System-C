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
public class EmployeeBankAccount implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String accountname;
	private String accountnumber;
	private int bankid;
	private String bankbranchname;
	private int employeeid;
	private String bankName;
	private int createdbyuserid;
	private int priority;
	private int amount;
	private int bankbranchid;

}

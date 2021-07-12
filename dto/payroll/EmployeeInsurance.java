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
public class EmployeeInsurance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int active;
	private int approved;
	private String datend;
	private String datestart;
    private int employeeid;
    private String fullName;
    private int id;
    private String insurancetype;
    private int insurancetypeid;
    private String insurancenumber;
    private String insuranceprovider;
    private int insuranceproviderid;
}

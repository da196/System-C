package tz.go.tcra.hrms.dto.payroll;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContributionVoluntary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double amount;
	private double rate;
	private int currencyid;
	private String currency;
	private int employeeid;
	private String fullName;
	private int serviceproviderid;
	private String serviceprovider;
	private int contributiontypeid;
	private String contributiontype;
	private int contributionmode;
	private int isformularcomputed;
	private String description;
	private String joiningdate;
	private String membershipnumber;
	private int locked;
	private int active;
	private int approved;
}

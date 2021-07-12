package tz.go.tcra.hrms.dto.payroll;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContributionMandatoryInsurance {
	private int id;
	private double amountemployee;
	private double amountemployer;
	private double amount;
	private double rateemployee;
	private double rateemployer;
	private String description;
	private int isformularcomputed;
	// fk
	private int currencyid;
	private int contributiontypeid;
	private int insurancetypeid;
	private int serviceproviderid;
	// common
	private int approved;
	private int active;
	private Date date_created;
	private Date date_updated;
	// transient
	private String currency;
	private String contributiontype;
	private String insurancetype;
	private String serviceprovider;
}

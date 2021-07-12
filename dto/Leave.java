package tz.go.tcra.hrms.dto;

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
public class Leave implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int acting;
	private String approvalcomment;
	private int approved;
	private int approvedby;
	private String comment;
	private String contactaddress;
	private int employeeid;
	private String enddate;
	private int id;
	private int leaveallowance;
	private int leaveallowanceapplicable;
	private int leavetypeid;
	private int month;
	private int numberofdays;
	private int requestedby;
	private String startdate;
	private int year;
	private String firstname;
	private String middlename;
	private String lastname;
	private String leavetypename;
	private String actingfullname;
	private int approverid;
	private int status;
	private int leaveid;
	private int daystaken;
	private int daysremaining;
	private int daystotal;
	private int onleave;
	private String approverfullname;
	
	private List<LeaveBalance> leaveBalancelist;

}

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
public class EmployeeEducation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String approvalComment;
	private int attchmentid;
	private String attachmentname;
	private int attachmenttypeid;
	private String attachmentdescription;
	private String attachmentUri;
	private int countryid;
	private String countryname;
	private int courseid;
	private String courseName;
	private int employeeid;
	private String employeename;
	private String endYear;
	private int institutionid;
	private String instituteName;
	private int levelid;
	private String levelName;
	private String startYear;
	private int approved;
	private int id;
	private String uri;
	private String datestart;
	private String datend;
	
	//Approve Reject
	private int approverEmployeeid;
	private String comment;
	
}

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
public class EmployeeShortCourse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String approvalComment;
	private int attchmentid;
	private String attachmentname;
	private int attachmenttypeid;
	private String attachmenttypename;
	private int countryid;
	private String countryname;
	private String coursename;
	private int employeeid;
	private String employeename;
	private String datend;
	private String institution;
	private String datestart;
	private String attachmentdescription;
	private String description;
	private int id;
	private int approverEmployeeid;
	private int approved;
	private String comment;
	private String uri;

}

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
public class EmployeeCertification implements Serializable {
    	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String approvalComment;
	private int approved;
	private int active;
	private int attachmentid;
	private int attachmenttypeid;
	private int certificationcategoryid;
	private int countryid;
	private int employeeid;
	private int id;
	private String attachmentname;
	private String attachmenttypename;
	private String certificationcategoryname;
	private String countryname;
	private String datend;
	private String datestart;
	private String description;
	private String employeename;
	private String expire;
	private String institution;
	private String uri;
	private int approverEmployeeid;
	private String comment;
}

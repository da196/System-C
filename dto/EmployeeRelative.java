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
public class EmployeeRelative implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String address;
	private String addresspermanent;
	private String approvalComment;
	private String attachmentdescription;
	private String attachmentname;
	private String attachmenttypename;
	private String countryofbirth;
	private String countryofresidence;
	private String employeename;
	private String firstname;
	private String fullname;
	private String lastname;
	private String middlename;
	private String mobileNo;
	private String nationality;
	private String phoneprimary;
	private String phonesecondary;
	private String relativecategoryname;
	private String uri;
	private int attachmentid;
	private int attachmenttypeid;
	private int countryofbirthid;
	private int countryofresidenceid;
	private int employeeid;
	private int id;
	private int nationalityid;
	private int relativecategoryid;
	private String dob;

}

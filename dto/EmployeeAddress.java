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
public class EmployeeAddress implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String addressline1;
	private String addressline2;
	private String addresstypename;
	private String adresscity;
	private String adressdescription;
	private String approvalComment;
	private String contactdescriptionend;
	private String contactdescriptionstart;
	private String contactemailaddress;
	private String contactphoneprimary;
	private String contactphonesecondary;
	private String contacttypename;
	private String employeename;
	private String postalcode;
	private int addresstypeid;
	private int adresscityid;
	private int adressid;
	private int contactid;
	private int contacttypeid;
	private int empadressid;
	private int empcontactid;
	private int employeeid;

}

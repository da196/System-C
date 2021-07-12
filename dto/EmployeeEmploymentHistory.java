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
public class EmployeeEmploymentHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String approvalComment;
	private String fromDate;
	private int id;
	private String instituteName;
	private String jobTitle;
	private String toDate;

}

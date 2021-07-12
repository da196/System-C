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
public class EmployeeProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<EmployeeEducation> employeeEducationResponseV2list;
	private List<EmployeeShortCourse> hrmsEmployeeShortCoursesRlist;
	private List<EmployeeRelative> employeeRelativeResponselist;
	private List<EmployeeAddress> hrmsEmployeeAddressContactResponselist;
	private List<EmployeeEmploymentHistory> hrmsEmployeeEmploymentHistoryResponseByEmpIdlist;
	private Employee employeeResponse;
	private List<EmployeeCertification> hrmsEmployeeCertificationResponselist;
	
	

}

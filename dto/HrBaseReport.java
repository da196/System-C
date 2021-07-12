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
public class HrBaseReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private int totalnumberg;
	private int totaldirectors;
	private int totalheads;
	private int totaltopstaff;
	private List<ReportsPreDefinitions> employeeHeadCountDistributionperDirectoratelist;
	private List<ReportsPreDefinitions> employeeAgeAverageperDirectoratelist;
	private List<ReportsPreDefinitions> ageGroupdetails;
	
	//getHeadCountPerLocationAndGender
	private int totalfemale;
	private int totalmale;
	private int totalnumber;
	private List<ReportsPreDefinitions> employeeHeadCountDistributionlist;
	
	//getTopStaffCountPerGender
	private int femaledirectors;
	private int femaleheads;
	private int maledirectors;
	private int maleheads;
	
	//Number of staff by age Group
	private List<ReportsPreDefinitions> employeeStaffDistributionByAgelist;
	
	//Number of staff (Staff Distribution) by age groups in directorates
	private String agegroup;	
	private List<ReportsPreDefinitions> employeeStaffDistributionByAgeAndDirectoratelist;
	
	//Employees by employment exit statuses
	private int inactiveNumber;
	private int individualTrainingNumber;
	private int corporateTrainingNumber;
	private int retirementNumber;
	private int resignationNumber;
	private int terminationNumber;
	private int transferNumber;
	private int deathNumber;
	
	
}

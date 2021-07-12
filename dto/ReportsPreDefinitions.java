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
public class ReportsPreDefinitions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	//Employee Head Count Distribution Per Directorate
	private String departmentname;
	private int id;
	private int totalnumber;
	private int departmentpercentage;
	
	//Age Averages by Directorate
	private int ageaverage;
	private String directorate;
	
	//Age Distribution Top Staff
	private String agegroup;
	private int directors;
	private int heads;
	private int totalinAgeGroup;
	
	//getHeadCountPerLocationAndGender
	private int femalenumber;
	private String location;
	private int malenumber;
	private int totallocationnumber;
	
	//Number of staff (Staff Distribution) by age groups in directorates
	private int agegroupDepartmentPercentage;
	private int totalDepartmentNumber;
	private String departmentName;

}

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
public class EmployeeStatistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int totalnumber;
	private String totalpercentage;
	private int malenumber;
	private int femalenumber;
	private String globalpercentage;
	private int unitid;
	private String unitname;
	private String unitshortname;
	
}

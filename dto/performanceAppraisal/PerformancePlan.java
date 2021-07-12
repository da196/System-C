package tz.go.tcra.hrms.dto.performanceAppraisal;

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
public class PerformancePlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String abbreviation;
	private int active;
	private int approved;
	private String description;
	private int id;
	private String name;
	private int yearending;
	private int yearstarting;
	private String financialyear;
	private Double yearduration;
	
	private List<PerformanceGoal> performanceGoallist;

}

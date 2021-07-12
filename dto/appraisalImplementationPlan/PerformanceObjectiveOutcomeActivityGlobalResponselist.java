package tz.go.tcra.hrms.dto.appraisalImplementationPlan;

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
public class PerformanceObjectiveOutcomeActivityGlobalResponselist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int active;
	private int approved;
	private String description;
	private int outcomeid;
	private List<PerformanceObjectiveOutcomeActivityOutputGlobalResponselist> performanceObjectiveOutcomeActivityOutputGlobalResponselist;

}

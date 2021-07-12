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
public class ImplementationPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String financialYear;
	private String goalName;
	private int goalid;
	private int objectiveId;
	private String objectiveName;
	private List<PerformanceObjectiveOutcomeGlobalResponselist> performanceObjectiveOutcomeGlobalResponselist;

}

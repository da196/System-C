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
public class PerformanceObjectiveOutcomeActivityOutputTargetGlobalResponselist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String description;
	private int active;
	private int approved;
	private String keyperformanceindicator;
	private int outputid;
	private int target;
	private String timeline;
	private List<PerformanceObjectiveOutcomeActivityOutputResponsibleResponselist> performanceObjectiveOutcomeActivityOutputResponsibleResponselist;

}

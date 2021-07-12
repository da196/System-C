package tz.go.tcra.hrms.dto.appraisalImplementationPlan;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceObjectiveOutcomeActivityOutputResponsibleResponselist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int active;
	private int approved;
	private int targetid;
	private String unitName;
	private int unitid;
	private PerformanceObjectiveOutcomeActivityOutputTarget performanceObjectiveOutcomeActivityOutputTarget;

}

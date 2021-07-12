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
public class PerformanceObjectiveSummary implements Serializable   {

	private static final long serialVersionUID = 1L;
	
	private int objectiveid;
	private PerformanceObjective performanceObjective;
	private List<PerformanceTargetSummary> targetlist;
}

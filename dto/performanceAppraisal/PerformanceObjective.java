package tz.go.tcra.hrms.dto.performanceAppraisal;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceObjective implements Serializable   {

	private static final long serialVersionUID = 1L;
	
	private int active;
	private int approved;
	private String description;
	private int id;
	//private String name;
	private int goalid;
}

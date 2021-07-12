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
public class ImplementationOutcome implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String description;
	private int active;
	private int approved;
	private int objectiveid;

}

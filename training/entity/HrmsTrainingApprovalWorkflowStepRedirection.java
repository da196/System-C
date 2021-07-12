package tz.go.tcra.hrms.training.entity;

import java.util.Date;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor

public class HrmsTrainingApprovalWorkflowStepRedirection {

	private int id;

	private String description;

	private Date dateredirected;

	private int stepid;

	private int fromdesignationid;

	private int todesignationid;

	private int fromuserid;

	private int touserid;

	private int riderectionreasonid;

	private int approved;

	private int active;


}

package tz.go.tcra.hrms.dto.leave;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrmsLeaveApprovalWorkflowStep {

	private int id;

	private int workflowid;

	private int stepnumber;

	private String description;

	private int approverdesignationid;

	private int approverdesignationnextid;

	private int approverdesignationprevid;

	private int approved;

	private int active;
}

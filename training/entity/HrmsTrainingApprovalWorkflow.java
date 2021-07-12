package tz.go.tcra.hrms.training.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor


public class HrmsTrainingApprovalWorkflow {

	private int id;

	private int code;

	private String name;

	private String description;

	private int supervisordesignationid;

	private int approved;

	private int active;


}

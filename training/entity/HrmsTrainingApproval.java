package tz.go.tcra.hrms.training.entity;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor


public class HrmsTrainingApproval {

	private int id;

	private String description;

	private String approvedby;

	private int stepnumber;

	private int stepnumbernext;

	private int trainingid;

	private int stepid;

	private int approverdesignationid;

	private int workflowid;

	private int approveruserid;

	private int approved;

	private int active;

	private int status;

}

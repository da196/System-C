package tz.go.tcra.hrms.dto.leave;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.go.tcra.hrms.dto.LeaveBalance;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLeaveBalance implements Serializable {
	private static final long serialVersionUID = 1L;
	private int employeeid;
	private String firstname;
	private String middlename;
	private String lastname;
	private List<LeaveBalance> leaveBalancelist;
}

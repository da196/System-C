package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.LeaveSettings;

@Component
public interface ILeaveSettingsServices {

	//Get Leave Types
	ResponseEntity<LeaveSettings[]> GetLeaveTypes();
	
	//Add new leave type
	ResponseEntity<LeaveSettings> AddLeaveType(LeaveSettings leave);
	
	//Delete leave type
	ResponseEntity<LeaveSettings> DeleteLeaveType(int id);
}

package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.ContributionVoluntary;

@Component
public interface IContributionVoluntaryServices {
	ResponseEntity<ContributionVoluntary[]> GetAll(String authToken);
	ResponseEntity<ContributionVoluntary[]> GetAllByEmployee(String authToken,int employeeId);
	ResponseEntity<ContributionVoluntary> Get(String authToken,int id);
	ResponseEntity<ContributionVoluntary> Create(String authToken,ContributionVoluntary contribution);
	ResponseEntity<ContributionVoluntary> Update(String authToken,int id,ContributionVoluntary contribution);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

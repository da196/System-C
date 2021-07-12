package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.ContributionVoluntaryServiceProvider;

@Component
public interface IContributionVoluntaryServiceProviderServices {
	ResponseEntity<ContributionVoluntaryServiceProvider[]> GetAll(String authToken);
	ResponseEntity<ContributionVoluntaryServiceProvider[]> GetAllByEmployee(String authToken,int employeeId);
	ResponseEntity<ContributionVoluntaryServiceProvider> Get(String authToken,int id);
	ResponseEntity<ContributionVoluntaryServiceProvider> Create(String authToken,ContributionVoluntaryServiceProvider provider);
	ResponseEntity<ContributionVoluntaryServiceProvider> Update(String authToken,int id,ContributionVoluntaryServiceProvider provider);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryPension;

@Component
public interface IContributionMandatoryPensionServices {
	ResponseEntity<ContributionMandatoryPension[]> GetAll(String authToken);
	ResponseEntity<ContributionMandatoryPension> Get(String authToken,int id);
	ResponseEntity<ContributionMandatoryPension> GetByAmount(String authToken,double amount);
	ResponseEntity<ContributionMandatoryPension> Create(String authToken,ContributionMandatoryPension contribution);
	ResponseEntity<ContributionMandatoryPension> Update(String authToken,int id,ContributionMandatoryPension contribution);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

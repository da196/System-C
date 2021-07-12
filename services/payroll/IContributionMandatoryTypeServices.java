package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryType;

@Component
public interface IContributionMandatoryTypeServices {
	ResponseEntity<ContributionMandatoryType[]> GetAll(String authToken);
	ResponseEntity<ContributionMandatoryType> Get(String authToken,int id);
	ResponseEntity<ContributionMandatoryType> Create(String authToken,ContributionMandatoryType type);
	ResponseEntity<ContributionMandatoryType> Update(String authToken,int id,ContributionMandatoryType type);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

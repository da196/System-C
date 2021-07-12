package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryInsurance;

@Component
public interface IContributionMandatoryInsuranceServices {
	ResponseEntity<ContributionMandatoryInsurance[]> GetAll(String authToken);
	ResponseEntity<ContributionMandatoryInsurance> Get(String authToken,int id);
	ResponseEntity<ContributionMandatoryInsurance> GetByAmount(String authToken,double amount);
	ResponseEntity<ContributionMandatoryInsurance> Create(String authToken,ContributionMandatoryInsurance contribution);
	ResponseEntity<ContributionMandatoryInsurance> Update(String authToken,int id,ContributionMandatoryInsurance contribution);
	ResponseEntity<Integer> Delete(String authToken,int id);
}

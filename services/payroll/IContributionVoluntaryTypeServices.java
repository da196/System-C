package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.ContributionVoluntaryType;

@Component
public interface IContributionVoluntaryTypeServices {
	ResponseEntity<ContributionVoluntaryType[]> GetAll(String authToken);
	ResponseEntity<ContributionVoluntaryType> Get(String authToken,int id);
	ResponseEntity<ContributionVoluntaryType> Create(String authToken,ContributionVoluntaryType type);
	ResponseEntity<ContributionVoluntaryType> Update(String authToken,int id,ContributionVoluntaryType type);
	ResponseEntity<Integer> Delete(String authToken,int id);
	ResponseEntity<ContributionVoluntaryType[]> GetAllByProvider(String authToken, int providerid);
}

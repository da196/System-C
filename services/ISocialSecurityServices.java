package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.SocialSecurity;

@Component
public interface ISocialSecurityServices {

	//Get Social Security Funds
	ResponseEntity<SocialSecurity[]> GetAll();
	
	//Add New Social security fund
	ResponseEntity<SocialSecurity> Create(SocialSecurity socialSecurity);
	
	//Delete Fund
	ResponseEntity<SocialSecurity> Delete(int id);
}

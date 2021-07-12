package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.UserX;

@Component
public interface IUserAuthenticationServices {

	//Get Authentication
	ResponseEntity<UserX[]> GetAll();
	
	//Post Request
	ResponseEntity<UserX> CreateRequest(UserX user);
}

package tz.go.tcra.hrms.auth;

import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.UserAuthentication;

@Component
public interface IAuthenticationService {
	boolean exists(String username, String password);
	UserAuthentication authenticate(String username, String password);
}

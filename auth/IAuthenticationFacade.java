package tz.go.tcra.hrms.auth;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.UserAuthenticationPrincipal;

/*
 * https://www.baeldung.com/get-user-in-spring-security
 * */
@Component
public interface IAuthenticationFacade {
	Authentication getAuthentication();
	String getAuthenticationToken();
	UserAuthenticationPrincipal getUser();
}

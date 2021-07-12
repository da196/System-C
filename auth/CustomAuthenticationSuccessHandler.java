package tz.go.tcra.hrms.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import tz.go.tcra.hrms.core.AppUserRoles;
import tz.go.tcra.hrms.dto.UserAuthenticationPrincipal;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		int hasRoleHR = 0;
		// principal
		UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) authentication.getPrincipal();
		if (principal != null) {
			System.out.println("CustomAuthenticationSuccessHandler >> " + principal.getFullname()); // debug
		}
		authenticationFacade = new AuthenticationFacade();
		if(authenticationFacade.getAuthentication()!=null && authenticationFacade.getAuthentication().getPrincipal()!=null) {
			UserAuthenticationPrincipal _principal = (UserAuthenticationPrincipal)authenticationFacade.getAuthentication().getPrincipal();
			if(_principal!=null) {
				System.out.println("CustomAuthenticationSuccessHandler >> "+_principal.getFullname()); // debug
				// do something with roles?
				for (GrantedAuthority authority : authentication.getAuthorities()) {
					final List<String> rolesHR = Arrays.asList(AppUserRoles.ROLES_HR);
					if(rolesHR.contains(authority.getAuthority())) {
						++hasRoleHR;
					}
					System.out.println("CustomAuthenticationSuccessHandler >> "+authority.getAuthority()); // debug
			    }
			}
		}
		// Redirect user to login page with error message.
		if(hasRoleHR>0) {			
			final String action = "/dashboard.htm";
		    String path = request.getContextPath()+action;
			UriComponents uri = UriComponentsBuilder
						.newInstance()
						.path(path)
						.build();
		    response.sendRedirect(uri.toUriString());
		}else {		
			// kill session
			authentication.setAuthenticated(false);
			logout(request,response,authenticationFacade.getAuthentication());
			// redirect
			final String action = "/login.htm";
		    String path = request.getContextPath()+action;
			UriComponents uri = UriComponentsBuilder
						.newInstance()
						.path(path)
						.build();
		    response.sendRedirect(uri.toUriString());
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
		final SecurityContextLogoutHandler contextLogOut = new SecurityContextLogoutHandler();	
		contextLogOut.logout(request, response, auth); // concern you
	}

}
package tz.go.tcra.hrms.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.core.SessionRequestCode;
import tz.go.tcra.hrms.dto.UserX;
import tz.go.tcra.hrms.services.IUserAuthenticationServices;

@Controller
@Scope("session")
public class UserAuthenticationController {
	
	@Autowired
	private IUserAuthenticationServices authServices;
	
	/*
	 * Create authentication request.
	 * 
	 */
	@RequestMapping(value = "/authenticate.htm", method = RequestMethod.POST)
	public String ReqAuth(@ModelAttribute("user") UserX user,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			HttpSession session) {
		if(user == null ||user.getUsername()==null || user.getUsername().isEmpty() || user.getPassword()==null || user.getPassword().isEmpty()) {
			return "redirect:/error.htm";
		}
		
		ResponseEntity<UserX> result = authServices.CreateRequest(user);
		if(result==null) {
			System.out.println("Result is empty"); // print for debug
			return "redirect:/error.htm";
		}
		int statusCode = result.getStatusCodeValue();
		System.out.println("Status code-"+statusCode);
		System.out.println(result);
		
		// failed login
		if(statusCode!=200) {
			System.out.println("Result is empty"); // print for debug
			return "redirect:/error.htm";
		}
		
		// authenticated
		UserX userLoggedIn = result.getBody();
		// set session
		session.setAttribute(SessionRequestCode.SESSION_REQUEST_KEY_USER, userLoggedIn);
		
		request.getSession().setAttribute("email", userLoggedIn.getEmail());
		request.getSession().setAttribute("fullname", userLoggedIn.getFullname());
		request.getSession().setAttribute("filenumber", userLoggedIn.getFilenumber());
		request.getSession().setAttribute("empId", userLoggedIn.getEmpId());
		request.getSession().setAttribute("token", result.getHeaders().getFirst("Authorization"));
		
		return "redirect:/";
	}

}

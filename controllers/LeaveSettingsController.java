package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.dto.LeaveSettings;
import tz.go.tcra.hrms.services.ILeaveSettingsServices;

@Controller
public class LeaveSettingsController {

	@Autowired
	private ILeaveSettingsServices leaveSettingsServices;
	
	@RequestMapping(value = "/leave-types.htm", method = RequestMethod.GET)
	public ModelAndView LeaveTypesIndex() {
		ResponseEntity<LeaveSettings[]> leaveTypesResult  = null;
		if(leaveSettingsServices != null) {
			leaveTypesResult = leaveSettingsServices.GetLeaveTypes();
		}
		
		List<LeaveSettings> leaveTypes = null;
		if(leaveTypesResult.getBody() != null) {
			leaveTypes = Arrays.asList(leaveTypesResult.getBody());
		}
		
		ModelAndView mv = new ModelAndView("leave/leave-types");
		mv.addObject("leaveTypes", leaveTypes);
		
		return mv;
	}
	
	@RequestMapping(value = "/add-new-leave-type.htm", method = RequestMethod.POST)
	public String AddLeaveType(@ModelAttribute("NewLeaveType") @RequestBody LeaveSettings leave) {
		if(leave == null || leave.getName().isEmpty() || leave.getMaxdayNumber() < 1) {
			return "redirect:/null-values-error.htm";
		}
		
		try {
			ResponseEntity<LeaveSettings> result = leaveSettingsServices.AddLeaveType(leave);
			if(result.getStatusCodeValue()!=200) {
				if(result.getStatusCodeValue() == 208) {
					return "redirect:/duplicate-values-error.htm";
				}else if(result.getStatusCodeValue() == 400) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 401) {
					return "redirect:/not-authorized-error.htm";
				}else if(result.getStatusCodeValue() == 404) {
					return "redirect:/not-found.htm";
				}else {
					return "redirect:/error.htm";
				}

			}			
			// Success? redirect to Contracts
			return "redirect:/leave-types.htm";
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			return "redirect:/bad-request.htm";
		}
	}
	
	@RequestMapping(value = "/delete-leave-type/{id}", method = RequestMethod.GET)
	public String DeleteLeaveType(@PathVariable("id") int id) {
		if(id > 0) {
			ResponseEntity<LeaveSettings> result = leaveSettingsServices.DeleteLeaveType(id);
			if(result.getStatusCodeValue()!=200) {
				if(result.getStatusCodeValue() == 400) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 401) {
					return "redirect:/not-authorized-error.htm";
				}else if(result.getStatusCodeValue() == 404) {
					return "redirect:/not-found.htm";
				}else {
					return "redirect:/error.htm";
				}

			}			
			// Success? redirect to Contracts
			return "redirect:/leave-types.htm";
		}
		return null;
	}
}

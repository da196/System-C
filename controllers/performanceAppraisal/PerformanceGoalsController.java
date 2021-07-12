package tz.go.tcra.hrms.controllers.performanceAppraisal;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceGoal;
import tz.go.tcra.hrms.services.performanceAppraisal.IPerformanceGoalServices;

@Controller
public class PerformanceGoalsController {
	@Autowired
	private IPerformanceGoalServices goalsServices;

	@RequestMapping(value = "/all-strategic-goals.htm")
	public String GetAllPerformanceGoals() {
		return "performance-appraisal/all-strategic-goals";
	}
	
	@RequestMapping(value = "/all-strategic-goalsAjax", method = RequestMethod.GET)
	public @ResponseBody Object GetAllPerformanceGoalsAjax() {
		final ResponseEntity<PerformanceGoal[]> result = goalsServices.GetAll();
		if(result.getBody() != null) {
			final List<PerformanceGoal> strategicGoals = Arrays.asList(result.getBody());
			return strategicGoals;
		}
		return null;
	}
	
	@RequestMapping(value = "/delete-strategic-goalAjax/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object DeleteStrategicGoalsAjax(@PathVariable("id") int id) {
		final ResponseEntity<PerformanceGoal> result = goalsServices.DeleteStrategicGoal(id);
		if(result.getBody() != null) {
			if (result.getStatusCodeValue() != 200) {
				if(result.getStatusCodeValue() == 400) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 401) {
					return "redirect:/not-authorized-error.htm";
				}else if(result.getStatusCodeValue() == 404) {
					return "redirect:/not-found.htm";
				}else if(result.getStatusCodeValue() == 412) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 500) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 503) {
					return "redirect:/service-unavailable.htm";
				}else {
					return "redirect:/error.htm";
				}
			}
			return null;
		}
		return null;
	}
	
	@RequestMapping(value = "/edit-strategic-goalAjax/{id}", method = RequestMethod.GET)
	public @ResponseBody Object EditStrategicGoalsAjax(@PathVariable("id") int id) {
		final ResponseEntity<PerformanceGoal> result = goalsServices.EditStrategicGoal(id);
		if(result.getBody() != null) {
			if (result.getStatusCodeValue() != 200) {
				if(result.getStatusCodeValue() == 400) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 401) {
					return "redirect:/not-authorized-error.htm";
				}else if(result.getStatusCodeValue() == 404) {
					return "redirect:/not-found.htm";
				}else if(result.getStatusCodeValue() == 412) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 500) {
					return "redirect:/bad-request.htm";
				}else if(result.getStatusCodeValue() == 503) {
					return "redirect:/service-unavailable.htm";
				}else {
					return "redirect:/error.htm";
				}
			}
			return null;
		}
		return null;
	}
}

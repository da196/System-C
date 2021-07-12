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

import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceObjective;
import tz.go.tcra.hrms.services.performanceAppraisal.IPerformanceObjectiveServices;

@Controller
public class PerformanceObjectiveController {
	
	@Autowired
	private IPerformanceObjectiveServices objectivesServices;

	@RequestMapping(value = "/all-strategic-objectives.htm")
	public String RenderObjectivesPage() {
		return "performance-appraisal/all-strategic-objectives";
	}
	
	@RequestMapping(value = "/all-strategic-objectivesAjax", method = RequestMethod.GET)
	public @ResponseBody Object GetAllObjectivesAjax(){
		final ResponseEntity<PerformanceObjective[]> result = objectivesServices.GetAllObjectives();
		if(result.getBody() != null) {
			final List<PerformanceObjective> objectives = Arrays.asList(result.getBody());
			return objectives;
		}
		return null;
	}
	
	@RequestMapping(value = "/delete-strategic-objectiveAjax/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object DeleteStrategicObjectiveAjax(@PathVariable("id") int id){
		final ResponseEntity<PerformanceObjective> result = objectivesServices.DeleteStrategicObjective(id);
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
	
	@RequestMapping(value = "/get-objective-by-objectiveIdAjax/{id}", method = RequestMethod.GET)
	public @ResponseBody Object GetObjectiveBuObjectiveIdAjax(@PathVariable("id") int id){
		final ResponseEntity<PerformanceObjective> result = objectivesServices.GetObjectiveByObjectiveId(id);
		if(result.getBody() != null) {
			final PerformanceObjective objective = result.getBody();
			return objective;
		}
		return null;
	}
}

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

import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceTargets;
import tz.go.tcra.hrms.services.performanceAppraisal.IPerformanceTargetServices;

@Controller
public class PerformanceTargetsController {

	@Autowired
	private IPerformanceTargetServices targetsServices;
	
	@RequestMapping(value = "/all-strategic-targets.htm")
	public String RenderTargetsPage() {
		return "performance-appraisal/all-strategic-targets";
	}
	
	@RequestMapping(value = "/all-strategic-targetsAjax", method = RequestMethod.GET)
	public @ResponseBody Object GetAllStrategicTargetssAjax(){
		final ResponseEntity<PerformanceTargets[]> result = targetsServices.GetAllTargets();
		if(result.getBody() != null) {
			final List<PerformanceTargets> targets = Arrays.asList(result.getBody());
			return targets;
		}
		
		return null;
	}
	
	@RequestMapping(value = "/delete-strategic-targetAjax/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object DeleteStrategicTargetAjax(@PathVariable("id") int id){
		final ResponseEntity<PerformanceTargets> result = targetsServices.DeleteStrategicTarget(id);
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

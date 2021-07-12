package tz.go.tcra.hrms.controllers.ImplementationPlan;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationActivity;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationActivityResponsible;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationOutcome;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationOutput;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationPlan;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.ImplementationTarget;
import tz.go.tcra.hrms.dto.appraisalImplementationPlan.PerformanceObjectiveOutcomeGlobalResponselist;
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceGoal;
import tz.go.tcra.hrms.services.appraisalImplementationPlan.IImplementationPlanServices;
import tz.go.tcra.hrms.services.performanceAppraisal.IPerformanceGoalServices;

@Controller
public class ImplementationPlanController {
	
	@Autowired
	private IImplementationPlanServices implementationServices;
	@Autowired
	private IPerformanceGoalServices goalsServices;
	
	@RequestMapping(value = "/implementation-plan.htm")
	public String RenderImplementationPlans() {
		return "appraisal-implementation/implementation-plan";
	}
	
	//Add objective outcome
	@RequestMapping(value = "/add-objective-outcomeAjax", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody Object AddObjectiveOutcomeAjax(@RequestBody ImplementationOutcome outcome) {
		final ResponseEntity<ImplementationOutcome> result = implementationServices.AddNewOutcome(outcome);
		if (result.getStatusCodeValue() != 200) {
			if(result.getStatusCodeValue() == 208) {
				return "redirect:/duplicate-values-error.htm";
			}else if(result.getStatusCodeValue() == 400) {
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
		return result.getStatusCodeValue();
	}
	
	//Get all objective outcomes
	@RequestMapping(value = "/get-all-objective-outcomeAjax")
	public @ResponseBody Object GetAllObjectiveOutcomeAjax() {
		final ResponseEntity<ImplementationOutcome[]> result = implementationServices.GetAllOutcomes();
		if(result.getBody() != null) {
			final List<ImplementationOutcome> objectiveOutcomes = Arrays.asList(result.getBody());
			return objectiveOutcomes;
		}
		return null;
	}
	
	//Add outcome activity
	@RequestMapping(value = "/add-objective-activityAjax", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody Object AddObjectiveActivityAjax(@RequestBody ImplementationActivity activity) {
		final ResponseEntity<ImplementationActivity> result = implementationServices.AddNewActivity(activity);
		if (result.getStatusCodeValue() != 200) {
			if(result.getStatusCodeValue() == 208) {
				return "redirect:/duplicate-values-error.htm";
			}else if(result.getStatusCodeValue() == 400) {
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
		return result.getStatusCodeValue();
	}
	
	//Get all outcome activities
	@RequestMapping(value = "/get-all-objective-activitiesAjax")
	public @ResponseBody Object GetAllObjectiveActivitiesAjax() {
		final ResponseEntity<ImplementationActivity[]> result = implementationServices.GetAllActivities();
		if(result.getBody() != null) {
			final List<ImplementationActivity> objectiveActivities = Arrays.asList(result.getBody());
			return objectiveActivities;
		}
		return null;
	}
	
	//Add activity output
	@RequestMapping(value = "/add-activity-outputAjax", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody Object AddActivityOutputAjax(@RequestBody ImplementationOutput output) {
		final ResponseEntity<ImplementationOutput> result = implementationServices.AddNewOutput(output);
		if (result.getStatusCodeValue() != 200) {
			if(result.getStatusCodeValue() == 208) {
				return "redirect:/duplicate-values-error.htm";
			}else if(result.getStatusCodeValue() == 400) {
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
		return result.getStatusCodeValue();
	}
	
	//Get all activities output
	@RequestMapping(value = "/get-all-activity-outputsAjax")
	public @ResponseBody Object GetAllActivitieOutputsAjax() {
		final ResponseEntity<ImplementationOutput[]> result = implementationServices.GetAllOutputs();
		if(result.getBody() != null) {
			final List<ImplementationOutput> activityOutputs = Arrays.asList(result.getBody());
			return activityOutputs;
		}
		return null;
	}
	
	//Add output target
	@RequestMapping(value = "/add-output-targetAjax", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody Object AddOutputTargetAjax(@RequestBody ImplementationTarget target) {
		final ResponseEntity<ImplementationTarget> result = implementationServices.AddNewTarget(target);
		if (result.getStatusCodeValue() != 200) {
			if(result.getStatusCodeValue() == 208) {
				return "redirect:/duplicate-values-error.htm";
			}else if(result.getStatusCodeValue() == 400) {
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
		return result.getStatusCodeValue();
	}
	
	//Get all output targets
	@RequestMapping(value = "/get-all-outputs-targetsAjax")
	public @ResponseBody Object GetAllOutputsTargetsAjax() {
		final ResponseEntity<ImplementationTarget[]> result = implementationServices.GetAllTargets();
		if(result.getBody() != null) {
			final List<ImplementationTarget> outputsTargets = Arrays.asList(result.getBody());
			return outputsTargets;
		}
		return null;
	}
	
	//Add Responsible
	@RequestMapping(value = "/add-responsibleByAjax", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody Object AddResponsibleByAjax(@RequestBody ImplementationActivityResponsible responsible) {
		final ResponseEntity<ImplementationActivityResponsible> result = implementationServices.AddResponsible(responsible);
		if (result.getStatusCodeValue() != 200) {
			if(result.getStatusCodeValue() == 208) {
				return "redirect:/duplicate-values-error.htm";
			}else if(result.getStatusCodeValue() == 400) {
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
		return result.getStatusCodeValue();
	}
	
	//Get all responsible
	@RequestMapping(value = "/get-all-responsibleByAjax")
	public @ResponseBody Object GetAllResponsibleByAjax() {
		final ResponseEntity<ImplementationActivityResponsible[]> result = implementationServices.GetAllResponsible();
		if(result.getBody() != null) {
			final List<ImplementationActivityResponsible> allResponsible = Arrays.asList(result.getBody());
			return allResponsible;
		}
		return null;
	}
	
	//Render detailed objectives page
	@RequestMapping(value = "/detailed-objectives.htm")
	public String DetailedObjectives() {
		return "appraisal-implementation/detailed-objectives"; 
	}
	
	//Render detailed objectives implementation plans page
	@RequestMapping(value = "/detailed-implementation-plan.htm/{goalId}/{objectiveId}")
	public ModelAndView DetailedImplementationPlan(@PathVariable("goalId") int goalId, @PathVariable("objectiveId") int objectiveId) {
		if(objectiveId > 0) {
			final ResponseEntity<PerformanceGoal> getGoalById = goalsServices.GetGoalByGoalId(goalId);
			if(getGoalById.getBody() != null) {
				int planID = getGoalById.getBody().getPlanid();
				ResponseEntity<ImplementationPlan> result = implementationServices.GetCompiledImplementationPlanList(planID, goalId, objectiveId);
				if(result != null) {
					List<PerformanceObjectiveOutcomeGlobalResponselist> compiledImplPlan = result.getBody().getPerformanceObjectiveOutcomeGlobalResponselist();
					ModelAndView mv = new ModelAndView("appraisal-implementation/detailed-implementation-plan");
					mv.addObject("compiledImplPlan", compiledImplPlan);
					
					return mv;
				}
			}
		}
		return null; 
	}

}

package tz.go.tcra.hrms.controllers.performanceAppraisal;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceGoal;
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceGoalSummary;
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceObjective;
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformancePlan;
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformancePlanSummary;
import tz.go.tcra.hrms.dto.performanceAppraisal.PerformanceTargets;
import tz.go.tcra.hrms.services.performanceAppraisal.IPerformanceGoalServices;
import tz.go.tcra.hrms.services.performanceAppraisal.IPerformanceObjectiveServices;
import tz.go.tcra.hrms.services.performanceAppraisal.IPerformancePlanServices;
import tz.go.tcra.hrms.services.performanceAppraisal.IPerformanceTargetServices;

@Controller
public class PerformancePlanController {
	
	@Autowired
	private IPerformancePlanServices appraisalServices;
	@Autowired
	private IPerformanceGoalServices performanceGoalsServices;
	@Autowired
	private IPerformanceObjectiveServices performanceObjectivesServices;
	@Autowired
	private IPerformanceTargetServices targetServices;

	//Renders strategic plan page
	@RequestMapping(value = "/performance-plans.htm")
	public String StrategicPlans() {
		return "performance-appraisal/performance-plans";
	}
	
	//Renders strategic goals, objectives and targets page
	@RequestMapping(value = "/strategic-goals.htm/{id}")
	public ModelAndView StrategicGoals(@PathVariable("id") int id) {
		
		if(id>0) {
			ResponseEntity<PerformancePlanSummary> result = appraisalServices.GetCompiledList(id);
			
			if(result != null) {
				List<PerformanceGoalSummary> goals = result.getBody().getPerformanceGoallist();
				
				ModelAndView mv = new ModelAndView("performance-appraisal/strategic-goals");
				mv.addObject("goals", goals);
				
				return mv;
			}
		}
		return null;
	}
	
	//add new performance plans(POST)
	@RequestMapping(value = "/add-new-performance-plan.htm", method = RequestMethod.POST)
	public String AddNewPerformancePlan(@ModelAttribute("newPerformancePlan") @RequestBody PerformancePlan plan){
		if(plan == null || plan.getName().isEmpty() || plan.getYearstarting() < 1) {
			return "redirect:/null-values-error.htm";
		}
		
		try {
			 ResponseEntity<PerformancePlan> result = appraisalServices.AddNewPerfomancePlan(plan);
			 if (result.getStatusCodeValue() != 200) {
					System.out.println(result.getStatusCodeValue());
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
			 return "redirect:/performance-plans.htm";
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}
	}
	
	//get all performance plans
	@RequestMapping(value = "/get-performance-plansAjax", method = RequestMethod.GET)
	public @ResponseBody Object GetPerformancePlansAjax() {
		final ResponseEntity<PerformancePlan[]> result = appraisalServices.GetAllPerformancePlans();
		if(result.getBody() != null) {
			final List<PerformancePlan> performancePlans = Arrays.asList(result.getBody());
			return performancePlans;
		}
		return null;
	}
	
	//add new performance goal(POST)
	@RequestMapping(value = "/add-new-performance-goal.htm", method = RequestMethod.POST)
	public String AddNewPerformanceGoal(@ModelAttribute("newPerformanceGoal") @RequestBody PerformanceGoal goal){
		if(goal == null || goal.getName().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		
		try {
			 ResponseEntity<PerformanceGoal> result = performanceGoalsServices.AddNewPerformanceGoal(goal);
			 if (result.getStatusCodeValue() != 200) {
					System.out.println(result.getStatusCodeValue());
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
			 final int planID = goal.getPlanid();
			 return "redirect:/strategic-goals.htm/"+planID;
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}
	}
	
	//get all performance goals
	@RequestMapping(value = "/get-performance-goalsAjax", method = RequestMethod.GET)
	public @ResponseBody Object GetPerformanceGoalsAjax() {
		final ResponseEntity<PerformanceGoal[]> result = performanceGoalsServices.GetAll();
		if(result.getBody() != null) {
			final List<PerformanceGoal> performanceGoals = Arrays.asList(result.getBody());
			return performanceGoals;
		}
		return null;
	}
	
	//add new performance objective(POST)
	@RequestMapping(value = "/add-new-performance-objective.htm", method = RequestMethod.POST)
	public String AddNewPerformanceObjective(@ModelAttribute("newPerformanceObjective") @RequestBody PerformanceObjective objective, @PathVariable("url") String url){
		if(objective == null || objective.getDescription().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		
		try {
			 ResponseEntity<PerformanceObjective> result = performanceObjectivesServices.AddNewPerformanceObjective(objective);
			 if (result.getStatusCodeValue() != 200) {
					System.out.println(result.getStatusCodeValue());
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
			 String planID = url.replaceFirst(".*/([^/?]+).*", "$1");
			 //return "redirect:/performance-plans.htm";
			 return "redirect:/strategic-goals.htm/"+planID;
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}
	}
	
	//get all performance objectives
	@RequestMapping(value = "/get-performance-objectivesAjax", method = RequestMethod.GET)
	public @ResponseBody Object GetPerformanceObjectivesAjax() {
		final ResponseEntity<PerformanceObjective[]> result = performanceObjectivesServices.GetAllObjectives();
		if(result.getBody() != null) {
			final List<PerformanceObjective> performanceObjectives = Arrays.asList(result.getBody());
			return performanceObjectives;
		}
		return null;
	}
	
	//get strategic goals by plan ID
	@RequestMapping(value = "/get-performance-goals-by-planIdAjax/{id}", method = RequestMethod.GET)
	public @ResponseBody Object GetPerformanceGoalsByPlanIdAjax(@PathVariable("id") int id) {
		final ResponseEntity<PerformanceGoal[]> result = performanceGoalsServices.GetGoalByPlanId(id);
		if(result.getBody() != null) {
			final List<PerformanceGoal> performanceGoalsByPlanId = Arrays.asList(result.getBody());
			return performanceGoalsByPlanId;
		}
		return null;
	}
	
	//get strategic objectives by goal ID
	@RequestMapping(value = "/get-performance-objectives-by-goalIdAjax/{id}", method = RequestMethod.GET)
	public @ResponseBody Object GetPerformanceObjectivesByGoalIdAjax(@PathVariable("id") int id) {
		final ResponseEntity<PerformanceObjective[]> result = performanceObjectivesServices.GetObjectivesByGoalId(id);
		if(result.getBody() != null) {
			final List<PerformanceObjective> objectivesByGoalId = Arrays.asList(result.getBody());
			return objectivesByGoalId;
		}
		return null;
	}
	
	//add new performance target(POST)
	@RequestMapping(value = "/add-new-performance-target.htm", method = RequestMethod.POST)
	public String AddNewPerformanceTarget(@ModelAttribute("newPerformanceTarget") @RequestBody PerformanceTargets target){
		if(target == null || target.getDescription().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		
		try {
			 ResponseEntity<PerformanceTargets> result = targetServices.AddNewTargets(target);
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
			 return "redirect:/performance-plans.htm";
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}
	}
	
	//get strategic targets by objective ID
	@RequestMapping(value = "/get-performance-targets-by-objectiveIdAjax/{id}", method = RequestMethod.GET)
	public @ResponseBody Object GetPerformanceTargetsByObjectiveIdAjax(@PathVariable("id") int id) {
		final ResponseEntity<PerformanceTargets[]> result = targetServices.GetTargetsByObjectiveId(id);
		if(result.getBody() != null) {
			final List<PerformanceTargets> performanceTargets = Arrays.asList(result.getBody());
			return performanceTargets;
		}
		return null;
	}
	
	//delete plan by plan ID
	@RequestMapping(value = "/delete-plan-byPlanIdAjax/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object DeletePlanByPlanIdAjax(@PathVariable("id") int id) {
		final ResponseEntity<PerformancePlan> result = appraisalServices.DeletePlan(id);
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

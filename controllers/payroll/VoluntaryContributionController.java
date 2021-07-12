package tz.go.tcra.hrms.controllers.payroll;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.payroll.ContributionVoluntary;
import tz.go.tcra.hrms.dto.payroll.Loan;
import tz.go.tcra.hrms.services.payroll.IContributionVoluntaryServices;
import tz.go.tcra.hrms.utils.DateUtils;

@Controller
public class VoluntaryContributionController {
	private static final Logger logger = Logger.getLogger(VoluntaryContributionController.class); // log4j
	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	@Autowired 
	private IContributionVoluntaryServices contributionVoluntaryServices;
	
	@RequestMapping(value = "/payroll-voluntary-contribution.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// contributions
		List<ContributionVoluntary> _contributions = null;
		if(contributionVoluntaryServices!=null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionVoluntary[]> contributions = contributionVoluntaryServices.GetAll(authToken);
			if (contributions != null && contributions.getBody() != null) {
				_contributions = Arrays.asList(contributions.getBody());
			}
		}
		
		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-contribution-voluntary");
		// add model data
		mv.addObject("contributions", _contributions);
		
		return mv;		
	}
	
	@RequestMapping(value = "/payroll-add-voluntary-contribution.htm", method = RequestMethod.GET)
	public ModelAndView add() {
		// initialize
		// loan
		
		
		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-add-voluntary-contribution");
		// add model data
		//mv.addObject("loans", _loans);
		
		return mv;		
	}
	
	@RequestMapping(value = "/payroll-add-voluntary-contribution.htm", method = RequestMethod.POST)
	public String add(@ModelAttribute("voluntaryContribution")  @RequestBody ContributionVoluntary contribution) {
		// check
		if(contribution == null || contribution.getEmployeeid()<=0) {
			return "redirect:/error.htm";
		}
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionVoluntary> result = contributionVoluntaryServices.Create(authToken, contribution);	
			if(result.getStatusCodeValue()!=200) {
				return "redirect:/error.htm";
			}			
			return "redirect:/payroll-voluntary-contribution.htm";
		}catch(Exception ex) 
		{			
			System.out.print(ex.toString());
			ex.printStackTrace();
			return "redirect:/error.htm";
		}	
	}
	
	//--------------------------------------------------
	//- Ajax Calls
	//--------------------------------------------------	
	@RequestMapping(value = "/v1/payroll-add-voluntary-contribution", method = RequestMethod.POST,
			produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody ResponseEntity<?> Add(@Validated @RequestBody ContributionVoluntary contribution){
		logger.info(String.format("Contribution passed is {%s} ",contribution));
		if(contribution == null || contribution.getEmployeeid()<=0) {
			logger.info("Contribution is null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// date to database format
			final String joiningDate = contribution.getJoiningdate();
			final String joining = DateUtils.toYYYYMMDD(joiningDate);
			contribution.setJoiningdate(joining);
			final ResponseEntity<ContributionVoluntary> result = contributionVoluntaryServices.Create(authToken, contribution);

			int code = result.getStatusCodeValue();
			logger.info(String.format("Status {%d} is not equal to 200", result.getStatusCodeValue()));
			if(HttpStatusCodes.EXISTS==code) {
				return new ResponseEntity<>(
						String.format("Status code is {%d} already exists",code), 
				          HttpStatus.ALREADY_REPORTED);
			}else if(HttpStatusCodes.NOT_FOUND==code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else if(HttpStatusCodes.NOT_AUTHORIZED==code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}else if(HttpStatusCodes.NOT_UNAVAILABLE==code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}else {
				return new ResponseEntity<ContributionVoluntary>(result.getBody(),HttpStatus.OK);
			}			
		}catch(Exception ex) 
		{			
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@RequestMapping(value = "/v1/payroll-update-voluntary-contribution/{id}", method = RequestMethod.POST,
			produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody ResponseEntity<ContributionVoluntary> Update(@PathVariable("id")int id, @Validated @RequestBody ContributionVoluntary contribution){
		logger.info(String.format("Contribution passed is {%s} ",contribution));
		if(contribution == null || contribution.getEmployeeid()<=0) {
			logger.info("Contribution is null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// date to database format
			final String joiningDate = contribution.getJoiningdate();
			final String joining = DateUtils.toYYYYMMDD(joiningDate);
			contribution.setJoiningdate(joining);
			final ResponseEntity<ContributionVoluntary> result = contributionVoluntaryServices.Update(authToken, id, contribution);

			int code = result.getStatusCodeValue();
			logger.info(String.format("Status {%d} is not equal to 200", result.getStatusCodeValue()));
			if(HttpStatusCodes.EXISTS==code) {
				return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
			}else if(HttpStatusCodes.NOT_FOUND==code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else if(HttpStatusCodes.NOT_AUTHORIZED==code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}else if(HttpStatusCodes.NOT_UNAVAILABLE==code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}			
			return new ResponseEntity<ContributionVoluntary>(result.getBody(),HttpStatus.OK);	
		}catch(Exception ex) 
		{			
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}

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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.payroll.ContributionVoluntaryServiceProvider;
import tz.go.tcra.hrms.dto.payroll.ContributionVoluntaryType;
import tz.go.tcra.hrms.dto.payroll.Loan;
import tz.go.tcra.hrms.dto.payroll.PensionServiceProvider;
import tz.go.tcra.hrms.services.payroll.IContributionVoluntaryServiceProviderServices;
import tz.go.tcra.hrms.services.payroll.IContributionVoluntaryServices;

@Controller
public class VoluntaryContributionServiceProviderController {
	private static final Logger logger = Logger.getLogger(VoluntaryContributionServiceProviderController.class); // log4j
	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	@Autowired 
	private IContributionVoluntaryServiceProviderServices serviceProviderServices;
	
	@RequestMapping(value = "/payroll-voluntary-contribution-service-providers.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// providers
		List<ContributionVoluntaryServiceProvider> _providers = null;
		if(serviceProviderServices!=null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionVoluntaryServiceProvider[]> providers = serviceProviderServices.GetAll(authToken);
			if (providers != null && providers.getBody() != null) {
				_providers = Arrays.asList(providers.getBody());
			}
		}
		
		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-voluntary-contribution-service-providers");
		// add model data
		mv.addObject("providers", _providers);
		
		return mv;		
	}
	
	@RequestMapping(value = "/payroll-add-voluntary-contribution-service-provider.htm", method = RequestMethod.GET)
	public ModelAndView add() {
		// initialize
		
		
		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-voluntary-contribution-service-provider-add");
		// add model data
		//mv.addObject("providers", _providers);
		
		return mv;		
	}
	
	@RequestMapping(value = "/payroll-add-voluntary-contribution-service-provider.htm", method = RequestMethod.POST)
	public String add(@ModelAttribute("voluntaryContribution")  @RequestBody ContributionVoluntaryServiceProvider provider) {
		// check
		if(provider == null || StringUtils.isEmpty(provider.getName())) {
			return "redirect:/error.htm";
		}
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionVoluntaryServiceProvider> result = serviceProviderServices.Create(authToken, provider);	
			if(result.getStatusCodeValue()!=200) {
				return "redirect:/error.htm";
			}			
			return "redirect:/payroll-voluntary-contribution-service-providers.htm";
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
	@RequestMapping(value = "/v1/payroll-voluntary-contribution-providers.json", method = RequestMethod.GET)
	public @ResponseBody Object Get() {
		logger.info("Get voluntary contribution service providers all.");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionVoluntaryServiceProvider[]> result = serviceProviderServices.GetAll(authToken);
			int code = result.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<List<ContributionVoluntaryServiceProvider>>(Arrays.asList(result.getBody()), HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

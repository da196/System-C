package tz.go.tcra.hrms.controllers.payroll;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.payroll.ContributionVoluntaryType;
import tz.go.tcra.hrms.dto.payroll.InsuranceServiceProvider;
import tz.go.tcra.hrms.services.payroll.IContributionVoluntaryTypeServices;

@Controller
public class ContributionVoluntaryTypeController {
	private static final Logger logger = Logger.getLogger(ContributionVoluntaryTypeController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private IContributionVoluntaryTypeServices contributionVoluntaryTypeServices;

	// --------------------------------------------------
	// - Ajax Calls
	// --------------------------------------------------
	@RequestMapping(value = "/v1/payroll-voluntary-contribution-types.json/{providerid}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> Get(@PathVariable("providerid")int providerid,HttpServletRequest request) {
		logger.info("Get voluntary contribution types");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionVoluntaryType[]> result = contributionVoluntaryTypeServices.GetAllByProvider(authToken,providerid);
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
				return new ResponseEntity<List<ContributionVoluntaryType>>(Arrays.asList(result.getBody()), HttpStatus.OK);
			}
		} catch (Exception ex) {
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

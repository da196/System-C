package tz.go.tcra.hrms.controllers.settings;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryInsurance;
import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryPension;
import tz.go.tcra.hrms.dto.payroll.ContributionMandatoryType;
import tz.go.tcra.hrms.dto.payroll.ContributionVoluntaryType;
import tz.go.tcra.hrms.dto.payroll.LoanType;
import tz.go.tcra.hrms.services.payroll.IContributionMandatoryInsuranceServices;
import tz.go.tcra.hrms.services.payroll.IContributionMandatoryPensionServices;
import tz.go.tcra.hrms.services.payroll.IContributionMandatoryTypeServices;
import tz.go.tcra.hrms.services.payroll.IContributionVoluntaryTypeServices;
import tz.go.tcra.hrms.services.payroll.LoanTypeServices;

@Controller
public class ContributionSettingsController {
	private static final Logger logger = Logger.getLogger(ContributionSettingsController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private IContributionMandatoryTypeServices contributionMandatoryTypeServices;
	@Autowired
	private IContributionVoluntaryTypeServices contributionVoluntaryTypeServices;
	@Autowired
	private IContributionMandatoryPensionServices contributionMandatoryPensionServices;
	@Autowired
	private IContributionMandatoryInsuranceServices contributionMandatoryInsuranceServices;

	@RequestMapping(value = "/payroll-contribution-settings.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// mandatory types
		List<ContributionMandatoryType> _mandatoryTypes = null;
		if (contributionMandatoryTypeServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionMandatoryType[]> mandatoryTypes = contributionMandatoryTypeServices
					.GetAll(authToken);
			if (mandatoryTypes != null && mandatoryTypes.getBody() != null) {
				_mandatoryTypes = Arrays.asList(mandatoryTypes.getBody());
			}
		}
		// voluntary types
		List<ContributionVoluntaryType> _voluntaryTypes = null;
		if (contributionVoluntaryTypeServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionVoluntaryType[]> voluntaryTypes = contributionVoluntaryTypeServices
					.GetAll(authToken);
			if (voluntaryTypes != null && voluntaryTypes.getBody() != null) {
				_voluntaryTypes = Arrays.asList(voluntaryTypes.getBody());
			}
		}

		// mandatory pension
		List<ContributionMandatoryPension> _contributionMandatoryPension = null;
		if (contributionMandatoryPensionServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionMandatoryPension[]> contributionMandatoryPension = contributionMandatoryPensionServices
					.GetAll(authToken);
			if(contributionMandatoryPension!=null && contributionMandatoryPension.getBody()!=null) {
				_contributionMandatoryPension = Arrays.asList(contributionMandatoryPension.getBody());
			}
		}

		// mandatory insurance
		List<ContributionMandatoryInsurance> _contributionMandatoryInsurance = null;
		if (contributionMandatoryInsuranceServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<ContributionMandatoryInsurance[]> contributionMandatoryInsurance = contributionMandatoryInsuranceServices
					.GetAll(authToken);
			if(contributionMandatoryInsurance!=null && contributionMandatoryInsurance.getBody()!=null) {
				_contributionMandatoryInsurance = Arrays.asList(contributionMandatoryInsurance.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-settings/payroll-contribution-settings");
		// add model data
		mv.addObject("mandatoryTypes", _mandatoryTypes);
		mv.addObject("voluntaryTypes", _voluntaryTypes);
		mv.addObject("contributionMandatoryPension", _contributionMandatoryPension);
		mv.addObject("contributionMandatoryInsurance", _contributionMandatoryInsurance);

		return mv;
	}
}

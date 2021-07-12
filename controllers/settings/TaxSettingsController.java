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
import tz.go.tcra.hrms.dto.payroll.TaxStructure;
import tz.go.tcra.hrms.dto.payroll.TaxType;
import tz.go.tcra.hrms.services.payroll.TaxStructureServices;
import tz.go.tcra.hrms.services.payroll.TaxTypeServices;

@Controller
public class TaxSettingsController {
	private static final Logger logger = Logger.getLogger(TaxSettingsController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private TaxTypeServices taxTypeServices;
	@Autowired
	private TaxStructureServices taxStructureServices;


	@RequestMapping(value = "/payroll-tax-settings.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// tax types
		List<TaxType> _taxTypes = null;
		if (taxTypeServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<TaxType[]> taxTypes = taxTypeServices.GetAll(authToken);
			if (taxTypes != null && taxTypes.getBody() != null) {
				_taxTypes = Arrays.asList(taxTypes.getBody());
			}
		}
		// tax structure
		List<TaxStructure> _taxStructure = null;
		if (taxStructureServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<TaxStructure[]> taxStructure = taxStructureServices.GetAll(authToken);
			if (taxStructure != null && taxStructure.getBody() != null) {
				_taxStructure = Arrays.asList(taxStructure.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-settings/payroll-tax-settings");
		// add model data
		mv.addObject("taxTypes", _taxTypes);
		mv.addObject("taxStructure", _taxStructure);

		return mv;
	}
}

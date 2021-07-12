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
import tz.go.tcra.hrms.dto.Bank;
import tz.go.tcra.hrms.dto.payroll.TaxStructure;
import tz.go.tcra.hrms.dto.payroll.TaxType;
import tz.go.tcra.hrms.services.BankServices;
import tz.go.tcra.hrms.services.payroll.TaxStructureServices;
import tz.go.tcra.hrms.services.payroll.TaxTypeServices;

@Controller
public class BankSettingsController {
	private static final Logger logger = Logger.getLogger(BankSettingsController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private BankServices bankServices;


	@RequestMapping(value = "/payroll-bank-settings.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// initialize
		// banks
		List<Bank> _banks = null;
		if (bankServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<Bank[]> banks = bankServices.GetAll();
			if (banks != null && banks.getBody() != null) {
				_banks = Arrays.asList(banks.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-settings/payroll-bank-settings");
		// add model data
		mv.addObject("banks", _banks);

		return mv;
	}
}

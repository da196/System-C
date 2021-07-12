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
import tz.go.tcra.hrms.dto.payroll.LoanType;
import tz.go.tcra.hrms.dto.payroll.PayrollType;
import tz.go.tcra.hrms.services.payroll.LoanTypeServices;
import tz.go.tcra.hrms.services.payroll.PayrollTypeServices;

@Controller
public class PayrollSettingsController {
	private static final Logger logger = Logger.getLogger(PayrollSettingsController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private PayrollTypeServices payrollTypeServices;

	@RequestMapping(value = "/payroll-settings.htm", method = RequestMethod.GET)
	public ModelAndView index() {
		// payroll types
		List<PayrollType> _payrollTypes = null;
		if (payrollTypeServices != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<PayrollType[]> payrollTypes = payrollTypeServices.GetAll(authToken);
			if (payrollTypes != null && payrollTypes.getBody() != null) {
				_payrollTypes = Arrays.asList(payrollTypes.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-settings/payroll-settings");
		// add model data
		mv.addObject("payrollTypes", _payrollTypes);

		return mv;
	}
}

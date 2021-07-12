package tz.go.tcra.hrms.controllers.payroll;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.payroll.InsuranceServiceProvider;
import tz.go.tcra.hrms.dto.payroll.InsuranceType;
import tz.go.tcra.hrms.services.payroll.IInsuranceServiceProviderServices;
import tz.go.tcra.hrms.services.payroll.IInsuranceTypeServices;

@Controller
public class InsuranceTypeController {
	private static final Logger logger = Logger.getLogger(InsuranceTypeController.class); // log4j
	
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	@Autowired 
	private IInsuranceTypeServices insuranceTypeServices;
	
	// --------------------------------------------------
	// - Ajax Calls
	// --------------------------------------------------
	@RequestMapping(value = "/v1/payroll-insurance-types.json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> Get() {
		logger.info("Get insurance types all.");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<InsuranceType[]> result = insuranceTypeServices.GetAll(authToken);
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
				return new ResponseEntity<List<InsuranceType>>(Arrays.asList(result.getBody()), HttpStatus.OK);
			}
		} catch (Exception ex) {
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

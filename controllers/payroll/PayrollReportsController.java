package tz.go.tcra.hrms.controllers.payroll;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.UserAuthenticationPrincipal;
import tz.go.tcra.hrms.dto.payroll.HealthInsuranceResponse;
import tz.go.tcra.hrms.dto.payroll.HeslbReportResponse;
import tz.go.tcra.hrms.dto.payroll.HeslbRun;
import tz.go.tcra.hrms.dto.payroll.PAYEResponse;
import tz.go.tcra.hrms.dto.payroll.PayrollCycle;
import tz.go.tcra.hrms.dto.payroll.PublicSocialSecurityFundResponse;
import tz.go.tcra.hrms.dto.payroll.WorkersCompensationFundResponse;
import tz.go.tcra.hrms.services.payroll.IPayrollReportServices;
import tz.go.tcra.hrms.utils.DateUtils;


@Controller
public class PayrollReportsController {
	private static final Logger logger = Logger.getLogger(PayrollReportsController.class); // log4j
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	

	@Autowired
	private IPayrollReportServices payrollReportServices;

	@RequestMapping(value = "/payroll-reports-heslb.htm", method = RequestMethod.GET)
	public ModelAndView PayrollHeslbReport() {
		logger.info(String.format("Start Accessing Heslb Report"));
		ModelAndView mv = new ModelAndView("pay-roll/payroll-reports-heslb");
		final UserAuthenticationPrincipal authUser = authenticationFacade.getUser();
		mv.addObject("authUser", authUser);
		return mv;
	}
	
	@RequestMapping(value = "/v1/heslb-run", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> RunJournal(@Validated @RequestBody HeslbRun heslbRun) {
		logger.info(String.format("HESLB run data passed is {%s} ", heslbRun));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (heslbRun == null || heslbRun.getPayrollcycle() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String date = heslbRun.getPayrollcycle();
			if (date == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final PayrollCycle payrollCycle = DateUtils.toPayrollCycle(date);
			if (payrollCycle == null) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			logger.info(String.format("PayrollPay cycle data passed is {%s} ", payrollCycle));
			final ResponseEntity<HeslbReportResponse> response = payrollReportServices.RunHeslb(authToken, payrollCycle);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// code
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<HeslbReportResponse>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<HeslbReportResponse>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/payroll-reports-wcf.htm", method = RequestMethod.GET)
	public ModelAndView PayrollWCFReport() {
		logger.info(String.format("Start Accessing WCF Report"));
		ModelAndView mv = new ModelAndView("pay-roll/payroll-reports-wcf");
		final UserAuthenticationPrincipal authUser = authenticationFacade.getUser();
		mv.addObject("authUser", authUser);
		return mv;
	}
	
	
	@RequestMapping(value = "/v1/wcf-run", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> RunWCF(@Validated @RequestBody HeslbRun wcfRun) {
		logger.info(String.format("HESLB run data passed is {%s} ", wcfRun));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (wcfRun == null || wcfRun.getPayrollcycle() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String date = wcfRun.getPayrollcycle();
			if (date == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final PayrollCycle payrollCycle = DateUtils.toPayrollCycle(date);
			if (payrollCycle == null) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			logger.info(String.format("PayrollPay cycle data passed is {%s} ", payrollCycle));
			final ResponseEntity<WorkersCompensationFundResponse> response = payrollReportServices.RunWCF(authToken, payrollCycle);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// code
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<WorkersCompensationFundResponse>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<WorkersCompensationFundResponse>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/payroll-reports-psssf.htm", method = RequestMethod.GET)
	public ModelAndView PayrollPSSSFReport() {
		logger.info(String.format("Start Accessing PSSSF Report"));
		ModelAndView mv = new ModelAndView("pay-roll/payroll-reports-psssf");
		final UserAuthenticationPrincipal authUser = authenticationFacade.getUser();
		mv.addObject("authUser", authUser);
		return mv;
	}
	
	
	@RequestMapping(value = "/v1/psssf-run", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> RunPSSSF(@Validated @RequestBody HeslbRun psssfRun) {
		logger.info(String.format("PSSSF run data passed is {%s} ", psssfRun));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (psssfRun == null || psssfRun.getPayrollcycle() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String date = psssfRun.getPayrollcycle();
			if (date == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final PayrollCycle payrollCycle = DateUtils.toPayrollCycle(date);
			if (payrollCycle == null) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			logger.info(String.format("PayrollPay cycle data passed is {%s} ", payrollCycle));
			final ResponseEntity<PublicSocialSecurityFundResponse> response = payrollReportServices.RunPSSSF(authToken, payrollCycle);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// code
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<PublicSocialSecurityFundResponse>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<PublicSocialSecurityFundResponse>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/payroll-reports-payee.htm", method = RequestMethod.GET)
	public ModelAndView PayrollPayeeFReport() {
		logger.info(String.format("Start Accessing Payeee Report"));
		ModelAndView mv = new ModelAndView("pay-roll/payroll-reports-payee");
		final UserAuthenticationPrincipal authUser = authenticationFacade.getUser();
		mv.addObject("authUser", authUser);
		return mv;
	}
	
	@RequestMapping(value = "/v1/payee-run", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> RunPayee(@Validated @RequestBody HeslbRun payeeRun) {
		logger.info(String.format("Payee run data passed is {%s} ", payeeRun));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (payeeRun == null || payeeRun.getPayrollcycle() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String date = payeeRun.getPayrollcycle();
			if (date == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final PayrollCycle payrollCycle = DateUtils.toPayrollCycle(date);
			if (payrollCycle == null) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			logger.info(String.format("PayrollPay cycle data passed is {%s} ", payrollCycle));
			final ResponseEntity<PAYEResponse> response = payrollReportServices.RunPayee(authToken, payrollCycle);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// code
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<PAYEResponse>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<PAYEResponse>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/payroll-reports-nhif.htm", method = RequestMethod.GET)
	public ModelAndView PayrollNHIFReport() {
		logger.info(String.format("Start Accessing NHIF Report"));
		ModelAndView mv = new ModelAndView("pay-roll/payroll-reports-nhif");
		final UserAuthenticationPrincipal authUser = authenticationFacade.getUser();
		mv.addObject("authUser", authUser);
		return mv;
	}
	
	@RequestMapping(value = "/v1/nhif-run", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> RunNHIF(@Validated @RequestBody HeslbRun nhifRun) {
		logger.info(String.format("NHIF run data passed is {%s} ", nhifRun));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (nhifRun == null || nhifRun.getPayrollcycle() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String date = nhifRun.getPayrollcycle();
			if (date == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final PayrollCycle payrollCycle = DateUtils.toPayrollCycle(date);
			if (payrollCycle == null) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			logger.info(String.format("PayrollPay cycle data passed is {%s} ", payrollCycle));
			final ResponseEntity<HealthInsuranceResponse> response = payrollReportServices.RunNHIF(authToken, payrollCycle);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// code
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<HealthInsuranceResponse>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<HealthInsuranceResponse>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

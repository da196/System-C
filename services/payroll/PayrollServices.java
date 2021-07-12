package tz.go.tcra.hrms.services.payroll;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.payroll.PayrollPay;
import tz.go.tcra.hrms.dto.payroll.Journal;
import tz.go.tcra.hrms.dto.payroll.Payroll;
import tz.go.tcra.hrms.dto.payroll.PayrollCycle;
import tz.go.tcra.hrms.dto.payroll.PayrollType;

@Service
public class PayrollServices implements IPayrollServices {
	private static final Logger logger = Logger.getLogger(PayrollServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<PayrollPay> Create(String authToken, PayrollCycle payrollCycle) {
		logger.info("Create payroll");
		if(payrollCycle!=null) {			
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Create payroll year = {%d} and month = {%d}",year,month));
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PayrollPay> request = new HttpEntity<PayrollPay> (headers);
			final ResponseEntity<PayrollPay> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payroll/processPayrollPerPeriod/"+year+"/"+month, 
					  HttpMethod.GET, 
					  request, 
					  PayrollPay.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Payroll> Get(String authToken, PayrollCycle payrollCycle) {
		logger.info("Get payroll");
		if(payrollCycle!=null) {			
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Get payroll year = {%d} and month = {%d}",year,month));
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Payroll> request = new HttpEntity<Payroll> (headers);
			final ResponseEntity<Payroll> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payroll/getPayrollByPeriod/"+year+"/"+month, 
					  HttpMethod.GET, 
					  request, 
					  Payroll.class);			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Void> SendPayslip(String authToken, PayrollCycle payrollCycle) {
		logger.info("Create payroll");
		if(payrollCycle!=null) {			
			final int day = payrollCycle.getDay();
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Create payroll year = {%d} and month = {%d}",year,month));
			
			StringBuilder paydateBuilder = new StringBuilder();
			paydateBuilder.append(year+"-");
			paydateBuilder.append(month+"-");
			paydateBuilder.append(day);
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PayrollPay> request = new HttpEntity<PayrollPay> (headers);
			final ResponseEntity<Void> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/sendPayslipNotification/sendPaySlipNotification/"+paydateBuilder.toString(), 
					  HttpMethod.GET, 
					  request, 
					  Void.class);			
			return response;
		}
		return null;
	}

}

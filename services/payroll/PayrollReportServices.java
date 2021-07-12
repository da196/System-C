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
import tz.go.tcra.hrms.dto.payroll.HealthInsuranceResponse;
import tz.go.tcra.hrms.dto.payroll.HeslbReportResponse;
import tz.go.tcra.hrms.dto.payroll.Journal;
import tz.go.tcra.hrms.dto.payroll.PAYEResponse;
import tz.go.tcra.hrms.dto.payroll.Payroll;
import tz.go.tcra.hrms.dto.payroll.PayrollCycle;
import tz.go.tcra.hrms.dto.payroll.PayrollType;
import tz.go.tcra.hrms.dto.payroll.PublicSocialSecurityFundResponse;
import tz.go.tcra.hrms.dto.payroll.WorkersCompensationFundResponse;

@Service
public class PayrollReportServices implements IPayrollReportServices {
	private static final Logger logger = Logger.getLogger(PayrollReportServices.class); // log4j
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<Journal> RunJournal(String authToken, PayrollCycle payrollCycle) {
		logger.info("Create journal");
		if(payrollCycle!=null) {			
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Create payroll year = {%d} and month = {%d}",year,month));
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<Journal> request = new HttpEntity<Journal> (headers);
			final ResponseEntity<Journal> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollReports/getPayrollJournal/"+year+"/"+month, 
					  HttpMethod.GET, 
					  request, 
					  Journal.class);			
			return response;
		}
		return null;
	}
	@Override
	public ResponseEntity<HeslbReportResponse> RunHeslb(String authToken, PayrollCycle payrollCycle) {
		logger.info("Create HESLB");
		if(payrollCycle!=null) {			
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Create payroll year = {%d} and month = {%d}",year,month));
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HeslbReportResponse> request = new HttpEntity<HeslbReportResponse> (headers);
			final ResponseEntity<HeslbReportResponse> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollReports/getHeslbReportByMonthAndYear/"+year+"/"+month, 
					  HttpMethod.GET, 
					  request, 
					  HeslbReportResponse.class);		
			System.out.println(response.getBody());
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<WorkersCompensationFundResponse> RunWCF(String authToken, PayrollCycle payrollCycle) {
		logger.info("Create Workers Compesation Fund");
		if(payrollCycle!=null) {			
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Create payroll year = {%d} and month = {%d}",year,month));
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<WorkersCompensationFundResponse> request = new HttpEntity<WorkersCompensationFundResponse> (headers);
			final ResponseEntity<WorkersCompensationFundResponse> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollReports/getWCFPaymentByMonthAndYear/"+year+"/"+month, 
					  HttpMethod.GET, 
					  request, 
					  WorkersCompensationFundResponse.class);		
			System.out.println(response.getBody());
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<PublicSocialSecurityFundResponse> RunPSSSF(String authToken, PayrollCycle payrollCycle) {
		logger.info("Create PSSSF");
		if(payrollCycle!=null) {			
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Create payroll year = {%d} and month = {%d}",year,month));
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PublicSocialSecurityFundResponse> request = new HttpEntity<PublicSocialSecurityFundResponse> (headers);
			final ResponseEntity<PublicSocialSecurityFundResponse> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollReports/getPSSSFPaymentByMonthAndYear/"+year+"/"+month, 
					  HttpMethod.GET, 
					  request, 
					  PublicSocialSecurityFundResponse.class);		
			System.out.println(response.getBody());
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<PAYEResponse> RunPayee(String authToken, PayrollCycle payrollCycle) {
		logger.info("Create Payee");
		if(payrollCycle!=null) {			
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Create payroll year = {%d} and month = {%d}",year,month));
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<PAYEResponse> request = new HttpEntity<PAYEResponse> (headers);
			final ResponseEntity<PAYEResponse> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollReports/getPAYEByMonthAndYear/"+year+"/"+month, 
					  HttpMethod.GET, 
					  request, 
					  PAYEResponse.class);		
			System.out.println(response.getBody());
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<HealthInsuranceResponse> RunNHIF(String authToken, PayrollCycle payrollCycle) {
		logger.info("Create NHIF");
		if(payrollCycle!=null) {			
			final int month = payrollCycle.getMonth();
			final int year = payrollCycle.getYear();	
			logger.info(String.format("Create payroll year = {%d} and month = {%d}",year,month));
			
			final HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			final HttpEntity<HealthInsuranceResponse> request = new HttpEntity<HealthInsuranceResponse> (headers);
			final ResponseEntity<HealthInsuranceResponse> response = restTemplate.exchange(AppConstants.BASE_URL+ 
					"/v1/payrollReports/getNHIFByMonthAndYear/"+year+"/"+month, 
					  HttpMethod.GET, 
					  request, 
					  HealthInsuranceResponse.class);		
			System.out.println(response.getBody());
			return response;
		}
		return null;
	}
}

package tz.go.tcra.hrms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpHeader;
import tz.go.tcra.hrms.dto.EmployeeBankAccount;

@Component
public class EmployeeBankAccountServices implements IEmployeeBankAccountServices {
	
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<EmployeeBankAccount[]> GetAll(int empId) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeBankAccount> request = new HttpEntity<EmployeeBankAccount>(headers);
			ResponseEntity<EmployeeBankAccount[]> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/EmployeeBankAccount/getEmployeeBankAccountByEmpId/"+empId, 
											  HttpMethod.GET, 
											  request, 
											  EmployeeBankAccount[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeBankAccount> AddBankAccount(EmployeeBankAccount bankAccount) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<EmployeeBankAccount> request = new HttpEntity<EmployeeBankAccount>(bankAccount, headers);
			ResponseEntity<EmployeeBankAccount> response = restTemplate.exchange(
											  AppConstants.BASE_URL+"/v1/EmployeeBankAccount/addEmployeeBankAccount", 
											  HttpMethod.POST, 
											  request, 
											  EmployeeBankAccount.class);
			
			return response;
		}
		return null;
	}

}

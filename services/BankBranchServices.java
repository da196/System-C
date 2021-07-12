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
import tz.go.tcra.hrms.dto.BankBranch;

@Component
public class BankBranchServices implements IBankBranchServices {
	
	//Access Token Configurations.
	@Autowired 
	private IAuthenticationFacade authenticationFacade;	
	private String authToken;
		
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<BankBranch[]> GetAll() {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<BankBranch> request = new HttpEntity<BankBranch>(headers);
			ResponseEntity<BankBranch[]> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/bankBranch/getAllBankBranch", 
										  HttpMethod.GET, 
										  request, 
										  BankBranch[].class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<BankBranch> AddBranch(BankBranch branch) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<BankBranch> request = new HttpEntity<BankBranch>(branch, headers);
			ResponseEntity<BankBranch> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/bankBranch/addBankBranch", 
										  HttpMethod.POST, 
										  request, 
										  BankBranch.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<BankBranch> Delete(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<BankBranch> request = new HttpEntity<BankBranch>(headers);
			ResponseEntity<BankBranch> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/bankBranch/deleteBankBranch/"+id, 
										  HttpMethod.DELETE, 
										  request, 
										  BankBranch.class);
			
			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<BankBranch[]> GetBranchesByBankId(int id) {
		if(restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<BankBranch> request = new HttpEntity<BankBranch>(headers);
			ResponseEntity<BankBranch[]> response = restTemplate.exchange(
										AppConstants.BASE_URL+"/v1/bankBranch/getBankBranchByBankId/"+id, 
										  HttpMethod.GET, 
										  request, 
										  BankBranch[].class);
			
			return response;
		}
		return null;
	}

}

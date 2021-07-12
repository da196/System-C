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
import tz.go.tcra.hrms.dto.Currency;
import tz.go.tcra.hrms.dto.EmploymentCategory;
import tz.go.tcra.hrms.dto.SalaryStructure;

@Component
public class SalaryStructureServices implements ISalaryStructureServices {
	// Access Token Configurations.
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	private String authToken;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<SalaryStructure> Create(SalaryStructure salaryStructure) {
		authToken = authenticationFacade.getAuthenticationToken();
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(salaryStructure, headers);
		ResponseEntity<SalaryStructure> response = restTemplate.exchange(
				AppConstants.BASE_URL + "/v1/salarystructure/addSalarystructure", HttpMethod.POST, request,
				SalaryStructure.class);

		return response;
	}

	@Override
	public ResponseEntity<SalaryStructure> AddEmployeeSalaryStructure(SalaryStructure employeeSalaryStructure) {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(employeeSalaryStructure, headers);
			ResponseEntity<SalaryStructure> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/employeesalary/addEmployeesalary", HttpMethod.POST, request,
					SalaryStructure.class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SalaryStructure[]> GetEmployeesSalaryStructure() {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(headers);
			ResponseEntity<SalaryStructure[]> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/employeesalary/getAllEmployeesalary", HttpMethod.GET, request,
					SalaryStructure[].class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<Currency[]> GetAllCurrency() {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<Currency> request = new HttpEntity<Currency>(headers);
			ResponseEntity<Currency[]> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/currency/getAllCurrencies", HttpMethod.GET, request, Currency[].class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SalaryStructure[]> GetemployeeSalaryStructuresMoreDetails() {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(headers);
			ResponseEntity<SalaryStructure[]> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/employeesalary/getAllemployeesalaryv2", HttpMethod.GET, request,
					SalaryStructure[].class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SalaryStructure> EditEmployeeSalaryStructure(SalaryStructure employeeSalaryStructureint,
			int id) {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(employeeSalaryStructureint, headers);
			ResponseEntity<SalaryStructure> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/employeesalary/updateEmployeesalary/" + id, HttpMethod.PUT, request,
					SalaryStructure.class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SalaryStructure> GetSalaryStructureByEmployeeId(int id) {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(headers);
			ResponseEntity<SalaryStructure> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/employeesalary/getEmployeesalaryByEmpId/" + id, HttpMethod.GET,
					request, SalaryStructure.class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SalaryStructure> GetEmployeeSalaryStructureById(int id) {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(headers);
			ResponseEntity<SalaryStructure> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/employeesalary/getEmployeesalary/" + id, HttpMethod.GET, request,
					SalaryStructure.class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SalaryStructure> DeleteEmployeeSalaryStructure(int id) {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(headers);
			ResponseEntity<SalaryStructure> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/employeesalary/deleteEmployeesalary/" + id, HttpMethod.DELETE, request,
					SalaryStructure.class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SalaryStructure[]> GetAll() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(headers);
		ResponseEntity<SalaryStructure[]> response = restTemplate.exchange(
				AppConstants.BASE_URL + "/v1/salarystructure/getAllSalarystructure", HttpMethod.GET, request,
				SalaryStructure[].class);

		return response;
	}

	@Override
	public ResponseEntity<SalaryStructure> GetSalaryStructureById(int id) {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(headers);
			ResponseEntity<SalaryStructure> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/salarystructure/getSalarystructure/" + id, HttpMethod.GET, request,
					SalaryStructure.class);

			return response;
		}
		return null;
	}

	@Override
	public ResponseEntity<SalaryStructure> DeleteSalaryStructureById(int id) {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(headers);
			ResponseEntity<SalaryStructure> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/salarystructure/deleteSalarystructure/" + id, HttpMethod.DELETE,
					request, SalaryStructure.class);

			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<SalaryStructure> EditSalaryStructure(SalaryStructure salaryStructure, int id) {
		if (restTemplate != null) {
			authToken = authenticationFacade.getAuthenticationToken();
			HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
			HttpEntity<SalaryStructure> request = new HttpEntity<SalaryStructure>(salaryStructure, headers);
			ResponseEntity<SalaryStructure> response = restTemplate.exchange(
					AppConstants.BASE_URL + "/v1/salarystructure/updateSalarystructure/" + id, HttpMethod.PUT, request,
					SalaryStructure.class);

			return response;
		}
		return null;
	}


	@Override
	public ResponseEntity<EmploymentCategory[]> GetEmployementCategory() {
		// token
		authToken = authenticationFacade.getAuthenticationToken();
		// headers
		HttpHeaders headers = HttpHeader.setHttpHeader(authToken);
		HttpEntity<EmploymentCategory> request = new HttpEntity<EmploymentCategory>(headers);
		ResponseEntity<EmploymentCategory[]> response = restTemplate.exchange(
				AppConstants.BASE_URL + "/v1/employmentCategory/getAllEmploymentCategory", HttpMethod.GET, request,
				EmploymentCategory[].class);

		return response;
	}

}

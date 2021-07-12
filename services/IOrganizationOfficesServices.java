package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.GeneralSettings;
import tz.go.tcra.hrms.dto.OrganizationOffices;

@Component
public interface IOrganizationOfficesServices {

	//Get organization categories
	ResponseEntity<GeneralSettings[]> GetOrganizationCategory();
	
	//Get organization offices
	ResponseEntity<OrganizationOffices[]> GetOrganizationOffice();
	
	//Add/Create new Office
	ResponseEntity<OrganizationOffices> CreateOffice(OrganizationOffices office);
	
	//Delete Office
	ResponseEntity<OrganizationOffices> DeleteOffice(int id);
}

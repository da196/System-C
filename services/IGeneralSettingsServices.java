package tz.go.tcra.hrms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tz.go.tcra.hrms.dto.GeneralSettings;

@Component
public interface IGeneralSettingsServices {
	
	//Get Salary scales notches
	ResponseEntity<GeneralSettings[]> GetNotches();
	
	//Add Salary scale notches
	ResponseEntity<GeneralSettings> AddNotch(GeneralSettings settings);
	
	//Get Institution type
	ResponseEntity<GeneralSettings[]> GetInstitutionTypes();
	
	//Get Institution type
	ResponseEntity<GeneralSettings[]> GetInstitutionCategory();
	
	//Get relative categories
	ResponseEntity<GeneralSettings[]> GetRelativeCategories();
	
	//Get religions
	ResponseEntity<GeneralSettings[]> GetReligions();
	
	//Add Relative category
	ResponseEntity<GeneralSettings> CreateRelativeCategory(GeneralSettings relativeCategory);
	
	//Delete Relative category
	ResponseEntity<GeneralSettings> DeleteRelativeCategory(int id);
	
	//Add Religion
	ResponseEntity<GeneralSettings> CreateReligion(GeneralSettings religion);
	
	//Delete religion
	ResponseEntity<GeneralSettings> DeleteReligion(int id);
	
	//Get employment status reasons
	ResponseEntity<GeneralSettings[]> GetAllEmploymentStatusReasons();
	
	//Get Salary Notch by ID
	ResponseEntity<GeneralSettings> getSalaryscaleNotch(int id);
	
	//Get Salary Notch by ID
	ResponseEntity<GeneralSettings> deleteSalaryscaleNotch(int id);
	
	ResponseEntity<GeneralSettings> updateSalaryNotch(GeneralSettings generalSettings, int id);

}

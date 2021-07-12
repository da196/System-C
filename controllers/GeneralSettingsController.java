package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.dto.GeneralSettings;
import tz.go.tcra.hrms.dto.MaritalStatus;
import tz.go.tcra.hrms.dto.Salutation;
import tz.go.tcra.hrms.dto.Unit;
import tz.go.tcra.hrms.services.IGeneralSettingsServices;
import tz.go.tcra.hrms.services.IMaritalStatusServices;
import tz.go.tcra.hrms.services.ISalutationServices;
import tz.go.tcra.hrms.services.IUnitServices;

@Controller
public class GeneralSettingsController {

	@Autowired
	private ISalutationServices sServices;
	@Autowired
	private IMaritalStatusServices msServices;
	@Autowired
	private IGeneralSettingsServices generalServices;
	@Autowired
	private IUnitServices unitsServices;
	
	@RequestMapping(value = "/general-settings.htm", method = RequestMethod.GET)
	public ModelAndView GetAll() {
		ResponseEntity<Salutation[]> result = null;
		ResponseEntity<MaritalStatus[]> msResult = null;
		ResponseEntity<GeneralSettings[]> relativeResults = generalServices.GetRelativeCategories();
		ResponseEntity<GeneralSettings[]> religionsResults = generalServices.GetReligions();
		
		if(sServices != null) {
			result = (ResponseEntity<Salutation[]>) sServices.GetAll();
			msResult = msServices.GetAll();
		}
		
		List<Salutation> salutations = null;
		List<MaritalStatus> maritalStatuses = null;
		List<GeneralSettings> relativeCategories = Arrays.asList(relativeResults.getBody());
		List<GeneralSettings> religions = Arrays.asList(religionsResults.getBody());
		if(result.getBody() != null) {
			salutations = Arrays.asList(result.getBody());
			maritalStatuses = Arrays.asList(msResult.getBody());
		}
		
		ModelAndView mv = new ModelAndView("hr-base/general-settings");
		mv.addObject("salutations", salutations);
		mv.addObject("maritalStatuses", maritalStatuses);
		mv.addObject("relativeCategories", relativeCategories);
		mv.addObject("religions", religions);
				
		return mv;
	}
	
	@RequestMapping(value = "/create-relative-category.htm", method = RequestMethod.POST)
	public String CreateRelativeCategory(@ModelAttribute("newRelativeCategory") @RequestBody GeneralSettings relativeCategory) {
		if(generalServices != null) {
				try {
					if(relativeCategory == null || relativeCategory.getName().isEmpty()) {
						return "redirect:/null-values-error.htm";
					}
					ResponseEntity<GeneralSettings> result = generalServices.CreateRelativeCategory(relativeCategory);
					if(result.getStatusCodeValue() != 200) {
						return "redirect:/error.htm";
					}
					return "redirect:/general-settings.htm";
				} catch (Exception e) {
					System.out.print(e.toString());
					e.printStackTrace();
					return "redirect:/error.htm";
				}
		}
		return null;
	}
	
	@RequestMapping(value = "/delete-relative-category.htm/{id}", method = RequestMethod.GET)
	public String DeleteRelativeCategory(@PathVariable("id") int id) {
		if(id>0) {
			ResponseEntity<GeneralSettings> result = generalServices.DeleteRelativeCategory(id);
			if(result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/general-settings.htm";
		}
		return null;
	}
	
	@RequestMapping(value = "/create-religion.htm", method = RequestMethod.POST)
	public String CreateReligion(@ModelAttribute("newReligion") @RequestBody GeneralSettings religion) {
		if(generalServices != null) {
				try {
					if(religion == null || religion.getName().isEmpty()) {
						return "redirect:/null-values-error.htm";
					}
					ResponseEntity<GeneralSettings> result = generalServices.CreateReligion(religion);
					if(result.getStatusCodeValue() != 200) {
						return "redirect:/error.htm";
					}
					return "redirect:/general-settings.htm";
				} catch (Exception e) {
					System.out.print(e.toString());
					e.printStackTrace();
					return "redirect:/error.htm";
				}
		}
		return null;
	}
	
	@RequestMapping(value = "/delete-religion.htm/{id}", method = RequestMethod.GET)
	public String DeleteReligion(@PathVariable("id") int id) {
		if(id>0) {
			ResponseEntity<GeneralSettings> result = generalServices.DeleteReligion(id);
			if(result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/general-settings.htm";
		}
		return null;
	}
	
	//Get all responsible
	@RequestMapping(value = "/get-all-unitsByAjax")
	public @ResponseBody Object GetAllUnitsByAjax() {
		final ResponseEntity<Unit[]> result = unitsServices.GetDirAndUnits();
		if(result.getBody() != null) {
			final List<Unit> units = Arrays.asList(result.getBody());
			return units;
		}
		return null;
	}

}

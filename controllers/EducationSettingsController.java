package tz.go.tcra.hrms.controllers;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tz.go.tcra.hrms.config.Utility;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.dto.CertificationCategory;
import tz.go.tcra.hrms.dto.City;
import tz.go.tcra.hrms.dto.EducationCourse;
import tz.go.tcra.hrms.dto.EducationInstitution;
import tz.go.tcra.hrms.dto.EducationLevel;
import tz.go.tcra.hrms.dto.EmployeeCertification;
import tz.go.tcra.hrms.dto.EmployeeEducation;
import tz.go.tcra.hrms.dto.EmployeeShortCourse;
import tz.go.tcra.hrms.dto.GeneralSettings;
import tz.go.tcra.hrms.dto.Location;
import tz.go.tcra.hrms.services.ICityServices;
import tz.go.tcra.hrms.services.IEducationCourseServices;
import tz.go.tcra.hrms.services.IEducationInstitutionServices;
import tz.go.tcra.hrms.services.IEducationLevelServices;
import tz.go.tcra.hrms.services.IEmployeeEducationServices;
import tz.go.tcra.hrms.services.IGeneralSettingsServices;
import tz.go.tcra.hrms.services.ILocationServices;

@Controller
public class EducationSettingsController {
	
	private static final Logger logger = Logger.getLogger(EducationSettingsController.class); // log4j

	@Autowired
	private IEducationLevelServices eduLevelServices;
	@Autowired
	private IEducationInstitutionServices eduInstitutionServices;
	@Autowired
	private IEducationCourseServices courseServices;
	@Autowired
	private IGeneralSettingsServices generalServices;
	@Autowired
	private IEducationInstitutionServices institutionServices;
	@Autowired
	private ICityServices cityServices;
	@Autowired
	private ILocationServices locationServices;
	@Autowired
	private IEmployeeEducationServices employeeEducation;
	
	@RequestMapping(value = "/education-settings.htm", method = RequestMethod.GET)
	public ModelAndView educationSettings() {
		
		ResponseEntity<EducationLevel[]> eduLevelResult = null;
		ResponseEntity<EducationInstitution[]> eduInstitutionResult = null;
		ResponseEntity<EducationCourse[]> courseResult = null;
		ResponseEntity<GeneralSettings[]> institutionTypeResult = null;
		ResponseEntity<GeneralSettings[]> institutionCategoryResult = null;
		ResponseEntity<City[]> cityResult = null;
		ResponseEntity<Location[]> locationResult = null;
		if(eduLevelServices != null) {
			eduLevelResult = eduLevelServices.GetAll();
			eduInstitutionResult = eduInstitutionServices.GetAll();
			courseResult = courseServices.GetAll();
			institutionTypeResult = generalServices.GetInstitutionTypes();
			institutionCategoryResult = generalServices.GetInstitutionCategory();
			cityResult = cityServices.GetAll();
			locationResult = locationServices.GetAll();
		}
		
		List<EducationLevel> educationLevels = null;
		List<EducationInstitution> institutions = null;
		List<EducationCourse> courses = null;
		List<GeneralSettings> institutionTypes = null;
		List<GeneralSettings> institutionCategories = null;
		List<City> cities = null;
		List<Location> locations = null;
		
		if(eduLevelResult.getBody() != null) {
			educationLevels = Arrays.asList(eduLevelResult.getBody());
			institutions = Arrays.asList(eduInstitutionResult.getBody());
			courses = Arrays.asList(courseResult.getBody());
			institutionTypes = Arrays.asList(institutionTypeResult.getBody());
			institutionCategories = Arrays.asList(institutionCategoryResult.getBody());
			cities = Arrays.asList(cityResult.getBody());
			locations = Arrays.asList(locationResult.getBody());
		}
		
		ModelAndView mv = new ModelAndView("hr-base/education-settings");
		mv.addObject("educationLevels", educationLevels);
		mv.addObject("institutions", institutions);
		mv.addObject("courses", courses);
		mv.addObject("institutionTypes", institutionTypes);
		mv.addObject("institutionCategories", institutionCategories);
		mv.addObject("cities", cities);
		mv.addObject("locations", locations);
		return mv;
	}
	
	@RequestMapping(value = "/create-education-level.htm", method = RequestMethod.POST)
	public String addEducationLevel(@ModelAttribute("newEducationLevel") @RequestBody EducationLevel level) {
		if(level == null || level.getAbbreviation().isEmpty() || level.getName().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		try {
			ResponseEntity<EducationLevel> result = eduLevelServices.Create(level);
			if(result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/education-settings";
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}
		
	}
	
	@RequestMapping(value = "/create-education-course.htm", method = RequestMethod.POST)
	public String addEducationCourse(@ModelAttribute("newEducationCourse") @RequestBody EducationCourse course) {
		if(course == null || course.getAbbreviation().isEmpty() || course.getName().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		try {
			ResponseEntity<EducationCourse> result = courseServices.Create(course);
			if(result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/education-settings.htm";
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}
		
	}
	
	@RequestMapping(value = "/create-institution.htm", method = RequestMethod.POST)
	public String addInstitution(@ModelAttribute("newInstitution") @RequestBody EducationInstitution institution) {
		if(institution == null || institution.getName().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		try {
			ResponseEntity<EducationInstitution> result = institutionServices.Create(institution);
			if(result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/education-settings.htm";
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}
		
	}
	
	/************************** Education ******************************************/
	
	@RequestMapping(value = "/education-certificates.htm", method = RequestMethod.GET)
	public String EducationCertificates() {
		return "hr-base/education-certificates";
	}
	
	//Get education certificates ajax
	@RequestMapping(value = "/get-education-cerificatesAjax")
	public @ResponseBody Object GetEducationCertificatesAjax() {
		final ResponseEntity<EmployeeEducation[]> result = courseServices.GetAllEducationCertificates();
		if(result.getBody() != null) {
			final List<EmployeeEducation> certificates = Arrays.asList(result.getBody());
			return certificates;
		}
		return null;
	}
	
	//Get institutions
	@RequestMapping(value = "/get-institutionsAjax")
	public @ResponseBody Object GetInstitutionsAjax() {
		final ResponseEntity<EducationInstitution[]> result = institutionServices.GetAll();
		if(result.getBody() != null) {
			final List<EducationInstitution> institutions = Arrays.asList(result.getBody());
			return institutions;
		}
		return null;
	}
	
	//Get education levels
	@RequestMapping(value = "/get-education-levelsAjax")
	public @ResponseBody Object GetEducationLevelsAjax() {
		final ResponseEntity<EducationLevel[]> result = eduLevelServices.GetAll();
		if(result.getBody() != null) {
			final List<EducationLevel> educationLevels = Arrays.asList(result.getBody());
			return educationLevels;
		}
		return null;
	}
	
	//Get non-approved educations ajax
	@RequestMapping(value = "/get-nonApproved-educationsAjax")
	public @ResponseBody Object GetNonApprovedEducationsAjax() {
		final ResponseEntity<EmployeeEducation[]> result = courseServices.GetUnApprovedEducations();
		if(result.getBody() != null) {
			final List<EmployeeEducation> unApprovedEdu = Arrays.asList(result.getBody());
			return unApprovedEdu;
		}
		return null;
	}
	
	//Get education courses by ajax
	@RequestMapping(value = "/get-education-coursesAjax")
	public @ResponseBody Object GetEducationCoursesAjax() {
		final ResponseEntity<EducationCourse[]> result = courseServices.GetAll();
		if(result.getBody() != null) {
			final List<EducationCourse> courses = Arrays.asList(result.getBody());
			return courses;
		}
		return null;
	}
	
	//Post/Add employee education by ajax
	@RequestMapping(value = "/add-employee-educationAjax", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> AddEmployeeEducation(@RequestParam("educationCertificationFile") MultipartFile certificateFile, 
			@ModelAttribute("newEducation") @RequestBody EmployeeEducation education) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			long timestampVariable = timestamp.getTime();
			
			String fileExtention = FilenameUtils.getExtension(certificateFile.getOriginalFilename());
			String fileName = Utility.trimFileExtension(certificateFile.getOriginalFilename());
			String newFileName = fileName + "_" + timestampVariable + "." + fileExtention;
			String fileURL = AppConstants.UPLOAD_DIRECTORY + newFileName;
			String fileURI = AppConstants.URL + newFileName;
			
			//Setting URI
			education.setUri(fileURI);
			
			if (fileExtention.toLowerCase().equals("pdf") || fileExtention.toLowerCase().equals("png")
					|| fileExtention.toLowerCase().equals("jpeg") || fileExtention.toLowerCase().equals("jpg")) {

				boolean isFileUpload = Utility.uploadFile(certificateFile, fileURL);
			}
			final ResponseEntity<EmployeeEducation> result = employeeEducation.AddEducation(education);

		  }
		  catch (Exception e) {
		    System.out.println(e.getMessage());
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  }
		  
		  return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/************************** Short Courses ******************************************/
	
	@RequestMapping(value = "/short-courses.htm", method = RequestMethod.GET)
	public String ShortCourses() {
		return "hr-base/short-courses";
	}
	
	//Get short courses ajax
	@RequestMapping(value = "/get-short-coursesAjax")
	public @ResponseBody Object GetShortCoursesAjax() {
		final ResponseEntity<EmployeeShortCourse[]> result = courseServices.GetAllShortCourses();
		if(result.getBody() != null) {
			final List<EmployeeShortCourse> shortCourses = Arrays.asList(result.getBody());
			return shortCourses;
		}
		return null;
	}
	
	//Get short courses ajax
	@RequestMapping(value = "/get-noApproved-short-coursesAjax")
	public @ResponseBody Object GetNonApprovedShortCoursesAjax() {
		final ResponseEntity<EmployeeShortCourse[]> result = courseServices.GetNonApprovedShortCourses();
		if(result.getBody() != null) {
			final List<EmployeeShortCourse> nonApprovedShortCourses = Arrays.asList(result.getBody());
			return nonApprovedShortCourses;
		}
		return null;
	}
	
	//Post/Add employee short course by ajax
	@RequestMapping(value = "/add-employee-short-courseAjax", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> AddEmployeeShortCourse(@RequestParam("shortCourseFile") MultipartFile certificateFile, 
			@ModelAttribute("newShortCourseModel") @RequestBody EmployeeShortCourse shortCourse) {
			
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			long timestampVariable = timestamp.getTime();
			
			String fileExtention = FilenameUtils.getExtension(certificateFile.getOriginalFilename());
			String fileName = Utility.trimFileExtension(certificateFile.getOriginalFilename());
			String newFileName = fileName + "_" + timestampVariable + "." + fileExtention;
			String fileURL = AppConstants.UPLOAD_DIRECTORY + newFileName;
			String fileURI = AppConstants.URL + newFileName;
			
			//Setting URI
			shortCourse.setUri(fileURI);
			
			if (fileExtention.toLowerCase().equals("pdf") || fileExtention.toLowerCase().equals("png")
					|| fileExtention.toLowerCase().equals("jpeg") || fileExtention.toLowerCase().equals("jpg")) {

				boolean isFileUpload = Utility.uploadFile(certificateFile, fileURL);
			}
			final ResponseEntity<EmployeeShortCourse> result = employeeEducation.AddShortCourse(shortCourse);

		  }
		  catch (Exception e) {
		    System.out.println(e.getMessage());
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  }
		  
		  return new ResponseEntity<>(HttpStatus.OK);

	}
	
	/************************** Certifications ******************************************/
	
	@RequestMapping(value = "/certifications.htm", method = RequestMethod.GET)
	public String Certifications() {
		return "hr-base/certifications";
	}
	
	//Get certifications ajax
	@RequestMapping(value = "/get-certificationsAjax")
	public @ResponseBody Object GetCertificationsAjax() {
		final ResponseEntity<EmployeeCertification[]> result = courseServices.GetAllCertification();
		if(result.getBody() != null) {
			final List<EmployeeCertification> certifications = Arrays.asList(result.getBody());
			return certifications;
		}
		return null;
	}
	
	//Get non approved certifications ajax
	@RequestMapping(value = "/get-noApproved-certificationsAjax")
	public @ResponseBody Object GetNonApprovedCertificationssAjax() {
		final ResponseEntity<EmployeeCertification[]> result = courseServices.GetNonApprovedCertification();
		if(result.getBody() != null) {
			final List<EmployeeCertification> nonApprovedCertifications = Arrays.asList(result.getBody());
			return nonApprovedCertifications;
		}
		return null;
	}
	
	//Get certification category ajax
	@RequestMapping(value = "/get-certification-categoryAjax")
	public @ResponseBody Object GetCertificationCategoryAjax() {
		final ResponseEntity<CertificationCategory[]> result = employeeEducation.GetCertificationCategory();
		if(result.getBody() != null) {
			final List<CertificationCategory> categories = Arrays.asList(result.getBody());
			return categories;
		}
		return null;
	}
	
	//Post/Add employee certification by ajax
	@RequestMapping(value = "/add-employee-certificationAjax", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> AddEmployeeCertification(@RequestParam("certificationFile") MultipartFile certificateFile, 
			@ModelAttribute("newCertificationModel") @RequestBody EmployeeCertification certification) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			long timestampVariable = timestamp.getTime();
			
			String fileExtention = FilenameUtils.getExtension(certificateFile.getOriginalFilename());
			String fileName = Utility.trimFileExtension(certificateFile.getOriginalFilename());
			String newFileName = fileName + "_" + timestampVariable + "." + fileExtention;
			String fileURL = AppConstants.UPLOAD_DIRECTORY + newFileName;
			String fileURI = AppConstants.URL + newFileName;
			
			//Setting URI
			certification.setUri(fileURI);
			
			if (fileExtention.toLowerCase().equals("pdf") || fileExtention.toLowerCase().equals("png")
					|| fileExtention.toLowerCase().equals("jpeg") || fileExtention.toLowerCase().equals("jpg")) {

				boolean isFileUpload = Utility.uploadFile(certificateFile, fileURL);
			}
			final ResponseEntity<EmployeeCertification> result = employeeEducation.AddCertification(certification);

		  }
		  catch (Exception e) {
		    System.out.println(e.getMessage());
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  }
		  
		  return new ResponseEntity<>(HttpStatus.OK);		
	}
	
}

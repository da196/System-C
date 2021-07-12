package tz.go.tcra.hrms.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.dto.HrBaseReport;
import tz.go.tcra.hrms.dto.ReportsPreDefinitions;
import tz.go.tcra.hrms.services.IHrBaseReportServices;

@Controller
public class HrBaseReportsController {
	
	@Autowired
	private IHrBaseReportServices hrReportServices;

	@RequestMapping(value = "/hr-base-reports.htm")
	public String hrBaseReports() {
		
		return "hr-base/hr-base-reports";
	}
	
	@RequestMapping(value = "/gender-distribution-by-location.htm")
	public ModelAndView GenderDistributionByLocation() {
		ResponseEntity<HrBaseReport> distributionByLocation = hrReportServices.EmployeeDistributionByLocation();
		
		List<ReportsPreDefinitions> distByLocations = distributionByLocation.getBody().getEmployeeHeadCountDistributionlist();
		HrBaseReport preDefinitions = distributionByLocation.getBody();
		
		ModelAndView mv = new ModelAndView("hr-base/reports/gender-distribution-by-location");
		mv.addObject("distByLocations", distByLocations);
		mv.addObject("male", preDefinitions.getTotalmale());
		mv.addObject("female", preDefinitions.getTotalfemale());
		mv.addObject("total", preDefinitions.getTotalnumber());
		
		return mv;
	}
	
	// ------------------------------------------------------------
	// JSON API
	// ------------------------------------------------------------
	@RequestMapping(value = "/gender-distribution-by-locationAjax")
	public @ResponseBody Object GetGenderDistributionByLocation() {
		final ResponseEntity<HrBaseReport> distributionByLocation = hrReportServices.EmployeeDistributionByLocation();
		if(distributionByLocation.getBody() !=null) {
			final List<ReportsPreDefinitions> employeesInLocations  = distributionByLocation.getBody().getEmployeeHeadCountDistributionlist();
			return employeesInLocations;
		}
		return null;
	}
	
	@RequestMapping(value = "/gender-distribution-percentagewise.htm")
	public ModelAndView GenderDistributionPercentagewise() {
		ResponseEntity<HrBaseReport> distributionByLocation = hrReportServices.EmployeeDistributionByLocation();
		
		HrBaseReport preDefinitions = distributionByLocation.getBody();
		
		ModelAndView mv = new ModelAndView("hr-base/reports/gender-distribution-percentagewise");
		mv.addObject("male", preDefinitions.getTotalmale());
		mv.addObject("female", preDefinitions.getTotalfemale());
		mv.addObject("total", preDefinitions.getTotalnumber());
		
		return mv;
	}
	
	@RequestMapping(value = "/age-analysis-top-positions.htm")
	public ModelAndView AgeDistributionTopStaff() {
		ResponseEntity<HrBaseReport> resultDistributionAgeTopStaff = hrReportServices.AgeDistributionTopStaff();
		
		List<ReportsPreDefinitions> ageDistTopStaffs  = resultDistributionAgeTopStaff.getBody().getAgeGroupdetails();
		HrBaseReport preDefinitions = resultDistributionAgeTopStaff.getBody();
		
		ModelAndView mv = new ModelAndView("hr-base/reports/age-analysis-top-positions");
		mv.addObject("ageDistTopStaffs", ageDistTopStaffs);
		mv.addObject("directors", preDefinitions.getTotaldirectors());
		mv.addObject("heads", preDefinitions.getTotalheads());
		mv.addObject("totals", preDefinitions.getTotaltopstaff());
		
		return mv;
	}
	
	// ------------------------------------------------------------
	// JSON API
	// ------------------------------------------------------------
	@RequestMapping(value = "age-analysis-top-positionsAjax")
	public @ResponseBody Object Get() {
		final ResponseEntity<HrBaseReport> resultDistributionAgeTopStaff = hrReportServices.AgeDistributionTopStaff();
		if(resultDistributionAgeTopStaff!=null && resultDistributionAgeTopStaff.getBody()!=null) {
			final List<ReportsPreDefinitions> ageDistTopStaffs  = resultDistributionAgeTopStaff.getBody().getAgeGroupdetails();
			return ageDistTopStaffs;
		}
		return null;
	}
	
	@RequestMapping(value = "/gender-distribution-top-positions.htm")
	public ModelAndView GenderDistributionTopStaff() {
		ResponseEntity<HrBaseReport> resultDistributionGenderTopStaff = hrReportServices.TopPositionsGenderDistribution();
		
		List<HrBaseReport> genderDistTopStaffs = null;
		if(resultDistributionGenderTopStaff.getBody() != null) {
			 genderDistTopStaffs  = Arrays.asList(resultDistributionGenderTopStaff.getBody());
		}		
		
		ModelAndView mv = new ModelAndView("hr-base/reports/gender-distribution-top-positions");
		mv.addObject("genderDistTopStaffs", genderDistTopStaffs);
		
		return mv;
	}
	
	// ------------------------------------------------------------
	// JSON API
	// ------------------------------------------------------------
	@RequestMapping(value = "/gender-distribution-top-positionsAjax")
	public @ResponseBody Object GetGenderDistTopPositions() {
		final ResponseEntity<HrBaseReport> resultDistributionGenderTopStaff = hrReportServices.TopPositionsGenderDistribution();
		if(resultDistributionGenderTopStaff.getBody() != null) {
			final HrBaseReport genderDistTopStaffs  = resultDistributionGenderTopStaff.getBody();
			return genderDistTopStaffs;
		}
		return null;
	}
		
		@RequestMapping(value = "/employee-distribution-per-directorate.htm")
		public ModelAndView EmployeeDistributionPerDirectorate() {
			ResponseEntity<HrBaseReport> resultDistributionByDictorate = hrReportServices.GetEmployeeDistributionByDirectorate();
			
			List<ReportsPreDefinitions> empInDirectorates = null;
			if(resultDistributionByDictorate.getBody().getEmployeeHeadCountDistributionperDirectoratelist() != null) {
				empInDirectorates = resultDistributionByDictorate.getBody().getEmployeeHeadCountDistributionperDirectoratelist();
			}		
			
			ModelAndView mv = new ModelAndView("hr-base/reports/employee-distribution-per-directorate");
			mv.addObject("empInDirectorates", empInDirectorates);
			mv.addObject("totals", resultDistributionByDictorate.getBody().getTotalnumberg());
			
			return mv;
		}
		
		// ------------------------------------------------------------
		// JSON API
		// ------------------------------------------------------------
		@RequestMapping(value = "/employee-distribution-per-directorateAjax")
		public @ResponseBody Object GetEmployeePerDirectorateAjax() {
			final ResponseEntity<HrBaseReport> resultDistributionByDictorate = hrReportServices.GetEmployeeDistributionByDirectorate();
			
			List<ReportsPreDefinitions> empInDirectorates = null;
			if(resultDistributionByDictorate.getBody().getEmployeeHeadCountDistributionperDirectoratelist() != null) {
				empInDirectorates = resultDistributionByDictorate.getBody().getEmployeeHeadCountDistributionperDirectoratelist();
			}
			
			return empInDirectorates;
		}
		
		@RequestMapping(value = "/distribution-per-directorate-percentagewise.htm")
		public String EmployeeDistributionPerDirectoratePercentagewise() {		
			
			return "hr-base/reports/distribution-per-directorate-percentagewise";
		}
		
		
		// ------------------------------------------------------------
		// JSON API
		// ------------------------------------------------------------
		@RequestMapping(value = "/distribution-per-directorate-percentagewiseAjax")
		public @ResponseBody Object GetEmployeeDistPerDirectoratePercentagewise() {
			final ResponseEntity<HrBaseReport> resultDistributionByDictorate = hrReportServices.GetEmployeeDistributionByDirectorate();
			if(resultDistributionByDictorate.getBody().getEmployeeHeadCountDistributionperDirectoratelist() != null) {
				final List<ReportsPreDefinitions> empInDirectorates = resultDistributionByDictorate.getBody().getEmployeeHeadCountDistributionperDirectoratelist();
				return empInDirectorates;
			}
			return null;
		}
			
		@RequestMapping(value = "/age-average-by-directorates.htm")
		public ModelAndView AgeAverageByDirectorates() {
			ResponseEntity<HrBaseReport> resultDistributionByDictorate = hrReportServices.GetageAveragePerDirectorate();
			
			List<ReportsPreDefinitions> ageAvPerDirectorates = null;
			if(resultDistributionByDictorate.getBody() != null) {
				ageAvPerDirectorates  = resultDistributionByDictorate.getBody().getEmployeeAgeAverageperDirectoratelist();
			}				
			
			ModelAndView mv = new ModelAndView("hr-base/reports/age-average-by-directorates");
			mv.addObject("ageAvPerDirectorates", ageAvPerDirectorates);
			
			return mv;
		}
		
		@RequestMapping(value = "/number-of-staff-by-age-groups.htm")
		public ModelAndView NumberOfStaffByAgeGroup() {
			ResponseEntity<HrBaseReport> resultStaffByAgeGroups = hrReportServices.GetNumberOfStaffByAgeGroups();
			
			List<ReportsPreDefinitions> ageGroups = null;
			if(resultStaffByAgeGroups.getBody() != null) {
				ageGroups  = resultStaffByAgeGroups.getBody().getEmployeeStaffDistributionByAgelist();
			}				
			
			ModelAndView mv = new ModelAndView("hr-base/reports/number-of-staff-by-age-groups");
			mv.addObject("ageGroups", ageGroups);
			
			return mv;
		}
		
		// ------------------------------------------------------------
		// JSON API
		// ------------------------------------------------------------
		@RequestMapping(value = "/number-of-staff-by-age-groupsAjax")
		public @ResponseBody Object GetNumberOfStaffBtAgeAjax() {
			final ResponseEntity<HrBaseReport> resultStaffByAgeGroups = hrReportServices.GetNumberOfStaffByAgeGroups();
			if(resultStaffByAgeGroups.getBody() != null) {
				final List<ReportsPreDefinitions> ageGroups =  resultStaffByAgeGroups.getBody().getEmployeeStaffDistributionByAgelist();
				return ageGroups;
			}
			return null;
		}
		
		@RequestMapping(value = "/staff-distribution-by-agegroups-per-directorates.htm")
		public String StaffDistributionByAgeGroupsPerDirectorates() {		
			
			return "hr-base/reports/staff-distribution-by-agegroups-per-directorates";
		}
		
		// ------------------------------------------------------------
		// JSON API
		// ------------------------------------------------------------
		@RequestMapping(value = "/staff-distribution-by-agegroups-per-directoratesAjax")
		public @ResponseBody Object GetStaffDistributionByAgegroupsPerDirectorates() {
			final ResponseEntity<HrBaseReport> resultStaffDistByAgeInDir = hrReportServices.StaffDistributionByAgeGroupsPerDirectorates();
			if(resultStaffDistByAgeInDir.getBody() != null) {
				final HrBaseReport staffDistByAgeInDir = resultStaffDistByAgeInDir.getBody();
				return staffDistByAgeInDir;
			}
			return null;
		}
		
		@RequestMapping(value = "/employees-by-employment-exit-statuses.htm")
		public String EmployeeExitStatuses() {		
			
			return "hr-base/reports/employees-by-employment-exit-statuses";
		}
		
		// ------------------------------------------------------------
		// JSON API
		// ------------------------------------------------------------
		@RequestMapping(value = "/employees-by-employment-exit-statusesAjax")
		public @ResponseBody Object GetEmployeeExitStatuses() {
			final ResponseEntity<HrBaseReport> resultExitStatuses = hrReportServices.EmployeesByEmployemntExitStatus();
			if(resultExitStatuses.getBody() != null) {
				final HrBaseReport exitStatuses = resultExitStatuses.getBody();
				return exitStatuses;
			}
			return null;
		}
			
}

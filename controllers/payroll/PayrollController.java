package tz.go.tcra.hrms.controllers.payroll;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.Unit;
import tz.go.tcra.hrms.dto.UserAuthenticationPrincipal;
import tz.go.tcra.hrms.dto.payroll.Journal;
import tz.go.tcra.hrms.dto.payroll.JournalRun;
import tz.go.tcra.hrms.dto.payroll.Payroll;
import tz.go.tcra.hrms.dto.payroll.PayrollCycle;
import tz.go.tcra.hrms.dto.payroll.PayrollPay;
import tz.go.tcra.hrms.dto.payroll.PayrollRun;
import tz.go.tcra.hrms.dto.payroll.PayrollTable;
import tz.go.tcra.hrms.dto.payroll.PayrollTableRow;
import tz.go.tcra.hrms.dto.payroll.PayslipSend;
import tz.go.tcra.hrms.services.IEmployeeServices;
import tz.go.tcra.hrms.services.IUnitServices;
import tz.go.tcra.hrms.services.payroll.IPayrollExcelServices;
import tz.go.tcra.hrms.services.payroll.IPayrollReportServices;
import tz.go.tcra.hrms.services.payroll.IPayrollServices;
import tz.go.tcra.hrms.utils.DateUtils;
import tz.go.tcra.hrms.utils.ExcelHelper;
import tz.go.tcra.hrms.utils.FileHelper;

@Controller
public class PayrollController {
	private static final Logger logger = Logger.getLogger(PayrollController.class); // log4j

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private IPayrollServices payrollServices;
	@Autowired
	private IEmployeeServices employeeServices;
	@Autowired
	private IUnitServices unitServices;
	@Autowired
	private IPayrollExcelServices payrollExcelServices;
	@Autowired
	private IPayrollReportServices payrollReportServices;

	@RequestMapping(value = "/payroll-run.htm", method = RequestMethod.GET)
	public ModelAndView Run() {
		// initialize
		// employees
		List<Employee> _employees = null;
		if (employeeServices != null) {
			final ResponseEntity<Employee[]> employees = employeeServices.GetAllEmployeesLessDetails();
			if (employees != null && employees.getBody() != null) {
				_employees = Arrays.asList(employees.getBody());
			}
		}
		// unit
		List<Unit> _units = null;
		if (unitServices != null) {
			final ResponseEntity<Unit[]> units = unitServices.GetAll();
			if (units != null && units.getBody() != null) {
				_units = Arrays.asList(units.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-run");
		// add model data
		mv.addObject("employees", _employees);
		mv.addObject("units", _units);

		return mv;
	}

	@RequestMapping(value = "/payroll-run-details.htm/{year}/{month}", method = RequestMethod.GET)
	public ModelAndView Details(@PathVariable("year") int year, @PathVariable("month") int month) {
		logger.info(String.format("Payroll details - data passed is {%d and %d} ", year, month));
		// initialize
		// payroll
		final String authToken = authenticationFacade.getAuthenticationToken();
		ModelAndView mv = null;
		if (year > 0 && month > 0) {
			PayrollCycle payrollCycle = new PayrollCycle();
			payrollCycle.setYear(year);
			payrollCycle.setMonth(month);
			final ResponseEntity<Payroll> response = payrollServices.Get(authToken, payrollCycle);
			if (response != null) {
				logger.info(String.format("Payroll details - response is {%s} ", response.toString()));
				Payroll payroll = response.getBody();
				logger.info(String.format("Payroll details - body is {%s} ", payroll.toString()));
				mv = new ModelAndView("payroll-transactions/payroll-run-details");
				// add model data
				mv.addObject("payroll", payroll);
				return mv;
			}
		}
		mv = new ModelAndView("common/error");
		return mv;
	}

	@GetMapping(value = "/payroll-run-details-download.excel/{fileName}")
	public ResponseEntity<ByteArrayResource> DownloadExcel(@PathVariable("fileName")String fileName) throws Exception {
		try {
			// read saved excel file 
			final String destinationFileName = fileName;
			final File destinationDirectory = new File(System.getProperty("java.io.tmpdir"));
			final File destination = new File(destinationDirectory.getAbsolutePath() + File.separator+"HRMS"+File.separator +"Payroll"+File.separator+"Data"+File.separator+ destinationFileName);
			if(!destination.exists() && !destination.isDirectory()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			// add saved excel file to input stream
			final FileInputStream in = new FileInputStream(destination);
			HSSFWorkbook workbook = ExcelHelper.createWorkBook2003(in); // creates the workbook
			// add excel file output stream
			final ByteArrayOutputStream stream = new ByteArrayOutputStream();
			
			// response
			// header
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "force-download"));
			header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
			// body
			workbook.write(stream);
			workbook.close();
			return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()), header, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/payroll-send-payslip.htm", method = RequestMethod.GET)
	public ModelAndView SendPayslip() {
		// initialize
		// employees
		List<Employee> _employees = null;
		if (employeeServices != null) {
			final ResponseEntity<Employee[]> employees = employeeServices.GetAllEmployeesLessDetails();
			if (employees != null && employees.getBody() != null) {
				_employees = Arrays.asList(employees.getBody());
			}
		}
		// unit
		List<Unit> _units = null;
		if (unitServices != null) {
			final ResponseEntity<Unit[]> units = unitServices.GetAll();
			if (units != null && units.getBody() != null) {
				_units = Arrays.asList(units.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("payroll-transactions/payroll-payslip-send");
		// add model data
		mv.addObject("employees", _employees);
		mv.addObject("units", _units);

		return mv;
	}

	@RequestMapping(value = "/payroll-journal.htm", method = RequestMethod.GET)
	public ModelAndView PayrollJournal() {
		final String authToken = authenticationFacade.getAuthenticationToken();
		final UserAuthenticationPrincipal authUser = authenticationFacade.getUser();
		// initialize
		// employees
		List<Employee> _employees = null;
		if (employeeServices != null) {
			final ResponseEntity<Employee[]> employees = employeeServices.GetAllEmployeesLessDetails();
			if (employees != null && employees.getBody() != null) {
				_employees = Arrays.asList(employees.getBody());
			}
		}
		// unit
		List<Unit> _units = null;
		if (unitServices != null) {
			final ResponseEntity<Unit[]> units = unitServices.GetAll();
			if (units != null && units.getBody() != null) {
				_units = Arrays.asList(units.getBody());
			}
		}
		
		//ModelAndView mv = new ModelAndView("pay-roll/payroll-reports-journal-result");
		ModelAndView mv = new ModelAndView("pay-roll/payroll-reports-journal");
		// add model data
		mv.addObject("employees", _employees);
		mv.addObject("units", _units);
		mv.addObject("authUser", authUser);

		return mv;
	}
	

	// --------------------------------------------------
	// - Ajax Calls
	// --------------------------------------------------
	@RequestMapping(value = "/v1/payroll-run-details.excel", 
			method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> Excel(@Validated @RequestBody PayrollTable payrollTable) {
		logger.info(String.format("Payroll dataTable passed is {%s} ", payrollTable));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if(authToken==null) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			if (payrollTable == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			final List<PayrollTableRow> rows = payrollTable.getRows();
			final List<String> headers = payrollTable.getHeaders();
			final String paydate = payrollTable.getPaydate();
			if (rows == null || headers == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			// 1. init
			// GET
			// Payroll - excel template
			final File source = ResourceUtils.getFile("classpath:Payroll/Template/PAYROLL_TEMPLATE-v1.0.xls");
			if(!source.exists() && !source.isDirectory()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}	
			// CREATE - in os temp folder
			// Payroll - excel doc
			// file naming
			final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
			final Date dateCurrent = new Date();
			final String dateCurrentFormatted = dateFormatter.format(dateCurrent);
			final long nanoTime = System.nanoTime();
			final String destinationFileName = "PAYROLL_"+dateCurrentFormatted+"_"+nanoTime+".xls";
			// directory
			final File destinationDirectory = new File(System.getProperty("java.io.tmpdir"));
			final File destination = new File(destinationDirectory.getAbsolutePath() + File.separator+"HRMS"+File.separator +"Payroll"+File.separator+"Data"+File.separator+ destinationFileName);
			if(!destination.exists()) {
				FileUtils.touch(destination);
			}
		    // 2. load data to excel
		    final int offset = 18; // zero index, start at row 19
		    final Workbook workBookPayroll = payrollExcelServices.loadExcel(paydate,rows,offset,source,destination);
		    if(workBookPayroll==null) {
		    	logger.info(String.format("Failed to load data to payroll excel file starting at row {%d} ", offset));
		    }		    
		    final int sheetCount = workBookPayroll.getNumberOfSheets();
			logger.info(String.format("Succcessfully loaded data to payroll excel file starting at row = {%d}, total sheet = {%d}, excel path is {%s} ", offset,sheetCount,destination.getPath()));
			return new ResponseEntity<String>(destination.getName(), HttpStatus.OK); // return file name
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/payroll-run-details-xx.excel", 
			method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> ExcelXX(@Validated @RequestBody PayrollTable payrollTable) {
		logger.info(String.format("Payroll dataTable passed is {%s} ", payrollTable));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if(authToken==null) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			if (payrollTable == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			final List<PayrollTableRow> rows = payrollTable.getRows();
			final List<String> headers = payrollTable.getHeaders();
			if (rows == null || headers == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			// GET
			// Payroll - excel template
			final File source = ResourceUtils.getFile("classpath:Payroll/Template/PAYROLL_TEMPLATE-v1.0.xls");
			if(!source.exists() && !source.isDirectory()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}	
			// CREATE
			// Payroll - excel doc
			final String destinationFileName = "PAYROLL_"+System.nanoTime()+".xls";
			final File destinationDirectory = new File(System.getProperty("java.io.tmpdir"));
			final File destination = new File(destinationDirectory.getAbsolutePath() + File.separator+"HRMS"+File.separator +"Payroll"+File.separator+"Data"+File.separator+ destinationFileName);
			if(!destination.exists()) {
				FileUtils.touch(destination);
			}
			// COPY
			// Payroll template to excel doc
			//
			// 1. source file input stream
			final FileInputStream sourceFIS =new FileInputStream(source); // load excel template 
			HSSFWorkbook workbookSource = ExcelHelper.createWorkBook2003(sourceFIS);			
		    //Workbook wb = WorkbookFactory.create(sourceFIS);
			
		    // 2. destination file output stream
		    final FileOutputStream destinationFOS = new FileOutputStream(destination);
		    workbookSource.write(destinationFOS); // copy
		    destinationFOS.close(); 
		    
		    // write data to destination file
		    // 1. destination input stream
		    final FileInputStream destinationFIS =new FileInputStream(destination); // load live excel 
		    HSSFWorkbook workbookDestination = ExcelHelper.createWorkBook2003(destinationFIS);
		    // 2. load data to excel
		    int offset = 19;
		    boolean loaded = payrollExcelServices.load(rows, headers,workbookDestination,offset);
		    if(!loaded) {
		    	logger.info(String.format("Failed to load data to payroll excel file starting at row {%d} ", offset));
		    }		    
		    final int sheetCount = workbookDestination.getNumberOfSheets();
			logger.info(String.format("Succcessfully loaded data to payroll excel file starting at row = {%d}, total sheet = {%d}, excel path is {%s} ", offset,sheetCount,destination.getPath()));
			return new ResponseEntity<String>(destination.getName(), HttpStatus.OK); // return file name
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@RequestMapping(value = "/v1/payroll-run-details-x.excel", 
			method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> ExcelX(@Validated @RequestBody PayrollTable payrollTable) {
		logger.info(String.format("Payroll dataTable passed is {%s} ", payrollTable));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (payrollTable == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			/*
			 * final ResponseEntity<PayrollPay> response = payrollServices.Create(authToken,
			 * payrollCycle); if (response == null) { return new
			 * ResponseEntity<>(HttpStatus.NOT_FOUND); } // code int code =
			 * response.getStatusCodeValue(); if (HttpStatusCodes.EXISTS == code) { // read
			 * retrieved data for already reported payroll return new
			 * ResponseEntity<PayrollPay>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			 * } else if (HttpStatusCodes.NOT_FOUND == code) { return new
			 * ResponseEntity<>(HttpStatus.NOT_FOUND); } else if
			 * (HttpStatusCodes.NOT_AUTHORIZED == code) { return new
			 * ResponseEntity<>(HttpStatus.UNAUTHORIZED); } else if
			 * (HttpStatusCodes.NOT_UNAVAILABLE == code) { return new
			 * ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED); } else { return new
			 * ResponseEntity<PayrollPay>(response.getBody(), HttpStatus.OK); }
			 */
			//
			final List<PayrollTableRow> rows = payrollTable.getRows();
			final List<String> headers = payrollTable.getHeaders();
			// files
			// source
			final File source = ResourceUtils.getFile("classpath:Payroll/Template/PAYROLL_TEMPLATE-v1.0.xls");
			if(!source.exists() && !source.isDirectory()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String destinationFileName = "PAYROLL_"+System.nanoTime()+".xls";
			// destination
			final File destinationDirectory = new File(System.getProperty("java.io.tmpdir"));
			final File destination = new File(destinationDirectory.getAbsolutePath() + File.separator+"HRMS"+File.separator +"Payroll"+File.separator+"Data"+File.separator+ destinationFileName);
			if(!destination.exists()) {
				FileUtils.touch(destination);
			}
			// copy files
			FileHelper.copyFileUsingApacheCommonsIO(source, destination);
			// streams
			final InputStreamResource in = new InputStreamResource(payrollExcelServices.load(rows, headers));
			final FileOutputStream out = new FileOutputStream(destination.getPath());

			logger.info(String.format("Payroll dataTable inputStream is {%s} ", destination.getPath()));
			return new ResponseEntity<String>(destination.getName(), HttpStatus.OK);
			/*
			 * return ResponseEntity.ok() .header(HttpHeaders.CONTENT_DISPOSITION,
			 * "attachment; filename=" + filename)
			 * .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file)
			 * ;
			 */
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/v1/payroll-run", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> Run(@Validated @RequestBody PayrollRun payrollRun) {
		logger.info(String.format("Payroll run data passed is {%s} ", payrollRun));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (payrollRun == null || payrollRun.getPayrollcycle() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String date = payrollRun.getPayrollcycle();
			if (date == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final PayrollCycle payrollCycle = DateUtils.toPayrollCycle(date);
			if (payrollCycle == null) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			logger.info(String.format("PayrollPay cycle data passed is {%s} ", payrollCycle));
			final ResponseEntity<PayrollPay> response = payrollServices.Create(authToken, payrollCycle);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// code
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<PayrollPay>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<PayrollPay>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/payroll-send-payslip", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> Run(@Validated @RequestBody PayslipSend payslipSend) {
		logger.info(String.format("Payslip send data passed is {%s} ", payslipSend));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (payslipSend == null || payslipSend.getPayrollcycle() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String date = payslipSend.getPayrollcycle();
			if (date == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final PayrollCycle payrollCycle = DateUtils.toPayrollCycle(date);
			if (payrollCycle == null) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			logger.info(String.format("Payslip send cycle data passed is {%s} ", payrollCycle));
			final ResponseEntity<Void> response = payrollServices.SendPayslip(authToken, payrollCycle);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// code
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<Void>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else if (HttpStatusCodes.SUCCESS == code) {
				return new ResponseEntity<Void>(response.getBody(), HttpStatus.OK);				
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/journal-run", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> RunJournal(@Validated @RequestBody JournalRun journalRun) {
		logger.info(String.format("Journal run data passed is {%s} ", journalRun));
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			if (journalRun == null || journalRun.getPayrollcycle() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String date = journalRun.getPayrollcycle();
			if (date == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final PayrollCycle payrollCycle = DateUtils.toPayrollCycle(date);
			if (payrollCycle == null) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			logger.info(String.format("PayrollPay cycle data passed is {%s} ", payrollCycle));
			final ResponseEntity<Journal> response = payrollReportServices.RunJournal(authToken, payrollCycle);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// code
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<Journal>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				return new ResponseEntity<Journal>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.info(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package tz.go.tcra.hrms.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

import tz.go.tcra.hrms.auth.IAuthenticationFacade;
import tz.go.tcra.hrms.config.Utility;
import tz.go.tcra.hrms.core.AppConstants;
import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.Currency;
import tz.go.tcra.hrms.dto.Designation;
import tz.go.tcra.hrms.dto.Employee;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflow;
import tz.go.tcra.hrms.dto.TrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.dto.TrainingResponse;
import tz.go.tcra.hrms.services.IDesignationServices;
import tz.go.tcra.hrms.services.IEmployeeServices;
import tz.go.tcra.hrms.services.ISalaryStructureServices;
import tz.go.tcra.hrms.training.entity.FinancialYear;
import tz.go.tcra.hrms.training.entity.HrmsTraining;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflow;
import tz.go.tcra.hrms.training.entity.HrmsTrainingApprovalWorkflowStep;
import tz.go.tcra.hrms.training.entity.HrmsTrainingCategory;
import tz.go.tcra.hrms.training.entity.HrmsTrainingInitiator;
import tz.go.tcra.hrms.training.entity.HrmsTrainingReport;
import tz.go.tcra.hrms.training.entity.HrmsTrainingSponsor;
import tz.go.tcra.hrms.training.entity.HrmsTrainingType;
import tz.go.tcra.hrms.training.service.IFinancialYearServices;
import tz.go.tcra.hrms.training.service.ITrainingService;

@Controller
public class TrainingController {

	@Autowired
	private ITrainingService trainingService;
	@Autowired
	private IFinancialYearServices financialYear;

	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Autowired
	private ISalaryStructureServices structureServices;

	@Autowired
	private IEmployeeServices employeeServices;

	@Autowired
	private IDesignationServices designationServices;

	private static final Logger logger = Logger.getLogger(TrainingController.class); // log4j

	@RequestMapping(value = "/training-category.htm", method = RequestMethod.GET)
	public ModelAndView TrainingCategory() {
		List<HrmsTrainingCategory> trainingCategory = null;

		if (trainingService != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<HrmsTrainingCategory[]> _trainingCategory = trainingService
					.GetTrainingCategory(authToken);

			if (_trainingCategory != null && _trainingCategory.getBody() != null) {
				trainingCategory = Arrays.asList(_trainingCategory.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("training/training-category");
		mv.addObject("trainingCategory", trainingCategory);
		return mv;
	}

	@RequestMapping(value = "/training-initiator.htm", method = RequestMethod.GET)
	public ModelAndView TrainingInitiator() {

		List<HrmsTrainingInitiator> trainingInitiator = null;

		if (trainingService != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<HrmsTrainingInitiator[]> _trainingInitiator = trainingService
					.GetTrainingInitiator(authToken);

			if (_trainingInitiator != null && _trainingInitiator.getBody() != null) {
				trainingInitiator = Arrays.asList(_trainingInitiator.getBody());
			}
		}
		ModelAndView mv = new ModelAndView("training/training-initiator");
		mv.addObject("trainingInitiator", trainingInitiator);
		return mv;
	}

	@RequestMapping(value = "/training-sponsor.htm", method = RequestMethod.GET)
	public ModelAndView TrainingSponsor() {
		List<HrmsTrainingSponsor> trainingSponsor = null;

		if (trainingService != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<HrmsTrainingSponsor[]> _trainingSponsor = trainingService
					.GetTrainingSponsor(authToken);

			if (_trainingSponsor != null && _trainingSponsor.getBody() != null) {
				trainingSponsor = Arrays.asList(_trainingSponsor.getBody());
			}
		}
		ModelAndView mv = new ModelAndView("training/training-sponsor");
		mv.addObject("trainingSponsor", trainingSponsor);
		return mv;
	}

	@RequestMapping(value = "/training-type.htm", method = RequestMethod.GET)
	public ModelAndView TrainingType() {
		List<HrmsTrainingType> trainingType = null;

		if (trainingService != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<HrmsTrainingType[]> _trainingType = trainingService.GetTrainingType(authToken);

			if (_trainingType != null && _trainingType.getBody() != null) {
				trainingType = Arrays.asList(_trainingType.getBody());
			}
		}
		ModelAndView mv = new ModelAndView("training/training-type");
		mv.addObject("trainingType", trainingType);
		return mv;
	}

	// Retrieve Currency JSON
	@RequestMapping(value = "/get-training-currency-listAjax")
	public @ResponseBody Object GetTrainingCurrencyList() {

//		final String authToken = authenticationFacade.getAuthenticationToken();	
		final ResponseEntity<Currency[]> listCurrency = structureServices.GetAllCurrency();

		if (listCurrency.getBody() != null) {
			final List<Currency> _listCurrency = Arrays.asList(listCurrency.getBody());
			return _listCurrency;
		}
		return null;
	}

	// Retrieve training category JSON
	@RequestMapping(value = "/get-training-category-listAjax")
	public @ResponseBody Object GetTrainingCategoryList() {
		final String authToken = authenticationFacade.getAuthenticationToken();
		final ResponseEntity<HrmsTrainingCategory[]> listCategory = trainingService.GetTrainingCategory(authToken);
		if (listCategory.getBody() != null) {
			final List<HrmsTrainingCategory> _listCategory = Arrays.asList(listCategory.getBody());
			return _listCategory;
		}
		return null;
	}

	// Retrieve training Initiator JSON
	@RequestMapping(value = "/get-training-initiation-listAjax")
	public @ResponseBody Object GetTrainingInitiationList() {
		final String authToken = authenticationFacade.getAuthenticationToken();
		final ResponseEntity<HrmsTrainingInitiator[]> listInitiation = trainingService.GetTrainingInitiator(authToken);
		if (listInitiation.getBody() != null) {
			final List<HrmsTrainingInitiator> _listInitiation = Arrays.asList(listInitiation.getBody());
			return _listInitiation;
		}
		return null;
	}

	// Retrieve training Initiator JSON
	@RequestMapping(value = "/get-training-type-listAjax")
	public @ResponseBody Object GetEmployeeList() {
		final String authToken = authenticationFacade.getAuthenticationToken();
		final ResponseEntity<HrmsTrainingType[]> listType = trainingService.GetTrainingType(authToken);
		if (listType.getBody() != null) {
			final List<HrmsTrainingType> _listType = Arrays.asList(listType.getBody());
			return _listType;
		}
		return null;
	}

	// Retrieve training sponsor JSON
	@RequestMapping(value = "/get-training-sponsor-listAjax")
	public @ResponseBody Object GetTrainingSponsorList() {
		final String authToken = authenticationFacade.getAuthenticationToken();
		final ResponseEntity<HrmsTrainingSponsor[]> listSponsor = trainingService.GetTrainingSponsor(authToken);
		if (listSponsor.getBody() != null) {
			final List<HrmsTrainingSponsor> _listSponsor = Arrays.asList(listSponsor.getBody());
			return _listSponsor;
		}
		return null;
	}

	// Retrieve training sponsor JSON
	@RequestMapping(value = "/get-financial-year-listAjax")
	public @ResponseBody Object GetFinancialYearList() {
		final String authToken = authenticationFacade.getAuthenticationToken();
		final ResponseEntity<FinancialYear[]> listFinacialYear = financialYear.GetAll(authToken);
		if (listFinacialYear.getBody() != null) {
			final List<FinancialYear> _listFinacialYear = Arrays.asList(listFinacialYear.getBody());
			return _listFinacialYear;
		}
		return null;
	}

	// Get All Training for Current Financial Year
	@RequestMapping(value = "/training.htm", method = RequestMethod.GET)
	public ModelAndView Training() {
		List<TrainingResponse> training = null;
		List<Currency> currencies = null;
		logger.info("Get Training information for the Current Financial Year");

		List<Employee> _employees = null;
		if (employeeServices != null) {
			final ResponseEntity<Employee[]> employees = employeeServices.GetAllEmployeesLessDetails();
			if (employees != null && employees.getBody() != null) {
				_employees = Arrays.asList(employees.getBody());
			}
		}
		ResponseEntity<Currency[]> currencyResult = structureServices.GetAllCurrency();
		if (trainingService != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<TrainingResponse[]> _training = trainingService
					.GetTrainingCurrentFinancialYear(authToken);

			if (_training != null && _training.getBody() != null) {
				currencies = Arrays.asList(currencyResult.getBody());
				training = Arrays.asList(_training.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("training/training");
		mv.addObject("training", training);
		mv.addObject("currencies", currencies);
		mv.addObject("employees", _employees);
		return mv;
	}

	// Add Training and upload file using Ajax

	@RequestMapping(value = "/v1/training-add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addTraining(Model model, @ModelAttribute("training") HrmsTraining training,
			BindingResult bindingResult, @RequestParam("feestructureattachment") MultipartFile fileUpload,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		logger.info(String.format("Training data passed is {%s} ", training));
		logger.info(String.format("Attachment file name {%s} ", fileUpload.getOriginalFilename()));
		try {

			if (training == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			int employeeID = authenticationFacade.getUser().getEmpId();
			training.setRequestedby(employeeID);
			ResponseEntity<HrmsTraining> response = null;
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			Long id = timestamp2.getTime();

			String extensionType = FilenameUtils.getExtension(fileUpload.getOriginalFilename());
			String fileName = Utility.trimFileExtension(fileUpload.getOriginalFilename());
			String newFileName = fileName + "_" + id + "." + extensionType;
			String fileURL = AppConstants.UPLOAD_DIRECTORY + newFileName;

			training.setFeestructureattachment(AppConstants.URL + newFileName);
			training.setTrainingpurposeid(3);
			final String authToken = authenticationFacade.getAuthenticationToken();

			if (fileUpload.isEmpty()) {
				response = trainingService.CreateTraining(authToken, training);
			}

			if (extensionType.toLowerCase().equals("pdf") || extensionType.toLowerCase().equals("png")
					|| extensionType.toLowerCase().equals("jpeg")) {

				System.out.println(fileURL);
				boolean isFileUpload = Utility.uploadFile(fileUpload, fileURL, redirectAttributes);
				response = trainingService.CreateTraining(authToken, training);
			}
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<HrmsTraining>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
				return new ResponseEntity<HrmsTraining>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// View Training Details
	@RequestMapping(value = "/v1/training-view-details/{trainingId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetTraningDetails(@PathVariable("trainingId") int trainingId) {
		logger.info("Training Detail ID =[" + trainingId + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<TrainingResponse> response = trainingService.GetTrainingDetails(authToken, trainingId);
			if (response != null) {
				logger.info(String.format("View Training Details {%s}", response.getBody()));
				return new ResponseEntity<TrainingResponse>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			HttpStatus status = ex.getStatusCode();
			int code = status.value();
			logger.info(String.format("Status {%d} is not equal to 200", code));
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update Training
	@RequestMapping(value = "/v1/training-update/{id}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> updateTraining(@PathVariable("id") int id, Model model,
			@ModelAttribute("updateTraining") HrmsTraining hrmsTraining, BindingResult bindingResult,
			@RequestParam("feestructureattachment") MultipartFile fileUpload, HttpServletRequest request,
			RedirectAttributes redirectAttributes

	) {

		logger.info(String.format("Training data passed is {%s} ", hrmsTraining));
		logger.info(String.format("Attachment file name {%s} ", fileUpload.getOriginalFilename()));
		try {
			if (hrmsTraining == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			ResponseEntity<HrmsTraining> response = null;
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			Long _id = timestamp2.getTime();
			int employeeID = authenticationFacade.getUser().getEmpId();
			hrmsTraining.setRequestedby(employeeID);
			String extensionType = FilenameUtils.getExtension(fileUpload.getOriginalFilename());
			String fileName = Utility.trimFileExtension(fileUpload.getOriginalFilename());
			String newFileName = fileName + "_" + _id + "." + extensionType;
			String fileURL = AppConstants.UPLOAD_DIRECTORY + newFileName;

			hrmsTraining.setFeestructureattachment(AppConstants.URL + newFileName);
			hrmsTraining.setTrainingpurposeid(3);
			final String authToken = authenticationFacade.getAuthenticationToken();

			if (fileUpload.isEmpty()) {
				response = trainingService.UpdateTraining(authToken, hrmsTraining, id);
			}

			if (extensionType.toLowerCase().equals("pdf") || extensionType.toLowerCase().equals("png")
					|| extensionType.toLowerCase().equals("jpeg")) {

				System.out.println(fileURL);
				boolean isFileUpload = Utility.uploadFile(fileUpload, fileURL, redirectAttributes);
				response = trainingService.UpdateTraining(authToken, hrmsTraining, id);
			}
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<HrmsTraining>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
				return new ResponseEntity<HrmsTraining>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	// Update Training
//	@RequestMapping(value = "/v1/training-update/{id}", method = RequestMethod.POST, produces = {
//			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	public @ResponseBody ResponseEntity<?> updateTraining(@RequestBody HrmsTraining hrmsTraining,
//			@PathVariable("id") int id) {
//		logger.info(String.format("Training data passed is {%s} ", hrmsTraining));
//
//		try {
//			final String authToken = authenticationFacade.getAuthenticationToken();
//			if (hrmsTraining == null) {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}
//
//			final ResponseEntity<HrmsTraining> response = trainingService.UpdateTraining(authToken, hrmsTraining, id);
//			if (response == null) {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//			// code
//			int code = response.getStatusCodeValue();
//			if (HttpStatusCodes.EXISTS == code) {
//				// read retrieved data for already reported payroll
//				return new ResponseEntity<HrmsTraining>(response.getBody(), HttpStatus.ALREADY_REPORTED);
//			} else if (HttpStatusCodes.NOT_FOUND == code) {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
//				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
//				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//			} else {
//				logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
//				return new ResponseEntity<HrmsTraining>(response.getBody(), HttpStatus.OK);
//			}
//		} catch (Exception ex) {
//			System.out.print(ex.toString());
//			ex.printStackTrace();
//			logger.error(String.format("Exception is {%s} ", ex.toString()));
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	// Delete Training
	@RequestMapping(value = "/v1/training-delete/{trainingId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> DeleteTraningDetails(@PathVariable("trainingId") int trainingId) {
		logger.info("Training Detail ID =[" + trainingId + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<HrmsTraining> response = trainingService.DeleteTraining(authToken, trainingId);
			if (response != null) {
				logger.info(String.format("View Training Details {%s}", response.getBody()));
				return new ResponseEntity<HrmsTraining>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			HttpStatus status = ex.getStatusCode();
			int code = status.value();
			logger.info(String.format("Status {%d} is not equal to 200", code));
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Training Report
	@RequestMapping(value = "/training-report.htm", method = RequestMethod.GET)
	public ModelAndView TrainingReport() {
		// List<TrainingResponse> training = null;
		logger.info("Training Report");

//		if (trainingService != null) {
//			final String authToken = authenticationFacade.getAuthenticationToken();
//			final ResponseEntity<TrainingResponse[]> _training = trainingService.GetTrainingCurrentFinancialYear(authToken);
//			
//			if (_training != null && _training.getBody() != null) {		
//				training = Arrays.asList(_training.getBody());
//			}
//		}

		ModelAndView mv = new ModelAndView("training/training-report");
		// mv.addObject("training", training);
		return mv;
	}

	// View Training Details Report
	@RequestMapping(value = "/v1/training-report-run/{financialyearid}/{quaterid}/{trainingcategoryid}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetTraningReport(@PathVariable("financialyearid") int financialyearid,
			@PathVariable("quaterid") int quaterid, @PathVariable("trainingcategoryid") int trainingcategoryid) {
		logger.info(
				"Training Detail ID =[" + financialyearid + " --> " + quaterid + " --> " + trainingcategoryid + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<TrainingResponse[]> response = trainingService.GetTrainingReport(authToken, quaterid,
					trainingcategoryid, financialyearid);
			if (response != null) {
//				logger.info(String.format("View Training Details {%s}", response.getBody()));
				return new ResponseEntity<TrainingResponse[]>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			HttpStatus status = ex.getStatusCode();
			int code = status.value();
			logger.info(String.format("Status {%d} is not equal to 200", code));
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// View Training Details Report with Jasper report
//	@RequestMapping(value = "/v1/training-report-run" , method = RequestMethod.GET)
//	@ResponseBody
//	public void GetTraningReport(@ModelAttribute("trainingReport") HrmsTrainingReport hrmsTrainingReport,
//			HttpServletResponse httpResponse ) throws JRException, IOException {
//		logger.info("Training Detail ID {%s}" + hrmsTrainingReport);
//
//		
//		final String authToken = authenticationFacade.getAuthenticationToken();
//
//		final ResponseEntity<TrainingResponse[]> response = trainingService.GetTrainingReport(authToken, hrmsTrainingReport.getQuaterid(),
//				hrmsTrainingReport.getTrainingcategoryid(), hrmsTrainingReport.getFinancialyearid());
//		
//		List<TrainingResponse> trainingList = Arrays.asList(response.getBody());
////		InputStream jasperStream = this.getClass().getResourceAsStream("employees.jrxml");
//		File file = ResourceUtils.getFile("classpath:employees.jrxml");
//		Map<String, Object> parameters = new HashMap<>();
//	    parameters.put("createdBy", "Java Techie");
//	    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(trainingList);
//	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//	    httpResponse.setContentType("application/pdf");
////	    httpResponse.setHeader("Content-disposition", "inline;filename=employees.pdf");
//	    
////	    final OutputStream outputStream = httpResponse.getOutputStream();
////	    JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
//	    ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
//	    
//	    JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
//		httpResponse.setContentLength(outputStream.size());
//		
//		ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
//		outputStream.writeTo(servletOutputStream);
//		servletOutputStream.flush();
//		servletOutputStream.close();
//		
//	    	
//	}

	// ******************************** Training Workflow
	// ******************************************

	@RequestMapping(value = "/training-workflow.htm", method = RequestMethod.GET)
	public ModelAndView TrainingWorkflow() {

		List<TrainingApprovalWorkflow> trainingWorkflow = null;
		List<Designation> _listDesignation = null;
		
		logger.info("Training Report");

		if (trainingService != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<TrainingApprovalWorkflow[]> _trainingWorkflow = trainingService
					.GetAllTrainingWorkflow(authToken);

			if (_trainingWorkflow != null && _trainingWorkflow.getBody() != null) {
				trainingWorkflow = Arrays.asList(_trainingWorkflow.getBody());
			}
			
			final ResponseEntity<Designation[]> listDesignation = designationServices.GetSupervisor();

			if (listDesignation.getBody() != null) {
				_listDesignation = Arrays.asList(listDesignation.getBody());
				
			}
		}

		ModelAndView mv = new ModelAndView("training/training-workflow");
		mv.addObject("trainingWorkflow", trainingWorkflow);
//		mv.addObject("listDesignation", _listDesignation);
		return mv;
	}

	// Add Training workflow using Ajax

	@RequestMapping(value = "/v1/training-workflow-add", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> addTrainingWorkflow(
			@Validated @RequestBody HrmsTrainingApprovalWorkflow addTrainingWorkflow) {

		logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflow));

		try {

			if (addTrainingWorkflow == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String authToken = authenticationFacade.getAuthenticationToken();
			ResponseEntity<HrmsTrainingApprovalWorkflow> response = trainingService.CreateTrainingWorkflow(authToken,
					addTrainingWorkflow);

			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
				return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Retrieve Currency JSON
	@RequestMapping(value = "/get-supervisor-designation-listAjax")
	public @ResponseBody Object GetSupervisorDesignationList() {

//		final String authToken = authenticationFacade.getAuthenticationToken();	
		final ResponseEntity<Designation[]> listDesignation = designationServices.GetSupervisor();

		if (listDesignation.getBody() != null) {
			final List<Designation> _listDesignation = Arrays.asList(listDesignation.getBody());
			return _listDesignation;
		}
		return null;
	}

	// View Training Workflow
	@RequestMapping(value = "/v1/training-workflow-view-details/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetTraningWorkflowByID(@PathVariable("id") int id) {
		logger.info("Training Detail ID =[" + id + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<TrainingApprovalWorkflow> response = trainingService.GetTrainingWorkflowByID(authToken, id);
			if (response != null) {
				logger.info(String.format("View Training Details {%s}", response.getBody()));
				return new ResponseEntity<TrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			HttpStatus status = ex.getStatusCode();
			int code = status.value();
			logger.info(String.format("Status {%d} is not equal to 200", code));
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/v1/training-workflow-delete/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> DeleteTraningWorkflow(@PathVariable("id") int id) {
		logger.info("Training Detail ID =[" + id + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<HrmsTrainingApprovalWorkflow> response = trainingService.DeleteTrainingWorkflow(authToken, id);
			if (response != null) {
				logger.info(String.format("View Training Details {%s}", response.getBody()));
				return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			HttpStatus status = ex.getStatusCode();
			int code = status.value();
			logger.info(String.format("Status {%d} is not equal to 200", code));
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Add Training workflow using Ajax

		@RequestMapping(value = "/v1/training-workflow-update/{id}", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> updateTrainingWorkflow(@PathVariable("id") int id,
				@Validated @RequestBody HrmsTrainingApprovalWorkflow addTrainingWorkflow) {

			logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflow));

			try {

				if (addTrainingWorkflow == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				final String authToken = authenticationFacade.getAuthenticationToken();
				ResponseEntity<HrmsTrainingApprovalWorkflow> response = trainingService.UpdateTrainingWorkflow(authToken,
						addTrainingWorkflow, id);
				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(),
							HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
					return new ResponseEntity<HrmsTrainingApprovalWorkflow>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	// *************************************** Training Workflow Step ***********************************************************
	// **********************************

	// Training Workflow Step
	@RequestMapping(value = "/training-workflow-step.htm", method = RequestMethod.GET)
	public ModelAndView TrainingWorkflowStep() {
		List<TrainingApprovalWorkflow> trainingWorkflow = null;
		List<TrainingApprovalWorkflowStep> trainingWorkflowStep = null;

		logger.info("Training Report");

		if (trainingService != null) {
			final String authToken = authenticationFacade.getAuthenticationToken();
			final ResponseEntity<TrainingApprovalWorkflow[]> _trainingWorkflow = trainingService
					.GetAllTrainingWorkflow(authToken);

			final ResponseEntity<TrainingApprovalWorkflowStep[]> _trainingWorkflowStep = trainingService
					.GetAllTrainingWorkflowStep(authToken);

			if (_trainingWorkflow != null && _trainingWorkflow.getBody() != null) {
				trainingWorkflow = Arrays.asList(_trainingWorkflow.getBody());
			}

			if (_trainingWorkflowStep != null && _trainingWorkflowStep.getBody() != null) {
				trainingWorkflowStep = Arrays.asList(_trainingWorkflowStep.getBody());
			}
		}

		ModelAndView mv = new ModelAndView("training/training-workflow-step");
		mv.addObject("trainingWorkflow", trainingWorkflow);
		mv.addObject("trainingWorkflowStep", trainingWorkflowStep);
		return mv;
	}

	// Retrieve Training Workflow Name
	@RequestMapping(value = "/get-training-workflow-name-listAjax")
	public @ResponseBody Object GetWorkflowName() {
		final String authToken = authenticationFacade.getAuthenticationToken();
		final ResponseEntity<TrainingApprovalWorkflow[]> listType = trainingService.GetAllTrainingWorkflow(authToken);
		if (listType.getBody() != null) {
			final List<TrainingApprovalWorkflow> _listType = Arrays.asList(listType.getBody());
			return _listType;
		}
		return null;
	}

	// Add Training Workflow Step using Ajax
	@RequestMapping(value = "/v1/training-workflow-step", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> addTrainingWorkflowStep(
			@Validated @RequestBody HrmsTrainingApprovalWorkflowStep addTrainingWorkflowStep) {

		logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflowStep));

		try {

			if (addTrainingWorkflowStep == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String authToken = authenticationFacade.getAuthenticationToken();
			ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = trainingService
					.CreateTrainingWorkflowStep(authToken, addTrainingWorkflowStep);

			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
				return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/training-workflow-step-byID/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetTrainingWorkflowStep(@PathVariable("id") int id) {
		logger.info("Training Detail ID =[" + id + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<TrainingApprovalWorkflowStep> response = trainingService.GetTrainingWorkflowStepByID(authToken, id);
			if (response != null) {
				logger.info(String.format("View Training Details {%s}", response.getBody()));
				return new ResponseEntity<TrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			HttpStatus status = ex.getStatusCode();
			int code = status.value();
			logger.info(String.format("Status {%d} is not equal to 200", code));
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/update-training-workflow-step/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> updateTrainingWorkflowStep(@PathVariable("id") int id,
			@Validated @RequestBody HrmsTrainingApprovalWorkflowStep addTrainingWorkflow) {

		logger.info(String.format("Training data passed is {%s} ", addTrainingWorkflow));

		try {

			if (addTrainingWorkflow == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String authToken = authenticationFacade.getAuthenticationToken();
			ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = trainingService.
					UpdateTrainingWorkflowStep(authToken, addTrainingWorkflow, id);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
				return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/v1/training-workflow-step-delete/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> DeleteTraningWorkflowStep(@PathVariable("id") int id) {
		logger.info("Training Detail ID =[" + id + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<HrmsTrainingApprovalWorkflowStep> response = trainingService.DeleteTrainingWorkflowStep(authToken, id);
			if (response != null) {
				logger.info(String.format("View Training Details {%s}", response.getBody()));
				return new ResponseEntity<HrmsTrainingApprovalWorkflowStep>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			HttpStatus status = ex.getStatusCode();
			int code = status.value();
			logger.info(String.format("Status {%d} is not equal to 200", code));
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//************************************ End Training Workflow Step ********************************************

	// Add Training Workflow Step using Ajax
	@RequestMapping(value = "/v1/add-training-category", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> addTrainingCategory(
			@Validated @RequestBody HrmsTrainingCategory trainingCategory) {

		logger.info(String.format("Training data passed is {%s} ", trainingCategory));

		try {

			if (trainingCategory == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			final String authToken = authenticationFacade.getAuthenticationToken();
			ResponseEntity<HrmsTrainingCategory> response = trainingService.CreateTrainingCategory(authToken,
					trainingCategory);

			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<HrmsTrainingCategory>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Training Successfully Added {%s}", response.getBody()));
				return new ResponseEntity<HrmsTrainingCategory>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// View Training Details
	@RequestMapping(value = "/v1/training-category-view/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetTraningCategory(@PathVariable("id") int id) {
		logger.info("Training Detail ID =[" + id + "]");
		try {
			final String authToken = authenticationFacade.getAuthenticationToken();
			// create
			final ResponseEntity<HrmsTrainingCategory> response = trainingService.GetTrainingCategoryByID(authToken,
					id);
			if (response != null) {
				logger.info(String.format("View Training Details {%s}", response.getBody()));
				return new ResponseEntity<HrmsTrainingCategory>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			HttpStatus status = ex.getStatusCode();
			int code = status.value();
			logger.info(String.format("Status {%d} is not equal to 200", code));
			if (HttpStatusCodes.EXISTS == code) {
				return new ResponseEntity<>(String.format("Status code is {%d} already exists", code),
						HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

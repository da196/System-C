package tz.go.tcra.hrms.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import tz.go.tcra.hrms.core.HttpStatusCodes;
import tz.go.tcra.hrms.dto.Allowance;
import tz.go.tcra.hrms.services.IAllowanceServices;

@Controller
public class AllowanceController {

	@Autowired
	private IAllowanceServices allowanceServices;
	private static Logger logger = Logger.getLogger(AllowanceController.class);
	

	@RequestMapping(value = "/create-allowance.htm", method = RequestMethod.POST)
	public String addAllowance(@ModelAttribute("Allowance") @RequestBody Allowance allowance) {
		if (allowance == null) {
			return "redirect:/null-values-error.htm";
		}
		try {
			ResponseEntity<Allowance> result = allowanceServices.AddAllowance(allowance);
			if (result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/salary-scales.htm";
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}

	}
	
	@RequestMapping(value = "/create-allowance-type.htm", method = RequestMethod.POST)
	public String addAllowanceType(@ModelAttribute("Allowancetype") @RequestBody Allowance allowanceType) {
		if (allowanceType == null || allowanceType.getName().isEmpty()) {
			return "redirect:/null-values-error.htm";
		}
		try {
			ResponseEntity<Allowance> result = allowanceServices.AddAllowanceType(allowanceType);
			if (result.getStatusCodeValue() != 200) {
				return "redirect:/error.htm";
			}
			return "redirect:/salary-scales.htm";
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
			return "redirect:/error.htm";
		}

	}

	// *************************************** Allowance Type ******************************************************
	@RequestMapping(value = "/v1/allowance-type-by-id/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> GetAllowanceTypeByID(@PathVariable("id") int id) {

		logger.info("Get Allowance Type by ID =[" + id + "]");
		try {
			final ResponseEntity<Allowance> response = allowanceServices.getAllowanceTypeByID(id);
			if (response != null) {
				logger.info(String.format("Allowance Type Details {%s}", response.getBody()));
				return new ResponseEntity<Allowance>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			ex.printStackTrace();
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

	@RequestMapping(value = "/v1/allowance-type-delete/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> DeleteAllowanceTypeByID(@PathVariable("id") int id) {

		logger.info("Delete allowance type by ID =[" + id + "]");
		try {
			final ResponseEntity<Allowance> response = allowanceServices.deleteAllowancetype(id);
			if (response != null) {
				logger.info(String.format("allowance type Details {%s}", response.getBody()));
				return new ResponseEntity<Allowance>(response.getBody(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (HttpClientErrorException ex) {
			ex.printStackTrace();
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
	
	@RequestMapping(value = "/v1/allowance-type-update/{id}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> updateAllowanceType(@PathVariable("id") int id,
			@Validated @RequestBody Allowance allowanceType) {
		try {
			allowanceType.setId(id);
			allowanceType.setAbbreviation(allowanceType.getAbbreviation());
			allowanceType.setName(allowanceType.getName());
			allowanceType.setDescription(allowanceType.getDescription());

			logger.info(String.format("Allowance Type update data passed is {%s} ", allowanceType));
			ResponseEntity<Allowance> response = allowanceServices.updateAllowanceType(allowanceType, id);
			if (response == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			int code = response.getStatusCodeValue();
			if (HttpStatusCodes.EXISTS == code) {
				// read retrieved data for already reported payroll
				return new ResponseEntity<Allowance>(response.getBody(), HttpStatus.ALREADY_REPORTED);
			} else if (HttpStatusCodes.NOT_FOUND == code) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			} else {
				logger.info(String.format("Allowance Type Successfully Updated {%s}", response.getBody()));
				return new ResponseEntity<Allowance>(response.getBody(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			System.out.print(ex.toString());
			ex.printStackTrace();
			logger.error(String.format("Exception is {%s} ", ex.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// *************************************** CRUD Allowance ******************************************************
		@RequestMapping(value = "/v1/allowance-by-id/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> GetAllowanceByID(@PathVariable("id") int id) {

			logger.info("Get Allowance by ID =[" + id + "]");
			try {
				final ResponseEntity<Allowance> response = allowanceServices.getAllowanceeByID(id);
				if (response != null) {
					logger.info(String.format("Allowance Details {%s}", response.getBody()));
					return new ResponseEntity<Allowance>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				ex.printStackTrace();
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
		
		@RequestMapping(value = "/v1/allowance-delete/{id}", method = RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> DeleteAllowanceByID(@PathVariable("id") int id) {

			logger.info("Delete allowance by ID =[" + id + "]");
			try {
				final ResponseEntity<Allowance> response = allowanceServices.deleteAllowance(id);
				if (response != null) {
					logger.info(String.format("allowance Details {%s}", response.getBody()));
					return new ResponseEntity<Allowance>(response.getBody(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException ex) {
				ex.printStackTrace();
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
		

		@RequestMapping(value = "/v1/allowance-update/{id}", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public @ResponseBody ResponseEntity<?> updateAllowance(@PathVariable("id") int id,
				@Validated @RequestBody Allowance allowance) {
			try {
				allowance.setId(id);
				allowance.setAllowancetypeid(allowance.getAllowancetypeid());
				allowance.setSalarystructureid(allowance.getSalarystructureid());
				allowance.setDesignationid(allowance.getDesignationid());
				allowance.setAmount(allowance.getAmount());
				allowance.setEmploymentcategoryid(allowance.getEmploymentcategoryid());
				allowance.setDescription(allowance.getDescription());

				logger.info(String.format("Allowance update data passed is {%s} ", allowance));
				ResponseEntity<Allowance> response = allowanceServices.updateAllowance(allowance, id);
				if (response == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				int code = response.getStatusCodeValue();
				if (HttpStatusCodes.EXISTS == code) {
					// read retrieved data for already reported payroll
					return new ResponseEntity<Allowance>(response.getBody(), HttpStatus.ALREADY_REPORTED);
				} else if (HttpStatusCodes.NOT_FOUND == code) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} else if (HttpStatusCodes.NOT_AUTHORIZED == code) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				} else if (HttpStatusCodes.NOT_UNAVAILABLE == code) {
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				} else {
					logger.info(String.format("Allowance Successfully Updated {%s}", response.getBody()));
					return new ResponseEntity<Allowance>(response.getBody(), HttpStatus.OK);
				}
			} catch (Exception ex) {
				System.out.print(ex.toString());
				ex.printStackTrace();
				logger.error(String.format("Exception is {%s} ", ex.toString()));
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}

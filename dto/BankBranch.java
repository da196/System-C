package tz.go.tcra.hrms.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankBranch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bankid;
	private int id;
	private String name;
	private String physicaladdress;
	private String postaladdress;
	private String sortcode;
	private String telephone;	

}

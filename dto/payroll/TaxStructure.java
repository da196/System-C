package tz.go.tcra.hrms.dto.payroll;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxStructure implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int code;
	private Double amount;
	private Double amountmax;
	private Double amountmin;
	private Double rate;
	private int isformularcomputed;
	private String description;
	private int taxtypeid;
	private int currencyid;
	private int active;
	private int approved;
	// transient
	private String currency;
	private String taxtype;
	private String datelastchanged;
}

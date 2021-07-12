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
public class LeaveSettings implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Leave Types Settings
	private String abbreviation;
	private String description;
	private int id;
	private int maxdayNumber;
	private String name;
	private int paid;
	private int code;

}

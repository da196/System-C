package tz.go.tcra.hrms.training.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialYear implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private int id;
	private int active;
	private int approved;	
	private double yearending;
	private double yearstarting;
	private double duration;
	private String description;
}

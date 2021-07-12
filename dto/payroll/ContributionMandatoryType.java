package tz.go.tcra.hrms.dto.payroll;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContributionMandatoryType {
	private int id;
	private int code;
	private String name;
	private String abbreviation;
	private String description;
	private int active;
	private int approved;
}

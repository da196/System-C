package tz.go.tcra.hrms.dto.payroll;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeslbReport {

	private String fileNumber;
	private String formFourIndexNumber;
	private String fullnameAsperHeslb;
	private String fullEmployeeName;
	private int year;
	private int month;
	private String deductiondate;
	private Double AmountDeducted;
	private Double BalanceRemaining;

}

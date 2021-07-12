package tz.go.tcra.hrms.dto.payroll;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeslbReportResponse {
	private List<HeslbReport> heslbReportlist;
	private Double totalAmountDeducted;

	// private Double totalbalanceRemaining;

}

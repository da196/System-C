package tz.go.tcra.hrms.dto.payroll;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PAYEResponse {

	private List<PAYE> payelist;
	private Double totalpaye;

}

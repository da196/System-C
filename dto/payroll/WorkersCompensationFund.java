package tz.go.tcra.hrms.dto.payroll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkersCompensationFund {
	private String firstName;

	private String middleName;

	private String lastName;

	private String gender;

	private String dateOfBirth;

	private Double basicPay;

	private Double grossPay;

	private Double wcfPay;

	private String designation;

	private String employmentCategory;
	private String nationalId;
	private String datepay;
	private String datepayroll;

}

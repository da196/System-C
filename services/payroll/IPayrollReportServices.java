package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.PublicSocialSecurityFundResponse;
import tz.go.tcra.hrms.dto.payroll.WorkersCompensationFundResponse;
import tz.go.tcra.hrms.dto.payroll.HealthInsuranceResponse;
import tz.go.tcra.hrms.dto.payroll.HeslbReportResponse;
import tz.go.tcra.hrms.dto.payroll.Journal;
import tz.go.tcra.hrms.dto.payroll.PAYEResponse;
import tz.go.tcra.hrms.dto.payroll.PayrollCycle;

@Component
public interface IPayrollReportServices {
	ResponseEntity<Journal> RunJournal(String authToken,PayrollCycle payrollCycle);
	ResponseEntity<HeslbReportResponse> RunHeslb(String authToken,PayrollCycle payrollCycle);
	ResponseEntity<WorkersCompensationFundResponse> RunWCF(String authToken,PayrollCycle payrollCycle);
	ResponseEntity<PublicSocialSecurityFundResponse> RunPSSSF(String authToken,PayrollCycle payrollCycle);
	ResponseEntity<PAYEResponse> RunPayee(String authToken,PayrollCycle payrollCycle);
	ResponseEntity<HealthInsuranceResponse> RunNHIF(String authToken,PayrollCycle payrollCycle);
}

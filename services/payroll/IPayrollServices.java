package tz.go.tcra.hrms.services.payroll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.PayrollPay;
import tz.go.tcra.hrms.dto.payroll.Journal;
import tz.go.tcra.hrms.dto.payroll.Payroll;
import tz.go.tcra.hrms.dto.payroll.PayrollCycle;

@Component
public interface IPayrollServices {
	ResponseEntity<PayrollPay> Create(String authToken,PayrollCycle payrollCycle);
	ResponseEntity<Payroll> Get(String authToken,PayrollCycle payrollCycle);
	ResponseEntity<Void> SendPayslip(String authToken,PayrollCycle payrollCycle);
}

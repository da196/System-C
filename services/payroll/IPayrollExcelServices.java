package tz.go.tcra.hrms.services.payroll;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import tz.go.tcra.hrms.dto.payroll.PayrollTable;
import tz.go.tcra.hrms.dto.payroll.PayrollTableRow;

@Component
public interface IPayrollExcelServices {
	public ByteArrayInputStream load(PayrollTable table);
	public ByteArrayInputStream load(List<PayrollTableRow> rows, List<String> headers);
	public boolean load(List<PayrollTableRow> rows, List<String> headers,Workbook workbook,int row);
	public boolean load(List<PayrollTableRow> rows, List<String> headers, int row, File source, File destination);
	public boolean load(List<PayrollTableRow> rows,int row, File source, File destination);
	public Workbook loadExcel(List<PayrollTableRow> rows,int row, File source, File destination);
	public Workbook loadExcel(String payDate,List<PayrollTableRow> rows,int row, File source, File destination);
}

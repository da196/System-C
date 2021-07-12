package tz.go.tcra.hrms.services.payroll;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import tz.go.tcra.hrms.dto.payroll.PayrollTable;
import tz.go.tcra.hrms.dto.payroll.PayrollTableRow;
import tz.go.tcra.hrms.utils.ExcelHelper;

@Service
public class PayrollExcelServices implements IPayrollExcelServices {

	@Override
	public ByteArrayInputStream load(PayrollTable table) {
		if(table!=null) {
			final ByteArrayInputStream in = ExcelHelper.TableToExcel(table.getRows(),table.getHeaders());
		    return in;
		}
		return null;
	}

	@Override
	public ByteArrayInputStream load(List<PayrollTableRow> rows, List<String> headers) {
		if(rows!=null && headers!=null) {
			final ByteArrayInputStream in = ExcelHelper.TableToExcel(rows,headers);
		    return in;
		}
		return null;
	}

	@Override
	public boolean load(List<PayrollTableRow> rows, List<String> headers,Workbook workbook, int row) {
		if(rows!=null && headers!=null && workbook!=null && row>=0) {
			final Workbook workbookLoaded = ExcelHelper.TableToExcel(rows,headers,workbook,row);
			return workbookLoaded!=null?true:false;
		}
		return false;
	}

	@Override
	public boolean load(List<PayrollTableRow> rows, List<String> headers, int row, File source, File destination) {
		if(rows!=null && headers!=null && row>=0) {
			final Workbook workbookLoaded = ExcelHelper.TableToExcel(rows,row,source,destination);
			return workbookLoaded!=null?true:false;
		}
		return false;
	}

	@Override
	public boolean load(List<PayrollTableRow> rows, int row, File source, File destination) {
		if(rows!=null && row>=0) {
			final Workbook workbookLoaded = ExcelHelper.TableToExcel(rows,row,source,destination);
			return workbookLoaded!=null?true:false;
		}
		return false;
	}

	@Override
	public Workbook loadExcel(List<PayrollTableRow> rows, int row, File source, File destination) {
		if(rows!=null && row>=0) {
			final Workbook workbookLoaded = ExcelHelper.TableToExcel(rows,row,source,destination);
			return workbookLoaded;
		}
		return null;
	}

	@Override
	public Workbook loadExcel(String payDate, List<PayrollTableRow> rows, int row, File source, File destination) {
		if(rows!=null && row>=0) {
			final Workbook workbookLoaded = ExcelHelper.TableToExcel(payDate,rows,row,source,destination);
			return workbookLoaded;
		}
		return null;
	}

}

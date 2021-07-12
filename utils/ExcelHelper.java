package tz.go.tcra.hrms.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tz.go.tcra.hrms.dto.payroll.PayrollTableRow;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String[] SHEET = { "PAYROLL", "APPROVAL" };

	public static Workbook TableToExcel(List<PayrollTableRow> payrollRows, int start, File source, File destination) {
		try {
			// 1. init
			// streams
			FileInputStream inputStreamSource = new FileInputStream(source);
			// book
			Workbook workbook = WorkbookFactory.create(inputStreamSource);
			// configure
			workbook.setFirstVisibleTab(0);
			Sheet sheet = workbook.getSheetAt(0);
			// int n = 300 - 247;
			int n = payrollRows.size() - 247;
			if (n > 0) {
				int startRow = 18;
				int endRow = (startRow + 300) - 1;
				sheet.shiftRows(startRow, endRow, n);
			}
			// close
			inputStreamSource.close();

			// save changes
			FileOutputStream outputStreamDestination = new FileOutputStream(destination);
			workbook.write(outputStreamDestination);
			outputStreamDestination.flush();
			outputStreamDestination.close();

			// 2.
			// open workbook
			FileInputStream inputStreamDestination = new FileInputStream(destination);
			workbook = WorkbookFactory.create(inputStreamDestination);

			// re-configure
			workbook.setFirstVisibleTab(0);
			sheet = workbook.getSheetAt(0);

			// style
			// Style the cell with borders all around.
			CellStyle style = workbook.createCellStyle();
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			
			// title
			final String titleText = "PAYROLL FOR THE MONTH OF {MMMM-YYYY}".replace("{MMMM-YYYY}", "DECEMBER 2020");
			final int rownumTitle = 5;
			final int cellnumTitle = 8;
			Row rowTitle = sheet.getRow(rownumTitle);
			if(rowTitle!=null) {
				Cell cellTitle = rowTitle.getCell(cellnumTitle);
				if(cellTitle!=null) {
					cellTitle.setCellValue(titleText.toUpperCase());
				}
			}
			
			// add rows
			int rowIdx = start;
			int rowCount = 0;
			for (PayrollTableRow item : payrollRows) {
				Row row = sheet.createRow(rowIdx++);
				int sn = rowCount + 1;
				row.createCell(0).setCellValue(sn);
				row.createCell(1).setCellValue(item.getFullName());
				row.createCell(2).setCellValue(item.getSalaryBasic());
				row.createCell(3).setCellValue(item.getWages());
				row.createCell(4).setCellValue(item.getAllowanceHospitality());
				row.createCell(5).setCellValue(item.getAllowanceHousing());
				row.createCell(6).setCellValue(item.getAllowanceTransport());
				row.createCell(7).setCellValue(item.getSalaryGross());
				row.createCell(8).setCellValue(item.getSalaryTaxable());
				row.createCell(9).setCellValue(item.getTaxPaye());
				row.createCell(10).setCellValue(item.getPensionPSSSF());
				row.createCell(11).setCellValue(item.getPensionPSSSFEmployer());
				row.createCell(12).setCellValue(item.getPensionZSSF());
				row.createCell(13).setCellValue(item.getPensionZSSFEmployer());
				row.createCell(14).setCellValue(item.getLoanAzaniaBank());
				row.createCell(15).setCellValue(item.getLoanResidential());
				row.createCell(16).setCellValue(item.getLoanResidentialBalance());
				row.createCell(17).setCellValue(item.getLoanTransport());
				row.createCell(18).setCellValue(item.getLoanTransportBalance());
				row.createCell(19).setCellValue(item.getLoanHESLB());
				row.createCell(20).setCellValue(item.getLoanHESLBBalance());
				row.createCell(21).setCellValue(item.getInsuranceJubilee());
				row.createCell(22).setCellValue(item.getInsuranceNIC());
				row.createCell(23).setCellValue(item.getVoluntaryPostaSimu());
				row.createCell(24).setCellValue(item.getVoluntaryTCRASACCOS());
				row.createCell(25).setCellValue(item.getVoluntaryTCRAMKM());
				row.createCell(26).setCellValue(item.getLoanSalaryAdvance());
				row.createCell(27).setCellValue(item.getLoanSalaryAdvanceBalance());
				row.createCell(28).setCellValue(item.getTotalDeduction());
				row.createCell(29).setCellValue(item.getSalaryNetPay());

				// apply style over cells
				for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						cell.setCellStyle(style);
					}
				}
				++rowCount;
			}
			// apply style
			// close
			inputStreamDestination.close();

			// 3. write changes to target file
			outputStreamDestination = new FileOutputStream(destination);
			workbook.write(outputStreamDestination);
			workbook.close();
			outputStreamDestination.close();

			if (rowCount > 0) {
				return workbook;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to populate data into Excel file: " + e.getMessage());
		}
	}
	
	// with pay date
	public static Workbook TableToExcel(String payDate,List<PayrollTableRow> payrollRows, int start, File source, File destination) {
		try {
			// 1. init
			// streams
			FileInputStream inputStreamSource = new FileInputStream(source);
			// book
			Workbook workbook = WorkbookFactory.create(inputStreamSource);
			// configure
			workbook.setFirstVisibleTab(0);
			Sheet sheet = workbook.getSheetAt(0);
			// int n = 300 - 247;
			int n = payrollRows.size() - 14;
			if (n > 0) {
				int startRow = 18;
				int endRow = (startRow + 300) - 1;
				sheet.shiftRows(startRow, endRow, n);
			}
			// close
			inputStreamSource.close();

			// save changes
			FileOutputStream outputStreamDestination = new FileOutputStream(destination);
			workbook.write(outputStreamDestination);
			outputStreamDestination.flush();
			outputStreamDestination.close();

			// 2.
			// open workbook
			FileInputStream inputStreamDestination = new FileInputStream(destination);
			workbook = WorkbookFactory.create(inputStreamDestination);

			// re-configure
			workbook.setFirstVisibleTab(0);
			sheet = workbook.getSheetAt(0);

			// style
			// Style the cell with borders all around.
			CellStyle style = workbook.createCellStyle();
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());

			// title
			String titleText = "PAYROLL FOR THE MONTH OF {MMMM-YYYY}".replace("{MMMM-YYYY}", payDate);
			final int rownumTitle = 5;
			final int cellnumTitle = 8;
			Row rowTitle = sheet.getRow(rownumTitle);
			if(rowTitle!=null) {
				Cell cellTitle = rowTitle.getCell(cellnumTitle);
				if(cellTitle!=null) {
					cellTitle.setCellValue(titleText.toUpperCase(Locale.getDefault()));
				}
			}
			
			// add rows
			int rowIdx = start;
			int rowCount = 0;
			for (PayrollTableRow item : payrollRows) {
				Row row = sheet.createRow(rowIdx++);
				int sn = rowCount + 1;
				row.createCell(0).setCellValue(sn);
				row.createCell(1).setCellValue(item.getFullName());
				row.createCell(2).setCellValue(item.getSalaryBasic());
				row.createCell(3).setCellValue(item.getWages());
				row.createCell(4).setCellValue(item.getAllowanceHospitality());
				row.createCell(5).setCellValue(item.getAllowanceHousing());
				row.createCell(6).setCellValue(item.getAllowanceTransport());
				row.createCell(7).setCellValue(item.getSalaryGross());
				row.createCell(8).setCellValue(item.getSalaryTaxable());
				row.createCell(9).setCellValue(item.getTaxPaye());
				row.createCell(10).setCellValue(item.getPensionPSSSF());
				row.createCell(11).setCellValue(item.getPensionPSSSFEmployer());
				row.createCell(12).setCellValue(item.getPensionZSSF());
				row.createCell(13).setCellValue(item.getPensionZSSFEmployer());
				row.createCell(14).setCellValue(item.getLoanAzaniaBank());
				row.createCell(15).setCellValue(item.getLoanResidential());
				row.createCell(16).setCellValue(item.getLoanResidentialBalance());
				row.createCell(17).setCellValue(item.getLoanTransport());
				row.createCell(18).setCellValue(item.getLoanTransportBalance());
				row.createCell(19).setCellValue(item.getLoanHESLB());
				row.createCell(20).setCellValue(item.getLoanHESLBBalance());
				row.createCell(21).setCellValue(item.getInsuranceJubilee());
				row.createCell(22).setCellValue(item.getInsuranceNIC());
				row.createCell(23).setCellValue(item.getVoluntaryPostaSimu());
				row.createCell(24).setCellValue(item.getVoluntaryTCRASACCOS());
				row.createCell(25).setCellValue(item.getVoluntaryTCRAMKM());
				row.createCell(26).setCellValue(item.getLoanSalaryAdvance());
				row.createCell(27).setCellValue(item.getLoanSalaryAdvanceBalance());
				row.createCell(28).setCellValue(item.getTotalDeduction());
				row.createCell(29).setCellValue(item.getSalaryNetPay());

				// apply style over cells
				for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						cell.setCellStyle(style);
					}
				}
				++rowCount;
			}
			// apply style
			// close
			inputStreamDestination.close();

			// 3. write changes to target file
			outputStreamDestination = new FileOutputStream(destination);
			workbook.write(outputStreamDestination);
			workbook.close();
			outputStreamDestination.close();

			if (rowCount > 0) {
				return workbook;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to populate data into Excel file: " + e.getMessage());
		}
	}

	public static Workbook TableToExcel(List<PayrollTableRow> payrollRows, Workbook workbook, int start) {
		try {
			Sheet sheet = workbook.getSheet(SHEET[0]);
			// add rows
			int rowIdx = start;
			int rowCount = 0;
			for (PayrollTableRow item : payrollRows) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(item.getSn());
				row.createCell(1).setCellValue(item.getFullName());
				row.createCell(2).setCellValue(item.getSalaryBasic());
				row.createCell(3).setCellValue(item.getWages());

				++rowCount;
			}
			if (rowCount > 0) {
				return workbook;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("Failed to populate data into Excel file: " + e.getMessage());
		}
	}

	public static Workbook TableToExcel(List<PayrollTableRow> payrollRows, List<String> headers, Workbook workbook,
			int start) {
		final String[] HEADERs = headers.toArray(new String[headers.size()]);
		try {
			final Sheet sheet = workbook.getSheet(SHEET[0]);
			if (sheet == null) {
				return null;
			}
			// add rows
			int rowIdx = start;
			int rowCount = 0;
			for (PayrollTableRow item : payrollRows) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(item.getSn());
				row.createCell(1).setCellValue(item.getFullName());
				row.createCell(2).setCellValue(item.getSalaryBasic());
				row.createCell(3).setCellValue(item.getWages());

				++rowCount;
			}
			if (rowCount > 0) {
				return workbook;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("Failed to populate data into Excel file: " + e.getMessage());
		}
	}

	public static Workbook TableToExcel(List<PayrollTableRow> payrollRow, List<String> headers, File file) {
		final String[] HEADERs = headers.toArray(new String[headers.size()]);
		try {
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);// .createSheet(SHEET);

			final int rownum = 18; // header row
			// headers
			Row headerRow = sheet.getRow(rownum);// .createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			// rows
			int rowIndex = 19;
			for (PayrollTableRow item : payrollRow) {
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(item.getSn());
				row.createCell(1).setCellValue(item.getFullName());
				row.createCell(2).setCellValue(item.getSalaryBasic());
				row.createCell(3).setCellValue(item.getWages());
			}
			return workbook;
		} catch (IOException e) {
			throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream TableToExcel(List<PayrollTableRow> payrollRow, List<String> headers) {
		final String[] HEADERs = headers.toArray(new String[headers.size()]);
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET[0]);

			// headers
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			// rows
			int rowIdx = 1;
			for (PayrollTableRow item : payrollRow) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(item.getSn());
				row.createCell(1).setCellValue(item.getFullName());
				row.createCell(2).setCellValue(item.getSalaryBasic());
				row.createCell(3).setCellValue(item.getWages());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
		}
	}

	public static XSSFWorkbook createWorkBook() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		return workbook;
	}

	public static XSSFWorkbook createWorkBook(InputStream in) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	public static HSSFWorkbook createWorkBook2003(InputStream in) {
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}
}

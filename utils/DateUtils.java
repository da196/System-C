package tz.go.tcra.hrms.utils;

import java.text.SimpleDateFormat;

import tz.go.tcra.hrms.dto.payroll.PayrollCycle;

public class DateUtils {
	public static PayrollCycle toPayrollCycle(String date) {
		PayrollCycle cycle = null;
		if(date!=null) {
			final String[] dateParts = date.split("-");
			if(dateParts!=null) {
				cycle = new PayrollCycle();
				// add year
				final String year = dateParts[2];
				if(year!=null) {
					cycle.setYear(Integer.parseInt(year));
				}
				// add month
				final String month = dateParts[1];
				if(month!=null) {
					cycle.setMonth(Integer.parseInt(month));
				}
				// add day
				final String day = dateParts[0];
				if(day!=null) {
					cycle.setDay(Integer.parseInt(day));
				}
			}
		}		
		return cycle;
	}
	
	public static String toYYYYMMDD(String date) {
		if(date!=null) {
			final String[] dateParts = date.split("-");
			if(dateParts!=null) {
				StringBuilder dateBuilder = new StringBuilder();
				// add year
				final String year = dateParts[2];
				if(year!=null) {
					dateBuilder.append(year);
				}
				// add month
				final String month = dateParts[1];
				if(year!=null && month!=null) {
					dateBuilder.append("-");
					dateBuilder.append(month);
				}
				// add day
				final String day = dateParts[0];
				if(year!=null && month!=null && day!=null) {
					dateBuilder.append("-");
					dateBuilder.append(day);
				}
				if(dateBuilder!=null && dateBuilder.length()>0) {
					return dateBuilder.toString();
				}
			}
		}		
		return null;
	}

	static String getMonth(int month) {
		String monthMMMM = null;
		switch(month){
			case 0:
			case 1:
				monthMMMM = "JANUARY";
				break;
			case 2:
				monthMMMM = "FEBRUARY";
				break;
			case 3:
				monthMMMM = "MARCH";
				break;
			case 4:
				monthMMMM = "APRIL";
				break;
			case 5:
				monthMMMM = "MAY";
				break;
			case 6:
				monthMMMM = "JUNE";
				break;
			case 7:
				monthMMMM = "JULY";
				break;
			case 8:
				monthMMMM = "AUGUST";
				break;
			case 9:
				monthMMMM = "SEPTEMBER";
				break;
			case 10:
				monthMMMM = "OCTOBER";
				break;
			case 11:
				monthMMMM = "NOVEMBER";
				break;
			case 12:
				monthMMMM = "DECEMBER";
				break;
		}
		return monthMMMM;
	}
	public static String toMMMMYYYY(String date) {
		if(date!=null) {
			final String dateYYYYMMDD = toYYYYMMDD(date);
			SimpleDateFormat format = new SimpleDateFormat("MMMM,yyyy");
			return format.format(dateYYYYMMDD);
		}		
		return null;
	}
	
	public static String toPayDate(int year,int month,int day) {
		if(year>0&&month>0) {
			return year+"-"+month+"-"+day;
		}
		return null;
	}
}

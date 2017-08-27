package farto.cleva.guilherme.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String DATETIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;

	public static Date parse(String date) {
		return parse(date, DATE_FORMAT);
	}

	public static Date parse(String date, String format) {
		DateFormat df = new SimpleDateFormat(format);

		try {
			return df.parse(date);
		} catch (ParseException e) {
		}

		return null;
	}

	public static String format(String date) {
		return format(date, DATE_FORMAT);
	}

	public static String format(String date, String format) {
		DateFormat df = new SimpleDateFormat(format);

		return df.format(date);
	}

}

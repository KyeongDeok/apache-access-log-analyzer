package com.daou.logpjt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LogTimeUtil {
	private final static Locale locale = Locale.ENGLISH;
	public static long getUnixTime(String time) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",locale);
        try {
			return format.parse(time).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return -1;
	}
	public static int getHourOfDay(String log) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",locale);
        try {
        	c.setTime(format.parse(log));
	        return c.get(Calendar.HOUR_OF_DAY);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return -1;
	}
	public static long getEpoch(String log) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",locale);
        try {
        	return format.parse(log).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return -1;
	}
}
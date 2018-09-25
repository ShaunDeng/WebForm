package com.shaunz.framework.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Deal with the date class
 * @since 2016-01-01
 * @author Shaun
 * @version 1.0
 */
public class DateUtil {
	private final static Logger logger = Logger.getLogger(DateUtil.class);
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

	private final static SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Get system time(YYYY Format)
	 * @return String
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * Get system time(YYYY-MM-DD Format)
	 * @return String
	 */
	public static String getDate() {
		return sdfDay.format(new Date());
	}

	/**
	 * Get system time(HH:mm:ss Format)
	 * @return String
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	
	/**
	 * Get system time(YYYY-MM-DD HH:mm:ss Format)
	 * @return String
	 */
	public static String getDateTime() {
		return sdfDateTime.format(new Date());
	}
	/**
	* @method: compareDate
	* @param s
	* @param e
	* @param fmtPartten The format used to convert
	* @return boolean  true if s >= e
	 */
	public static boolean compareDate(String s, String e,SimpleDateFormat dateFormat) {
		if(fomatDate(s,dateFormat)==null||fomatDate(e,dateFormat)==null){
			return false;
		}
		return fomatDate(s,dateFormat).getTime() >=fomatDate(e,dateFormat).getTime();
	}

	/**
	 * Convert String to Date
	 * @param date String which need be converted.
	 * @param fmtPartten The format used to convert
	 * @return
	 */
	public static Date fomatDate(String date,SimpleDateFormat dateFormat) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			logger.error("[DateUtil#fomatDate] "+e.getMessage());
			return null;
		}
	}
	/**
	 * @method getDiffYear
	 * @param startTime
	 * @param endTime
	 * @param dateFormat
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime,SimpleDateFormat dateFormat) {
		try {
			int years=(int) (((dateFormat.parse(endTime).getTime()-dateFormat.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			return -1;
		}
	}
	 /**
     * @method getDaySub
     * @param beginDateStr
     * @param endDateStr
     * @return long
     */
    public static long getDaySub(String beginDateStr,String endDateStr,SimpleDateFormat dateFormat){
		long day=0;
		java.util.Date beginDate = null;
		java.util.Date endDate = null;
		try {
			beginDate = dateFormat.parse(beginDateStr);
			endDate= dateFormat.parse(endDateStr);
		} catch (ParseException e) {
			logger.error("[DateUtil#getDaySub] "+e.getMessage());
			day = -1;
		}
	    day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
		return day;
    }
    
    /**
     * @method getDateAfterSystemTime
     * @param days
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String getDateAfterSystemTime(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance();
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();
        
        String dateStr = sdfDateTime.format(date);
        
        return dateStr;
    }
}

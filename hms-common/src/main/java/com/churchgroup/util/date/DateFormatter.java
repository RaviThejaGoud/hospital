package com.churchgroup.util.date;
/*************************************************************************
 * Copyright (C) 2005-2006 
 * IFS
 * All Rights Reserved 
 *
 * File:  DateFormatter.java
 *************************************************************************
 *  Ver   Date      Name           Description
 *  ---   -------   ------------   -------------------
 *  1.0   8/17/06   Sreeram     Initial version
 *************************************************************************/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.object.Pair;
import com.churchgroup.util.string.StringFunctions;

/**
 * Date formatter is a class of static methods to handle date formatting and parsing.
 * All patterns required for date formatting are specified in the class.
 * <p>
 * The formatter definitions are:
 * <p>
 * <PRE>
 * MMMMDCCYY    uses the pattern MMMM d, yyyy
 *
 * MMDDCCYYHHMMA    uses the pattern MM/dd/yyyy hh:mm a
 *
 * MMDDCCYYHHMMAZZZ uses the pattern MM/dd/yyyy HH:mm a zzz
 *
 * MMDDYYHHMMA uses the pattern MM/dd/yy hh:mm a
 * 
 * MMDDCCYY uses the pattern MMddyyyy
 * 
 * HHMMSSSSSEEEMMMDCCYY uses the pattern hh:mm:ss:SSS EEE MMM,d,yyyy
 *
 * EEEMMMDDHHMMSSZZZYYY   uses the pattern EEE MMM dd HH:mm:ss zzz yyyy
 *
 * EEEMMMDCCYY uses the pattern EEE, MMM d, yyyy
 *
 * EEEMMMD uses the pattern EEE, MMM d
 *
 * CCYYMMDD uses the pattern yyyyMMdd
 *
 * CCYYJJJ (Julian date) uses the pattern yyyyDDD
 *
 * YYYYMMDD uses the pattern yyyy/MM/dd
 *
 * MMDDYYYY uses the pattern MM/dd/yyyy
 *
 * MMDDYY uses the pattern MM/dd/yy
 *
 * MMMYYYY uses the pattern MMM yyyy
 *
 * MMDD uses the pattern MM/dd
 *
 * MMDDCCYYHHMMSS uses the pattern MM/dd/yyyy HH:mm:ss
 *
 * MMDDCCYYHHMM   uses the pattern MM/dd/yyyy HH:mm
 *
 * HHMMSS uses the pattern HH:mm:ss
 *
 * HHMMSSN uses the pattern HH:mm:ssS
 *
 * HMMA uses the pattern h:mm a
 *
 * HMMA_NO_SPACE uses the pattern h:mmA
 *
 * MM_DD_YYYY uses the pattern MM-dd-yyyy
 *
 * CCYY_MM_DD uses the pattern yyyy-MM-dd
 *
 * MM uses the pattern MM
 *
 * MMMM uses the pattern MMMM (full month name)
 *
 * DD uses the pattern dd
 *
 * YYYY uses the pattern yyyy
 *
 * YYMM uses the pattern yyMM
 *
 * YYYYMMDDHHMMSSNNNNNN uses the pattern yyyy-MM-dd-HH.mm.ss.SSS'000'
 *                      and represents a DB2 timestamp with millisecond
 *                      precision, zero padded for formatting
 *
 * YYYY_MM_DD uses pattern yyyy-MM-dd
 * 
 * YYYYMMDDHHMMSSN uses the pattern yyyy-MM-dd:HH:mm:ssS
 * 
 * YYYYMMDDHHMMSSN_2 uses the pattern yyyy-MM-ddHH:mm:ssS
 *
 * YYYYMMDDHHMMSSN_3 uses the pattern yyyy-MM-dd HH:mm:ss.S
 *
 * DDMMMYYYYHHMMSSA uses the pattern dd MMM yyyy hh:mm:ssa
 * 
 * </PRE>
 * <p>
 * The API of this class has changed in order to be more thread-safe.
 * Calls to format() and parse() on the DateFormat instances are not
 * thread-safe and should be made within a synchronized method or code
 * block.  The old APIs use synchronized code blocks, but there is
 * nothing that forces the use of those APIs.  (The format and parse
 * methods can be invoked directly on the DateFormat instances, which
 * are available as public class variables.)  The old APIs and the
 * class variables should be deprecated.
 * <p>
 * New APIs have been added that take an identifier for the date
 * format instance (which happens to be the date format pattern).  The
 * actual date format instances are stored in a private Map class
 * variable and are not made available externally.  Currently, only the
 * patterns that are defined as constants within this class are
 * supported.
 * <p>
 * For more information on what the format patterns mean, see the
 * JavaDoc for the SimpleDateFormat class
 *
 */
public class DateFormatter {
	
	private static final Log log = LogFactory.getLog(DateFormatter.class);
	static public String version = "0";
	
	
	/**
	 * Formatter for HOST DB2 timestamp
	 */
	private static SimpleDateFormat YYYYMMDDHHMMSSNNNNNN_HOST;
	/**
	 * Parser for non-HOST DB2 timestamp
	 */
	private static SimpleDateFormat YYYYMMDDHHMMSSNNNNNN_PARSE;
	/**
	 * Parser for HOST DB2 timestamp
	 */
	private static SimpleDateFormat YYYYMMDDHHMMSSNNNNNN_HOST_PARSE;
	
	
	/**
	 * DateFormat pattern: MMMM d, yyyy  
	 *
	 * @see SimpleDateFormat
	 */
	public static final String DMMMMDDCCYY_PATTERN = "MMMM d, yyyy";
	/**
     * DateFormat pattern: MMMM d, yyyy  
     *
     * @see SimpleDateFormat
     */
    public static final String MMMMDCCYY_PATTERN = "MMMM d, yyyy";
	/**
	 * DateFormat pattern: MMM d, yyyy  
	 *
	 * @see SimpleDateFormat
	 */
    public static final String MMMMDDCCYY_PATTERN = "MMMM d, yyyy hh:mm a";
	/**
	 * DateFormat pattern: MMM d, yyyy  
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMMDYYYY_PATTERN = "MMM d, yyyy";
	public static final String DMMMYYYY_PATTERN = "d, MMM, yyyy";
	/**
	 * DateFormat pattern: MMM yyyy  
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMMYYYY_PATTERN = "MMM yyyy";
	/**
	 * DateFormat pattern: MM/dd/yyyy HH:mm a zzz
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDCCYYHHMMAZZZ_PATTERN = "MM/dd/yyyy HH:mm a zzz";

	/**
	 * DateFormat pattern: MM/dd/yyyy HH:mm a
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDCCYYHHMMA_PATTERN = "MM/dd/yyyy HH:mm a";
	/**
	 * DateFormat pattern: MM/dd/yy hh:mm a
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDYYHHMMA_PATTERN = "MM/dd/yy hh:mm a";
	/**
	 * DateFormat pattern: MMddyyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDCCYY_PATTERN = "MM/dd/yyyy";
	/**
	 * DateFormat pattern: dd/MM/yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String DDMMCCYY_PATTERN = "dd/MM/yyyy";
	/**
	 * DateFormat pattern: hh:mm:ss:SSS EEE MMM,d,yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String HHMMSSSSSEEEMMMDCCYY_PATTERN = "hh:mm:ss:SSS EEE MMM,d,yyyy";
	/**
	 * DateFormat pattern: HH:mm
	 *
	 * @see SimpleDateFormat
	 */
	public static final String HHMM_PATTERN = "HH:mm";
	/**
	 * DateFormat pattern: HH:mm:ss
	 *
	 * @see SimpleDateFormat
	 */
	public static final String HHMMSS_PATTERN = "HH:mm:ss";
	/**
	 * DateFormat pattern: HH:mm:ssS
	 *
	 * @see SimpleDateFormat
	 */
	public static final String HHMMSSN_PATTERN = "HH:mm:ssS";
	/**
	 * DateFormat pattern: EEE MMM dd HH:mm:ss zzz yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String EEEMMMDDHHMMSSZZZYYYY_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";
	/**
	 * DateFormat pattern: EEE, MMM d, yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String EEEMMMDCCYY_PATTERN = "EEE, MMM d, yyyy";
	/**
	 * DateFormat pattern: EEEE, MMMM d
	 *
	 * @see SimpleDateFormat
	 */
	public static final String EEEEMMMDCCYY_PATTERN = "EEEE, MMMM d. yyyy";
	/**
	 * DateFormat pattern: EEEE, MMMM d
	 *
	 * @see SimpleDateFormat
	 */
	public static final String EEEEMMMMD_PATTERN = "EEEE, MMMM d";
	/**
	 * DateFormat pattern: yyyyMMdd
	 *
	 * @see SimpleDateFormat
	 */
	public static final String CCYYMMDD_PATTERN = "yyyyMMdd";
	/**
	 * DateFormat pattern: yyyyDDD
	 *
	 * @see SimpleDateFormat
	 */
	public static final String CCYYJJJ_PATTERN = "yyyyDDD";
	/**
	 * DateFormat pattern: yyyy/MM/dd
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYYMMDD_PATTERN = "yyyy/MM/dd";
	/**
	 * DateFormat pattern: MM/dd/yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDYYYY_PATTERN = "MM/dd/yyyy";
	/**
	 * DateFormat pattern: dd/MM/yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String DDMMYYYY_PATTERN = "dd/MM/yyyy";
	/**
	 * DateFormat pattern: MM/dd/yy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDYY_PATTERN = "MM/dd/yy";
	/**
	 * DateFormat pattern: MM/dd
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDD_PATTERN = "MM/dd";
	/**
	 * DateFormat pattern: MM/dd/yyyy HH:mm:ss
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDCCYYHHMMSS_PATTERN = "MM/dd/yyyy HH:mm:ss";
    
	/**
	 * DateFormat pattern: MM/dd/yyyy HH:mm
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDCCYYHHMM_PATTERN = "MM/dd/yyyy HH:mm";
	/**
	 * DateFormat pattern: MM-dd-yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MM_DD_YYYY_PATTERN = "MM-dd-yyyy";
	/**
	 * DateFormat pattern: MM-dd-yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MM_DD_YYYY_PATTERN1 = "MM/dd/yyyy";
	/**
	 * DateFormat pattern: MM/dd/yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MM_D_YY_PATTERN = "MM-dd-yy";
	/**
	 * DateFormat pattern: yyyy-MM-dd
	 *
	 * @see SimpleDateFormat
	 */
	public static final String CCYY_MM_DD_PATTERN = "yyyy-MM-dd";
	/**
	 * DateFormat pattern: MM
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MM_PATTERN = "MM";
	/**
	 * DateFormat pattern: MMMM
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMMM_PATTERN = "MMMM";
	/**
	 * DateFormat pattern: dd
	 *
	 * @see SimpleDateFormat
	 */
	public static final String DD_PATTERN = "dd";
	/**
	 * DateFormat pattern: yyMM
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYMM_PATTERN = "yyMM";
	/**
	 * DateFormat pattern: yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYY_PATTERN = "yyyy";
	/**
	 * DateFormat pattern: yyyyyMMdd HH:mm
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYMMDD_HHMM_PATTERN = "yyyyMMdd HH:mm";
	/**
	 * DateFormat pattern: yyyy-MM-dd-HH.mm.ss.SSS000 
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYYMMDDHHMMSSNNNNNN_PATTERN = "yyyy-MM-dd-HH.mm.ss.SSS'000'";
	/**
	 * DateFormat pattern: yyyy-MM-dd:HH:mm:ss:SSS'000'
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYYMMDDHHMMSSNNNNNN_HOST_PATTERN = "yyyy-MM-dd:HH:mm:ss:SSS'000'";
	/**
	 * DateFormat pattern: yyyy-MM-dd-HH.mm.ss.SSS000
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYYMMDDHHMMSSNNNNNN_PARSE_PATTERN = "yyyy-MM-dd-HH.mm.ss.SSS";
	/**
	 * DateFormat pattern: yyyy-MM-dd:HH:mm:ssS
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYYMMDDHHMMSSN_PATTERN = "yyyy-MM-dd:HH:mm:ssS";
	/**
	 * DateFormat pattern: yyyy-MM-ddHH:mm:ssS
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYYMMDDHHMMSSN_2_PATTERN = "yyyy-MM-ddHH:mm:ssS";
	/**
	 * DateFormat pattern: yyyy-MM-dd HH:mm:ss.S
	 *
	 * @see SimpleDateFormat
	 */
    public static final String YYYYMMDDHHMMSSN_3_PATTERN = "yyyy-MM-dd HH:mm:ss.S";
	/**
	 * DateFormat pattern: yyyy-MM-dd:HH:mm:ss:SSS'000'
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYYMMDDHHMMSSNNNNNN_HOST_PARSE_PATTERN = "yyyy-MM-dd:HH:mm:ss:SSS";
    /**
     * DateFormat pattern:  yyyyMMddHHmmssSSS
     * 
     * @see SimpleDateFormat
     */
    public static final String YYYYMMDDHHMMSSNNN_NO_PUNCTUATION_PATTERN = "yyyyMMddHHmmssSSS";
	/**
	 * DateFormat pattern: h:mm:ss a
	 *
	 * @see SimpleDateFormat
	 */
	public static final String HMMSSA_PATTERN = "h:mm:ss a";

	/**
	 * DateFormat pattern: h:mm a
	 *
	 * @see SimpleDateFormat
	 */
	public static final String HMMA_PATTERN = "h:mm a";
	
	/**
	 * DateFormat pattern: h:mmA
	 *
	 * @see SimpleDateFormat
	 */
	public static final String HMMA_NO_SPACE_PATTERN = "h:mma";
	
	/**
	 * DateFormat pattern: yyyy-MM-dd
	 *
	 * @see SimpleDateFormat
	 */
	public static final String YYYY_MM_DD_PATTERN = "yyyy-MM-dd";
	/**
	 * DateFormat pattern: yyyyMMddHHmmss  
	 *
	 * @see SimpleDateFormat
	 */
    public static final String YYYYMMDDHHMMSS_PATTERN = "yyyyMMddHHmmss";
	/**
	 * DateFormat pattern: yyyy-MM-dd HH:mm:ss  
	 *
	 * @see SimpleDateFormat
	 */
   	public static final String YYYY_MM_DD_HHMMSS_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * DateFormat pattern: MM-dd-yyyy hh:mm:ss a  
	 *
	 * @see SimpleDateFormat
	 */
 	public static final String MM_DD_YYYY_HHMMSS_A_PATTERN = "MM-dd-yyyy hh:mm:ss a";
 	public static final String MM_DD_YYYY_HHMM_A_PATTERN = "MM-dd-yyyy hh:mm a";
 	public static final String YYYY_MM_DD_HHMM_A_PATTERN = "yyyy-MM-dd hh:mm a";
	/**
	 * DateFormat pattern: yyyy-MM-dd'T'HH:mm:ss
	 *
	 * @see SimpleDateFormat
	 */
 	public static final String HHMMSS_A_MM_DD_YYYY_PATTERN = "hh:mm a MM-dd-yy";
	/**
	 * DateFormat pattern: yyyy-MM-dd'T'HH:mm:ss
	 *
	 * @see SimpleDateFormat
	 */
   	public static final String YYYY_MM_DD_THHMMSS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"; 	
	/**
	 * DateFormat pattern: MMM dd yyyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMM_D_YYYY_PATTERN = "MMM d yyyy";
    public static final String MMM_DCOMA_YYYY_PATTERN = "MMM d, yyyy";
    /**
     * DateFormat pattern: MMM dd 
     *
     * @see SimpleDateFormat
     */
    public static final String MMM_D_PATTERN = "MMM d";
	/**
	 * DateFormat pattern: dd MMM yyyy hh:mm:ssa
	 *
	 * @see SimpleDateFormat
	 */
	public static final String DDMMMYYYYHHMMSSA_PATTERN = "dd MMM yyyy hh:mm:ssa";
	/**
	 * DateFormat pattern: MMDDyy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MM_DD_YY_PATTERN = "MMddyy";
	/**
	 * DateFormat pattern:  DD-MM-YYYY HH:MI:SS
	 * 
	 * @see SimpleDateFormat
	 */
	public static final String DD_MM_YYYY_HHMMSS_PATTERN = "dd-MM-yyyy HH:mm:ss";
	/**
	 * DateFormat pattern:  EEEEE, dd MMM, yyyy
	 * 
	 * @see SimpleDateFormat
	 */
	public static final String EEEEDDMMMYYYY_PATTERN = "EEEE, dd MMM, yyyy";
	public static final String EEEDDMMMYYYY_PATTERN = "EEE, dd MMM yyyy";
    public static final String EEEEDDMMMYYYY_PATTERN1 = "EEEE, MM/dd";
	/**
	 * DateFormat pattern: yyyyMMddHH  This is displayed in GMT Time
	 * 
	 * @see SimpleDateFormat
	 */
	public static final String  YYYYMMDDHH_GMT_PATTERN= "yyyyMMddHH";
	/**
	 * DateFormat pattern:    HHmm    This is displayed in GMT Time
	 * 
	 * @see SimpleDateFormat
	 */
	public static final String HHMM_GMT_PATTERN = "HHmm";
	
	/**
	 * DateFormat pattern: "MMM. d, yyyy 'at' h:mm aaa
	 * This pattern was created for confirmation pages of applications.
	 * The 'getSubmissionDate()' function alters the returned date slightly for display standards
	 * 
	 * @see DateFunctions.getSubmissionDate()
	 * @see SimpleDateFormat
	 */
	public static final String DATE_AT_TIME_PATTERN = MMMMDCCYY_PATTERN + " 'at' h:mm aaa";
    public static final String MMDDCCYY_HHMMSS_PATTERN = MMMMDCCYY_PATTERN +  " - h:mm aaa";
    public static final String DMMMDDCCYY_HHMMSS_PATTERN = DMMMMDDCCYY_PATTERN +  " - h:mm aaa";
    public static final String MMDDYY_HHMMA_PATTERN = "MM/dd/yyyy hh:mm aa";

	public static final String AM = "AM";
	public static final String PM = "PM";
	public static final String AM_DISPLAY = "a.m.";
	public static final String PM_DISPLAY = "p.m.";
	public static final String ddMMMyyyy_PATTERN1 = "dd-MMM-yyyy";
    

	public static final String GMT = "GMT";
	/**
	 * DateFormat pattern: MM/dd/yy
	 *
	 * @see SimpleDateFormat
	 */
	public static final String MMDDYYY_PATTERN = "MM.dd.yyyy";
	/*
	 * Map instance used to cache the DateFormat instances.
	 * 
	 * Declared using Collections.synchronizedMap in order to make DateFormatter more
	 * thread-safe.  (8/15/2005)
	 */
	private static final Map formatInstances = Collections.synchronizedMap(new HashMap(40));

	/* Constants used to help parse DB2 timestamps */
	private static final int DB2_TIMESTAMP_LENGTH = 26;
	private static final int DB2_TIMESTAMP_PARSE_LENGTH = 23;

	
	// static initializer creates required format patterns
	static 
	{
		initializeFormatInstances();
	}

	
/**
 * Check if the pattern is an exception pattern, like a DB2 timestamp, which
 *   poses parsing difficulties.  Modify the nature of the formatter and pattern
 *   string, if needed, and return as a Pair.<br>
 * @return Pair            pair having formatter as first item and string as second
 *
 * @param formatter        SimpleDateFormat to use
 * @param string           String containing the date 
 */
private static Pair checkDb2TimeStampFormat(SimpleDateFormat formatter,String string)
{	
	Pair p = null;
		
	if (formatter != null && !StringFunctions.isNullOrEmpty(string))
	{
		/* The YYYYMMDDHHMMSSNNNNNN_PATTERN and YYYYMMDDHHMMSSNNNNNN_HOST_PATTERN
		** cannot be parsed.  This is due to the fact that the SimpleDateFormat parser 
		** (working as designed - ahem) treats each of the parsing symbols as a field.
		** This means that when parsing the date string using the above patterns, all 
		** numerics signifying the subsecond field are trying to be stuffed into a millisecond 
		** field (which only values 0-999 are valid). So in the above patterns, SSS'000' can be 
		** formatted, but actually never parsed.
		** Ex:
		**     If the SSS field returns a 123 on format, and then you concatenate 000 on the end
		**     (as in the above formats) and then try to parse it (123000) ... 123000 is out of the
		**     range of accepted values for milliseconds, so the parser throws an exception.  Some people
		**     have tried to battle a change to the SimpleDateFormat class, but they are always refused
		**     because S implies milliseconds ... not subseconds.  One could argue, though that
		**     the parser should look at only the number of SSS characters specified and only extract that
		**     number of digits, but it looks like they aren't gonna budge ... that would be a different
		**     formatter.
		**
		** As a result, we need to check for the above two patterns and remove the last three chars since
		** the parser cannot handle the zeroes.
		*/

		if(string.length() == DB2_TIMESTAMP_LENGTH)
		{
			String pattern = null;
			/* Make DateFormat.toPattern call with a synchronized block */
			synchronized(formatter){
				/* Determine which pattern is being used */
				pattern = formatter.toPattern();
			}

			/* Change formatters to specialized parsers */
			if(YYYYMMDDHHMMSSNNNNNN_PATTERN.equals(pattern))
			{
				string = string.substring(0,DB2_TIMESTAMP_PARSE_LENGTH);
				formatter = YYYYMMDDHHMMSSNNNNNN_PARSE;
			}
			else if(YYYYMMDDHHMMSSNNNNNN_HOST_PATTERN.equals(pattern))
			{
				string = string.substring(0,DB2_TIMESTAMP_PARSE_LENGTH);
				formatter = YYYYMMDDHHMMSSNNNNNN_HOST_PARSE;
			}
		}

		p = new Pair(formatter,string);
	}
	
	return p;
}


/**
 * Creates a SimpleDateFormat instance having the pattern provided and
 * caches the instance by putting it in the Map.  If the instance
 * already exists, a new one is <b>not</b> created.
 * 
 * @param pattern the pattern of the SimpleDateFormat instance
 */
private static SimpleDateFormat createDateFormat(String pattern)
{
	return createDateFormat(pattern,null);
}
/**
 * Create a SimpleDateFormat instance having the pattern provided and
 * cache the instance by putting it in the Map.  
 * 
 * @param pattern the pattern of the SimpleDateFormat instance
 */
private static SimpleDateFormat createDateFormat(String pattern, String timeZoneId)
{
	if (StringFunctions.isNullOrEmpty(pattern))
	{
		return null;
	}

	Map subMap = (Map)formatInstances.get(pattern);
	if (subMap == null)
	{
		subMap = Collections.synchronizedMap(new HashMap(3));
		formatInstances.put(pattern,subMap);
	}

	SimpleDateFormat format = new SimpleDateFormat(pattern);

	if (timeZoneId != null)
	{
		TimeZone tz = TimeZone.getTimeZone(timeZoneId);
		if (tz == null)
		{
			log.debug("DateFormatter.getFormatter():WARNING:" + 
			"Tried to create a date format with invalid time zone id (" + timeZoneId + ").");
		}
		else
		{
			format.setTimeZone(tz);
		}
	}
	subMap.put(timeZoneId,format);

	return format;
}

/**
 * Format the input date using the input formatter.
 * The formatter should be one of the formatters specified 
 * in this class.  If any input parameter is null, null will be returned.
 *
 * @return String containing the formatted date or null
 * @param formatter SimpleDateFormat that uses the required pattern
 * @param date Date to format
 *
 * @deprecated Use the formatDate method that takes the pattern instead
 * of the formatter
 */
public static String formatDate(SimpleDateFormat formatter, Date date)
{
	String formattedDate = null;
	
	if (formatter != null && date != null)
	{
		synchronized (formatter)
		{
			formattedDate =  formatter.format(date);
		}	
	}
	
	return formattedDate;		
}


/**
 * Format the input date using a formatter having the pattern provided.
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input date is <tt>null</tt>.
 *
 * @return String containing the formatted date or null
 * @param pattern the pattern into which the date object should be
 * formatted
 * @param date the date instance to format
 */
public static String formatDate(String pattern, Date date)
{
	return formatDate(pattern,date,null);
}

public static String formatDate(String pattern, String date)
{
    return formatDate(pattern,parseString(DateFormatter.MMDDYYYY_PATTERN,date),null);
}


public static String formatDateAsStr(String outPattern, String pattern, String date)
{
    return formatDate(outPattern,parseString(pattern,date),null);
}



/**
 * Format the input date using a formatter having the pattern provided.
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input date is <tt>null</tt>.
 * <br>
 * This version of the methods supports the timeZoneId as a third parameter.
 *
 * @return String containing the formatted date or null
 *
 * @param pattern the pattern into which the date object should be
 * formatted
 * @param date the date instance to format
 * @param timeZoneId  the time zone to use (i.e., 'GMT', 'EST', etc)
 */
public static String formatDate(String pattern, Date date, String timeZoneId)
{
	SimpleDateFormat formatter = getFormatter(pattern, timeZoneId);
	
	String parsedDate = null;
	if (formatter != null)
	{
		parsedDate = formatDate(formatter, date);
	}
	
	return parsedDate;
}


/**
 * Returns the cached SimpleDateFormat instance having the pattern
 * provided.
 * 
 * Creation date: (12/11/2001 3:46:24 PM)
 * @return The cached SimpleDateFormat instance.
 * @param pattern the pattern of the SimpleDateFormat
 */
protected static SimpleDateFormat getFormatter(String pattern)
{
	return getFormatter(pattern,null);
}


/**
 * Returns the cached SimpleDateFormat instance having the pattern
 * provided.  If the pattern does not match one of the patterns
 * defined in this class, null will be returned.
 * 
 * Creation date: (12/11/2001 3:46:24 PM)
 * @return The cached SimpleDateFormat instance.
 * @param pattern the pattern of the SimpleDateFormat
 */
protected static SimpleDateFormat getFormatter(String pattern, String timeZoneId)
{
	Map formatterSubMap = (Map)formatInstances.get(pattern);

	SimpleDateFormat formatter = null;
	if (formatterSubMap == null)
	{
		// not using MessageLogger to avoid creating dependency between INF Utilities
		//  and Common Services
		log.debug("DateFormatter.getFormatter():WARNING:" + 
			"A SimpleDateFormat instance could not be found having pattern: '" + pattern + "'");
	}
	else
	{
		formatter = (SimpleDateFormat)formatterSubMap.get(timeZoneId);
	
		// if there was no instance of the formatter for the time zone they
		//   specified, create one
		if (formatter == null)
		{
			formatter = createDateFormat(pattern,timeZoneId);
		}	
	}
	return formatter;
}


/**
 * Initializes the different SimpleDateFormat instances based upon
 * the different patterns.  The static instance variables are
 * maintained for the moment for backward compatibility.  At some
 * point, once all of the classes that are using the old interface
 * are changed, the SimpleDateFormat class variables can be removed
 * in favor of the Map.
 * 
 * Creation date: (12/11/2001 3:55:42 PM)
 */
private static void initializeFormatInstances()
{
	
	setYYYYMMDDHHMMSSNNNNNN_HOST(createDateFormat(YYYYMMDDHHMMSSNNNNNN_HOST_PATTERN));
	YYYYMMDDHHMMSSNNNNNN_PARSE = createDateFormat(YYYYMMDDHHMMSSNNNNNN_PARSE_PATTERN);
	YYYYMMDDHHMMSSNNNNNN_HOST_PARSE = createDateFormat(YYYYMMDDHHMMSSNNNNNN_HOST_PARSE_PATTERN);
	// the lines above are setting deprecated variables.
	//  Any future updates should look like the ones below
	createDateFormat(MMDDCCYYHHMMSS_PATTERN);
	createDateFormat(YYYY_MM_DD_PATTERN);
	createDateFormat(MMMDYYYY_PATTERN);
	createDateFormat(DMMMYYYY_PATTERN);
	createDateFormat(MMM_D_YYYY_PATTERN);
	createDateFormat(DATE_AT_TIME_PATTERN);
	createDateFormat(YYYYMMDDHHMMSS_PATTERN);
   	createDateFormat(YYYY_MM_DD_HHMMSS_PATTERN);
   	createDateFormat(YYYY_MM_DD_THHMMSS_PATTERN);
   	createDateFormat(EEEEMMMMD_PATTERN);
	createDateFormat(EEEMMMDCCYY_PATTERN);
	createDateFormat(EEEEMMMDCCYY_PATTERN);
   	createDateFormat(HMMA_PATTERN);
   	createDateFormat(MM_D_YY_PATTERN);
   	createDateFormat(HHMMSSN_PATTERN);
   	createDateFormat(HHMM_PATTERN);
   	createDateFormat(MMMM_PATTERN);
    createDateFormat(YYYY_PATTERN);
   	createDateFormat(HMMA_NO_SPACE_PATTERN);
   	createDateFormat(YYYYMMDDHHMMSSN_PATTERN);
   	createDateFormat(YYYYMMDDHHMMSSN_2_PATTERN);
   	createDateFormat(DDMMMYYYYHHMMSSA_PATTERN);
   	createDateFormat(YYYYMMDDHHMMSSN_3_PATTERN);
   	createDateFormat(MMMYYYY_PATTERN);
    createDateFormat(MMDDCCYY_PATTERN);
    createDateFormat(DDMMCCYY_PATTERN);
   	createDateFormat(MMDDCCYYHHMMA_PATTERN);
   	createDateFormat(EEEMMMDDHHMMSSZZZYYYY_PATTERN);
   	createDateFormat(YYMM_PATTERN);
   	createDateFormat(YYMMDD_HHMM_PATTERN);
   	createDateFormat(CCYYJJJ_PATTERN);
   	createDateFormat(MMMMDCCYY_PATTERN);
   	createDateFormat(MMMMDDCCYY_PATTERN);
	createDateFormat(MM_DD_YYYY_HHMMSS_A_PATTERN);
	createDateFormat(MM_DD_YYYY_HHMM_A_PATTERN);
	createDateFormat(YYYY_MM_DD_HHMM_A_PATTERN);
	createDateFormat(HHMMSS_A_MM_DD_YYYY_PATTERN);
	createDateFormat(MM_DD_YY_PATTERN);
	createDateFormat(DD_MM_YYYY_HHMMSS_PATTERN);
	createDateFormat(EEEEDDMMMYYYY_PATTERN);
    createDateFormat(EEEEDDMMMYYYY_PATTERN1);
    createDateFormat(ddMMMyyyy_PATTERN1);
	createDateFormat(YYYYMMDDHH_GMT_PATTERN,GMT);
	createDateFormat(HHMM_GMT_PATTERN, GMT);
    createDateFormat(YYYYMMDDHHMMSSNNN_NO_PUNCTUATION_PATTERN);
    createDateFormat(MMM_D_PATTERN);
    createDateFormat(MMDDCCYY_HHMMSS_PATTERN);
    createDateFormat(DMMMDDCCYY_HHMMSS_PATTERN);
    createDateFormat(MMDDYYHHMMA_PATTERN);
    createDateFormat(MMM_DCOMA_YYYY_PATTERN);
    createDateFormat(DD_PATTERN);
    createDateFormat(MMDDYYY_PATTERN);
    createDateFormat(MMDDYY_HHMMA_PATTERN);
    createDateFormat(MM_DD_YYYY_PATTERN1);
    createDateFormat(CCYY_MM_DD_PATTERN);
}


/**
 * Parse the string representation of a date using the input formatter.
 * The formatter should be one of the formatters specified in this class.
 * If any input parameter is null, null will be returned.
 *	<p>
 * This method allows out-of-range field values in the date string.<br>
 * If you need parse to enforce validation use <code>parseString(formatter, dateString, false)</code>,
 * which will invoke DateFormat.setLenient(false)
 * <p>
 * @return a Date or null
 *
 * @param formatter        SimpleDateFormat to use
 * @param string           String containing the date
 *
 * @deprecated    Use the parseString method that takes the pattern instead of the formatter
 */
public static Date parseString(SimpleDateFormat formatter, String string)
{
	Date date = null;
	
	if (formatter != null && !StringFunctions.isNullOrEmpty(string))
	{
		try
		{
			date = parseString(formatter, string, true);
		}
		catch (ParseException ignore) {
			ignore.printStackTrace();
		}
	}
	
	return date;	
}


/**
 * Parse the string representation of a date using the input formatter.
 * The formatter should be one of the formatters specified in this class.
 * If any input parameter is null, null will be returned.
 *	<p>
 * If isLenient is false, validation is enforced in the parse, which will result 
 * in a parse exception if the date is invalid.
 * <p>
 * @return Date or null if a ParseException occurs
 *
 * @param formatter        SimpleDateFormat to use
 * @param string           String containing the date 
 * @param isLenient        boolean indicating whether lenient should be set true or false
 *
 * @exception ParseException thrown if isLenient is false and the dateString is an invalid date
 *
 * @deprecated Use the parseString method that takes the pattern instead of the formatter
 */
public static Date parseString(SimpleDateFormat formatter, String string, boolean isLenient)
	throws ParseException
{	
	Date d = null;

	Pair p = checkDb2TimeStampFormat(formatter,string);
	if (p != null)
	{
		//SimpleDateFormat format = (SimpleDateFormat)p.first();
		String pattern = (String)p.second();

		/* Do the parsing within a synchronized block because */
		/*  the format object has instance variables which are being modified here */
		synchronized (formatter)
		{
			formatter.setLenient(isLenient);
			d = formatter.parse(pattern);
		}
	}
	return d;
}


/**
 * Parse the string representation of a date using the pattern provided.
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input string is <tt>null</tt>.
 * <p>
 * This allows out-of-range field values in the date string. 
 * If you need parse to enforce validation use 
 * <code><nobr>parseString(pattern, dateString, <b>false</b>)</nobr></code>
 * which will invoke DateFormat.setLenient(false).
 *
 * @return a Date or null
 *
 * @param pattern        the pattern that the string representation of the date should have
 * @param string         String containing the date
 */
public static Date parseString(String pattern, String string)
{
	return parseString(pattern,string,null);
}


/**
 * Parse the string representation of a date using the pattern provided.
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input string is <tt>null</tt>.
 * <p>
 * This method allows out-of-range field values in the date string. 
 * If you need 'parse' to enforce validation,  use 
 * <nobr>parseString(pattern, dateString, <b>false</b>, timeZone)</nobr>
 * which will invoke DateFormat.setLenient(false).
 * <br>
 * This version of the method supports the timeZoneId as a third parameter.
 * <br>
 * @return a Date or null
 *
 * @param pattern   String containing the pattern for the Date
 * @param string    String containing the date
 */
public static Date parseString(String pattern, String string, String timeZone)
{
	SimpleDateFormat formatter = getFormatter(pattern,timeZone);
	
	Date date = null;
	if (formatter != null)
	{
		date = parseString(formatter, string);
	}
	
	return date;
}


/**
 * Parse the string representation of a date using the pattern provided.
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input string is <tt>null</tt>.
 * <p>
 * If isLenient is false, validation is enforced in the parse, which will result 
 * in a parse exception if the date is invalid.
 *
 * @return Date   if no ParseException occurred
 *
 * @param pattern the pattern that the string representation of the date should have
 * @param pattern the pattern that the string representation of the date should have
 * @param isLenient boolean indicating whether lenient should be set true or false
 *
 * @exception ParseException thrown if isLenient is false and the dateString is an invalid date
 */
public static Date parseString(String pattern, String string, boolean isLenient)
	throws ParseException
{
	return parseString(pattern,string,isLenient,null);
}


/**
 * Parse the string representation of a date using the pattern provided. <br>
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input string is <tt>null</tt>.
 * <p>
 * If isLenient is false, validation is enforced in the parse, which will result 
 * in a parse exception if the date is invalid.
 * <br>
 * This version of the methods supports the timeZoneId as a fourth parameter.
 * <br>
 *
 * @return Date
 *
 * @param pattern     the String pattern representing the format of the date
 * @param string      the String representation of the Date
 * @param isLenient   boolean indicating whether lenient should be set true or false
 * @param timeZoneId  the String representing the time zone to use (i.e., 'GMT', 'EST', etc)
 *
 * @exception ParseException thrown if isLenient is false and the dateString is an invalid date
 */
public static Date parseString(String pattern, String string, boolean isLenient, String timeZone)
	throws ParseException
{
	SimpleDateFormat formatter = getFormatter(pattern,timeZone);
	
	Date d = null;
	if (formatter != null)
	{
		d = parseString(formatter, string, isLenient);
	}
	
	return d;
}


/**
 * Parse the string representation of a date and produce a Calendar
 *    using the input formatter.
 * The formatter should be one of the formatters specified in this class.
 * If any input parameter is null, null will be returned.
 *	<p>
 * This method invokes DateFormat.setLenient(true), which is the default,
 * before calling parse.  This allows out-of-range field values in the date string.
 * If you need parse to enforce validation use parseString(formatter, dateString, false)
 * which will invoke DateFormat.setLenient(false)
 * <p>
 * @return a Calendar or null 
 * @param formatter SimpleDateFormat to use
 * @param string String containing the date
 *
 */
private static Calendar parseStringAsCalendar(SimpleDateFormat formatter, String string)
{
	Calendar c = null;
	
	if (formatter != null && !StringFunctions.isNullOrEmpty(string))
	{
		try
		{
			c = parseStringAsCalendar(formatter, string, true);
		}
		catch (ParseException ignore) {
			ignore.printStackTrace();
		}
	}
	
	return c;	
}


/**
 * Parse the string representation of a date using the input formatter.
 * The formatter should be one of the formatters specified in this class.
 * If any input parameter is null, null will be returned.
 *	<p>
 * If isLenient is false, validation is enforced in the parse, which will result 
 * in a parse exception if the date is invalid.
 * <p>
 * @return Calendar or null if a ParseException occurs
 *
 * @param formatter        SimpleDateFormat to use
 * @param string           String containing the date 
 * @param isLenient        boolean indicating whether lenient should be set true or false
 *
 * @exception ParseException thrown if isLenient is false and the dateString is an invalid date
 */
private static Calendar parseStringAsCalendar(SimpleDateFormat formatter,String string,boolean isLenient)
	throws ParseException
{	
	Calendar c = null;

	Pair p = checkDb2TimeStampFormat(formatter,string);
	if (p != null)
	{
//		SimpleDateFormat format = (SimpleDateFormat)p.first();
		String pattern = (String)p.second();

		/* Do the parsing within a synchronized block because */
		/*  the format object has instance variables which are being modified here */
		synchronized (formatter)
		{
			formatter.setLenient(isLenient);
			formatter.parse(pattern);
			// clone the calendar so the caller won't be modified the instance of
			//  the calendar used inside the date format
			c = (Calendar)(formatter.getCalendar().clone());
		}
	}
	
	return c;
}


/**
 * Parse the string representation of a date using the pattern provided.
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input string is <tt>null</tt>.
 * <br>
 * Return a Calendar representation of the date.
 *
 * This method will use a DateFormat with <code>lenient</code> set to <code>true</code>, 
 *   which is the default.  This allows out-of-range field values in the
 * date string. If you need parse to enforce validation use 
 * <nobr>parseStringAsCalendar(pattern, dateString, <b>false</b>)</nobr>
 * which will invoke DateFormat.setLenient(false).
 *
 * @return a Calendar or null
 * @param pattern the pattern that the string representation of the date should have
 * @param string String containing the date
 */
public static Calendar parseStringAsCalendar(String pattern, String string)
{
	return parseStringAsCalendar(pattern,string,null);
}


/**
 * Parse the string representation of a date using the pattern provided.
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input string is <tt>null</tt>.
 * <p>
 * This method invokes DateFormat.setLenient(true), which is the default,
 * before calling parse.  This allows out-of-range field values in the
 * date string. If you need parse to enforce validation use 
 * <nobr>parseString(pattern, dateString, <b>false</b>)</nobr>
 * which will invoke DateFormat.setLenient(false).
 * <br>
 * This version of the methods supports the timeZoneId as a third parameter.
 * <br>
 * @return a Date or null
 * @param pattern the pattern that the string representation of the date
 * should have
 * @param string String containing the date
 */
public static Calendar parseStringAsCalendar(String pattern, String string, String timeZone)
{
	SimpleDateFormat formatter = getFormatter(pattern,timeZone);
	
	Calendar c = null;
	if (formatter != null)
	{
		c = parseStringAsCalendar(formatter, string);
	}
	
	return c;
}


/**
 * Parse the string representation of a date into a Calendar 
 *    using the pattern provided. <br>
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input string is <tt>null</tt>.
 * <p>
 * This method invokes DateFormat.setLenient(isLenient), 
 * before calling parse.  If isLenient is true, out-of-range 
 * field values are allowed in the date string.  If isLenient is
 * false, validation is enforced in the parse which will result 
 * in a parse exception if the date is invalid.
 *
 * @return Calendar 
 * @param pattern the pattern that the string representation of the date should have
 * @param pattern the pattern that the string representation of the date should have
 * @param isLenient boolean indicating whether lenient should be set true or false
 * @exception ParseException thrown if isLenient is false and the dateString is an invalid date
 */
public static Calendar parseStringAsCalendar(String pattern, String string, boolean isLenient)
	throws ParseException
{
	return parseStringAsCalendar(pattern,string,isLenient,null);
}


/**
 * Parse the string representation of a date as a Calendar
 *    using the pattern provided. <br>
 * The pattern should be one of the patterns defined by this class.
 * <tt>null</tt> will be returned if the pattern is not defined within
 * this class, or if the input string is <tt>null</tt>.
 * <p>
 * If isLenient is false, validation is enforced in the parse, which will result 
 * in a parse exception if the date is invalid.
 * <br>
 * This version of the methods supports the timeZoneId as a fourth parameter.
 * <br>
 *
 * @return Calendar
 * @param  pattern     String representing the pattern the date should have
 * @param  string      String representation of the date value
 * @param  isLenient   boolean indicating whether lenient should be set true or false
 * @param  timeZoneId  String indicating the time zone to use (i.e., 'GMT', 'EST', etc)
 *
 * @exception ParseException thrown if isLenient is false and the dateString is an invalid date
 */
public static Calendar parseStringAsCalendar(String pattern, String string, boolean isLenient, String timeZone)
	throws ParseException
{
	SimpleDateFormat formatter = getFormatter(pattern,timeZone);
	
	Calendar c = null;
	if (formatter != null)
	{
		c = parseStringAsCalendar(formatter, string, isLenient);
	}
	
	return c;
}
/**
 * Creates today date string with given pattern
 *
 * @return String
 * @param  pattern     String representing the pattern the date should have
 * @exception ParseException thrown if isLenient is false and the dateString is an invalid date
 */
  public static String getTodayDateStr (String pattern) {
    return formatDate(pattern,new Date());
  }
  public static String getDateStr(String pattern,Date date) {
	    return formatDate(pattern,date);
  }

/**
 * Creates today date with given pattern
 *
 * @return Date
 * @param  pattern     String representing the pattern the date should have
 * @exception ParseException thrown if isLenient is false and the dateString is an invalid date
 */
  public static Date getTodayDate (String pattern) {
    return parseString(pattern,getTodayDateStr(pattern));
    }

  
  public static void main(String [] args) 
  {
      try
      {
          Date date=null;
          //SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");
          date=DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN,"04/01/2011");
          log.debug(" Date date: " + date);
          //log.debug(" Date : " + DateUtil.getDateTime(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,date));
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
  }


public static SimpleDateFormat getYYYYMMDDHHMMSSNNNNNN_HOST() {
	return YYYYMMDDHHMMSSNNNNNN_HOST;
}


public static void setYYYYMMDDHHMMSSNNNNNN_HOST(
		SimpleDateFormat yYYYMMDDHHMMSSNNNNNN_HOST) {
	YYYYMMDDHHMMSSNNNNNN_HOST = yYYYMMDDHHMMSSNNNNNN_HOST;
}
public static boolean between(Date date, Date dateStart, Date dateEnd) {
    if (date != null && dateStart != null && dateEnd != null) {
        if (date.after(dateStart) && date.before(dateEnd)) {
            return false;
        }else if (date.equals(dateStart) && date.equals(dateEnd)) {
        	 return false;
        }else if (date.before(dateStart) && date.after(dateEnd)) {
        	 return false;
        }else if (date.after(dateStart) && date.after(dateEnd)) {
        	 return true;
        }else if (date.after(dateStart) && date.equals(dateEnd)) {
            return false;
        }else if (date.equals(dateStart) && date.before(dateEnd)) {
            return false;
        }
        else{
        	return true;
        }
    }else if(date != null && dateStart == null && dateEnd != null){
    	 if (date.equals(dateEnd)) {
    		 return false;
	     }else if(date.before(dateEnd)){
	    	 return false;
	     }else{
	    	 return true;
	     }
    }
    return false;
}
}

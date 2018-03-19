package com.churchgroup.util.date;
/*************************************************************************
 * Copyright (C) 2005-2006 
 * IFS
 * All Rights Reserved 
 *
 * File:  DateFunctions.java
 *************************************************************************
 *  Ver   Date      Name           Description
 *  ---   -------   ------------   -------------------
 *  1.0   8/17/06   Sreeram     Initial version
 *************************************************************************/

 
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

/**
 * DateFunctions is a class of static methods related to 
 * calendars, dates and time.
 * @author: methods compiled from various authors
 */
public class DateFunctions {
    
    private static Log log = LogFactory.getLog(DateFunctions.class);
    
    private static String REGEX = " ";

    private static long MSECS_PER_DAY = 1000 * 60 * 60 * 24;
    /**
     * daysBetween will calculates the number of days between d1 and d2.
     * The calculated value will to positive if d2 occurs after d1 and
     * negative if d1 occurs before d2.
     *
     * @param d1 Date.
     * @param d2 Date.
     * @return the difference between d1 and d2 in days.
     */
    public static int daysBetween (Date d1, Date d2)
    {
        long days;
        try{
           days = (d2.getTime() - d1.getTime()) / MSECS_PER_DAY;
        }catch(Exception ex){
        	return 0;
        }
        return (int) days;
    }

    /**
     * add() will add nDays to d1 and will return the resulting date
     *
     * @param d1 Date.  Starting date.
     * @param nDays int.  Number of days to be added to d1.
     * @return new date which is nDays days after d1.
     */
    public static Date add (Date d1, int nDays)
    {
        Calendar c;

        c = Calendar.getInstance ();
        c.setTime(d1);
        c.add (Calendar.DAY_OF_MONTH, nDays);

        return c.getTime();
    }
    /**
     * add() will add nDays to today and will return the resulting date
     *
     * @param nDays int.  Number of days to be added to d1.
     * @return new date which is nDays days after d1.
     */
    public static Date getTodayPlusNdays(int nDays)
    {
        return add(new Date(), nDays);
    }
    
    
/**
* This method calculates the age of a person based on today's date and the date
*  	passed to the method.  
*
* @return int     current age  <B>(will return -1 if an error occurs)</B>
* @param   sDOB  the date of birth, as a Date object
**/
 
public static int calculateAge(Date dateOfBirth) 
{
    if (dateOfBirth == null)
    {
	    return -1;
    }

 	// if it still couldn't create the calendar for some reason,
 	//  return -1
    Calendar gcDOB = new GregorianCalendar();
    gcDOB.setTime(dateOfBirth);
    if (gcDOB == null)
    {
	    return -1;
    }
	
	return calculateAge(gcDOB);
}

public static int calculateAge(Calendar gcDOB)
{
	boolean hasReachedBirthday = true;
	
	GregorianCalendar now = new GregorianCalendar();
	
	// check for BirthMonth not reached this year
	if (now.get(Calendar.MONTH) < gcDOB.get(Calendar.MONTH))
	{
	    hasReachedBirthday = false;
	}
	// check for BirthMonth reached this year
	else
	{    	
		if (now.get(Calendar.MONTH) == gcDOB.get(Calendar.MONTH) && now.get(Calendar.DAY_OF_MONTH) < gcDOB.get(Calendar.DAY_OF_MONTH))
		{
		// check for BirthDayOfMonth not reached
	    	//if (now.get(Calendar.DAY_OF_MONTH) < gcDOB.get(Calendar.DAY_OF_MONTH)){
	       	 	hasReachedBirthday = false;
	    	//}
		}
	}
	
	// start with Y - Y, then deduct 0 or 1
	return now.get(Calendar.YEAR) - gcDOB.get(Calendar.YEAR) - (hasReachedBirthday ? 0 : 1);
}



/**
* This method calculates the age of a person based on today's date and the date
*  	passed to the method.  It expects the input string will be a date in
*   <CODE>mm-dd-yy</CODE> or <CODE>mm/dd/yy</CODE> format<br>
*
* @return int     current age  <B>(will return -1 if an error occurs)</B>
* @param   sDOB  the date of birth, as either mm-dd-yyyy or mm/dd/yyyy
**/
 
public static int calculateAge(String sDOB) 
{
    if (sDOB == null)
    {
	    return -1;
    }

    // if they only passed a two-digit year in a date of birth,
    //  we can't really make any assumptions ('getCalendar' does), 
    //  so we have to treat it as an error
    String[] dateParts = splitDate(sDOB);
    if (dateParts[2].length() < 4)  // if the year is not yyyy
    {
	    return -1;
    }
    
 	// if it still couldn't create the calendar for some reason,
 	//  return -1
    Calendar gcDOB = DateFunctions.getCalendar(dateParts[0],dateParts[1],dateParts[2]);
    if (gcDOB == null)
    {
	    return -1;
    }

   	return calculateAge(gcDOB);
}
/**
 *   Get the <B><code><font size=4>Calendar</font></code></B> for the
 *   date input
 *   @param   sDate Either mm-dd-yy or mm/dd/yy
 *   @return : Calendar object for the corresponding date,
 *               null if any one of the input parameter is null or not valid
 */
public static Calendar getCalendar(String sDate) {

	if (StringFunctions.isNullOrEmpty(sDate)) 
	{
        return null;
    }

    final String sDelimiters = "-/";

    StringTokenizer st = new StringTokenizer(sDate, sDelimiters);

    // Shouldn't be date
    if (st.countTokens() != 3) 
    {
        return null;
    }

    return getCalendar(st.nextToken(), st.nextToken(), st.nextToken());
}


/**
 *   return a Calendar object populated with the existing month, day, and year values
 *
 *   @param   <I>sMonth</I> : month from "1" to "12" <br>
 *   @param   <I>sDate</I> :  "1" to "31"<br>
 *   @param   <I>sYear</I> :  4 'digit' year in string
 *   @param   <I>sTimeZone</I> :  3 character identifier for a Time Zone (like 'GMT')
 *   @return : Calendar object for the corresponding date,
 *               null if any one of the input parameter is null or not valid
 */
public static Calendar getCalendar(String sMonth, String sDate, String sYear) {

	if (StringFunctions.isNullOrEmpty(sMonth) || 
		StringFunctions.isNullOrEmpty(sDate)  || 
		StringFunctions.isNullOrEmpty(sYear)) 
	{
        return null;
    } 
    else // Passed initial verification tests
    {
        int iDate, iMonth, iYear;

        try 
        {
            // invalid string to integer conversion will throw an exception
            //  e.g. the letter O, instead of zero, will fail
            iDate = Integer.parseInt(sDate);
            iYear = Integer.parseInt(sYear);
            iMonth = Integer.parseInt(sMonth);

            //  The following year checks were put in place to match the 3/99 MS
            //   implemenation.  One and two digit years are converted

            // Check for one or two digit year
            if (iYear >= 0 && iYear < 100) 
            {
                // convert 0 to 29 to 21st century
                if (iYear < 30) 
                {
                    iYear += 2000;
                }
                // convert 30 to 99 to 20th century
                else 
                {
                    iYear += 1900;
                }
            } //  else if ( iYear < 100 )

            GregorianCalendar cal = new GregorianCalendar();

            // Force validation of values by the method
            // Disallows things like:
            //   negative numbers,
            //   values larger than possible (13 months, 32 days)
            //   noncompliant number of days in the month (31 days in April)
            cal.setLenient(false);

            // Month is zero indexed
            iMonth--;
            cal.set(iYear, iMonth, iDate);

            // set doesn't throw exception, getTime will throw exception
            //   if setLenient is false and date is not valid
            cal.getTime();

            return cal;
        } 
        // try
        catch (Exception e) 
        {
            //e.printStackTrace();
            return null;
        } // catch
    } 

}


/**
 *   this is a special but perhaps utilitarian method, which return a Date
 *     representing Noon GMT for the date passed as input. <br>
 *   Noon GMT is useful because at that time the whole world is on the same day.<br>
 *   This technique is used as a standard in some financial transactions. <br>
 *
 *  <B>  NOTE:     if null is passed in, null will be returned  </B><br>
 *
 *   @param    aDate   a Date object to be converted to noon GMT
 *           NOTE:     if null is passed in, null will be returned
 *   @return : Calendar object representing Noon GMT for today's date
 */
public static Date getDateNoonGMT(Date aDate) {

	if (aDate == null)
	{
		return null;
	}
	Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
	cal.setTime(aDate);
	cal.set(Calendar.HOUR_OF_DAY,12);
	cal.set(Calendar.MINUTE,0);
	cal.set(Calendar.SECOND,0);
	cal.set(Calendar.MILLISECOND,0);
	cal.set(Calendar.AM_PM,Calendar.PM);
	return cal.getTime();
}


    /**
     * @see getSubmissionDate(Date)
     *
     * @return a String in the form: "[date] at [time]"
     */

    public static String getSubmissionDate()
    {
	    return getSubmissionDate(new Date());
    }


/**
 * Special method used when constructing date for display.
 * It is primarily used when displaying the date indicating
 *   the successful submission of a request.  It uses
 *   a format found in DateFormatter and applies some special
 *   logic to get it ready for display.<br>
 *
 * @param  a Date to format
 * @return a String in the form: "[date] at [time]"
 */
public static String getSubmissionDate(Date aDate)
{
	if (aDate == null)
	{
		return null;
	}
	
    String submissionDate = DateFormatter.formatDate(DateFormatter.DATE_AT_TIME_PATTERN, aDate);

    int dateLen = submissionDate.length();
    StringBuffer returnBuffer = new StringBuffer(dateLen + 2);

    returnBuffer.append(submissionDate.substring(0,3));
    // if the month was anything other than 'May', put three chars and '.'
    int firstSpace = submissionDate.indexOf(" ");
    if (firstSpace != 3)
    {
        returnBuffer.append(".");
    }
    returnBuffer.append(submissionDate.substring(firstSpace));

    return formatAmPm(returnBuffer.toString());
}


/**
 * Determines whether the input month, day, year represent a date
 * that is at least as old as the input age.
 *  
 *
 * @return boolean true if age is older than input date, false otherwise
 * @param age int representing age to compare
 * @param month String containing month of date for comparison
 * @param day String containing day of date for comparison
 * @param year String containing year of date for comparison
 */
public static boolean isDateAtLeastThisOld(int age, String sMonth, String sDay, String sYear) {

		
	int iMonth;
	int iDay;
	int iYear;

	try
	{
    	// create objects for comparing dates.
	  	GregorianCalendar argumentDate = new GregorianCalendar();
		GregorianCalendar currentDate = new GregorianCalendar();
  		GregorianCalendar compareDate = new GregorianCalendar();
      	argumentDate.setLenient(false);
      	currentDate.setLenient(false);
      	compareDate.setLenient(false);

      	// set the argumentDate using the arguments
      	// Calendar month is 0 indexed, make adjustment
		argumentDate.set(Integer.parseInt(sYear), Integer.parseInt(sMonth) - 1, Integer.parseInt(sDay));

      	// extract Year, Month and Day from the currentDate object.
	  	iMonth = currentDate.get(currentDate.MONTH);
		iDay = currentDate.get(currentDate.DATE);
  		iYear = currentDate.get(currentDate.YEAR);
  		
      	// subtract the number of years to compare from the extracted current year.
  		iYear = iYear- age;

      	// set the compareDate to the number of years prior indicated by the
      	// intAge argument to be compared against the argumentDate.
		compareDate.set(iYear, iMonth, iDay);

      	// set the Gregorian Calendar Objects to be all equal except for the date
      	// so that only the date will be different on a compare. 
  		compareDate.set(Calendar.HOUR, 0);
	  	compareDate.set(Calendar.HOUR_OF_DAY, 0);
		compareDate.set(Calendar.MINUTE, 0);
		compareDate.set(Calendar.SECOND, 0);
		compareDate.set(Calendar.MILLISECOND, 0);

  		argumentDate.set(Calendar.HOUR, 0);
	  	argumentDate.set(Calendar.HOUR_OF_DAY, 0);
		argumentDate.set(Calendar.MINUTE, 0);
		argumentDate.set(Calendar.SECOND, 0);
		argumentDate.set(Calendar.MILLISECOND, 0);

      	// compare the argumentDate with the compareDate to see if it comes after
      	// that date.  If so, then the argumentDate is not older than the number
      	// of years specified in the iAge argument.  if not, then the
      	// argumentDate is older than the number of years specified in the iAge
      	// argument.
		if (argumentDate.after(compareDate))
      	{
		  	return false;
      	}
    }
	catch ( Throwable e )
	{
		  return false;
	}
	
	return true;
}

/**
 * Given a date object, see if it represents a future date
 *
 * @return boolean  true if input date/time is greater than current 
 */

public static boolean isFutureDate(Date aDate)
{
	boolean result = false;

	if (aDate != null)
	{
		Date now = new Date();

		if (aDate.after(now))
		{
			result = true;
		}
	}
	return result;
}
public static boolean isTodayDate(Date aDate)
{
    boolean result = false;

    if (aDate != null)
    {
        Date now = new Date();

        if (aDate.compareTo(now) == 0)
        {
            result = true;
        }
    }
    return result;
}

/**
 * Given a date object, see if it represents a future date
 *
 * @return boolean  true if input date/time is greater than current 
 */

public static boolean isFutureOrTodayDate(Date aDate)
{
    boolean result = false;
    if (aDate != null)
    {
        Calendar c1 = GregorianCalendar.getInstance();
        Calendar c2 = GregorianCalendar.getInstance();
        c2.setTime(aDate);
        c1.set(c1.HOUR, 1);
        c1.set(c1.MINUTE, 0);
        c1.set(c1.SECOND, 0);
        c2.set(c2.HOUR, 23);
        c2.set(c2.MINUTE, 59);
        c2.set(c2.SECOND, 0);
        Date now = c1.getTime();
        aDate = c2.getTime();
        log.debug("aDate.after(now) : ");
        log.debug(aDate.after(now));
        log.debug("Date Compare To: ");
        log.debug(aDate.compareTo(now));
        if (aDate.after(now) || aDate.compareTo(now) == 0)
        {
            result = true;
        }
    }
    return result;
}
public static boolean isTodayDateExecptTime(String aDate)
{
    boolean result = false;
    if (aDate != null)
    {
    	String now = DateFormatter.getDateStr(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
        if (aDate.equalsIgnoreCase(now))
        {
            result = true;
        }
    }
    return result;
}

/**
* Take a formatted date string and split it into its components.
*   It expects the input string will be a date in one of the following
*     formats:
*   <PRE>
*   	mm-dd-yyyy, mm-dd-yy
*		mm/dd/yyyy, mm/dd/yy
*       mm dd yyyy  mm dd yy
*       mmddyyyy    mmddyy
*   </PRE>
*   It will always return 3 elements, some of which may be empty strings
*      if the fields could not be parsed correctly
*
* @return  String[]    month, day, and year in positions 0,1,2
* @param   String      the date to be parsed, in one of the valid 'mm dd yy' formats 
**/
 
public static String[] splitDate(String sDate) 
{
	final int ARRAY_SIZE = 3;
	
	String[] returnValue = new String[] {"","",""};
	
	if (sDate != null)
	{
		StringTokenizer st = new StringTokenizer(sDate," /-");
		int stSize = st.countTokens();

		if (stSize == 1)  // no delimiters
		{
			if (sDate.length() > 4)
			{
				returnValue[0] = sDate.substring(0,2);
				returnValue[1] = sDate.substring(2,4);
				returnValue[2] = sDate.substring(4);
			}
		}
		else
		{
			for (int i=0; i<stSize && i<ARRAY_SIZE; i++)
			{
				returnValue[i] = (String)st.nextToken();
			}
		}
		
	}
	return returnValue;
}

/**
 * Take a formatted date string and convert the characters
 *   'AM' or 'PM' to 'a.m.' or 'p.m.' (there is no date format
 *   in Java that does that). <br>
 * if the input string is null or does not contain 'AM' or 'PM',
 * it will be returned as-is. <br>
 *
 * @param  String   a formatted date
 * @return a String   with   PM or AM converted to p.m. or a.m.
 */
public static String formatAmPm(String aDateString)
{
	if (aDateString == null)
	{
		return null;

	}

	int dateLen = aDateString.length();
	
	StringBuffer returnBuffer = new StringBuffer(dateLen + 2);
	returnBuffer.append(aDateString);	
 
	if (aDateString.endsWith(DateFormatter.AM))
    {
        returnBuffer.replace(dateLen - 2, dateLen, DateFormatter.AM_DISPLAY);
    }
    else if (aDateString.endsWith(DateFormatter.PM))
    {
        returnBuffer.replace(dateLen - 2, dateLen, DateFormatter.PM_DISPLAY);
    }
    
    return returnBuffer.toString();
}

/**
* Take a date object and split it into its components, returning strings
*   representing numeric values for month, day, and year.
*   
*   It will always return 3 elements, some of which may be empty strings
*      if the fields could not be parsed correctly.
*   position 0 is the two-digit month  <br>
*   position 1 is the two-digit day  <br>
*   position 2 is the four-digit year <br>
*  
* @return  String[]    month, day, and year in positions 0,1,2
* @param   Date        the date to be parsed
**/
 
public static String[] splitDate(Date aDate) 
{
	//final int ARRAY_SIZE = 3;
	
	String[] returnValue = new String[] {"","",""};
	
	if (aDate != null)
	{
		returnValue[0] = DateFormatter.formatDate(DateFormatter.MM_PATTERN,aDate);
		returnValue[1] = DateFormatter.formatDate(DateFormatter.DD_PATTERN,aDate);
		returnValue[2] = DateFormatter.formatDate(DateFormatter.YYYY_PATTERN,aDate);
	}
	return returnValue;
}

/**
 * This function returns true if the date passed in is within
 * the window specified (+/- milliseconds) of the current date.  
 * For example, if milliseconds is set to 2000, the actual 
 * window is 4001 milliseconds.  The time can be 2 seconds older
 * or 2 seconds younger or the exact time.
 * If the either (or both) of the dates are null, false is returned.
 * Creation date: (09/07/2002 1:27:10 PM)
 * @return boolean true if date is within the specified window
 * @param dateToCheck Date that is to be within mils of the dateToCompareTo
 * @param milliseconds long the Check date can be less/greater than dateToCompareTo by this amount
 */
public static boolean isWithinWindow(Date dateToCheck, long milliseconds)
{
    /* Use the other interface to do the work */
    return isWithinWindow(dateToCheck, milliseconds, milliseconds);
}


/**
 * This function returns true if the date passed in is within
 * the window specified (+/- milliseconds) of the specified date.  
 * For example, if milliseconds is set to 2000, the actual 
 * window is 4001 milliseconds.  The time can be 2 seconds older
 * or 2 seconds younger or the exact time.
 * If the either (or both) of the dates are null, false is returned.
 * Creation date: (09/07/2002 1:27:10 PM)
 * @return boolean true if date is within the specified window
 * @param dateToCheck Date that is to be within mils of the dateToCompareTo
 * @param youngerThanMils long the Check date can be less than dateToCompareTo by this amount
 * @param olderThanMils long the Check date can be more than the dateToCompareTo by this amount
 */
public static boolean isWithinWindow(
    Date dateToCheck,
    long youngerThanMils,
    long olderThanMils)
{
    /* Get the current time */
    Date currentDate = new Date();

    /* Use the other interface to do the work */

    return isWithinWindow(dateToCheck, youngerThanMils, olderThanMils, currentDate);
}


/**
 * This function returns true if the date passed in is within
 * the window specified (+/- milliseconds) of the specified date.  
 * For example, if milliseconds is set to 2000, the actual 
 * window is 4001 milliseconds.  The time can be 2 seconds older
 * or 2 seconds younger or the exact time.
 * If the either (or both) of the dates are null, false is returned.
 * Creation date: (09/07/2002 1:27:10 PM)
 * @return boolean true if date is within the specified window
 * @param dateToCheck Date that is to be within mils of the dateToCompareTo
 * @param youngerThanMils long the Check date can be less than dateToCompareTo by this amount
 * @param olderThanMils long the Check date can be more than the dateToCompareTo by this amount
 * @param dateToCompareTo Date
 */
public static boolean isWithinWindow(
    Date dateToCheck,
    long youngerThanMils,
    long olderThanMils,
    Date dateToCompareTo)
{
    boolean result = false;

    /* Get the number of milliseconds */
    if (dateToCheck != null || dateToCompareTo != null)
    {
        long dCheck = dateToCheck.getTime();
        long dCompareTo = dateToCompareTo.getTime();

        /* If the date is younger, compare against the youngerThanMils */
        if (dCheck < dCompareTo)
        {
            if (dCompareTo - dCheck < youngerThanMils)
            {
                result = true;
            }
        }
        else
        {
        	/* Since the date is older, compare against the olderThanMils */
            if (dCheck - dCompareTo < olderThanMils)
            {
                result = true;
            }
        }

    }

    return result;
}


    /**
     * This function returns true if the date passed in is within
     * the window specified (+/- milliseconds) of the specified date.  
     * For example, if milliseconds is set to 2000, the actual 
     * window is 4001 milliseconds.  The time can be 2 seconds older
     * or 2 seconds younger or the exact time.
     * If the either (or both) of the dates are null, false is returned.
     * Creation date: (09/07/2002 1:27:10 PM)
     * @return boolean true if date is within the specified window
     * @param dateToCheck Date that is to be within mils of the dateToCompareTo
     * @param milliseconds long the Check date can be less/greater than dateToCompareTo by this amount
     * @param dateToCompareTo Date
     */
    public static boolean isWithinWindow(Date dateToCheck, long milliseconds, Date dateToCompareTo)
    {
        /* Use the interface that takes the youngerthan/olderthan mils */
        return isWithinWindow(dateToCheck, milliseconds, milliseconds, dateToCompareTo);
    }
    /* Add Day/Month/Year to a Date
    add() is used to add  values to a Calendar object. 
    You specify which Calendar field is to be affected by the operation 
    (Calendar.YEAR, Calendar.MONTH, Calendar.DATE). 
    */
        public static void addToDate(){
            log.debug("In the ADD Operation");
        //  String DATE_FORMAT = "yyyy-MM-dd";
           // String DATE_FORMAT = "dd-MM-yyyy";      //Refer Java DOCS for formats
         //   java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat(DATE_FORMAT);
            Calendar c1 = Calendar.getInstance();
            Date d1 = new Date();
            log.debug("Todays date in Calendar Format : "+c1);
            log.debug("c1.getTime() : "+c1.getTime());
            log.debug("c1.get(Calendar.YEAR): " + c1.get(Calendar.YEAR));
            log.debug("Todays date in Date Format : "+d1);
            c1.set(1999,0 ,20);         //(year,month,date)
            log.debug("c1.set(1999,0 ,20) : "+c1.getTime());
            c1.add(Calendar.DATE,40);
          //  log.debug("Date + 20 days is : " + sdf.format(c1.getTime()));
        }
        
     public static Date addToDate(int aNumber){
            log.debug("In the ADD Operation");
        //  String DATE_FORMAT = "yyyy-MM-dd";
         //   String DATE_FORMAT = "dd-MM-yyyy";      //Refer Java DOCS for formats
          //  java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat(DATE_FORMAT);
            Calendar c1 = Calendar.getInstance();
            c1.add(Calendar.DATE,aNumber);
         //   log.debug("Date " + aNumber + " days is : " + sdf.format(c1.getTime()));
           return c1.getTime();
        }
        
    /*Substract Day/Month/Year to a Date
        roll() is used to substract values to a Calendar object. 
        You specify which Calendar field is to be affected by the operation 
        (Calendar.YEAR, Calendar.MONTH, Calendar.DATE). 
        
        Note: To substract, simply use a negative argument. 
        roll() does the same thing except you specify if you want to roll up (add 1) 
        or roll down (substract 1) to the specified Calendar field. The operation only
        affects the specified field while add() adjusts other Calendar fields. 
        See the following example, roll() makes january rolls to december in the same 
        year while add() substract the YEAR field for the correct result
     
    */
        
        public static void subToDate(){
            log.debug("In the SUB Operation");
         //   String DATE_FORMAT = "dd-MM-yyyy";
         //   java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
            Calendar c1 = Calendar.getInstance(); 
            c1.set(1999, 0 , 20); 
          //  log.debug("Date is : " + sdf.format(c1.getTime()));
            
            // roll down, substract 1 month
            c1.roll(Calendar.MONTH, false); 
        //    log.debug("Date roll down 1 month : " + sdf.format(c1.getTime())); 
     
            c1.set(1999, 0 , 20); 
         //   log.debug("Date is : " + sdf.format(c1.getTime()));
            c1.add(Calendar.MONTH, -1); 
            // substract 1 month
         //   log.debug("Date minus 1 month : " + sdf.format(c1.getTime())); 
        }
        
        public static void daysBetween2Dates(){
            Calendar c1 = Calendar.getInstance();   //new GregorianCalendar();
            Calendar c2 = Calendar.getInstance();   //new GregorianCalendar();
            c1.set(1999, 0 , 20); 
            c2.set(1999, 0 , 22); 
            log.debug("Days Between "+c1.getTime()+"\t"+ c2.getTime()+" is");
            log.debug((c2.getTime().getTime() - c1.getTime().getTime())/(24*3600*1000));
        }
        
        public static int daysInMonth() {
            Calendar c1 = Calendar.getInstance();   //new GregorianCalendar();
            c1.set(1999, 6 , 20); 
            int year = c1.get(Calendar.YEAR);
         //   int month = c1.get(Calendar.MONTH);
//          int days = c1.get(Calendar.DATE);
            int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
            daysInMonths[1] += isLeapYear(year) ? 1 : 0;
            return daysInMonths[c1.get(Calendar.MONTH)];
        }
        
        public static void getDayofTheDate() {
            Date d1 = new Date();
            String day = null;
            DateFormat f = new SimpleDateFormat("EEEE");
            try {
             day = f.format(d1);
            }
            catch(Exception e) {
              e.printStackTrace();
              log.error(" Exception thrown by getDayofTheDate : " + e.getMessage());
            }
            log.debug("The dat for "+d1+" is "+day);
        }
        
        public static void getDayofTheDate(Date aDate) {
            String day = null;
            DateFormat f = new SimpleDateFormat("EEEE");
            try {
             day = f.format(aDate);
            }
            catch(Exception e) {
              e.printStackTrace();
              log.error(" Exception thrown by getDayofTheDate : " + e.getMessage());
            }
            log.debug("The dat for "+aDate+" is "+day);
       }
       /*
         * Generates a Date based on week day string
        */
        public static Date getWeekDayofTheDate(String aWeekDay) {
            String day = null;
            Date aDate = new Date();
            int counter = 0;
            DateFormat f = new SimpleDateFormat("EEEE");
            try {
                 day = f.format(aDate);
                 log.debug("The dat for "+aDate+" is "+day);
                 while(!aWeekDay.equalsIgnoreCase(day) && counter < 7)
                 {
                     aDate=add(aDate,1);
                     day=f.format(aDate);
                     ++counter;
                     log.debug("The dat for "+aDate+" is "+day);
                 }
            }
            catch(Exception e) {
              e.printStackTrace();
              log.error(" Exception thrown by getDayofTheDate : " + e.getMessage());
            }
            return aDate;
       }
        
        public static void validateAGivenDate() {
            String dt = "20011223";   
         //   String invalidDt = "20031315";
            String dateformat = "yyyyMMdd";   
            Date dt1=null; //, dt2=null;
            try {     
                SimpleDateFormat sdf = new SimpleDateFormat(dateformat);    
                sdf.setLenient(false);    
                dt1 = sdf.parse(dt);  
             //   dt2 = sdf.parse(invalidDt);   
                log.debug("Date is ok = " + dt1 + "(" + dt + ")");     
            }  
            catch (ParseException e) {     
                log.debug(e.getMessage()); 
            }  
            catch (IllegalArgumentException e) {    
                log.debug("Invalid date");     
            }
        }
        
        public static Date getDateByDayAndWeek(Date date,int weekCount,int day){
        	Calendar c1=Calendar.getInstance();
			c1.setTime(date);
			c1.set(Calendar.WEEK_OF_MONTH, weekCount);
			c1.set(Calendar.DAY_OF_WEEK, day);		
			log.debug("The day  " + 
					c1.getTime());
			return c1.getTime();
        }
        
        public static void compare2Dates(){
            SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            
            c1.set(2000, 02, 15);
            c2.set(2001, 02, 15);
            
            log.debug(fm.format(c1.getTime())+" is ");
            if(c1.before(c2)){
                log.debug("less than "+c2.getTime());
            }else if(c1.after(c2)){
                log.debug("greater than "+c2.getTime());
            }else if(c1.equals(c2)){
                log.debug("is equal to "+fm.format(c2.getTime()));
            }
        }
        
        public static boolean compareDatesByCompareTo(Date todayDate,Date oldDate) {
            //how to check if date1 is equal to date2
            if (oldDate.compareTo(todayDate) == 0) {
                log.debug((oldDate) + " and " + (todayDate) + " are equal to each other");
                return false;
            }

            //checking if date1 is less than date 2
            if (oldDate.compareTo(todayDate) < 0) {
                log.debug((oldDate) + " is less than " + todayDate);
                return true;
            }

            //how to check if date1 is greater than date2 in java
            if (oldDate.compareTo(todayDate) > 0) {
                log.debug((oldDate) + " is greater than " + todayDate);
                return false;
            }
            return false;
        }
     
        public static boolean isLeapYear(int year){
             if((year%100 != 0) || (year%400 == 0)){
                 return true;
             }
             return false;
        }
     
        public static int checkTodaysDay(){
    		Calendar c=Calendar.getInstance();
			c.setTime(new Date());
			return c.get(Calendar.DAY_OF_WEEK);
	    }   
        
        public static void main(String args[]) throws ParseException{
        	StringBuffer buffer = new StringBuffer();
        	Calendar cal = Calendar.getInstance();
        	cal.set(1994, 7,02 );
			buffer.append(DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, cal.getTime()));
			buffer.append(" ( ");
			SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
			buffer.append(DateFunctions.DateInWords(sdf.format( cal.getTime())));
			buffer.append(")");
			log.debug(buffer.toString());
            

        }
        
        /**
         * Return a day wich is a given number of month after this day.
         *
         * The actual number of days added depends on the staring day.
         * Subtracting a number of months can be done
         * by a negative argument to addMonths() or calling subtactMonths()
         * explicitly.
         * NOTE: addMonth(n) m times will in general give a different result
         * than addMonth(m*n). Add 1 month to January 31, 2005 will give
         * February 28, 2005.
         *
         * @param nMonths  Number of months to add.
         * @return  Day as requested.
         */
        public static Date addMonths(int nMonths)
        {
          // Create a clone
          Calendar calendar = Calendar.getInstance();

          // Add/remove the specified number of days
          calendar.add(Calendar.MONTH, nMonths);

          // Return new instance
          return calendar.getTime();
        }
        public static Date addMonths(Date selectedDate,int nMonths){
        	 // Create a clone
            Calendar cal= Calendar.getInstance();
        	cal.setTime(selectedDate);
        	cal.add(cal.MONTH, nMonths);
        	 return cal.getTime();
        }

        public static boolean compare2Dates(Date aDate){
            SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy");
            Calendar c1 = GregorianCalendar.getInstance();
            Calendar c2 = GregorianCalendar.getInstance();
            String aStrDate = fm.format(aDate);
            c1.setTime(DateFormatter.parseString("MM/dd/yyyy",aStrDate));
            c1.set(c1.HOUR, 0);
            c1.set(c1.MINUTE, 0);
            c1.set(c1.SECOND, 0);
            c2.setTime(new Date());
            log.debug(c1.getTime());
            log.debug(c2.getTime());
            if(c1.after(c2) || c1.compareTo(c2) == 0){
                return true;
            }else if(c1.before(c2)){
                return false;
            }else if(c1.equals(c2)){
                return true;
            }
            return false;
        }
        
        public static boolean compare2Dates(Date aDate,Date secondDate){
            SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy");
            Calendar c1 = GregorianCalendar.getInstance();
            Calendar c2 = GregorianCalendar.getInstance();
            String aStrDate = fm.format(aDate);
            String aStrSecondDate = fm.format(secondDate);
            c1.setTime(DateFormatter.parseString("MM/dd/yyyy",aStrDate));
            c1.set(c1.HOUR, 0);
            c1.set(c1.MINUTE, 0);
            c1.set(c1.SECOND, 0);
            c2.setTime(DateFormatter.parseString("MM/dd/yyyy",aStrSecondDate));
            c2.set(c1.HOUR, 0);
            c2.set(c1.MINUTE, 0);
            c2.set(c1.SECOND, 0);
            
            log.debug(c1.getTime());
            log.debug(c2.getTime());
            if(c1.after(c2) || c1.compareTo(c2) == 0){
                return true;
            }else if(c1.before(c2)){
                return false;
            }else if(c1.equals(c2)){
                return true;
            }
            return false;
        }
        
        public static long timeDifference(Date d2)
        {
            Date d1 = new Date();
            long dif = (d1.getTime() - d2.getTime()) / (1000*60*60*24);
            return dif * 1000 * 60 * 60;
        }
        
        public static Date addSecondsToDate(Date input,int seconds)
        {
        	try{
	        	Calendar calendar = Calendar.getInstance();
	        	calendar.setTime(input);
	        	calendar.add(Calendar.SECOND, seconds);
	            return calendar.getTime();
        	}
        	catch(Exception ex){
        		ex.printStackTrace();
        		return null;
        	}
        }
        
        public static int getDayOfMonth(Date input){

        	try{
	        	Calendar calendar = Calendar.getInstance();
	        	calendar.setTime(input);
	        	return calendar.get(Calendar.DAY_OF_MONTH);
        	}
        	catch(Exception ex){
        		ex.printStackTrace();
        		return 1;
        	}
        }
        public static int getDayOfYear(Date input){

        	try{
	        	Calendar calendar = Calendar.getInstance();
	        	calendar.setTime(input);
	        	return calendar.get(Calendar.YEAR);
        	}
        	catch(Exception ex){
        		ex.printStackTrace();
        		return 1;
        	}
        }
        public static int getMonthOfDate(Date input){

        	try{
	        	Calendar calendar = Calendar.getInstance();
	        	calendar.setTime(input);
	        	return calendar.get(Calendar.MONTH)+1;
        	}
        	catch(Exception ex){
        		ex.printStackTrace();
        		return 1;
        	}
        }
        public static int minutesBetween(Date todayDate,Date showTimeingDate){
        	try{
        		 long days;
        	        try{
        	           days = (showTimeingDate.getTime() - todayDate.getTime()) / 60000;
        	        }catch(Exception ex){
        	        	return 0;
        	        }
        	        return (int) days;
        	}catch (Exception e) {
				e.printStackTrace();
			}
        	return 0;
        }
        public static int minutesBetween(Date d1)
        {
            return minutesBetween(new Date(), d1);
        }
        public static int minutesBetween(String d1)
        {
            return minutesBetween(new Date(), DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMM_A_PATTERN, d1));
        }
        
        public static boolean compare2DatesWithTime(Date aDate){
            SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Calendar c1 = GregorianCalendar.getInstance();
            Calendar c2 = GregorianCalendar.getInstance();
            String aStrDate = fm.format(aDate);
            c1.setTime(DateFormatter.parseString("MM/dd/yyyy hh:mm aa",aStrDate));
            /*c1.set(c1.HOUR, 0);
            c1.set(c1.MINUTE, 0);
            c1.set(c1.SECOND, 0);*/
            c2.setTime(new Date());
            log.debug(c1.getTime());
            log.debug(c2.getTime());
            if(c1.after(c2) || c1.compareTo(c2) == 0){
                return true;
            }else if(c1.before(c2)){
                return false;
            }else if(c1.equals(c2)){
                return true;
            }
            return false;
        }
        
      /*returns integer value of the time.
       * For exmple 05:00 PM returns 61200000
       */
      public static synchronized int decodeTime (String s) throws Exception {
 		   SimpleDateFormat f = new SimpleDateFormat("hh:mm aa");
 		   TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
 		   f.setTimeZone (utcTimeZone);
 		   f.setLenient (false);
 		   ParsePosition p = new ParsePosition(0);
 		   Date d = f.parse(s,p);
 		   if (d == null || !isRestOfStringBlank(s,p.getIndex()))
 		      throw new Exception("Invalid time value (hh:mm aa): \"" + s + "\".");
 		   return (int)d.getTime();
    	}

    	public static boolean isRestOfStringBlank (String s, int p) {
 		   while (p < s.length() && Character.isWhitespace(s.charAt(p))) p++;
 		   return p >= s.length(); 
        }
   /*public static final String yearsInWords[] = { "1", "One", "2", "Two", "3", "Three", "4",
			"Four", "5", "Five", "6", "Six", "7", "Seven", "8",
				"Eight", "9", "Nine", "10", "Ten", "11", "Eleven",
				"12", "Twelve", "13", "Thirteen", "14", "Forteen",
				"15", "Fifteen", "16", "Sixteen", "17", "Seventeen",
				"18", "Eighteen", "19", "Nineteen", "20", "Twenty",
				"30", "Thirty", "40", "Forty", "50", "Fifty", "60",
				"Sixty", "70", "Seventy", "80", "Eighty", "90",
				"Ninty", "100", "Hundred" };*/
   private static final Map<String, String> daysInWords;
   private static final Map<String, String> yearsInWords;
   static {
       Map<String, String> aMap = new HashMap<String, String>();
       aMap.put("1", "First");
       aMap.put("2", "Second");
       aMap.put("3", "Third");
       aMap.put("4", "Fourth");
       aMap.put("5", "Fifth");
       aMap.put("6", "Sixth");
       aMap.put("7", "Seventh");
       aMap.put("8", "Eighth");
       aMap.put("9", "Ninth");
       aMap.put("10", "Tenth");
       aMap.put("11", "Eleventh");
       aMap.put("12", "Twelfth");
       aMap.put("13", "Thirteenth");
       aMap.put("14", "Fourteenth");
       aMap.put("15", "Fifteenth");
       aMap.put("16", "Sixteenth");
       aMap.put("17", "Seventeenth");
       aMap.put("18", "Eighteenth");
       aMap.put("19", "Nineteenth");
       aMap.put("20", "Twentieth");
       aMap.put("30", "Thirtieth");
       daysInWords = Collections.unmodifiableMap(aMap);

       aMap = new HashMap<String, String>();
       aMap.put("1", "One");
       aMap.put("2", "Two");
       aMap.put("3", "Three");
       aMap.put("4", "Four");
       aMap.put("5", "Five");
       aMap.put("6", "Six");
       aMap.put("7", "Seven");
       aMap.put("8", "Eight");
       aMap.put("9", "Nine");
       aMap.put("10", "Ten");
       aMap.put("11", "Eleven");
       aMap.put("12", "Twelve");
       aMap.put("13", "Thirteen");
       aMap.put("14", "Fourteen");
       aMap.put("15", "Fifteen");
       aMap.put("16", "Sixteen");
       aMap.put("17", "Seventeen");
       aMap.put("18", "Eighteen");
       aMap.put("19", "Nineteen");
       aMap.put("20", "Twenty");
       aMap.put("30", "Thirty");
       aMap.put("40", "Forty");
       aMap.put("50", "Fifty");
       aMap.put("60", "Sixty");
       aMap.put("70", "Seventy");
       aMap.put("80", "Eighty");
       aMap.put("90", "Ninety");
       aMap.put("100", "Hundred");
       yearsInWords = Collections.unmodifiableMap(aMap);
   } 	
   
      public static String DateInWords(String dateStr){
    	StringBuffer buffer = new StringBuffer();
  		Pattern p1 = Pattern.compile(REGEX);
  		String[] items = p1.split(dateStr);
  		for (int i1 = 0; i1 < items.length; i1++) {
  			if (StringFunctions.isNumeric(items[i1])) {
  				log.debug(items[i1]);
  				String a;
  				Scanner s = new Scanner(items[i1]);
  				a = s.next();
  				int b = a.length();
  				if (b == 4) {
  					String s1 = a.substring(0, 1);
  					String s2 = a.substring(1, 2);
  					String s3 = a.substring(2, 3);
  					String s4 = a.substring(3, 4);
  					String s10 = a.substring(2, 4);
					if (!s1.equals("0") && StringFunctions.isNotNullOrEmpty(yearsInWords.get(s1)))
						buffer.append(yearsInWords.get(s1)).append(" Thousand ");
					if (!s2.equals("0") && StringFunctions.isNotNullOrEmpty(yearsInWords.get(s2)))
  						buffer.append(yearsInWords.get(s2)).append(" Hundred ");
  					if (!s10.equals("00")){
  						if (s3.equals("1")) {
  							buffer.append(yearsInWords.get(s10));
  						} else {
  							if(StringFunctions.isNotNullOrEmpty(yearsInWords.get(s3+"0")))
  								buffer.append(yearsInWords.get(s3+"0")).append(" ");
  							if(StringFunctions.isNotNullOrEmpty(yearsInWords.get(s4)))
  								buffer.append(yearsInWords.get(s4)).append(" ");
  						}
  					}
  				} else if (b == 3) {
  				//	String s1 = a.substring(0, 1);
  					String s2 = a.substring(1, 2);
  					String s3 = a.substring(2, 3);
  				//	String s10 = a.substring(1, 3);
  					if(!s2.equals("1")){
  						buffer.append(yearsInWords.get(s2));
  						buffer.append(yearsInWords.get(s3));
  					}
  				} else if (b == 2) {
  					String s1 = a.substring(0, 1);
  					String s2 = a.substring(1, 2);
  					String s10 = a.substring(0, 2);
  					{
  						if (!s10.equals("00")){
							if (s1.equals("1")) {
	  							buffer.append(daysInWords.get(s10)).append(" ");
	  						} else if(s2.equals("0")){
	  							buffer.append(daysInWords.get(s10)).append(" ");
	  						}else{
	  							buffer.append(yearsInWords.get(s1+"0"));
	  							if(!"0".equals(s2))
	  								buffer.append(daysInWords.get(s2).toLowerCase()).append(" ");
	  						}
						}
  					}
  				} else if (b == 1) {
  					String s1 = a.substring(0, 1);
  					buffer.append(daysInWords.get(s1));
  					/*for (int q = 0; q <= 40; q++)
  						if (daysInWords[q].equals(s1))
  							buffer.append(yearsInWords[q + 1]);*/
  					buffer.append(" ");
  				}
  				log.debug("\n");

  			} else {
  				if(buffer.length() > 0)
  					buffer.append(" ");
  				buffer.append(items[i1]);
  				buffer.append(" ");
  			}
  			//log.debug("buffer----" + buffer.toString());
  		}

  		return buffer.toString();

  	}
      public static String getMonthForInt(int m) {
    	    String month = "invalid";
    	    DateFormatSymbols dfs = new DateFormatSymbols();
    	    String[] months = dfs.getMonths();
    	    m-=1;
    	    if (m >= 0 && m <= 11 ) {
    	        month = months[m];
    	    }
    	    return month;
    	}
      public static boolean compareDateOfBirths(Date todayDate,Date oldDate) {
          //how to check if date1 is equal to date2
          if (oldDate.compareTo(todayDate) == 0) {
              log.debug((oldDate) + " and " + (todayDate) + " are equal to each other");
              return false;
          }
          //checking if date1 is less than date 2
          if (oldDate.compareTo(todayDate) < 0) {
        	    Calendar cal = Calendar.getInstance();
        	    cal.setTime(todayDate);
        	    int year = cal.get(Calendar.YEAR);
        	  //  int month = cal.get(Calendar.MONTH);
        	 //   int day = cal.get(Calendar.DAY_OF_MONTH);
        	 //   Calendar oldcal = Calendar.getInstance();
        	    cal.setTime(oldDate);
        	    int oldyear = cal.get(Calendar.YEAR);
        	  //  int oldMonth = cal.get(Calendar.MONTH);
        	    int range= year-oldyear;
        	    if(range>2){
        	    	log.debug(oldDate + " is less than " + todayDate);
                    return true;
        	    }else{
        	    	return false;
        	    }
          }
          //how to check if date1 is greater than date2 in java
          if (oldDate.compareTo(todayDate) > 0) {
              log.debug(oldDate + " is greater than " + todayDate);
              return false;
          }
          return false;
      }

	public static int getNumberOfMonths(Date fromDate, Date toDate) {
		int monthCount = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		int c1date = cal.get(Calendar.DATE);
		int c1month = cal.get(Calendar.MONTH);
		int c1year = cal.get(Calendar.YEAR);
		cal.setTime(toDate);
		int c2date = cal.get(Calendar.DATE);
		int c2month = cal.get(Calendar.MONTH);
		int c2year = cal.get(Calendar.YEAR);

		monthCount = (c2year - c1year) * 12 + c2month - c1month + (c2date >= c1date ? 1 : 0);
		log.debug(monthCount);
		return monthCount;
	}
	
	public static String getMonthFullName(int monthNumber)
	{
		 String monthName="";
		 
		 if(monthNumber>=0 && monthNumber<12)
		 try
		 {
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(Calendar.MONTH, monthNumber);
		 
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
		 simpleDateFormat.setCalendar(calendar);
		 monthName = simpleDateFormat.format(calendar.getTime());
		 } catch (Exception e)
		 {
		 if(e!=null)
		 e.printStackTrace();
		 }
		 
		 return monthName;
	 }
	
	public static boolean isValidDay(int month, int day)
    {
        if (month == 1 && day >= 1 && month == 1 && day == 31)
        {
            return true;
        }
        if (month == 2 && day >= 1 && (month == 2 && day == 29 || day == 28))
        {
            return true;
        }
        if (month == 3 && day >= 1 && month == 3 && day == 31)
        {
            return true;
        }
        if (month == 4 && day >= 1 && month == 4 && day == 30)
        {
            return true;
        }
        if (month == 5 && day >= 1 && month == 5 && day == 31)
        {
            return true;
        }
        if (month == 6 && day >= 1 && month == 6 && day == 30)
        {
            return true;
        }
        if (month == 7 && day >= 1 && month == 7 && day == 31)
        {
            return true;
        }
        if (month == 8 && day >= 1 && month == 8 && day == 31)
        {
            return true;
        }
        if (month == 9 && day >= 1 && month == 9 && day == 30)
        {
            return true;
        }
        if (month == 10 && day >= 1 && month == 10 && day == 31)
        {
            return true;
        }
        if (month == 11 && day >= 1 && month == 11 && day == 31)
        {
            return true;
        }
        if (month == 12 && day >= 1 && month == 12 && day == 31)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	public static final double DAYS_IN_YEAR = 365.242;
    public static final double DAYS_IN_MONTH = DAYS_IN_YEAR / 12d;
    
	public static String calculateExperience(Date fromDate, Date toDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		int c1date = cal.get(Calendar.DATE);
		int c1month = cal.get(Calendar.MONTH);
		int c1year = cal.get(Calendar.YEAR);
		
		cal.setTime(toDate);
		int c2date = cal.get(Calendar.DATE);
		int c2month = cal.get(Calendar.MONTH);
		int c2year = cal.get(Calendar.YEAR);
		
		double time = toDays(c1year, c1month, c1date);
        // One day less of the year 2015...
        double today = toDays(c2year, c2month, c2date); // today...

        double diff = today - time;

        int years = (int)Math.round(diff / DAYS_IN_YEAR);
        double over = diff % DAYS_IN_YEAR;
        int months = (int)(over / DAYS_IN_MONTH);
        over = over % DAYS_IN_MONTH;
        int days = (int) over;

        log.debug(years + " years, " + months + " months, " + days + " days");           
        log.debug(NumberFormat.getNumberInstance().format(diff / DAYS_IN_YEAR));
        		
        		
		return years+"."+months;
	}
	
	public static double toDays(int year, int month, int day) {  
        return year * DAYS_IN_YEAR + month * DAYS_IN_MONTH + day;            
    }
	
	public static int getMonthNumberByMonthName(String monthName) 
	{
		Date date;
		int monthNumber = 0;
		try {
			date = new SimpleDateFormat("MMM").parse(monthName);//put your month name here
			Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    monthNumber=cal.get(Calendar.MONTH) + 1;
		    
		    log.debug(cal.getActualMaximum(cal.DAY_OF_MONTH));
		    cal = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
       
		return monthNumber;
	}
	
	public static String geMonthNameByMonthId(int monthId) 
	{
		if (monthId >= 1 && monthId <= 12 ) {
			 String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			    return monthNames[monthId-1];
		 }
		return "";
	}
	
	public static int getActualMonthDaysByMonthId(int monthId) 
	{
		if(monthId > 0) // month Id starts from 1 to 12
		{
			Calendar calendar =Calendar.getInstance();
	        calendar.add(Calendar.MONTH, monthId);
			return  calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		}
		return 0;
	}
	
	public static int geActualMonthDaysByMonthName(String monthName) 
	{
		Date date;
		int actualMonthDays = 0;
		try {
			date = new SimpleDateFormat("MMM").parse(monthName);//put your month name here
			Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		   // monthNumber=cal.get(Calendar.MONTH) + 1;
		    actualMonthDays= cal.getActualMaximum(cal.DAY_OF_MONTH);
		    cal = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
       
		return actualMonthDays;
	}
	
	public static HashMap<Integer,String> ajaxGetAllMonthNamesMonthIdByStartAndEndDate(Date fstartDate, Date fendDate) {
		int monthId;
		int yearId;
  		if(!ObjectFunctions.isNullOrEmpty(fstartDate) && !ObjectFunctions.isNullOrEmpty(fendDate)){
  			HashMap<Integer,String> academicYearmonths = new HashMap<Integer,String>();
  			Calendar cal = Calendar.getInstance();
			cal.setTime(fstartDate);
			for(Calendar startDate=cal;DateFunctions.compare2Dates(fendDate, startDate.getTime());){
    			monthId=DateFunctions.getMonthOfDate(startDate.getTime());
				java.util.Date d = new java.util.Date(startDate.getTimeInMillis());
				academicYearmonths.put(monthId,new SimpleDateFormat("MMMM").format(d));
				yearId = DateFunctions.getDayOfYear(startDate.getTime());
				//log.debug("monthId"+monthId+"MonthName "+new SimpleDateFormat("MMMM").format(d)+"yearId"+yearId);
				startDate.add(Calendar.MONTH, 1);
				startDate.set(Calendar.DATE, 1);
			}
  			return academicYearmonths;
			}
	  	return null;
	  }
	public static Date convertStringToDate(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date newDate = formatter.parse(date);
			return newDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertDateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String convertedDate = null;
		if (date != null) {
			convertedDate = formatter.format(date);
		}
		return convertedDate;
	}
	public static String convertDateToYYYYMMDDString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String convertedDate = null;
		if (date != null) {
			convertedDate = formatter.format(date);
		}
		return convertedDate;
	}
	public static String convertDteToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd,yyyy");
		String convertedDate = null;
		if (date != null) {
			convertedDate = formatter.format(date);
		}
		return convertedDate;
	}
	
	public static Date getDatePlusNdays(Date date,int nDays)
    {
        return add(date, nDays);
    }
	public static Date convertDDMMMYYYYStringToDate(String dateStr) {
		try {
			if(!StringFunctions.isNullOrEmpty(dateStr)){
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				Date date = dateFormat.parse(dateStr);
				return date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}

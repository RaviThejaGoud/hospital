package com.churchgroup.util.date;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Date Utility Class
 * This is used to convert Strings to Dates and Timestamps
 *
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:jsreeram29@yahoo.com">Sreeram J</a>
 */
public class DateUtil {
    //~ Static fields/initializers =============================================

    private static Log log = LogFactory.getLog(DateUtil.class);
    //private static String defaultDatePattern = null;
    private static String timePattern = "HH:mm";
    static GregorianCalendar calendar = new GregorianCalendar();

    /** Constant for Sunday */
    public static String SUNDAY = "Sunday";

    /** Constant for Monday */
    public static String MONDAY = "Monday";

    /** Constant for Tuesday */
    public static String TUESDAY = "Tuesday";

    /** Constant for Wednesday */
    public static String WEDNESDAY = "Wednesday";

    /** Constant for Thursday */
    public static String THURSDAY = "Thursday";

    /** Constant for Friday */
    public static String FRIDAY = "Friday";

    /** Constant for Saturday */
    public static String SATURDAY = "Saturday";

    //~ Methods ================================================================

    /**
     * Return default datePattern (MM/dd/yyyy)
     * @return a string representing the date pattern on the UI
     */
    public static synchronized String getDatePattern() {
        return "dd/MM/yyyy";
    }
    
    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return returnValue;
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
      throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                      + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return date;
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     * 
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * 
     * @see java.text.SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return returnValue;
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     * 
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * 
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate)
      throws ParseException {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + getDatePattern());
            }

            aDate = convertStringToDate(getDatePattern(), strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate
                      + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(),
                                     pe.getErrorOffset());
                    
        }

        return aDate;
    }

    /**
     * This method returns nearest Date when the given dayStr falls.<br>
     * <b>For Example:</b> Date when next Sunday falls after today.
     * 
     * @param dayStr
     * @return Date
     * 
     * @see DateUtil#getDayValue(String)
     * @see DateUtil#getNextDateForDay(int)
     */
    public static Date getNextDateForDay(String dayStr)
    {
        log.debug(" getNextDateForDay : " + dayStr);
        return getNextDateForDay(getDayValue(dayStr));
    }

    /**
     * This method returns nearest Date when the given day falls.<br>
     * <b>For Example:</b> Date when next Calendar.SUNDAY falls after today.
     * Note: The day is considered to be one of Calendar day constants.
     * 
     * @param day
     * @return Date
     * 
     * @see DateUtil#getNextDateForDay(String)
     */
    public static Date getNextDateForDay(int day)
    {
        return getNextDateForDay(day, new GregorianCalendar());
    }

    /**
     * This method returns nearest Date when the given day falls after given
     * "calendar".<br>
     * <b>For Example:</b> Date when next Calendar.SUNDAY falls after given
     * "calendar".<br>
     * Note: The day is considered to be one of Calendar day constants.
     * 
     * @param day
     * @param calendar
     * @return Date
     * 
     * @see DateUtil#getNextDateForDay(String)
     */
    public static Date getNextDateForDay(int day,
            Calendar calendar)
    {
        Date eqDate = null;

        if (isValidDay(day) && calendar != null)
        {
            int dayOfToday = calendar.get(Calendar.DAY_OF_WEEK);
            int difference = day - dayOfToday;
            if (difference <= 0)
            {
                difference = difference + 7;
            }
            calendar.add(Calendar.DAY_OF_MONTH, difference);
            eqDate = calendar.getTime();
        }
        return eqDate;
    }
    
    /**
     * Returns true if the given day is valid. Determines whether day 
     * is between Calendar.SUNDAY and Calendar.SATURDAY.
     * @param day
     * @return boolean Returns true if day is valid.
     */
    public static boolean isValidDay(int day)
    {
        return day >= Calendar.SUNDAY && day <= Calendar.SATURDAY;
    }

    /**
     * This method returns equalent int value for a given day.<br>
     * <b>For Example:</b> Returns Calendar.SUNDAY for "Sunday".
     * 
     * @param dayStr
     * @return Date
     * 
     * @see DateUtil#getDayValue(String)
     */
    public static int getDayValue(String dayStr)
    {
        int day = -1;
        if (dayStr != null)
        {
            dayStr = dayStr.trim();
            if (DateUtil.SUNDAY.equalsIgnoreCase(dayStr))
            {
                day = Calendar.SUNDAY;
            }
            else if (DateUtil.MONDAY.equalsIgnoreCase(dayStr))
            {
                day = Calendar.MONDAY;
            }
            else if (DateUtil.TUESDAY.equalsIgnoreCase(dayStr))
            {
                day = Calendar.TUESDAY;
            }

            else if (DateUtil.WEDNESDAY.equalsIgnoreCase(dayStr))
            {
                day = Calendar.WEDNESDAY;
            }

            else if (DateUtil.THURSDAY.equalsIgnoreCase(dayStr))
            {
                day = Calendar.THURSDAY;
            }

            else if (DateUtil.FRIDAY.equalsIgnoreCase(dayStr))
            {
                day = Calendar.FRIDAY;
            }
            else if (DateUtil.SATURDAY.equalsIgnoreCase(dayStr))
            {
                day = Calendar.SATURDAY;
            }
        }
        log.debug(" getDayValue : " + day);
        return day;
    }

    /**
     * Returns the first given day of the current month.<br>
     * Here the dayStr is given as string.
     * For Example: First "Sunday" of the current month.<br>
     * @param dayStr
     * @return Date
     */
    public static Date getFirstGivenDayOfMonth(String dayStr)
    {
        log.debug("getFirstGivenDayOfMonth(string) : " + dayStr);
        return getFirstGivenDayOfMonth(getDayValue(dayStr));
    }
    
    /**
     * Returns the first given day of the current month. <br>
     * Here day is given as integer.
     * For Example: First Calendar.SUNDAY of the current month. 
     * @param day
     * @return Date
     */
    public static Date getFirstGivenDayOfMonth(int day)
    {
        Date result = null;
        if (isValidDay(day))
        {
            Calendar firstDateOfMonth = new GregorianCalendar();
            log.debug("Current Day of Month : " + Calendar.DAY_OF_MONTH);
            firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
            log.debug("firstDateOfMonth : " + firstDateOfMonth.getTime());
            int firstDayOfMonth = firstDateOfMonth.get(Calendar.DAY_OF_WEEK);
            result = firstDateOfMonth.getTime();
            if (day != firstDayOfMonth)
            {
                result = getNextDateForDay(day, firstDateOfMonth);
            }            
            //Check if today is greater than result and recalucate the date.
            Calendar todayCal = new GregorianCalendar();
            Date today = todayCal.getTime();
            
            if(today.after(result))
            {
               // firstDateOfMonth.add(Calendar.MONTH, 1);
                result = getNextDateForDay(day, firstDateOfMonth);
            }
        }
        return result;
    }
    
    public static void main(String [] args)
    {
        /*String memberName = new String();
        memberName = "urt5 test (urt5@pachinkoman.com, )";
        int pos = memberName.indexOf("(");
        log.debug(" Memmber Name 1 : " + memberName.substring(0,pos));*/
    /*	log.debug(prepareRSSDateFormat(new Date()));
        Date date=DateFunctions.getTodayPlusNdays(9);
        log.debug(date);
    	log.debug("----Date is 1----"+getFirstGivenDayOfMonth(date,"Sunday"));
    	log.debug(Calendar.SUNDAY);
    	log.debug(Calendar.SATURDAY);
    	log.debug("----Date is 2----"+getFirstGivenDayOfMonth("Sunday"));
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Calendar c1 = GregorianCalendar.getInstance();
        c1.setTime(DateFormatter.parseString("MM/dd/yyyy","01/06/2008"));
        
         
        log.debug(" Next Sunday: "  + DateUtil.getEndOfMonth(c1.getTime(),3));
        log.debug(" Days between: "  + DateFunctions.daysBetween(cal.getTime(), DateUtil.getNextDateForDay(Calendar.SUNDAY)));
       /* cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.AM_PM,Calendar.AM);
        log.debug(" cal.getTime() 2: "  + DateUtil.getDateTime("hh:mm a", cal.getTime()));
        String strTime = DateUtil.getDateTime("HH", cal.getTime());
        log.debug(" cal.getTime() 3: " + Long.valueOf(strTime) + " => " + DateUtil.getDateTime("HH:mm a", cal.getTime()));
        int rHours = (int) ((24 - Long.valueOf(strTime))*4);
        String[] strsTime;
        for(int i=0;i<rHours; i++)
        {
            strsTime = "16:45 PM".split(" ");
            log.debug(" i : " + i+ " => " + DateUtil.getDateTime("h:mm a", cal.getTime())+ " Index1 :" + strsTime[0]+ " Index2 :" + strsTime[1]);
            cal.add(Calendar.MINUTE, 15);
        }
        
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        cal.set(Calendar.AM_PM,Calendar.PM);
        log.debug(" cal.getTime() 2: " + cal.getTime());*/
    	log.debug(" Date " + DateUtil.getDateTime(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,DateUtil.getLast2HourOfDay(new Date())));
    }
    
    public static Date getStartDate(Date date){
        calendar.setTime(date);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, calendar.getActualMinimum(GregorianCalendar.HOUR_OF_DAY));
        calendar.set(GregorianCalendar.MINUTE, calendar.getActualMinimum(GregorianCalendar.MINUTE));
        calendar.set(GregorianCalendar.SECOND, calendar.getActualMinimum(GregorianCalendar.SECOND));
        calendar.set(GregorianCalendar.MILLISECOND, calendar.getActualMinimum(GregorianCalendar.MILLISECOND));      
        return (Date) calendar.getTime().clone();
    }
    public static Date getEndDate(Date date){
        calendar.setTime(date);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY));
        calendar.set(GregorianCalendar.MINUTE, calendar.getActualMaximum(GregorianCalendar.MINUTE));
        calendar.set(GregorianCalendar.SECOND, calendar.getActualMaximum(GregorianCalendar.SECOND));
        calendar.set(GregorianCalendar.MILLISECOND, calendar.getActualMaximum(GregorianCalendar.MILLISECOND));      
        return (Date) calendar.getTime().clone();
    }
    public static Date getNextDay(Date date){
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        return (Date) calendar.getTime().clone();
    }
    public static Date getNextNDay(Date date, int n){
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DAY_OF_MONTH, n);
        return (Date) calendar.getTime().clone();
    }
    public static Date getBeginOfMonth(Date date, int months){
        calendar.setTime(date);
        if(months!=0)
            calendar.add(GregorianCalendar.MONTH, months);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar.getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
        return (Date) calendar.getTime().clone();
    }
    
    public static Date getEndOfMonth(Date date, int months){
        calendar.setTime(date);
        if(months!=0)
            calendar.add(GregorianCalendar.MONTH, months);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar
                .getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        return (Date) calendar.getTime().clone();
    }
    public static Date getBeginOfYear(Date date){
        calendar.setTime(date);
        calendar.set(GregorianCalendar.MONTH, calendar
                .getActualMinimum(GregorianCalendar.MONTH));
        calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar
                .getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
        calendar.set(GregorianCalendar.HOUR_OF_DAY, calendar.getActualMinimum(GregorianCalendar.HOUR_OF_DAY));
        calendar.set(GregorianCalendar.MINUTE, calendar.getActualMinimum(GregorianCalendar.MINUTE));
        calendar.set(GregorianCalendar.SECOND, calendar.getActualMinimum(GregorianCalendar.SECOND));
        calendar.set(GregorianCalendar.MILLISECOND, calendar.getActualMinimum(GregorianCalendar.MILLISECOND));      
        return (Date) calendar.getTime().clone();
    }
    
    public static Date getEndOfYear(Date date) {
        calendar.setTime(date);
        calendar.set(GregorianCalendar.MONTH, calendar
                .getActualMaximum(GregorianCalendar.MONTH));
        calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar
                .getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        calendar.set(GregorianCalendar.HOUR_OF_DAY, calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY));
        calendar.set(GregorianCalendar.MINUTE, calendar.getActualMaximum(GregorianCalendar.MINUTE));
        calendar.set(GregorianCalendar.SECOND, calendar.getActualMaximum(GregorianCalendar.SECOND));
        calendar.set(GregorianCalendar.MILLISECOND, calendar.getActualMaximum(GregorianCalendar.MILLISECOND));      
        return (Date) calendar.getTime().clone();
    }
    public static int getYearOfDate(Date date){
        return calendar.get(Calendar.YEAR);
    }
    
    public static Date getEndOfYear(int year) {
        calendar.set(GregorianCalendar.YEAR, year);
        calendar.set(GregorianCalendar.MONTH, calendar
                .getActualMaximum(GregorianCalendar.MONTH));
        calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar
                .getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        calendar.set(GregorianCalendar.HOUR_OF_DAY, calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY));
        calendar.set(GregorianCalendar.MINUTE, calendar.getActualMaximum(GregorianCalendar.MINUTE));
        calendar.set(GregorianCalendar.SECOND, calendar.getActualMaximum(GregorianCalendar.SECOND));
        calendar.set(GregorianCalendar.MILLISECOND, calendar.getActualMaximum(GregorianCalendar.MILLISECOND));      
        return (Date) calendar.getTime().clone();
    }
    
    public static int monthsBetween(Date startDate, Date endDate) {
        calendar.setTime(startDate);
        int startMonth = calendar.get(GregorianCalendar.MONTH);
        int startYear = calendar.get(GregorianCalendar.YEAR);
        calendar.setTime(endDate);
        int endMonth = calendar.get(GregorianCalendar.MONTH);
        int endYear = calendar.get(GregorianCalendar.YEAR);
        return (endYear-startYear)*12 + endMonth-startMonth;
    }
    
 public static final long millisInDay = 86400000;
    
    // a bunch of date formats
    private static final String formatDefaultDate = "dd.MM.yyyy";
    private static final String formatDefaultDateMinimal = "d.M.yy";
    private static final String formatDefaultTimestamp = "yyyy-MM-dd HH:mm:ss.SSS";
    
    private static final String formatFriendlyTimestamp = "dd.MM.yyyy HH:mm:ss";
    
    private static final String format6chars = "yyyyMM";
    private static final String format8chars = "yyyyMMdd";
    
    private static final String formatIso8601 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String formatIso8601Day = "yyyy-MM-dd";
    
    private static final String formatRfc822 = "EEE, d MMM yyyy HH:mm:ss Z";
    
    
    /**
     * Returns a Date set to the first possible millisecond of the day, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day) {
        return getStartOfDay(day, Calendar.getInstance());
    }
    
    
    /**
     * Returns a Date set to the first possible millisecond of the day, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE,      cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }
    
    
    /**
     * Returns a Date set to the last possible millisecond of the day, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfDay(Date day) {
        return getEndOfDay(day,Calendar.getInstance());
    }
    
    
    public static Date getEndOfDay(Date day,Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE,      cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }
    
    
    /**
     * Returns a Date set to the first possible millisecond of the hour.
     * If a null day is passed in, a new Date is created.
     */
    public static Date getStartOfHour(Date day) {
        return getStartOfHour(day, Calendar.getInstance());
    }
    
    
    /**
     * Returns a Date set to the first possible millisecond of the hour.
     * If a null day is passed in, a new Date is created.
     */
    public static Date getStartOfHour(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.MINUTE,      cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }
    
    
    /**
     * Returns a Date set to the last possible millisecond of the day, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfHour(Date day) {
        return getEndOfHour(day, Calendar.getInstance());
    }
    
    public static Date getLast2HourOfDay(Date day) {
        return getLast2HourOfDay(day, Calendar.getInstance());
    }
    
    public static Date getLast30SecsOfDay(Date day) {
        return getLast30SecsOfDay(day, Calendar.getInstance());
    }
    
    public static Date getEndOfHour(Date day, Calendar cal) {
        if (day == null || cal == null) {
            return day;
        }
        
        cal.setTime(day);
        cal.set(Calendar.MINUTE,      cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }
    
    public static Date getLast2HourOfDay(Date day, Calendar cal) {
        if (day == null || cal == null) {
            return day;
        }
        
        cal.setTime(day);
        cal.set(Calendar.HOUR,     cal.get(Calendar.HOUR)-1);
       
        return cal.getTime();
    }
    
    public static Date getLast30SecsOfDay(Date day, Calendar cal) {
        if (day == null || cal == null) {
            return day;
        }
        
        cal.setTime(day);
        cal.set(Calendar.SECOND,     cal.get(Calendar.SECOND)-30);
       
        return cal.getTime();
    }
    
    /**
     * Returns a Date set to the first possible millisecond of the minute.
     * If a null day is passed in, a new Date is created.
     */
    public static Date getStartOfMinute(Date day) {
        return getStartOfMinute(day, Calendar.getInstance());
    }
    
    
    /**
     * Returns a Date set to the first possible millisecond of the minute.
     * If a null day is passed in, a new Date is created.
     */
    public static Date getStartOfMinute(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.SECOND,      cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }
    
    
    /**
     * Returns a Date set to the last possible millisecond of the minute.
     * If a null day is passed in, a new Date is created.
     */
    public static Date getEndOfMinute(Date day) {
        return getEndOfMinute(day, Calendar.getInstance());
    }
    
    
    public static Date getEndOfMinute(Date day, Calendar cal) {
        if (day == null || cal == null) {
            return day;
        }
        
        cal.setTime(day);
        cal.set(Calendar.SECOND,      cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }
    
    
    /**
     * Returns a Date set to the first possible millisecond of the month, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfMonth(Date day) {
        return getStartOfMonth(day, Calendar.getInstance());
    }
    
    
    public static Date getStartOfMonth(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        
        // set time to start of day
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE,      cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        
        // set time to first day of month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        return cal.getTime();
    }
    
    
    /**
     * Returns a Date set to the last possible millisecond of the month, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfMonth(Date day) {
        return getEndOfMonth(day, Calendar.getInstance());
    }
    
    
    public static Date getEndOfMonth(Date day,Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        
        // set time to end of day
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE,      cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        
        // set time to first day of month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        // add one month
        cal.add(Calendar.MONTH, 1);
        
        // back up one day
        cal.add(Calendar.DAY_OF_MONTH, -1);
        
        return cal.getTime();
    }
    
    
    /**
     * Returns a Date set just to Noon, to the closest possible millisecond
     * of the day. If a null day is passed in, a new Date is created.
     * nnoon (00m 12h 00s)
     */
    public static Date getNoonOfDay(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE,      cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }
    
    
    /**
     * Returns a java.sql.Timestamp equal to the current time
     **/
   /* public static java.sql.Timestamp now() {
        return new java.sql.Timestamp(new java.util.Date().getTime());
    }
    */
    
    /**
     * Returns a string the represents the passed-in date parsed
     * according to the passed-in format.  Returns an empty string
     * if the date or the format is null.
     **/
    public static String format(Date aDate, SimpleDateFormat aFormat) {
        if (aDate == null || aFormat == null ) { return ""; }
        synchronized (aFormat) {
            return aFormat.format(aDate);
        }
    }
    
    
    /**
     * Returns a Date using the passed-in string and format.  Returns null if the string
     * is null or empty or if the format is null.  The string must match the format.
     **/
    public static Date parse(String aValue, SimpleDateFormat aFormat) throws ParseException {
        if (StringUtils.isEmpty(aValue) || aFormat == null) {
            return null;
        }
        synchronized(aFormat) {
            return aFormat.parse(aValue);
        }
    }
    
    
    /**
     * Returns true if endDate is after startDate or if startDate equals endDate
     * or if they are the same date.  Returns false if either value is null.
     **/
    public static boolean isValidDateRange(Date startDate, Date endDate) {
        return isValidDateRange(startDate, endDate, true);
    }
    
    
    /**
     * Returns true if endDate is after startDate or if startDate equals endDate.
     * Returns false if either value is null.  If equalOK, returns true if the
     * dates are equal.
     **/
    public static boolean isValidDateRange(Date startDate, Date endDate, boolean equalOK) {
        // false if either value is null
        if (startDate == null || endDate == null) { return false; }
        
        if (equalOK && startDate.equals(endDate)) {
            // true if they are equal
            //if (startDate.equals(endDate)) { 
            	return true; 
            	//}
        }
        
        // true if endDate after startDate
        if (endDate.after(startDate)) { return true; }
        
        return false;
    }
    
    
    // convenience method returns minimal date format
    public static SimpleDateFormat defaultDateFormat() {
        return DateUtil.friendlyDateFormat(true);
    }
    
    
    // convenience method returns minimal date format
/*    public static java.text.SimpleDateFormat minimalDateFormat() {
        return friendlyDateFormat(true);
    }*/
    
    
    // convenience method that returns friendly data format
    // using full month, day, year digits.
    public static SimpleDateFormat fullDateFormat() {
        return friendlyDateFormat(false);
    }
    
    
    /** 
     * Returns a "friendly" date format.
     * @param mimimalFormat Should the date format allow single digits.
     **/
    public static SimpleDateFormat friendlyDateFormat(boolean minimalFormat) {
        if (minimalFormat) {
            return new SimpleDateFormat(formatDefaultDateMinimal);
        }
        
        return new SimpleDateFormat(formatDefaultDate);
    }
    
    
    // returns full timestamp format
    public static SimpleDateFormat defaultTimestampFormat() {
        return new SimpleDateFormat(formatDefaultTimestamp);
    }
    
    
    // convenience method returns long friendly timestamp format
    public static SimpleDateFormat friendlyTimestampFormat() {
        return new SimpleDateFormat(formatFriendlyTimestamp);
    }
    
    
    // convenience method returns minimal date format
    public static SimpleDateFormat get8charDateFormat() {
        return new SimpleDateFormat(format8chars);
    }
    
    
    // convenience method returns minimal date format
    public static SimpleDateFormat get6charDateFormat() {
        return new SimpleDateFormat(format6chars);
    }
    
    
    // convenience method returns minimal date format
    public static SimpleDateFormat getIso8601DateFormat() {
        return new SimpleDateFormat(formatIso8601);
    }
    
    
    // convenience method returns minimal date format
    public static SimpleDateFormat getIso8601DayDateFormat() {
        return new SimpleDateFormat(formatIso8601Day);
    }
    
    
    // convenience method returns minimal date format
    public static SimpleDateFormat getRfc822DateFormat() {
        // http://www.w3.org/Protocols/rfc822/Overview.html#z28
        // Using Locale.US to fix ROL-725 and ROL-628
        return new SimpleDateFormat(formatRfc822, Locale.US);
    }
    
    
    // convenience method
    public static String defaultDate(Date date) {
        return format(date, defaultDateFormat());
    }
    
    
    // convenience method using minimal date format
   /* public static String minimalDate(Date date) {
        return format(date, DateUtil.minimalDateFormat());
    }*/
    
    
    public static String fullDate(Date date) {
        return format(date, DateUtil.fullDateFormat());
    }
    
    
    /**
     * Format the date using the "friendly" date format.
     */
    public static String friendlyDate(Date date, boolean minimalFormat) {
        return format(date, friendlyDateFormat(minimalFormat));
    }
    
    
    // convenience method
    public static String friendlyDate(Date date) {
        return format(date, friendlyDateFormat(true));
    }
    
    
    // convenience method
    public static String defaultTimestamp(Date date) {
        return format(date, defaultTimestampFormat());
    }
    
    
    // convenience method returns long friendly formatted timestamp
    public static String friendlyTimestamp(Date date) {
        return format(date, friendlyTimestampFormat());
    }
    
    
    // convenience method returns 8 char day stamp YYYYMMDD
    public static String format8chars(Date date) {
        return format(date, get8charDateFormat());
    }
    
    
    // convenience method returns 6 char month stamp YYYYMM
    public static String format6chars(Date date) {
        return format(date, get6charDateFormat());
    }
    
    
    // convenience method returns long friendly formatted timestamp
    public static String formatIso8601Day(Date date) {
        return format(date, getIso8601DayDateFormat());
    }
    
    
    public static String formatRfc822(Date date) {
        return format(date, getRfc822DateFormat());
    }
    
    
    // This is a hack, but it seems to work
    public static String formatIso8601(Date date) {
        if (date == null) return "";
        
        // Add a colon 2 chars before the end of the string
        // to make it a valid ISO-8601 date.
        
        String str = format(date, getIso8601DateFormat());
        StringBuffer sb = new StringBuffer();
        sb.append( str.substring(0,str.length()-2) );
        sb.append( ":" );
        sb.append( str.substring(str.length()-2) );
        return sb.toString();
    }
    
    
    public static Date parseIso8601(String value) throws Exception {
        return ISO8601DateParser.parse(value);
    }
    
    
    /**
     * Parse data as either 6-char or 8-char format.
     */
    public static Date parseWeblogURLDateString(String dateString, TimeZone tz, Locale locale) {
        
        Date ret = new Date();
        SimpleDateFormat char8DateFormat = DateUtil.get8charDateFormat();
        SimpleDateFormat char6DateFormat = DateUtil.get6charDateFormat();
        
        if (dateString != null
                && dateString.length()==8
                && StringUtils.isNumeric(dateString) ) {
            ParsePosition pos = new ParsePosition(0);
            ret = char8DateFormat.parse(dateString, pos);
            
            // make sure the requested date is not in the future
            Date today = null;
            Calendar todayCal = Calendar.getInstance();
            todayCal = Calendar.getInstance(tz, locale);
            todayCal.setTime(new Date());
            today = todayCal.getTime();
            if(ret.after(today)) {
                ret = today;
            }
            
        } else if(dateString != null
                && dateString.length()==6
                && StringUtils.isNumeric(dateString)) {
            ParsePosition pos = new ParsePosition(0);
            ret = char6DateFormat.parse(dateString, pos);
            
            // make sure the requested date is not in the future
            Calendar todayCal = Calendar.getInstance();
            todayCal = Calendar.getInstance(tz, locale);
            todayCal.setTime(new Date());
            Date today = todayCal.getTime();
            if(ret.after(today)) {
                ret = today;
            }
        }
        
        return ret;
    }
    public static Date getFirstGivenDayOfMonth(Date selectedDate,String dayStr)
    {
        log.debug("getFirstGivenDayOfMonth(string) : " + dayStr);
        return getFirstGivenDayOfMonth(selectedDate,getDayValue(dayStr));
    }
    public static Date getFirstGivenDayOfMonth(Date selectedDate,int day)
    {
        Date result = null;
        if (isValidDay(day))
        {
            Calendar firstDateOfMonth =Calendar.getInstance();
            firstDateOfMonth.setTime(selectedDate); 
            log.debug("Current DAY OF WEEK : " + Calendar.DAY_OF_WEEK);
            log.debug("Current Day of Month : " + Calendar.DAY_OF_MONTH);
            log.debug("Current Day of Month : " + Calendar.DAY_OF_MONTH);
            firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
            log.debug("firstDateOfMonth : " + firstDateOfMonth);
            log.debug("firstDateOfMonth : " + firstDateOfMonth);
            int firstDayOfMonth = firstDateOfMonth.get(Calendar.DAY_OF_WEEK);
            log.debug("firstDayOfMonth : " +firstDayOfMonth+"----Day---"+day);            
            result = firstDateOfMonth.getTime();
            log.debug("firstDayOfMonth : " +result);            
            
            if (day != firstDayOfMonth)
            {
                result = getNextDateForDay(day, firstDateOfMonth);
            }            
            //Check if today is greater than result and recalucate the date.
                       
            while(selectedDate.after(result))
            {
                //firstDateOfMonth.add(Calendar.MONTH, 1);
                result = getNextDateForDay(day, firstDateOfMonth);
            }            
        }
        return result;
    }
    public static Date getWeekStart(Date givenDate) {
    	try{
	    //	SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");			
	    	Calendar c=Calendar.getInstance();
	    	c.setTime(givenDate);
		    c.setFirstDayOfWeek(Calendar.SUNDAY);
		    c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		    return c.getTime();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
        return null;
    }
    
    public static Date getWeekEnd(Date givenDate) {
    	try{	    	
	    	Calendar c=Calendar.getInstance();
	    	c.setTime(givenDate);
		    c.setFirstDayOfWeek(Calendar.SUNDAY);
		    c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		    return c.getTime();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
        return null;
    }
    
    public static Date getWeekDateByday(Date givenDate, String day) {
    	try{
    		 int dayValue=Integer.parseInt(day);
    		 if (isValidDay(dayValue))
    	     {
		    	//SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");			
		    	Calendar c=Calendar.getInstance();
		    	c.setTime(givenDate);
			    c.setFirstDayOfWeek(Calendar.SUNDAY);
			    c.set(Calendar.DAY_OF_WEEK, dayValue);
			    return c.getTime();
    	     }
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
        return null;
    }
    
    public static Date getFirstGivenDayOfMonth(int day,int month,Date date)
    {
        Date result = null;
        if (isValidDay(day))
        {
            Calendar firstDateOfMonth = new GregorianCalendar();   
            firstDateOfMonth.setTime(date); 
            firstDateOfMonth.setFirstDayOfWeek(Calendar.SUNDAY);          
            firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);            
            firstDateOfMonth.set(Calendar.MONTH, month);
            int firstDayOfMonth = firstDateOfMonth.get(Calendar.DAY_OF_WEEK);
            int daysCount=0;
            if (day != firstDayOfMonth)
            {  
            	int difference = day - firstDayOfMonth;
                if (difference <= 0)
                {
                    difference = difference + 7;
                    daysCount=difference;
                }else{
                	daysCount=difference;
                }
                firstDateOfMonth.add(Calendar.DAY_OF_MONTH, daysCount);                
            }
            result=firstDateOfMonth.getTime();           
        }
              
        return result;
    }
    
    public static Date getFirstGivenDayOfMonth(int day,Date date)
    {
        Date result = null;
        if (isValidDay(day))
        {
            Calendar firstDateOfMonth = Calendar.getInstance();
            firstDateOfMonth.setTime(date);
            firstDateOfMonth.setFirstDayOfWeek(Calendar.SUNDAY);
            firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);           
            int firstDayOfMonth = firstDateOfMonth.get(Calendar.DAY_OF_WEEK);
            int daysCount=0;
            if (day != firstDayOfMonth)
            {  
            	int difference = day - firstDayOfMonth;
                if (difference <= 0)
                {
                    difference = difference + 7;
                    daysCount=difference;
                }else{
                	daysCount=difference;
                }
                firstDateOfMonth.add(Calendar.DAY_OF_MONTH, daysCount);                
            }
            result=firstDateOfMonth.getTime();     
            log.debug("result--->"+result);
        }
              
        return result;
    }
    
    public static String prepareRSSDateFormat(Date inputdate){
		try{
			StringBuffer gregorianDateBuffer=new StringBuffer();
    		String startDate=DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,inputdate);
    		String startDateTime=DateFormatter.formatDate(DateFormatter.HHMM_PATTERN,inputdate);
	        gregorianDateBuffer.append(startDate);
    		gregorianDateBuffer.append("T");
    		gregorianDateBuffer.append(startDateTime);
    		gregorianDateBuffer.append(":00Z");
            return gregorianDateBuffer.toString();	
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return "";
    	}
	}
	
    public static String prepareICALDateFormat(Date inputdate){
    	 try{ 
    		 StringBuffer icalDateBuffer=new StringBuffer();
    		 String strDate=DateFormatter.formatDate(DateFormatter.YYYYMMDDHHMMSS_PATTERN,inputdate);
    		 icalDateBuffer.append(strDate.substring(0,8));
    		 icalDateBuffer.append("T");
    		 icalDateBuffer.append(strDate.substring(8));
    		 return icalDateBuffer.toString();
     	  }
     	  catch(Exception ex){
     		ex.printStackTrace();
     		return "";
     	  }
	}    

    public static Date getYesterday(){
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
    }

}

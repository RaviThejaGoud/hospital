/**
 * 
 */
package com.churchgroup.util.ical;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ICalendarFromString {
	private static final Log log = LogFactory.getLog(ICalendarFromString.class);
	
  private static final String CALENDAR_HOME = "E:/adds/";
  private static FileWriter fileWriter;

  public static void saveStringAsCalendarFile(String contents,
                                              String destinationCalendar)
                         throws Exception {
    createCalendarFileWriter(destinationCalendar);
    contents = changeCalendarName(contents, destinationCalendar);
    writeCalendarToFile(contents);
  }

  private static void createCalendarFileWriter(String calendarName)
                            throws Exception {
    File outputFile = new File(CALENDAR_HOME + calendarName + ".ics");
    try {
      fileWriter = new FileWriter(outputFile);
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e.getCause());
    }
  }

  private static String changeCalendarName(String contents, String destinationName) {
    Pattern calendarNamePattern = Pattern.compile("(.*?CALNAME.*?:)(.*?)\n");
    Matcher calendarNameMatcher = calendarNamePattern.matcher(contents);
    return calendarNameMatcher.replaceAll("$1" + destinationName + "\n");
  }

  private static void writeCalendarToFile(String contents) throws Exception {
    try {
      fileWriter.write(calendarTxt1+"\r\n");
      fileWriter.write(calendarTxt2+"\r\n");
      fileWriter.write(calendarTxt3+"\r\n");
      fileWriter.write(calendarTxt4+"\r\n");
      fileWriter.write(calendarTxt5+"\r\n");
      fileWriter.write(calendarTxt6+"\r\n");
      fileWriter.close();
    } catch (IOException e) {
      throw new Exception(e.getMessage(), e.getCause());
    }

  }
  public static void main(String[] args) {
	    try {
	     // String contents = ICalendarToString.getCalendarAsString("example");
	     // contents = ICalendarTodoConverter.convert(contents);
	      ICalendarFromString.saveStringAsCalendarFile(calendarTxt1, "ipod");
	    } catch (CalendarNotFoundException e) {
	      log.debug("Could not find Calendar example.ics: " + e.getMessage());
	    } catch (Exception e) {
	      log.debug("Could not save transformed calendar: " + e.getMessage());
	    }
	  }

  public static String calendarTxt1="BEGIN:VEVENT";
  public static String calendarTxt2="DTSTART:20080814T170000Z";
  public static String calendarTxt3="DTEND:20080815T035959Z";
  public static String calendarTxt4="SUMMARY:Bastille Day Party";
  public static String calendarTxt5="ATTACH;FMTTYPE=image/jpeg:http://domain.com/images/bastille.jpg";
  public static String calendarTxt6="END:VEVENT";
}
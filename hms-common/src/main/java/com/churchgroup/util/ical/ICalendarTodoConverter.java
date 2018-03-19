/**
 * 
 */
package com.churchgroup.util.ical;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ICalendarTodoConverter {

  private static final String SAVED_PORTION = "(.*?)";

  public static String convert(String contents) {
    Matcher todoMatcher = getTodoMatcher(contents);
    return todoMatcher.replaceAll(setTodoRegEx());
  }

  private static Matcher getTodoMatcher(String contents) {
    Pattern todoPattern = Pattern.compile(getTodoRegEx(), Pattern.DOTALL);
    return todoPattern.matcher(contents);
  }

  private static String getTodoRegEx() {
    return "BEGIN:VTODO" + getTodoContentsRegEx() + "END:VTODO";
  }

  private static String setTodoRegEx() {
    return "BEGIN:VEVENT" + setTodoContentsRegEx() + "END:VEVENT";
  }

  private static String getTodoContentsRegEx() {
    return SAVED_PORTION // $1
        + getSummaryRegEx()
        + getDateRegEx()
        + SAVED_PORTION; //$4
  }

  private static String setTodoContentsRegEx() {
    return "$1"
        + setSummaryRegEx()
        + setDateRegEx()
        + "$4";
  }

  private static String getSummaryRegEx() {
    return "SUMMARY:"
        + SAVED_PORTION; // $2
  }

  private static String setSummaryRegEx() {
    return "SUMMARY:TODO "
        + "$2";
  }

  private static String getDateRegEx() {
    return "DTSTART"
        + SAVED_PORTION //$3
        + "\n";
  }

  private static String setDateRegEx() {
    return "DTSTART;VALUE=DATE:"
        + getCurrentDateAsString()
        + "\nDURATION:P1D\n";
  }

  private static String getCurrentDateAsString() {
    Calendar now = Calendar.getInstance();
    String rightNow = now.get(now.YEAR)
          + formatDateComponent(now.get(now.MONTH) + 1)
          + formatDateComponent(now.get(now.DAY_OF_MONTH));
    return rightNow;
  }

  private static String formatDateComponent(int i) {
    if (i < 10)
      return "0" + i;
    else
      return "" + i;
  }
}
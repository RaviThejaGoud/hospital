/**
 * 
 */
package com.churchgroup.util.ical;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ICalendarToString {
  private static final String CALENDAR_HOME = "Calendar/";
  private static File inputFile;
  private static FileReader calendarReader;

  public static String getCalendarAsString(String calendarName)
                          throws CalendarNotFoundException {
    findCalendarFile(calendarName);
    setUpFileReader();
    return representCalendarAsString();
  }

  private static void findCalendarFile(String calendarName)
                          throws CalendarNotFoundException {
    inputFile = new File(CALENDAR_HOME + calendarName + ".ics");
    if (!inputFile.exists())  throw new CalendarNotFoundException(
         "There is no file with the name " + inputFile.getAbsolutePath());
  }

  private static void setUpFileReader() throws CalendarNotFoundException {
    try {
      calendarReader = new FileReader(inputFile);
    } catch (FileNotFoundException e) {
      throw new CalendarNotFoundException(e.getMessage(), e.getCause());
    }
  }

  private static String representCalendarAsString()
                                 throws CalendarNotFoundException {
    try {
      char[] contentCharacterArray = new char[(int) inputFile.length()];
      calendarReader.read(contentCharacterArray);
      calendarReader.close();
      return new String(contentCharacterArray);
    } catch (IOException e) {
      throw new CalendarNotFoundException(e.getMessage(), e.getCause());
    }
  }
}
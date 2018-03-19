/**
 * 
 */
package com.churchgroup.util.ical;

/**
 * @author Sreeram
 *
 */
public class CalendarNotFoundException extends Exception {
	  public CalendarNotFoundException(String description) {
	    super(description);
	  }

	  public CalendarNotFoundException(String description, Throwable cause) {
	    super(description, cause);
	  }
	}
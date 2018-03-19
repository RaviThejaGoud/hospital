package com.urt.service.exception;


/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 * <p><a href="DataNotFoundException.java.html"><i>View Source</i></a></p>
 *
 */
public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = 4050482305178810162L;

	/**
     * Constructor for DataNotFoundException.
     *
     * @param message
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}

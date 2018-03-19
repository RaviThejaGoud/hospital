package com.urt.sql.mssql;


/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 * @author <a href="mailto:sreeram@uroomtech.com">Sreeramulu J</a>
 */
public class UserSQLServerException extends Exception {
    private static final long serialVersionUID = 4050482305178810162L;

    /**
     * Constructor for UserExistsException.
     *
     * @param message exception message
     */
    public UserSQLServerException(final String message) {
        super(message);
    }
}

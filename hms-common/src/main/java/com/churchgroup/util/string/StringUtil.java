package com.churchgroup.util.string;

import java.security.MessageDigest;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * String Utility Class This is used to encode passwords programmatically
 *
 * <p>
 * <a h
 * ref="StringUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StringUtil {
    //~ Static fields/initializers =============================================

    private final static Log log = LogFactory.getLog(StringUtil.class);
    private final static String alphabet = "abcdefghijkmnopqrstuvwxyz";
    private final static String numbers = "023456789";

    //~ Methods ================================================================

    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials
     * string is returned
     *
     * @param password Password or other credentials to use in authenticating
     *        this username
     * @param algorithm Algorithm used to do the digest
     *
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            log.error("Exception: " + e);

            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * Encode a string using Base64 encoding. Used when storing passwords
     * as cookies.
     *
     * This is weak encoding in that anyone can use the decodeString
     * routine to reverse the encoding.
     *
     * @param str
     * @return String
     */
    public static String encodeString(String str)  {
        /*sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encodeBuffer(str.getBytes()).trim();*/
    	return "";
    }

    /**
     * Decode a string using Base64 encoding.
     *
     * @param str
     * @return String
     */
    public static String decodeString(String str) {
        /*sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
        try {
            return new String(dec.decodeBuffer(str));
        } catch (IOException io) {
        	throw new RuntimeException(io.getMessage(), io.getCause());
        }*/
    	return "";
    }
    public static String generateRandomString()
    {
        
        StringBuffer randomString = new StringBuffer(15);

        Random ran = new Random();
        int nextInt = 0;
        for(int c1=0; c1<7;c1++)
        {
            nextInt = ran.nextInt(25);
            randomString.append(alphabet.charAt(nextInt));
            if((c1 == 2 || c1 == 4 || c1 == 6) && nextInt < 9)
                randomString.append(numbers.charAt(nextInt));
        }
        return randomString.toString();
    }
    public static String convertListToString(List<String> asList){
        String commaDelimitedString = org.springframework.util.StringUtils.collectionToCommaDelimitedString(asList);
    return commaDelimitedString;
    }
    public static String convertSetToString(Set<String> asSet){
        String commaDelimitedString = org.springframework.util.StringUtils.collectionToCommaDelimitedString(asSet);
    return commaDelimitedString;
    }
    public static String getOrdinalSuffix( int value ){
	    int hunRem = value % 100;
	    int tenRem = value % 10;
	    if ( hunRem - tenRem == 10 )
	    {
	        return "th";
	    }
	    switch ( tenRem )
	    {
	    case 1:
	        return "st";
	    case 2:
	        return "nd";
	    case 3:
	        return "rd";
	    default:
	        return "th";
	    }
	}
}

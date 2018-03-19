package com.churchgroup.util.string;
/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: StringFunctions.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
/ ********************************************************************/
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.EmailValidator;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;

/**
*	StringFunctions is a class of <Code><B>static</Code></B>
*	methods targeted at supplementing the Java String 
*   class capabilities.
*/

public class StringFunctions 
{
	private static final Log log = LogFactory.getLog(StringFunctions.class);
	public static final String NL = System.getProperty("line.separator");

	/**
	 * Empty string static
	 */
	public static final String EMPTY_STRING = "";

	/**
	 * Errors Returned by the
	 * StringFunctions.containsAtleastALetterAndANumeric method
	 */	
    public static int ERROR_HAS_ALPHA_CHARACTERS = 4;
	public static int ERROR_NEITHER_DIGITORALPHA = 3;
	public static int ERROR_NO_ALPHA_CHARACTERS = 2;
	public static int ERROR_NO_DIGIT_CHARACTERS = 1;
	public static int NOERROR = 0;
	public static String STRIP_SYMBOLS = "#$@,'`?& ";
	
	public static String version = "1.0";
	
/**
 * Convert a byte array to a String.  This method differs from 
 * using new String(byteArray) to do the conversion in that it
 * doesn't use a translation table to convert the byte array to
 * a String.  Using new String(byteArray) will convert certain
 * byte values within an "untranslatable" range to values within
 * the String that cannot be converted back to the original byte
 * value.
 * See bytesToString(byte[]) to convert an entire byte array.
 * See stringFromBytes() to convert an array, with protection against
 *     bad offsets or lengths.
 * <br>
 * Note: may return an empty String if the byte array passed in is
 * null or empty.  If the offset or length are "bad", an exception
 * may be thrown.  This method does not protect against bad offsets
 * or lengths.
 * <br>
 * @param inBytes byte[] - byte array to be converted to a String
 * @param offset int - starting point into the byte array for the 
 *                     conversion to start
 * @param length int - number of bytes, starting at offset, to convert
 *                     to a string
 * @return String - String value representing 
 */
	public static String bytesToString(byte[] inBytes, int offset, int length)
	{
		// if the byte array is null or empty, just return
		// an empty string.
		if (inBytes == null || inBytes.length == 0)
		{
			return "";
		}
		// create a character array the same size as the byte array and
		// convert each byte into a character.
		final char[] inChars = new char[length];
    	for (int i = offset; i < offset + length; i++)
    	{
        	int x = inBytes[i];

			// if it is negative, add 256 to it to make it positive
	        if (x < 0)
	        {
 	           x += 256;
	        }

            inChars[i-offset] = (char) x;
        }
        return new String(inChars);
	}
    /**
     * Method checkNotEmpty.
     * @param strings
     */
    /*private static boolean checkNotEmpty(Collection strings)
    {
        boolean retValue=false;
        if (!ObjectFunctions.isNullOrEmpty(strings))
        {
            Iterator iterator = strings.iterator();
            while (iterator.hasNext())
            {
                Object o = iterator.next();
                if (o != null && o instanceof String)
                {
                    String element = (String) o;
                    if (StringFunctions.isNullOrEmpty(element))
                    {
                        retValue=true;  
                    }
                }
            }
        }
        return retValue;
    }*/
/**
 * Convert a byte array to a String.  See byteToString(byte[], int, int)
 * for more details.
 * <br>
 * Note: may return an empty String if the byte array passed in is
 * null or empty.  
 * <br>
 * @param inBytes byte[] - byte array to be converted to a String
 * @return String - String value representing 
 */
	public static String bytesToString(byte[] inBytes)
	{
		// if the byte array is null or empty, just return
		// an empty string.
		if (inBytes == null || inBytes.length == 0)
		{
			return "";
		}
        return bytesToString(inBytes, 0, inBytes.length);
	}
/**
 * Given an argument, check if it is a String.  If so, return it,
 *  or else return the default value supplied.
 * <br>
 * Note: may return null if null is passed as defaultString
 * <br>
 * @param o java.lang.Object   object to check   
 * @param defaultString java.lang.String   the value to use if o is not a String
 * @return java.lang.String
 */
public static String checkString(Object o, String defaultString) {
	
	if (o == null)
	{
		return defaultString;
	}
	else
	{
		if (o instanceof String)
		{
			return (String)o;
		}
		else
		{
			return defaultString;
		}
	}
}


/**
*	Returns a String which is the concatenation of
*	<I>aSourceString</I>, and <I>aStringToConcatenate</I>,
*		with <I>aSeparatorString</I> between the two.  
*	If <I>aStringToConcatenate</I> is blank, nothing is concatenated to
*      <I>aSourceString</I>. <P>
*	The concatenation is implemented according to the following discussion:<UL>
*	<LI>If <I>aStringToConcatenate</I> is <Code>null</Code> or <Code>""</Code>,
*	    then <I>aSourceString</I> is returned untouched.
*	<LI>Otherwise, the concatenation of	<I>aSourceString</I>
*		and <I>aSeparatorString</I>	and <I>aStringToConcatenate</I>
*		is returned.
*	</UL>
*	<P>
*	This is useful, for example, for concatenating only a single
*		blank between several fields, each of which could be optional.
*	For example, it cannot always be reliably known	whether all of the fields
*  <I>FirstName</I>, <I>MiddleInitial</I> and <I>LastName</I> have non-blank values.
*	This method can be used to ensure that only one intermediate blank is 
*     concatenated between	any two of the three fields, regardless of which
*	  fields have non-blank values.
*
*    @param  aSourceString       String   the initial String
*    @param  aSeparatorString      String  the separator to append
*    @param  aStringToConcatenate  String   the value to append
*/
public static String concatWithSeparator(String aSourceString,String aSeparatorString,
											String aStringToConcatenate)	{
			
	// if our string to concat is empty return the original string
	if (isNullOrEmptyNoTrim(aStringToConcatenate))
	{
		return aSourceString;
	}
    // otherwise concatenate with the separator
    else
    {
		aSourceString += aSeparatorString + aStringToConcatenate;
 		return aSourceString;
	}
}
/**
 * Parse a given string starting from first occurance of char
 * Return values:
 *   string - string
 * Creation date: (05/12/2007 10:14:07 PM)
 * @return String
 * @param param java.lang.String
 */
public static String parseString(String stringCheck) {
        if (stringCheck == null)
        {
            return "";
        }
        
        int firstCharOccurance=0;
        int length = stringCheck.length();
        for (int i = 0; i < length; i++)
        {
            char charValue = stringCheck.charAt(i);

            if (Character.isLetter(charValue))
            {
                //Char is a letter
                firstCharOccurance = i;
                break;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = firstCharOccurance; i <length; i++)
        {
            sb.append(stringCheck.charAt(i));
        }

        return sb.toString();
}
/**
 * Parse a given string starting with seperator value
 * Return values:
 *   string - string
 * Creation date: (05/12/2007 10:14:07 PM)
 * @return String
 * @param param java.lang.String
 */
public static String parseString(String stringCheck, String seperator) {
        if (stringCheck == null)
        {
            return "";
        }
        int beginIndex = stringCheck.indexOf(seperator);
        return stringCheck.substring(beginIndex+1);
}
/**
 * Parse a given string ending of int
 * Return values:
 *   String
 * Creation date: (05/12/2007 10:14:07 PM)
 * @return String
 * @param param java.lang.String
 */
public static String parseInt(String stringCheck) {
        if (stringCheck == null)
        {
            return "0";
        }
        
        StringBuffer sb = new StringBuffer();
        int length = stringCheck.length();
        for (int i = 0; i < length; i++)
        {
            char charValue = stringCheck.charAt(i);

            if (Character.isDigit(charValue))
            {
                sb.append(stringCheck.charAt(i));
            }
            else
                break;
        }
        return sb.toString();
}
/**
 * Parse a given string ending of int
 * Return values:
 *   String
 * Creation date: (05/12/2007 10:14:07 PM)
 * @return String
 * @param param java.lang.String
 */
public static String parseInt(String stringCheck,String seperator) {
        if (stringCheck == null)
        {
            return "0";
        }
        
        int endIndex = stringCheck.indexOf(seperator);
        log.debug(" parseInt : endIndex : " + endIndex);
        return stringCheck.substring(0, endIndex);
}

/**
 * Checks to see if the string passed in contains atleast
 * one letter and one numeric.
 * Return values:
 * 	3 - missing both letter and numeric
 * 	2 - missing letter
 * 	1 - missing numeric
 * 	0 - not missing either, passes test.
 * Creation date: (11/12/2002 6:14:07 PM)
 * @return int
 * @param param java.lang.String
 */
public static int containsAtleastALetterAndANumeric(String stringCheck) {
   {
		if (stringCheck == null)
		{
			return ERROR_NEITHER_DIGITORALPHA;
		}
		
        boolean alphaChar = false;
        boolean numericChar = false;

        int length = stringCheck.length();
        for (int i = 0; i < length; i++)
        {
            char charValue = stringCheck.charAt(i);

            if (Character.isLetter(charValue))
            {
                //Char is a letter
                alphaChar = true;
            }
            else if (Character.isDigit(charValue))
            {
                //Char is a digit
                numericChar = true;
            }
        }

        int errorCode = 0;
        //Set string based on boolean variables
        if (!alphaChar && !numericChar)
            errorCode = ERROR_NEITHER_DIGITORALPHA;
        else if (!alphaChar)
            errorCode = ERROR_NO_ALPHA_CHARACTERS;
        else if (!numericChar)
            errorCode = ERROR_NO_DIGIT_CHARACTERS;
        else
            errorCode = NOERROR;

        return errorCode;
    }
}
/**
 * Checks to see if the string passed in contains atleast
 * one letter and one numeric.
 * Return values:
 *  3 - missing both letter and numeric
 *  2 - missing letter
 *  1 - missing numeric
 *  0 - not missing either, passes test.
 * Creation date: (11/12/2002 6:14:07 PM)
 * @return int
 * @param param java.lang.String
 */
public static int containsOnlyANumeric(String stringCheck) {
   {
        if (stringCheck == null)
        {
            return ERROR_NEITHER_DIGITORALPHA;
        }
        
        boolean alphaChar = false;
        int length = stringCheck.length();
        for (int i = 0; i < length; i++)
        {
            char charValue = stringCheck.charAt(i);

            if (!Character.isDigit(charValue) && charValue != '-')
            {
                //Char is a Char
                alphaChar = true;
            }
        }

        int errorCode = 0;
        //Set string based on boolean variables
        if (alphaChar)
            errorCode = ERROR_HAS_ALPHA_CHARACTERS;
        else 
            errorCode = NOERROR;

        return errorCode;
    }
}


/**
 * Search through <code>list</code> to locate an occurrence of
 * <code>find</code>.  If <code>find</code> is found, return
 * <b>true</b>, otherwise return <b>false</b>.
 * <br><br>
 *
 * If a reverse search would provide better performance 
 *  (i.e. need to search from end of string), use the three-argument
 *  version and pass 'false' for the 'searchForward' parm.<br><br>
 *
 * <b>If either search parm is null, this method will return false.</b><br>
 *
 * @return A flag indicating that the search string was found.
 * @param list a String containing a list of values, delimited
 *        in some manner (the delimiter used does not matter)
 * @param find a String that will be matched to the values
 *        within the list
 */
public static boolean containsString(String list, String find) {

	return containsString(list,find,true);
}


/**
 * Search through <code>list</code> to locate an occurrence of
 * <code>find</code>.  If <code>find</code> is found, return
 * <b>true</b>, otherwise return <b>false</b>.
 * <br><br>
 *
 * The third parameter instructs the method whether to search 
 *   forward (indexOf) or backwards (lastIndexOf).<br><br>
 *
 * <b>If either search parm is null, this method will return false.</b><br>
 *
 * @return A flag indicating that the search string was found.
 * @param list a String containing a list of values, delimited
 *        in some manner (the delimiter used does not matter)
 * @param find a String that will be matched to the values
 *        within the list
 * @param boolean   searchForward   if false, it will do 'lastIndexOf'
 */
public static boolean containsString(String list, String find, boolean searchForward) {

	boolean value = false;
	
	if (list != null && find != null)
	{
		if (searchForward)
		{
			value = list.indexOf(find) > -1;
		}
		else
		{
			value = list.lastIndexOf(find) > -1;
		}
	}
	
	return value;
}


/**
 * Populates a String array with the elements of the input delimited string
 * @return String array containing delimited elements of original string or no elements if input is null or empty
 * @param inputString delimited string to be parsed
 * @param delimiter String containing delimiter character(s)
 */
public static String[] createStringArray(String inputString, String delimiter) {
	return createStringArray(inputString,delimiter,false);
}


/**
 * Populates a String array with the elements of the input delimited string
 * @return String array containing delimited elements of original string or no elements if input is null or empty
 * @param inputString delimited string to be parsed
 * @param delimiter String containing delimiter character(s)
 * @param returnDelim boolean if true it will return the delimiters as elements in the array
 */
public static String[] createStringArray(String inputString, String delimiter,boolean returnDelim) {
	int initialSize = 20;
	ArrayList arrayList = new ArrayList(20);
	String [] stringArray;
	
	if (!isNullOrEmpty(inputString))
	{	
		StringTokenizer tokenizer = new StringTokenizer(inputString, delimiter, returnDelim);
		while (tokenizer.hasMoreTokens())
		{
			arrayList.add(tokenizer.nextToken());
		}
	}		
	if (arrayList.size() < initialSize)
	{
		arrayList.trimToSize();
	}	
	stringArray = new String [arrayList.size()];
	{
		for (int i = 0; i < stringArray.length; i++)
		{
			stringArray[i] = (String) arrayList.get(i);
		}	
	}	
	return stringArray;
}

/**
 * Creates a delimited String from the contents of a String Array.
 * If the String Array is null or empty, an empty string will be returned.
 * @return String delimited by 'delimiter' character between array elements
 * @param String[] array whose contents are to be used in the creation of the String
 * @param delimiter String containing delimiter character(s)
 */
public static String createStringFromArray(String[] array, String delimiter)
{
    int endIdx;

    if (array == null)
    {
        endIdx = 0;
    }
    else
    {
        endIdx = array.length;
    }

    return createStringFromArray(array, delimiter, 0, endIdx);
}

/**
 * Creates a delimited String from the contents of a String Array.
 * If the String Array is null or empty, the string will contain only the delimiter.
 * @return String delimited by 'delimiter' character between array elements
 * @param String[] array whose contents are to be used in the creation of the String
 * @param delimiter String containing delimiter character(s)
 * @param int endIdx the ending index in the array to stop building the delimited String
 */
public static String createStringFromArray(String[] array, String delimiter, int endIdx)
{
    return createStringFromArray(array, delimiter, 0, endIdx);
}

/**
 * Creates a delimited String from the contents of a String Array.
 * If the String Array is null or empty, the string will contain only the delimiter.
 * @return String delimited by 'delimiter' character between array elements
 * @param String[] array whose contents are to be used in the creation of the String
 * @param delimiter String containing delimiter character(s)
 * @param int startIdx the starting index in the array to begin building the delimited String
 * @param int endIdx the ending index in the array to stop building the delimited String
 */
public static String createStringFromArray(String[] array, String delimiter, int startIdx, int endIdx)
{
    StringBuffer sb = new StringBuffer();

    for (int idx = startIdx; idx < endIdx; idx++)
    {
        if (idx > startIdx)
        {
            sb.append(delimiter);
        }

        if (array != null && array.length > idx)
        {
            sb.append(array[idx]);
        }
    }

    return sb.toString();
}


/**
 * Returns byte dump in following format:
 * <pre>
 *	00000000: 31 32 33 34 35 36 37 38 - 39 30 54 68 65 20 71 75  '1234567890The qu'
 *	00000010: 69 63 6b 20 62 72 6f 77 - 6e 20 66 6f 78 20 6a 75  'ick brown fox ju'
 *	00000020: 6d 70 65 64 20 6f 76 65 - 72 20 74 68 65 20 6c 61  'mped over the la'
 *	00000030: 7a 79 20 64 6f 67 2e                               'zy dog.'
 * </pre>
 * Creation date: (5/3/01 6:40:13 PM)
 * @param bArray byte[]
 */
public static String dumpBytes(byte[] bArray)
{
    final String NL = System.getProperty("line.separator");

    StringBuffer readableData = new StringBuffer();
    StringBuffer returnStr = new StringBuffer();

    int remainder = 0;

    if (bArray == null)
        return "";

    for (int i = 0; i < bArray.length; i++)
    {
        remainder = (i + 1) % 16;

        if (remainder == 1)
        {
            returnStr.append(padLeadingZeroes(Integer.toHexString(i), 8) + ": ");
        }

        int x = bArray[i];

        if (x < 0)
            x += 256;

        if (x >= ' ' && x <= 127)
        {
            char ch = (char) x;
            readableData.append(ch);
        }
        else
            readableData.append(".");

        returnStr.append(padLeadingZeroes(Integer.toHexString(x), 2) + " ");

        if (remainder == 0)
        {
            returnStr.append(" '" + readableData.toString() + "'").append(NL);
            readableData = new StringBuffer();
        }
        else
            if (remainder % 8 == 0)
                returnStr.append("- ");
    }

    if (remainder != 0)
    {
        int numSpaces = (16 - remainder) * 3;
        if (remainder < 8)
            numSpaces += 2;
        returnStr
            .append(padTrailingSpaces("", numSpaces) + " '" + readableData.toString() + "'")
            .append(NL);
    }

    return returnStr.toString();
}


/**
 * Returns byte dump in following format:
 * <pre>
 * 	00000000: f1 f2 f3 f4 f5 f6 f7 f8 - f9 f0 e3 88 85 40 98 a4  '1234567890The qu'
 * 	00000010: 89 83 92 40 82 99 96 a6 - 95 40 86 96 a7 40 91 a4  'ick brown fox ju'
 * 	00000020: 94 97 85 84 40 96 a5 85 - 99 40 a3 88 85 40 93 81  'mped over the la'
 * 	00000030: a9 a8 40 84 96 87 4b                               'zy dog.'
 * </pre>
 * Creation date: (5/3/01 6:40:13 PM)
 * @param bArray byte[]
 */
public static String dumpEBCDICBytes(byte[] bArray)
{
    final String NL = System.getProperty("line.separator");

    String tmp;
    StringBuffer readableData = new StringBuffer();
    StringBuffer returnStr = new StringBuffer();

    int remainder = 0;

    if (bArray == null)
        return "";

    for (int i = 0; i < bArray.length; i++)
    {
        remainder = (i + 1) % 16;

        if (remainder == 1)
        {
            returnStr.append(padLeadingZeroes(Integer.toHexString(i), 8) + ": ");
        }

        int x = bArray[i];

        if (x < 0)
            x += 256;

        try
        {
			tmp = new String(bArray,i,1,"037");
        }
        catch(Exception e)
        {
	        tmp = null;
        }
        
        if (tmp!=null && tmp.charAt(0) >= ' ' &&  tmp.charAt(0) <= 127)
        {
            readableData.append(tmp.charAt(0));
        }
        else
        {
            readableData.append(".");
        }
        
        returnStr.append(padLeadingZeroes(Integer.toHexString(x), 2) + " ");

        if (remainder == 0)
        {
            returnStr.append(" '" + readableData.toString() + "'").append(NL);
            readableData = new StringBuffer();
        }
        else
            if (remainder % 8 == 0)
                returnStr.append("- ");
    }

    if (remainder != 0)
    {
        int numSpaces = (16 - remainder) * 3;
        if (remainder < 8)
            numSpaces += 2;
        returnStr
            .append(padTrailingSpaces("", numSpaces) + " '" + readableData.toString() + "'")
            .append(NL);
    }

    return returnStr.toString();
}


/**
 * Take a string of XML output and try to find and
 *   replace all occurrences of filtered data, such as passwords.
 * @return java.lang.String    the output string with filtered data replaced with '*'
 * @param s java.lang.String
 * @param sb java.lang.StringBuffer  the string buffer to populate (used on recursive calls)
 * @param startTag java.lang.String  the tag indicating the beginning of the filtered data
 * @param endTag java.lang.String the tag indicating the end of the filtered data
 */
public static final String filter(String s, StringBuffer sb, String startTag, String endTag) {

	final String START_PASSWORD_TAG = startTag;
	final String END_PASSWORD_TAG   = endTag;
	final int    START_TAG_LENGTH   = START_PASSWORD_TAG.length();
	final int    END_TAG_LENGTH     = END_PASSWORD_TAG.length();

	if (s == null)
	{
		return "";
	}
	if (sb == null)
	{
		sb = new StringBuffer(100);
	}
	
	//int stringLength = s.length();
	int startOfStart = s.indexOf(START_PASSWORD_TAG);

	if (startOfStart == -1)
	{
		sb.append(s);
	}
	else if (startOfStart > -1)
	{
		int endOfStart = startOfStart + START_TAG_LENGTH;
		sb.append(s.substring(0,endOfStart));
		int startOfEnd = s.indexOf(END_PASSWORD_TAG, endOfStart);
		if (startOfEnd > -1)
		{
			int length = 0;
			if (startOfEnd > endOfStart)
			{
				length = startOfEnd - endOfStart;
				for (int i=0; i<length; i++)
				{
					sb.append("*");
				}
			}
			sb.append(END_PASSWORD_TAG);
			return filter(s.substring(endOfStart + length + END_TAG_LENGTH),sb, startTag, endTag);
		}
		
	}
	return sb.toString(); 
}

/**
*  This following static "getArgument: methods are 
*  targeted at aiding the search for a 
*  given argument from a list arguments.   <p>
*  The "argument array" expected for each method is 
*  what was passed into a given Java program "main" method. <p>
*  Typically the format for for the argument list would be...<p>
*  <LI>-argID1 arg1 -argID2 arg2 -argID3 arg3 ...</LI>
*   <p>    
*  ... where "-argID1" is the argument identifier that the search is made on, and 
*  "arg1" is the value that represents that "-argID1". <p>
*  To check the presence of an argument, use the "getArgument" method that returns 
*   a "boolean".  Consequently, no "arg1" is needed to be in the argument list. <p>
*  This method returns a <i>String</i> for the specified <i>argName</i>
*     from the list of arguments in <i>args</i>.
*  <i>null</i> is returned if "argName" is not found in the list of "args".
*    
*     @param String[] args     The array of arguments passed to the caller's "main".
*     @param String argName    The name of the argument to find in "args".
*     @return String           The value of the "argName" found in "args".
*/
public static String getArgument (String[] args,String argName)    {

	String returnValue = null;
	boolean argFound = false;
	int  i = 0;
		
	// avoid null pointer exception
	if (args != null && argName != null)
	{
		int argsLength = args.length;
		while (i < argsLength && !argFound)
		{
		   if (argName.equals(args[i]))
 	       {
	 		    argFound = true;
				// increment the index to get from the arg indicator
				//  to its value
		        i++;
	            if (i < argsLength)
	            {
	                returnValue = args[i];
	            }
	            // if we are at the end of the list, don't return
	            //  args[i] because we will get an exception
	       }
 	       i++;
		}
    }
    return returnValue;
}


/**
* This method returns a <i>String</i> for the specified <i>"argName"</i>
* from the list of arguments in <i>args</i>. <i>"defaultValue"</i> is 
* returned if <i>argName</i> is not found in the list of args.
*    
* @param String[] args         The array of arguments passed to the caller's "main".
* @param String argName        The name of the argument to find in "args".
* @param String defaultValue   The value to return if "argName" is not found in "args".
* @return String               The value of the "argName" found in "args".
*/
public static String getArgument (String[] args,String argName,String defaultValue){

    final String arg = getArgument(args,argName);

    if (arg == null)
    {
        return defaultValue;
    }
    else
    {
    	return arg;
    }
}


/**
* This method returns a <i>boolean</i> for the specified <i>argName</i>
*   from the list of arguments in <i>args</i>. "false" is returned if "argName"
*   is not found in the list of "args".
*    
* @param String[] args     The array of arguments passed to the caller's "main".
* @param String argName    The name of the argument to find in "args".
* @return boolean          "true" if the "argName" found in "args".  "false" if not.
*/
public static boolean getArgumentAsBoolean (String[] args,String argName)   {

	if (args == null || argName == null)
	{
		return false;
	}

	final int argsLength = args.length;
	for (int i = 0; i < argsLength; i++)
    {
		if (argName.equals(args[i]))
        {
           	return true;
        }
    }
    return false;
}


/**
*  This method returns a <i>int</i> for the specified <i>argName</i>
*     from the list of arguments in <i>args</i>. <i>defaultValue</i> is 
*     returned if "argName" is not found in the list of "args".
*    
*  @param String[] args         The array of arguments passed to the caller's "main".
*  @param String argName        The name of the argument to find in "args".
*  @param int defaultValue      The value to return if "argName" is not found in "args".
*  @return int                  The value of the "argName" found in "args".
*/
public static int getArgumentAsInt (String[] args,String argName,int defaultValue) {

	final String arg = getArgument(args, argName);
    return stringToInt(arg,defaultValue);
}


/**
*  This method returns a <i>long</i> for the specified <i>argName</i>
*    from the list of arguments in <i>args</i>.
*  <i>"defaultValue"</i> is returned if <i>argName</i> 
*     is not found in the list of "args".
*    
*  @param String[] args         The array of arguments passed to the caller's "main".
*  @param String argName        The name of the argument to find in "args".
*  @param long defaultValue     The value to return if "argName" is not found in "args".
*  @return long                 The value of the "argName" found in "args".
*/
public static long getArgumentAsLong (String[] args,String argName,long defaultValue) {

	final String arg = getArgument(args,argName);
    return stringToLong(arg,defaultValue);
}


/**
 * Returns a blank string of the specified length
 * NOTE: This has been tested and proven to be faster
 *       than looping aLength times setting the characters
 *       individually.  It is LOTS faster than the previous
 *       version of creating a string buffer and doing a
 *       .append for each require character.
 * 
 * @return String of aLength blanks
 */
public static String getBlankString(int aLength)
{
	char[] space = {' '};
	    
    return new String(getRepeatedCharArray(space,aLength));
}


/** 
*  Returns a String which is the fully qualified ClassName of <i>anObject</i>.
*  @param anObject   Object   an object for which to get a class name
*  @return String    the fully-qualified class name
*/
public static String getClassName(Object anObject)  {
	
	if (anObject == null)
	{
		return null;
	}
	else
	{
		return anObject.getClass().getName();
	}
}


/**
 * Return the class name of the class without the package structure.
 *
 * @param c java.lang.Class
 * @return  the name of the class, or null if null was passed in 
 */
 
public static String getClassNameOnly(Class c) {

	String returnName = null;
	if (c != null)
	{
		// extract the class name from the fully-qualify name
		String className = c.getName();
        int beginningOfClassName = className.lastIndexOf(".") + 1;
        returnName = className.substring(beginningOfClassName);
	}
	return returnName;
}


/**
 * Return the class name of an object, without the package structure.
 * @return java.lang.String
 * @param o java.lang.Object
 */
public static String getClassNameOnly(Object o) {

	String returnName = null;
	if (o != null)
	{
		// extract the class name from the fully-qualify name
		String className = o.getClass().getName();
        int beginningOfClassName = className.lastIndexOf(".") + 1;
        returnName = className.substring(beginningOfClassName);
	}
	return returnName;
}


/**
 *  Return a platform-independent representation of the newline character
 */
public static String getNewLine() {
	return System.getProperty("line.separator");
}


/** 
*	Returns a String which consists only of the
*      numeric characters fould in <I>aString</I>.
*   If a <i>decimal point</i> is encountered, it will be included in the
*      returned String also.<br>
*   <br>
*   This function could be used for things like Stripping "$" from currency
*      data, or "-" from social security numbers or phone numbers.
*	@param  aString  String   the string to pull the numerics from
*	@return String
*/
public static String getNumerics(String aString)   {
	
	final StringBuffer tempStringBuffer = new StringBuffer("");
    String tempChar;

    final int stringLength = aString.length();
    
    for (int i=0; i < stringLength; i++)
    {
        tempChar = aString.substring(i,i+1);
        if (StringFunctions.isNumeric(tempChar))
        {
            tempStringBuffer.append(tempChar);
        }
        else if (tempChar.equals("."))
        {
        	tempStringBuffer.append(tempChar);
        }
	}
    return tempStringBuffer.toString();
}


 /**
 * Return the package name of the class without the class name.
 *
 * @param c java.lang.Class
 * @return  the name of the package, or null if null was passed in 
 */
 
public static String getPackageNameOnly(Class c) {

	String returnName = null;
	if (c != null)
	{
		// extract the package name from the fully-qualify name
		String className = c.getName();
        int endOfPackageName = className.lastIndexOf(".");
        if(endOfPackageName>=0)
        {
	        returnName = className.substring(0,endOfPackageName);
        }
	    else
	    {
	    	return "";
	    }
	}
	return returnName;
}


 /**
 * Return the package name of the object without the class name.
 *
 * @param o  Object
 * @return  the name of the package, or null if null was passed in 
 */
 
public static String getPackageNameOnly(Object o) {

	String returnName = null;
	if (o != null)
	{
		// extract the package name from the fully-qualify name
		String className = o.getClass().getName();
        int endOfPackageName = className.lastIndexOf(".");
        if(endOfPackageName>=0)
        {
	        returnName = className.substring(0,endOfPackageName);
        }
	    else
	    {
	    	return "";
	    }
	       
	}
	return returnName;
}


/**
 * Repeat the character array given until the size
 * specified is met.  For example, if a character array
 * of 3 is specified as the repeating character array
 * (I.E 'ABC') and the size needed is 5, the resultant
 * array will be 'ABCAB'.
 *
 * NOTE: This has been tested and proven to be faster
 *       than looping aLength times setting the characters
 *       individually.  It is LOTS faster than creating a 
 *       string buffer and doing a .append for each required
 *       character.
 *
 * @param char[] arrayToRepeat  The character array to repeat
 * @param int lengthNeeded  The size of the character array needed
 * @return character array of lengthNeeded
 */
public static char[] getRepeatedCharArray(char[] arrayToRepeat, int lengthNeeded)
{
	/* By default, if we have a negative length, return an empty array */
	if(lengthNeeded < 0)
		lengthNeeded = 0;

	/* Create the new array of the specified length */	
    char[] requestedOutput = new char[lengthNeeded];

    /* If the length is greater than zero,
    **    copy over the specified amount
    */
    if(lengthNeeded > 0)
    {
	    /* If the lengthNeeded is less than the arrayToRepeat
	    **    simply copy over the subset needed
	    ** otherwise
	    **    repeat the array the specified amount
	    */
	    int mov_amt = arrayToRepeat.length;

	    if(mov_amt > lengthNeeded)
	   	{
		   	mov_amt = lengthNeeded;
	   	}

	   	/* Prime the requestedOutput array with the arrayToRepeat */
	   	System.arraycopy(arrayToRepeat,0,requestedOutput,0,mov_amt);

	   	for (; mov_amt * 2 < lengthNeeded; mov_amt *= 2)
	    {
	        System.arraycopy(requestedOutput, 0, requestedOutput, mov_amt, mov_amt);
	    }

	    /* Copy over the subset needed */
	    if(lengthNeeded > mov_amt)
	    {
		    System.arraycopy(requestedOutput, 0, requestedOutput, mov_amt, lengthNeeded - mov_amt);
	    }
    }
    
    return requestedOutput;
}


/**
 * Repeat the string given until the size
 * specified is met.  For example, if a string
 * of 3 is specified as the repeating string
 * (I.E 'ABC') and the size needed is 5, the resultant
 * string will be 'ABCAB'.
 * 
 * @param String strToRepeat  The string to repeat
 * @param int lengthNeeded  The size of the character array needed
 * @return String of lengthNeeded
 */
public static String getRepeatedString(String strToRepeat, int lengthNeeded)
{
	if(strToRepeat == null)
		return strToRepeat;

	return new String(getRepeatedCharArray(strToRepeat.toCharArray(),lengthNeeded));
}


/** Returns a String which is a modified version of
*   <I>anInputString</I>, with all <I>aSearchString</I>
*      converted to <I>aReplacementString</I>.
*  <B> NOTE:  If search value or replacement value is null, 
*     this method returns the original string.
*  @param anInputString   the original string
*  @param aSearchString     the string to be replaced
*  @param aReplacementString   the string to use in place of aSearchString in the returned String
*      @return String
*/
public static String getReplaceAll(String anInputString,
            						String aSearchString,
     						        String aReplacementString) {

	if (anInputString == null) 
	{
		return null;
	}
	else if (aSearchString == null || aReplacementString == null)
	{
		return anInputString;
	}
	
    String sourceString = anInputString;
	String beforeString = "";

	final StringBuffer returnString = new StringBuffer( "" );
	boolean moreOccurrences = true;
		
    while (moreOccurrences)
    {
       final int i = sourceString.indexOf( aSearchString );

       if ( i < 0 )
       {
	       beforeString = sourceString;
           moreOccurrences = false;
       }
       else
       {
           beforeString = sourceString.substring(0,i) + aReplacementString;
			//shorten the string we are searching, removing the text prior
			// to search string
           sourceString = sourceString.substring(i);
			// remove the search chars
           sourceString = sourceString.substring(aSearchString.length()) ;
       }
       returnString.append(beforeString);
    }
    return returnString.toString();
}



/**
 *    Given a throwable, this method will capture the stack trace
 *     information and return it as a String
 *
 *   @param  t       a Throwable
 *   @return String  the stack trace information
 *
 */
 public static String getStackTrace(Throwable t)
 {
	if (t == null)
	{
		return null;
	}
	
	final ByteArrayOutputStream os=new ByteArrayOutputStream();
	PrintWriter pw = new PrintWriter(os,true);
	if (pw != null)
	{
		t.printStackTrace(pw);
	}
	return "Stack trace:" + NL + os.toString();
 }


/**
 * Attempt to convert a string of hex characters back into a byte array.
 * 
 * @return byte[]
 * @param hexString java.lang.String
 */
public static byte[] hexToByteArray(String hexString) throws IllegalArgumentException {

	final int BASE_HEX = 16;
	
	if (hexString == null)
	{
		return null;
	}
	// if it's a hex string, it should have an even number of characters
	if (hexString.length() % 2 != 0)
	{
		throw new IllegalArgumentException("argument not a valid hex string:" + hexString);
	}
		
	int stringLength = hexString.length()/2;
	byte[] b = new byte[stringLength];
	
	// go through the string, and for each 2-byte hex code, get the int equivalent
	for (int i=0; i<stringLength; i++)
	{
		int start = i*2;
		b[i]=(byte)Integer.parseInt(hexString.substring(start,start+2),BASE_HEX);
	}
	return b;
}


/**
 * Determines if the ascii value of each of the characters in the input 
 * string is within the specified range.
 * 
 * @return boolean true if all in range, false otherwise
 * @param anInputString string to check ascii values
 * @param beginRange beginnig ascii value
 * @param endRange ending ascii value
 */
public static boolean isAsciiValueInRange(String anInputString, int beginRange, int endRange) {

	if (isNullOrEmptyNoTrim(anInputString))
	{
		return false;
	}
	if (!(beginRange >= 0 && endRange >= 0))
	{
		return false;
	}		
	byte [] byteArray = anInputString.getBytes();
	for (int i = 0; i < anInputString.length(); i++)
	{
		if (byteArray[i] < beginRange || byteArray[i] > endRange)
		{
			return false;
		}
			
	}	
	return true;
}


/**
 *   Returns a boolean indicating whether or not <I>anInputString1</I>
 *   is equal to <I>anInputString2</I> in a <B>case sensitive</B>
 *   manner.   
 *   <B>NOTE: This treats empty string ("") and null as being equal.</B>
 *
 *  @param  anInputString1   string to compare with second string
 *  @param  anInputString2   string to compare with first string 
 *  @return boolean
 */
public static boolean isEqual(String anInputString1,String anInputString2 ) {

	boolean isNoe1 = isNullOrEmpty(anInputString1);
	boolean isNoe2 = isNullOrEmpty(anInputString2);
	
	// if they are both null or empty, return true
	if (isNoe1 && isNoe2)
	{
		return true;
	}
	
	// if neither is null or empty, do string comparison
   	if ( !isNoe1 && !isNoe2 )
    {
    	return anInputString1.equals( anInputString2 );
    }
	
    // should get here if one is null or empty and one is not
    return false;
}


/**
*    Returns a boolean indicating whether or not <I>anInputString1</I>
*    is equal to <I>anInputString2</I> in a <B>case <I>in</I>sensitive</B>
*    manner. <br>  
*    <B>NOTE: This treats empty string ("") and null as being equal.</B>
*  	@param  anInputString1   string to compare with second string
*  	@param  anInputString2   string to compare with first string 
*   @return boolean
*/
public static boolean isEqualIgnoreCase(String anInputString1,
            								String anInputString2 ) {

	boolean isNoe1 = isNullOrEmpty(anInputString1);
	boolean isNoe2 = isNullOrEmpty(anInputString2);
	
	// if they are both null or empty, return true
	if (isNoe1 && isNoe2)
	{
		return true;
	}
	
	// if neither is null or empty, do string comparison
   	if ( !isNoe1 && !isNoe2 )
    {
    	return anInputString1.equalsIgnoreCase( anInputString2 );
    }
	
    // should get here if one is null or empty and one is not
    return false;
}

/**
*  Returns a boolean indicating whether or not  <I>anInputLong</I>
*    is a null object.
* @param  anInputLong    a Long to check
* @return boolean
*/
public static boolean isNull(Long anInputLong ) {

    return anInputLong == null;
}

/**
*  Returns a boolean indicating whether or not  <I>anInputString</I>
*    is a null object.
* @param  anInputString    a string to check
* @return boolean
*/
public static boolean isNull(String anInputString ) {

	return anInputString == null;
}


/**
*	Returns a boolean indicating whether or not <I>anInputString</I>
*     is an empty String ("" or any number of blank spaces) or a null object.<br>
*   <br>
*   <b>Note: use "isNullOrEmptyNoTrim" if a String of one or more blanks is
*          not to be treated as "empty".</b><br>
*   
*  @param anInputString   a string to check
*  @return boolean
*/
public static boolean isNullOrEmpty(String anInputString ) {
	
	if (anInputString == null)
	{
		return true;
	}
    		
	if (anInputString.trim().length() == 0)
	{
		return true;
	}
    		
	return false;
}
public static boolean isNotNullOrEmpty(String anInputString)
{
    return !isNullOrEmpty(anInputString );
}

/**
 * Returns a boolean indicating whether or not <I>anInputString</I>
 * is an empty String or a null object, " " is not empty
 * @param anInputString   a string to check
 * @return boolean
 */
public static boolean isNullOrEmptyNoTrim(String anInputString) {
	if (isNull(anInputString))
	{
		return true;
	}
	if	(anInputString.length() == 0)
	{
		return true;
	}		
	return false;
}


/**
*  return <i>true</i> if all characters <i>anInputString</i>
*    are digits.
*
*  @see java.lang.Character
*
*  @param  anInputString  a string to check
*  @return boolean        true if each and every character is a digit
*/
public static boolean isNumeric(String anInputString) {

	if (isNullOrEmpty(anInputString))
	{
		return false;
	}
	
	for (int i = 0; i < anInputString.length(); i++)
	{
		if ( !Character.isDigit(anInputString.charAt(i)) )
		{
			return false;
        }
	}
    return true;
}


/**
 * Returns true if input string stripped of allowable characters contains 
 * only digits, false otherwise
 * @return boolean 
 * @param anInputString String to test for numeric only after allowableChars are stripped
 * @param allowableChar Characters to strip from input string
 */
public static boolean isNumeric(String inputString, String allowableChars) {
	
	return isNumeric(stripSymbols(inputString, allowableChars));
}


/**
 * Determines if the double value of the input numeric string is within
 * the specified range.
 * @return boolean true if value is within range, false otherwise
 * @param numericString String representation of value to check
 * @param rangeMin Range minimum
 * @param rangeMax Range maximum
 */
public static boolean isNumericValueInRange(String numericString, double rangeMin, double rangeMax) {

	if (!isNumeric(numericString))
	{
		return false;
	}

	try
	{
		Double numericValue = new Double (numericString);
		if (numericValue.doubleValue() > rangeMin && numericValue.doubleValue() < rangeMax)
		{
			return true;
		}	
		else
		{
			return false;
		}	
	}
	catch (NumberFormatException nfe)
	{
		return false;
	}
}


/**
 * Determines if the long value of the input numeric string is within
 * the specified range.
 * @return boolean true if value is within range, false otherwise
 * @param numericString String representation of value to check
 * @param rangeMin Range minimum
 * @param rangeMax Range maximum
 */
public static boolean isNumericValueInRange(String numericString, long rangeMin, long rangeMax) {

	if (!isNumeric(numericString))
	{
		return false;
	}

	try
	{
		Long numericValue = new Long (numericString);
		if (numericValue.longValue() > rangeMin && numericValue.longValue() < rangeMax)
		{
			return true;
		}	
		else
		{
			return false;
		}	
	}
	catch (NumberFormatException nfe)
	{
		return false;
	}
}


/**
*  return <i>true</i> if all characters in<i>anInputString</i>
*    are the same. <br>
*  This method is useful to determine if someone has entered
*    potentially invalid data in a field (111111111 for ssn, for example) <br><br>
*  If <i>anInputString</i> is length 1, <i>false</i> is returned.<br><br>
*  If <i>anInputString</i> has text along with leading or trailing spaces, <i>false</i> is returned.<br>
* 
*  @param  anInputString  a string to check
*  @return boolean        true if each and every character is the same 
*/
public static boolean isRepeatedChar(String anInputString) {

	if (isNullOrEmpty(anInputString))
	{
		return false;
	}

	char[] chars = anInputString.toCharArray();
	int len = chars.length;

	// if it's only one character, return false
	if (chars.length == 1)
	{
		return false;
	}
	
	char firstChar = chars[0];

	for (int i = 1; i < len; i++)
	{
		if (chars[i] != firstChar)
		{
			return false;
        }
	}
    return true;
}


/**
 * To justify the string and pad/truncate as needed.
 * Creation date: (10/04/2001 10:49:47 AM)
 * @return String
 * @param input String
 * @param len int
 * @param padChar String
 * @param just String
 */
public static String justifyAndPad(String input, int len, String padStr, String just)
{
	boolean padLeft = false;

	if(just != null)
	{
		input = input.trim();

		if(just.equalsIgnoreCase("right"))
			padLeft = true;
	}
	
    return padString(input,len,padStr,padLeft,true);
}


/** 
*   Returns a String after right-justifying and padding <I>anInt</I> to
*	   <I>aLength</I>  and zero-filling to the left.
*   @param anInt an integer to be converted to a String
*	        and right-justified and zero-filled
*	@param aLength the int value containing the desired 
*	        length of the return String
*	@return String
*/
public static String padLeadingZeroes(int anInt,int aLength )  {
	
	return padLeadingZeroes(String.valueOf(anInt),aLength);
}


/** 
*   Returns a String after right-justifying and padding <I>aLong</I> to
*	   <I>aLength</I>  and zero-filling to the left.
*   @param aLong a long to be converted to a String
*	        and right-justified and zero-filled
*	@param aLength the int value containing the desired 
*	        length of the return String
*	@return String
*/
public static String padLeadingZeroes(long aLong,int aLength )  {
	
	return padLeadingZeroes(String.valueOf(aLong),aLength);
}


/** 
*   Returns a String after right-justifying and padding <I>anInt</I> to
*	   <I>aLength</I>  and zero-filling to the left.
*      Note:  If a null String is passed as input, a zero-filled String
*      	of length aLength will be returned.  If the input string is
*       longer than the input length, it will be truncated.<p>
*   @param aString the String to be right-justified and zero-filled
*   @param aLength the int value containing the desired length of the return String
*	@return String
*/
public static String padLeadingZeroes(String aString,int aLength) {

	if (isNull(aString))
	{
		aString = "";
	}
	return padString(aString.trim(), aLength, "0", true, true);
}


/**
 * Pad the input string with the specified character array to the specified length. 
 * If padLeft left pad the string, otherwise right pad the string.
 * If the input string length is greater than the specified length
 * and truncate is true the input string truncated to the specified
 * length is returned, otherwise the input string is returned.
 *
 * NOTE:  This function now utilizes the getRepeatedCharArray which
 *        has significantly improved performance.
 *
 * @return a padded string
 * @param inputString the string to pad
 * @param totalLength length of padding string
 * @param padChars padding character(s)
 * @param padLeft indicates whether to pad left or right
 * @param truncate indicates whether to truncate the input string or not
 */
public static String padString(
    String inputString,
    int totalLength,
    char[] padChars,
    boolean padLeft,
    boolean truncate)
{

    /* If there is nothing to pad, return the original string */
    if (padChars.length == 0 || StringFunctions.isNull(inputString))
    {
        return inputString;
    }

    int inputStringLength = inputString.length();

    /*
    ** If string is larger than needed, perform truncation as required
    **
    ** NOTE:  The following code was copied from the original padString ...
    **        just because you pad on the left should not imply you trim on the left.
    **        This functionality should be extracted and placed in another function but it
    **        has been kept due to current dependencies.
    ** 
    ** If the input string length is already the length (or greater)
    **   if we need to truncate
    **      if padLeft specified
    **         return the left trimmed inputString
    **      else
    **         return the right trimmed inputString
    **   else
    **      return the inputString
    */
    
    if (inputStringLength >= totalLength)
    {
	    /* See if the value should be trimmed */
        if (truncate)
        {
	        /* Trim on the left if padLeft was specified */
            if (padLeft)
            {
                return inputString.substring(inputStringLength - totalLength, inputStringLength);
            }
            else
            {
	            /* Trim on the right */
                return inputString.substring(0, totalLength);
            }
        }
        else
        {
	        /* Return original inputString */
            return inputString;
        }
    }

    /******* Perform padding *******/
    
    /* Determine the length needed */
    int lengthNeeded = totalLength - inputStringLength;

    StringBuffer result = new StringBuffer(totalLength);

    /* Build the result appending or prepending the padChars */

    if(padLeft)
    {
	    result
	    	.append(getRepeatedCharArray(padChars,lengthNeeded))
	    	.append(inputString);
    }
    else
    {
	    result
	    	.append(inputString)
	    	.append(getRepeatedCharArray(padChars,lengthNeeded));
    }
    
    return result.toString();
}


/**
 * Pad the input string with the specified string to the specified length. 
 * If padLeft left pad the string, otherwise right pad the string.
 * If the input string length is greater than the specified length
 * and truncate is true the input string truncated to the specified
 * length is returned, otherwise the input string is returned.
 *
 * NOTE: This function now converts the padString to a character array and
 *       calls the other padString signature that accepts the character array.
 *       The character array significalntly improves performance when repeating characters
 *       (via the getRepeatedCharArray method).
 *
 * @return a padded string
 * @param inputString the string to pad
 * @param totalLength length of padding string
 * @param padString padding character(s)
 * @param padLeft indicates whether to pad left or right
 * @param truncate indicates whether to truncate the input string or not
 */
public static String padString(String inputString, int totalLength, String padString, boolean padLeft, boolean truncate) {

	if(StringFunctions.isNullOrEmptyNoTrim(padString))
		return inputString;
		
	return padString(inputString,totalLength,padString.toCharArray(),padLeft,truncate);
}


/** 
*	Returns a String which is based on <I>anInputString</I>
*    after padding it to <I>aLength</I> to the right with the 
*    characters found in <I>aPadString</I>.     <br>
* <B>Note: If the input string is longer than aLength, 
*  	the whole string will be returned -- it will not be 
*  	truncated to aLength.</B> <p>
*
*	If a null String is passed as input, an empty string of <i>aLength</i> 
*    will be returned.<p>
*  @param anInputString the String to be left-justified and right-padded
*  @param aLength the int value containing the desired length of the return String
*  @param aPadString the String to be used to right-pad
*  @return String
*/
public static String padTrailing(String anInputString,int aLength,	String aPadString ) {	

	if (isNull(anInputString))
	{
		return getBlankString(aLength);
	}	
	return padString(anInputString, aLength, aPadString, false, false);
}


/**
* Returns a String constructed of anInputString padded to 
* aLength to the right with aPadString.
* If the input string is longer than aLength, 
* it will be truncated to aLength.
*
* If a null String is passed as input, an empty string of aLength 
* will be returned.
*  @param anInputString the String to be left-justified and right-padded
*  @param aLength the int value containing the desired length of the return String
*  @param aPadString the String to be used to right-pad
*  @return String
 * 
 */
public static String padTrailingFixedLength(String anInputString, int aLength, String aPadString) {

	if (isNull(anInputString))
	{
		return getBlankString(aLength);
	}
	return padString(anInputString, aLength, aPadString, false, true);
}


/** 
*	Returns a String which is based on <I>anInputString</I>
*    after padding it to <I>aLength</I> to the right with spaces. 
*	<B>Note: If the input string is longer than aLength, 
*  	the whole string will be returned -- it will not be 
*  	truncated to aLength.</B> <p>
*
*	If a null String is passed as input, an empty string of <i>aLength</i> 
*    will be returned.<p>
*  @param anInputString the String to be left-justified and right-padded
*  @param aLength the int value containing the desired length of the return String
*  @return String
*/
public static String padTrailingSpaces(String anInputString,int aLength) {

	if (isNull(anInputString))
	{
		return getBlankString(aLength);
	}	
	return padTrailing(anInputString,aLength," " );
}


/** 
*	Returns a String which is based on <I>anInputString</I>
*    after padding it to <I>aLength</I> to the right with spaces. 
*	<B>Note: If the input string is longer than "aLength", 
*		   it will be truncated to the length of "aLength"</B> <p>
*		If a null string is passed as input, an empty String of 
*			size "aLength" will be returned
*
*  @param anInputString the String to be left-justified and right-padded
*  @param aLength the int value containing the desired length of the return String
*  @return String
*/
public static String padTrailingSpacesFixedLength(String anInputString,int aLength) {
   
	if (isNull(anInputString))
	{
		return getBlankString(aLength);
	}
	
	return padTrailingFixedLength(anInputString,aLength," ");
}


/**
 *  This method converts a portion of a byte array to a string starting at the specified 
 *  offset for the length specified.  If the offset is out of range, an empty string 
 *  is returned.  If the length specified is larger than the remainder of the byte array, 
 *  the string returned will be shorter than the length.
 *
 *  @param  byteArray       Array of bytes to be converted
 *  @param  offset          index of the first character to be converted
 *  @param  length          length to be converted
 *  @return string          the string representation of the byte array
 */
 public static String stringFromBytes(byte[] data, int offset, int length) 
 {
	 /* Return an empty string if the offset is out of range for the array
	 ** or the input is null.
	 */
	 if(data == null || offset < 0 || offset > data.length || length <=0)
	 	return "";

	 /* Compute the number of bytes to convert */
	 int numToConvert;

	 /* If the requested length is greater than the remainder of the buffer size
	 **    use the remainder of the buffer to generate the string
	 ** otherwise
	 **    use the length specified
	 */
	 if(offset + length > data.length)
	 	numToConvert = data.length - offset;
	 else
	 	numToConvert = length;
	 
	 return new String(data,offset,numToConvert);
 }


/**
 *  This method converts a String to boolean data type. If a null value 
 *   is passed in, the default value will be used; otherwise, as long as 
 *   a String is passed in, it will return true or false based on Java's 
 *   interpretation of the String.<p>
 *
 * Creation date: (05/16/2001 1:53:42 PM)
 *  @param  aString     lang.String A string value to be converted
 *  @param  defaultValue boolean    A default value that will be returned if the
 *                          conversion failed <p>
 * @return boolean  according to rules within java.lang.Boolean, this will return
 *                  true if the String is "true", regardless of case, and false for anything else
 */
public static boolean stringToBoolean(String aString, boolean defaultValue) {

	if (aString == null)
	{
		return defaultValue;
	}

	return Boolean.valueOf(aString).booleanValue();
}


/**
 *  This method converts string to double data type. If the conversion failed
 *  a default value that is passed in is returned to the caller.
 *
 *  @param  stringDataValue A string value to be converted
 *  @param  defaultValue    A default value that will be returned if the
 *                          converstion failed
 *  @return double representation of the specified string
 */
 public static double stringToDouble(String stringDataValue, double defaultValue) {

        double convertedDataValue;
        
        if(stringDataValue != null && stringDataValue.length() > 0)
        {
            Double doubleDataValue = null;
            try
            {
               doubleDataValue = new Double(stringDataValue);
            }
            catch(NumberFormatException e)
            {
		        //if it failed, maybe it's because it had symbols in it.
		        //   take those out and try again.
	 	        // Doing this to handle exception cases rather than putting
	  	        //   this call up front and taking the hit on every single
	   	        //   invocation.
	       		String strippedValue = stripSymbols(stringDataValue, "$,");
	 	        try
 	       		{
 	          		doubleDataValue = new Double(strippedValue);
 	       		}
 	       		catch(NumberFormatException e2)
 	       		{
	               doubleDataValue = null;
 	       		}
            }
               
            if(doubleDataValue != null)
            {
                convertedDataValue = doubleDataValue.doubleValue();
            }
            else
            {
                convertedDataValue = defaultValue;
            }
        }
        else
        {
            convertedDataValue = defaultValue;
        }
        
        return convertedDataValue;
}


/**
 *  This method converts string to float data type. If the conversion failed
 *  a default value that is passed in is returned to the caller.
 *
 *  @param  stringDataValue A string value to be converted
 *  @param  defaultValue    A default value that will be returned if the
 *                          converstion failed
 *  @return  float representation of the specified string 
 */
 public static float stringToFloat(String stringDataValue, float defaultValue) {
	
	 return (float)stringToDouble(stringDataValue, defaultValue);
 }


/**
 *  This method converts string to integer data type. If the conversion failed
 *  a default value that is passed in is returned to the caller.
 *
 *  @param  stringDataValue A string value to be converted
 *  @param  defaultValue    A default value that will be returned if the
 *                          converstion failed
 *  @return  int     the string converted to int or the default value 
 */
 public static int stringToInt(String stringDataValue, int defaultValue) {
	
	 return (int)stringToLong(stringDataValue, defaultValue);
 }
 /**
  *  This method converts string to integer data type. If the conversion failed
  *  a default value that is passed in is returned to the caller.
  *
  *  @param  stringDataValue A string value to be converted
  *  @param  defaultValue    A default value that will be returned if the
  *                          converstion failed
  *  @return  int     the string converted to int or the default value 
  */
  public static int charToInt(char stringDataValue, int defaultValue) {
      Character c = new Character(stringDataValue);
     return (int)stringToLong(c.toString(), defaultValue);
  }


/**
 *  This method converts string to integer data type. If the conversion failed
 *  a default value that is passed in is returned to the caller.
 *
 *  @param  stringDataValue A string value to be converted
 *  @param  defaultValue    A default value that will be returned if the
 *                          converstion failed
 *  @return  int     the string converted to int or the default value 
 */
 public static int stringToInt(String stringDataValue, int defaultValue, int radix) {
	
	 return (int)stringToLong(stringDataValue, defaultValue, radix);
 }


/**
 *  This method converts string to long data type. If the conversion failed
 *  a default value that is passed in is returned to the caller.
 *
 *  @param  stringDataValue A string value to be converted
 *  @param  defaultValue    A default value that will be returned if the
 *                          converstion failed
 *  @return long   the string as a long or a default value
 */
 public static long stringToLong(String stringDataValue, long defaultValue) {

	 return stringToLong(stringDataValue, defaultValue, 10);
}


/**
 *  This method converts string to long data type. If the conversion failed
 *  a default value that is passed in is returned to the caller.
 *
 *  @param  stringDataValue A string value to be converted
 *  @param  defaultValue    A default value that will be returned if the
 *                          converstion failed
 *  @return long   the string as a long or a default value
 */
 public static long stringToLong(String stringDataValue, long defaultValue, int radix) {

	 // convertedDataValue is declared as long, so that it is capable of
     //  storing all integral data type, i.e., char, byte, short, int, and
     //   long 
    long convertedDataValue = 0;
        
    // TAG: a
    if(stringDataValue != null && stringDataValue.length() > 0)
    {
        try
        {
           convertedDataValue = Long.parseLong(stringDataValue, radix);
        }
        catch(NumberFormatException e)
        {
	        //if it failed, maybe it's because it had symbols in it.
	        //   take those out and try again.
	        // Doing this to handle exception cases rather than putting
	        //   this call up front and taking the hit on every single
	        //   invocation.
	       String strippedValue = stripSymbols(stringDataValue, "$,");
        
 	       try
 	       {
 	          convertedDataValue = Long.parseLong(strippedValue, radix);
 	       }
 	       catch(NumberFormatException e2)
 	       {
 	          convertedDataValue = defaultValue;
 	       }
        }
    }
    else
    {
    	convertedDataValue = defaultValue;                  // TAG: a2
    }
        
    // TAG: b
    return convertedDataValue;
}


/**
 *  This method converts string to short data type. If the conversion failed
 *  a default value that is passed in is returned to the caller.
 *
 *  @param  stringDataValue A string value to be converted
 *  @param  defaultValue    A default value that will be returned if the
 *                          converstion failed
 *  @return short   the string as a short or a default value
 */
 public static short stringToShort(String stringDataValue, short defaultValue) {

	 return (short)stringToLong(stringDataValue,defaultValue);
}


	/***********************************************************************
	*   Strip Symbols
	*   @param <I>sIn</I> is the String to be stripped
	*   @param <I>sSymbols<I> are the symbols to be stripped from the string.
	*   <p> e.g. "$.," means all occurrences of "$", ".", and "," will be 
	*    stripped from the string<br>
	*   Returns null  if input string is null.<br>
	*   Returns sIn if sSymbols is null
	*   @return String - the string with symbols stripped
	***********************************************************************/
	public static String stripSymbols(String sIn, String sSymbols)
	{
		if (sIn == null)
		{
			return null;
		}
		if (sSymbols == null)
		{
			return sIn;
		}
		
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(sIn, sSymbols);
		while ( st.hasMoreTokens() )
		{
			sb.append(st.nextToken());
		}

		return sb.toString();
	}
	public static boolean isSringHasSymbols(String sIn, String sSymbols)
	{
		boolean status=Boolean.FALSE;
		if (sIn == null)
		{
			return status;
		}
		if (sSymbols == null)
		{
			return status;
		}
		
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(sIn, sSymbols);
		int tokenCount=st.countTokens();
		while ( st.hasMoreTokens() )
		{
			sb.append(st.nextToken());
		}
		if(tokenCount > 1 || tokenCount == 1 && sIn.length() != sb.length())
		{
			status=Boolean.TRUE;
		}
		return status;
	}
	public static String stripSymbols(String sIn)
	{
		return stripSymbols(sIn,STRIP_SYMBOLS);
	}


/**
 * This method runs one way hash computation on the input string and returns
 * the computed digest value
 * @param type The hash algorithm to use, such as MD5
 * @param input The input string on which the computation is run, such as a
 * password
 * @return byte[] The computed hash value of the string.  null is returned if
 * the hash algorithm is unknown
 */
 
public static byte[] toDigest( String type, String input ) {

	byte[] result = null;

	try 
	{
		final java.security.MessageDigest md = 
			java.security.MessageDigest.getInstance( type );
		md.update( input.getBytes() );
		result = md.digest();
	} 
	catch (java.security.NoSuchAlgorithmException e) 
	{
		e.printStackTrace();
	}
	
	return result;	
}


/**
 * This method converts a byte array into a String of hex digits.
 * @param input The input byte array
 * @return String Each byte in the array is converted into a 2-character hex
 * representation and the array is consolidated in the return string
 */

public static String toHexString( byte[] input )
{

	final StringBuffer sb = new StringBuffer();
	final int inputLength = input.length;
	
	for (int i = 0; i < inputLength; i++ ) 
	{
		final String tmp = Integer.toHexString( (int)( input[i] & 0xff ) );
		if (tmp.length() <= 1) 
		{
			sb.append( "0" );
		}
		sb.append( tmp );
	}
	return sb.toString();
}


/** 
 *   Given a String representing a number, returns a String 
 *    with a descriptive suffix on it.  Sample return values: <br>
 * <PRE>
 *   null = null
 *   ""  = ""
 *   "0" = "0"
 *   "1" = "1st"
 *   "11" = "11th"
 *   "21" = "21st"
 *   "999" = 999th"
 *   invalid string = the input string
 *   ...
 *  </PRE>
 *
 *   @param anInputString   a String, expected to be an integer value
 *   @return String  see examples above
 */
public static String toOrdinalNumber(String anInputString) 
{
	final String SUFFIX_ND = "nd";
	final String SUFFIX_RD = "rd";
	final String SUFFIX_ST = "st";
	final String SUFFIX_TH = "th";

	String returnString = null;
		
	int intValue = StringFunctions.stringToInt(anInputString,-1);
	if (intValue == -1 || intValue == 0)
	{
		returnString = anInputString;
	}
	else
	{
		int remainder = intValue % 100;
		switch (remainder)
		{
			case 11: returnString = String.valueOf(intValue) + SUFFIX_TH;
					 break;
					 
			case 12: returnString = String.valueOf(intValue) + SUFFIX_TH;
					 break;
					 
			case 13: returnString = String.valueOf(intValue) + SUFFIX_TH;
					 break;
			default:
					int rem2 = remainder % 10;
					switch (rem2)
					{
						case 1: returnString = String.valueOf(intValue) + SUFFIX_ST;
								break;
						case 2: returnString = String.valueOf(intValue) + SUFFIX_ND;
								break;
						case 3: returnString = String.valueOf(intValue) + SUFFIX_RD;
								break;
						default: returnString = String.valueOf(intValue) + SUFFIX_TH;
					}
		}
	}
	return returnString;
}


/** 
 *   Returns a USAA number which is a modified version of
 *   <I>anInputUsaaNr</I>, converted to terminal digit order<br>
 *   To convert a USAA number from terminal digit to straight/display
 *   format, use fromTerminalDigitOrder.
 *
 *   @param anInputUsaaNr a String
 *   @return String
 */
public static String toTerminalDigitOrder(String anInputUsaaNr) 
{
	final int USAA_NR_LENGTH = 9;
	
    if (anInputUsaaNr == null)
    {
	    return null;
    }

    if (!isNumeric(anInputUsaaNr))
    {
	    return null;
    }

    String usaaNr = null;
    if (anInputUsaaNr.length() < USAA_NR_LENGTH)
    {
    	usaaNr = padLeadingZeroes(anInputUsaaNr, USAA_NR_LENGTH);
    }
    else
    {
	    usaaNr = anInputUsaaNr;
    }

    //We have a valid number; change it to terminal digit format
    StringBuffer buffer = new StringBuffer(9);
    buffer.append(usaaNr.charAt(7));
    buffer.append(usaaNr.charAt(8));
    buffer.append(usaaNr.charAt(5));
    buffer.append(usaaNr.charAt(6));
    buffer.append(usaaNr.charAt(0));
    buffer.append(usaaNr.charAt(1));
    buffer.append(usaaNr.charAt(2));
    buffer.append(usaaNr.charAt(3));
    buffer.append(usaaNr.charAt(4));
	
   /* buffer.append(usaaNr.substring(7, 9));
    buffer.append(usaaNr.substring(5, 7));
    buffer.append(usaaNr.substring(0, 5));
	*/
    return buffer.toString();
}


/** 
 *   Returns a String which is a modified
 *   version of
 *   <I>anInputString</I>,
 *   converted to Proper Case
 *
 *   @param anInputString a String
 *   @return String
 */
public static String toTitleCase(String anInputString) 
{
    if (anInputString == null)
    {
	    return null;
    }
    
    char[] buf = new char[anInputString.length()];
    
    boolean isFollowingABlank = true;
    anInputString.getChars(0, anInputString.length(), buf, 0);

    // Capitalize first letter of each word
	int bufLength = buf.length;
	
    for (int i = 0; i < bufLength; i++) 
    {
        if (isFollowingABlank)
        {
            buf[i] = Character.toTitleCase(buf[i]);
        }
        else
        {
            buf[i] = Character.toLowerCase(buf[i]);
        }
        isFollowingABlank = Character.isWhitespace(buf[i]);
    }
    return new String(buf);
}


/**
 * This method trims zeroes from the beginning of a string.
 * If null is passed, it returns null. <br>
 * If input of all zeroes is passed, it returns empty string (""). <br>
 * If a string is passed beginning with any character other than zero, that string is returned. <br>
 * If a string is passed with leading spaces, those spaces will be removed.<br>
 *
 * @param value the source String to be trimmed.
 * @return String the resulting trimmed string.
 */
public static String trimLeadingZeroes(String aString) {

	if (aString == null)
	{
		return null;
	}
	String trimmedInputString = aString.trim();
	String returnString = "";

	boolean done = false;
	int len = trimmedInputString.length();
	int idx = 0;
	char ZERO = '0';
	
	while (idx < len && !done) 
	{
		if(trimmedInputString.charAt(idx) == ZERO) 
		{
			idx++;
		}
		else 
		{
			returnString = trimmedInputString.substring(idx);
			done = true;
		}
	}
	return returnString;
}
/**
 * This method slightly enhances the <code>trim</code> method from java.lang.String
 *   by adding a null check.<br>
 * If null is passed, it returns null. <br>
 * Otherwise, it returns the input string with leading and trailing spaces removed.<br>
 * 
 * @see java.lang.String.trim
 *
 * @param  value  the source String to be trimmed.
 * @return String the resulting trimmed string.
 */
public static String trim(String aString) {

	if (aString != null)
	{
		aString = aString.trim();
	}
	return aString;
}
/**
 * Converts a String to a byte array without the translation of the characters.  
 * This differs from String.getBytes() in that a translation doesn't occur.  
 * When using String.getBytes(), a translation can occur from the string value 
 * to the byte value in which you cannot get back to the original string value.  <br>
 * <br>
 * Note:  if a null or empty string is passed, an empty byte array is returned.
 * @param inString the string to be converted to a byte array
 * @return byte byte array representing string
 */
public static byte[] stringToBytes(String inString) {
	// if a null or empty string is supplied, just return an empty byte array
	if (StringFunctions.isNullOrEmpty(inString))
	{
		return new byte[0];
	}
	
	// create an intermediate character array representing the string
	final char [] inChars = new char[inString.length()];
	inString.getChars(0, inString.length(), inChars, 0);
	// convert the character array to a byte array
	final byte [] inBytes = new byte[inChars.length];
   	for (int i = 0; i < inChars.length; i++)
   	{
		inBytes[i] = (byte)inChars[i];
    }
    return inBytes;				
}

	/**
	 * Method notNullOrEmpty is used to validate the input parameter is not Null or Empty.
	 * Currently this method returns the inverse of the StringFunctions.isNullOrEmpty call
	 * 
	 * @param propertyName: property to be checked
	 * @return boolean: return true for not null and not Empty string else false.
	 */
	/*private boolean notNullOrEmpty(String propertyName)
	{
		return !StringFunctions.isNullOrEmpty(propertyName);
	}*/

	/** 
	*	Returns a String which consists only of the
	*      alphanumeric characters fould in <I>aString</I>.
	*   <br>
	*   This function could be used for things like Stripping spaces or dashes 
	*   from international zipcodes
	*	@param  aString  String   the string to pull the alphanumerics from
	*	@return String
	*/
	public static String getAlphaNumerics(String aString)   
	{
		StringBuffer tempStringBuffer = new StringBuffer("");
		@SuppressWarnings("unused")
		String alphaNumericString = null;
		String tempChar;
	
		if (isNullOrEmpty(aString)) 
		{
			alphaNumericString = aString;
		}
		else
		{
			int stringLength = aString.length();
	
			for (int i=0; i < stringLength; i++)
			{
				tempChar = aString.substring(i,i+1);
				if (StringFunctions.isAlphaNumeric(tempChar))
				{
					tempStringBuffer.append(tempChar);
				}
			}
			alphaNumericString = tempStringBuffer.toString();
		}
		return tempStringBuffer.toString();
	}

	/**
	*  Returns <i>true</i> if all characters <i>anInputString</i>
	*    are digits or letters.
	*
	*  @see java.lang.Character
	*
	*  @param  anInputString  a string to check
	*  @return boolean        true if each and every character is a letter or digit.
	*/
	public static boolean isAlphaNumeric(String anInputString) {

		if (isNullOrEmpty(anInputString))
		{
			return false;
		}
	
		for (int i = 0; i < anInputString.length(); i++)
		{
			if ( !Character.isLetterOrDigit(anInputString.charAt(i)) )
			{
				return false;
			}
		}
		return true;
	}
	
	 /**
     * @return true if the string is not null and has at least one non whitespace character.
     */
    public static boolean usable( String s )
    {
        boolean returnVal = false;
        if ( s != null )
        {
            s = s.trim();
            if ( s.length() > 0 )
            {
                returnVal = true;
            }
        }
        return returnVal;
    }
    public static void jsr()
    {
    	@SuppressWarnings("unused")
		String jsr123="<object width=\"600\" height=\"338\"><param name=\"allowfullscreen\" value=\"true\" /><param name=\"allowscriptaccess\" value=\"always\" /><param name=\"movie\" value=\"http://vimeo.com/moogaloop.swf?clip_id=11598705&amp;server=vimeo.com&amp;show_title=0&amp;show_byline=0&amp;show_portrait=0&amp;color=59a5d1&amp;fullscreen=1\" /><embed src=\"http://vimeo.com/moogaloop.swf?clip_id=11598705&amp;server=vimeo.com&amp;show_title=0&amp;show_byline=0&amp;show_portrait=0&amp;color=59a5d1&amp;fullscreen=1&amp;autoplay=1\" type=\"application/x-shockwave-flash\" allowfullscreen=\"true\" allowscriptaccess=\"always\" width=\"600\" height=\"338\"></embed></object>";
    	
    	log.debug("New String : " +substringBetween("http://www.vimeo.com/15640042", "clip_id=","&amp;"));
    }
    /**
     * <p>Gets the String that is nested in between two instances of the
     * same String.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.
     * A <code>null</code> tag returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.substringBetween(null, *)            = null
     * StringUtils.substringBetween("", "")             = ""
     * StringUtils.substringBetween("", "tag")          = null
     * StringUtils.substringBetween("tagabctag", null)  = null
     * StringUtils.substringBetween("tagabctag", "")    = ""
     * StringUtils.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     *
     * @param str  the String containing the substring, may be null
     * @param tag  the String before and after the substring, may be null
     * @return the substring, <code>null</code> if no match
     * @since 2.0
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag);
    }
    /**
     * <p>Gets the String that is nested in between two Strings.
     * Only the first match is returned.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.
     * A <code>null</code> open/close returns <code>null</code> (no match).
     * An empty ("") open and close returns an empty string.</p>
     *
     * <pre>
     * StringUtils.substringBetween("wx[b]yz", "[", "]") = "b"
     * StringUtils.substringBetween(null, *, *)          = null
     * StringUtils.substringBetween(*, null, *)          = null
     * StringUtils.substringBetween(*, *, null)          = null
     * StringUtils.substringBetween("", "", "")          = ""
     * StringUtils.substringBetween("", "", "]")         = null
     * StringUtils.substringBetween("", "[", "]")        = null
     * StringUtils.substringBetween("yabcz", "", "")     = ""
     * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     *
     * @param str  the String containing the substring, may be null
     * @param open  the String before the substring, may be null
     * @param close  the String after the substring, may be null
     * @return the substring, <code>null</code> if no match
     * @since 2.0
     */
    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    public static void main(String [] args)
    {
    	StringFunctions.jsr();
        String emailAddress="@#$, `3~rpra sad15 &  uroomtech.com@";
        log.debug("Inside Main");
        log.debug(isSringHasSymbols(emailAddress, ".,><?@~`!#$%^&*()()+=/|"));
        /*String[] params = emailAddress.split("~");
        String longSring="Hello man how are you doing. trying the truncate string functio by specified number of words and chars.";
        log.debug(" String functions : " + breakString(longSring,40));
        log.debug(" String functions : " + params[1]);*/
        /*log.debug( "Test variations in the email name" );
        log.debug( "Is steve@javasrc.com a valid email? " + 
                 isValidEmailAddress( "steve@javasrc.com" ) );
        log.debug( "Is steven.haines@javasrc.com a valid email? " + 
                 isValidEmailAddress( "steven.haines@javasrc.com" ) );
        log.debug( "Is steven-haines@javasrc.com a valid email? " + 
                 isValidEmailAddress( "steven-haines@javasrc.com" ) );
        log.debug( "Is steven+haines@javasrc.com a valid email? " + 
                 isValidEmailAddress( "steven+haines@javasrc.com" ) );
        log.debug( "Is steven_haines@javasrc.com a valid email? " + 
                 isValidEmailAddress( "steven_haines@javasrc.com" ) );
        log.debug( "Is steven#haines@javasrc.com a valid email? " + 
                 isValidEmailAddress( "steven#haines@javasrc.com" ) );

        // Test variations in the domain name
        log.debug( "\nTest variations in the domain name" );
        log.debug( "Is steve@java-src.com a valid email? " + 
                 isValidEmailAddress( "steve@java-src.com" ) );
        log.debug( "Is steve@java.src.com a valid email? " + 
                 isValidEmailAddress( "steve@java.src.com" ) );
        log.debug( "Is steve@java\\src.com a valid email? " + 
                 isValidEmailAddress( "steve@java\\src.com" ) );
        log.debug( "Is steve@java+src.com a valid email? " + 
                 isValidEmailAddress( "steve@java+src.com" ) );


        // Test variations in the email address suffic
        log.debug( "\nTest variations in the domain name" );
        log.debug( "Is steve@javasrc.a a valid email? " + 
                 isValidEmailAddress( "steve@javasrc.a" ) );
        log.debug( "Is steve@javasrc.aa a valid email? " + 
                 isValidEmailAddress( "steve@javasrc.aa" ) );
        log.debug( "Is steve@javasrc.aaa a valid email? " + 
                 isValidEmailAddress( "steve@javasrc.aaa" ) );
        log.debug( "Is steve@javasrc.aaaa a valid email? " + 
                 isValidEmailAddress( "steve@javasrc.aaaa" ) );
        log.debug( "Is steve@javasrc.aaaaa a valid email? " + 
                 isValidEmailAddress( "steve@javasrc.aaaaa" ) );

        // Test that the email address marks the beginning of the string
        log.debug( "\nTest that the email address marks the beginning of the string" );
        log.debug( "Is \"aaa steve@javasrc.com\" a valid email? " + 
                 isValidEmailAddress( "aaa steve@javasrc.com" ) );

        // Test that the email address marks the end of the string
        log.debug( "\nTest that the email address marks the end of the string" );
        log.debug( "Is \"steve@javasrc.com aaa\" a valid email? " + 
                 isValidEmailAddress( "steve@javasrc.com aaa" ) );*/

    }

    public static String breakString(String s, int size) {
       
        if (StringFunctions.isNullOrEmpty(s))
        {
            return "Description not available";
        }
        if(s.length() < size)
        {
            return s;
        }
        int lineLength = 0;
        StringBuffer sb = new StringBuffer();
        StringTokenizer tk = new StringTokenizer(s);
        while (tk.hasMoreTokens()) {
            String nextWord = tk.nextToken();
            if (lineLength + nextWord.length() > size) {
               break;
            }
            sb.append(nextWord + " ");
            lineLength += nextWord.length()+1;
        }
        sb.append("...");
        return sb.toString();
    }
    public static String numberBreakString(String s, int size) {
        
        if (StringFunctions.isNullOrEmpty(s))
        {
            return "Description not available";
        }
        if(s.length() < size)
        {
            return s;
        }
        int lineLength = 0;
        StringBuffer sb = new StringBuffer();
        StringTokenizer tk = new StringTokenizer(s);
        while (tk.hasMoreTokens()) {
            String nextWord = tk.nextToken();
            if (lineLength + nextWord.length() > size) {
               break;
            }
            //sb.append(nextWord + " ");
            lineLength += nextWord.length()+1;
        }
       
        return sb.toString();
    }

    /**
     * Validate the form of an email address.
     *
     * <P>Return <tt>true</tt> only if 
     *<ul> 
     * <li> <tt>aEmailAddress</tt> can successfully construct an 
     * {@link javax.mail.internet.InternetAddress} 
     * <li> when parsed with "@" as delimiter, <tt>aEmailAddress</tt> contains 
     * two tokens which satisfy {@link hirondelle.web4j.util.Util#textHasContent}.
     *</ul>
     *
     *<P> The second condition arises since local email addresses, simply of the form
     * "<tt>albert</tt>", for example, are valid for 
     * {@link javax.mail.internet.InternetAddress}, but almost always undesired.
     */
    /*public static boolean isValidEmailAddress(String emailAddress)
    {
      // a null string is invalid
      if ( emailAddress == null )
        return false;

      // a string without a "@" is an invalid email address
      if ( emailAddress.indexOf("@") < 0 )
        return false;

      // a string without a "."  is an invalid email address
      if ( emailAddress.indexOf(".") < 0 )
        return false;

      if ( lastEmailFieldTwoCharsOrMore(emailAddress) == false )
        return false;

      try
      {
        InternetAddress internetAddress = new InternetAddress(emailAddress);
        return true;
      }
      catch (AddressException ae)
      {
        // log exception
        return false;
      }
    }*/


    /**
     * Returns true if the last email field (i.e., the country code, or something
     * like .com, .biz, .cc, etc.) is two chars or more in length, which it really
     * must be to be legal.
     */
/*    private static boolean lastEmailFieldTwoCharsOrMore(String emailAddress)
    {
      if (emailAddress == null) return false;
      StringTokenizer st = new StringTokenizer(emailAddress,".");
      String lastToken = null;
      while ( st.hasMoreTokens() )
      {
        lastToken = st.nextToken();
      }

      if ( lastToken.length() >= 2 )
      {
        return true;
      }
      else
      {
        return false;
      }
    }*/
    
    public static boolean isValidEmailAddress(String emailAddress )
    {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(emailAddress);
    }

    public static String getDateTimeBegin(String date)
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,date));
        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, cal.getTime()) + " 00:00:00";
    }
    public static String getDateTimeEnd(String date)
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,date));
        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, cal.getTime()) + " 11:59:59";
    }
    
    public static String getFormattedMobileNumber(String mobileNum)
	{
			try {
				mobileNum = StringFunctions.stripSymbols(mobileNum, "+()-#$@,'`?& ");
				if (mobileNum.length() < 10 || mobileNum.length() > 12 || mobileNum.length() > 10 && (mobileNum.length() < 12)) {
					log.debug("Invalid mobile Number " + mobileNum);
					return null;
				}
				if (mobileNum.length() == 10) {
					//mobileNum = "+91-" + mobileNum;
					return mobileNum;
				}
				//return mobileNum;
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
			return null;
	}
    public static String getFormattedMobileNumberOtherCountry(String mobileNum,String phoneCode)
	{
			try {
				mobileNum = StringFunctions.stripSymbols(mobileNum, "+()-#$@,'`?& ");
				if (mobileNum.length() < 10 || mobileNum.length() > 12 || mobileNum.length() > 10 && (mobileNum.length() < 12)) {
					log.debug("Invalid mobile Number " + mobileNum);
					return null;
				}
				if (mobileNum.length() == 10) {
					mobileNum = phoneCode + mobileNum;
					return mobileNum;
				}
				//return mobileNum;
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
			return null;
	}
	public static String encodeHTML(String s) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 32 && c <= 46 || c >= 58 && c <= 64 || c >= 91 && c <= 96 || c >= 123 && c <= 126) {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}
	public static String formatDataWithTable(String[][] bodyParagraphs, String width) {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("<table style='margin: 0px 0px 0px -42px;'>");
		if (!ObjectFunctions.isNullOrEmpty(bodyParagraphs)) {
			int size = bodyParagraphs.length;
			for (int i = 0; i < size; i++) {
				if (!StringFunctions.isNullOrEmpty(width)) {
					strBuff.append("<tr><td align='right' valign='top' width='"
							+ width + "' style='padding:0px 0px 0px 7px;'>");
				} else {
					strBuff
							.append("<tr><td align='right' valign='top' style='padding:0px 0px 0px 7px;'>");
				}
				strBuff.append("<b>" + bodyParagraphs[i][0] + "</b>");
				strBuff.append("</td>");
				strBuff.append("<td align='left' valign='top'>");
				strBuff.append(bodyParagraphs[i][1]);
				strBuff.append("</td></tr>");

			}
		}
		strBuff.append("</table>");
		return strBuff.toString();
	}

	public static String formatEmailBody(String[] bodyParagraphs) {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("<p style='margin: 10px 10px 10px 10px;font-family:Trebuchet Ms,serif;font-size:14px;'>");
		if (!ObjectFunctions.isNullOrEmpty(bodyParagraphs)) {
			int size = bodyParagraphs.length;
			for (int i = 0; i < size; i++) {

				if (!StringFunctions.isNullOrEmpty(bodyParagraphs[i])) {
					if ("~".equalsIgnoreCase(bodyParagraphs[i].substring(0, 1))) {
						strBuff.append("<p style='margin: 0px 0px 0px -9px;font-size:13px;'>");
						strBuff.append(bodyParagraphs[i].substring(1));
						strBuff.append("</p>");
					} else {
						strBuff.append("<p style='margin: 10px 0px 0px 0px;'>");
						strBuff.append(bodyParagraphs[i]);
						strBuff.append("</p>");
					}
				}
			}
		}
		strBuff.append("<br />");// +getFooter());
		return strBuff.toString();
	}
	public static void zipFiles(File directory,ZipOutputStream zipOutStream){
		 URI base = null;
		 Deque<File> queue = null;
		 
		 try{
			 queue = new LinkedList<File>();
		     queue.push(directory);
		     base = directory.toURI();
			 while (!queue.isEmpty()) {
				 directory = queue.pop();
				 for (File kid : directory.listFiles()) {
					 if(kid.isDirectory()){
						 for(File kid1 : kid.listFiles())
							 addFiles(base,zipOutStream,kid1);
					 }else
						 addFiles(base,zipOutStream,kid);
				 }
	        }
			zipOutStream.finish();
			zipOutStream.close();
		 }catch (Exception e) {
			 e.printStackTrace();
		}finally{
			zipOutStream = null;
			directory = null;
			queue = null;
			base = null;
		}
	 }
	
	public static void addFiles(URI base,ZipOutputStream zipOutStream,File kid){
		try{
			 InputStream in = null;
	         in = new FileInputStream(kid);
	         try {
	        	 String name = base.relativize(kid.toURI()).getPath();
	        	 zipOutStream.putNextEntry(new ZipEntry(name));
	           	byte[] buffer = new byte[1024];
	             while (true) {
	                int readCount = in.read(buffer);
	                if (readCount < 0) {
	                   break;
	                }
	                zipOutStream.write(buffer, 0, readCount);
	             }
	          }catch(Exception e){
	         	 e.printStackTrace();
	          } finally {
	               in.close();
	               zipOutStream.closeEntry();
	               in= null;
	          }
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static String convertListToCommaDelimitedString(List coll){
		if(ObjectFunctions.isNotNullOrEmpty(coll))
			return org.springframework.util.StringUtils.collectionToCommaDelimitedString(coll);
		return null;
	}
	
	public static String  prepareUserName(String precode, String firstName, String initial)
	{
		if(StringFunctions.isNotNullOrEmpty(initial))
		{
			return StringFunctions.stripSymbols(precode + firstName + StringFunctions.stripSymbols(initial,".#$@,'`?& ")).toLowerCase();
		}
		return StringFunctions.stripSymbols(precode + firstName).toLowerCase();
	}

	public static String getMobileNumberLengthChecking(String mobileNum) {
		try {
			String fourDigits = null;
			String afterDigits = null;
			if(StringFunctions.isNotNullOrEmpty(mobileNum)){
				if (mobileNum.length() == 10 && !"0000000000".equalsIgnoreCase(mobileNum)) {
					fourDigits = mobileNum.substring(0, 4);
					afterDigits = mobileNum.substring(4, 10);
					if (!"+91-".equalsIgnoreCase(fourDigits) && !"000000".equalsIgnoreCase(afterDigits)) {
						if (StringFunctions.isNotNullOrEmpty(StringFunctions.getFormattedMobileNumber(mobileNum)))
							return StringFunctions.getFormattedMobileNumber(mobileNum);
						else
							return null;
					}
				} else if (mobileNum.length() == 14 && !"00000000000000".equalsIgnoreCase(mobileNum)) {
					fourDigits = mobileNum.substring(0, 4);
					afterDigits = mobileNum.substring(4, 14);
					if ("+91-".equalsIgnoreCase(fourDigits) && !"0000000000".equalsIgnoreCase(afterDigits))
						return mobileNum;
					else
						return null;
				} else 
					return null;
			}else
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String removeDuplicateWords(String s,String wordType)
	{
		//http://stackoverflow.com/questions/6790689/remove-duplicate-values-from-a-string-in-java
		return new LinkedHashSet<String>(Arrays.asList(s.split(wordType))).toString().replaceAll("(^\\[|\\]$)", "").replace(", ", wordType);
	}
}
package com.churchgroup.util.string;
/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: TextEncoder.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
/ ********************************************************************/



/**
 * Supports translation of special characters into equivalent values 
 *  supported in XML and HTML.
 *
 */
public class TextEncoder 
{
	
	private final static int HIGH_INT = 128;
	

	/**
	 * Try to get a valid substitution character for
	 *   input character. If none is found, return the
	 *   character as a String.
	 *
	 * @param  char   c
	 * @return String
	 */
	private static String getValidText(char c) 
	{
		String strValue = "";
		
		int cInt = (int)c;
		
		if(cInt>HIGH_INT)
		{
			strValue = "&#"+String.valueOf(cInt)+";";
			return strValue;
		}
		
		switch (cInt)
		{
			case 32: strValue = "&#32;"; break;   //  space (" ")
			case 34: strValue = "&quot;"; break;  //  quote ("\"")
			case 35: strValue = "&#35;"; break;   //   pound sign ("#")
			case 60: strValue = "&lt;"; break;    //  less than  ("<")
			case 61: strValue = "&#61;"; break;   //    equals ("=")
			case 62: strValue = "&gt;"; break;    //  greater than (">")
			case 37: strValue = "&#37;"; break;   //  percent ("%")
			case 38: strValue = "&amp;"; break;   //   ampersand ("&")
			case 47: strValue = "&#47;"; break;   //  slash ("/")
			case 58: strValue = "&#58;"; break;   //   colon  (":")
			case 59: strValue = "&#59;"; break;   //   semicolon (";")
			case 63: strValue = "&#63;"; break;   //   question mark ("?")
			case 64: strValue = "&#64;"; break;   //   at sign ("@")
			case 91: strValue = "&#91;"; break;   //  left bracket ("[")
			case 92: strValue = "&#92;"; break;   //  backslash ("\\")
			case 93: strValue = "&#93;"; break;   //   right bracket ("]")
			case 94: strValue = "&#94;"; break;   //   caret ("^")
			case 96: strValue = "&#96;"; break;   //   back-tick ("`")
			case 123: strValue = "&#123;"; break; //  left brace ("{")
			case 124: strValue = "&#124;"; break; //  vertical bar ("|")
			case 125: strValue = "&#125;"; break; //  right brace ("}")
			case 126: strValue = "&#126;"; break; //   tilde  ("~")
			case 169: strValue = "&#169;"; break; //   copyright ("�")
			case 174: strValue = "&#174;"; break; //   trademard ("�")
			case 185: strValue = "&#185;"; break; //   superscript 1 ("u00B9" in unicode)
			case 189: strValue = "&#189;"; break; //   fraction 1/2
	
			default: strValue = String.valueOf(c);
		}
		
	 
	  	return strValue;
	}


	/**
	 * Search the input string and substitute equivalent values
	 *  for any non-alphanumeric characters.<br>
	 *
	 * @param  originalText String   the text to translate
	 * @return String    the translated text
	 */
	public static String translate(String originalText) 
	{
		StringBuffer translatedText = new StringBuffer();
	
		if (originalText != null)
		{
			int length = originalText.length();
	    
			// iterate over the chars in the string
	    	for (int i = 0; i < length; i++)
	    	{
	            char currentChar = originalText.charAt(i);
	            if (Character.isLetterOrDigit(currentChar) && 
	            	((int)currentChar)<HIGH_INT )
	         	{
		         	translatedText.append(currentChar);
	         	}
		        else
		        {
			        translatedText.append(getValidText(currentChar));
		        }
	    	}
		}
	    return translatedText.toString();
	}
	
	
}
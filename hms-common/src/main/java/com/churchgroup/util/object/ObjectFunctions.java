package com.churchgroup.util.object;
/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: ObjectFunctions.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
/ ********************************************************************/

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.TextEncoder;


/**
 * @author
 */
public class ObjectFunctions {
	
	private static final Log log = LogFactory.getLog(ObjectFunctions.class);

	//public static String version = "Nov14Excp2003";
	
	private static final String AMPERSAND = "&";
	
	private static final char EQUALS = '=';

/**
 * Creates a query string (such as for an HTTP request) from a Map.
 * There is no guaranteed ordering of the parameters.  Keys or values that
 * are null or empty are not included in the resultant string.
 * @param parms The map containing the key value pairs
 * @param addInitialSeparator boolean true if the '?' should be added,
 * or false if it should be omitted
 * @return String The resultant query string
 */
 
public static String buildQueryString( Map parms, boolean addInitialSeparator )
{
	StringBuffer sb = new StringBuffer( 1000 );

	if (!ObjectFunctions.isNullOrEmpty( parms)) {

		if (addInitialSeparator) {
			sb.append( '?' );
		}

		Iterator iter = parms.entrySet().iterator();

		while (iter.hasNext()) {

			//
			// add the key and value
			//
			
			Map.Entry me = (Map.Entry)iter.next();
			if (!StringFunctions.isNullOrEmptyNoTrim( (String)me.getKey() ) &&
				!StringFunctions.isNullOrEmptyNoTrim( (String)me.getValue() )) {
					
				sb.append( me.getKey() ).
				   append( '=' ).
				   append( me.getValue() );


				//
				// if more keys, then add the separator
				//
				
				if (iter.hasNext()) {

					sb.append( '&' );

				}
			}
		}
	}

	//
	// if the last character is a '&', then remove it
	// subtract 1 from length to get position of the last character.  Then
	// make sure that we are still referencing a positive index
	//

	int len = sb.length() - 1;
		
	if (len >= 0 && sb.charAt( len ) == '&' ) {

		sb.deleteCharAt( len );

	}

	//
	// if the last character is a '?', then remove it.  This could
	// happen if the map is not empty, but contains empty keys or
	// values
	//
	
	len = sb.length() - 1;
		
	if (len >= 0 && sb.charAt( len ) == '?' ) {

		sb.deleteCharAt( len );

	}
		
	return sb.toString();
}


/*
*	Given a fully-qualified class name, return an instance of
*    its class.
*
*   @param  String   a class name
*/
public static Class getClassForClassName(String aClassName ) throws ClassNotFoundException 
{
	Class result = null;
	try
	{
		result = Class.forName(aClassName,true,Thread.currentThread().getContextClassLoader());
	}
	catch (ClassNotFoundException e)
	{
		// if the context class loader did not work, try
		//  the old-fashioned way.  Added this back in response
		//  to errors encountered by PAS in their custom session
		//  cleanup routines
		result = Class.forName(aClassName);
	}
	return result;
}

/*
*	Given a fully-qualified class name, return an instance of
*    an object of that class.
*
*   @param  String   a class name identifying a class with a public no-arg constructor
*/
public static Object getObjectForClassName(String aClassName ) throws Exception
{

	Class tempClass = getClassForClassName(aClassName);
	Object tempObject = tempClass.newInstance();

	return tempObject;
}
/**
 *   Convenience method to help determine whether a object is 
 *    null or empty.  <br>
 *   Object is used as the input type because it is a broad
 *    interface encompassing common classes and interfaces <br>
 *
 *   @param   java.lang.Object
 *   @return  boolean
 */
public static final boolean isNullOrEmpty(Object obj) {

    if (obj == null || "".equals(obj))
    {
        return true;
    }
    return false;
}

/**
 *    Convenience method to help determine whether a collection is 
 *    null or empty.  <br>
 *    List is used as the input type because it is a broad
 *    interface encompassing common classes and interfaces such as 
 *    Vector, ArrayList, and List.<br>
 *
 *   @param   java.util.List
 *   @return  boolean
 */
public static final boolean isNullOrEmpty(List aList) {

	if (aList == null || aList.isEmpty())
	{
		return true;
	}
	return false;
}
/**
 *    Convenience method to help determine whether a collection is 
 *    null or empty.  <br>
 *    List is used as the input type because it is a broad
 *    interface encompassing common classes and interfaces such as 
 *    Vector, ArrayList, and List.<br>
 *
 *   @param   java.util.List
 *   @return  boolean
 */
public static final boolean isNotNullOrEmpty(List aList) {

    if (aList == null || aList.isEmpty())
    {
        return false;
    }
    return true;
}

/**
 *   Convenience method to help determine whether a collection is 
 *    null or empty.  <br>
 *   Collection is used as the input type because it is a broad
 *    interface encompassing common classes and interfaces such as 
 *    Vector, ArrayList, and List.<br>
 *
 *   @param   java.util.Collection
 *   @return  boolean
 */
public static final boolean isNullOrEmpty(Collection aColl) {

    if (aColl == null || aColl.isEmpty())
    {
        return true;
    }
    return false;
}

/**
 *   Convenience method to help determine whether a collection is 
 *    null or empty.  <br>
 *   Collection is used as the input type because it is a broad
 *    interface encompassing common classes and interfaces such as 
 *    Vector, ArrayList, and List.<br>
 *
 *   @param   java.util.Collection
 *   @return  boolean
 */
public static final boolean isNotNullOrEmpty(Set aSet) {

    if (aSet == null || aSet.isEmpty())
    {
        return false;
    }
    return true;
}

public static final boolean isNullOrEmpty(Set aSet) {

    if (aSet == null || aSet.isEmpty())
    {
        return true;
    }
    return false;
}
/**
 *   Convenience method to help determine whether an Enumeration is 
 *    null or empty.  <br>
 *   Enumeration has its own method because it does not fall under 
 *    a broad interface like Map or Collection.
 *
 *   @param   java.util.Enumeration
 *   @return  boolean
 */
public static final boolean isNullOrEmpty(Enumeration aEnum) {

	if (aEnum == null || !aEnum.hasMoreElements())
	{
		return true;
	}
	return false;
}


/**
 *   Convenience method to help determine whether an Iterator is 
 *    null or empty.  <br>
 *
 *   @param   java.util.Iterator
 *   @return  boolean
 */
public static final boolean isNullOrEmpty(Iterator aIter) {

	if (aIter == null || !aIter.hasNext())
	{
		return true;
	}
	return false;
}


/**
 *   Convenience method to help determine whether a map is 
 *    null or empty.  <br>
 *   Map is used as the input type because it is a broad
 *    interface implemented by classes such as HashMap and Hashtable.<br>
 *
 *   @param   java.util.Map
 *   @return  boolean
 */
public static final boolean isNullOrEmpty(Map aMap) {

	if (aMap == null || aMap.isEmpty())
	{
		return true;
	}
	return false;
}


/**
 *   Use the StringFunctions class to determine if the input
 *    String is null or an empty string.  <br>
 *   (This could have been implemented here and deprecated 
 *    in StringFunctions, but because there is already extensive use
 *    of StringFunctions, we just call it from here for backward-compatibility).<br>
 *
 *   @param   aString  
 *   @return  boolean
 */
public static final boolean isNullOrEmpty(String aString) {

	return StringFunctions.isNullOrEmpty(aString);
}


/**
 * Adapter method to create a List object from an Enumeration. <br>
 * Useful in cases where you can only get an Enumeration back from
 *   something but you need List-handling capabilities.
 *
 * @param enum An Enumeration to be converted to a list
 * @return List An ArrayList containing the elements from an Enumeration
 */
public static List toList( Enumeration eNum )
{
	if (eNum == null)
	{
		return null;
	}
	List result = new ArrayList( 10 );
	
	while (eNum.hasMoreElements()) 
	{
		result.add( eNum.nextElement() );
	}
	return result;
}


/**
 * Adapter method to create a List object from an Iterator. <br>
 * Useful in cases where you can only get an Iterator back from
 *   something but you need List-handling capabilities.
 *
 * @param iter  An Iterator to be converted to a list
 * @return List An ArrayList containing the elements from an Iterator
 *           returns null if null was passed as input
 */

public static List toList(Iterator iter)
{
	if (iter == null)
	{
		return null;
	}
	List result = new ArrayList( 10 );
	
	while (iter.hasNext()) 
	{
		result.add( iter.next() );
	}

	return result;
}


/**
 * Create an XML representation of a given Collection.<p>
 * This method will iterate through the collection, constructing an
 * XML String with each value identified by the key.
 * <p>
 * This method assumes encoding of the data is not required.
 * <p>
 * If the value is a Map or itself a Collection, toXml is called
 * recursively to process the value.
 * Example, if <code>myList</code> contains:
 * <PRE>
 *   { "firstvalue", {a=1,b=2,c=3}, item3=["h","i","j"], "lastvalue" }
 * </PRE>
 *  calling <code>toXml(myList, "item")</code> would yield: <br>
 * <PRE>
 *  <item>firstvalue</item>
 *  <item>
 *    <a>1</a><b>2</b><c>3</c>
 *  </item>
 *  <item>
 *    <item>h</item>
 *    <item>i</item>
 *    <item>j</item>
 *  </item>
 *  <item>lastvalue</item>
 * </PRE><br>
 *
 * @param  Collection
 * @param  key The element tag for each item of the collection
 * @return String  an XML representation of the data in the Map
 */
 
public static String toXml(Collection aCollection, String key)
{
	return toXml(aCollection,key,false);
}


/**
 * Create an XML representation of a given Collection.<p>
 * This method will iterate through the collection, constructing an
 * XML String with each value identified by the key.
 * <p>
 * This method will check the boolean parameter, <B>encode</B> and use
 *  it to determine whether to translate special characters into allowable
 *  XML values.
 * <p>
 * If the value is a Map or itself a Collection, toXml is called
 * recursively to process the value.
 * Example, if <code>myList</code> contains:
 * <PRE>
 *   { "firstvalue", {a=1,b=2,c=3}, item3=["h","i","j"], "lastvalue" }
 * </PRE>
 *  calling <code>toXml(myList, "item")</code> would yield: <br>
 * <PRE>
 *  <item>firstvalue</item>
 *  <item>
 *    <a>1</a><b>2</b><c>3</c>
 *  </item>
 *  <item>
 *    <item>h</item>
 *    <item>i</item>
 *    <item>j</item>
 *  </item>
 *  <item>lastvalue</item>
 * </PRE><br>
 *
 * @param  Collection
 * @param  key The element tag for each item of the collection
 * @param  boolean  flag to set when the data is to be encoded
 *    (for example, when the data contains HTML tags that prevent it from 
 *     being used as-is in valid XML)
 * @return String  an XML representation of the data in the Map
 */
 
public static String toXml(Collection aCollection, String key, boolean encode)
{

    final String NL = StringFunctions.getNewLine();

    if (aCollection == null || ObjectFunctions.isNullOrEmpty(aCollection)) {

        return "";
    }

    StringBuffer sb = new StringBuffer(500);
    Iterator collIter = ((Collection) aCollection).iterator();
    
    while (collIter.hasNext()) {

        sb.append("<").append(key).append(">");

	    Object obj = collIter.next();
	    if (obj instanceof Map) 
	    {
		    sb.append(toXml((Map)obj,encode));
	    } 
	    else if (obj instanceof Collection) 
	    {
		    sb.append(toXml((Collection)obj, "item", encode));
	    }
	    else 
	    {
		    if (encode)
		    {
		    	sb.append(TextEncoder.translate(obj.toString()));
		    }
		    else
		    {
		    	sb.append(obj.toString());
		    }
	    }
		    
        sb.append("</").append(key).append(">").append(NL);

    }
    
    return sb.toString();
}


/**
 * Create an XML representation of a given Map.<br>
 * <p>
 * This method assumes encoding of the data is not required.
 * <p>
 * This method will iterate through the map, constructing an
 *  XML String with each key in the map as a tag and each value
 *  as the content of that tag.  If the value is itself a map,
 *  this method will make a recursive call to itself to include
 *  that substructure in the XML.<br>
 * Example, if <code>myMap</code> contains:
 * <PRE>
 *   {firstvalue=27,item1={a=1,b=2,c=3},item2={x=8,y=9,z=0},item3=["h","i","j"]}
 * </PRE>
 *  calling <code>toXml(myMap)</code> would yield: <br>
 * <PRE>
 *  <firstvalue>27</firstvalue>
 *  <item1>
 *    <a>1</a><b>2</b><c>3</c>
 *  </item>
 *  <item2>
 *    <x>8</x><y>9</y><z>0</z>
 *  </item2>
 *  <item3>h</item3>
 *  <item3>i</item3>
 *  <item3>j</item3>
 * </PRE><br>
 *
 * @param  Map
 * @return String  an XML representation of the data in the Map
 */
public static String toXml(Map aMap) {

	return toXml(aMap,false);
}


/**
 * Create an XML representation of a given Map with a specific name 
 *   to use as an outer tag.<br>
 * <p>
 * This method assumes encoding of the data is not required.
 * <p>
 * This method is useful when there are repeating sets of data 
 *  in the map and you want to construct the XML with the same 
 *  repeating outer tag, rather than the key from the Map.<br>
 * For example, if <code>myMap</code> contains:
 * <PRE>
 *   {firstvalue=27,item1={a=1,b=2,c=3},item2={x=8,y=9,z=0},item3=["h","i","j"]}
 * </PRE>
 *  calling <code>toXml(myMap,"item")</code> would yield: <br>
 * <PRE>
 *  <item name="firstvalue" value="27"/>
 *  <item>
 *    <a>1</a><b>2</b><c>3</c>
 *  </item>
 *  <item>
 *    <x>8</x><y>9</y><z>0</z>
 *  </item>
 *  <item>
 *    <listitem>h</listitem>
 *    <listitem>i</listitem>
 *    <listitem>j</listitem>
 *  </item>
 * </PRE><br>
 *
 * @param  Map
 * @param  String  the name to use as the outer tag of the XML
 * @return String  an XML representation of the data in the Map
 */
public static String toXml(Map aMap, String id)
{
	return toXml(aMap,id,false);
}


/**
 * Create an XML representation of a given Map with a specific name 
 *   to use as an outer tag.<br>
 * This method will check the boolean parameter, <B>encode</B> and use
 *  it to determine whether to translate special characters into allowable
 *  XML values.
 * <p>
 * This method is useful when there are repeating sets of data 
 *  in the map and you want to construct the XML with the same 
 *  repeating outer tag, rather than the key from the Map.<br>
 * For example, if <code>myMap</code> contains:
 * <PRE>
 *   {firstvalue=27,item1={a=1,b=2,c=3},item2={x=8,y=9,z=0},item3=["h","i","j"]}
 * </PRE>
 *  calling <code>toXml(myMap,"item")</code> would yield: <br>
 * <PRE>
 *  <item name="firstvalue" value="27"/>
 *  <item>
 *    <a>1</a><b>2</b><c>3</c>
 *  </item>
 *  <item>
 *    <x>8</x><y>9</y><z>0</z>
 *  </item>
 *  <item>
 *    <listitem>h</listitem>
 *    <listitem>i</listitem>
 *    <listitem>j</listitem>
 *  </item>
 * </PRE><br>
 *
 * @param  Map
 * @param  String  the name to use as the outer tag of the XML
 * @param  boolean  flag to set when the data is to be encoded
 *    (for example, when the data contains HTML tags that prevent it from 
 *     being used as-is in valid XML)
 * @return String  an XML representation of the data in the Map
 */
public static String toXml(Map aMap, String id, boolean encode) {

	final String NL = StringFunctions.getNewLine();

	if (aMap == null)
	{
		return "";
	}
	
	StringBuffer sb = new StringBuffer(500);
	Iterator iter = aMap.entrySet().iterator();
	while (iter.hasNext())
	{
		Map.Entry entry = (Map.Entry)iter.next();
		Object value = entry.getValue();
		if (value instanceof Map)
		{
			sb.append("<").append(id).append(">").append(NL);
			sb.append(toXml((Map)value,encode));
			sb.append("</").append(id).append(">").append(NL);
		}
		else if (value instanceof Collection)
		{
			sb.append(toXml((Collection)value,id,encode));			
		}
		else if (value == null)
		{
			sb.append(toXml(id,null,encode)).append(NL);
		}
		else
		{
			sb.append("<").append(id).append(" name=\"").append(entry.getKey()).
			   append("\" value=\"");
			if (encode)
			{
				sb.append(TextEncoder.translate(value.toString()));
			}
			else
			{
				sb.append(value.toString());
			}
			sb.append("\"/>").append(NL);
		}
	}
	return sb.toString();
}


/**
 * Create an XML representation of a given Map.<br>
 * This method will check the boolean parameter, <B>encode</B> and use
 *  it to determine whether to translate special characters into allowable
 *  XML values.
 * <p>
 * This method will iterate through the map, constructing an
 *  XML String with each key in the map as a tag and each value
 *  as the content of that tag.  If the value is itself a map,
 *  this method will make a recursive call to itself to include
 *  that substructure in the XML.<br><br>
 * <p>
 * Example, if <code>myMap</code> contains:
 * <PRE>
 *   {firstvalue=27,item1={a=1,b=2,c=3},item2={x=8,y=9,z=0},item3=["h","i","j"]}
 * </PRE>
 *  calling <code>toXml(myMap)</code> would yield: <br>
 * <PRE>
 *  <firstvalue>27</firstvalue>
 *  <item1>
 *    <a>1</a><b>2</b><c>3</c>
 *  </item>
 *  <item2>
 *    <x>8</x><y>9</y><z>0</z>
 *  </item2>
 *  <item3>h</item3>
 *  <item3>i</item3>
 *  <item3>j</item3>
 * </PRE><br>
 *
 * @param  Map
 * @param  boolean  flag to set when the data is to be encoded
 *    (for example, when the data contains HTML tags that prevent it from 
 *     being used as-is in valid XML)
 * @return String  an XML representation of the data in the Map
 */
public static String toXml(Map aMap, boolean encode) {

	final String NL = StringFunctions.getNewLine();
	
	if (aMap == null)
	{
		return "";
	}
	
	StringBuffer sb = new StringBuffer(500);
	Iterator iter = aMap.entrySet().iterator();
	while (iter.hasNext())
	{
		Map.Entry entry = (Map.Entry)iter.next();
		Object key = entry.getKey();
		String keyStr = null;
		if (key == null)
		{
			keyStr = "null";
		}
		else
		{
			keyStr = key.toString();
		}
		
		Object value = entry.getValue();
		if (value instanceof Map)
		{
			sb.append("<").append(key).append(">");
			sb.append(NL).append(toXml((Map)value,encode));
			sb.append("</").append(key).append(">").append(NL);
		}
		else if (value instanceof Collection)
		{
			sb.append(toXml((Collection)value,keyStr,encode));			
		}
		else if (value == null)
		{
			sb.append(toXml(keyStr,null,encode)).append(NL);
		}
		else  
		{
			sb.append(toXml(keyStr,value.toString(),encode)).append(NL);
		}
	}
	return sb.toString();
}


/**
 * Create an XML string containing the begin tag, content, and end tag.<br>
 *
 * If <i>tag</i> is null, this method will return null. <br>
 * If only <i>content is null, an empty tag is returned.<br>
 *
 * @param tag     The tag wrapping the content
 * @param content The value of the tag
 * @return String The resulting XML string
 */

public static String toXml(String tag,String content)
{
	return toXml(tag,content,false);
}


/**
 * Create an XML string containing the begin tag, content, and end tag.<br>
 *
 * If <i>tag</i> is null, this method will return empty string(""). Not returning
 *   null because if it gets put into the XML stream the caller is building, the XML 
 *   be invalid.<br>
 * If only <i>content is null, an empty tag is returned.<br>
 *
 * This method will check the boolean parameter, <B>encode</B> and use
 *  it to determine whether to translate special characters into allowable
 *  XML values.<br>
 *
 * @param tag     The tag wrapping the content
 * @param content The value of the tag
 * @param  boolean  flag to set when the data is to be encoded
 *    (for example, when the data contains HTML tags that prevent it from 
 *     being used as-is in valid XML)
 * @return String The resulting XML string
 */

public static String toXml(String tag,String content,boolean encode)
{
	if (tag == null)
	{
		return "";
	}
	content = StringFunctions.checkString(content,"");	

	StringBuffer sb = new StringBuffer(500);
	sb.append("<").append(tag).append(">");
	if (encode)
	{
		sb.append(TextEncoder.translate(content.toString()));
	}
	else
	{
		sb.append(content);
	}
	sb.append("</").append(tag).append(">");

	return sb.toString();
}

/**
 * This method takes a generic object and does it's best to convert the object to a String.
 * For collections it takes the returned collection and adds a line for each item in the collections
 * It populates this line with the toString of each returned object in the Collection.
 * 
 * @param inputObject  The object to be converted to a string
 * @param defaultString The string to use if inputObject is null
 */
public static String objectToString(Object inputObject, String defaultString)
{
	String returnValue = defaultString;

    if (inputObject == null)
    {
	    return defaultString;
    }
    
    Class returnType = inputObject.getClass();
	
    // See what the return type is, and process the method accordingly
	if( String.class.isAssignableFrom(returnType) )
	{
		returnValue = (String)inputObject;
    }
    else if (int.class.isAssignableFrom(returnType) ||
		     Integer.class.isAssignableFrom(returnType))
	{
        Integer intValue = (Integer)inputObject;
		if (intValue != null)
		{
			int anInt = intValue.intValue();
			returnValue = String.valueOf(anInt);
		}
	}
    else if (boolean.class.isAssignableFrom(returnType) || 
	        Boolean.class.isAssignableFrom(returnType))
    {
        Boolean boolValue = (Boolean) inputObject;
        if (boolValue != null)
        {
			boolean bool = boolValue.booleanValue();
            returnValue = String.valueOf(bool);
        }
    }
    else if (double.class.isAssignableFrom(returnType) ||
		     Double.class.isAssignableFrom(returnType))
	{
        Double doubleValue = (Double)inputObject;
		if (doubleValue != null)
		{
			double anDouble = doubleValue.doubleValue();
			returnValue = String.valueOf(anDouble);
		}
	}
    else if (float.class.isAssignableFrom(returnType) ||
		     Float.class.isAssignableFrom(returnType))
	{
        Float floatValue = (Float)inputObject;
		if (floatValue != null)
		{
			float anFloat = floatValue.floatValue();
			returnValue = String.valueOf(anFloat);
		}
	}
    else if (char.class.isAssignableFrom(returnType) ||
		     Character.class.isAssignableFrom(returnType))
	{
        Character charValue = (Character)inputObject;
		if (charValue != null)
		{
			char anChar = charValue.charValue();
			returnValue = String.valueOf(anChar);
		}
	}
    else if (Collection.class.isAssignableFrom(returnType))
    {
		Collection c = (Collection) inputObject;
        Iterator iter = c.iterator();
        StringBuffer sb = new StringBuffer();
        while (iter.hasNext())
        {
            sb.append(iter.next());
            sb.append(StringFunctions.getNewLine());
        }
        returnValue = sb.toString();
   	}
   	
   	return returnValue;
}



	/**
	 * This method performs a deep copy (aka deep clone) of an object
	 * and all of its references.  It requires that the objects implement
	 * Serializable because the implementation uses serialization.
	 * Transients in the original object will not be set in the copy.
	 * 
	 * If something goes wrong, an Exception is thrown. In this case,
	 * nothing is logged, and it is up to the caller to determine how
	 * to proceed. This allows flexibility of better logging, or no logging
	 * at all. Also, MessageLogger is not available from this component
	 * (inf_utils), so any logging done here would be non-standard anyway.
	 * 
	 * @param oldObj
	 * @return Object
	 * @throws Exception
	 */
	public static Object deepCopy(Object oldObj) throws Exception
	{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			oos = new ObjectOutputStream(bos);
			
			// serialize and pass the object
			oos.writeObject(oldObj);
			oos.flush();
			ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bin);
			
			// return the new object
			return ois.readObject();
		}
		catch (Exception e)
		{
			// re-throw the same exception
			throw e;
		}
		finally
		{
			oos.close();
			ois.close();
		}
	}

	/**
	 * This method takes an ampersand (&) delimited string of key pairs
	 * in the form:<br>
	 * Key=Value&Key=Value&...&Key=Value
	 *<br>
	 * and inserts the key/value pairs into a Map.
	 * 
	 * See stringToMap(String, String) for notes on expected return values.  
	 * 
	 * @param inString String of key pairs to be inserted into Map
	 * @return String Map representing <i>inString</i>
	 */
	public static Map stringToMap(String inString)
	{
		//AMPERSAND is the delimiter by default
		return stringToMap(inString, AMPERSAND);
	}
	/**
	 * This method takes an delimited string of key pairs and a delimiter
	 * in the form: <br>Example: <br>
	 * Key=Value%Key=Value%...%Key=Value
	 *<br>where "%" would be the delimiter, <br>
	 * and inserts the key/value pairs into a Map.
	 * 
	 * stringToMap Return Value Notes:	
	 *	-If a delimiter is the first encountered character in the String, it is ignored,
	 *	 and the next part of the String is evaluated.
	 *	-If two delimiters are encoutnered next to each other, nothing will be entered into the Map.  
	 * 	-In a substring (the String between two delimiters), if no �=� is found, the substring
	 * 	 will be inserted into the Map as a key with an empty string value.
	 *	-In a substring, if �=� is the first character found, the substring will be inserted 
	 *	 into the Map as a value for the empty string key.  	
	 * 
	 * @param inString String of key pairs to be inserted into Map
	 * @param delimiter String of delimiters, usually single character
	 * @return String Map representing <i>inString</i>
	 */
	public static Map stringToMap(String inString, String delimiter)
	{
		Map result = new HashMap();
		int index;
		if (!StringFunctions.isNullOrEmpty(inString))
		{
			//create array of & delimited elements
			String[] elements = StringFunctions.createStringArray(inString, delimiter);
			for(int i=0; i<elements.length; i++)
			{
				//parse array and add keys and values to Map
				index = elements[i].indexOf(EQUALS);
				//if no '=' is found in String, set what is there to key with "" value
				if (index > -1)
				{
					result.put(elements[i].substring(0,index), elements[i].substring(index+1,elements[i].length()));
				}
				else
				{
					result.put(elements[i], "");
				}
			}
		}
		return result;	
	}
	
	 public static void fileDelete(File file)	throws IOException{
	     	if(file.isDirectory()){
	   		//directory is empty, then delete it
	   		if(file.list().length==0){
	   		   file.delete();
	   		   log.debug("Directory is deleted : " + file.getAbsolutePath());

	   		}else{
	   		   //list all the directory contents
	       	   String files[] = file.list();
	       	   for (String temp : files) {
	       	      //construct the file structure
	       	      File fileDelete = new File(file, temp);
	       	      //recursive delete
	       	      fileDelete(fileDelete);
	       	   }
	       	   //check the directory again, if empty then delete it
	       	   if(file.list().length==0){
	          	     file.delete();
	       	         log.debug("Directory is deleted : "+ file.getAbsolutePath());
	       	     }
	   		   }
			  }else{
			  	//if file, then delete it
			  		file.delete();
			   		log.debug("File is deleted : " + file.getAbsolutePath());
			   	}
	   }
}

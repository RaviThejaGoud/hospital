/*************************************************************************
 * Copyright (C) 1999,2000,2001 
 * United Services Automobile Association 
 * All Rights Reserved 
 *
 * File: Pair.java
 *************************************************************************
 *  Ver   Date      Name           Description
 *  ---   -------   ------------   ------------------
 * Jabba
 * 10.2   7/10/01   M. Westerman   added to util package
 *************************************************************************/
package com.churchgroup.util.object;

/********************************************************
*  This class provides a means of treating two separate pieces of
*   data as a unit.  Useful when you need a composite key as your
*   key in something like a Hashtable, rather than nesting the
*   tables.
*
*  @author  M.Westerman  (really not author, just owner)
*********************************************************/
public class Pair implements java.io.Serializable
{
	/**
	*  the array that holds the two objects in the pairing
	*/
	private Object[] pair = new Object[2];

	/**
	*  two-arg constructor
	*/
	public Pair(Object o1, Object o2)
    {
        pair[0] = o1;
        pair[1] = o2;
    }

	/**
	*  return the first object of the pair
	*/
    public Object first()   { 
	    return pair[0];  
	}

	/**
	*  return the second object of the pair
	*/
    public Object second()  { 
	    return pair[1]; 
	}

	/**
	*  return string representation of the pair
	*/
    public String toString()
    {
        return pair[0].toString() + "+" + pair[1].toString();
    }

    /**
    *  Need to override the equals method in order to use
    *  Pair as a key in Hashtable
    */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Pair)) 
        {
	        return false;
        }
        return equals((Pair)obj);
    }

    /**
    *  Need to override the equals method in order to use
    *  Pair as a key in Hashtable
    */
    public boolean equals(Pair p) 
    { 
        return pair[0].equals(p.pair[0]) && pair[1].equals(p.pair[1]);
    }
    
    /**
    *  Construct hashcode from the String representation of this object
    */
    public int hashCode()
    {
        return toString().hashCode();
    }

}

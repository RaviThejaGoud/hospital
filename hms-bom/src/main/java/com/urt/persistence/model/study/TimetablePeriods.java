package com.urt.persistence.model.study;
/********************************************************************
 * Copyright (C) 2005-06

 * IFS
 * All Rights Reserved 
 *
 * File: Person.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
/ ********************************************************************/
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "timetablePeriods")
public class TimetablePeriods extends PersistentObject {
	    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	/**
	 * Brief Description:  A formal appellation attached to the name of a person or family by virtue of office, rank, hereditary privilege, noble birth, or attainment or used as a mark of respect.
	 * Pre-fill: True
	 * Pre-fill Source: 
	 */
	
	private long custId;
	
	private String timePeriod;
	private long academicYearId;
	
	private String status;
	
	

	

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}





	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}





	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}








	/**
	 * Constructor for Student.
	 */
	public TimetablePeriods() {
		super();
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if(null == obj || this.getClass() != obj.getClass()){
			return false;
		} else {

			TimetablePeriods period = (TimetablePeriods) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder
				.append(
					custId,
					period.getCustId())
				.append(timePeriod, period.getTimePeriod());
				

			return builder.isEquals();
		}
	}

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);
		if (!StringFunctions.isNullOrEmpty(timePeriod)) {
			buffer.append(timePeriod).append(" ");			
		}
		buffer.append(custId);		
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(375130981, -1650712895)
                .append(this.custId).append(this.timePeriod).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
    	TimetablePeriods myClass = (TimetablePeriods) object;
        return 0;
    }
	
}

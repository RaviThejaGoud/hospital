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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "studentSiblings")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)

public class StudentSiblings extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	private static final int STRING_BUFFER_SIZE = 1024;
	
	private String status;
	
	private User siblingAccount;
	private StudyClass studyClass;
	private String siblingStatus;
	private long custId;
	private String academicYear;
	
	
	@Transient
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siblingAccountId") 
	public User getSiblingAccount() {
		return siblingAccount;
	}
	public void setSiblingAccount(User siblingAccount) {
		this.siblingAccount = siblingAccount;
	}
	public void setStudyClass(StudyClass studyClass) {
		this.studyClass = studyClass;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="studyClassId") 
	public StudyClass getStudyClass() {
		return studyClass;
	}
	
	public String getSiblingStatus() {
		return siblingStatus;
	}
	public void setSiblingStatus(String siblingStatus) {
		this.siblingStatus = siblingStatus;
	}
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public StudentSiblings() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Student)) {
			return false;
		} else {
			EqualsBuilder builder = new EqualsBuilder();

			return builder.isEquals();
		}
	}

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	/* Changed by seshu on 4 Apr for import student sheet end row purpose 
	  */
	@Override
	public String toString() {
		try {
			
			StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);
			buffer.append(getId()).append(" ");
			return buffer.toString();
		} catch (Exception ex) {
			return "";
		}
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
        return new HashCodeBuilder(375130981, -1650712895).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    //For Sorting students  alphabetically -- sash
    @Override
	public int compareTo(Object object) {
        StudentSiblings stud = (StudentSiblings) object;
        if(ObjectFunctions.isNullOrEmpty(stud)){
        	return 0;
        }
		return 0;
    }
   	
	
}

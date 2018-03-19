/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Scraps.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Feb 03, 2011       Narahari          Created
/ ********************************************************************/

package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.urt.persistence.model.base.PersistentObject;

/**
 * @author Narahari
 *
 */
@Entity
@Table(name = "feedback")
public class Feedback extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String fbackGrade;
     private String description;
     private String fquestionId;
     private String fbackOption;
     private long studentAccountId;
     private long parentAccountId;
     private String status;
     private long custId;
     private Date feedbackDate;


     
	public String getFbackGrade() {
		return fbackGrade;
	}

	public void setFbackGrade(String fbackGrade) {
		this.fbackGrade = fbackGrade;
	}

	public String getFquestionId() {
		return fquestionId;
	}

	public void setFquestionId(String fquestionId) {
		this.fquestionId = fquestionId;
	}

	public String getFbackOption() {
		return fbackOption;
	}

	public void setFbackOption(String fbackOption) {
		this.fbackOption = fbackOption;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}


	public String getStatus() {
		return status;
	}

	
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getStudentAccountId() {
		return studentAccountId;
	}

	public void setStudentAccountId(long studentAccountId) {
		this.studentAccountId = studentAccountId;
	}

	public long getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(long parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    @Column(name = "description", nullable = true, length = 1024)
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Feedback() {
		super();
	}


	public Feedback( String status) {
		super();
		this.status = status;
		
	}


	/**
     * @see java.lang.Comparable#compareTo(Object)
     */
	public int compareTo(Object object) {
		Feedback target = (Feedback) object;
    	long timeDifference = 0;
    	if (target.getCreatedDate() != null && this.getCreatedDate() != null) {
    	timeDifference = this.getCreatedDate().getTime()
    	- target.getCreatedDate().getTime();
    	}
    	int difference;
    	if (timeDifference == 0) {
    	difference = 0;
    	} else if (timeDifference > 0) {
    	difference = -1;
    	} else {
    	difference = 1;
    	}
    	return difference;
    	}
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof UserImage)) {
            return false;
        }
        Feedback rhs = (Feedback) object;
        return new EqualsBuilder().append(this.status, rhs.status)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1923559909, -664973933).toHashCode();
    }


	@Override
	public String toString() {
		return "";
	}
  
}
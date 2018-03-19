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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author Narahari
 *
 */
@Entity
@Table(name = "feedbackGrades")
public class FeedbackGrades extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String title;
     private String description;
     private String status;
     private Date fgradeDate;
     private long custId;
     
	

	public Date getFgradeDate() {
		return fgradeDate;
	}

	public void setFgradeDate(Date fgradeDate) {
		this.fgradeDate = fgradeDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Column(name = "title", nullable = true, length = 256)
     public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "status", nullable = true, length = 10)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
     
    public FeedbackGrades() {
		super();
	}


	public FeedbackGrades(String title, String status) {
		super();
		this.title = title;
		this.status = status;
	}
	@Override
	public int compareTo(Object object) {
		ReplyScrapMessage target = (ReplyScrapMessage) object;
    	long timeDifference = 0;
    	if (target.getCreatedDate() != null && this.getCreatedDate() != null) {
    	timeDifference =  target.getCreatedDate().getTime() - this.getCreatedDate().getTime();
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
    @Override
	public boolean equals(Object object) {
        if (!(object instanceof UserImage)) {
            return false;
        }
        FeedbackGrades rhs = (FeedbackGrades) object;
        return new EqualsBuilder().append(this.status, rhs.status).append(
                this.description, rhs.description).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
        return new HashCodeBuilder(-1923559909, -664973933).append(
                this.title).append(this.title).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                .append("title",
                        this.title).append("description", this.description)
                .toString();
    }
    
    @Transient
    public String getScrapMessageDateStr() {
        return DateFormatter.formatDate(DateFormatter.DATE_AT_TIME_PATTERN,this.getFgradeDate());

    }
}
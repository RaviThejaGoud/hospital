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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author Narahari
 *
 */
@Entity
@Table(name = "feedbackOptions")
public class FeedbackOptions extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String title;
     private String description;
     private String status;
     private Date foptionDate;
     private long custId;
     protected Set<FeedbackGrades> feedbackGrades;
     
     
     

     @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
   	@JoinColumn(name="fOptionsId") 
	public Set<FeedbackGrades> getFeedbackGrades() {
		return this.feedbackGrades;
	}

	public void setFeedbackGrades(Set<FeedbackGrades> feedbackGrades) {
		this.feedbackGrades = feedbackGrades;
	}
	
 	public void addFeedbackGrades(FeedbackGrades feedbackGrades) {
		if(ObjectFunctions.isNullOrEmpty(this.getFeedbackGrades())){
			this.feedbackGrades=new HashSet<FeedbackGrades>();
		}
		this.feedbackGrades.add(feedbackGrades);
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFoptionDate() {
		return foptionDate;
	}

	public void setFoptionDate(Date foptionDate) {
		this.foptionDate = foptionDate;
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
     
    public FeedbackOptions() {
		super();
	}


	public FeedbackOptions(String title, String status) {
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
        FeedbackOptions rhs = (FeedbackOptions) object;
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
        return DateFormatter.formatDate(DateFormatter.DATE_AT_TIME_PATTERN,this.getFoptionDate());

    }
}
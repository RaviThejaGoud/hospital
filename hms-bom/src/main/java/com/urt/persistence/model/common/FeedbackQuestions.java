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
 *  3.0  Feb 10, 2011       Narahari          Created
/ ********************************************************************/

package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.churchgroup.common.constants.Constants;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author Narahari
 *
 */
@Entity
@Table(name = "feedbackQuestions")
public class FeedbackQuestions extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String title;
     private String description;
     private String activityType;
     private String status=Constants.ACTIVE_STATUS;
     private long custId;
     //protected Set<FeedbackOptions> feedbackOptions;
     protected AcademicYear academicYear;
     private String roleDescription;
     
     @OneToOne
     @JoinColumn(name="academicYearId")
     public AcademicYear getAcademicYear() {
		return academicYear;
     }
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
     }

     /*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
  	@JoinColumn(name="fQuestionsId") 
 	public Set<FeedbackOptions> getFeedbackOptions() {
 		return this.feedbackOptions;
 	}

 	public void setFeedbackOptions(Set<FeedbackOptions> feedbackOptions) {
 		this.feedbackOptions = feedbackOptions;
 	} 
 	
 	public void addFeedbackOptions(FeedbackOptions feedbackOptions) {
		if(ObjectFunctions.isNullOrEmpty(this.getFeedbackOptions())){
			this.feedbackOptions=new HashSet<FeedbackOptions>();
		}
		this.feedbackOptions.add(feedbackOptions);
	}*/
 	

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
    @Column(name = "description", nullable = true, length = 1024)
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public FeedbackQuestions() {
		super();
	}


	public FeedbackQuestions(String title, String status) {
		super();
		this.title = title;
		this.status = status;
		
	}


	/**
     * @see java.lang.Comparable#compareTo(Object)
     */
	@Override
	public int compareTo(Object object) {
		FeedbackQuestions target = (FeedbackQuestions) object;
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
    @Override
	public boolean equals(Object object) {
        if (!(object instanceof UserImage)) {
            return false;
        }
        FeedbackQuestions rhs = (FeedbackQuestions) object;
        return new EqualsBuilder().append(this.title, rhs.title).append(this.status, rhs.status)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
        return new HashCodeBuilder(-1923559909, -664973933).append(
                this.title).append(this.title).toHashCode();
    }


	@Override
	public String toString() {
		return "";
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
  
}
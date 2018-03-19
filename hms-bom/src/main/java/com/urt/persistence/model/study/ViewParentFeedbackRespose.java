package com.urt.persistence.model.study;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewGroupType.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0  July 14, 2010        Siva G           	Created
/********************************************************************/

@Entity
@Table(name = "vw_parentFeedBackResponse")
public class ViewParentFeedbackRespose extends PersistentObject{

     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long parentId;
     private long custId;
     //protected Date lastAccessDate;
     private String activityType;
     private String description;
     private long feedbackQuestionId;
     private long feedbackGradeId;
     private String title;
     //private String lastUpdatedBy;
 	 //protected Date lastUpdatedDate;
 	 //protected Date createdDate;
 	 private String gradeDescription;

 	 
 	 
 	 
	public long getParentId() {
		return parentId;
	}


	public void setParentId(long parentId) {
		this.parentId = parentId;
	}


	public String getActivityType() {
		return activityType;
	}


	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getFeedbackQuestionId() {
		return feedbackQuestionId;
	}


	public void setFeedbackQuestionId(long feedbackQuestionId) {
		this.feedbackQuestionId = feedbackQuestionId;
	}


	public long getFeedbackGradeId() {
		return feedbackGradeId;
	}


	public void setFeedbackGradeId(long feedbackGradeId) {
		this.feedbackGradeId = feedbackGradeId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGradeDescription() {
		return gradeDescription;
	}


	public void setGradeDescription(String gradeDescription) {
		this.gradeDescription = gradeDescription;
	}


	public ViewParentFeedbackRespose()
    {
        super();
    }
  
  
   	/**
	 * @return the custId
	 */
	 @Column(name = "custId", nullable = true, length = 10)
	public long getCustId() {
		return custId;
	}


	public void setCustId(long custId) {
		this.custId = custId;
	}


	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	
	 
}
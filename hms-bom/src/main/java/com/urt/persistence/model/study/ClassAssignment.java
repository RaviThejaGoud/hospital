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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Attachment;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "classAssignment")
public class ClassAssignment extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private long classSectionId;
	private long custId;
	private long subjectId;
	private String description;
	private Date assignmentDate;
	private String subjectName;
	private String status;
	protected List<Attachment> attachmentList;
	protected String isDocsUpload;
	
	@Transient
	private String completionStatus;
	 
	
	
	public String getCompletionStatus() {
		return completionStatus;
	}
	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}
	@Column(name = "isDocsUpload", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getIsDocsUpload() {
		return isDocsUpload;
	}
	public void setIsDocsUpload(String isDocsUpload) {
		this.isDocsUpload = isDocsUpload;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="classAssignmentId") 	
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
  public void addAttachmentFiles(Attachment attachment) {
		if(ObjectFunctions.isNullOrEmpty(this.getAttachmentList())){
			this.attachmentList=new ArrayList<Attachment>();
		}
		this.attachmentList.add(attachment);
	}
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	public ClassAssignment() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object o) {		
		if (this == o) 
          return true;
		else
         return false;
	}

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);	
		buffer.append(getId()).append(" ");
	 return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
    	 return  0;
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */    public int compareTo(Object object) {
    	 
        return 0;
    }
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	} 
	@Transient
    public String getAssignmentDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getAssignmentDate()); 
    }
	
	@Transient
    public String getAssignmentDateFormat()
    {
        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getAssignmentDate()); 
    }
	
    
  }

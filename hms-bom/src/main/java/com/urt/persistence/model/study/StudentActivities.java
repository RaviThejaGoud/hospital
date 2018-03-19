package com.urt.persistence.model.study;
/********************************************************************
 * Copyright (C) 20011-12

 * IFS
 * All Rights Reserved 
 *
 * File: StudentAcademicPerformance.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Dec 12, 2011          Narahari           Created
/ ********************************************************************/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "studentActivities")
public class StudentActivities extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private String activityName;
	private String activityDescription;
	private String categoryName;
	
	private long custId;
	
	protected List<StudentActivityTypes> studentActivityTypes;
	
	private AcademicYear academicYear;
	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true) 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

	

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="studentActivityId") 
	public List<StudentActivityTypes> getStudentActivityTypes() {
		return studentActivityTypes;
	}

	public void setStudentActivityTypes(
			List<StudentActivityTypes> studentActivityTypes) {
		this.studentActivityTypes = studentActivityTypes;
	}


	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public StudentActivities() {
		super();
	}
	

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) {
			 return true;
		 } else{
			 return false;
		 }
	}

	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);	
		buffer.append(getId()).append(" ");
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
    	 return  0;
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    @Override
	public int compareTo(Object object) {
    	/*StudentMarks target = (StudentMarks) object;
        if (target.getExamType() != null && this.getExamType() != null)
        {
            if(this.getExamType().equalsIgnoreCase(target.getExamType()))
                return 0;
            else 
               return this.getExamType().compareToIgnoreCase(target.getExamType());
                 
        }*/
        return 0;
    }
    public void addStudentActivityTypes(StudentActivityTypes studentActivityTypes) {
		if(ObjectFunctions.isNullOrEmpty(this.studentActivityTypes)){
			this.studentActivityTypes=new ArrayList<StudentActivityTypes>();
		}
		this.studentActivityTypes.add(studentActivityTypes);
	}
    public void copyFrom(StudentActivities obj)
    {
		setCustId(obj.getCustId());
		setActivityDescription(obj.getActivityDescription());
		setActivityName(obj.getActivityName());
		setCategoryName(obj.getCategoryName());
		setCreatedDate(new Date());
		setLastAccessDate(new Date());
    }
    // This field is used in student activities template screen.
    @Transient
	public String getActivityCategoryName(){
		if(StringFunctions.isNullOrEmpty(this.categoryName))
			return "Others";
		else
			return this.categoryName;
	}
  }

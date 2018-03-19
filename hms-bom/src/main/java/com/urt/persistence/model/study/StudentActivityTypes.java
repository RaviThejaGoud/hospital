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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "studentActivityTypes")
public class StudentActivityTypes extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private String activityTypeName;
	private String activityTypeDescription;
	private long custId;
	
	protected List<StudentAcademicPerformance> studentAcademicPerformance;
	
	protected AcademicYear academicYear;
	protected Set<StudyClass> activityTypClasses;
	private String selectedClassIds;
	private StudentActivities studentActivity;
	
	
	
	/**
	 * @return the studentActivity
	 */
	@ManyToOne
	@JoinColumn(name="studentActivityId", insertable=false, updatable=false)
	public StudentActivities getStudentActivity() {
		return studentActivity;
	}
	/**
	 * @param studentActivity the studentActivity to set
	 */
	public void setStudentActivity(StudentActivities studentActivity) {
		this.studentActivity = studentActivity;
	}
	@ManyToMany
    @JoinTable(name = "activityTypeClasses",
       joinColumns = { @JoinColumn(name = "activityTypeId") },
       inverseJoinColumns = { @JoinColumn(name = "classId") })
	public Set<StudyClass> getActivityTypClasses() {
		if(ObjectFunctions.isNullOrEmpty(this.activityTypClasses)){
			this.activityTypClasses=new HashSet<StudyClass>();
		}
		return activityTypClasses;
	}
	/**
	 * @param activityTypClasses the activityTypClasses to set
	 */
	public void setActivityTypClasses(Set<StudyClass> activityTypClasses) {
		this.activityTypClasses = activityTypClasses;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="studentActivityTypeId") 
	public List<StudentAcademicPerformance> getStudentAcademicPerformance() {
		return this.studentAcademicPerformance;
	}
	public void setStudentAcademicPerformance(
			List<StudentAcademicPerformance> studentAcademicPerformance) {
		this.studentAcademicPerformance = studentAcademicPerformance;
	}


	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public StudentActivityTypes() {
		super();
	}
	
	public String getActivityTypeName() {
		return activityTypeName;
	}

	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}

	public String getActivityTypeDescription() {
		return activityTypeDescription;
	}

	public void setActivityTypeDescription(String activityTypeDescription) {
		this.activityTypeDescription = activityTypeDescription;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) {
			 return true;
		 } else {
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
    	/*If any one change this logic plz change order by clause in AdminDaoHibernate.java file 
    	 *service name getStudentObtainedActivitiesGradesByStudIdAndExamTypeIds() */
    	StudentActivityTypes activityTypes = (StudentActivityTypes) object;
    	return new CompareToBuilder().append(this.getId(), activityTypes.getId()).toComparison();
    }
    
    public void addStudentAcademicPerformance(StudentAcademicPerformance studentAcademicPerformance) {
		if(ObjectFunctions.isNullOrEmpty(this.studentAcademicPerformance)){
			this.studentAcademicPerformance=new ArrayList<StudentAcademicPerformance>();
		}
		this.studentAcademicPerformance.add(studentAcademicPerformance);
	}
    public void copyFrom(StudentActivityTypes obj)
    {
		setCustId(obj.getCustId());
		setActivityTypeDescription(obj.getActivityTypeDescription());
		setActivityTypeName(obj.getActivityTypeName());
		setCreatedDate(new Date());
		setLastAccessDate(new Date());
    }
    @Transient
	public String getSelectedClassIds() {
		return selectedClassIds;
	}
	public void setSelectedClassIds(String selectedClassIds) {
		this.selectedClassIds = selectedClassIds;
	}
	@Transient
	public List<Long> getActivityTypeClassNameIdsList(){
		if(ObjectFunctions.isNullOrEmpty(this.activityTypClasses))
			return null;
		else{
			List<Long> classNames = new ArrayList<Long>();
			for(StudyClass activityClass : this.activityTypClasses){
				classNames.add(activityClass.getId());
				activityClass = null;
			}
			return classNames;
		}
	}
	@Transient
	public String getActivityTypeClassNames(){
		if(ObjectFunctions.isNotNullOrEmpty(this.activityTypClasses)){
			List<String> classNames = new ArrayList<String>();
			for(StudyClass activityClass : this.activityTypClasses){
				classNames.add(activityClass.getClassName());
			}
			return StringFunctions.convertListToCommaDelimitedString(classNames);
		}
		return null;
	}
	@Transient
	public String getStudentActivityName(){
		if(ObjectFunctions.isNullOrEmpty(this.studentActivity))
			return "";
		else
			return this.studentActivity.getActivityName();
	}
  }

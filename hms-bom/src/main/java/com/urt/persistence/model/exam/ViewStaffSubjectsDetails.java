package com.urt.persistence.model.exam;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.string.StringFunctions;


/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewClassExamDetails.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Ganesh Created /
 ********************************************************************/

@Entity
@Table(name = "vw_staffSubjectsDetails")
public class ViewStaffSubjectsDetails implements Serializable,Cloneable,Comparable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private String staffId;
	
	private String classTeacher;
	private long studyClassId;
	private String subjectName;
	private long studySubjectId;
	private String className;
	private String section;
	private long custId;
	private long academicYearId;
	private long classTeacherId;
	private long accountId;
	private long classId;
	private long classSubjectId;
	//private int periodsCount;
	private int periodsPerWeek;
	private int periodsHandleCount;
	private int classSortingOrder;
	protected String status;
	private int subjectPriority;
	private String classAndSection;
	
	public int getSubjectPriority() {
		return subjectPriority;
	}
	public void setSubjectPriority(int subjectPriority) {
		this.subjectPriority = subjectPriority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getClassSortingOrder() {
		return classSortingOrder;
	}
	public void setClassSortingOrder(int classSortingOrder) {
		this.classSortingOrder = classSortingOrder;
	}
	public int getPeriodsHandleCount() {
		return periodsHandleCount;
	}
	public void setPeriodsHandleCount(int periodsHandleCount) {
		this.periodsHandleCount = periodsHandleCount;
	}
	public int getPeriodsPerWeek() {
		return periodsPerWeek;
	}
	public void setPeriodsPerWeek(int periodsPerWeek) {
		this.periodsPerWeek = periodsPerWeek;
	}
	/*public int getPeriodsCount() {
		return periodsCount;
	}
	public void setPeriodsCount(int periodsCount) {
		this.periodsCount = periodsCount;
	}*/
	
	@Column(name = "classId", nullable = false, columnDefinition=" int default 0")
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	
	@Column(name = "classSubjectId", nullable = false, columnDefinition=" int default 0")
	public long getClassSubjectId() {
		return classSubjectId;
	}
	public void setClassSubjectId(long classSubjectId) {
		this.classSubjectId = classSubjectId;
	}
	
	@Id
	@Column(name = "classTeacherId", nullable = false)
	public long getClassTeacherId() {
		return classTeacherId;
	}
	public void setClassTeacherId(long classTeacherId) {
		this.classTeacherId = classTeacherId;
	}
	@Column(name = "accountId", nullable = false, columnDefinition=" int default 0")
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	@Column(name = "staffId", nullable = false, columnDefinition=" int default 0")
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	@Column(name = "classTeacher", nullable = true, length = 255)
	public String getClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}
	
	@Column( name="studyClassId", nullable=false, updatable=false )
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	@Column(name = "subjectName", nullable = true, length = 255)
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	@Column(name = "studySubjectId", unique=false,nullable = false, columnDefinition=" int default 0")
	public long getStudySubjectId() {
		return studySubjectId;
	}
	public void setStudySubjectId(long studySubjectId) {
		this.studySubjectId = studySubjectId;
	}
	
	@Column(name = "className", nullable = true, length = 255)
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Column(name = "section", nullable = true, length = 255)
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	@Column(name = "custId", nullable = false, columnDefinition=" int default 0")
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Column(name = "academicYearId", nullable = false, columnDefinition=" int default 0")
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	@Override
	public int compareTo(Object o) {
		ViewStaffSubjectsDetails target = (ViewStaffSubjectsDetails) o;
    	if (target != null && this != null)
        {
    		if(this.getClassSortingOrder() > target.getClassSortingOrder())
    				return 1;
    		else if(this.getClassSortingOrder() == target.getClassSortingOrder()){
    			if(this.getSection().compareTo(target.getSection()) > 0)
    				return 1;
    		}
    		else
    			return 0;
        }
		return 0;
	}
	
	@Transient
	public String getClassSection(){
		if(StringFunctions.isNullOrEmpty(getSection()))
    	{
    		return getClassName();
    	}
    	return getClassName()+" - "+getSection();
	}
	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	
	
}

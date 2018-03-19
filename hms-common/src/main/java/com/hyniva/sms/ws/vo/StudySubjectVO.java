package com.hyniva.sms.ws.vo;

import java.util.List;


public class StudySubjectVO{
	
	public long id;
	public String name;       
    public String description; 
    public long custId;
    //protected long parentId;
    public List expecTeacherList;
    public String staffAccountId;
    public List subjectClassList;
    public String subjectNumber;    
    public int sortingOrder;
    public boolean language;
    public String subjectStaffId;
    public String subjectType;     
    public AcademicYearVo academicYearVo;
    

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List getExpecTeacherList() {
		return expecTeacherList;
	}

	public void setExpecTeacherList(List expecTeacherList) {
		this.expecTeacherList = expecTeacherList;
	}

	public String getStaffAccountId() {
		return staffAccountId;
	}

	public void setStaffAccountId(String staffAccountId) {
		this.staffAccountId = staffAccountId;
	}

	public List getSubjectClassList() {
		return subjectClassList;
	}

	public void setSubjectClassList(List subjectClassList) {
		this.subjectClassList = subjectClassList;
	}

	public String getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public int getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public boolean isLanguage() {
		return language;
	}

	public void setLanguage(boolean language) {
		this.language = language;
	}

	public String getSubjectStaffId() {
		return subjectStaffId;
	}

	public void setSubjectStaffId(String subjectStaffId) {
		this.subjectStaffId = subjectStaffId;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public AcademicYearVo getAcademicYearVo() {
		return academicYearVo;
	}

	public void setAcademicYearVo(AcademicYearVo academicYearVo) {
		this.academicYearVo = academicYearVo;
	}
    
     
    
}

package com.urt.persistence.model.study;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.common.constants.Constants;


@Entity
@Table(name = "vw_studentsLatestExamMarksDetails")
public class ViewStudentsLatestExamMarksDetails implements Serializable {
	
	private long marksId;
	private long examTypeId;
	private long custId;
	private long  classSectionId;
	private long classNameClassId;
	private String examTypeName;
	private long academicYearId;
	private Date examStartDate;
	private String className;
	protected Date lastUpdatedDate;
	private String section;
	private String minMarks;
	private long studId;
	private double obtainedMarks;
	protected String present = Constants.YES_STRING;
	private long subjectId ;
	private String status=Constants.YES_STRING;
	
	
	
	
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getStudId() {
		return studId;
	}
	public void setStudId(long studId) {
		this.studId = studId;
	}
	public double getObtainedMarks() {
		return obtainedMarks;
	}
	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getMinMarks() {
		return minMarks;
	}
	public void setMinMarks(String minMarks) {
		this.minMarks = minMarks;
	}
	public Date getExamStartDate() {
		return examStartDate;
	}
	public void setExamStartDate(Date examStartDate) {
		this.examStartDate = examStartDate;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	
	@Id
	@Column( name="marksId", unique=false, nullable=false, updatable=false )
	public long getMarksId() {
		return marksId;
	}
	public void setMarksId(long marksId) {
		this.marksId = marksId;
	}
	
	@Column(name = "custId", nullable = true, length = 20)
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Column(name = "examTypeId", nullable = true, length = 255)
	public long getExamTypeId() {
		return examTypeId;
	}
	public void setExamTypeId(long examTypeId) {
		this.examTypeId = examTypeId;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getExamTypeName() {
		return examTypeName;
	}
	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public long getClassNameClassId() {
		return classNameClassId;
	}
	public void setClassNameClassId(long classNameClassId) {
		this.classNameClassId = classNameClassId;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	@Transient
	public String getClassAndSection() {
		return getClassName() + "-" + getSection();
	}
	/*public ViewStudentsLatestExamMarksDetails(){
		super();
	}*/
	
}

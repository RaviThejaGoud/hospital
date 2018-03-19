package com.urt.persistence.model.exam;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.common.StudyMaterialAttachments;

@Entity
@Table(name="vw_questionBankDetails")
public class ViewQuestionBankDetails implements java.io.Serializable,Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5105710325613050558L;
	private long id;
	
	private long custId;
	private long academicYearId;
	private long questionBankId;
	private long subjectId;
	private long lessonId;
	private long classSectionId;
	private String classNameAndSection;
	private String lessonName;
	private String subjectName;
	private String materialName;
	private String description;
	public List<Attachment> attachments;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public long getQuestionBankId() {
		return questionBankId;
	}
	public void setQuestionBankId(long questionBankId) {
		this.questionBankId = questionBankId;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public long getLessonId() {
		return lessonId;
	}
	public void setLessonId(long lessonId) {
		this.lessonId = lessonId;
	}
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public String getClassNameAndSection() {
		return classNameAndSection;
	}
	public void setClassNameAndSection(String classNameAndSection) {
		this.classNameAndSection = classNameAndSection;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transient
	public List<Attachment> getAttachments() {
		if(!ObjectFunctions.isNullOrEmpty(this.attachments))
		{
			this.attachments = new ArrayList<Attachment>();
		}
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
}

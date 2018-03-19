package com.urt.persistence.model.study;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.StudyMaterialAttachments;


/**
 * ViewStudyClassSubjects entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="vw_studyClassMaterials")

public class ViewStudyClassMaterials  implements java.io.Serializable,Comparable {

	 /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long id;
     private long classSectionId;
     private String className;
     private long academicYearId;
     private long subjectId;
     private String subjectName;
     private String materialName;
     private String description;
     private long custId;
     private long materialId;
     private String SectionName;
     protected List<StudyMaterialAttachments> attachmentsList;
     
     @Id
 	public long getId() {
 		return id;
 	}

 	public void setId(long id) {
 		this.id = id;
 	}
     public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSectionName() {
		return SectionName;
	}
	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}
     
     public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}
     
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
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
	public List<StudyMaterialAttachments> getAttachmentsList() {
		if(ObjectFunctions.isNullOrEmpty(this.attachmentsList))
		{
			this.attachmentsList = new ArrayList();
		}
		return attachmentsList;
	}

	public void setAttachmentsList(List<StudyMaterialAttachments> attachmentsList) {
		this.attachmentsList = attachmentsList;
	}
}
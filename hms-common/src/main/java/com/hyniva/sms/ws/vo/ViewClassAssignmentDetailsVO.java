package com.hyniva.sms.ws.vo;

import java.util.List;




public class ViewClassAssignmentDetailsVO{
	
	 private String description;
	 private Long id;
	 private String createdBy;
	 private String date;
	 private Long studyClassId;
	 private String  classAndSection;
	 private Long subjectId;
	 private String subjectName;
	 private String status; 
	 private List<AttachmentVO> attachments;
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the studyClassId
	 */
	public Long getStudyClassId() {
		return studyClassId;
	}
	/**
	 * @param studyClassId the studyClassId to set
	 */
	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}
	/**
	 * @return the subjectId
	 */
	public Long getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
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
	 * @return the attachments
	 */
	public List<AttachmentVO> getAttachments() {
		return attachments;
	}
	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<AttachmentVO> attachments) {
		this.attachments = attachments;
	}
	/**
	 * @return the classAndSection
	 */
	public String getClassAndSection() {
		return classAndSection;
	}
	/**
	 * @param classAndSection the classAndSection to set
	 */
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
    
   
	 
     
}

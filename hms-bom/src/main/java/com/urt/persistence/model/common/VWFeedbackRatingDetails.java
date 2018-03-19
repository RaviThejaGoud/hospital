package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "vw_FeedbackRatingDetails") 
public class VWFeedbackRatingDetails  implements java.io.Serializable {
	
	private long parentFeedbackId;
	protected long custId; 
    protected long parentId;
    protected long studentId;
    protected long staffId;
    protected long feedbackQuestionId;
    protected long feedbackGradeId;
    private long academicYearId;
    private String resultGradeValue;
    private String roleDescription;
    private String qusDescription;
    protected Double totalRatingVal;
    protected Double totalAvgRatingVal;
    protected Double avgRatingVal;
    private String description;
    
    @Id
	@Column(name = "parentFeedbackId", nullable = false)
	public long getParentFeedbackId() {
		return parentFeedbackId;
	}
	public void setParentFeedbackId(long parentFeedbackId) {
		this.parentFeedbackId = parentFeedbackId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
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
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	@Transient
	public Double getTotalRatingVal() {
		return totalRatingVal;
	}
	public void setTotalRatingVal(Double totalRatingVal) {
		this.totalRatingVal = totalRatingVal;
	}
	public String getResultGradeValue() {
		return resultGradeValue;
	}
	public void setResultGradeValue(String resultGradeValue) {
		this.resultGradeValue = resultGradeValue;
	}
	public String getQusDescription() {
		return qusDescription;
	}
	public void setQusDescription(String qusDescription) {
		this.qusDescription = qusDescription;
	}
	@Transient
	public Double getTotalAvgRatingVal() {
		return totalAvgRatingVal;
	}
	public void setTotalAvgRatingVal(Double totalAvgRatingVal) {
		this.totalAvgRatingVal = totalAvgRatingVal;
	}
	@Transient
	public Double getAvgRatingVal() {
		return avgRatingVal;
	}
	public void setAvgRatingVal(Double avgRatingVal) {
		this.avgRatingVal = avgRatingVal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

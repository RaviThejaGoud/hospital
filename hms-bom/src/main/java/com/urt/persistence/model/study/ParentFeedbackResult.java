package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "parentFeedbackResult")
public class ParentFeedbackResult  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected long custId; 
    protected long parentId;
    protected long studentId;
    protected long staffId;
    protected long feedbackQuestionId;
    protected String feedbackGradeId;
    private String academicYearId;
    private String description;
    protected Double totalRatingVal;
    
    
    
    
    
    /*protected long parentFeedbackResultId;
    
    
    
    
     public long getParentFeedbackResultId() {
		return parentFeedbackResultId;
	}

	public void setParentFeedbackResultId(long parentFeedbackResultId) {
		this.parentFeedbackResultId = parentFeedbackResultId;
	}*/

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the academicYearId
	 */
	public String getAcademicYearId() {
		return academicYearId;
	}

	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(String academicYearId) {
		this.academicYearId = academicYearId;
	}
	/**
	 * @return the studentId
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the staffId
	 */
	public long getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getFeedbackQuestionId() {
		return feedbackQuestionId;
	}

	public void setFeedbackQuestionId(long feedbackQuestionId) {
		this.feedbackQuestionId = feedbackQuestionId;
	}

	public String getFeedbackGradeId() {
		return feedbackGradeId;
	}

	public void setFeedbackGradeId(String feedbackGradeId) {
		this.feedbackGradeId = feedbackGradeId;
	}

	/**
	 * @return the studentAnswer
	 */

	public ParentFeedbackResult() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }
    
	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Transient
	public String getSectionName()
	{
		 StringBuffer ret = new StringBuffer(10);
		 ret.append("Section ");
		 
		return ret.toString();
	}

    @Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Transient
	public Double getTotalRatingVal() {
		return totalRatingVal;
	}

	public void setTotalRatingVal(Double totalRatingVal) {
		this.totalRatingVal = totalRatingVal;
	}
	
		
}
    

  


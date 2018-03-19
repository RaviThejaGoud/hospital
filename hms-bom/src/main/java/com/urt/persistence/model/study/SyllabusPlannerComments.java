package com.urt.persistence.model.study;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "syllabusPlannerComments")
public class SyllabusPlannerComments  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private long receiverAccountId;
    private Date commentsDate;
    private long staffSyllabusPlannerId;
    private String messageContent;
    protected long custId;
    private long academicYearId;
    private String status;
    

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
	 * @return the receiverAccountId
	 */
	@Column(name = "receiverAccountId", nullable = true, columnDefinition="bigint(20) default 0")
	public long getReceiverAccountId() {
		return receiverAccountId;
	}

	/**
	 * @param receiverAccountId the receiverAccountId to set
	 */
	public void setReceiverAccountId(long receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}

	/**
	 * @return the commentsDate
	 */
	public Date getCommentsDate() {
		return commentsDate;
	}

	/**
	 * @param commentsDate the commentsDate to set
	 */
	public void setCommentsDate(Date commentsDate) {
		this.commentsDate = commentsDate;
	}

	/**
	 * @return the staffSyllabusPlannerId
	 */
	@Column(name = "staffSyllabusPlannerId", nullable = true, columnDefinition="bigint(20) default 0")
	public long getStaffSyllabusPlannerId() {
		return staffSyllabusPlannerId;
	}

	/**
	 * @param staffSyllabusPlannerId the staffSyllabusPlannerId to set
	 */
	public void setStaffSyllabusPlannerId(long staffSyllabusPlannerId) {
		this.staffSyllabusPlannerId = staffSyllabusPlannerId;
	}

	/**
	 * @return the messageContent
	 */
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * @param messageContent the messageContent to set
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
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

    
    public SyllabusPlannerComments() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

    public SyllabusPlannerComments(long id) {
        setId(id);
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
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId()).toString();
	}
	@Transient
	public String getCommentsDateStr() {
		return DateFormatter.formatDate(DateFormatter.DATE_AT_TIME_PATTERN, getCommentsDate());
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
}

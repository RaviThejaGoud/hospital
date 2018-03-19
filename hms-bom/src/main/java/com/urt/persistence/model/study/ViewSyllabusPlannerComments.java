package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.UserImage;


/**
 * ViewSyllabusPlannerComments entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "vw_syllabusPlannerComments")
public class ViewSyllabusPlannerComments  implements Serializable,Cloneable,Comparable {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ViewSyllabusPlannerComments.class);
    private long receiverAccountId;
    private Date commentsDate;
    private long staffSyllabusPlannerId;
    private String messageContent;
    protected long custId;
    private long academicYearId;
    private String status;
    private String imagePath;
    private String thumbNail;
    private String imageName;
    private String lastName;
    private String firstName;
    private long syllabusPlannerCommentsId;
    
    
    
    

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the syllabusPlannerCommentsId
	 */
    @Id
	public long getSyllabusPlannerCommentsId() {
		return syllabusPlannerCommentsId;
	}

	/**
	 * @param syllabusPlannerCommentsId the syllabusPlannerCommentsId to set
	 */
	public void setSyllabusPlannerCommentsId(long syllabusPlannerCommentsId) {
		this.syllabusPlannerCommentsId = syllabusPlannerCommentsId;
	}

	/**
	 * @return the thumbNail
	 */
	public String getThumbNail() {
		return thumbNail;
	}

	/**
	 * @param thumbNail the thumbNail to set
	 */
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	 * @return the receiverAccountId
	 */
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


    public ViewSyllabusPlannerComments() {
    }
    
    public int compareTo(Object o) {
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
	
	@Transient
    public String getAdjustedAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getImagePath()))
        {
        	log.debug(getImagePath().concat(getImageName())); 
        	return ".."+getImagePath().concat(getImageName());
        }
        return UserImage.getStudyImageNotFoundFile();
    }
}

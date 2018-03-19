/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Scraps.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Feb 03, 2011       Narahari          Created
/ ********************************************************************/

package com.urt.persistence.model.alumnee;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.user.User;

/**
 * @author Narahari
 *
 */
@Entity
@Table(name = "shareUserActivities")
public class ShareUserActivities extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String description;
     private String status;
     private String messageDate;
     private long custId;
     //private String isNewMessage="UR";
     protected List<ShareUserActivitiesComments> shareUserActivitiesComments;
     private User account;
     private String messageType;
     private String imagePath;
     private String imageName;
     private String imageType;
     private String eventName;
     private String startDateTime;
     private String endDateTime;
     private String eventLocation;
     private String visibleType;
     
     
     
    
     
     
    public String getVisibleType() {
		return visibleType;
	}

	public void setVisibleType(String visibleType) {
		this.visibleType = visibleType;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Column(name = "description", nullable = true, length = 2048)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	/*public String getIsNewMessage() {
		return isNewMessage;
	}

	public void setIsNewMessage(String isNewMessage) {
		this.isNewMessage = isNewMessage;
	}*/

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="shareUserActivitiesId") 
	public List<ShareUserActivitiesComments> getShareUserActivitiesComments() {
		return shareUserActivitiesComments;
	}

	public void setShareUserActivitiesComments(
			List<ShareUserActivitiesComments> shareUserActivitiesComments) {
		this.shareUserActivitiesComments = shareUserActivitiesComments;
	}
	

	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="accountId", insertable=true, updatable=true)
	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
	}
	
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public int compareTo(Object object) {
		return 0;
	}
  
	@Transient
	public void addShareUserActivitiesComments(ShareUserActivitiesComments shareUserActivitiesComments) {
		if(ObjectFunctions.isNullOrEmpty(this.getShareUserActivitiesComments())){
			this.shareUserActivitiesComments=new ArrayList<ShareUserActivitiesComments>();
		}
		this.shareUserActivitiesComments.add(shareUserActivitiesComments);
	}
	
	@Transient
    public String getCreatedDateStr() {
        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,this.createdDate);

    }
	@Transient
	 public String getFormatDateStr() {
	        return DateFormatter.formatDate(DateFormatter.DDMMMYYYYHHMMSSA_PATTERN,this.createdDate);

	 }
	@Transient
	public String getAdjustedStudentImagePath() {
		if (!ObjectFunctions.isNullOrEmpty(getAccount())) {
			if (!ObjectFunctions.isNullOrEmpty(getAccount().getProfileImage())) {
				if (!StringFunctions.isNullOrEmpty(getAccount().getProfileImage().getPath())) {
					log.debug(getAccount().getProfileImage().getThumbNail());
					return getAccount().getProfileImage().getPath().concat(getAccount().getProfileImage().getThumbNail());
				}
			}
		}
		return UserImage.getStudyImageNotFoundFile();
	}
}
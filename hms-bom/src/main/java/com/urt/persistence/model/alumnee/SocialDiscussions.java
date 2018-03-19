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
import java.util.Date;
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
@Table(name = "socialDiscussions")
public class SocialDiscussions extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String description;
     private String status;
     private Date messageDate;
     private long custId;
     private User account;
     private String subject;
     private String attachmentPath;
     private String attachmentName;
     private String attachmentType;
     private String category;
     protected List<SocialDiscussionsComments> socialDiscussionsComments;
     
     
    

     
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
  	@JoinColumn(name="socialDiscussionsCommentsId")
	public List<SocialDiscussionsComments> getSocialDiscussionsComments() {
		return socialDiscussionsComments;
	}

	public void setSocialDiscussionsComments(
			List<SocialDiscussionsComments> socialDiscussionsComments) {
		this.socialDiscussionsComments = socialDiscussionsComments;
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

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="accountId", insertable=true, updatable=true)
	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
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
	public void addDisCommnents(SocialDiscussionsComments socialDiscussionsComments) {
		if(ObjectFunctions.isNullOrEmpty(this.getSocialDiscussionsComments())){
			this.socialDiscussionsComments=new ArrayList<SocialDiscussionsComments>();
		}
		this.socialDiscussionsComments.add(socialDiscussionsComments);
	}
	
	@Transient
	public String getCategoryStr() 
	{
		if("G".equalsIgnoreCase(getCategory()))
			return "General";
		else if("B".equalsIgnoreCase(getCategory()))
			return "Business";
		return "";
	}
	
	@Transient
    public String getMessageDateStr() {
        return DateFormatter.formatDate(DateFormatter.DDMMMYYYYHHMMSSA_PATTERN,this.getMessageDate());

    }
	@Transient
	public String getAdjustedStudentImagePath() {
		if (!ObjectFunctions.isNullOrEmpty(getAccount())) {
			if (!ObjectFunctions.isNullOrEmpty(getAccount().getProfileImage())) {
				if (!StringFunctions.isNullOrEmpty(getAccount().getProfileImage().getPath())) {
					//log.debug(getAccount().getProfileImage().getThumbNail());
					return getAccount().getProfileImage().getPath().concat(getAccount().getProfileImage().getThumbNail());
				}
			}
		}
		return UserImage.getStudyImageNotFoundFile();
	}
}
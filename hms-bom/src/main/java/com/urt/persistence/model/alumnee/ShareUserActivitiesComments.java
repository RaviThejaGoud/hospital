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

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/**
 * @author Narahari
 *
 */
@Entity
@Table(name = "shareUserActivitiesComments")
public class ShareUserActivitiesComments extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String commentDescription;
     private String status;
     private Date commentDate;
     private long custId;
     //protected Set<ReplyScrapMessage> replayScraps;
     private User commentUserAccount;
     
     
     
    @Column(name = "commentDescription", nullable = true, length = 1048)
    public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="commentUserAccountId", insertable=true, updatable=true)
	public User getCommentUserAccount() {
		return commentUserAccount;
	}

	public void setCommentUserAccount(User commentUserAccount) {
		this.commentUserAccount = commentUserAccount;
	}

	/**
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
    	return "";
    }
    
    @Transient
    public String getScrapMessageDateStr() {
        return DateFormatter.formatDate(DateFormatter.DATE_AT_TIME_PATTERN,this.commentDate);
    }
    
    @Transient
    public String getCommentDateStr() {
        return DateFormatter.formatDate(DateFormatter.DDMMMYYYYHHMMSSA_PATTERN,this.commentDate);

    }
    @Transient
    public String getShortMessageDesc() {
        return StringFunctions.breakString(getCommentDescription(), 50);
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
	
	
}
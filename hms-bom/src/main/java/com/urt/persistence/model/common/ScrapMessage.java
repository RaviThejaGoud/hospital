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

package com.urt.persistence.model.common;

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.ScrapMessageVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/**
 * @author Narahari
 *
 */
@Entity
@Table(name = "scrapMessage")
public class ScrapMessage extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String title;
     private String scrapDescription;
     private String status;
     private Date scrapDate;
     private long custId;
     private String isNewMessage="UR";
     private String replyStatus="N";
     protected List<ReplyScrapMessage> replayScrap;
     private User senderAccount;
     private User receiverAccount;
     private String isNewReply="RM";
     protected AcademicYear academicYear;
     private String messageType;
     
     
     /**
      * @return the academicYear
      */
     @OneToOne
     @JoinColumn(name="academicYearId", insertable=true, updatable=true)
     public AcademicYear getAcademicYear() {
		return academicYear;
     }
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

     
    @OneToOne
 	@JoinColumn(name="senderAccountId", insertable=true, updatable=true) 
    public User getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(User senderAccount) {
		this.senderAccount = senderAccount;
	}
	
	

	@OneToOne
 	@JoinColumn(name="receiverAccountId", insertable=true, updatable=true) 
	public User getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(User receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public String getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="scrapId") 
	public List<ReplyScrapMessage> getReplayScrap() {
		return (List<ReplyScrapMessage>) this.replayScrap;
	}

	public void setReplayScrap(List<ReplyScrapMessage> replayScrap) {
		this.replayScrap = replayScrap;
	}

	public Date getScrapDate() {
		return scrapDate;
	}

	public void setScrapDate(Date scrapDate) {
		this.scrapDate = scrapDate;
	}

	public String getIsNewReply() {
		return isNewReply;
	}

	public void setIsNewReply(String isNewReply) {
		this.isNewReply = isNewReply;
	}
	
	public String getIsNewMessage() {
		return isNewMessage;
	}

	public void setIsNewMessage(String isNewMessage) {
		this.isNewMessage = isNewMessage;
	}

	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Column(name = "title", nullable = true, length = 256)
     public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	 @Column(name = "scrapDescription", nullable = true, length = 1024)
	public String getScrapDescription() {
		return scrapDescription;
	}


	public void setScrapDescription(String scrapDescription) {
		this.scrapDescription = scrapDescription;
	}

	@Column(name = "status", nullable = true, length = 10)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "messageType", nullable = true, length = 1024)
    public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
     
    public ScrapMessage() {
		super();
		this.setScrapDate(new Date());
		this.setCreatedDate(new Date());
		this.setLastAccessDate(new Date());
		this.setLastUpdatedDate(new Date());
	}


	public ScrapMessage(String title, String scrapDescription, String status) {
		super();
		this.title = title;
		this.scrapDescription = scrapDescription;
		this.status = status;
		
	}


	/**
     * @see java.lang.Comparable#compareTo(Object)
     */
	@Override
	public int compareTo(Object object) {
		ScrapMessage target = (ScrapMessage) object;
    	long timeDifference = 0;
    	if (target.getCreatedDate() != null && this.getCreatedDate() != null) {
    	timeDifference = this.getCreatedDate().getTime()
    	- target.getCreatedDate().getTime();
    	}
    	int difference;
    	if (timeDifference == 0) {
    	difference = 0;
    	} else if (timeDifference > 0) {
    	difference = -1;
    	} else {
    	difference = 1;
    	}
    	return difference;
    	}
    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
	public boolean equals(Object object) {
        if (!(object instanceof UserImage)) {
            return false;
        }
        ScrapMessage rhs = (ScrapMessage) object;
        return new EqualsBuilder().append(this.title, rhs.title).append(
                this.scrapDescription, rhs.scrapDescription).append(this.status, rhs.status)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
        return new HashCodeBuilder(-1923559909, -664973933).append(
                this.title).append(this.title).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                .append("title",
                        this.title).append("scrapDescription", this.scrapDescription)
                .toString();
    }
    
    @Transient
    public String getScrapMessageDateStr() {
        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,this.scrapDate);

    }
    @Transient
    public String getShortMessageDesc() {
        return StringFunctions.breakString(getScrapDescription(), 50);
    }
    @Transient
    public String getShortScrapTitle() {
        return StringFunctions.breakString(getTitle(), 50);
    }

    public void addReplayScrapMessage(ReplyScrapMessage replyScrapMessage) {
		if(ObjectFunctions.isNullOrEmpty(this.getReplayScrap())){
			this.replayScrap=new ArrayList<ReplyScrapMessage>();
		}
		this.replayScrap.add(replyScrapMessage);
	}
	public ScrapMessageVO copyFromEntityToVo(ScrapMessage scrapMessage) {
		
		ScrapMessageVO scrapMessageVO = new ScrapMessageVO(); 
		scrapMessageVO.id = scrapMessage.id;
		scrapMessageVO.title = scrapMessage.title;
		scrapMessageVO.scrapDescription = scrapMessage.scrapDescription;
		scrapMessageVO.status = scrapMessage.status;
		scrapMessageVO.scrapDate = DateFunctions.convertDateToString(scrapMessage.scrapDate);
		scrapMessageVO.custId = scrapMessage.custId;
		scrapMessageVO.isNewMessage = scrapMessage.isNewMessage;
		//scrapMessageVO.replyStatus = scrapMessage.replyStatus;
		//scrapMessageVO.replayScrap = scrapMessage.replayScrap;
		scrapMessageVO.senderAccountId = scrapMessage.senderAccount.getId();
		scrapMessageVO.receiverAccountId = scrapMessage.receiverAccount.getId();
		scrapMessageVO.isNewReply = scrapMessage.isNewReply;
		//scrapMessageVO.academicYear = scrapMessage.academicYear;
		scrapMessageVO.messageType = scrapMessage.messageType;
		scrapMessageVO.senderName = scrapMessage.senderAccount.getFullPersonName();
		
		return scrapMessageVO;
	}
  
    
    
}
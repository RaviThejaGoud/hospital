/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Messages.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Dec 03, 2010       Narahari          Created
/ ********************************************************************/

package com.urt.persistence.model.common;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;

/** @author Narahari */
@Entity
@Table(name = "messages")
public class Messages extends PersistentObject {

    private static final long serialVersionUID = 1L;
    private String title;
    private String messageDescription;
    private String status;
    private long senderAccountId;
    private long receiverAccountId;
    private Date messageDate;
    private String receiverType; // T - staff, S - Student , P - Parent, O -
				 // Other
    private String purposeType; // Reminder for - A - Attendance, F - Fee
				// Reminder, E - Exam
    private String username; // Sender User Name
    // private long custId;
    protected Customer customer;
    private String fullPersonName; // Receiver Name
    protected Date feeDueDateForStudent;
    private String feeOfStudentName;
    private String messageType; //SMS or EMAIL
    protected AcademicYear academicYear;
    protected List<MessageActivity> messageActivityList;
    protected int smsCount;
    protected String senderName;
    protected String guid;
    private int sentSms;
    private String emailIds;
    private String otherType;
    private String resString;
    private boolean deliveredSmsStatus;
    private String staffType; // T- teaching, NT- non teaching
    private SMSServiceProviders smsProviders;
    private String smsResXmlLocation;
    private String smsSenderId;
    private String mobileNumbers;
    private String messageContent;
    private String emailContent;
    private String successMobileNos;
    private String failedMobileNos;
    private String invalidMobileNos;
    protected Set<MessageDetailsTracking> messageDetailsTracking;
    protected int messagesCount;
    private String channel ; //Web/Desktop/Android application name
    
    @Column(name = "messagesCount", nullable = false, columnDefinition=" int default 0")
    public int getMessagesCount() {
		return messagesCount;
	}
	public void setMessagesCount(int messagesCount) {
		this.messagesCount = messagesCount;
	}
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
 	@JoinColumn(name="messageId")
	public Set<MessageDetailsTracking> getMessageDetailsTracking() {
		return messageDetailsTracking;
	}
	public void setMessageDetailsTracking(Set<MessageDetailsTracking> messageDetailsTracking) {
		this.messageDetailsTracking = messageDetailsTracking;
	}

    
    @Column(name = "successMobileNos", nullable = true, length = 65536)
	public String getSuccessMobileNos() {
		return successMobileNos;
	}
	public void setSuccessMobileNos(String successMobileNos) {
		this.successMobileNos = successMobileNos;
	}
	 
	@Column(name = "invalidMobileNos", nullable = true, length = 65536)
	public String getInvalidMobileNos() {
		return invalidMobileNos;
	}
	public void setInvalidMobileNos(String invalidMobileNos) {
		this.invalidMobileNos = invalidMobileNos;
	}
	
	@Column(name = "failedMobileNos", nullable = true, length = 65536)
	public String getFailedMobileNos() {
		return failedMobileNos;
	}
	public void setFailedMobileNos(String failedMobileNos) {
		this.failedMobileNos = failedMobileNos;
	}
	@Transient
    public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public Messages() {
		super();
		this.setMessageDate(new Date());
		this.setCreatedDate(new Date());
		this.setLastAccessDate(new Date());
		this.setLastUpdatedDate(new Date());
		this.channel = "Web";
    }
    public Messages(Customer customer, SMSServiceProviders smsprovider, AcademicYear ayear, String mtype, String purpose, String status) {
		super();
		this.setMessageDate(new Date());
		this.setCreatedDate(new Date());
		this.setLastAccessDate(new Date());
		this.setLastUpdatedDate(new Date());
		this.customer=customer;
		this.academicYear=ayear;
		this.smsProviders = smsprovider;
		this.status = status;
		this.purposeType = purpose;
		this.messageType = mtype;
		this.smsSenderId=customer.getSender();
		this.channel = "Web";
    }

    /** @return the academicYear */
    //@OneToOne(cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "academicYearId", insertable = true, updatable = true)
    public AcademicYear getAcademicYear() {
	return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
	this.academicYear = academicYear;
    }

    @Column(name = "feeOfStudentName", nullable = true, length = 255)
    public String getFeeOfStudentName() {
	return feeOfStudentName;
    }

    public void setFeeOfStudentName(String feeOfStudentName) {
	this.feeOfStudentName = feeOfStudentName;
    }

    /** @see java.lang.Comparable#compareTo(Object) */
    public int compareTo(Object object) {
	Messages target = (Messages) object;
	long timeDifference = 0;
	if (target.getCreatedDate() != null && this.getCreatedDate() != null) {
	    timeDifference = this.getCreatedDate().getTime() - target.getCreatedDate().getTime();
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

    /** @see java.lang.Object#equals(Object) */
    public boolean equals(Object object) {
	if (!(object instanceof UserImage)) {
	    return false;
	}
	Messages rhs = (Messages) object;
	return new EqualsBuilder().append(this.title, rhs.title)
		.append(this.messageDescription, rhs.messageDescription).append(this.status, rhs.status).isEquals();
    }

    /** @see java.lang.Object#hashCode() */
    public int hashCode() {
	return new HashCodeBuilder(-1923559909, -664973933).append(this.title).append(this.title).toHashCode();
    }

    /** @see java.lang.Object#toString() */
    public String toString() {
	return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.getId())
		.append("title", this.title)
		.append("messageDescription", this.messageDescription).toString();
    }

    @Transient
    public String getMessageDateStr() {
	return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.messageDate);

    }

    @Transient
    public String getSortingMessageDate() {
	return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.messageDate);

    }

    @Transient
    public String getShortMessageDesc() {
	return StringFunctions.breakString(getMessageDescription(), 50);
    }

    @Transient
    public String getShortMessageTitle() {
	return StringFunctions.breakString(getTitle(), 15);
    }

    @Transient
    public String getOtherType() {
	return otherType;
    }

    public void setOtherType(String otherType) {
	this.otherType = otherType;
    }

    @Transient
    public String getResString() {
	return resString;
    }

    public void setResString(String resString) {
	this.resString = resString;
    }

    /** @return the messageActivityList */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "messageId")
    public List<MessageActivity> getMessageActivityList() {
	return messageActivityList;
    }

    /** @param messageActivityList
     *            the messageActivityList to set */
    public void setMessageActivityList(List<MessageActivity> messageActivityList) {
	this.messageActivityList = messageActivityList;
    }

    /** @return the title */
    public String getTitle() {
	return title;
    }

    /** @param title
     *            the title to set */
    public void setTitle(String title) {
	this.title = title;
    }

    /** @return the messageDescription */
    @Column(length = 10000)
    public String getMessageDescription() {
	return messageDescription;
    }

    /** @param messageDescription
     *            the messageDescription to set */
    public void setMessageDescription(String messageDescription) {
	this.messageDescription = messageDescription;
    }

    /** @return the status */
    @Column(name = "status", nullable = true, length = 5)
    public String getStatus() {
	return status;
    }

    /** @param status
     *            the status to set */
    public void setStatus(String status) {
	this.status = status;
    }

    /** @return the senderAccountId */
    public long getSenderAccountId() {
	return senderAccountId;
    }

    /** @param senderAccountId
     *            the senderAccountId to set */
    public void setSenderAccountId(long senderAccountId) {
	this.senderAccountId = senderAccountId;
    }

    /** @return the receiverAccountId */
    public long getReceiverAccountId() {
	return receiverAccountId;
    }

    /** @param receiverAccountId
     *            the receiverAccountId to set */
    public void setReceiverAccountId(long receiverAccountId) {
	this.receiverAccountId = receiverAccountId;
    }

    /** @return the messageDate */
    public Date getMessageDate() {
	return messageDate;
    }

    /** @param messageDate
     *            the messageDate to set */
    public void setMessageDate(Date messageDate) {
	this.messageDate = messageDate;
    }

    /** @return the receiverType */
    public String getReceiverType() {
	return receiverType;
    }

    /** @param receiverType
     *            the receiverType to set */
    public void setReceiverType(String receiverType) {
	this.receiverType = receiverType;
    }

    /** @return the purposeType */
    public String getPurposeType() {
	return purposeType;
    }

    /** @param purposeType
     *            the purposeType to set */
    public void setPurposeType(String purposeType) {
	this.purposeType = purposeType;
    }

    /** @return the username */
    public String getUsername() {
	return username;
    }

    /** @param username
     *            the username to set */
    public void setUsername(String username) {
	this.username = username;
    }

    /** @return the custId */
    /*
     * public long getCustId() {
     * return custId;
     * }
     *//** @param custId
     *            the custId to set */
    /*
     * public void setCustId(long custId) {
     * this.custId = custId;
     * }
     */

    /** @return the fullPersonName */
    public String getFullPersonName() {
	return fullPersonName;
    }

    /** @param fullPersonName
     *            the fullPersonName to set */
    public void setFullPersonName(String fullPersonName) {
	this.fullPersonName = fullPersonName;
    }

    /** @return the feeDueDateForStudent */
    public Date getFeeDueDateForStudent() {
	return feeDueDateForStudent;
    }

    /** @param feeDueDateForStudent
     *            the feeDueDateForStudent to set */
    public void setFeeDueDateForStudent(Date feeDueDateForStudent) {
	this.feeDueDateForStudent = feeDueDateForStudent;
    }

    /** @return the messageType */
    public String getMessageType() {
	return messageType;
    }

    /** @param messageType
     *            the messageType to set */
    public void setMessageType(String messageType) {
	this.messageType = messageType;
    }

    /** @return the serialversionuid */
    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    /** @return the smsCount */
    public int getSmsCount() {
	return smsCount;
    }

    /** @param smsCount
     *            the smsCount to set */
    public void setSmsCount(int smsCount) {
	this.smsCount = smsCount;
    }

    /** @return the customer */
    @OneToOne
    @JoinColumn(name = "custId", insertable = true, updatable = true)
    public Customer getCustomer() {
	return customer;
    }

    /** @param customer
     *            the customer to set */
    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

    /** @return the senderName */
    public String getSenderName() {
	return senderName;
    }

    /** @param senderName
     *            the senderName to set */
    public void setSenderName(String senderName) {
	this.senderName = senderName;
    }

    public String getGuid() {
	return guid;
    }

    public void setGuid(String guid) {
	this.guid = guid;
    }

    @Column(nullable = false, columnDefinition = " int default 0")
    public int getSentSms() {
	return sentSms;
    }

    public void setSentSms(int sentSms) {
	this.sentSms = sentSms;
    }

    public String getEmailIds() {
	return emailIds;
    }

    public void setEmailIds(String emailIds) {
	this.emailIds = emailIds;
    }

    @Column(nullable = false, length = 1, columnDefinition = "char(1) default 'N'")
    @Type(type = "yes_no")
    public boolean isDeliveredSmsStatus() {
	return deliveredSmsStatus;
    }

    public void setDeliveredSmsStatus(boolean deliveredSmsStatus) {
	this.deliveredSmsStatus = deliveredSmsStatus;
    }

    @Transient
    public String getStaffType() {
	return staffType;
    }

    public void setStaffType(String staffType) {
	this.staffType = staffType;
    }

    @Transient
    public Set<String> getOtherContactDetails() {
	Set<String> mobileNumberset = new HashSet<String>();
	try {
	    if ("M".equalsIgnoreCase(this.getStatus())) {
		if (StringFunctions.isNotNullOrEmpty(this.getMessageType())) {
		    String[] mb = this.getMessageType().split(",");
		    for (String mobileNumber : mb) {
    			if((!mobileNumber.equalsIgnoreCase("0000000000")) && (!mobileNumber.equalsIgnoreCase("+91-0000000000")) && (!StringFunctions.isNullOrEmpty(mobileNumber) && !(mobileNumber.length()>10) && !(mobileNumber.length()<10)))
    				mobileNumberset.add(mobileNumber);
		    }
		}
	    } else {
		if (StringFunctions.isNotNullOrEmpty(this.getEmailIds())) {
		    String[] emails = this.getEmailIds().split(",");
		    for (String email : emails) {
			mobileNumberset.add(email);
		    }
		}
	    }
	} catch (Exception ex) {
	    ex.getMessage();
	}
	return mobileNumberset;
    }

    /** @return the smsProviders */
    @OneToOne
	@JoinColumn(name="smsProvidersId",nullable=true)
    public SMSServiceProviders getSmsProviders() {
	return this.smsProviders;
    }

    /** @param smsProviders
     *            the smsProviders to set */
    public void setSmsProviders(SMSServiceProviders smsProviders) {
	this.smsProviders = smsProviders;
    }

    /** @return the smsResXmlLocation */
    @Transient
    public String getSmsResXmlLocation() {
        return this.smsResXmlLocation;
    }

    /**
     * @param smsResXmlLocation the smsResXmlLocation to set
     */
    public void setSmsResXmlLocation(String smsResXmlLocation) {
        this.smsResXmlLocation = smsResXmlLocation;
    }

    /** @return the smsSenderId */
    @Transient
    public String getSmsSenderId() {
	return this.smsSenderId;
    }

    /** @param smsSenderId
     *            the smsSenderId to set */
    public void setSmsSenderId(String smsSenderId) {
	this.smsSenderId = smsSenderId;
    }

    /** @return the mobileNumbers */
    @Transient
    public String getMobileNumbers() {
	return this.mobileNumbers;
    }

    /** @param mobileNumbers
     *            the mobileNumbers to set */
    public void setMobileNumbers(String mobileNumbers) {
	this.mobileNumbers = mobileNumbers;
    }

	/**
	 * @return the messageContent
	 */
    @Transient
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * @param messageContent the messageContent to set
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public void copyFrom(Messages obj)
    {
		setAcademicYear(obj.getAcademicYear());
		setCreatedById(obj.getCreatedById());
		setCreatedDate(obj.getCreatedDate());
		setLastAccessDate(obj.getLastAccessDate());
		setCustomer(obj.getCustomer());
		setEmailIds(obj.getEmailIds());
		setMessageContent(obj.getMessageContent());
		setMessageDate(obj.getMessageDate());
		setMessageDescription(obj.getMessageDescription());
		setMessageType(obj.getMessageType());
		setMobileNumbers(obj.getMobileNumbers());
		setOtherType(obj.getOtherType());
		setPurposeType(obj.getPurposeType());
		setReceiverAccountId(obj.getReceiverAccountId());
		setReceiverType(obj.getReceiverType());
		setSenderName(obj.getSenderName());
		setSenderAccountId(obj.getSenderAccountId());
		setSmsSenderId(obj.getCustomer().getSender());
		setStaffType(obj.getStaffType());
		setStatus(obj.getStatus());
		setTitle(obj.getTitle());
		setChannel(obj.getChannel());
		setMessageDetailsTracking(obj.getMessageDetailsTracking());
    }
	public void addMessageDetails(MessageDetailsTracking messageDetailsTracking) {
		if (ObjectFunctions.isNullOrEmpty(getMessageDetailsTracking())) {
			this.messageDetailsTracking = new HashSet<MessageDetailsTracking>();
		}
		getMessageDetailsTracking().add(messageDetailsTracking);
	}
	/**
	 * @return the channel
	 */
	@Column(name = "channel", nullable = false, columnDefinition="char(20) default 'Web'")
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
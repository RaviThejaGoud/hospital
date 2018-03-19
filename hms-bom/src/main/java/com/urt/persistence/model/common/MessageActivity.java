package com.urt.persistence.model.common;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;


/**
 * MessageActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="messageActivity")
public class MessageActivity  extends PersistentObject {

	 private static final long serialVersionUID = 1L;
	 private long customerId;
     private String toAddress;
     private String fromAddress;
     private String sender;
     private Date sendDate;
     private String type;
     private String purpose;
     private boolean delivered;
     private long messageId; 


    // Constructors

    /** default constructor */
    public MessageActivity() {
    	this.sendDate = DateFunctions.getTodayPlusNdays(0);
    }

    
    /** full constructor */
    public MessageActivity(long customerId, String toAddress, String fromAddress, String sender, Date sendDate, String type, String purpose, boolean delivered) {
        this.customerId = customerId;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.sender = sender;
        this.sendDate = sendDate;
        this.type = type;
        this.purpose = purpose;
        this.delivered = delivered;
    }

    @Column(name="customerId")

    public long getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    
    @Column(name="toAddress")

    public String getToAddress() {
        return this.toAddress;
    }
    
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
    
    @Column(name="fromAddress", length=128)

    public String getFromAddress() {
        return this.fromAddress;
    }
    
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
    
    @Column(name="sender", length=128)

    public String getSender() {
        return this.sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    @Column(name="sendDate", length=19)

    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
    @Column(name="type", length=128)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="purpose", length=2000)

    public String getPurpose() {
        return this.purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

	@Column(name = "delivered", nullable = true, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
   
	public boolean isDelivered() {
		return delivered;
	}


	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}


	/**
	 * @return the messageId
	 */
	public long getMessageId() {
		return messageId;
	}


	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	   
}
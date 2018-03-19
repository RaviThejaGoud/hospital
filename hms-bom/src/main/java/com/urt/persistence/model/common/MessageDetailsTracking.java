package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

@Entity
@Table(name="messageDetailsTracking")

public class MessageDetailsTracking extends PersistentObject {
	
	private static final long serialVersionUID = 1L;
	private long  custId; 
	private long academicYearId;
	private User account;
	private String mobileNumber;
	protected char deliveryStatus;  // S - SuccessMobileNo, F - FailedMobileNo , I - InvalidMobileNo
	protected Long messageId;
	
	protected String className;
	 /**
		 * @return the custId
		 */
	    @Column(name = "custId", nullable = true, length = 255)
		public long getCustId() {
			return custId;
		}

		/**
		 * @param custId the custId to set
		 */
		public void setCustId(long custId) {
			this.custId = custId;
		}
		/**
	     * @return the academicYear
	     */
			
		public long getAcademicYearId() {
			return academicYearId;
		}

		public void setAcademicYearId(long academicYearId) {
			this.academicYearId = academicYearId;
		}
	
	   
		public Long getMessageId() {
			return messageId;
		}
		
		public void setMessageId(Long messageId) {
			this.messageId = messageId;
		}		
		
		/**
		 * @return the accountId
		 */
		@OneToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="accountId")	
		public User getAccount() {
			return account;
		}
		/**
		 * @param accountId the accountId to set
		 */
		public void setAccount(User account) {
			this.account = account;
		}
		 /** @return the mobileNumber */   
		 @Column(name = "mobileNumber", nullable = true, length = 255)
	    public String getMobileNumber() {
		return this.mobileNumber;
	    }
	
	    /** @param mobileNumber the mobileNumber to set */
	    public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	    }
	   	
		 /** @return the deliveryStatus */
	    @Column(name = "deliveryStatus", nullable = false, length = 1, columnDefinition = "char(1) default 'N'")
	    public char getDeliveryStatus() {
		return deliveryStatus;
	    }
	    /** @param deliveryStatus the deliveryStatus to set */
	    public void setDeliveryStatus(char deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
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
		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}	
	
		
	}

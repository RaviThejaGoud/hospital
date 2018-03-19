package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.Customer;
@Entity
@Table(name="messageEnquiryDetails")
public class MessageEnquiryDetails extends PersistentObject {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageDescription;
	private long senderAccountId;
    private long receiverAccountId;
    private Date messageDate;
	private int sentSms;
    protected int smsCount;
    protected String senderName;
    private String roleName;
	private String checkBoxSelectedStudIds;
    protected String inquiryStatus="P";
    protected AcademicYear academicYear;
    protected Customer customer;
    @Column(length = 10000)
    public String getMessageDescription() {
		return messageDescription;
	}
	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	 public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "inquiryStatus",nullable = true, length = 1,columnDefinition="char(1) default 'P'")
	public String getInquiryStatus() {
		return inquiryStatus;
	}
	public void setInquiryStatus(String inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}
	public long getSenderAccountId() {
		return senderAccountId;
	}
	public void setSenderAccountId(long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	@OneToOne
    @JoinColumn(name = "custId", insertable = true, updatable = true)
    public Customer getCustomer() {
	return customer;
    }
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    /** @param customer
     *            the customer to set */

	public long getReceiverAccountId() {
		return receiverAccountId;
	}
	public void setReceiverAccountId(long receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public int getSentSms() {
		return sentSms;
	}
	public void setSentSms(int sentSms) {
		this.sentSms = sentSms;
	}
	public int getSmsCount() {
		return smsCount;
	}
	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	
	public String getCheckBoxSelectedStudIds() {
		return checkBoxSelectedStudIds;
	}
	public void setCheckBoxSelectedStudIds(String checkBoxSelectedStudIds) {
		this.checkBoxSelectedStudIds = checkBoxSelectedStudIds;
	}
	
	@OneToOne
    @JoinColumn(name = "academicYearId", insertable = true, updatable = true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transient
    public String getMessageDateStr() {
	return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.messageDate);

    }
	@Transient
    public String getSenderNameStr() {
		if(this.getCreatedById()>0){
			return this.getSenderName()+"<br/>"+"("+this.getRoleName()+")";
		}else
			return "System Generated";

    }
}

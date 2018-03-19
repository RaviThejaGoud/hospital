/**
 * 
 */
package com.urt.persistence.model.common;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author sunanda
 *
 */
@Entity
@Table(name = "paidSMS")
public class PaidSMS extends PersistentObject {
	
	private static final long serialVersionUID = 1L;
	
	
	private long paidSms;
	private Double paidSmsAmount;
    private Date paidDate;
    private String status;
    private long custId;
    private long academicYearId;
    private String bankRefNo;
    private String trackingId;
    private String paymentMode;
    private String cardName;
     
    
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public long getPaidSms() {
		return paidSms;
	}

	public void setPaidSms(long paidSms) {
		this.paidSms = paidSms;
	}

	public Double getPaidSmsAmount() {
		return paidSmsAmount;
	}

	public void setPaidSmsAmount(Double paidSmsAmount) {
		this.paidSmsAmount = paidSmsAmount;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getBankRefNo() {
		return bankRefNo;
	}

	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
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
	@Override
	public int compareTo(Object object) {
		return 0;
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
	public String toString() {
		return "";
	}
	@Transient
	public String getPaidDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getPaidDate());
	}

}

package com.urt.persistence.model.hostel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "visitors")
public class Visitors extends PersistentObject {

    private static final long serialVersionUID = 1L;


    public Visitors() {

    }

    public Visitors(long id) {
	setId(id);
    }

    private String visitorName;
    private String relation;
    private String reason;
    private long custId;
    private Date inDate;
    private Date outDate;
    protected String outTime;
    protected String inTime;
    protected long accountId;
    private AcademicYear academicYear;
    
    
    
    
    /**
	 * @return the academicYear
	 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academicYearId", insertable = true, updatable = true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
     * @return the relation
     */
    public String getRelation() {
        return relation;
    }

    /**
     * @param relation the relation to set
     */
    public void setRelation(String relation) {
        this.relation = relation;
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

    /**
     * @return the inDate
     */
    public Date getInDate() {
        return inDate;
    }

    /**
     * @param inDate the inDate to set
     */
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    /**
     * @return the outDate
     */
    public Date getOutDate() {
        return outDate;
    }

    /**
     * @param outDate the outDate to set
     */
    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    /**
     * @return the outTime
     */
    public String getOutTime() {
        return outTime;
    }

    /**
     * @param outTime the outTime to set
     */
    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    /**
     * @return the inTime
     */
    public String getInTime() {
        return inTime;
    }

    /**
     * @param inTime the inTime to set
     */
    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    
    /**
     * @return the visitorName
     */
    public String getVisitorName() {
	return visitorName;
    }

    /**
     * @param visitorName
     *            the visitorName to set
     */
    public void setVisitorName(String visitorName) {
	this.visitorName = visitorName;
    }


    /**
     * @return the customer name.
     */

    @Override
    public String toString() {
	return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
		.append("id", this.getId()).toString();
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
    @Transient
    public String getOutDateStr() {
	return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getOutDate());
    }

    @Transient
    public String getInDateStr() {
	return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getInDate());
    }
}

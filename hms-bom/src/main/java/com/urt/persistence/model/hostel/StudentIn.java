package com.urt.persistence.model.hostel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "studentIn")
public class StudentIn extends PersistentObject {

    private static final long serialVersionUID = 1L;

    public StudentIn() {
    }

    public StudentIn(long id) {
	setId(id);
    }

    private Long custId;
    private Date inDate;
    protected String inTime;
    private Long outTimeId;

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
     * @return the outTimeId
     */
    public Long getOutTimeId() {
        return outTimeId;
    }

    /**
     * @param outTimeId the outTimeId to set
     */
    public void setOutTimeId(Long outTimeId) {
        this.outTimeId = outTimeId;
    }

    /**
     * @return the custId
     */
    public Long getCustId() {
	return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(Long custId) {
	this.custId = custId;
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

}

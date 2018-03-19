package com.urt.persistence.model.library;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "fineDetails")
public class FineDetails  extends PersistentObject {
	
	private static final long serialVersionUID = 1L;

	public FineDetails() {
    }
    public FineDetails(long id) {
        setId(id);
    }

	    
	private long issueBookId;
	private long custId;
	private Date returnDate;
	private int fineAmmount;
	private String status;
	 
	
	/**
	 * @return the status
	 */
	@Column(name = "status", nullable = true, length = 40)
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the issueBookId
	 */
	@Column(name = "issueBookId", nullable = true, length = 20)
	public long getIssueBookId() {
		return issueBookId;
	}
	/**
	 * @param issueBookId the issueBookId to set
	 */
	public void setIssueBookId(long issueBookId) {
		this.issueBookId = issueBookId;
	}
	/**
	 * @return the custId
	 */
	@Column(name = "custId", nullable = true, length = 20)
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
	 * @return the returnDate
	 */
	@Column(name = "returnDate", nullable = true )
	public Date getReturnDate() {
		return returnDate;
	}
	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	/**
	 * @return the fineAmmount
	 */
	@Column(name = "fineAmmount", nullable = true, length = 20)
	public int getFineAmmount() {
		return fineAmmount;
	}
	/**
	 * @param fineAmmount the fineAmmount to set
	 */
	public void setFineAmmount(int fineAmmount) {
		this.fineAmmount = fineAmmount;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
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

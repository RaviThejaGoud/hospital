package com.urt.persistence.model.library;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "issuedBook")
public class IssuedBook  extends PersistentObject {
	
    /*private static final long serialVersionUID = 3832626162173359411L;*/
    
    private static final long serialVersionUID = 1L;

    public IssuedBook() {
    }
    public IssuedBook(long id) {
        setId(id);
    }
    private BookTitle bookTitle;
    private long custId;
    private User user; 
    private String status;
    private Date issuedDate;
    private Date dueDate;
    private BookLables bookLable;
    private Date submitedDate;
    private int fineAmount;
    private String userStatus;
    private String renewalStatus;
    private String approvedStatus;
    private long classId;
    private int paidFineAmount;
    private String fineExemption;
    
    
    
   
    public String getFineExemption() {
		return fineExemption;
	}
	public void setFineExemption(String fineExemption) {
		this.fineExemption = fineExemption;
	}
	public int getPaidFineAmount() {
		return paidFineAmount;
	}
	public void setPaidFineAmount(int paidFineAmount) {
		this.paidFineAmount = paidFineAmount;
	}
	@Column(name = "classId", nullable = false, columnDefinition="int default 0")
    public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="accountId")
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	 
	/**
	 * @return the fineAmount
	 */
    @Column(name = "fineAmount", nullable = true)
	public int getFineAmount() {
		return fineAmount;
	}
	/**
	 * @param fineAmount the fineAmount to set
	 */
	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}
	/**
	 * @return the submitedDate
	 */
    @Column(name = "submitedDate", nullable = true)
	public Date getSubmitedDate() {
		return submitedDate;
	}
	/**
	 * @param submitedDate the submitedDate to set
	 */
	public void setSubmitedDate(Date submitedDate) {
		this.submitedDate = submitedDate;
	}
	/**
	 * @return the bookTitle
	 */
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="bookId")
	public BookTitle getBookTitle() {
		return bookTitle;
	}
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(BookTitle bookTitle) {
		this.bookTitle = bookTitle;
	}
	 
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="bookLableId")
	public BookLables getBookLable() {
		return bookLable;
	}
	/**
	 * @param bookLable the bookLable to set
	 */
	public void setBookLable(BookLables bookLable) {
		this.bookLable = bookLable;
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
	 * @return the status
	 */
	@Column(name = "status", nullable = true, length = 20)
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
	 * @return the issuedDate
	 */
	@Column(name = "issuedDate", nullable = true)
	public Date getIssuedDate() {
		return issuedDate;
	}
	/**
	 * @param issuedDate the issuedDate to set
	 */
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	/**
	 * @return the dueDate
	 */
	@Column(name = "dueDate", nullable = true)
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name = "userStatus", nullable = true, length = 20)
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	@Column(name = "renewalStatus", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getRenewalStatus() {
		return renewalStatus;
	}
	public void setRenewalStatus(String renewalStatus) {
		this.renewalStatus = renewalStatus;
	}
	@Column(name = "approvedStatus", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
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
	@Transient
	public String getIssuedDateStr() {
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getIssuedDate());
	}
	@Transient
	public String getDueDateStr() {
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getDueDate());
	}
	@Transient
	public int getBetweenDueDays() {
		Date date=new Date();
		int fineDueDays=DateFunctions.daysBetween(getDueDate(),date);
		return fineDueDays;
		/*if(fineDueDays>=0 && feeDueDays <=14){
			return "A";
		}*/
	}
}

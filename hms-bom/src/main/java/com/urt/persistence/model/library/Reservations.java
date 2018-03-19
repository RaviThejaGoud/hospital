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
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "reservations")
public class Reservations  extends PersistentObject {
	
	
	private static final long serialVersionUID = 1L;

	 public Reservations() {
	 }
	 public Reservations(long id) {
		 setId(id);
	 }
	   
	 private long custId; 
	 private User user; 
	 private Date expiryDate;
	 private String status;
	 private String bookReservationNo;
	 private BookTitle bookTitle;
	 private BookLables bookLable;
	 private String userStatus;
	 
	 
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="accountId")
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	 
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
	 /**
	 * @return the bookReservationNo
	 */
	@Column(name = "bookReservationNo", nullable = true, length = 120)
	public String getBookReservationNo() {
		return bookReservationNo;
	}
	/**
	 * @param bookReservationNo the bookReservationNo to set
	 */
	public void setBookReservationNo(String bookReservationNo) {
		this.bookReservationNo = bookReservationNo;
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
	 
	@Column(name = "userStatus", nullable = true, length = 20)
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
	}
	/**
	 * @return the expiryDate
	 */
	@Column(name = "expiryDate", nullable = true)
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	public String getExpiryDateStr() {
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getExpiryDate());
	}
}

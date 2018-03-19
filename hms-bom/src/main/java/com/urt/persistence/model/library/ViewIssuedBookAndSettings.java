package com.urt.persistence.model.library;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "vw_issuedBookAndSettings")
public class ViewIssuedBookAndSettings implements Serializable,Cloneable,Comparable {
	
    private static final long serialVersionUID = 1L;

    public ViewIssuedBookAndSettings() {
		super();
	}
    public ViewIssuedBookAndSettings(long custId) {
        setCustId(custId);
    } 
    
    
    private String name;
    private String author;
    private String publisher;
    private String bookName;
    private String username;
    private String type;
    private String deleteStatus;
    private String bookLabelStatus; //Closed or Opend
    private String status; //Pending or Submitted
    private String lableCode;
    private String otherSubjects;
    private Date issuedDate;
    private Date dueDate;
    private Date submitedDate;
    private int fineAmount;
    private long issuedBookId;
    private long accountId;
    private long custId;
    private int fineAmountPerDay; 
    private int limitDays; 
    private int noOfStudentIssuBooks;
    private int noOfStaffIssuBooks;
    private long lableCodeId;
    private long bookTitleId;
    private long classId;
    private String userStatus;//staff or student
    private long academicYearId;
    private int paidFineAmount;
    private String fineExemption;
    private long subjectId;
    private String admissionNumber;
    
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId; 
	}
	public int getPaidFineAmount() {
		return paidFineAmount;
	}
	public void setPaidFineAmount(int paidFineAmount) {
		this.paidFineAmount = paidFineAmount;
	}
	public String getFineExemption() {
		return fineExemption;
	}
	public void setFineExemption(String fineExemption) {
		this.fineExemption = fineExemption;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
    public long getBookTitleId() {
		return bookTitleId;
	}
	public void setBookTitleId(long bookTitleId) {
		this.bookTitleId = bookTitleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getBookLabelStatus() {
		return bookLabelStatus;
	}
	public void setBookLabelStatus(String bookLabelStatus) {
		this.bookLabelStatus = bookLabelStatus;
	}
	@Column(name = "otherSubjects", nullable = true, length = 200)
	public String getOtherSubjects() {
		return otherSubjects;
	}
	public void setOtherSubjects(String otherSubjects) {
		this.otherSubjects = otherSubjects;
	}
    /**
	 * @return the name
	 */
	 
	public String getUsername() {
		return username;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Id
	@Column( name="issuedBookId", unique=false, nullable=false, updatable=false )
	
	public long getIssuedBookId() {
		return issuedBookId;
	}
	public void setIssuedBookId(long issuedBookId) {
		this.issuedBookId = issuedBookId;
	}
	
	 
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public long getLableCodeId() {
		return lableCodeId;
	}
	public void setLableCodeId(long lableCodeId) {
		this.lableCodeId = lableCodeId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "lableCode", nullable = true, length = 150)
	public String getLableCode() {
		return lableCode;
	}
	
	/**
	 * @param lableCode the lableCode to set
	 */
	public void setLableCode(String lableCode) {
		this.lableCode = lableCode;
	}
	 /**
	 * @return the bookName
	 */
    @Column(name = "bookName", nullable = true, length = 256)
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	 
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	 
	public Date getSubmitedDate() {
		return submitedDate;
	}
	public void setSubmitedDate(Date submitedDate) {
		this.submitedDate = submitedDate;
	}
	public int getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}
	public int getFineAmountPerDay() {
		return fineAmountPerDay;
	}
	public void setFineAmountPerDay(int fineAmountPerDay) {
		this.fineAmountPerDay = fineAmountPerDay;
	}
	public int getLimitDays() {
		return limitDays;
	}
	public void setLimitDays(int limitDays) {
		this.limitDays = limitDays;
	}
	 


	public int getNoOfStudentIssuBooks() {
		return noOfStudentIssuBooks;
	}
	public void setNoOfStudentIssuBooks(int noOfStudentIssuBooks) {
		this.noOfStudentIssuBooks = noOfStudentIssuBooks;
	}
	public int getNoOfStaffIssuBooks() {
		return noOfStaffIssuBooks;
	}
	public void setNoOfStaffIssuBooks(int noOfStaffIssuBooks) {
		this.noOfStaffIssuBooks = noOfStaffIssuBooks;
	}
	
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getIssuedBookId())
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
	public ViewIssuedBookAndSettings( long custId,  String bookName,
			int fineAmountPerDay, int noOfStudentIssuBooks,int noOfStaffIssuBooks) {
		super();
		this.bookName = bookName;
		this.custId = custId;
		this.fineAmountPerDay = fineAmountPerDay;
		this.noOfStaffIssuBooks = noOfStaffIssuBooks;
		this.noOfStudentIssuBooks = noOfStudentIssuBooks;
		//this.bookLabelStatus = bookLabelStatus;
		
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
	}
	
	@Transient
	public int getFine() {
		Date date=new Date();
		int fineDueDays=DateFunctions.daysBetween(getDueDate(),date);
		int fine=getFineAmountPerDay()*fineDueDays;
		return fine;
	}
	
	@Transient
	public String getSubmitedDateStr() {
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getSubmitedDate());
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	
	
	
}

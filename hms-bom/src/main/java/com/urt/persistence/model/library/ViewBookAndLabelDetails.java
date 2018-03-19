package com.urt.persistence.model.library;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "vw_bookAndLabelDetails")
public class ViewBookAndLabelDetails implements Serializable,Cloneable,Comparable {
	
    private static final long serialVersionUID = 1L;

    public ViewBookAndLabelDetails() {
		super();
	}
    public ViewBookAndLabelDetails(long id) {
        setCustId(id);
    } 
    
    private String name;
    private String author;
    private String publisher;
    private String bookName;
    private String type;
    private String deleteStatus;
    private String bookLabelStatus; //Closed or Opend
    private String lableCode;
    private String otherSubjects;
    private long custId;
    private int fineAmountPerDay; 
    private int limitDays; 
    private int noOfStudentIssuBooks;
    private int noOfStaffIssuBooks;
    private long bookTitleId;
    private long readingBookCount;
    private long issueBookCount;
    private long noOfCopies;
    private long bookLableId;
    private String className;
    private String sections;
    private String bookKeyWord;
    private String bookPublishedDate;
    private String yearOfPublication;
    private String callNo;
    private String classNo;
    private String billNo;
    private String noOfPages;
    private String bookEdition;
    private String place;
    private String bookRemarks;
    private String language;
    private String acquisitionNumber;
    private String bookTitleStatus;
    
    
    
    public String getBookTitleStatus() {
		return bookTitleStatus;
	}
	public void setBookTitleStatus(String bookTitleStatus) {
		this.bookTitleStatus = bookTitleStatus;
	}
	public String getBookKeyWord() {
		return bookKeyWord;
	}
	public void setBookKeyWord(String bookKeyWord) {
		this.bookKeyWord = bookKeyWord;
	}
	public String getBookPublishedDate() {
		return bookPublishedDate;
	}
	public void setBookPublishedDate(String bookPublishedDate) {
		this.bookPublishedDate = bookPublishedDate;
	}
	public String getYearOfPublication() {
		return yearOfPublication;
	}
	public void setYearOfPublication(String yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}
	public String getCallNo() {
		return callNo;
	}
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(String noOfPages) {
		this.noOfPages = noOfPages;
	}
	public String getBookEdition() {
		return bookEdition;
	}
	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getBookRemarks() {
		return bookRemarks;
	}
	public void setBookRemarks(String bookRemarks) {
		this.bookRemarks = bookRemarks;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getAcquisitionNumber() {
		return acquisitionNumber;
	}
	public void setAcquisitionNumber(String acquisitionNumber) {
		this.acquisitionNumber = acquisitionNumber;
	}
	public String getSections() {
		return sections;
	}
	public void setSections(String sections) {
		this.sections = sections;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Id
    public long getBookLableId() {
		return bookLableId;
	}
	public void setBookLableId(long bookLableId) {
		this.bookLableId = bookLableId;
	}
	public long getReadingBookCount() {
		return readingBookCount;
	}
	public void setReadingBookCount(long readingBookCount) {
		this.readingBookCount = readingBookCount;
	}
	public long getIssueBookCount() {
		return issueBookCount;
	}
	public void setIssueBookCount(long issueBookCount) {
		this.issueBookCount = issueBookCount;
	}
	public long getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(long noOfCopies) {
		this.noOfCopies = noOfCopies;
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
        .append("id", this.getBookTitleId())
        .toString();
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
	public ViewBookAndLabelDetails(long custId, String bookName,
			int fineAmountPerDay, int noOfStudentIssuBooks,int noOfStaffIssuBooks) {
		super();
		this.bookName = bookName;
		this.custId = custId;
		this.fineAmountPerDay = fineAmountPerDay;
		this.noOfStaffIssuBooks = noOfStaffIssuBooks;
		this.noOfStudentIssuBooks = noOfStudentIssuBooks;
	}
}

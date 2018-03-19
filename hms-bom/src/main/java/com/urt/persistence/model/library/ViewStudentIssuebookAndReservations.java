package com.urt.persistence.model.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "vw_studentissuebookandreservations")
public class ViewStudentIssuebookAndReservations implements Serializable,Cloneable,Comparable {
	
    private static final long serialVersionUID = 1L;

    public ViewStudentIssuebookAndReservations() {
		super();
	}
    public ViewStudentIssuebookAndReservations(long id) {
        setCustId(id);
    } 
    
    private String lableCode;
    private String subjectName;
    private long bookTitleId;
    private long bookLableId;
    private String bookName;
    private String author;
    private String bookKeyword;
    private String publisher; //Closed or Opend
    private String otherSubjects;
    private String status;
    private long classId;
    private long subjectId;
    private long academicYearId; 
    private String booklabelStatus; 
    private String deleteStatus;
    private String type;
    private Date dueDate;
    private Date issuedDate;
    private String issueBookStatus;
    private long ibAccountId;
    private long bookReservationNo;
    private String reservationStatus;
    private long rsAccountId;
    private Date reservationExpiryDate;
    private Date reservationCreatedDate;
    private long custId;
    protected List booksAvaliableList;
    protected List readingBooks;
    private String blockName;
    private String rackName;
    private String acquisitionNumber;
    private String bookEdition;
    private int noOfCopies;
    private long issuedBooksCount;
   
    
    
    
    @Transient
    public long getIssuedBooksCount() {
		return issuedBooksCount;
	}
	public void setIssuedBooksCount(long issuedBooksCount) {
		this.issuedBooksCount = issuedBooksCount;
	}
    public String getAcquisitionNumber() {
		return acquisitionNumber;
	}
	public void setAcquisitionNumber(String acquisitionNumber) {
		this.acquisitionNumber = acquisitionNumber;
	}
	public String getBookEdition() {
		return bookEdition;
	}
	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getRackName() {
		return rackName;
	}
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}
	@Id
	public String getLableCode() {
		return lableCode;
	}
	public void setLableCode(String lableCode) {
		this.lableCode = lableCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public long getBookTitleId() {
		return bookTitleId;
	}
	public void setBookTitleId(long bookTitleId) {
		this.bookTitleId = bookTitleId;
	}
	public long getBookLableId() {
		return bookLableId;
	}
	public void setBookLableId(long bookLableId) {
		this.bookLableId = bookLableId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBookKeyword() {
		return bookKeyword;
	}
	public void setBookKeyword(String bookKeyword) {
		this.bookKeyword = bookKeyword;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="classId", nullable=false, length=20, unique=false, columnDefinition="int default 0")
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	@Column(name="subjectId", nullable=false, length=20, unique=false, columnDefinition="int default 0")
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getBooklabelStatus() {
		return booklabelStatus;
	}
	public void setBooklabelStatus(String booklabelStatus) {
		this.booklabelStatus = booklabelStatus;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getIssueBookStatus() {
		return issueBookStatus;
	}
	public void setIssueBookStatus(String issueBookStatus) {
		this.issueBookStatus = issueBookStatus;
	}
	public Date getReservationCreatedDate() {
		return reservationCreatedDate;
	}
	public void setReservationCreatedDate(Date reservationCreatedDate) {
		this.reservationCreatedDate = reservationCreatedDate;
	}
	
	@Column(name="ibAccountId", nullable=false, length=20, unique=false, columnDefinition="int default 0")
	public long getIbAccountId() {
		return ibAccountId;
	}
	public void setIbAccountId(long ibAccountId) {
		this.ibAccountId = ibAccountId;
	}
	public long getBookReservationNo() {
		return bookReservationNo;
	}
	@Column(name="bookReservationNo", nullable=false, length=20, unique=false, columnDefinition="int default 0")
	public void setBookReservationNo(long bookReservationNo) {
		this.bookReservationNo = bookReservationNo;
	}
	public String getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	@Column(name="rsAccountId", nullable=false, length=20, unique=false, columnDefinition="int default 0")
	public long getRsAccountId() {
		return rsAccountId;
	}
	public void setRsAccountId(long rsAccountId) {
		this.rsAccountId = rsAccountId;
	}
	public Date getReservationExpiryDate() {
		return reservationExpiryDate;
	}
	public void setReservationExpiryDate(Date reservationExpiryDate) {
		this.reservationExpiryDate = reservationExpiryDate;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getOtherSubjects() {
		return otherSubjects;
	}
	public void setOtherSubjects(String otherSubjects) {
		this.otherSubjects = otherSubjects;
	}
	@Transient
	public List getBooksAvaliableList() {
		if(ObjectFunctions.isNullOrEmpty(this.booksAvaliableList)){
			this.booksAvaliableList=new ArrayList();
		}
		return booksAvaliableList;
	}
	public void setBooksAvaliableList(List booksAvaliableList) {
		this.booksAvaliableList = booksAvaliableList;
	}
	
	@Transient
	public List getReadingBooks() {
		if(ObjectFunctions.isNullOrEmpty(this.readingBooks)){
			this.readingBooks=new ArrayList();
		}
		return readingBooks;
	}
	public void setReadingBooks(List readingBooks) {
		this.readingBooks = readingBooks;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getLableCode())
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
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
}

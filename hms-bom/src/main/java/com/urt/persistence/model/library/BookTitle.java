package com.urt.persistence.model.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.StudySubject;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.MappedExcelObject;
import com.urt.util.excel.parser.ParseType;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "bookTitle")
@ExcelObject(parseType = ParseType.ROW, start = 4, end = 11)
public class BookTitle  extends PersistentObject {
	
    private static final long serialVersionUID = 1L;

    public BookTitle() {
		super();
	}
    public BookTitle(long id) {
        setId(id);
    }
    @ExcelField(position = 1)
	private String bookTitleId;
	
    @MappedExcelObject
    private StudySubject studySubject;
    @MappedExcelObject
    private ClassName className;
    @ExcelField(position = 4)
    private String sections;
    @ExcelField(position = 5)
    private String bookName;
    @ExcelField(position = 6)
    private String bookLableCode;
    @ExcelField(position = 7)
    private String bookKeyWord;
    @ExcelField(position = 8)
    private String author;
    @ExcelField(position = 9)
    private String publisher;
    @ExcelField(position = 10)
    private String noOfCopiesStr;
    @ExcelField(position = 11)
    private String costStr;
    @ExcelField(position = 12)
    private String issueBooksCountStr;
    @ExcelField(position = 13)
    private String issueDaysStr;
    @ExcelField(position = 14)
     private String otherSubjects;
    @ExcelField(position = 15)
    private String bookPublishedDateStr;
    @ExcelField(position = 16)
    private String yearOfPublication;
    @ExcelField(position = 17)
    private String callNo;
    @ExcelField(position = 18)
    private String classNo;  
    @ExcelField(position = 19)
    private String bookEdition;  
    @ExcelField(position = 20)
    private String noOfPages;  
    @ExcelField(position = 21)
    private String billNo;  
    @ExcelField(position = 22)
    private String place;  
    @ExcelField(position = 23)
    private String bookRemarks;  
    @ExcelField(position = 24)
    private String language;
    
    private int noOfCopies;
    private int cost;
    private int totalCost;
    private int readingBookCount;
    private int issueBookCount;
    private int issueDays;
    private int bookRequestExpareDays;
    private long custId;
    private String status; 
   
    
    protected List<BookLables> bookLablesList;
    protected List booksAvaliableList;
    protected List readingBooks; 
    private BookAndBlockDetails bookAndBlockDetails;
    private String readingBooksCountStr;
    private String bookRequestExpiryDaysStr;
    protected AcademicYear academicYear;
    
    /** This is for library new changes for upload */
    private Date bookPublishedDate;
    @ExcelField(position = 25)
    private String acquisitionNumber;
    
    private long issuedBooksCount;
   
    
    
    
    @Transient
    public long getIssuedBooksCount() {
		return issuedBooksCount;
	}
	public void setIssuedBooksCount(long issuedBooksCount) {
		this.issuedBooksCount = issuedBooksCount;
	}
	@Transient
    public String getBookTitleId() {
		return bookTitleId;
	}
	public void setBookTitleId(String bookTitleId) {
		this.bookTitleId = bookTitleId;
	}
	public String getAcquisitionNumber() {
		return acquisitionNumber;
	}
	public void setAcquisitionNumber(String acquisitionNumber) {
		this.acquisitionNumber = acquisitionNumber;
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
	 
	public Date getBookPublishedDate() {
		return bookPublishedDate;
	}
	public void setBookPublishedDate(Date bookPublishedDate) {
		this.bookPublishedDate = bookPublishedDate;
	}
	public String getYearOfPublication() {
		return yearOfPublication;
	}
	public void setYearOfPublication(String yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}
	@Transient
	public String getIssueBooksCountStr() {
		if(StringFunctions.isNullOrEmpty(issueBooksCountStr)){
			return "0";
		}
		return issueBooksCountStr;
	}
	public void setIssueBooksCountStr(String issueBooksCountStr) {
		this.issueBooksCountStr = issueBooksCountStr;
	}
	@Transient
	public String getNoOfCopiesStr() {
		if(StringFunctions.isNullOrEmpty(noOfCopiesStr)){
			return "0";
		}
		return noOfCopiesStr;
	}
	public void setNoOfCopiesStr(String noOfCopiesStr) {
		this.noOfCopiesStr = noOfCopiesStr;
	}
	@Transient
	public String getCostStr() {
		if(StringFunctions.isNullOrEmpty(costStr)){
			return "0";
		}
		return costStr;
	}
	public void setCostStr(String costStr) {
		this.costStr = costStr;
	}
	@Transient
	public String getReadingBooksCountStr() {
		if(StringFunctions.isNullOrEmpty(readingBooksCountStr)){
			return "0";
		}
		return readingBooksCountStr;
	}
	public void setReadingBooksCountStr(String readingBooksCountStr) {
		this.readingBooksCountStr = readingBooksCountStr;
	}
	@Transient
	public String getIssueDaysStr() {
		if(StringFunctions.isNullOrEmpty(issueDaysStr)){
			return "0";
		}
		return issueDaysStr;
	}
	public void setIssueDaysStr(String issueDaysStr) {
		this.issueDaysStr = issueDaysStr;
	}
	@Transient
	public String getBookRequestExpiryDaysStr() {
		if(StringFunctions.isNullOrEmpty(bookRequestExpiryDaysStr)){
			return "0";
		}
		return bookRequestExpiryDaysStr;
	}
	public void setBookRequestExpiryDaysStr(String bookRequestExpiryDaysStr) {
		this.bookRequestExpiryDaysStr = bookRequestExpiryDaysStr;
	}
	
	public String getSections() {
		return sections;
	}
	public void setSections(String sections) {
		this.sections = sections;
	}
	public String getOtherSubjects() {
		return otherSubjects;
	}
	public void setOtherSubjects(String otherSubjects) {
		this.otherSubjects = otherSubjects;
	}
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="subjectId", columnDefinition="int default 0")
    public StudySubject getStudySubject() {
		return studySubject;
	}
	public void setStudySubject(StudySubject studySubject) {
		this.studySubject = studySubject;
	}
	/**
     * @return the academicYear
     */
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	/*@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(this.academicYear))
			return 0;
		else
			return this.academicYear.getId();
	}*/
	
    @Column(name = "bookRequestExpareDays", nullable = true, length = 30)  
    public int getBookRequestExpareDays() {
		return bookRequestExpareDays;
	}
	
	public void setBookRequestExpareDays(int bookRequestExpareDays) {
		this.bookRequestExpareDays = bookRequestExpareDays;
	}
	@OneToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="classId",columnDefinition="int default 0")
	public ClassName getClassName() {
		return className;
	}
	public void setClassName(ClassName className) {
		this.className = className;
	}
	/**
	 * @return the bookKeyWord
	 */
    @Column(name = "bookKeyWord", nullable = true, length = 100)
	public String getBookKeyWord() {
		return bookKeyWord;
	}
	/**
	 * @param bookKeyWord the bookKeyWord to set
	 */
	public void setBookKeyWord(String bookKeyWord) {
		this.bookKeyWord = bookKeyWord;
	}
	/**
	 * @return the bookLables
	 */
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="bookTitleId")
    public List<BookLables> getBookLablesList()
    {
           if(ObjectFunctions.isNullOrEmpty(this.bookLablesList))
           {
                       this.bookLablesList=new ArrayList<BookLables>();
           }
              return this.bookLablesList;
     }
	public void setBookLablesList(List<BookLables> bookLablesList) {
		this.bookLablesList = bookLablesList;
	}
	/**
	 * @return the status
	 */
	@Column(name = "status", nullable = true, length =2, columnDefinition="char(2) default 'Y'")
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
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the noOfCopies
	 */
	@Column(name = "noOfCopies", nullable = true, length = 20)
	public int getNoOfCopies() {
		return noOfCopies;
	}

	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	/**
	 * @return the cost
	 */
	@Column(name = "cost", nullable = true, length = 20)
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the totalCost
	 */
	@Column(name = "totalCost", nullable = true, length = 20)
	public int getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the readingBookCount
	 */
	@Column(name = "readingBookCount", nullable = true, length = 20)
	public int getReadingBookCount() {
		return readingBookCount;
	}

	/**
	 * @param readingBookCount the readingBookCount to set
	 */
	public void setReadingBookCount(int readingBookCount) {
		this.readingBookCount = readingBookCount;
	}

	/**
	 * @return the issueBookCount
	 */
	@Column(name = "issueBookCount", nullable = true, length = 20)
	public int getIssueBookCount() {
		return issueBookCount;
	}

	/**
	 * @param issueBookCount the issueBookCount to set
	 */
	public void setIssueBookCount(int issueBookCount) {
		this.issueBookCount = issueBookCount;
	}

	/**
	 * @return the issueDays
	 */
	public int getIssueDays() {
		return issueDays;
	}

	/**
	 * @param issueDays the issueDays to set
	 */
	public void setIssueDays(int issueDays) {
		this.issueDays = issueDays;
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
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
	}
	
	@Transient
	public String getBookPublishedDateStr() {
		return bookPublishedDateStr;
	}
	public void setBookPublishedDateStr(String bookPublishedDateStr) {
		this.bookPublishedDateStr = bookPublishedDateStr;
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
	public String getBookLableCode() {
		return bookLableCode;
	}
	/**
	 * @param bookLableCode the bookLableCode to set
	 */
	public void setBookLableCode(String bookLableCode) {
		this.bookLableCode = bookLableCode;
	}
	/**
	 * @return the booksAvaliableList
	 */
    @Transient
	public List getBooksAvaliableList() {
		if(ObjectFunctions.isNullOrEmpty(this.booksAvaliableList)){
			this.booksAvaliableList=new ArrayList();
		}
		return this.booksAvaliableList;
	}
	/**
	 * @param booksAvaliableList the booksAvaliableList to set
	 */
	public void setBooksAvaliableList(List booksAvaliableList) {
		this.booksAvaliableList = booksAvaliableList;
	}
	
	/**
	 * @return the readingBooks
	 */
	@Transient
	public List getReadingBooks() {
		if(ObjectFunctions.isNullOrEmpty(this.readingBooks)){
			this.readingBooks=new ArrayList();
		}
		return this.readingBooks;
	}
	/**
	 * @param readingBooks the readingBooks to set
	 */
	public void setReadingBooks(List readingBooks) {
		this.readingBooks = readingBooks;
	}
	/**
	 * @return the bookAndBlockDetails
	 */
  @Transient
	public BookAndBlockDetails getBookAndBlockDetails() {
		return bookAndBlockDetails;
	}
	/**
	 * @param bookAndBlockDetails the bookAndBlockDetails to set
	 */
	public void setBookAndBlockDetails(BookAndBlockDetails bookAndBlockDetails) {
		this.bookAndBlockDetails = bookAndBlockDetails;
	}
	public void addBookLablesSettings(BookLables bookLables) {
        if(!ObjectFunctions.isNullOrEmpty(bookLables)){
                getBookLablesList().add(bookLables);
        }
    }
	
	
	@Transient
	public String getBookLabelCode() {
		if(!ObjectFunctions.isNullOrEmpty(this.bookLablesList))
		{
			BookLables bookLables = this.bookLablesList.get(0);
			if(!ObjectFunctions.isNullOrEmpty(bookLables))
			{
				return bookLables.getLableCode();
			}
		}
		return null;
	}
 }

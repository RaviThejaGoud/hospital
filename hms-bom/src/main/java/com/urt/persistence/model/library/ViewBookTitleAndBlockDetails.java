package com.urt.persistence.model.library;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "vw_bookTitleAndBlockDetails")
public class ViewBookTitleAndBlockDetails  implements Serializable,Cloneable,Comparable {
	
    private static final long serialVersionUID = 1L;

    public ViewBookTitleAndBlockDetails() {
		super();
	}
    public ViewBookTitleAndBlockDetails(long id) {
        setCustId(id);
    } 

    private String bookName;
    private String author;
    private String publisher;
    private int noOfCopies;
    private int cost;
    private int totalCost;
    private int readingBookCount;
    private int issueBookCount;
    private int issueDays;
    private long custId;
    private String name;
    private String otherSubjects;
    private long bookRequestExpareDays;
    private String blockName;
    private String rackName;
    private long bookTitleId;
    protected List booksAvaliableList; 
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOtherSubjects() {
		return otherSubjects;
	}
	public void setOtherSubjects(String otherSubjects) {
		this.otherSubjects = otherSubjects;
	}
	public long getBookRequestExpareDays() {
		return bookRequestExpareDays;
	}
	public void setBookRequestExpareDays(long bookRequestExpareDays) {
		this.bookRequestExpareDays = bookRequestExpareDays;
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
	public long getBookTitleId() {
		return bookTitleId;
	}
	/**
	 * @param bookTitleId the bookTitleId to set
	 */
	public void setBookTitleId(long bookTitleId) {
		this.bookTitleId = bookTitleId;
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
     
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getCustId())
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
	public ViewBookTitleAndBlockDetails(long bookTitleId, long custId, String bookName,
			String author, String publisher, int noOfCopies,
			int cost, int readingBookCount, int issueBookCount, int issueDays, String name) {
		super();
		this.bookTitleId = bookTitleId;
		this.custId = custId;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.noOfCopies = noOfCopies;
		this.cost = cost;
		this.readingBookCount = readingBookCount;
		this.issueBookCount = issueBookCount;
		this.issueDays = issueDays;
		this.name = name;
	}
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
}

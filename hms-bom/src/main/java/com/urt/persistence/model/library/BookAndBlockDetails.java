package com.urt.persistence.model.library;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "bookAndBlockDetails")
public class BookAndBlockDetails  extends PersistentObject {
	
	private static final long serialVersionUID = 1L;

	 private long custId; 
	 private BookTitle bookTitle;
	 private Block block; 
	 private RackDetails rackDetails;
	
    
	 
     

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

	/**
	 * @return the block
	 */
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="blockId")
	public Block getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(Block block) {
		this.block = block;
	}

	/**
	 * @return the rackDetails
	 */
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="rackId")
	public RackDetails getRackDetails() {
		return rackDetails;
	}

	/**
	 * @param rackDetails the rackDetails to set
	 */
	public void setRackDetails(RackDetails rackDetails) {
		this.rackDetails = rackDetails;
	}

	public BookAndBlockDetails() {
        
    }

    public BookAndBlockDetails(long id) {
        setId(id);
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

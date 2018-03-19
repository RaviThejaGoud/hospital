package com.urt.persistence.model.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "block")
public class Block  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;

	 
	 private long custId; 
	 //private long bookId;  this column we are not using now
	 private String status;
	 private String blockName;
	protected AcademicYear academicYear;
    
 
	 
	/**
	 * @return the blockName
	 */
	 @Column(name = "blockName", nullable = true, length = 180)
	 public String getBlockName() {
		return blockName;
	 }
	/**
	 * @param blockName the blockName to set
	 */
	 public void setBlockName(String blockName) {
		 this.blockName = blockName;
	 }
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

	/**
	 * @return the bookId
	 *//*
	 @Column(name = "bookId", nullable = true, length = 20)
	public long getBookId() {
		return bookId;
	}

	*//**
	 * @param bookId the bookId to set
	 *//*
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}*/

	public Block() {
        
    }
    public Block(long id) {
        setId(id);
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
    /**
	 * @return the customer name.
	 */
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
    

  


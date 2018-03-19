package com.urt.persistence.model.library;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "rackDetails")
public class RackDetails  extends PersistentObject {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @return the studentId
	 */
	public RackDetails() {
    }
    public RackDetails(long id) {
        setId(id);
    }
    
    private Block block;
    private String rackName;
    private String type;
    private long custId; 
    private int rackCapacity; 
    protected List<BooksAssignToRack> bookAssignmentList;
    private int booksCount;
    protected AcademicYear academicYear;
    
    /**
	 * @return the rackAssigned books
	 */
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="rackDetailsId")
    public List<BooksAssignToRack> getBookAssignmentList() {
    	 if(ObjectFunctions.isNullOrEmpty(this.bookAssignmentList))
         {
                     this.bookAssignmentList=new ArrayList<BooksAssignToRack>();
         }
		return bookAssignmentList;
	}
	public void setBookAssignmentList(List<BooksAssignToRack> bookAssignmentList) {
		this.bookAssignmentList = bookAssignmentList;
	}
	 
	@Column(name = "type", nullable = true, length = 10)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	 
	@Column(name = "custId", nullable = true, length = 10)
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
	
    @OneToOne
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
	 * @return the rackName
	 */
	public String getRackName() {
		return rackName;
	}
	/**
	 * @param rackName the rackName to set
	 */
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}
	
	@Column(name="rackCapacity", nullable=false, length=10, unique=false, columnDefinition="int default 0")
	public int getRackCapacity() {
		return rackCapacity;
	}
	public void setRackCapacity(int rackCapacity) {
		this.rackCapacity = rackCapacity;
	}
	
	@Column(name="booksCount", nullable=false, length=10, unique=false, columnDefinition="int default 0")
	public int getBooksCount() {
		return booksCount;
	}
	public void setBooksCount(int booksCount) {
		this.booksCount = booksCount;
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
	
	public void addBookAssignments(BooksAssignToRack bookAssignments) {
        if(!ObjectFunctions.isNullOrEmpty(bookAssignments)){
                getBookAssignmentList().add(bookAssignments);
        }
    }
}

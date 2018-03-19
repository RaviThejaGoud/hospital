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
 * @create new table booksAssignToRack.
 */
@Entity
@Table(name = "booksAssignToRack")
public class BooksAssignToRack  extends PersistentObject {
	
    private static final long serialVersionUID = 1L;

    public BooksAssignToRack() {
		super();
	}
    
    private long subjectId;
    private int noOfCopies;
    private AcademicYear academicYear;
    private long custId;
    private long bookTitleId;
            
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
 
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Column(name="subjectId", nullable=false, length=10, unique=false, columnDefinition="int default 0")
	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name="noOfCopies", nullable=false, length=10, unique=false, columnDefinition="int default 0")
	public int getnoOfCopies() {
		return noOfCopies;
	}

	public void setnoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	@Column(name="bookTitleId", nullable=false, length=10, unique=false, columnDefinition="int default 0")
	public long getBookTitleId() {
		return bookTitleId;
	}
	public void setBookTitleId(long bookTitleId) {
		this.bookTitleId = bookTitleId;
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

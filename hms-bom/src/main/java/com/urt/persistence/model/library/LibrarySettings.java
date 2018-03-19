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
@Table(name = "librarySettings")
public class LibrarySettings  extends PersistentObject {
	
	private static final long serialVersionUID = 1L;

	public LibrarySettings() {
        
    }
    public LibrarySettings(long id) {
        setId(id);
    }
    
    
    private int fineAmountPerDay; 
    private int limitDays; 
    private int booksLeftCount;
    private String status;
    private int noOfStudentIssuBooks;
    private int noOfStaffIssuBooks;
    private long custId;
    protected AcademicYear academicYear;
    private int noOfDaysForReuturnStudents;
    private int noOfDaysForReuturnStaff;

    
    
    @Column(name = "custId", nullable = true, length = 20)
    public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
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
	
	@Column(name = "noOfStudentIssuBooks", nullable = true, length = 20)
	public int getNoOfStudentIssuBooks() {
		return noOfStudentIssuBooks;
	}
	public void setNoOfStudentIssuBooks(int noOfStudentIssuBooks) {
		this.noOfStudentIssuBooks = noOfStudentIssuBooks;
	}
	
	@Column(name = "noOfStaffIssuBooks", nullable = true, length = 20)
	public int getNoOfStaffIssuBooks() {
		return noOfStaffIssuBooks;
	}
	public void setNoOfStaffIssuBooks(int noOfStaffIssuBooks) {
		this.noOfStaffIssuBooks = noOfStaffIssuBooks;
	}
	/**
	 * @return the fineAmountPerDay
	 */
    @Column(name = "fineAmountPerDay", nullable = true, length = 20)
	public int getFineAmountPerDay() {
		return fineAmountPerDay;
	}

	/**
	 * @param fineAmountPerDay the fineAmountPerDay to set
	 */
	public void setFineAmountPerDay(int fineAmountPerDay) {
		this.fineAmountPerDay = fineAmountPerDay;
	}

	/**
	 * @return the limitDays
	 */
	@Column(name = "limitDays", nullable = true, length = 20)
	public int getLimitDays() {
		return limitDays;
	}

	/**
	 * @param limitDays the limitDays to set
	 */
	public void setLimitDays(int limitDays) {
		this.limitDays = limitDays;
	}

	/**
	 * @return the booksLeftCount
	 */
	@Column(name = "booksLeftCount", nullable = true, length = 20)
	public int getBooksLeftCount() {
		return booksLeftCount;
	}

	/**
	 * @param booksLeftCount the booksLeftCount to set
	 */
	public void setBooksLeftCount(int booksLeftCount) {
		this.booksLeftCount = booksLeftCount;
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
	@Column(name = "noOfDaysForReuturnStudents", nullable = false, length = 20, columnDefinition="int default 0")
	public int getNoOfDaysForReuturnStudents() {
		return noOfDaysForReuturnStudents;
	}
	public void setNoOfDaysForReuturnStudents(int noOfDaysForReuturnStudents) {
		this.noOfDaysForReuturnStudents = noOfDaysForReuturnStudents;
	}
	@Column(name = "noOfDaysForReuturnStaff", nullable = false, length = 20, columnDefinition="int default 0")
	public int getNoOfDaysForReuturnStaff() {
		return noOfDaysForReuturnStaff;
	}
	public void setNoOfDaysForReuturnStaff(int noOfDaysForReuturnStaff) {
		this.noOfDaysForReuturnStaff = noOfDaysForReuturnStaff;
	}
}

package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

/**
* Attendance entity. @author MyEclipse Persistence Tools
*/
@Entity
@Table(name = "staffMonthlyAttendance") 
public class StaffMonthlyAttendance extends PersistentObject {


	private static final long serialVersionUID = 1L;
	private long accountId;
	private String totalWorkingDays;
	private String noOfPresentDays;
	private int month;
	private String monthName;
	private long academicYearId;
	
	// Constructors

	/** default constructor */
	public StaffMonthlyAttendance() {
	}

	/** minimal constructor */
	public StaffMonthlyAttendance(long id, long accountId) {
		super.setId(id);
		this.accountId = accountId;
	}
	
	@Column(name = "totalWorkingDays", nullable = true, length = 1,columnDefinition = "varchar(5)")
	public String getTotalWorkingDays() {
		return totalWorkingDays;
	}

	public void setTotalWorkingDays(String totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}
	
	 @Column(name = "noOfPresentDays", nullable = true, length = 1,columnDefinition = "varchar(5)")
	public String getNoOfPresentDays() {
		return noOfPresentDays;
	}

	public void setNoOfPresentDays(String noOfPresentDays) {
		this.noOfPresentDays = noOfPresentDays;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
  
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 4572;
		result = prime * result + (int) (accountId ^ (accountId >>> 32)); 
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(null == obj || getClass() != obj.getClass()) {
			return false;		
		} else {
			StaffMonthlyAttendance other = (StaffMonthlyAttendance) obj;
			if (accountId != other.accountId)
				return false; 			
		}		
		return true;
	}
 

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	
	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
     
}
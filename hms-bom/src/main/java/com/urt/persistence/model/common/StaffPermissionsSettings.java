package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Medium.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "staffPermissionsSettings")
public class StaffPermissionsSettings  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private long custId;
    private long academicYearId;
    private String status;
    private String monthOrYear;
    private String isRolebased;

	 
 
	@Column(name = "status", nullable = true, length = 1)
	public String getStatus() {
		return status;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	@Column(name = "monthOrYear", nullable = true, length = 1)
	public String getMonthOrYear() {
		return monthOrYear;
	}

	public void setMonthOrYear(String monthOrYear) {
		this.monthOrYear = monthOrYear;
	}
	
	@Column(name = "isRolebased", nullable = true, length = 1)
	public String getIsRolebased() {
		return isRolebased;
	}

	public void setIsRolebased(String isRolebased) {
		this.isRolebased = isRolebased;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public StaffPermissionsSettings() {
        
    }
    public StaffPermissionsSettings(long id) {
        setId(id);
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
	public String getCreatedDateStr() {
		try{
			return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getCreatedDate());
		}
		catch(Exception ex){
			return "";
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
}


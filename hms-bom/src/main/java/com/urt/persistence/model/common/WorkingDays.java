package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.annotations.Type;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "workingDays")
public class WorkingDays  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	private long academicYearId;
	private long dayId;
	protected boolean halfDay;
	
	@Column(name = "halfDay", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isHalfDay() {
		return halfDay;
	}
	public void setHalfDay(boolean halfDay) {
		this.halfDay = halfDay;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	public long getDayId() {
		return dayId;
	}
	public void setDayId(long dayId) {
		this.dayId = dayId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the academicYear
	 */
	public WorkingDays() {
        
    }
    public WorkingDays(long id) {
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public int compareTo(Object object) {
		WorkingDays days = (WorkingDays) object;
        return new CompareToBuilder().append(this.getId(), days.getId()).toComparison();
	}
}


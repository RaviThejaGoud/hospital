package com.urt.persistence.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "weekDays")
public class WeekDays  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	private String dayName;
	private int sortingOrder;
	
	
	@Column(name = "sortingOrder",nullable = false, columnDefinition=" int default 0")
	public int getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	/**
	 * @return the academicYear
	 */
	public WeekDays() {
        
    }
    public WeekDays(long id) {
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
		WeekDays days = (WeekDays) object;
        return new CompareToBuilder().append(this.getId(), days.getId()).toComparison();
	}
}


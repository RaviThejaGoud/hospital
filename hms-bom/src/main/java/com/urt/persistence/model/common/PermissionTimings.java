package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Medium.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "permissionTimings")
public class PermissionTimings  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
	private String startTime;
	private String endTime;
	private String days;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Column(name = "startTime", nullable = true, length = 20)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Column(name = "endTime", nullable = true, length = 20)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public PermissionTimings() {
        
    }
    public PermissionTimings(long id) {
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
		// TODO Auto-generated method stub
		return 0;
	}
}


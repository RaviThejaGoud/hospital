package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "staffPermissionsDayDetails")
public class StaffPermissionsDayDetails  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private StaffPermissionsSettings staffPermissionsSettings;
    private long roleId;
    private int days;
	 
    
    
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="staffPermissionsSettingsId", insertable=true, updatable=true) 
	public StaffPermissionsSettings getStaffPermissionsSettings() {
		return staffPermissionsSettings;
	}

	public void setStaffPermissionsSettings(
			StaffPermissionsSettings staffPermissionsSettings) {
		this.staffPermissionsSettings = staffPermissionsSettings;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public StaffPermissionsDayDetails() {
        
    }
    public StaffPermissionsDayDetails(long id) {
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


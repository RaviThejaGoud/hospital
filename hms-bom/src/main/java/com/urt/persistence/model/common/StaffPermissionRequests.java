package com.urt.persistence.model.common;

import java.util.Date;

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
@Table(name = "staffPermissionRequests")
public class StaffPermissionRequests  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private long staffId;
    private long academicYearId;
	private String status;
    private Date permissionDate;
    private String startTime;
    private String endTime;
    protected long supervisorId;
    private String comments;
    private String approvalsComment;
    
    
    
    public String getApprovalsComment() {
		return approvalsComment;
	}
	public void setApprovalsComment(String approvalsComment) {
		this.approvalsComment = approvalsComment;
	}
	public long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	@Column(name = "status", nullable = true, length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public Date getPermissionDate() {
		return permissionDate;
	}
	public void setPermissionDate(Date permissionDate) {
		this.permissionDate = permissionDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public StaffPermissionRequests() {
        
    }
    public StaffPermissionRequests(long id) {
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


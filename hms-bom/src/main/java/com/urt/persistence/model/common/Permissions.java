package com.urt.persistence.model.common;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Medium.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "permissions")
public class Permissions  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private long studentId;
    private Date startDate;
    private Date endDate;
    private String status;
    private String comments;
    private String approvalsComment;
    protected long supervisorId;
    protected Set<PermissionTimings> permissionTimings;
    
    
    public void addPermissionTimingDetails(PermissionTimings permissionTimings) {
		if (ObjectFunctions.isNullOrEmpty(getPermissionTimings())) {
			this.permissionTimings = new HashSet<PermissionTimings>();
		}
		getPermissionTimings().add(permissionTimings);
	}
    
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="permissionsId")
    public Set<PermissionTimings> getPermissionTimings() {
		return permissionTimings;
	}
	public void setPermissionTimings(Set<PermissionTimings> permissionTimings) {
		this.permissionTimings = permissionTimings;
	}
	
	public long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	
	@Column(name = "approvalsComment", nullable = true, length = 1024)
	public String getApprovalsComment() {
		return approvalsComment;
	}

	public void setApprovalsComment(String approvalsComment) {
		this.approvalsComment = approvalsComment;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "status", nullable = true, length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "comments", nullable = true, length = 1024)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public Permissions() {
        
    }
    public Permissions(long id) {
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


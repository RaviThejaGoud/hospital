package com.urt.persistence.model.common;

/********************************************************************
 * Copyright (C) 2005-06

 * IFS
 * All Rights Reserved 
 *
 * File: Person.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
 / ********************************************************************/
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "vw_staffPermissionRequests")
public class ViewStaffPermissionRequests implements  java.io.Serializable {
 
	private static final long serialVersionUID = 1L;

	
	private long staffId;
    private String comments;
	private String startTime;
	private String endTime;
	private Date permissionDate;
	private long academicYearId;
	private long custId;
	private String fullName;
	private long permissionRequestId;
	private String status;
	private String approvalsComment;
	

	public String getApprovalsComment() {
		return approvalsComment;
	}
	public void setApprovalsComment(String approvalsComment) {
		this.approvalsComment = approvalsComment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Id
    public long getPermissionRequestId() {
		return permissionRequestId;
	}
	public void setPermissionRequestId(long permissionRequestId) {
		this.permissionRequestId = permissionRequestId;
	}
	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
	public Date getPermissionDate() {
		return permissionDate;
	}
	public void setPermissionDate(Date permissionDate) {
		this.permissionDate = permissionDate;
	}
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

		public long getAcademicYearId() {
			return academicYearId;
		}

		public void setAcademicYearId(long academicYearId) {
			this.academicYearId = academicYearId;
		}

		public long getCustId() {
			return custId;
		}

		public void setCustId(long custId) {
			this.custId = custId;
		}

		@Override
		public boolean equals(Object obj) {
			 if (this == obj)
			   return true;
			 else 
			   return false;
		}
	/**
	 * 
	 */
	public ViewStaffPermissionRequests() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(staffId);
		return buffer.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Transient
	public String getFormattedStartDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.permissionDate);
	}
	@Transient
	public String getPermissionDateStr() {
		return DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, this.permissionDate);
	}
}

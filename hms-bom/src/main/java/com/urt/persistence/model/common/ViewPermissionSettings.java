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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "vw_permissionSettings")
public class ViewPermissionSettings implements Serializable,Cloneable, Comparable {
 
	private static final long serialVersionUID = 1L;

	
	private long studentId;
    private Date startDate;
    private Date endDate;
    private String status;
    private String comments;
    private long permissionsId;
	private String startTime;
	private String endTime;
	private String days;
	private long academicYearId;
	private long custId;
	private long createdById;
	private long lastUpdatedById; 
	private long permissionTimingsId;
	private List permissionTimesList;
	private String classNameAndSection;
	private String fullName;
	private String approvalsComment;
    protected long supervisorId;
	
	
	
	
		 
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

	public String getClassNameAndSection() {
		return classNameAndSection;
	}

	public void setClassNameAndSection(String classNameAndSection) {
		this.classNameAndSection = classNameAndSection;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Transient
	public List getPermissionTimesList() {
		if(ObjectFunctions.isNullOrEmpty(this.permissionTimesList))
		{
			this.permissionTimesList = new ArrayList();
		}
		return permissionTimesList;
	}

	public void setPermissionTimesList(List permissionTimesList) {
		this.permissionTimesList = permissionTimesList;
	}

	@Id
	public long getPermissionTimingsId() {
		return permissionTimingsId;
	}

	public void setPermissionTimingsId(long permissionTimingsId) {
		this.permissionTimingsId = permissionTimingsId;
	}

	public long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(long createdById) {
		this.createdById = createdById;
	}

	public long getLastUpdatedById() {
		return lastUpdatedById;
	}

	public void setLastUpdatedById(long lastUpdatedById) {
		this.lastUpdatedById = lastUpdatedById;
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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		
		public long getPermissionsId() {
			return permissionsId;
		}

		public void setPermissionsId(long permissionsId) {
			this.permissionsId = permissionsId;
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

		public String getDays() {
			return days;
		}

		public void setDays(String days) {
			this.days = days;
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
	public ViewPermissionSettings() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(permissionsId);
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
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Transient
	public String getFormattedStartDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.startDate);
	}
	@Transient
	public String getFormattedEndDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.endDate);
	}
	@Transient
	public String getPermissionDateStr() {
		return DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, this.startDate);
	}
}

package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_StudentClassAssignment") 
public class VWStudentClassAssignment implements java.io.Serializable{
	
	private String studentname;
	private Long custId;
	private Long classSectionId;
	private Date assignmentDate;
	private boolean assignmentDone;
	private String rollNumber;
	private Long studentId;
	private long accountId;
	private long assignmentId;
	private long academicYearId;
	private String subjectName;	
	private long studentAssignmentId;
	private String classAndSection;
	private String mobileNumber;
	private String status;
	
	
	@Id
	@Column(name = "studentAssignmentId", nullable = false)
	public long getStudentAssignmentId() {
		return studentAssignmentId;
	}
	public void setStudentAssignmentId(long studentAssignmentId) {
		this.studentAssignmentId = studentAssignmentId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	} 
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	} 
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
  
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 4572;
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		result = prime * result
				+ ((assignmentDate == null) ? 0 : assignmentDate.hashCode());
		result = prime * result + ((classSectionId == null) ? 0 : classSectionId.hashCode());
		result = prime * result + (assignmentDone ? 1231 : 1237);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (null == obj || getClass() != obj.getClass()) {
			return false;
		} else {
			VWStudentClassAssignment other = (VWStudentClassAssignment) obj;
			if (studentId != other.studentId)
				return false;
			if (assignmentDate == null) {
				if (other.assignmentDate != null)
					return false;
			} else if (!assignmentDate.equals(other.assignmentDate))
				return false;
			
			if (assignmentDone != other.assignmentDone )
				return false;
		}
		
		return true;
	}
	public boolean isAssignmentDone() {
		return assignmentDone;
	}
	public void setAssignmentDone(boolean assignmentDone) {
		this.assignmentDone = assignmentDone;
	}
	public long getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(long assignmentId) {
		this.assignmentId = assignmentId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public void setClassSectionId(Long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}

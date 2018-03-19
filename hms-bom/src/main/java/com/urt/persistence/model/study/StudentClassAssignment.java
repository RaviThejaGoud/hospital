package com.urt.persistence.model.study;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.urt.persistence.model.base.PersistentObject;

/**
* Attendance entity. @author MyEclipse Persistence Tools
*/
@Entity
@Table(name = "studentClassAssignment") 
public class StudentClassAssignment extends PersistentObject {


	private static final long serialVersionUID = 1L;
	private long studentId;
	private boolean assignmentDone;  
	private Date assignmentDate;
	private long assignmentId;
 
	 

	// Constructors

	public long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(long assignmentId) {
		this.assignmentId = assignmentId;
	}

	/** default constructor */
	public StudentClassAssignment() {
	}

	/** minimal constructor */
	public StudentClassAssignment(long id, long studentId) {
		super.setId(id);
		this.studentId = studentId;
	} 
	
	@Column(name = "assignmentDone", nullable = true, length = 1, columnDefinition = "char(1) default 'Y'")
	@Type(type = "yes_no")
	public boolean isAssignmentDone() {
		return assignmentDone;
	}

	public void setAssignmentDone(boolean assignmentDone) {
		this.assignmentDone = assignmentDone;
	}
 

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 4572;
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		result = prime * result
				+ ((assignmentDate == null) ? 0 : assignmentDate.hashCode());
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
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
		if(this == obj){
			return true;
		}else if (null == obj || getClass() != obj.getClass())
			return false;
		else{
			StudentClassAssignment other = (StudentClassAssignment) obj;
			if (studentId != other.studentId )
				return false;
			if (assignmentDate == null) {
				if (other.assignmentDate != null)
					return false;
			} else if (!assignmentDate.equals(other.assignmentDate))
				return false;
		}
		return true;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	
     
}
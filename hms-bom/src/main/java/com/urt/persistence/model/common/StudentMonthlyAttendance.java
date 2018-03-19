package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.urt.persistence.model.base.PersistentObject;

/**
* Attendance entity. @author MyEclipse Persistence Tools
*/
@Entity
@Table(name = "studentMonthlyAttendance") 
public class StudentMonthlyAttendance extends PersistentObject {


	private static final long serialVersionUID = 1L;
	private long studentId;
	private String totalWorkingDays;
	private String noOfPresentDays;
	private int month;
	private String monthName;
	
	
	// for send emails in automatically
	private String studentName;
	private String admissionNumber;
	private double attendancePercentage;
	private String classNameAndSection;
	
	@Transient 
	public String getClassNameAndSection() {
		return classNameAndSection;
	}
	public void setClassNameAndSection(String classNameAndSection) {
		this.classNameAndSection = classNameAndSection;
	}
	@Transient
	public double getAttendancePercentage() {
		return attendancePercentage;
	}
	public void setAttendancePercentage(double attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}
	
	@Transient
	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	@Transient
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/** default constructor */
	public StudentMonthlyAttendance() {
	}

	/** minimal constructor */
	public StudentMonthlyAttendance(long id, long studentId) {
		super.setId(id);
		this.studentId = studentId;
	}
	
	@Column(name = "totalWorkingDays", nullable = true, length = 1,columnDefinition = "varchar(5)")
	public String getTotalWorkingDays() {
		return totalWorkingDays;
	}

	public void setTotalWorkingDays(String totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}
	
	 @Column(name = "noOfPresentDays", nullable = true, length = 1,columnDefinition = "varchar(5)")
	public String getNoOfPresentDays() {
		return noOfPresentDays;
	}

	public void setNoOfPresentDays(String noOfPresentDays) {
		this.noOfPresentDays = noOfPresentDays;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
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
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (null == obj || getClass() != obj.getClass()){
			return false;
		} else {
			StudentMonthlyAttendance other = (StudentMonthlyAttendance) obj;
			if (studentId != other.studentId)
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
     
}
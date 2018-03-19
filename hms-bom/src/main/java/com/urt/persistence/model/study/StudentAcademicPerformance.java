package com.urt.persistence.model.study;
/********************************************************************
 * Copyright (C) 20011-12

 * IFS
 * All Rights Reserved 
 *
 * File: StudentAcademicPerformance.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Dec 12, 2011          Narahari           Created
/ ********************************************************************/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.exam.ExamTypes;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "studentAcademicPerformance")
public class StudentAcademicPerformance extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private String grade;
	private String description;
	//private String category;
	private ExamTypes examType;
	private StudentActivityTypes studentActivityType;
	private long custId;
	private Student student;
	private AcademicYear academicYear;
	private long studentsAssessmentId;
	private String gradeStatus;
	private long classAndSectionId;
	
	/*private int gradePoint;
	

	@Column(name = "gradePoint", nullable = false, updatable = true, columnDefinition = "int default 0")
	public int getGradePoint() {
		return gradePoint;
	}
	public void setGradePoint(int gradePoint) {
		this.gradePoint = gradePoint;
	}
*/	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="examTypeId") 
	public ExamTypes getExamType() {
		return examType;
	}
	public void setExamType(ExamTypes examType) {
		this.examType = examType;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="studentActivityTypeId",nullable=true) 
	public StudentActivityTypes getStudentActivityType() {
		return studentActivityType;
	}
	public void setStudentActivityType(StudentActivityTypes studentActivityType) {
		this.studentActivityType = studentActivityType;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true) 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="studId", insertable=true, updatable=true) 
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public StudentAcademicPerformance() {
		super();
	}
	

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}*/

	@Override
	public boolean equals(Object o) {
		 if (this == o){
			 return true;
		 } else{
			 return false;
		 }
	}

	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);	
		buffer.append(getId()).append(" ");
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
    	 return  0;
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    @Override
	public int compareTo(Object object) {
    	/*StudentMarks target = (StudentMarks) object;
        if (target.getExamType() != null && this.getExamType() != null)
        {
            if(this.getExamType().equalsIgnoreCase(target.getExamType()))
                return 0;
            else 
               return this.getExamType().compareToIgnoreCase(target.getExamType());
                 
        }*/
        return 0;
    }
	public long getStudentsAssessmentId() {
		return studentsAssessmentId;
	}
	public void setStudentsAssessmentId(long studentsAssessmentId) {
		this.studentsAssessmentId = studentsAssessmentId;
	}
	@Column(name = "gradeStatus", nullable = true, length = 1,columnDefinition="char(1) default 'G'")
	public String getGradeStatus() {
		return gradeStatus;
	}
	public void setGradeStatus(String gradeStatus) {
		this.gradeStatus = gradeStatus;
	}
	public long getClassAndSectionId() {
		return classAndSectionId;
	}
	public void setClassAndSectionId(long classAndSectionId) {
		this.classAndSectionId = classAndSectionId;
	}
}

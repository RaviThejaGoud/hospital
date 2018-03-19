package com.urt.persistence.model.study;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "subTypeGrades")
public class SubTypeGrades  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected double minMarks;    
    protected double maxMarks;
    protected String grade;
    protected float gradePoint;
    protected long custId;
    protected long academicYearId;

    
    public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Column(name = "minMarks", columnDefinition = "double default 0.0")
	public double getMinMarks() {
		return minMarks;
	}

	public void setMinMarks(double minMarks) {
		this.minMarks = minMarks;
	}

	@Column(name = "maxMarks", columnDefinition = "double default 0.0")
	public double getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "gradePoint", nullable = false, updatable = true, columnDefinition = "float default 0.0")
	 public float getGradePoint() {
		return gradePoint;
	}

	public void setGradePoint(float gradePoint) {
		this.gradePoint = gradePoint;
	}


	public SubTypeGrades() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

    @Override
	public int compareTo(Object object) {
    	/*FormativeAssessmentGrades target = (FormativeAssessmentGrades) object;
    	if (target != null && this != null)
        {
    		if(this.getSortingOrder() >= target.getSortingOrder())
    			return 1;
    		else
    			return 0;
        }*/
		return 0;
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
	 public void copyFrom(SubTypeGrades obj)
	 {
		setCustId(obj.getCustId());
		setGrade(obj.getGrade());
		setGradePoint(obj.getGradePoint());
		setMaxMarks(obj.getMaxMarks());
		setMinMarks(obj.getMinMarks());
		setCreatedDate(new Date());
		setLastAccessDate(new Date());
	 }
}
    

  


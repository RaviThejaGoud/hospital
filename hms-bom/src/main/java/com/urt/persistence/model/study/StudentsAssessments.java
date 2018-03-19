package com.urt.persistence.model.study;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "StudentsAssessments")
public class StudentsAssessments extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private String assessmentName;
	private String assessmentDescription;
	
	private long custId;
	
	
	private AcademicYear academicYear;
	

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true) 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getAssessmentName() {
		return assessmentName;
	}
	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}
	public String getAssessmentDescription() {
		return assessmentDescription;
	}
	public void setAssessmentDescription(String assessmentDescription) {
		this.assessmentDescription = assessmentDescription;
	}
	@Override
	public boolean equals(Object o) {
		 if (this == o){
			 return true;
		 } else {
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
        return 0;
    }
    public void copyFrom(StudentsAssessments obj)
    {
		setCustId(obj.getCustId());
		setAssessmentDescription(obj.getAssessmentDescription());
		setAssessmentName(obj.getAssessmentName());
		setCreatedDate(new Date());
		setLastAccessDate(new Date());
    }
  }

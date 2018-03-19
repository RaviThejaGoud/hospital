/**
 * 
 */
package com.urt.persistence.model.exam;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/**
 * @author urtuser
 *
 */
@Entity
@Table(name = "activitiesGrades")
public class ActivitiesGrades extends PersistentObject{

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private long custId;
	private String gradeName;
    private float gradePoint;
    protected AcademicYear academicYear;
    
    

	@Column(name = "gradePoint", nullable = false, updatable = true, columnDefinition = "float default 0.0")
	 public float getGradePoint() {
		return gradePoint;
	}

	public void setGradePoint(float gradePoint) {
		this.gradePoint = gradePoint;
	}
    
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId")
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
    /**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	
	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}
	/**
	 * @param gradeName the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		ActivitiesGrades myClass = (ActivitiesGrades) object;
        return new CompareToBuilder().append(this.getId(), myClass.getId()).toComparison();
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
	 public void copyFrom(ActivitiesGrades obj)
	  {
			setCustId(obj.getCustId());
			setGradeName(obj.getGradeName());
			setGradePoint(obj.getGradePoint());
			//setVersion(0);
			setLastAccessDate(new Date());
	  }
}

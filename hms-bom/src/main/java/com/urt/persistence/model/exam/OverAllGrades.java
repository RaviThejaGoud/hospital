package com.urt.persistence.model.exam;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;


@Entity
@Table(name = "overAllGrades")
public class OverAllGrades extends PersistentObject{ 

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private long custId;
	private String gradeName;
    private double maxMarks;
    private double minMarks;
    protected AcademicYear academicYear;
    private String description;
    
    
    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "minMarks", nullable = false, updatable = true, columnDefinition = "double default 0.0")
    public double getMinMarks() {
		return minMarks;
	}
	public void setMinMarks(double minMarks) {
		this.minMarks = minMarks;
	}
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true)
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
	
	@Column(name = "maxMarks", nullable = false, updatable = true, columnDefinition = "double default 0.0")
	public double getMaxMarks() {
		return maxMarks;
	}
	@Override
	public int compareTo(Object object) {
		OverAllGrades myClass = (OverAllGrades) object;
		if(!ObjectFunctions.isNullOrEmpty(myClass) && !ObjectFunctions.isNullOrEmpty(this))
			return this.getGradeName().compareToIgnoreCase(myClass.getGradeName()); 
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
	public void copyFrom(OverAllGrades obj)
	{
			setCustId(obj.getCustId());
			setGradeName(obj.getGradeName());
			setMaxMarks(obj.getMaxMarks());
			setDescription(obj.getDescription());
			setMinMarks(obj.getMinMarks());
			setCreatedDate(new Date());
			setLastAccessDate(new Date());
	}
}

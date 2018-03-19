package com.urt.persistence.model.study;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

@Entity
@Table(name = "promoteClass")
public class PromoteClass  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected long currentClassSectionId;
    protected String promoteClassName;
    protected String promoteSectionName;
    protected AcademicYear academicYear;
    protected long mediumId;
    protected boolean promoteProcessCompleted;
    
    
	/**
	 * @return the promoteProcessCompleted
	 */
    @Column(name = "promoteProcessCompleted", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isPromoteProcessCompleted() {
		return promoteProcessCompleted;
	}

	/**
	 * @param promoteProcessCompleted the promoteProcessCompleted to set
	 */
	public void setPromoteProcessCompleted(boolean promoteProcessCompleted) {
		this.promoteProcessCompleted = promoteProcessCompleted;
	}

	/**
	 * @return the mediumId
	 */
	public long getMediumId() {
		return mediumId;
	}

	/**
	 * @param mediumId the mediumId to set
	 */
	public void setMediumId(long mediumId) {
		this.mediumId = mediumId;
	}

	/**
	 * @return the currentClassSectionId
	 */
	public long getCurrentClassSectionId() {
		return currentClassSectionId;
	}

	/**
	 * @param currentClassSectionId the currentClassSectionId to set
	 */
	public void setCurrentClassSectionId(long currentClassSectionId) {
		this.currentClassSectionId = currentClassSectionId;
	}

	/**
	 * @return the promoteClassName
	 */
	public String getPromoteClassName() {
		return promoteClassName;
	}

	/**
	 * @param promoteClassName the promoteClassName to set
	 */
	public void setPromoteClassName(String promoteClassName) {
		this.promoteClassName = promoteClassName;
	}

	/**
	 * @return the promoteSectionName
	 */
	public String getPromoteSectionName() {
		return promoteSectionName;
	}

	/**
	 * @param promoteSectionName the promoteSectionName to set
	 */
	public void setPromoteSectionName(String promoteSectionName) {
		this.promoteSectionName = promoteSectionName;
	}

	/**
	 * @return the academicYear
	 */
	@OneToOne
    @JoinColumn(name="academicYearId")
	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * @return the classNameId
	 *//*
	public long getClassNameId() {
		return classNameId;
	}

	*//**
	 * @return the promotedClassNameId
	 *//*
	public long getPromotedClassNameId() {
		return promotedClassNameId;
	}

	*//**
	 * @param classNameId the classNameId to set
	 *//*
	public void setClassNameId(long classNameId) {
		this.classNameId = classNameId;
	}

	*//**
	 * @param promotedClassNameId the promotedClassNameId to set
	 *//*
	public void setPromotedClassNameId(long promotedClassNameId) {
		this.promotedClassNameId = promotedClassNameId;
	}
*/
	public PromoteClass() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

	public PromoteClass(long id) {
        setId(id);
    }

    @Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
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

}
    

  

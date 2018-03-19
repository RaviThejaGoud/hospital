/**
 * 
 */
package com.urt.persistence.model.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.SubTypeGrades;

/**
 * @author urtuser
 *
 */
@Entity
@Table(name = "subType")
public class SubType extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long custId;
	private String subTypeName;
    protected AcademicYear academicYear;
    private boolean schedule;
    private boolean predefinedSubType;
    private int sortingOrder;
    protected List<SubTypeGrades> subTypeGrades;
    
    
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="subTypeId")
    public List<SubTypeGrades> getSubTypeGrades() {
		return subTypeGrades;
	}
	public void setSubTypeGrades(List<SubTypeGrades> subTypeGrades) {
		this.subTypeGrades = subTypeGrades;
	}
	@Column(name = "sortingOrder", columnDefinition="int(4) default 0")
	public int getSortingOrder() {
		return sortingOrder;
	}
	/**
	 * @param sortingOrder the sortingOrder to set
	 */
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
   	
    	
	
	
	/**
	 * @return the predefinedSubType
	 */
    @Column(name = "predefinedSubType", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isPredefinedSubType() {
		return predefinedSubType;
	}
	/**
	 * @param predefinedSubType the predefinedSubType to set
	 */
	public void setPredefinedSubType(boolean predefinedSubType) {
		this.predefinedSubType = predefinedSubType;
	}
	/**
	 * @return the subTypeName
	 */
	public String getSubTypeName() {
		return subTypeName;
	}
	/**
	 * @param subTypeName the subTypeName to set
	 */
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	/**
	 * @return the schedule
	 */
	@Column(name = "schedule", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isSchedule() {
		return schedule;
	}
	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
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
	
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		SubType subType=(SubType)object;
		if(subType.getSortingOrder() > 0 && this.getSortingOrder() > 0){
			if(this.getSortingOrder() > subType.getSortingOrder())
				return 1;
			else if(this.getSortingOrder() == subType.getSortingOrder())
				return 0;
			else
				return -1;
		}else{
			return new CompareToBuilder().append(this.getId(), subType.getId()).toComparison();	
		}
		/*SubType myClass = (SubType) object;
        return new CompareToBuilder().append(this.getId(), myClass.getId()).toComparison();*/
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
	
	public void copyFrom(SubType types)
    {
		setCustId(types.getCustId());
		setSubTypeName(types.getSubTypeName());
		setCreatedDate(new Date());
		setLastAccessDate(new Date());
		setPredefinedSubType(types.isPredefinedSubType());
		setSchedule(types.isSchedule());
    }
	public void addSubTypeGrades(SubTypeGrades grades) {
		if(ObjectFunctions.isNullOrEmpty(this.subTypeGrades)){
			this.subTypeGrades=new ArrayList<SubTypeGrades>();
		}
			this.subTypeGrades.add(grades);
	}
}

package com.urt.persistence.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/*
 * @create new table Fee Type.
 */
@Entity
@Table(name = "feeType")
public class FeeType  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private long  custId; 
    protected String status="S";
    protected long classId;
    protected String feeType;
    protected AcademicYear academicYear;
    protected long feeSettingId;
    protected long priorityPosition;
    protected String committedFeeStatus;
    
	/**
	 * @return the priorityPosition
	 */
    @Column(name="priorityPosition", nullable=false,columnDefinition="bigint(1) default 0")
	public long getPriorityPosition() {
		return priorityPosition;
	}
	/**
	 * @param priorityPosition the priorityPosition to set
	 */
	public void setPriorityPosition(long priorityPosition) {
		this.priorityPosition = priorityPosition;
	}
    
	
	@Column(name = "feeSettingId",nullable = false, columnDefinition=" int default 0")
	public long getFeeSettingId() {
		return feeSettingId;
	}
	public void setFeeSettingId(long feeSettingId) {
		this.feeSettingId = feeSettingId;
	}
	/**
     * @return the academicYear
     */
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="academicYearId", insertable=true, updatable=true)
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}


	/**
	 * @return the committedFeeStatus
	 */
	@Column(name = "committedFeeStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	//@Column(name="committedFeeStatus", nullable=false,columnDefinition="char(1) default N")
	public String getCommittedFeeStatus() {
		return committedFeeStatus;
	}
	/**
	 * @param committedFeeStatus the committedFeeStatus to set
	 */
	public void setCommittedFeeStatus(String committedFeeStatus) {
		this.committedFeeStatus = committedFeeStatus;
	}
	/**
	 * @return the feeType
	 */
	public String getFeeType() {
		return feeType;
	}

	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}


	public FeeType() {
        
    }
     
    
    public FeeType(long id) {
        setId(id);
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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("fee Type",this.getFeeType()).toString();
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
	/**
	 * @return the className
	 */
	/**
	 * @return the attendanceDetails
	 */
	/*@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="classId") 
	public ClassName getClassName() {
		return className;
	}
	public void setClassName(ClassName className) {
		this.className = className;
	}*/
	@Column(name="status", nullable=true, length=10, unique=false,columnDefinition="char(1) default 'S'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Transient
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	@Transient
	public String getFeeTypeName(){
		if(StringFunctions.isNullOrEmpty(this.feeType))
			return "";
		else
			return this.feeType;
	}
}


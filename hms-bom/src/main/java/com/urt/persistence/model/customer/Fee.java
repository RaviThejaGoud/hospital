package com.urt.persistence.model.customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.ClassName;

/*
 * @create new table customer.
 */

@Entity
@Table(name = "Fee")
public class Fee  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private Customer customer; 
    protected ClassName className;
    protected String status;
    protected double feeAmount;
    protected AcademicYear academicYear;
    protected SchoolTerms schoolTerms;
    protected FeeType feeType;
    private long  categoryId;
    private long routeBoardingPointId;
    
    @Column(name = "routeBoardingPointId", nullable = true, length = 10,columnDefinition="int default '0'")
    public long getRouteBoardingPointId() {
		return routeBoardingPointId;
	}

	public void setRouteBoardingPointId(long routeBoardingPointId) {
		this.routeBoardingPointId = routeBoardingPointId;
	}

	/**
	 * @return the categoryId
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'S'")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
	 * @return the schoolTerms
	 */
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="schoolTermId", insertable=true, updatable=true)
	public SchoolTerms getSchoolTerms() {
		return schoolTerms;
	}
	/**
	 * @param schoolTerms the schoolTerms to set
	 */
	public void setSchoolTerms(SchoolTerms schoolTerms) {
		this.schoolTerms = schoolTerms;
	}
	/**
	 * @return the feeType
	 */
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="feeTypeId", insertable=true, updatable=true)
	public FeeType getFeeType() {
		return feeType;
	}
	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
	/**
     * @return the academicYear
     */
    @OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    
    public Fee() {
        
    }
	public Fee(long id) {
        setId(id);
    }
    
	@OneToOne
    @JoinColumn(name="custId")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("classId",this.getClassName().getId()).toString();
	}

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="classId") 
	public ClassName getClassName() {
		return className;
	}
	public void setClassName(ClassName className) {
		this.className = className;
	}

	/**
	 * @return the feeAmount
	 */
	@Column(name = "feeAmount", nullable = true, length = 10,columnDefinition="double default '0'")
	public double getFeeAmount() {
		return feeAmount;
	}

	/**
	 * @param feeAmount the feeAmount to set
	 */
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
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
}


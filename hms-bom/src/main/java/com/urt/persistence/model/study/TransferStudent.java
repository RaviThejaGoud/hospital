package com.urt.persistence.model.study;
/********************************************************************
 * Copyright (C) 2005-06

 * IFS
 * All Rights Reserved 
 *
 * File: Person.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
/ ********************************************************************/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.user.User;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "transferStudent")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)

public class TransferStudent extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private User account;
	private AcademicYear academicYear;
	private long newSchoolAcademicYearId;
	private Customer customer;
	private Customer newCustomer;
	private String status;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accountId", insertable=true, updatable=true)
	@PrimaryKeyJoinColumn(name = "accountId", referencedColumnName = "id")
	public User getAccount() {
		return account;
	}
	public void setAccount(User account) {
		this.account = account;
	}
	
	public long getNewSchoolAcademicYearId() {
		return newSchoolAcademicYearId;
	}
	public void setNewSchoolAcademicYearId(long newSchoolAcademicYearId) {
		this.newSchoolAcademicYearId = newSchoolAcademicYearId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="custId", insertable=true, updatable=true) 
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	@JoinColumn(name="newSchoolId", insertable=true, updatable=true) 
	public Customer getNewCustomer() {
		return newCustomer;
	}
	public void setNewCustomer(Customer newCustomer) {
		this.newCustomer = newCustomer;
	}
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'A'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Constructor for Student.
	 */
	public TransferStudent() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof TransferStudent)) {
			return false;
		} else {

			EqualsBuilder builder = new EqualsBuilder();
	
			return builder.isEquals();
		}
	}

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	/* Changed by seshu on 4 Apr for import student sheet end row purpose 
	  */
	@Override
	public String toString() {
		try {
			if (StringFunctions
					.isNullOrEmpty(this.account.getAdmissionNumber()))
				return "";
			if(StringFunctions.isNullOrEmpty(this.account.getPerson().getFirstName()))
				return "";
			
			StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);
			buffer.append(getId()).append(" ");
			buffer.append(account).append(" ");
			return buffer.toString();
		} catch (Exception ex) {
			return "";
		}
	}

   

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    //For Sorting students  alphabetically -- seshu
    @Override
	public int compareTo(Object object) {
        TransferStudent stud = (TransferStudent) object;
        if(ObjectFunctions.isNullOrEmpty(stud)){
        	return 0;
        }else
        	return 0;
        /*return new CompareToBuilder().append(this.classSection, myClass.classSection)
                .append(this.account, myClass.getAccount()).append(this.academicYear, myClass.getAcademicYear()).toComparison();*/
    }
   	
	@Transient
    public String getStudentId()
    {
        return "S"+getStrId();
    }
	
	
	public void copyFrom(TransferStudent obj)
    {
		
    }
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}

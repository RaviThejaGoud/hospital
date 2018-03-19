package com.urt.persistence.model.customer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.fee.SchoolFeeSetting;

/*
 * @create new table Fee Type.
 */
@Entity
@Table(name = "schoolTerms")
public class SchoolTerms  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private Customer  customer; 
    protected String status="S";
    protected String description;
    protected String termName;
    protected Date fromDate;
    protected Date toDate;
    protected Date dueDate;
    protected String fromMonthName;
    protected String toMonthName;
    protected AcademicYear academicYear;
    protected String noOfDays;
    /*protected String mailContentDesc;
    protected String mobileContentDesc;*/
    protected SchoolFeeSetting feeSetting;
    protected boolean applToNewStuds;
    protected Date dueDate1;
    protected Date dueDate2;
    
    @Column(name = "applToNewStuds", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
    public boolean isApplToNewStuds() {
		return applToNewStuds;
	}
	public void setApplToNewStuds(boolean applToNewStuds) {
		this.applToNewStuds = applToNewStuds;
	}
	@OneToOne
    @JoinColumn(name="custId")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@OneToOne
    @JoinColumn(name="feeSettingId")
	public SchoolFeeSetting getFeeSetting() {
		return feeSetting;
	}
	public void setFeeSetting(SchoolFeeSetting feeSetting) {
		this.feeSetting = feeSetting;
	}
	@OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}


	


	public SchoolTerms() {
        
    }
     
    
    public SchoolTerms(long id) {
        setId(id);
    }
    
    /**
	 * @return the noOfDays
	 */
	public String getNoOfDays() {
		return noOfDays;
	}
	/**
	 * @param noOfDays the noOfDays to set
	 */
	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}
	/**
	 * @return the mailContentDesc
	 *//*
	public String getMailContentDesc() {
		return mailContentDesc;
	}
	*//**
	 * @param mailContentDesc the mailContentDesc to set
	 *//*
	public void setMailContentDesc(String mailContentDesc) {
		this.mailContentDesc = mailContentDesc;
	}
	*//**
	 * @return the mobileContentDesc
	 *//*
	public String getMobileContentDesc() {
		return mobileContentDesc;
	}
	*//**
	 * @param mobileContentDesc the mobileContentDesc to set
	 *//*
	public void setMobileContentDesc(String mobileContentDesc) {
		this.mobileContentDesc = mobileContentDesc;
	}
	*/
	
	/**
	 * @return the terms
	 */
	
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}
	/**
	 * @return the termName
	 */
	public String getTermName() {
		return termName;
	}
	/**
	 * @param termName the termName to set
	 */
	public void setTermName(String termName) {
		this.termName = termName;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the fromMonthName
	 */
	public String getFromMonthName() {
		return fromMonthName;
	}
	/**
	 * @param fromMonthName the fromMonthName to set
	 */
	public void setFromMonthName(String fromMonthName) {
		this.fromMonthName = fromMonthName;
	}
	/**
	 * @return the toMonthName
	 */
	public String getToMonthName() {
		return toMonthName;
	}
	/**
	 * @param toMonthName the toMonthName to set
	 */
	public void setToMonthName(String toMonthName) {
		this.toMonthName = toMonthName;
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
	
	//@Column(name="status", nullable=true, length=10, unique=false,columnDefinition="char(1) default 'P'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="description", nullable=true, length = 1024)
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Transient
	 public String getDescriptionAndId(){
	 	return getDescription() +"-"+getId();
	 }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Transient
    public String getDueDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getDueDate());
    }
	@Transient
    public String getToDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getToDate());
    }
	@Transient
    public String getFromDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getFromDate());
    }
	
	@Transient
    public String getTermFromDateStr() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getFromDate());
    }
	@Transient
    public String getTermToDateStr() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getToDate());
    }
	@Transient
    public String getTermDueDateStr() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getDueDate());
    }
	
	@Transient
	public String getIsBetweenFeeDueDays() {
		Date date=new Date();
		
		int feeDueDays=DateFunctions.daysBetween(getDueDate(),date);
		
		if(feeDueDays>=0 && feeDueDays <=14){
			return "A";
		}
		if(feeDueDays>14 && feeDueDays <=30){
			return "B";
		}
		if(feeDueDays>30 && feeDueDays<=60){
			return "C";
		}
		if(feeDueDays>60){
			return "D";
		}
		if(feeDueDays<=0 && feeDueDays>= -15){
			return "E";
		}
		
		return null;
	}
	@Transient
	public String getMonthOfTermName() {
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(getTermName())) {
			ret.append(getTermName());
			ret.append("(");
		}
		if (!StringFunctions.isNullOrEmpty(getFromMonthName())) {
			ret.append(getFromMonthName().substring(0, 3));
		}
		if (!StringFunctions.isNullOrEmpty(getToMonthName())) {
			ret.append(" - ");
			ret.append(getToMonthName().substring(0, 3));
			ret.append(" ) ");
		}

		return ret.toString().trim();
	}
	@Transient
	public long getFeeSettingId(){
		if(ObjectFunctions.isNullOrEmpty(this.feeSetting))
			return 0;
		else
			return this.feeSetting.getId();
	}
	@Transient
    public String getDueDate1Str() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getDueDate1());
    }
	@Transient
    public String getDueDate2Str() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getDueDate2());
    }
	@Transient
    public String getTermDueDate1Str() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getDueDate1());
    }
	@Transient
    public String getTermDueDate2Str() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getDueDate2());
    }
	public Date getDueDate1() {
		return dueDate1;
	}
	public void setDueDate1(Date dueDate1) {
		this.dueDate1 = dueDate1;
	}
	public Date getDueDate2() {
		return dueDate2;
	}
	public void setDueDate2(Date dueDate2) {
		this.dueDate2 = dueDate2;
	}
	@Transient
	public boolean getTermActive(){
		return DateFunctions.compare2Dates(this.fromDate,new Date());
	}
}


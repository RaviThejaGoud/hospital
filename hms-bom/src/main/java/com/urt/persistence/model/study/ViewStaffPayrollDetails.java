package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.LeaveManagement;
import com.urt.persistence.model.common.Salary;
import com.urt.persistence.model.common.UserImage;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewStaffPayrollDetails.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    JUl 17, 2012     Narahari          	Created
/********************************************************************/

@Entity
@Table(name = "vw_staffPayrollDetails")
public class ViewStaffPayrollDetails  implements Comparable,Serializable,Cloneable {

	private static final Log log = LogFactory.getLog(ViewStaffPayrollDetails.class);
    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long accountId;
     private long custId;
     private String username;
     private String sharePrivacy;
     private String userOnlineNow;
     private String academicYearId;
     private String status;
     private String qualification1;
     private long staffId;
     /*private long totalLeaves;*/
     private String firstName;
     private String lastName;
     private String middleName;
     //private String lastUpdatedBy;
 	 protected Date lastUpdatedDate;
     //protected Date createdDate;
    /* protected long casualLeaves;
     protected long sickLeaves;*/
     protected String addressLine1;
     protected String addressLine2;
     protected String city;
     protected String state;
     protected String postalCode;
     protected String mobileNumber;
     protected String phoneNumber;
     protected String bloodGroup;
     protected Date dateOfBirth;
     private String roleName;
     private long roleId;
     private String licenseNumber;
     private String licenseExpDate; 
     private String qualification2;
     private String experience;
     private Date dateofJoining;
     private String maritalStatus;
     private String roleDescription;
     private String supervisorId;
     private String leaveCount;
     private String imageName;
     private String thumbNail;
     private String imagePath;
     private String imageId;
     private String summary;
     private String email;
     private String gender;
     private String familyDoctor;
     private String prefferedHospital;
     private String staffType;
     protected String anyString;
     protected char leaveRequest;
     protected boolean present;
     private LeaveManagement leaveManagement;
     protected double sickLeavesCount;
     protected double casualLeavesCount;
     protected double payLeavesCount;
     private Long religionId;
     private String designation;
	 private String panNumber;
	 private String gpfNumber;
	 private String officeNumber;
	 private long annualIncome;
	 protected long castId;
	 protected long subCastId;
	 protected long motherToungId;
	 protected long organizationTypeId;
	 private String nationality;
	 protected long bedId;
	// protected boolean accountExpired;
	 protected String description;
	 
	 //protected String salaryMonth;
	 //protected String salaryYear;
	 protected String payrollName;
	 protected String payrollDescription;
	 protected String payrollTypesId;
	 protected long payrollSettingsId;
	 protected String payrollType;
	 
	 protected String  payrollMonth;
	 protected String  payrollYear;
	 
	 private Salary salary;
	 
	 private String staffPayrollMonth ;
	 private String staffPayrollYear;
	 
	 
	 private double payrollRate;
	 private double percentage;
	 private double percentageValue;
	 
	 private String payHeadType;
	 private String computedOn; 
	 protected Date pslastUpdatedDate;
	 
	 /*private String statutoryName;
	 private String statutoryNo;
	 private String statutoryDescription;
	 private double statutoryPercentage;
	 private Date statutoryDateofJoin;
	 private String statutoryStatus;
	 private String statutoryId;*/
	 private String staffPayrollDesc;
	 
	 
	 
	@Transient
	 public String getStaffPayrollDesc() {
		return staffPayrollDesc;
	}
	public void setStaffPayrollDesc(String staffPayrollDesc) {
		this.staffPayrollDesc = staffPayrollDesc;
	}
	/*@Transient
	public String getStatutoryStatusDesc() 
	{
		if("AW".equalsIgnoreCase(getStatutoryStatus()))
		{
			return "Allowance";
		}
		else
			return "Deduction";
	}*/
	/*public String getStatutoryName() {
		return statutoryName;
	}
	public void setStatutoryName(String statutoryName) {
		this.statutoryName = statutoryName;
	}
	public String getStatutoryNo() {
		return statutoryNo;
	}
	public void setStatutoryNo(String statutoryNo) {
		this.statutoryNo = statutoryNo;
	}
	public String getStatutoryDescription() {
		return statutoryDescription;
	}
	public void setStatutoryDescription(String statutoryDescription) {
		this.statutoryDescription = statutoryDescription;
	}
	public double getStatutoryPercentage() {
		return statutoryPercentage;
	}
	public void setStatutoryPercentage(double statutoryPercentage) {
		this.statutoryPercentage = statutoryPercentage;
	}
	public Date getStatutoryDateofJoin() {
		return statutoryDateofJoin;
	}
	public void setStatutoryDateofJoin(Date statutoryDateofJoin) {
		this.statutoryDateofJoin = statutoryDateofJoin;
	}
	public String getStatutoryStatus() {
		return statutoryStatus;
	}
	public void setStatutoryStatus(String statutoryStatus) {
		this.statutoryStatus = statutoryStatus;
	}
	
	@Id
	public String getStatutoryId() {
		return statutoryId;
	}
	public void setStatutoryId(String statutoryId) {
		this.statutoryId = statutoryId;
	}*/
	@Id
	 public long getPayrollSettingsId() {
		return payrollSettingsId;
	}
	public void setPayrollSettingsId(long payrollSettingsId) {
		this.payrollSettingsId = payrollSettingsId;
	}
	public Date getPslastUpdatedDate() {
		return pslastUpdatedDate;
	}
	public void setPslastUpdatedDate(Date pslastUpdatedDate) {
		this.pslastUpdatedDate = pslastUpdatedDate;
	}
	@Transient
	public String getComputedOn() {
		return computedOn;
	}
	public void setComputedOn(String computedOn) {
		this.computedOn = computedOn;
	}
	@Transient
	 public String getPayHeadType() {
		return payHeadType;
	}
	public void setPayHeadType(String payHeadType) {
		this.payHeadType = payHeadType;
	}
	@Transient
	 public double getPercentageValue() {
		return percentageValue;
	}
	public void setPercentageValue(double percentageValue) {
		this.percentageValue = percentageValue;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	@Transient
	public double getPayrollRate() {
		return payrollRate;
	}
	public void setPayrollRate(double payrollRate) {
		this.payrollRate = payrollRate;
	}
	public String getStaffPayrollMonth() {
		return staffPayrollMonth;
	}
	public void setStaffPayrollMonth(String staffPayrollMonth) {
		this.staffPayrollMonth = staffPayrollMonth;
	}
	public String getStaffPayrollYear() {
		return staffPayrollYear;
	}
	public void setStaffPayrollYear(String staffPayrollYear) {
		this.staffPayrollYear = staffPayrollYear;
	}
	@Transient
	public Salary getSalary() 
	{
		return this.salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	public String getPayrollMonth() {
		return payrollMonth;
	}
	public void setPayrollMonth(String payrollMonth) {
		this.payrollMonth = payrollMonth;
	}
	public String getPayrollYear() {
		return payrollYear;
	}
	public void setPayrollYear(String payrollYear) {
		this.payrollYear = payrollYear;
	}
	/* public String getSalaryMonth() {
		return salaryMonth;
	}
	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
	public String getSalaryYear() {
		return salaryYear;
	}
	public void setSalaryYear(String salaryYear) {
		this.salaryYear = salaryYear;
	}*/
	public String getPayrollName() {
		return payrollName;
	}
	public void setPayrollName(String payrollName) {
		this.payrollName = payrollName;
	}
	public String getPayrollDescription() {
		return payrollDescription;
	}
	public void setPayrollDescription(String payrollDescription) {
		this.payrollDescription = payrollDescription;
	}
	
	public String getPayrollTypesId() {
		return payrollTypesId;
	}
	public void setPayrollTypesId(String payrollTypesId) {
		this.payrollTypesId = payrollTypesId;
	}
	public String getPayrollType() {
		return payrollType;
	}
	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	    
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	 	 /**
	 * @return the accountExpired
	 */
	/*public boolean isAccountExpired() {
	    return accountExpired;
	}

	*//**
	 * @param accountExpired the accountExpired to set
	 *//*
	public void setAccountExpired(boolean accountExpired) {
	    this.accountExpired = accountExpired;
	}*/

		/**
		 * @return the bedId
		 */
		public long getBedId() {
			return bedId;
		}

		/**
		 * @param bedId the bedId to set
		 */
		public void setBedId(long bedId) {
			this.bedId = bedId;
		}

		
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the organizationTypeId
	 */
	public long getOrganizationTypeId() {
		return organizationTypeId;
	}
	/**
	 * @param organizationTypeId the organizationTypeId to set
	 */
	public void setOrganizationTypeId(long organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}
	/**
	 * @return the motherToung
	 */
	public long getMotherToungId() {
		return motherToungId;
	}
	/**
	 * @param motherToung the motherToung to set
	 */
	public void setMotherToungId(long motherToungId) {
		this.motherToungId = motherToungId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the castId
	 */
	public long getCastId() {
		return castId;
	}
	/**
	 * @param castId the castId to set
	 */
	public void setCastId(long castId) {
		this.castId = castId;
	}
	/**
	 * @return the subCastId
	 */
	public long getSubCastId() {
		return subCastId;
	}
	/**
	 * @param subCastId the subCastId to set
	 */
	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
	}
	
	/**
	 * @return the religionId
	 */
	public Long getReligionId() {
		return religionId;
	}
	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(Long religionId) {
		this.religionId = religionId;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the panNumber
	 */
	public String getPanNumber() {
		return panNumber;
	}
	/**
	 * @param panNumber the panNumber to set
	 */
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	/**
	 * @return the gpfNumber
	 */
	public String getGpfNumber() {
		return gpfNumber;
	}
	/**
	 * @param gpfNumber the gpfNumber to set
	 */
	public void setGpfNumber(String gpfNumber) {
		this.gpfNumber = gpfNumber;
	}
	/**
	 * @return the officeNumber
	 */
	public String getOfficeNumber() {
		return officeNumber;
	}
	/**
	 * @param officeNumber the officeNumber to set
	 */
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	/**
	 * @return the annualIncome
	 */
	public long getAnnualIncome() {
		return annualIncome;
	}
	/**
	 * @param annualIncome the annualIncome to set
	 */
	public void setAnnualIncome(long annualIncome) {
		this.annualIncome = annualIncome;
	}
     
    @Transient
	public double getSickLeavesCount() {
		return sickLeavesCount;
	}
    public void setSickLeavesCount(double sickLeavesCount) {
		this.sickLeavesCount = sickLeavesCount;
	}
    @Transient
	public double getCasualLeavesCount() {
		return casualLeavesCount;
	}
    public void setCasualLeavesCount(double casualLeavesCount) {
		this.casualLeavesCount = casualLeavesCount;
	}
    @Transient
	public double getPayLeavesCount() {
		return payLeavesCount;
	}
    public void setPayLeavesCount(double payLeavesCount) {
		this.payLeavesCount = payLeavesCount;
	}
	
	@Transient
	public LeaveManagement getLeaveManagement() {
		return leaveManagement;
	}
	public void setLeaveManagement(LeaveManagement leaveManagement) {
		this.leaveManagement = leaveManagement;
	}
	
	@Transient
    public boolean getPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	@Transient
    public char getLeaveRequest() {
		return leaveRequest;
	}
	public void setLeaveRequest(char leaveRequest) {
		this.leaveRequest = leaveRequest;
	}
     
     
    public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public String getFamilyDoctor() {
		return familyDoctor;
	}
	public void setFamilyDoctor(String familyDoctor) {
		this.familyDoctor = familyDoctor;
	}
	public String getPrefferedHospital() {
		return prefferedHospital;
	}
	public void setPrefferedHospital(String prefferedHospital) {
		this.prefferedHospital = prefferedHospital;
	}
	@Column(name = "gender", nullable = true, length = 1)
    public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getImageId() {
 		return imageId;
 	}
 	public void setImageId(String imageId) {
 		this.imageId = imageId;
 	}
     
   	
   	public String getImagePath() {
   		return imagePath;
   	}
   	public void setImagePath(String imagePath) {
   		this.imagePath = imagePath;
   	}
   public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getThumbNail() {
		return thumbNail;
	}
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}
     
     
	
	@Transient
     public String getLeaveCount() {
		return leaveCount;
	}
	@Transient
	public void setLeaveCount(String leaveCount) {
		this.leaveCount = leaveCount;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public ViewStaffPayrollDetails()
    {
        super();
    }
    /** default constructor */
    /*@Transient
    public ViewStaffPayrollDetails  ViewStaffPayrollDetails(Object object)
    {
    	ViewStaffPayrollDetails viewStaffPayrollDetails = null;
    	if(!ObjectFunctions.isNullOrEmpty(object))
    	{
    		
    		viewStaffPayrollDetails = new ViewStaffPayrollDetails();
    		
    		this.accountId = accountId;
    		this.custId = custId;
    		this.username = username;
    		this.sharePrivacy = sharePrivacy;
    		this.userOnlineNow = userOnlineNow;
    		this.academicYearId = academicYearId;
    		this.status = status;
    		//this.qualification1 = qualification;
    		this.staffId = staffId;
    		this.totalLeaves = totalLeaves;
    		this.firstName = firstName;
    		this.lastName = lastName;
    		this.middleName = middleName;
    	}
    	
    	
       return viewStaffPayrollDetails;
    }*/
    
    
    public String getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(String academicYearId) {
		this.academicYearId = academicYearId;
	}
	public ViewStaffPayrollDetails(long accountId, long custId,
			String username, String sharePrivacy, String userOnlineNow,
			String academicYearId,
			String status, String qualification, long staffId,
			String firstName, String lastName,
			String middleName) {
		super();
		this.accountId = accountId;
		this.custId = custId;
		this.username = username;
		this.sharePrivacy = sharePrivacy;
		this.userOnlineNow = userOnlineNow;
		this.academicYearId = academicYearId;
		this.status = status;
		this.qualification1 = qualification;
		this.staffId = staffId;
		/*this.totalLeaves = totalLeaves;*/
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewStaffPayrollDetails)) return false;
        
        final ViewStaffPayrollDetails staffDetails = (ViewStaffPayrollDetails) o;
        if(this.username !=null){
        	if(this.username.equals(staffDetails.getUsername())){
        		return true;
        	}
        	else{
        		return false;
        	}
        }

        return true;
    }

    @Override
	public int hashCode() {
        int result = 0;
        
        return result;
    }
   
    /**
     * @see java.lang.Object#toString()
     * 
     * Domestic Address Formatted as
     *  addressLine1; addressLine2; city, state   zipCode[-zipCodeSupplement]
     * 
     * Military Address Formatted as
     *  addressLine1; addressLine2; city postalCode;
     * @Override
     */
    
    @Override
	public String toString() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        buffer
            .append(" User Name: ")
            .append(getUsername())
            .append(" Status : ")
            .append(getStatus());
        return buffer.toString();
    }
    @Override
	public int compareTo(Object object) {
    	ViewStaffPayrollDetails myClass = (ViewStaffPayrollDetails) object;
    	 return new CompareToBuilder().append(this.firstName,
                 myClass.firstName).toComparison();
    }
    
	@Column( name="accountId", unique=false, nullable=false, updatable=false )
	public long getAccountId() {
		return accountId;
	}
	public long getCustId() {
		return custId;
	}
	public String getUsername() {
		return username;
	}
	public String getSharePrivacy() {
		return sharePrivacy;
	}
	public String getUserOnlineNow() {
		return userOnlineNow;
	}
	
	public String getStatus() {
		return status;
	}

	public String getQualification1() {
		return qualification1;
	}
	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}
	
	public long getStaffId() {
		return staffId;
	}
/*	public long getTotalLeaves() {
		return totalLeaves;
	}*/
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSharePrivacy(String sharePrivacy) {
		this.sharePrivacy = sharePrivacy;
	}
	public void setUserOnlineNow(String userOnlineNow) {
		this.userOnlineNow = userOnlineNow;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	/*public void setTotalLeaves(long totalLeaves) {
		this.totalLeaves = totalLeaves;
	}*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/*public long getCasualLeaves() {
		return casualLeaves;
	}
	public long getSickLeaves() {
		return sickLeaves;
	}
	public void setCasualLeaves(long casualLeaves) {
		this.casualLeaves = casualLeaves;
	}
	public void setSickLeaves(long sickLeaves) {
		this.sickLeaves = sickLeaves;
	}*/
	@Transient
    public String getFullFormattedName(
        boolean title,
        boolean middle,
        boolean suffix)
    {
        StringBuffer ret = new StringBuffer(10);
         /* if (title && !StringFunctions.isNullOrEmpty(strTitle))
            {
                ret.append(strTitle);
                if (!strTitle.endsWith("."))
                {
                    ret.append(".");
                }
                ret.append(" ");
            }*/
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(getLastName());
                
                
            }
           /* if (suffix && !StringFunctions.isNullOrEmpty(strSuffix) 
                    && !Constants.SELECT_ID.equalsIgnoreCase(strSuffix))
            {
                ret.append(" ");
                ret.append(strSuffix);
                ret.append(", ");
            }
            else
            {
                ret.append(", ");
            }*/
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(", ");
                ret.append(getFirstName());
              //  ret.append(" ");
            }
           /* if (middle && !StringFunctions.isNullOrEmpty(strMiddleInitial ))
            {
                ret.append(strMiddleInitial.charAt(0));
                ret.append(" ");
            }*/
        ret.append(" ");
        return ret.toString().trim();
    }
	@Transient
    public String getPersonFirstLastNameOnly()
    {
        StringBuffer ret = new StringBuffer(10);
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(getFirstName());
            }
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(" ");
                ret.append(getLastName());
            }
       
        return ret.toString().trim();
    }

    /**
     * Returns the student for display on UI
     * @return string with full formatted student
     */
   @Transient
    public String getDisplayName()
    {
        return getFullFormattedName(false, true, true);
    }
   @Transient
   public String getPersonFullName()
   {
       StringBuffer ret = new StringBuffer(10);
	       
           if (!StringFunctions.isNullOrEmpty(getFirstName()))
           {
               ret.append(getFirstName());
           }
           /*if (!StringFunctions.isNullOrEmpty(getFirstName()))
	       {
        	   ret.append(" ");
	           ret.append(getMiddleName());
	       }*/
           if (!StringFunctions.isNullOrEmpty(getLastName()))
           {
               ret.append(" ");
               ret.append(getLastName());
           }
      
       return ret.toString().trim();
   }
   /*@Transient
   public long getDisplayTotalLeaves()
   {
	  return getCasualLeaves() + getSickLeaves();
       
   }
   @Transient
   public long getDisplayUsedLeaves()
   {
	  return getCasualLeaves() + getSickLeaves();
       
   }*/
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}
	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}
	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "licenseExpDate", nullable = true, length = 128)
	public String getLicenseExpDate() {
		return licenseExpDate;
	}
	
	public void setLicenseExpDate(String licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}
	
	@Column(name = "licenseNumber", nullable = true, length = 128)
	public String getLicenseNumber() {
		return licenseNumber;
	}
	
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	/**
	 * @return the qualification2
	 */
	@Column(name = "qualification2", nullable = true, length = 128)
	public String getQualification2() {
		return qualification2;
	}
	/**
	 * @param qualification2 the qualification2 to set
	 */
	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}
	/**
	 * @return the experience
	 */
	@Column(name = "experience", nullable = true, length = 128)
	public String getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	/**
	 * @return the dateofJoining
	 */
	@Column(name = "dateofJoining", nullable = true, length = 128)
	public Date getDateofJoining() {
		return dateofJoining;
	}
	/**
	 * @param dateofJoining the dateofJoining to set
	 */
	public void setDateofJoining(Date dateofJoining) {
		this.dateofJoining = dateofJoining;
	}
	/**
	 * @return the maritialStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritialStatus the maritialStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	@Transient
	public  String getMaritalStausName()
	{
		if("M".equalsIgnoreCase(getMaritalStatus()))
		{
			return "Married";
		}
		else
		{
			return "UnMarried";
		}
	}
	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}
	/**
	 * @param roleDescription the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	//UserImage methods
	
	@Transient
	public static String getImageNotFoundFile()
	{
	    return "/images/common/photo_notAvailable.jpg"; 
	}
	@Transient
	public static String getStudyImageNotFoundFile()
	{
	    return "/images/common/studyImageNotAvailable.jpg"; 
	}
	@Transient
	public String getOriginalAttachmentFilePath()
	{
	    if(!StringFunctions.isNullOrEmpty(getImagePath()))
	    {
	        return getImagePath().concat(getImageName());
	    }
	    return UserImage.getStudyImageNotFoundFile();
	}
	@Transient
	public String getAdjustedAttachmentFilePath()
	{
	    if(!StringFunctions.isNullOrEmpty(getImagePath()))
	    {
	    	log.debug(getImagePath().concat(getThumbNail())); 
	        return getImagePath().concat(getThumbNail());
	    }
	    return UserImage.getStudyImageNotFoundFile();
	}
	@Transient
	public String getHrefOriginalAttachmentFilePath()
	{
	    if(!StringFunctions.isNullOrEmpty(getImagePath()))
	    {
	        return getImagePath().substring(1).concat(getImageName());
	    }
	    return UserImage.getStudyImageNotFoundFile();
	}
	@Transient
	public String getHrefAdjustedAttachmentFilePath()
	{
	    if(!StringFunctions.isNullOrEmpty(getImagePath()))
	    {
	        return getImagePath().substring(1).concat(getThumbNail());
	    }
	    return UserImage.getStudyImageNotFoundFile();
	}
	@Transient
	public String getDateOfBirthMonthName()
	{
	  Date stBirthDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getDateOfBirth()));
	
		if(stBirthDate==new Date()){
			return "Today";
		}
	    return DateFormatter.formatDate(DateFormatter.MMM_D_PATTERN, getDateOfBirth()); 
	}

	@Transient
	public String getDateOfBirthStr() {
		return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.dateOfBirth);
	}
	@Transient
	public void setAnyString(String anyString) {
		this.anyString = anyString;
	}
	@Transient
	public String getStaffDateOfDiscontune() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getLastUpdatedDate());
	}
	@Transient
	public String getStaffDateOfJoing() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getDateofJoining());
	}
	
	@Transient
	public String getGenderStr() 
	{
		if("M".equalsIgnoreCase(getGender()))
		{
			return "Male";
		}
		return "Female";
	}
	
	@Transient
	public String getPayrollTypeDesc() 
	{
		if("AW".equalsIgnoreCase(getPayrollType()))
		{
			return "Allowance";
		}
		else
			return "Deduction";
	}
}
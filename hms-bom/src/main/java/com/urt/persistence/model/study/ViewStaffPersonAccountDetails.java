package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.ServletActionContext;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.LeaveManagement;
import com.urt.persistence.model.common.UserImage;
import com.urt.util.excel.parser.ExcelField;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewGroupType.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Oct 05, 2010     Narahari          	Created
/********************************************************************/

@Entity
@Table(name = "vw_staffDetails")
public class ViewStaffPersonAccountDetails  implements Comparable,Serializable,Cloneable {


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
     private double experience;
     private Date dateofJoining;
     private String maritalStatus;
     private String roleDescription;
     private String supervisorId;
     private String leaveCount;
     private String imageName;
     private String thumbNail;
     private String imagePath;
     private long imageId;
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
     protected double earnedLeavesCount;
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
	 protected boolean accountExpired;
	 protected String description;
	// private Salary salary;
	 protected Double salary;
	 
	 private String staffPayrollMonth ;
	 private String staffPayrollYear;
	 
	 private String bankName;
     private String bankAccountNumber;
     private String bankBranchName;
	 private double basicSalary;
	 private double netPay;
	 private String payrollEffectiveFrom;
	/* private double loanAmount;
	 private Date loanTakenDate;
	 private int installments;
	 private String loanStatus;
	 private long loanId;*/
	 protected double totalAllowance;
	 protected double totalDeduction;
	 private long hostelCategoryId;
	 private Date contractStartDate;	
	 private Date contractEndDate;	
	 protected String castName;
	 protected String subCastName;
	 private String pfNo;
	 private String esiNo;
	 private Date esiDateofJoin;
	 private double esiPercentage;
	 private Date pfDateofJoin;
	 private double pfPercentage;
	 private String academicYearStatus;
     private int bioMetricId;
     private String fullName;
     protected String staffNumber;
     private String passportNumber;
     private String aadharNumber;
     private String ifscCode;
     protected String custImageName;
     protected String custImagePath;
     protected String custThumbNail;
     private String staffTypeStatus;
     private String staffUniqueNumber;
     protected String taddressLine1;
     protected String tcity;
     protected String tstate;
     protected String tpostalCode;
     private String outSideSchoolDuty;
     private String startTime;
     private String shiftName;
     private String endTime;
     private String shiftId;
     private String schoolMess;
     protected String barcode;
     private String staffGrade;
     private String  salaryPaymentMode;
     private String  staffLocation;
     private long personId;
     protected double subjectsMarksPercentage;
     private List assignedVehicleList;
     protected String secondaryUsername;
     private String stateName;
     private String academicYear;
     private String principalDigitalSignaturePath;
 	 private String fatherName;
 	 private String fatherContactNumber;

	public String getPrincipalDigitalSignaturePath() {
		return principalDigitalSignaturePath;
	}

	public void setPrincipalDigitalSignaturePath(
			String principalDigitalSignaturePath) {
		this.principalDigitalSignaturePath = principalDigitalSignaturePath;
	}
 	
     
     
 	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getSecondaryUsername() {
 		return secondaryUsername;
 	}

 	public void setSecondaryUsername(String secondaryUsername) {
 		this.secondaryUsername = secondaryUsername;
 	}
     
     
     @Transient
     public List getAssignedVehicleList() {
		return assignedVehicleList;
	}

	public void setAssignedVehicleList(List assignedVehicleList) {
		this.assignedVehicleList = assignedVehicleList;
	}


	// Constructors
      @Transient
     public double getSubjectsMarksPercentage() {
 		return subjectsMarksPercentage;
 	}

 	public void setSubjectsMarksPercentage(double subjectsMarksPercentage) {
 		this.subjectsMarksPercentage = subjectsMarksPercentage;
 	}
	 
     
 	public String getStaffGrade() {
		return staffGrade;
	}
	public void setStaffGrade(String staffGrade) {
		this.staffGrade = staffGrade;
	}
	public String getSalaryPaymentMode() {
		return salaryPaymentMode;
	}
	public void setSalaryPaymentMode(String salaryPaymentMode) {
		this.salaryPaymentMode = salaryPaymentMode;
	}
	public String getStaffLocation() {
		return staffLocation;
	}
	public void setStaffLocation(String staffLocation) {
		this.staffLocation = staffLocation;
	}
	public String getBarcode() {
 		return barcode;
 	}
 	public void setBarcode(String barcode) {
 		this.barcode = barcode;
 	}
     
    @Column(name = "schoolMess", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
 	public String getSchoolMess() {
 		return schoolMess;
 	}
 	public void setSchoolMess(String schoolMess) {
 		this.schoolMess = schoolMess;
 	}
  	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getOutSideSchoolDuty() {
 		return outSideSchoolDuty;
 	}

 	public void setOutSideSchoolDuty(String outSideSchoolDuty) {
 		this.outSideSchoolDuty = outSideSchoolDuty;
 	}
     
 	public String getTaddressLine1() {
		return taddressLine1;
	}

	public void setTaddressLine1(String taddressLine1) {
		this.taddressLine1 = taddressLine1;
	}

	public String getTcity() {
		return tcity;
	}

	public void setTcity(String tcity) {
		this.tcity = tcity;
	}

	public String getTstate() {
		return tstate;
	}

	public void setTstate(String tstate) {
		this.tstate = tstate;
	}

	public String getTpostalCode() {
		return tpostalCode;
	}

	public void setTpostalCode(String tpostalCode) {
		this.tpostalCode = tpostalCode;
	}

	public String getStaffUniqueNumber() {
		return staffUniqueNumber;
	}

	public void setStaffUniqueNumber(String staffUniqueNumber) {
		this.staffUniqueNumber = staffUniqueNumber;
	}

	public String getStaffTypeStatus() {
		return staffTypeStatus;
	}

	public void setStaffTypeStatus(String staffTypeStatus) {
		this.staffTypeStatus = staffTypeStatus;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	/**
	 * @return the passportNumber
	 */
	public String getPassportNumber() {
		return passportNumber;
	}

	/**
	 * @param passportNumber the passportNumber to set
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	/**
	 * @return the aadharNumber
	 */
	public String getAadharNumber() {
		return aadharNumber;
	}

	/**
	 * @param aadharNumber the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	/**
 	 * @return the staffNumber
 	 */
 	public String getStaffNumber() {
 		return staffNumber;
 	}

 	/**
 	 * @param staffNumber the staffNumber to set
 	 */
 	public void setStaffNumber(String staffNumber) {
 		this.staffNumber = staffNumber;
 	}

	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the bioMetricId
	 */
	public int getBioMetricId() {
		return bioMetricId;
	}

	/**
	 * @param bioMetricId the bioMetricId to set
	 */
	public void setBioMetricId(int bioMetricId) {
		this.bioMetricId = bioMetricId;
	}
	
	public Date getEsiDateofJoin() {
		return esiDateofJoin;
	}

	public void setEsiDateofJoin(Date esiDateofJoin) {
		this.esiDateofJoin = esiDateofJoin;
	}

	public double getEsiPercentage() {
		return esiPercentage;
	}

	public void setEsiPercentage(double esiPercentage) {
		this.esiPercentage = esiPercentage;
	}

	public Date getPfDateofJoin() {
		return pfDateofJoin;
	}

	public void setPfDateofJoin(Date pfDateofJoin) {
		this.pfDateofJoin = pfDateofJoin;
	}

	public double getPfPercentage() {
		return pfPercentage;
	}

	public void setPfPercentage(double pfPercentage) {
		this.pfPercentage = pfPercentage;
	}
	public String getPfNo() {
		return pfNo;
	}

	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}

	public String getEsiNo() {
		return esiNo;
	}

	public void setEsiNo(String esiNo) {
		this.esiNo = esiNo;
	}

	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	public String getSubCastName() {
		return subCastName;
	}

	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	 }

	 public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	 }

	 public Date getContractEndDate() {
		return contractEndDate;
	 }

	 public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	 }  
	 
	 public String getCustImageName() {
			return custImageName;
		}

		public void setCustImageName(String custImageName) {
			this.custImageName = custImageName;
		}

		public String getCustImagePath() {
			return custImagePath;
		}

		public void setCustImagePath(String custImagePath) {
			this.custImagePath = custImagePath;
		}

		public String getCustThumbNail() {
			return custThumbNail;
		}

		public void setCustThumbNail(String custThumbNail) {
			this.custThumbNail = custThumbNail;
		}

	/**
		 * @return the hostelCategoryId
		 */
		@Column(name = "hostelCategoryId",columnDefinition = "int default 0")
		public long getHostelCategoryId() {
			return hostelCategoryId;
		}
		/**
		 * @param hostelCategoryId the hostelCategoryId to set
		 */
		public void setHostelCategoryId(long hostelCategoryId) {
			this.hostelCategoryId = hostelCategoryId;
		}
	    @Transient
	    public double getTotalAllowance() {
			return totalAllowance;
		}

		public void setTotalAllowance(double totalAllowance) {
			this.totalAllowance = totalAllowance;
		}
		@Transient
		public double getTotalDeduction() {
			return totalDeduction;
		}

		public void setTotalDeduction(double totalDeduction) {
			this.totalDeduction = totalDeduction;
		}
	 
	 
	/* public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}*/
	@Transient
	 public String getPayrollEffectiveFrom() {
		return payrollEffectiveFrom;
	}
	public void setPayrollEffectiveFrom(String payrollEffectiveFrom) {
		this.payrollEffectiveFrom = payrollEffectiveFrom;
	}
	@Transient
	 public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	 @Transient
	public double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
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
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	 
	/*@Transient
	public Salary getSalary() 
	{
		return this.salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}*/
	
	/* public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Date getLoanTakenDate() {
		return loanTakenDate;
	}
	public void setLoanTakenDate(Date loanTakenDate) {
		this.loanTakenDate = loanTakenDate;
	}
	public int getInstallments() {
		return installments;
	}
	public void setInstallments(int installments) {
		this.installments = installments;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}*/
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
	public boolean isAccountExpired() {
	    return accountExpired;
	}

	/**
	 * @param accountExpired the accountExpired to set
	 */
	public void setAccountExpired(boolean accountExpired) {
	    this.accountExpired = accountExpired;
	}

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
	public long getImageId() {
 		return imageId;
 	}
 	public void setImageId(long imageId) {
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
	public ViewStaffPersonAccountDetails()
    {
        super();
    }

	public String getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(String academicYearId) {
		this.academicYearId = academicYearId;
	}
	public ViewStaffPersonAccountDetails(long accountId, long custId,
			String username, String sharePrivacy, String userOnlineNow,
			String academicYearId,String status, String qualification, long staffId,
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
        if (!(o instanceof ViewStaffPersonAccountDetails)) return false;
        
        final ViewStaffPersonAccountDetails staffDetails = (ViewStaffPersonAccountDetails) o;
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
    	ViewStaffPersonAccountDetails staff = (ViewStaffPersonAccountDetails) object;
        if(ObjectFunctions.isNullOrEmpty(staff)){
        	return 0;
        }else
        	return this.getDisplayName().compareToIgnoreCase(staff.getDisplayName());
    }
    @Id
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
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(getFirstName());
                
                
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
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(" ");
                ret.append(getLastName());
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
	@Column(name = "experience", nullable = true, length = 4)
	public double getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(double experience) {
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
	    	//log.debug(getImagePath().concat(getThumbNail())); 
	    	//return getImagePath().concat(getThumbNail());
	    	return getImagePath();
	    }
	    return UserImage.getStudyImageNotFoundFile();
	}
	@Transient
	public String getHrefOriginalAttachmentFilePath()
	{
	    if(!StringFunctions.isNullOrEmpty(getImagePath()))
	    {
	        //return getImagePath().substring(1).concat(getImageName());
	    	return getImagePath();
	    }
	    return UserImage.getStudyImageNotFoundFile();
	}
	@Transient
	public String getHrefAdjustedAttachmentFilePath()
	{
	    if(!StringFunctions.isNullOrEmpty(getImagePath()))
	    {
	        //return getImagePath().substring(1).concat(getThumbNail());
	    	return getImagePath();
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
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getDateofJoining());
	}
	@Transient
	public String getStaffJoingDate() {
		return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getDateofJoining());
	}
	@Transient
	public String getStaffFullNameWithRole(){
		return getDisplayName()+" ("+getRoleDescription()+")";
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
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
	public String getStaffExperiencestr() 
	{
		Date d1 = this.getDateofJoining();
		Date d2 = new Date();
		
		long years = (long)(d2.getYear() - d1.getYear() );
		long months = (long)(d2.getMonth() - d1.getMonth()) ;
		
		return years+"." + Math.abs(months); 
	}
	@Transient
	public String getSchoolIdCardAttachmentFilePath() {
	//String imagePath=ServletActionContext.getServletContext().getRealPath(getHrefOriginalAttachmentFilePath());
    //String hostUrl = ServletActionContext.getServletContext().getInitParameter("hostUrl");
    //hostUrl = hostUrl + "/images/header/headerPdfPng.png";
    return getHrefOriginalAttachmentFilePath();
	}
	/*@Transient
	public String getLoanTakenDateStr() {
		return DateFormatter.formatDate(DateFormatter.MMMDYYYY_PATTERN, this.getLoanTakenDate());
	}*/

	public String getAcademicYearStatus() {
		return academicYearStatus;
	}

	public void setAcademicYearStatus(String academicYearStatus) {
		this.academicYearStatus = academicYearStatus;
	}

	@Transient
	public String getContractStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getContractStartDate());
	}
	@Transient
	public String getContractEndDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getContractEndDate());
	}
	@Transient
	public String getPfDateofJoinStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getPfDateofJoin());
	}
	@Transient
	public String  getEsiDateofJoinStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this. getEsiDateofJoin());
	}
	@Transient
	public double getEarnedLeavesCount() {
		return earnedLeavesCount;
	}

	public void setEarnedLeavesCount(double earnedLeavesCount) {
		this.earnedLeavesCount = earnedLeavesCount;
	}
	@Transient
	public String getCustomerAttachmentFilePath() {
		//String imagePath=ServletActionContext.getServletContext().getRealPath(getHrefCustomerAdjustedAttachmentFilePath());
		return getHrefCustomerAdjustedAttachmentFilePath();
	}
	
	@Transient
    public String getCustomerReportAttachments() {
        String imagePath=ServletActionContext.getServletContext().getRealPath("userFilessvms/svmsColorReport.png");
        return imagePath;
    }
	
	@Transient
	public String getHrefCustomerAdjustedAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getCustImagePath())) {
			//return getCustImagePath().substring(1).concat(getCustImageName());
			return getCustImagePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}
	@Transient
	public String getParentAddress() {
		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
		if (!StringFunctions.isNullOrEmpty(this.addressLine1)) {
			buffer.append(getAddressLine1().trim());
			buffer.append(", ");
		}
		if (!StringFunctions.isNullOrEmpty(this.city)) {			
			buffer.append(getCity().trim());
			buffer.append(", ");
		}
		if (!StringFunctions.isNullOrEmpty(this.state)) {			
			buffer.append(getState().trim());
			buffer.append(", ");			
		}
		if (!StringFunctions.isNullOrEmpty(this.postalCode)) {			
			buffer.append(getPostalCode());
			buffer.append(", ");
		}
		if (!StringFunctions.isNullOrEmpty(this.mobileNumber)) {
			
			buffer.append("PH - ");
			buffer.append(getMobileNumber());
		}
		String parentAddr = buffer.toString().trim();
		if(parentAddr.endsWith(",")){
			parentAddr = parentAddr.substring(0, parentAddr.length() -1);
		}
		return parentAddr;
	}
	
	@Transient
	public String getFullNameRoleName()
	{
		if(!StringFunctions.isNullOrEmpty(getFullName()))
		{
			if(!StringFunctions.isNullOrEmpty(getRoleDescription()))
			{
				return getFullName() + " (" + getRoleDescription() + ")";
			}
			return getFullName();
		}
		return "";
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	
	@Transient
	public String getLibranSignature() {
		String imagePath = ServletActionContext.getServletContext().getRealPath("userFiles/"+getCustId()+"/"+"libranSign"+"/"+getCustId()+".png");
		return imagePath;
	}	
	@Transient
	public String getSpecimanSignature() {
		String imagePath = ServletActionContext.getServletContext().getRealPath("userFiles/"+getCustId()+"/"+"specimanSign"+"/"+getCustId()+".png");
		return imagePath;
	}
	@Transient
	public String getBarcodeImage() {
		return getBarcode();
	}
	@Transient
	public String getAccountIdAndFullName() {
		if(ObjectFunctions.isNullOrEmpty(getAccountId()))
			return "";
		else
			return getAccountId()+","+getPersonFirstLastNameOnly();
	   }
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	@Transient
	public String getDigitalSignature() {
		return getPrincipalDigitalSignaturePath();
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the fatherContactNumber
	 */
	public String getFatherContactNumber() {
		return fatherContactNumber;
	}

	/**
	 * @param fatherContactNumber the fatherContactNumber to set
	 */
	public void setFatherContactNumber(String fatherContactNumber) {
		this.fatherContactNumber = fatherContactNumber;
	}
	
}
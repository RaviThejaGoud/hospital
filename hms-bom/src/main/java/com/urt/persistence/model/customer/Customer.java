package com.urt.persistence.model.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.mobile.SchoolArea;
import com.urt.persistence.model.study.SyllabusType;
import com.urt.persistence.model.subscription.PackageDetails;
import com.urt.persistence.model.user.User;
/*
 * @create new table customer.
 */
@Entity
@Table(name = "customer")
public class Customer  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String customerName;                  
    protected String organization;                  
    protected boolean status;
    protected String subscriptionType;
    protected String custEmail;
    protected String firstName;
    protected String lastName;
    protected String contactNumber;
    protected String webSiteUrl;
    protected String mobileNumber;
    protected String educationalDistrict;
    protected String revenueDistrict;
    protected String accountType;
    //protected Set<Subscription> customerSubscriptions;
    protected String password;
   /* protected Set<Vehicles> vehicles;
    protected Set<Route> routes;*/
    protected UserImage customerOrgImage;
    public boolean checkEmailService;
    public boolean checkMobileService;
    private PackageDetails packageDetails;
    protected String customerShortName;
    protected String sender;
    protected String schoolCode;
    private long orgnizationTypeId;
    protected String reportColorCode;
    protected Set<SyllabusType> syllabusType;
    protected String recognisedBy;
    private String societyName;
    protected String boardOfEducation;
    public boolean hostelModuleStatus;
    public boolean transportModuleStatus;
    protected Address address;
    protected String deleteInvoicePassword;
    protected String modifyInvoicePassword;
    private long allowedTotalSms;
    private long startInvoiceNumber;
    private String affiliationNumber;
    private long organizationSubTypeId;
    private MasterCustomer masterCustomer;
    private char paymentType;
    private boolean academicWiseFeeReceipt;
    private String customerVision;
    private String customerMission;
    private String feeReceiptNoWithCustName;
    private String organizationLevel ;
	private WashRoom washRoom;
    protected String committedFeeStatus;
    protected long orgId;
    public boolean checkMobilePaymentService;
    private long stateId;
    protected String sendSmsUpdatedMobile ;
    protected String senderIdDesc;
    private String customerInActiveDescription;
    protected String contactEmail;
    protected String contactPassword;
    protected String mobileType;
    protected Set<SchoolArea> schoolAreas;//this for mobile app
    protected String parentPermissionStatus;
    protected String staffPermissionStatus; // need to capture the customer have late permission for a day 
    protected String mailChimpUserName;
    protected String mailChimpPassword;
    protected String mailChimpAPIKey;
    private long smsServiceProviderId;
    protected String barcodeStatus;
    protected double smsCost;
    public boolean checkParentSmsService;
    protected String feeReceiptModel;

	protected String standardType;
    protected String showAddreesInFeeReceipt;
    protected String showBalanceAmountInFeeReceipt;
    public boolean checkTransportService;
    public String countryName;
    protected String accountModuleUsing;
    protected String chalanaGenerationStatus;
    protected boolean addStudentsSameAdmissionNumber;
    protected UserImage principalDigitalSignature;
    protected String showPreviousYearPendingFee;
    protected String displayBloogGroupInStudentIdCards;
    protected String showTotalOrPaidAmount;
    protected String allowPastDatesForPayments;
    protected Long  allowedPastDatesForPayments;
    protected String  paymentPastDates;
    protected String allowDiscountOptOnOtherRoles;
    
    protected CustomerPreferences preferences;
    public boolean checkAttendanceEmailService;
    private boolean checkAssignmentSmsService;
    public String alphaNumericRollNumber;
  
	/*Ganesh - Default we will show paid amount if any customer will ask we will show total amount. */
    @Column(name = "showTotalOrPaidAmount", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
    public String getShowTotalOrPaidAmount() {
		return showTotalOrPaidAmount;
	}

	public void setShowTotalOrPaidAmount(String showTotalOrPaidAmount) {
		this.showTotalOrPaidAmount = showTotalOrPaidAmount;
	}
    
    @Column(name = "displayBloogGroupInStudentIdCards", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getDisplayBloogGroupInStudentIdCards() {
		return displayBloogGroupInStudentIdCards;
	}

	public void setDisplayBloogGroupInStudentIdCards(
			String displayBloogGroupInStudentIdCards) {
		this.displayBloogGroupInStudentIdCards = displayBloogGroupInStudentIdCards;
	}

	@Column(name = "showPreviousYearPendingFee", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
    public String getShowPreviousYearPendingFee() {
		return showPreviousYearPendingFee;
	}

	public void setShowPreviousYearPendingFee(String showPreviousYearPendingFee) {
		this.showPreviousYearPendingFee = showPreviousYearPendingFee;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="principalDigitalSignatureId", insertable=true, updatable=true)
    public UserImage getPrincipalDigitalSignature() {
		return principalDigitalSignature;
	}

	public void setPrincipalDigitalSignature(UserImage principalDigitalSignature) {
		this.principalDigitalSignature = principalDigitalSignature;
	}

	@Column(name = "addStudentsSameAdmissionNumber", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
   	@Type(type="yes_no")
    public boolean isAddStudentsSameAdmissionNumber() {
		return addStudentsSameAdmissionNumber;
	}

	public void setAddStudentsSameAdmissionNumber(
			boolean addStudentsSameAdmissionNumber) {
		this.addStudentsSameAdmissionNumber = addStudentsSameAdmissionNumber;
	}
   
    
    @Transient
    public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
    
	@Column(name = "checkTransportService", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	@Type(type="yes_no")
	@javax.xml.bind.annotation.XmlTransient()
    public boolean isCheckTransportService() {
		return checkTransportService;
	}
	public void setCheckTransportService(boolean checkTransportService) {
		this.checkTransportService = checkTransportService;
	}
    @Column(name = "feeReceiptModel", nullable = true, length = 1,columnDefinition="char(10) default 'General'")
    public String getFeeReceiptModel() {
		return feeReceiptModel;
	}
	public void setFeeReceiptModel(String feeReceiptModel) {
		this.feeReceiptModel = feeReceiptModel;
	}
	
	@Column(name = "checkParentSmsService", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	@javax.xml.bind.annotation.XmlTransient()
    public boolean isCheckParentSmsService() {
		return checkParentSmsService;
	}
	public void setCheckParentSmsService(boolean checkParentSmsService) {
		this.checkParentSmsService = checkParentSmsService;
	}
    
    @Column(name = "smsCost",nullable = false, columnDefinition=" double default 0.0")
    public double getSmsCost() {
		return smsCost;
	}
	public void setSmsCost(double smsCost) {
		this.smsCost = smsCost;
	}
	@Column(name = "barcodeStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    public String getBarcodeStatus() {
		return barcodeStatus;
	}
	public void setBarcodeStatus(String barcodeStatus) {
		this.barcodeStatus = barcodeStatus;
	}
    
    public long getSmsServiceProviderId() {
		return smsServiceProviderId;
	}
	public void setSmsServiceProviderId(long smsServiceProviderId) {
		this.smsServiceProviderId = smsServiceProviderId;
	}
    @Column(name = "parentPermissionStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    public String getParentPermissionStatus() {
		return parentPermissionStatus;
	}
	public void setParentPermissionStatus(String parentPermissionStatus) {
		this.parentPermissionStatus = parentPermissionStatus;
	}
	@Column(name = "staffPermissionStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getStaffPermissionStatus() {
		return staffPermissionStatus;
	}
	public void setStaffPermissionStatus(String staffPermissionStatus) {
		this.staffPermissionStatus = staffPermissionStatus;
	}
	
	@OneToMany
 	@JoinColumn(name="custId")
    public Set<SchoolArea> getSchoolAreas() {
		return schoolAreas;
	}
	public void setSchoolAreas(Set<SchoolArea> schoolAreas) {
		this.schoolAreas = schoolAreas;
	}
	
    public void addSchoolAreas(SchoolArea schoolAreas) {
		if (ObjectFunctions.isNullOrEmpty(getSchoolAreas())) {
			this.schoolAreas = new HashSet<SchoolArea>();
		}
		getSchoolAreas().add(schoolAreas);
	}
    
    
	@Column(name = "contactEmail", nullable = true, length = 128)
    public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactPassword() {
		return contactPassword;
	}
	public void setContactPassword(String contactPassword) {
		this.contactPassword = contactPassword;
	}
	@Column(name="senderIdDesc",nullable=true ,length = 1024,columnDefinition="varchar(1024) default null")
    public String getSenderIdDesc() {
		return senderIdDesc;
	}
	public void setSenderIdDesc(String senderIdDesc) {
		this.senderIdDesc = senderIdDesc;
	}
    @Column(name = "sendSmsUpdatedMobile", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    public String getSendSmsUpdatedMobile() {
		return sendSmsUpdatedMobile;
	}
	public void setSendSmsUpdatedMobile(String sendSmsUpdatedMobile) {
		this.sendSmsUpdatedMobile = sendSmsUpdatedMobile;
	}
	@Column(name = "mobileType", nullable = true, length = 1,columnDefinition="char(1) default 'P'")
    public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
    @Column(name = "orgId",nullable = false, columnDefinition=" int default 0")
	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	@Column(name = "committedFeeStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    public String getCommittedFeeStatus() {
		return committedFeeStatus;
	}

	public void setCommittedFeeStatus(String committedFeeStatus) {
		this.committedFeeStatus = committedFeeStatus;
	}

	/*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="custId") 
    public Set<StaffRoom> getStaffRoom() {
		return staffRoom;
	}

	public void setStaffRoom(Set<StaffRoom> staffRoom) {
		this.staffRoom = staffRoom;
	}
*/
	//@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="washRoomId", insertable=true, updatable=true)
    public WashRoom getWashRoom() {
		return washRoom;
	}

	public void setWashRoom(WashRoom washRoom) {
		this.washRoom = washRoom;
	}



	@Column(name = "feeReceiptNoWithCustName", nullable = true, length = 10)
	public String getFeeReceiptNoWithCustName() {
		return feeReceiptNoWithCustName;
	}

	/**
	 * @param feeReceiptNoWithCustName the feeReceiptNoWithCustName to set
	 */
	public void setFeeReceiptNoWithCustName(String feeReceiptNoWithCustName) {
		this.feeReceiptNoWithCustName = feeReceiptNoWithCustName;
	}

	/**
	 * @return the customerVision
	 */
	@Column(name="customerVision",nullable=true ,length = 1024,columnDefinition="varchar(2050) default null")
	public String getCustomerVision() {
		return customerVision;
	}

	/**
	 * @param customerVission the customerVission to set
	 */
	public void setCustomerVision(String customerVision) {
		this.customerVision = customerVision;
	}

	/**
	 * @return the customerMission
	 */
	@Column(name="customerMission",nullable=true,length = 1024,columnDefinition="varchar(2050) default null")
	public String getCustomerMission() {
		return customerMission;
	}

	/**
	 * @param customerMission the customerMission to set
	 */
	public void setCustomerMission(String customerMission) {
		this.customerMission = customerMission;
	}




    
    /**
	 * @return the academicWiseFeeReceipt
	 */
    @Column(name = "academicWiseFeeReceipt", nullable = false, length = 1, columnDefinition = "char(1) default 'N'")
	public boolean isAcademicWiseFeeReceipt() {
		return academicWiseFeeReceipt;
	}

	/**
	 * @param academicWiseFeeReceipt the academicWiseFeeReceipt to set
	 */
	public void setAcademicWiseFeeReceipt(boolean academicWiseFeeReceipt) {
		this.academicWiseFeeReceipt = academicWiseFeeReceipt;
	}
    
    /**
	 * @return the paymentType
	 */
    @Column(name = "paymentType", nullable = true, length = 2,columnDefinition="char(1) default 'T'")
	public char getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(char paymentType) {
		this.paymentType = paymentType;
	}

	@ManyToOne
	@JoinColumn(name="masterCustId", insertable=false, updatable=false)
	@javax.xml.bind.annotation.XmlTransient()
    public MasterCustomer getMasterCustomer() {
		return masterCustomer;
	}

	public void setMasterCustomer(MasterCustomer masterCustomer) {
		this.masterCustomer = masterCustomer;
	}

	@Column(name = "affiliationNumber", nullable = true, length = 20)
	public String getAffiliationNumber() {
		return affiliationNumber;
	}

	public void setAffiliationNumber(String affiliationNumber) {
		this.affiliationNumber = affiliationNumber;
	}

	@Column(name = "startInvoiceNumber", nullable = false ,columnDefinition="bigint(20) default 0")
    public long getStartInvoiceNumber() {
		return startInvoiceNumber;
	}

	public void setStartInvoiceNumber(long startInvoiceNumber) {
		this.startInvoiceNumber = startInvoiceNumber;
	}

	@Column(name = "allowedTotalSms", nullable = false ,columnDefinition="bigint(20) default 0")
    public long getAllowedTotalSms() {
		return allowedTotalSms;
	}

	public void setAllowedTotalSms(long allowedTotalSms) {
		this.allowedTotalSms = allowedTotalSms;
	}
  
    public String getDeleteInvoicePassword() {
		return deleteInvoicePassword;
	}

	public void setDeleteInvoicePassword(String deleteInvoicePassword) {
		this.deleteInvoicePassword = deleteInvoicePassword;
	}

	public String getModifyInvoicePassword() {
		return modifyInvoicePassword;
	}

	public void setModifyInvoicePassword(String modifyInvoicePassword) {
		this.modifyInvoicePassword = modifyInvoicePassword;
	}

	/**
     * @return the address
     */
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="addressId" ,insertable=true, updatable=true) 
    @javax.xml.bind.annotation.XmlTransient()
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "transportModuleStatus", nullable = false, length = 1, columnDefinition = "char(1) default 'N'")
    @Type(type = "yes_no")
    @javax.xml.bind.annotation.XmlTransient()
    public boolean isTransportModuleStatus() {
		return this.transportModuleStatus;
	}
    
	public void setTransportModuleStatus(boolean transportModuleStatus) {
		this.transportModuleStatus = transportModuleStatus;
	}

	/**
     * @return the isHostel
     */
    @Column(name = "hostelModuleStatus", nullable = false, length = 1, columnDefinition = "char(1) default 'N'")
    @Type(type = "yes_no")
    @javax.xml.bind.annotation.XmlTransient()
    public boolean isHostelModuleStatus() {
		return hostelModuleStatus;
	}
	public void setHostelModuleStatus(boolean hostelModuleStatus) {
		this.hostelModuleStatus = hostelModuleStatus;
	}

    public String getRecognisedBy() {
		return recognisedBy;
	}
    public void setRecognisedBy(String recognisedBy) {
	this.recognisedBy = recognisedBy;
    }
    
    public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
    public String getBoardOfEducation() {
	return boardOfEducation;
    }

    public void setBoardOfEducation(String boardOfEducation) {
	this.boardOfEducation = boardOfEducation;
    }

	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "syllabusTypeInfo", joinColumns = { @JoinColumn(name = "customerId") },
       inverseJoinColumns = { @JoinColumn(name = "syllabusTypeId") })
   @javax.xml.bind.annotation.XmlTransient()
    public Set<SyllabusType> getSyllabusType() {
    	if(syllabusType == null)
        {
    		syllabusType = new HashSet<SyllabusType>();
        }
		return syllabusType;
	}

	public void setSyllabusType(Set<SyllabusType> syllabusType) {
		this.syllabusType = syllabusType;
	}

	public String getReportColorCode() {
		return reportColorCode;
	}

	public void setReportColorCode(String reportColorCode) {
		this.reportColorCode = reportColorCode;
	}

	/**
	 * @return the orgnizationType
	 */
    @Column(name = "orgnizationTypeId", nullable = false ,columnDefinition="bigint(20) default 0")
	public long getOrgnizationTypeId() {
		return orgnizationTypeId;
	}

	/**
	 * @param orgnizationType the orgnizationType to set
	 */
	public void setOrgnizationTypeId(long orgnizationTypeId) {
		this.orgnizationTypeId = orgnizationTypeId;
	}

	/**
	 * @return the schoolCode
	 */
    @Column(name = "schoolCode", nullable = true, length = 20)
	public String getSchoolCode() {
		return schoolCode;
	}

	/**
	 * @param schoolCode the schoolCode to set
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="packageDetailsId", insertable=true, updatable=true)
	@javax.xml.bind.annotation.XmlTransient()
	public PackageDetails getPackageDetails() {
		return packageDetails;
	}


	public void setPackageDetails(PackageDetails packageDetails) {
		this.packageDetails = packageDetails;
	}

	public Customer() {
        
    }

    public Customer(long id) {
        setId(id);
    }
    
    @Column(name = "mobileNumber", nullable = true, length = 128)
    public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "checkEmailService", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
   	@javax.xml.bind.annotation.XmlTransient()
    public boolean isCheckEmailService() {
		return checkEmailService;
	}
	
	public void setCheckEmailService(boolean checkEmailService) {
		this.checkEmailService = checkEmailService;
	}
	@Column(name = "checkMobileService", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	@Type(type="yes_no")
	@javax.xml.bind.annotation.XmlTransient()
	public boolean isCheckMobileService() {
		return checkMobileService;
	}
	public void setCheckMobileService(boolean checkMobileService) {
		this.checkMobileService = checkMobileService;
	}
	@Column(name = "checkMobilePaymentService", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	@javax.xml.bind.annotation.XmlTransient()
	public boolean isCheckMobilePaymentService() {
		return checkMobilePaymentService;
	}
	public void setCheckMobilePaymentService(boolean checkMobilePaymentService) {
		this.checkMobilePaymentService = checkMobilePaymentService;
	}

	/**
	 * @return the customer name.
	 */
    @Column(name = "customerName", length = 128)
    public String getCustomerName() {
		return customerName;
	}

    /**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
     * @return Returns boolean value.
     */
	@Column(name = "status", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	@Type(type="yes_no")
	public boolean isStatus() {
		return status;
	}
	@Transient
	public boolean getStatus() {
        return status;
	}
	/**
	* @param status the status to set
	*/
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the subscriptionType.
	 */
	@Column(name = "subscriptionType", nullable = true, length = 5)
	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	/**
	 * @return the custEmail.
	 */
	@Column(name = "custEmail", nullable = false, unique=true, length = 128)
	public String getCustEmail() {
		return custEmail;
	}
	
	/**
	* @param custEmail the custEmail to set
	*/
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	/**
	 * @return the firstName.
	 */
	@Column(name = "firstName", nullable = true, length = 128)
	public String getFirstName() {
		return firstName;
	}

	/**
	* @param firstName the firstName to set
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName.
	 */
	@Column(name = "lastName", nullable = true, length = 128)
	public String getLastName() {
		return lastName;
	}

	/**
	* @param lastName the lastName to set
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the accountType.
	 */
	/* 'M'= master admin , 'C'= Customer Account , 'D'= Demo or Validation Account */
	@Column(name = "accountType", nullable = true, length = 5,columnDefinition="varchar(1) default 'C'")
	public String getAccountType() {
		return accountType;
	}

	/**
	* @param accountType the accountType to set
	*/
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the websites.
	 */
	@Column(name = "contactNumber", nullable = true, length = 128)
		public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Column(name = "webSiteUrl", nullable = true, length = 128)
	public String getWebSiteUrl() {
		return webSiteUrl;
	}

	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}
	
	/**
	 * @return the customerShotName
	 */
	public String getCustomerShortName() {
		return customerShortName;
	}

	/**
	 * @param customerShotName the customerShotName to set
	 */
	public void setCustomerShortName(String customerShortName) {
		this.customerShortName = customerShortName;
	}
	@Column(name = "sender", nullable = true, length = 10)
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Override
	public int compareTo(Object object) {
        
		Customer target = (Customer) object;
        if (target.getCustEmail() != null && this.getCustEmail() != null)
        {
            if(this.getCustEmail().equalsIgnoreCase(target.getCustEmail()))
                return 0;
            else 
               return this.getCustEmail().compareToIgnoreCase(target.getCustEmail());
                 
        }
        return 0;
    }
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final Customer customer = (Customer) o;

        if (custEmail != null ? !custEmail.equals(customer.getCustEmail()) : customer.getCustEmail() != null) return false;

        return true;
    }

    public int hashCode() {
        return (custEmail != null ? custEmail.hashCode() : 0);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("customerName", this.customerName)
        .append("organization", this.organization)
        .append("status", this.status)
        .append("hostelModuleStatus", this.hostelModuleStatus)
        .append("transportModuleStatus", this.transportModuleStatus)
        .append("subscriptionType", this.subscriptionType)
        .append("custEmail",this.custEmail)
        .append("firstName",this.firstName)
        .append("lastName",this.lastName)
        .append("accountType", this.accountType)
        .append("societyName",this.societyName)
        .toString();
	}

	/**
	 * @return the organization
	 */
	@Column(name = "organization", nullable = true, length = 128)
	public String getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}


	/**
	 * @return the customerOrgImage
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
	 */
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="custImageId", insertable=true, updatable=true) 
	@javax.xml.bind.annotation.XmlTransient()
	public UserImage getCustomerOrgImage() {
	return customerOrgImage;
	}

	public void setCustomerOrgImage(UserImage customerOrgImage) {
		this.customerOrgImage = customerOrgImage;
	}
	
    
    /**
	 * @return the password
	 */
    @Transient
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
    @Transient
	public void setPassword(String password) {
		this.password = password;
	}
    @Transient
    public String getCustomerFullPersonName() {
    	StringBuffer sbf = new StringBuffer();
    	if(!StringFunctions.isNullOrEmpty(this.getFirstName()))
    	{
    		sbf.append(this.getFirstName());
    	}
    	if(!StringFunctions.isNullOrEmpty(this.getLastName()))
    	{
    		sbf.append(" "+this.getLastName());
    	}
    	return sbf.toString();
    }
/*    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="custId") 
	public Set<Vehicles> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicles> vehicles) {
		this.vehicles = vehicles;
	}
	public void addVehicles(Vehicles vehicles) {
		getVehicles().add(vehicles);
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="custId") 
	public Set<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}
	public void addRoutes(Route routes) {
		getRoutes().add(routes);
	}*/
	
	/**
	 * @return the educationalDistrict
	 */
	public String getEducationalDistrict() {
		return educationalDistrict;
	}

	/**
	 * @param educationalDistrict the educationalDistrict to set
	 */
	public void setEducationalDistrict(String educationalDistrict) {
		this.educationalDistrict = educationalDistrict;
	}

	/**
	 * @return the revenueDistrict
	 */
	public String getRevenueDistrict() {
		return revenueDistrict;
	}

	/**
	 * @param revenueDistrict the revenueDistrict to set
	 */
	public void setRevenueDistrict(String revenueDistrict) {
		this.revenueDistrict = revenueDistrict;
	}
	
	@Transient
	public String getCustomerFilePathLogo() {
		String imagePath=ServletActionContext.getServletContext().getRealPath(getCustomerLogo());
    return imagePath;
	}
	
	@Transient
	public String getCustomerLogo()
	{
		if(ObjectFunctions.isNullOrEmpty(this.getCustomerOrgImage()))
   		{
   			return "";
   		}   	
   		return this.getCustomerOrgImage().getAdjustedAttachmentFilePath();
	}
	// For SMSAPP  *** Ramarao
	@Transient
	public String getCustomerLogoPath() {
		if(ObjectFunctions.isNullOrEmpty(this.getCustomerOrgImage())) {
   			return "";
   		}   	
   		return this.getCustomerOrgImage().getCropAttachmentFilePath();
	}
	@Transient
	public void addSyllabusTypeInfo(SyllabusType syllabusType) {
		getSyllabusType().add(syllabusType);
    }
	@Transient
	public String getCustomerFormattedAddress() {
		if(ObjectFunctions.isNullOrEmpty(getAddress()))
			return "";
		else
			return getAddress().getStreetName()+", "+getAddress().getAddressLine1()+", "+getAddress().getCity()+" - "+getAddress().getPostalCode();
    }
	@Transient
    public String getOrganizationFullAddress() {
		if(ObjectFunctions.isNullOrEmpty(getAddress()))
			return "";
		else
		{
			 StringBuffer ret = new StringBuffer(10);
			if(StringFunctions.isNotNullOrEmpty(getAddress().getAddressLine1()))
			{
				ret.append(getAddress().getAddressLine1());
			}
			if(StringFunctions.isNotNullOrEmpty(getAddress().getStreetName()))
			{
				if(ret.length() > 0)
					ret.append(", ");
				ret.append(getAddress().getStreetName());
					
			}
			if(StringFunctions.isNotNullOrEmpty(getAddress().getCity()))
			{
				if(ret.length() > 0)
					ret.append(", ");
				ret.append(getAddress().getCity());
			}
			if(StringFunctions.isNotNullOrEmpty(getAddress().getState()))
			{
				if(ret.length() > 0)
					ret.append(", ");
				ret.append(getAddress().getState());
			}
			if(StringFunctions.isNotNullOrEmpty(getAddress().getPostalCode()))
			{
				if(ret.length() > 0)
					ret.append(", ");
				ret.append(getAddress().getPostalCode());
			}
			return ret.toString();
		}
    }

	@Column(name = "organizationSubTypeId", nullable = false ,columnDefinition="bigint(20) default 0")
	public long getOrganizationSubTypeId() {
		return organizationSubTypeId;
	}

	public void setOrganizationSubTypeId(long organizationSubTypeId) {
		this.organizationSubTypeId = organizationSubTypeId;
	}
	
	public void copyFrom(Customer customer){
		this.setCustEmail(customer.getCustEmail());
		this.setOrganization(customer.getOrganization());
		this.setContactNumber(customer.getContactNumber());
		this.setMobileNumber(customer.getMobileNumber());
		this.setWebSiteUrl(customer.getWebSiteUrl());
		this.setRevenueDistrict(customer.getRevenueDistrict());
		this.setEducationalDistrict(customer.getEducationalDistrict());
		this.setReportColorCode(customer.getReportColorCode());
		this.setSchoolCode(customer.getSchoolCode());
		this.setDeleteInvoicePassword(customer.getDeleteInvoicePassword());
		this.setModifyInvoicePassword(customer.getModifyInvoicePassword());
		this.setRecognisedBy(customer.getRecognisedBy());
		this.setBoardOfEducation(customer.getBoardOfEducation());
		this.setAffiliationNumber(customer.getAffiliationNumber());
		this.setStartInvoiceNumber(customer.getStartInvoiceNumber());
		this.setPaymentType('P');
		this.setAcademicWiseFeeReceipt(customer.isAcademicWiseFeeReceipt());
		this.setCustomerMission(customer.getCustomerMission());
		this.setCustomerVision(customer.getCustomerVision());
		this.setFeeReceiptNoWithCustName(customer.getFeeReceiptNoWithCustName());
		this.setOrganizationLevel(customer.getOrganizationLevel());
		this.setCommittedFeeStatus(customer.getCommittedFeeStatus());
		this.setParentPermissionStatus(customer.getParentPermissionStatus());
		this.setStaffPermissionStatus(customer.getStaffPermissionStatus());
		this.setBarcodeStatus(customer.getBarcodeStatus());
		this.setFeeReceiptModel(customer.getFeeReceiptModel());
		this.setShowAddreesInFeeReceipt(customer.getShowAddreesInFeeReceipt());
		this.setShowBalanceAmountInFeeReceipt(customer.getShowBalanceAmountInFeeReceipt());
		this.setAccountModuleUsing(customer.getAccountModuleUsing());
		this.setChalanaGenerationStatus(customer.getChalanaGenerationStatus());
		this.setAddStudentsSameAdmissionNumber(customer.isAddStudentsSameAdmissionNumber());
		this.setDisplayBloogGroupInStudentIdCards(customer.getDisplayBloogGroupInStudentIdCards());
		this.setShowTotalOrPaidAmount(customer.getShowTotalOrPaidAmount());
		this.setSocietyName(customer.getSocietyName());
		this.setAlphaNumericRollNumber(customer.getAlphaNumericRollNumber());
		this.setAllowPastDatesForPayments(customer.getAllowPastDatesForPayments());
		this.setAllowedPastDatesForPayments(customer.getAllowedPastDatesForPayments());
		this.setAllowDiscountOptOnOtherRoles(customer.getAllowDiscountOptOnOtherRoles());
		//this.setDiceCode(customer.getDiceCode());
		
	}
	@Column(name = "organizationLevel", nullable = false, length = 1, columnDefinition = "char(1) default 'S'")
	public String getOrganizationLevel() {
		return organizationLevel;
	}
	public void setOrganizationLevel(String organizationLevel) {
		this.organizationLevel = organizationLevel;
	}
	 @Transient
	   public String getSchoolOrCollege() {
		   if("S".equalsIgnoreCase(getOrganizationLevel()))
				return "school";
	   		else
				return "college";
	   }
	 
	/* @Transient
	public void addStaffRooms(StaffRoom staffRoom) {
		if(ObjectFunctions.isNullOrEmpty(staffRoom)){
			staffRoom=new StaffRoom();
		}
		getStaffRoom().add(staffRoom);
	}*/
	@Transient
	public long getStateId() {
		if(getAddress().getStateId() >0){
			return getAddress().getStateId();
		}			
		return 0;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}
	@Column(name="customerInActiveDescription",nullable=true ,length = 1024,columnDefinition="varchar(1024) default null")
	public String getCustomerInActiveDescription() {
		return customerInActiveDescription;
	}
	public void setCustomerInActiveDescription(String customerInActiveDescription) {
		this.customerInActiveDescription = customerInActiveDescription;
	}
	public String getMailChimpUserName() {
		return mailChimpUserName;
	}
	public void setMailChimpUserName(String mailChimpUserName) {
		this.mailChimpUserName = mailChimpUserName;
	}
	public String getMailChimpPassword() {
		return mailChimpPassword;
	}
	public void setMailChimpPassword(String mailChimpPassword) {
		this.mailChimpPassword = mailChimpPassword;
	}
	public String getMailChimpAPIKey() {
		return mailChimpAPIKey;
	}
	public void setMailChimpAPIKey(String mailChimpAPIKey) {
		this.mailChimpAPIKey = mailChimpAPIKey;
	}
	@Column(name = "standardType", nullable = true, length = 1,columnDefinition="char(1)")
	public String getStandardType() {
		return standardType;
	}
	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}
	@Column(name = "showAddreesInFeeReceipt", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getShowAddreesInFeeReceipt() {
		return showAddreesInFeeReceipt;
	}
	public void setShowAddreesInFeeReceipt(String showAddreesInFeeReceipt) {
		this.showAddreesInFeeReceipt = showAddreesInFeeReceipt;
	}
	@Column(name = "showBalanceAmountInFeeReceipt", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getShowBalanceAmountInFeeReceipt() {
		return showBalanceAmountInFeeReceipt;
	}
	public void setShowBalanceAmountInFeeReceipt(String showBalanceAmountInFeeReceipt) {
		this.showBalanceAmountInFeeReceipt = showBalanceAmountInFeeReceipt;
	}
	@Column(name = "accountModuleUsing", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getAccountModuleUsing() {
		return accountModuleUsing;
	}
	public void setAccountModuleUsing(String accountModuleUsing) {
		this.accountModuleUsing = accountModuleUsing;
	}
	@Column(name = "chalanaGenerationStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getChalanaGenerationStatus() {
		return chalanaGenerationStatus;
	}
	public void setChalanaGenerationStatus(String chalanaGenerationStatus) {
		this.chalanaGenerationStatus = chalanaGenerationStatus;
	}

	/**
	 * @return the preferences
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="preferenceId", insertable=true, updatable=true) 
	@javax.xml.bind.annotation.XmlTransient()
	public CustomerPreferences getPreferences() {
		return preferences;
	}

	/**
	 * @param preferences the preferences to set
	 */
	public void setPreferences(CustomerPreferences preferences) {
		this.preferences = preferences;
	}

	/**
	 * @return the checkAttendanceEmailService
	 */
	@Column(name = "checkAttendanceEmailService", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
   	@javax.xml.bind.annotation.XmlTransient()
	public boolean isCheckAttendanceEmailService() {
		return checkAttendanceEmailService;
	}

	/**
	 * @param checkAttendanceEmailService the checkAttendanceEmailService to set
	 */
	public void setCheckAttendanceEmailService(boolean checkAttendanceEmailService) {
		this.checkAttendanceEmailService = checkAttendanceEmailService;
	}
	
	/**
	 * @return the checkAssignmentSmsService
	 */
	@Column(name = "checkAssignmentSmsService", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	@Type(type="yes_no")
	@javax.xml.bind.annotation.XmlTransient()
  
	public boolean isCheckAssignmentSmsService() {
		return checkAssignmentSmsService;
	}

	/**
	 * @param checkAssignmentSmsService the checkAssignmentSmsService to set
	 */
	public void setCheckAssignmentSmsService(boolean checkAssignmentSmsService) {
		this.checkAssignmentSmsService = checkAssignmentSmsService;
	}
	
	@Column(name = "alphaNumericRollNumber", nullable=true ,length = 1024,columnDefinition="varchar(1024) default null")
	public String getAlphaNumericRollNumber() {
		return alphaNumericRollNumber;
	}
	
	public void setAlphaNumericRollNumber(String alphaNumericRollNumber) {
		this.alphaNumericRollNumber = alphaNumericRollNumber;
	}

	/**
	 * @return the allowPastDatesForPaymnets
	 */
	@Column(name = "allowPastDatesForPayments", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getAllowPastDatesForPayments() {
		return allowPastDatesForPayments;
	}

	/**
	 * @param allowPastDatesForPaymnets the allowPastDatesForPaymnets to set
	 */
	public void setAllowPastDatesForPayments(String allowPastDatesForPayments) {
		this.allowPastDatesForPayments = allowPastDatesForPayments;
	}

	/**
	 * @return the allowedPastDatesForPaymnets
	 */
	@Column(name = "allowedPastDatesForPayments", nullable = false ,columnDefinition="bigint(20) default 0")
	public Long getAllowedPastDatesForPayments() {
		return allowedPastDatesForPayments;
	}

	/**
	 * @param allowedPastDatesForPaymnets the allowedPastDatesForPaymnets to set
	 */
	public void setAllowedPastDatesForPayments(Long allowedPastDatesForPayments) {
		this.allowedPastDatesForPayments = allowedPastDatesForPayments;
	}

	/**
	 * @return the paymentPastDates
	 */
	@Transient
	public String getPaymentPastDates() {
		return "-"+this.allowedPastDatesForPayments+"d";
	}

	

	/**
	 * @param paymentPastDates the paymentPastDates to set
	 */
	public void setPaymentPastDates(String paymentPastDates) {
		this.paymentPastDates = paymentPastDates;
	}

	/**
	 * @return the allowDiscountOptOnOtherRoles
	 */
	@Column(name = "allowDiscountOptOnOtherRoles", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getAllowDiscountOptOnOtherRoles() {
		return allowDiscountOptOnOtherRoles;
	}

	/**
	 * @param allowDiscountOptOnOtherRoles the allowDiscountOptOnOtherRoles to set
	 */
	public void setAllowDiscountOptOnOtherRoles(String allowDiscountOptOnOtherRoles) {
		this.allowDiscountOptOnOtherRoles = allowDiscountOptOnOtherRoles;
	}
	
}

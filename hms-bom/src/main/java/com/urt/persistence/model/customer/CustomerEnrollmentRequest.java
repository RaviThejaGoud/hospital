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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.study.SyllabusType;
import com.urt.persistence.model.subscription.PackageDetails;
import com.urt.persistence.model.user.User;
/*
 * @create new table customer.
 */
@Entity
@Table(name = "customerEnrollmentRequest")
public class CustomerEnrollmentRequest  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    
    private String firstName;
    private String lastName;
    private String customerShortName;
    private PackageDetails packageDetails;
    private Address address;
    private String customerVision;
    private String contactNumber;
    private String mobileNumber;
    private String organizationLevel;
    private boolean hostelModuleStatus;
    private boolean transportModuleStatus;
    private String feeReceiptModel;
    private String custEmail;
    private Set<SyllabusType> syllabusType;
    private String customerMission;
    private String status;
    private Address organizationAddress;
    private long orgnizationTypeId;
    private InviteCustomerEnrollment inviteCustomerEnrollment;
    private String organization;
    //private long organizationSubTypesId;
    private String password;
    private String termsConditions;
    
    
    
    
    @Column(name = "termsConditions", nullable = true, length = 1,columnDefinition="char(10) default 'A'")
    public String getTermsConditions() {
		return termsConditions;
	}
	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="inviteCustomerEnrollmentId" ,insertable=true, updatable=true) 
    @javax.xml.bind.annotation.XmlTransient()
    public InviteCustomerEnrollment getInviteCustomerEnrollment() {
		return inviteCustomerEnrollment;
	}
	public void setInviteCustomerEnrollment(
			InviteCustomerEnrollment inviteCustomerEnrollment) {
		this.inviteCustomerEnrollment = inviteCustomerEnrollment;
	}
	public long getOrgnizationTypeId() {
		return orgnizationTypeId;
	}
	public void setOrgnizationTypeId(long orgnizationTypeId) {
		this.orgnizationTypeId = orgnizationTypeId;
	}
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="organizationAddressId" ,insertable=true, updatable=true) 
    @javax.xml.bind.annotation.XmlTransient()
    public Address getOrganizationAddress() {
		return organizationAddress;
	}
	public void setOrganizationAddress(Address organizationAddress) {
		this.organizationAddress = organizationAddress;
	}
	
	@Column(name = "feeReceiptModel", nullable = true, length = 1,columnDefinition="char(10) default 'General'")
    public String getFeeReceiptModel() {
		return feeReceiptModel;
	}
	public void setFeeReceiptModel(String feeReceiptModel) {
		this.feeReceiptModel = feeReceiptModel;
	}

	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Column(name = "status", nullable = false, length = 1,columnDefinition="char(1) default 'P'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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


	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "syllabusCustomerEnrollmentInfo", joinColumns = { @JoinColumn(name = "customerEnrollmentRequestId") },
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

	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="packageDetailsId", insertable=true, updatable=true)
	@javax.xml.bind.annotation.XmlTransient()
	public PackageDetails getPackageDetails() {
		return packageDetails;
	}


	public void setPackageDetails(PackageDetails packageDetails) {
		this.packageDetails = packageDetails;
	}

	public CustomerEnrollmentRequest() {
        
    }

    public CustomerEnrollmentRequest(long id) {
        setId(id);
    }
    
    @Column(name = "mobileNumber", nullable = true, length = 128)
    public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
        .append("status", this.status)
        .append("hostelModuleStatus", this.hostelModuleStatus)
        .append("transportModuleStatus", this.transportModuleStatus)
        .append("custEmail",this.custEmail)
        .append("firstName",this.firstName)
        .append("lastName",this.lastName)
        .toString();
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
	 
	@Transient
	public long getStateId() {
		if(getAddress().getStateId() >0){
			return getAddress().getStateId();
		}			
		return 0;
	}
	@Transient
	public String getStatusStr() {
		if(Constants.PENDING_STATUS.equalsIgnoreCase(getStatus()))
			return "Pending";
		else if(Constants.REJECTED_STATUS.equalsIgnoreCase(getStatus()))
			return "Reject";
		else
			return "Active";
	}
}

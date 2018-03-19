package com.urt.persistence.model.customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.user.User;
/*
 * @create new table customer.
 */
@Entity
@Table(name = "hostel")
public class Hostel  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String hostelName;
    protected String custEmail;
    protected String contactNumber;
    //protected String customerAddress;
    protected String mobileNumber;
    protected UserImage customerOrgImage;
    protected long custId;
    /*private AcademicYear academicYear;*/
    protected long addressId;
    private String status;
    
    
    
    @Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    public long getAddressId() {
        return addressId;
    }
    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
    
    /*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId") 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}*/
    
   // private List<Building> buildingList;
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
    public Hostel(long id) {
        setId(id);
    }
    public Hostel() {        
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
	@Column(name = "custEmail", nullable = true, unique=false, length = 128)
	public String getCustEmail() {
		return custEmail;
	}
	/**
	* @param custEmail the custEmail to set
	*/
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	 
	@Column(name = "contactNumber", nullable = true, length = 128)
		public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/*@Column(name = "custAddress", nullable = true, length = 128)
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}*/
	@Override
	public int compareTo(Object object) {
		Hostel target = (Hostel) object;
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
        final Hostel hostel = (Hostel) o;
        if (custEmail != null ? !custEmail.equals(hostel.getCustEmail()) : hostel.getCustEmail() != null) return false;
        return true;
    }
    public int hashCode() {
        return (custEmail != null ? custEmail.hashCode() : 0);
    }
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("hostelName", this.hostelName)
        .append("custEmail",this.custEmail)
        .toString();
	}
	/**
	 * @return the hostelName
	 */
	@Column(name = "hostelName", nullable = true, length = 128)
	public String getHostelName() {
	    return hostelName;
	}
	/**
	 * @param hostelName the hostelName to set
	 */
	public void setHostelName(String hostelName) {
	    this.hostelName = hostelName;
	}
	/**
	 * @return the customerOrgImage
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
	 */
     @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="custImageId", insertable=true, updatable=true) 
	public UserImage getCustomerOrgImage() {
	return customerOrgImage;
	}
	public void setCustomerOrgImage(UserImage customerOrgImage) {
		this.customerOrgImage = customerOrgImage;
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
}

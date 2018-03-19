package com.urt.persistence.model.hostel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.UserImage;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "building")
public class Building  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;
	 
	public Building() {
    }
    public Building(long id) {
        setId(id);
    }

    private String buildingName;
    private String buildingShortName;
    //private String buildingAddress;
    protected String contactNumber;
    protected Set<Floor> floorList;
    protected UserImage customerOrgImage;
    private Long custId;
    private AcademicYear academicYear;
    private List<MessMenuTime> messMenuTimeingsList;
    private Long hostelId;
    protected long addressId;
    private String gender;
    private String allocatedFor;
    private String status;
    
    
    
    @Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the allocatedFor
	 */
	public String getAllocatedFor() {
		return allocatedFor;
	}
	/**
	 * @param allocatedFor the allocatedFor to set
	 */
	public void setAllocatedFor(String allocatedFor) {
		this.allocatedFor = allocatedFor;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
     * @return the addressId
     */
    public long getAddressId() {
        return addressId;
    }
    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }
    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    /**
     * @return the buildingShortName
     */
    public String getBuildingShortName() {
        return buildingShortName;
    }
    /**
     * @param buildingShortName the buildingShortName to set
     */
    public void setBuildingShortName(String buildingShortName) {
        this.buildingShortName = buildingShortName;
    }
    /**
     * @return the buildingAddress
     *//*
    @Column(name = "buildingAddress", nullable = true, length = 128)
    public String getBuildingAddress() {
        return buildingAddress;
    }
    *//**
     * @param buildingAddress the buildingAddress to set
     *//*
    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }*/
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
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true) 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   	@JoinColumn(name="buildingId") 
    public List<MessMenuTime> getMessMenuTimeingsList() {
		return messMenuTimeingsList;
	}
	public void setMessMenuTimeingsList(List<MessMenuTime> messMenuTimeingsList) {
		this.messMenuTimeingsList = messMenuTimeingsList;
	}
	/**
     * @return the custId
     */
    public Long getCustId() {
        return custId;
    }
    /**
     * @param custId the custId to set
     */
    public void setCustId(Long custId) {
        this.custId = custId;
    }
    /**
     * @return the floorList
     */
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="buildingId") 
    public Set<Floor> getFloorList() {
        return floorList;
    }
    /**
     * @param floorList the floorList to set
     */
    public void setFloorList(Set<Floor> floorList) {
        this.floorList = floorList;
    }
    public void addBuildingToFloor(Floor floor) {
	if(ObjectFunctions.isNullOrEmpty(this.getFloorList())){
		this.floorList=new HashSet<Floor>();
	}
	this.floorList.add(floor);
   }
    /**
     * @return the buildingName
     */
    public String getBuildingName() {
        return buildingName;
    }
    /**
     * @param buildingName the buildingName to set
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    /**
	 * @return the customer name.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
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
	/*public void addMessFoodTimeings(MessMenuTime messMenuTime){
		if(ObjectFunctions.isNullOrEmpty(this.getMessMenuTimeingsList())){
			this.messMenuTimeingsList=new ArrayList<MessMenuTime>();
		}
		this.messMenuTimeingsList.add(messMenuTime);
	}*/
	/**
	 * @return the hostelId
	 */
	public Long getHostelId() {
	    return hostelId;
	}
	/**
	 * @param hostelId the hostelId to set
	 */
	public void setHostelId(Long hostelId) {
	    this.hostelId = hostelId;
	}
	@Transient
	public int getNumberOfFloors() {
	    if(ObjectFunctions.isNullOrEmpty(getFloorList()))
		return 0;
	    else
		return getFloorList().size();
	}
}
    

  


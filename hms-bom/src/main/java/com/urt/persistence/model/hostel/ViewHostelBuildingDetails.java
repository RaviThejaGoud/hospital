package com.urt.persistence.model.hostel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "vw_hostelBuildingDetails")
public class ViewHostelBuildingDetails implements Serializable,Cloneable,Comparable   {

    private static final long serialVersionUID = 1L;

    private long  buildingId;
    private String buildingName;
    private long custId;
    private long academicYearId;
    private long hostelId;
    private String hostelName;
    //private String  buildingAddress;
    protected String contactNumber;
    protected String streetName;
    protected String city;
    protected String state;
    protected String postalCode;
    protected String buildingStatus;
    
    
    
	public String getBuildingStatus() {
		return buildingStatus;
	}
	public void setBuildingStatus(String buildingStatus) {
		this.buildingStatus = buildingStatus;
	}
	/**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }
    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
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
    public ViewHostelBuildingDetails() {

    }
    /**
     * @return the hostelId
     */
    
    public long getHostelId() {
        return hostelId;
    }

    /**
     * @param hostelId the hostelId to set
     */
    public void setHostelId(long hostelId) {
        this.hostelId = hostelId;
    }

    /**
     * @return the hostelName
     */
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
     * @return the buildingId
     */
    @Id
    @Column( name="buildingId", unique=false, nullable=false, updatable=false )
    public long getBuildingId() {
        return buildingId;
    }

    /**
     * @param buildingId the buildingId to set
     */
    
    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
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

    /**
     * @return the academicYearId
     */
    public long getAcademicYearId() {
        return academicYearId;
    }

    /**
     * @param academicYearId the academicYearId to set
     */
    public void setAcademicYearId(long academicYearId) {
        this.academicYearId = academicYearId;
    }
    /**
     * @return the customer name.
     */

    @Override
    public String toString() {
	return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
		.append("BuildingId: ", this.getBuildingId()).toString();
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
    @Transient
    public String getHostelNameAndBuildingName() {
	return getHostelName()+" - "+getBuildingName();
    }
    @Transient
    public String getHostelIdAndBuildingId() {
	return getHostelId()+"_"+getBuildingId();
    }
    /*@Transient
    public String getBuildingNameAndFloorName() {
	return getBuildingName()+" : "+getFloorName();
    }*/
}

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
@Table(name = "vw_hostelAllDetails")
public class ViewHostelAllDetails implements Serializable,Cloneable,Comparable  {

    private static final long serialVersionUID = 1L;

    private long buildingId;
    private String floorName;
    private String buildingName;
    private long custId;
    private long floorId;
    private long academicYearId;
    private long floorLevel;
    private String gender;
	private String fgender;
	protected String buildingStatus;
	protected String floorStatus;
	private long hostelId;
	private String hostelName;
	private String contactNumber;
	private String hostelWithBuildingName;
	private long bedId;
	private String hostelAllDetailsName;
	private String roomAndBedName;
	
    
	
	public String getRoomAndBedName() {
		return roomAndBedName;
	}
	public void setRoomAndBedName(String roomAndBedName) {
		this.roomAndBedName = roomAndBedName;
	}
	public long getHostelId() {
		return hostelId;
	}
	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getHostelWithBuildingName() {
		return hostelWithBuildingName;
	}
	public void setHostelWithBuildingName(String hostelWithBuildingName) {
		this.hostelWithBuildingName = hostelWithBuildingName;
	}
	
	@Id
	public long getBedId() {
		return bedId;
	}
	public void setBedId(long bedId) {
		this.bedId = bedId;
	}
	public String getHostelAllDetailsName() {
		return hostelAllDetailsName;
	}
	public void setHostelAllDetailsName(String hostelAllDetailsName) {
		this.hostelAllDetailsName = hostelAllDetailsName;
	}
	public String getFloorStatus() {
		return floorStatus;
	}
	public void setFloorStatus(String floorStatus) {
		this.floorStatus = floorStatus;
	}
	public String getBuildingStatus() {
		return buildingStatus;
	}
	public void setBuildingStatus(String buildingStatus) {
		this.buildingStatus = buildingStatus;
	}
    
	public String getFgender() {
		return fgender;
	}

	public void setFgender(String fgender) {
		this.fgender = fgender;
	}
    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
     * @return the floorLevel
     */
    public long getFloorLevel() {
        return floorLevel;
    }

    /**
     * @param floorLevel the floorLevel to set
     */
    public void setFloorLevel(long floorLevel) {
        this.floorLevel = floorLevel;
    }

    /**
     * @return the buildingId
     */
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
     * @return the floorName
     */
    public String getFloorName() {
        return floorName;
    }

    /**
     * @param floorName the floorName to set
     */
    public void setFloorName(String floorName) {
        this.floorName = floorName;
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
     * @return the floorId
     */
    @Id
    @Column( name="floorId", unique=false, nullable=false, updatable=false )
    public long getFloorId() {
        return floorId;
    }

    /**
     * @param floorId the floorId to set
     */
    public void setFloorId(long floorId) {
        this.floorId = floorId;
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

    public ViewHostelAllDetails() {

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
	return 0;
    }

    @Override
    public boolean equals(Object o) {
	return false;
    }

    @Override
    public int hashCode() {
	return 0;
    }
   /* @Transient
    public String getBuildingNameAndFloorName() {
	return getBuildingName()+" - "+getFloorName()+getFloorLevel();
    }*/
  @Transient
    public String getBuildingIdAndFloorId() {
	return getBuildingId()+"_"+getFloorId();
    }
    @Transient
    public String getBuildingNameAndFloorName() {
	return getBuildingName()+" - "+getFloorName()+getFloorLevel();
    }
}

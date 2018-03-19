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
@Table(name = "vw_roomDetails")
public class ViewRoomDetails implements Serializable,Cloneable,Comparable  {

    private static final long serialVersionUID = 1L;

    private long roomId;
    private long roomNumber;
    private long custId;
    private long academicYearId;
    private long floorId;
    private long floorLevel;
    private long roomLevel;
    protected String roomName;
    protected String buildingFloorName;
    protected String allocatedFor;
    protected String buildingName;
    private long buildingId;
    protected String capacity;
    protected String remainingBeds;
    protected String roomWithFloorName;
    private String floorName;  
    private String roomAndBedName;
    private long bedId;
    
    
    
    
    public long getBedId() {
		return bedId;
	}
	public void setBedId(long bedId) {
		this.bedId = bedId;
	}
	public void setBuildingId(long buildingId) {
		this.buildingId = buildingId;
	}
	public String getRoomAndBedName() {
		return roomAndBedName;
	}
	public void setRoomAndBedName(String roomAndBedName) {
		this.roomAndBedName = roomAndBedName;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getRemainingBeds() {
		return remainingBeds;
	}
	public void setRemainingBeds(String remainingBeds) {
		this.remainingBeds = remainingBeds;
	}
	public String getRoomWithFloorName() {
		return roomWithFloorName;
	}
	public void setRoomWithFloorName(String roomWithFloorName) {
		this.roomWithFloorName = roomWithFloorName;
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
	/**
	 * @return the buildingFloorName
	 */
	public String getBuildingFloorName() {
		return buildingFloorName;
	}
	/**
	 * @param buildingFloorName the buildingFloorName to set
	 */
	public void setBuildingFloorName(String buildingFloorName) {
		this.buildingFloorName = buildingFloorName;
	}
	/**
     * @return the buildingId
     */
    public Long getBuildingId() {
        return buildingId;
    }
    /**
     * @param buildingId the buildingId to set
     */
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
    /**
     * @return the roomLevel
     */
    public long getRoomLevel() {
        return roomLevel;
    }

    /**
     * @param roomLevel the roomLevel to set
     */
    public void setRoomLevel(long roomLevel) {
        this.roomLevel = roomLevel;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    //protected Set<Bed> bedList;
    /**
     * @return the roomId
     */
    @Id
    @Column( name="roomId", unique=false, nullable=false, updatable=false )
    public long getRoomId() {
        return roomId;
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
     * @param roomId the roomId to set
     */
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    /**
     * @return the roomNumber
     */
    public long getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
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

    public ViewRoomDetails() {

    }

   /* *//**
	 * @return the bedList
	 *//*
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="roomId",referencedColumnName="roomId") 
	public Set<Bed> getBedList() {
	    return bedList;
	}

	*//**
	 * @param bedList the bedList to set
	 *//*
	public void setBedList(Set<Bed> bedList) {
	    this.bedList = bedList;
	}*/
    
    /**
     * @return the customer name.
     */

    @Override
    public String toString() {
	return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
		.append("RoomId: ", this.getRoomId()).toString();
    }

    @Override
	public int compareTo(Object object) {
	ViewRoomDetails target = (ViewRoomDetails) object;
    	
   	 if (target.getFloorName() != null && this.getFloorName()!= null)
        {
   		String srcName=this.getFloorName()+this.getFloorLevel()+this.getRoomNumber();
   		String tarname=target.getFloorName()+target.getFloorLevel()+target.getRoomNumber();
           return srcName.compareToIgnoreCase(tarname);
        }
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
    public String getFloorIdAndRoomId() {
	return getFloorId()+"_"+getRoomId();
    }
    @Transient
    public String getFloorNameAndRoomName() {
	return getFloorName()+getFloorLevel()+" - "+getRoomName() +getRoomLevel();
    }
    @Transient
    public String getRoomNameAndRoomLevel() {
	return  getRoomName()+" "+getRoomLevel();
    }
   /* @Transient
    public long getNoOfBeds() {
	if(ObjectFunctions.isNullOrEmpty(getBedList())) 
	    return 0;
	else
	    return getBedList().size();
    }*/
}

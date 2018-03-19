package com.urt.persistence.model.hostel;

import java.util.HashSet;
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

/*
 * @create new table customer.
 */
@Entity
@Table(name = "room")
public class Room  extends PersistentObject {
	
    private static final long serialVersionUID = 1L;
    
    public Room() {
    }
    public Room(long id) {
        setId(id);
    }
    protected String roomName;
    //private int noOfBeds;
    //private int noOfRooms;
    private long roomNumber;
    protected Set<Bed> bedList;
    private long custId;
    private Long floorId;
    /*private Long buildingId;
    private Long hostelId;*/
    private AcademicYear academicYear;
    protected long roomLevel;
    protected String roomType;
    protected String capacity;
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
	 
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	/**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }
    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    /**
     * @return the roomLevel
     */
    @Column(name = "roomLevel", nullable = false, columnDefinition="int default 0")
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
    /**
     * @return the noOfRooms
     *//*
    public int getNoOfRooms() {
        return noOfRooms;
    }
    *//**
     * @param noOfRooms the noOfRooms to set
     *//*
    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }*/
    public long getRoomNumber() {
        return roomNumber;
    }
    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }
  /*  *//**
     * @return the buildingId
     *//*
    public Long getBuildingId() {
        return buildingId;
    }
    *//**
     * @param buildingId the buildingId to set
     *//*
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }*/
   /* *//**
     * @return the hostelId
     *//*
    public Long getHostelId() {
        return hostelId;
    }
    *//**
     * @param hostelId the hostelId to set
     *//*
    public void setHostelId(Long hostelId) {
        this.hostelId = hostelId;
    }*/
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId") 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    /**
     * @return the floorId
     */
    public Long getFloorId() {
        return floorId;
    }

    /**
     * @param floorId the floorId to set
     */
    public void setFloorId(Long floorId) {
        this.floorId = floorId;
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
	    Room target = (Room) object;
	    	if (target != null && this != null)
	        {
	    		if(this.getRoomNumber() >= target.getRoomNumber())
	    			return 1;
	    		else
	    			return 0;
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
	/**
	 * @return the bedList
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="roomId") 
	public Set<Bed> getBedList() {
	    return bedList;
	}

	/**
	 * @param bedList the bedList to set
	 */
	public void setBedList(Set<Bed> bedList) {
	    this.bedList = bedList;
	}
	 public void addRoomToBed(Bed bed) {
		if(ObjectFunctions.isNullOrEmpty(this.getBedList())){
			this.bedList=new HashSet<Bed>();
		}
		this.bedList.add(bed);
	   }
	 @Transient
	 public long getNoOfBeds() {
	    if(ObjectFunctions.isNullOrEmpty(getBedList()))
		return 0;
	    else
		return getBedList().size();
	 }
}
    

  


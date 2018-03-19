package com.urt.persistence.model.hostel;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "floor")
public class Floor  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;

	public Floor() {
	}
    public Floor(long id) {
        setId(id);
    }
    protected String floorName;
   // protected String roomName;
    protected long roomLevel;
    //private List<Building> buildingList;
    //private List<Building> hostelList;
    protected List<Room> roomList;
    protected long floorLevel;
    private Long custId;
    private Long buildingId;
    private Long hostelId;
    private AcademicYear academicYear;
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
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true) 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
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
	@Transient
	    public String getHostelIdAndBuildingId() {
		return getHostelId()+"_"+getBuildingId();
	    }
	/**
	 * @return the roomList
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="floorId") 
	public List<Room> getRoomList() {
	    return roomList;
	}
	public void setRoomList(List<Room> roomList) {
	    this.roomList = roomList;
	}
	 public void addFloorToRoom(Room room) {
		if(ObjectFunctions.isNullOrEmpty(this.getRoomList())){
			this.roomList= new ArrayList<Room>();
		}
		this.roomList.add(room);
	   }
	 @Transient
	 public int getRoomsCount() {
	     if(ObjectFunctions.isNullOrEmpty(getRoomList()))
		 return 0;
	     else {
		 return getRoomList().size();
	     }
	 }
	 @Transient
	 public int getBedsCount() {
	     if(ObjectFunctions.isNullOrEmpty(getRoomList()))
		 return 0;
	     else {
		 int berthsCount=0;
		 for(Room room:getRoomList()) {
		     if(ObjectFunctions.isNotNullOrEmpty(room.getBedList()))
			 berthsCount+=room.getBedList().size();
		 }
		 return berthsCount;
	     }
	 }
}
    

  


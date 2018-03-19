package com.urt.persistence.model.hostel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "vw_buildingMenuItemsDetails")
public class ViewBuildingMenuItemsDetails  implements Serializable,Cloneable, Comparable  {
	
	 private static final long serialVersionUID = 1L;

	    /**
	 * Default size for StringBuffer initialization
	 */

    private long buildingId;
    private long foodMenuItemsId;
    private long dayId;
    private long custId;
    private String menuItemNames;
    private long academicYearId;
    private String startTime;
    private String endTime;
	private String messFoodTypeName;
    private String dayName;
    private String buildingName;
    private long messMenuTimeId;
    private long foodTypeId;
     
    
	 
    public long getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(long foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	public long getMessMenuTimeId() {
		return messMenuTimeId;
	}

	public void setMessMenuTimeId(long messMenuTimeId) {
		this.messMenuTimeId = messMenuTimeId;
	}

	@Id
    public long getFoodMenuItemsId() {
		return foodMenuItemsId;
	}

	public void setFoodMenuItemsId(long foodMenuItemsId) {
		this.foodMenuItemsId = foodMenuItemsId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getMenuItemNames() {
		return menuItemNames;
	}

	public void setMenuItemNames(String menuItemNames) {
		this.menuItemNames = menuItemNames;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMessFoodTypeName() {
		return messFoodTypeName;
	}

	public void setMessFoodTypeName(String messFoodTypeName) {
		this.messFoodTypeName = messFoodTypeName;
	}

	
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
    public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public long getDayId() {
		return dayId;
	}

	public void setDayId(long dayId) {
		this.dayId = dayId;
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
	 * @return the customer name.
	 */
    public int compareTo(Object o) {
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
}
    

  


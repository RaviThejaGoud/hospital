package com.urt.persistence.model.hostel;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "messMenuTime")
public class MessMenuTime  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;

	public MessMenuTime() {
        
    }

    public MessMenuTime(long id) {
        setId(id);
    }

    protected String startTime;
    protected String endTime;
    private String messFoodTypeName;
    private long academicYearId;
    private long custId;
    private long buildingId;
   // private List<FoodTypes> foodTypes;

    
    
   /* @OneToMany
   	@JoinColumn(name="messMenuTimeId")
	public List<FoodTypes> getFoodTypes() {
		return foodTypes;
	}

	public void setFoodTypes(List<FoodTypes> foodTypes) {
		this.foodTypes = foodTypes;
	}*/

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}   
    
	public String getMessFoodTypeName() {
		return messFoodTypeName;
	}
	public void setMessFoodTypeName(String messFoodTypeName) {
		this.messFoodTypeName = messFoodTypeName;
	}

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
	public long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(long buildingId) {
		this.buildingId = buildingId;
	}
	
}
    

  


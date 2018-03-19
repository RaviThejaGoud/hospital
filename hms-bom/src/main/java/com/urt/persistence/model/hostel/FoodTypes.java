package com.urt.persistence.model.hostel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "foodTypes")
public class FoodTypes  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;
	 

	public FoodTypes() {
        
    }

    public FoodTypes(long id) {
        setId(id);
    }

    private long buildingId;
    private String foodTypeName;
    private long custId;
    private long academicYearId;
    private List<FoodMenuItems> foodMenuItems;
    
    
    
    
    @OneToMany(cascade = CascadeType.MERGE)
   	@JoinColumn(name="foodTypeId") 
   	public List<FoodMenuItems> getFoodMenuItems() {
		return foodMenuItems;
	}

	public void setFoodMenuItems(List<FoodMenuItems> foodMenuItems) {
		this.foodMenuItems = foodMenuItems;
	}
   	
	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
    public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getFoodTypeName() {
		return foodTypeName;
	}

	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
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
	public void addFoodMenuItems(FoodMenuItems foodMenuItems){
		if(ObjectFunctions.isNullOrEmpty(this.getFoodMenuItems())){
			this.foodMenuItems=new ArrayList<FoodMenuItems>();
		}
		this.foodMenuItems.add(foodMenuItems);
	}
	/*public void removeFoodMenuItems(FoodMenuItems foodMenuItems){
		if(ObjectFunctions.isNullOrEmpty(this.getFoodMenuItems())){
			this.foodMenuItems=new ArrayList<FoodMenuItems>();
		}
		this.foodMenuItems.remove(foodMenuItems);
	}*/
}

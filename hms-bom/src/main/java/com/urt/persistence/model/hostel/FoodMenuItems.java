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
@Table(name = "foodMenuItems")
public class FoodMenuItems  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;


	public FoodMenuItems() {
        
    }

    public FoodMenuItems(long id) {
        setId(id);
    }
    
    private long custId;
   // private FoodTypes foodTypesId;
    private long dayId;
    private String menuItemNames;
   // private Building building;
    private long messMenuTimeId;
    
    
    
   /* @OneToOne
	@JoinColumn(name="foodTypesId")
    public FoodTypes getFoodTypesId() {
		return foodTypesId;
	}

	public void setFoodTypesId(FoodTypes foodTypesId) {
		this.foodTypesId = foodTypesId;
	}
*/
	
	
	/*@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="buildingId")
	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}*/
    
    public long getMessMenuTimeId() {
		return messMenuTimeId;
	}

	public void setMessMenuTimeId(long messMenuTimeId) {
		this.messMenuTimeId = messMenuTimeId;
	}
	public long getDayId() {
		return dayId;
	}

	public String getMenuItemNames() {
		return menuItemNames;
	}

	public void setMenuItemNames(String menuItemNames) {
		this.menuItemNames = menuItemNames;
	}

	public void setDayId(long dayId) {
		this.dayId = dayId;
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

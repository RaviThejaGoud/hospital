package com.urt.persistence.model.hostel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table messFoodType.
 */
@Entity
@Table(name = "messFoodType")
public class MessFoodType  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;
	 

	public MessFoodType() {
        
    }

    public MessFoodType(long id) {
        setId(id);
    }

    private long messId;
    private String foodTypeName;
    private long custId;
    private String status;
    
    

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getMessId() {
		return messId;
	}

	public void setMessId(long messId) {
		this.messId = messId;
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

     
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
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
}

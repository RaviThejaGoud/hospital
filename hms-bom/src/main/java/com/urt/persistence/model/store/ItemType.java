package com.urt.persistence.model.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name="itemType")
public class ItemType extends PersistentObject{
	
	private String typeName;
	private String measurementType;
	private long custId;
		
	@Column(nullable = false, length = 40)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(nullable = false, length = 10)
	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {		
		return null;
	}

	@Override
	public boolean equals(Object o) {		
		return false;
	}

	@Override
	public int hashCode() {		
		return 0;
	}

	@Override
	public int compareTo(Object object) {		
		return 0;
	}

}

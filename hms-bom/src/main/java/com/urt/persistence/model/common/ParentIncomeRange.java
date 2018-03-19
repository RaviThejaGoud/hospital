package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name="parentIncomeRange")
public class ParentIncomeRange extends PersistentObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8441619465240769265L;

	public String maxRange;
	public String minRange;
	private String rangeValues;
	
	public String getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(String maxRange) {
		this.maxRange = maxRange;
	}
	public String getMinRange() {
		return minRange;
	}

	public void setMinRange(String minRange) {
		this.minRange = minRange;
	}
	@Transient
	public String getRangeValues() {
		return rangeValues;
	}

	public void setRangeValues(String rangeValues) {
		this.rangeValues = rangeValues;
	}

	public ParentIncomeRange() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

}

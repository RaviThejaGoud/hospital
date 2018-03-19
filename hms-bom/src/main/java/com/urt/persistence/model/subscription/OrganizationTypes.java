package com.urt.persistence.model.subscription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table productPrices.
 */
@Entity
@Table(name = "organizationTypes")
public class OrganizationTypes extends PersistentObject {

	private static final long serialVersionUID = 8136617732257667570L;
	
	protected String organizationType;	
    
	/**
	 * @return the organizationType
	 */
	@Column(name = "organizationType", nullable = true, length = 15)
	public String getOrganizationType() {
		return organizationType;
	}

	/**
	 * @param organizationType the organizationType to set
	 */
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
    
	/**
	 * @return the nextPaymentDate
	 */


	/**
	 * @return the startDate
	 */

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else {
			return false;
		}			
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


}

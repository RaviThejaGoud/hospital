package com.urt.persistence.model.mobile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "schoolArea")
public class SchoolArea extends PersistentObject  {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	protected Long custId;
	private double latitude; 
	private double longitude;

	
	
	

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}
	@Column(name = "latitude", nullable = false)
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	@Column(name = "longitude", nullable = false)
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "";
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

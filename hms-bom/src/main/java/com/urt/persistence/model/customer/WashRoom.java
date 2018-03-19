package com.urt.persistence.model.customer;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "washRoom")
public class WashRoom extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;
	
	protected String noofWashRoomsForGirls;
	protected String noofWashRoomsForBoys;
	protected String noofWashRoomsForHeadMasters;
	protected String noofWashRoomsForTeachers;
	
	
	public String getNoofWashRoomsForGirls() {
		return noofWashRoomsForGirls;
	}
	public void setNoofWashRoomsForGirls(String noofWashRoomsForGirls) {
		this.noofWashRoomsForGirls = noofWashRoomsForGirls;
	}
	public String getNoofWashRoomsForBoys() {
		return noofWashRoomsForBoys;
	}
	public void setNoofWashRoomsForBoys(String noofWashRoomsForBoys) {
		this.noofWashRoomsForBoys = noofWashRoomsForBoys;
	}
	public String getNoofWashRoomsForHeadMasters() {
		return noofWashRoomsForHeadMasters;
	}
	public void setNoofWashRoomsForHeadMasters(String noofWashRoomsForHeadMasters) {
		this.noofWashRoomsForHeadMasters = noofWashRoomsForHeadMasters;
	}
	public String getNoofWashRoomsForTeachers() {
		return noofWashRoomsForTeachers;
	}
	public void setNoofWashRoomsForTeachers(String noofWashRoomsForTeachers) {
		this.noofWashRoomsForTeachers = noofWashRoomsForTeachers;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	
	
}

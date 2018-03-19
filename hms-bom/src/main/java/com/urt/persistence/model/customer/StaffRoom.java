package com.urt.persistence.model.customer;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "staffRoom")
public class StaffRoom extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;
	
	protected String noofRoomsForNonTeachers;
	protected String noofRoomsForHeadMasters;
	protected String noofRoomsForTeachers;
	protected long academicYearId;
	protected long custId;
	
	
	
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getNoofRoomsForNonTeachers() {
		return noofRoomsForNonTeachers;
	}
	public void setNoofRoomsForNonTeachers(String noofRoomsForNonTeachers) {
		this.noofRoomsForNonTeachers = noofRoomsForNonTeachers;
	}
	public String getNoofRoomsForHeadMasters() {
		return noofRoomsForHeadMasters;
	}
	public void setNoofRoomsForHeadMasters(String noofRoomsForHeadMasters) {
		this.noofRoomsForHeadMasters = noofRoomsForHeadMasters;
	}
	public String getNoofRoomsForTeachers() {
		return noofRoomsForTeachers;
	}
	public void setNoofRoomsForTeachers(String noofRoomsForTeachers) {
		this.noofRoomsForTeachers = noofRoomsForTeachers;
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
	@Override
	public String toString() {
		return "";
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	
}

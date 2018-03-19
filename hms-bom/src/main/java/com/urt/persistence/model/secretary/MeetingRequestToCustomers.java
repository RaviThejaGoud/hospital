package com.urt.persistence.model.secretary;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "meetingRequestToCustomers")
public class MeetingRequestToCustomers  extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	protected long custId;

	

	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
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
	
}

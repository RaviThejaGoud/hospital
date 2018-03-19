package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "userAutoReportsConfiguration")
public class UserAutoReportsConfiguration extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public long custId;
	public AutoReportsTypes autoReportsTypes;
	public String status;
	
	
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	@OneToOne()
    @JoinColumn(name="reportId", insertable=true, updatable=true)
	public AutoReportsTypes getAutoReportsTypes() {
		return autoReportsTypes;
	}

	public void setAutoReportsTypes(AutoReportsTypes autoReportsTypes) {
		this.autoReportsTypes = autoReportsTypes;
	}
	@Column(name = "status",nullable=true, length = 1,columnDefinition="char(1) default 'N'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

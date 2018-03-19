package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "serviceRequestHistory")
public class ServiceRequestHistory  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	
    

    private String channel;
    private long custId;
    private String serviceName;
    private long academicYearId;
    

	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
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
	@Override
	public int compareTo(Object object) {
		ServiceRequestHistory days = (ServiceRequestHistory) object;
        return new CompareToBuilder().append(this.getId(), days.getId()).toComparison();
	}

}


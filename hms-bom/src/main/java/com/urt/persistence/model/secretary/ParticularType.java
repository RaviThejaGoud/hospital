package com.urt.persistence.model.secretary;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "particularType")
public class ParticularType  extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	protected String particularTypeName;
	protected String status;
	protected long orgId;
	protected Set<Particular> particulars;
	
	public String getParticularTypeName() {
		return particularTypeName;
	}

	public void setParticularTypeName(String particularTypeName) {
		this.particularTypeName = particularTypeName;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="particularTypeId")
	public Set<Particular> getParticulars() {
		if(ObjectFunctions.isNullOrEmpty(this.particulars))
		{
	    	 this.particulars=new HashSet<Particular>();
	    }
		return particulars;
	}

	public void setParticulars(Set<Particular> particulars) {
		this.particulars = particulars;
	}
	
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
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
	@Transient
	public void addParticulars(Particular particulars) {
		if(ObjectFunctions.isNullOrEmpty(particulars)){
			particulars=new Particular();
		}
		getParticulars().add(particulars);
	}
	
}

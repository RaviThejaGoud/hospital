package com.urt.persistence.model.hostel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.Hostel;

/*
 * @create new table mess.
 */
@Entity
@Table(name = "mess")
public class Mess  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;

	private String messName;
	private String messDescription;
	private Set<Hostel> hostels;
	private long custId;
	private String status;
    
    

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessName() {
		return messName;
	}

	public void setMessName(String messName) {
		this.messName = messName;
	}

	public String getMessDescription() {
		return messDescription;
	}

	public void setMessDescription(String messDescription) {
		this.messDescription = messDescription;
	}

	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name = "messHostel",
       joinColumns = { @JoinColumn(name = "messId") },
       inverseJoinColumns = { @JoinColumn(name = "hostelId") })
	public Set<Hostel> getHostels() {
		if(hostels == null)
        {
			hostels = new HashSet<Hostel>();
        }
		return hostels;
	}

	public void setHostels(Set<Hostel> hostels) {
		this.hostels = hostels;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
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
	
	public void addHostel(Hostel hostel) {
		getHostels().add(hostel);
        
    }
    
    public void removeHostel(Hostel hostel) {
        getHostels().remove(hostel);
    }
    
}

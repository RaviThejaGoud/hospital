package com.urt.persistence.model.study;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.Customer;

@Entity
@Table(name = "syllabusType")
public class SyllabusType  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String syllabusTypeDescription;    
    protected String syllabusTypeName;
    protected String status;
    
    protected Set<Customer> customer;
  

    public SyllabusType() {
		super();
	}
    
	public SyllabusType(String syllabusTypeDescription,
			String syllabusTypeName, String status, Set<Customer> customer) {
		super();
		this.syllabusTypeDescription = syllabusTypeDescription;
		this.syllabusTypeName = syllabusTypeName;
		this.status = status;
		this.customer = customer;
	}

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "syllabusTypeInfo", joinColumns = { @JoinColumn(name = "syllabusTypeId") },
       inverseJoinColumns = { @JoinColumn(name = "customerId") })
    public Set<Customer> getCustomer() 
    {
    	if(customer == null)
        {
    		customer = new HashSet<Customer>();
        }
		return customer;
	}
	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	public String getSyllabusTypeDescription() {
		return syllabusTypeDescription;
	}
	public void setSyllabusTypeDescription(String syllabusTypeDescription) {
		this.syllabusTypeDescription = syllabusTypeDescription;
	}
	public String getSyllabusTypeName() {
		return syllabusTypeName;
	}
	public void setSyllabusTypeName(String syllabusTypeName) {
		this.syllabusTypeName = syllabusTypeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public SyllabusType(long id) {
        setId(id);
    }

    @Override
	public int compareTo(Object object) {
    	SyllabusType target = (SyllabusType) object;
    	if (target != null && this != null)
        {
    		if(this.getId() >= target.getId())
    			return 1;
    		else
    			return 0;
        }
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
  
	@Transient
	public void addCustomer(Customer customer) {
    	getCustomer().add(customer);
    }
	

}
    
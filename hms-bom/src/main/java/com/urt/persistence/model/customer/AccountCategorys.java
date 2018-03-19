package com.urt.persistence.model.customer;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "accountCategorys")
public class AccountCategorys extends PersistentObject {
	
	private static final long serialVersionUID = -1297578229281370962L;
	
	private String name;
    protected Set<AccountGroup> accountGroup;
  
    
	public void addAccountGroupDetails(AccountGroup accountGroup) {
		if (ObjectFunctions.isNullOrEmpty(getAccountGroup())) {
			this.accountGroup = new HashSet<AccountGroup>();
		}
		getAccountGroup().add(accountGroup);
	}
	 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="accountCategorysId")
	public Set<AccountGroup> getAccountGroup() {
		return accountGroup;
	}

	public void setAccountGroup(Set<AccountGroup> accountGroup) {
		this.accountGroup = accountGroup;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

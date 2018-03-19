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
@Table(name = "accountGroup")
public class AccountGroup extends PersistentObject {
	
	private static final long serialVersionUID = 945591681360902928L;
	
	private long custId;
	private String groupName;
	 
	private String status="A"; //status=I(inactive) status=A(Active)  
	protected Set<LedgerDetails> ledgerDetails;
	protected Long accountCategorysId;
	
     
	public void addLedgerDetails(LedgerDetails ledgerDetails) {
		if (ObjectFunctions.isNullOrEmpty(getLedgerDetails())) {
			this.ledgerDetails = new HashSet<LedgerDetails>();
		}
		getLedgerDetails().add(ledgerDetails);
	}
	 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="accountGroupId")
	public Set<LedgerDetails> getLedgerDetails() {
		return ledgerDetails;
	}

	public void setLedgerDetails(Set<LedgerDetails> ledgerDetails) {
		this.ledgerDetails = ledgerDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	} 
	
	
	
	public Long getAccountCategorysId() {
		return accountCategorysId;
	}

	public void setAccountCategorysId(Long accountCategorysId) {
		this.accountCategorysId = accountCategorysId;
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

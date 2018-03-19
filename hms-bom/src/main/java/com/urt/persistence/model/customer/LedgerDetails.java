package com.urt.persistence.model.customer;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "ledgerDetails")
public class LedgerDetails extends PersistentObject {
 
	private static final long serialVersionUID = 8355621570324746599L;
	
	private long custId;
	private String ledgerName;
    private String status="A"; //status=I(inactive) status=A(Active)  
    protected Long accountGroupId;
    
    
    
	public Long getAccountGroupId() {
		return accountGroupId;
	}

	public void setAccountGroupId(Long accountGroupId) {
		this.accountGroupId = accountGroupId;
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

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
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

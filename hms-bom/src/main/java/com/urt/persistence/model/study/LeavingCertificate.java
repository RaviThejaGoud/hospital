/**
 * 
 */
package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.TcBookSettings;

/**
 * @author sunanda
 *
 */
@Entity
@Table(name = "leavingCertificate")
public class LeavingCertificate  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected int serialNumber; 
    protected long custId; 
    protected long accountId;
    private TcBookSettings bookSetting;
    
    
    
    /**
	 * @return the serialNumber
	 */
	public int getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@OneToOne
	@JoinColumn(name="bookSettingId")
	public TcBookSettings getBookSetting() {
		return bookSetting;
	}

	public void setBookSetting(TcBookSettings bookSetting) {
		this.bookSetting = bookSetting;
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
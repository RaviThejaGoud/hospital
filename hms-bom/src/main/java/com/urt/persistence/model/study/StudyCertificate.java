package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.StudyCertificateBookSettings;

@Entity
@Table(name = "studyCertificate")
public class StudyCertificate  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected int serialNumber; 
    protected long custId; 
    protected long accountId;
    private StudyCertificateBookSettings bookSetting; 
    
    @OneToOne
	@JoinColumn(name="bookSettingId")
   public StudyCertificateBookSettings getBookSetting() {
		return bookSetting;
	}

	public void setBookSetting(StudyCertificateBookSettings bookSetting) {
		this.bookSetting = bookSetting;
	}

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

	public StudyCertificate() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
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
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("accountId", this.getAccountId())
        .append("serialNumber", this.getSerialNumber()).toString();
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
		
}
    

  


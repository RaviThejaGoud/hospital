package com.urt.persistence.model.fee;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.FeeType;

/*
 * @create new table customer.
 */

@Entity
@Table(name = "schoolFeeSetting")
public class SchoolFeeSetting  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String status;
    protected String settingName;
    protected boolean settingType;
    protected Set<FeeType> feeTypeDetails;
    
    
	public void addFeeTypeDetails(FeeType feeTypeDetails) {
		if (ObjectFunctions.isNullOrEmpty(getFeeTypeDetails())) {
			this.feeTypeDetails = new HashSet<FeeType>();
		}
		getFeeTypeDetails().add(feeTypeDetails);
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "feeSettingId")
	public Set<FeeType> getFeeTypeDetails() {
		return feeTypeDetails;
	}

	/**
	 * @param feeTypeDetails
	 *            the feeTypeDetails to set
	 */
	public void setFeeTypeDetails(Set<FeeType> feeTypeDetails) {
		this.feeTypeDetails = feeTypeDetails;
	}
    @Column(name = "settingName", nullable = true, length = 40)
	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	
	@Column(name = "settingType", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	@Type(type="yes_no")
	public boolean isSettingType() {
		return settingType;
	}

	public void setSettingType(boolean settingType) {
		this.settingType = settingType;
	}

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'S'")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public SchoolFeeSetting() {
        
    }
	public SchoolFeeSetting(long id) {
        setId(id);
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


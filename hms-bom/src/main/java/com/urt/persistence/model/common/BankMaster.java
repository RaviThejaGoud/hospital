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
@Table(name = "bankMaster")
public class BankMaster  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	
    
    private String bankName;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BankMaster() {
        
    }
    public BankMaster(long id) {
        setId(id);
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
		BankMaster days = (BankMaster) object;
        return new CompareToBuilder().append(this.getId(), days.getId()).toComparison();
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}


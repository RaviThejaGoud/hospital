package com.urt.persistence.model.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "paySlips")
public class PaySlip  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    private long custId;
    private int monthId;
    private int yearName;
    private String monthName;
    protected List<StaffMonthlyPaySlips> staffMonthlyPaySlipDetails;
    
    
    @Transient
	public String getAccountIdAndClassSectionId() {
		return getMonthId()+"_"+getYearName();
	}
    
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public int getMonthId() {
		return monthId;
	}
	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public int getYearName() {
		return yearName;
	}
	public void setYearName(int yearName) {
		this.yearName = yearName;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 @JoinColumn(name="paySlipId") 
	 public List<StaffMonthlyPaySlips> getStaffMonthlyPaySlips() {
		if(ObjectFunctions.isNullOrEmpty(this.staffMonthlyPaySlipDetails))
		{
	    	  this.staffMonthlyPaySlipDetails=new ArrayList<StaffMonthlyPaySlips>();
	    }
             return this.staffMonthlyPaySlipDetails;
	}

	public void setStaffMonthlyPaySlips(List<StaffMonthlyPaySlips> staffMonthlyPaySlipDetails) {
		this.staffMonthlyPaySlipDetails = staffMonthlyPaySlipDetails;
	}
	
    @Transient
    public void addStaffMonthlyPySlips(StaffMonthlyPaySlips staffMonthlyPaySlipDetails){
		if(ObjectFunctions.isNullOrEmpty(staffMonthlyPaySlipDetails)){
			staffMonthlyPaySlipDetails=new StaffMonthlyPaySlips();
		}
		getStaffMonthlyPaySlips().add(staffMonthlyPaySlipDetails);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
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
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
    

  


package com.urt.persistence.model.customer;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.common.constants.Constants;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.subscription.PackageDetails;
/*
 * @create new table customer.
 */
@Entity
@Table(name = "inviteCustomerEnrollment")
public class InviteCustomerEnrollment  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private String schoolName;                  
    //private String totalStudents;                  
    private String status;
    private String email;
    private String accountType;
    private Date trailStartDate;
    private Date trailEndDate;
    private PackageDetails packageDetails;
    
    
    
    
    
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="packageDetailsId", insertable=true, updatable=true)
	public PackageDetails getPackageDetails() {
		return packageDetails;
	}
	public void setPackageDetails(PackageDetails packageDetails) {
		this.packageDetails = packageDetails;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	/*public String getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(String totalStudents) {
		this.totalStudents = totalStudents;
	}*/
	
	 @Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'P'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getTrailStartDate() {
		return trailStartDate;
	}
	public void setTrailStartDate(Date trailStartDate) {
		this.trailStartDate = trailStartDate;
	}
	public Date getTrailEndDate() {
		return trailEndDate;
	}
	public void setTrailEndDate(Date trailEndDate) {
		this.trailEndDate = trailEndDate;
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
   
	@Transient
	public String getStatusStr() {
		if(Constants.PENDING_STATUS.equalsIgnoreCase(getStatus()))
			return "Pending";
		else
			return "Active";
	}
	
}

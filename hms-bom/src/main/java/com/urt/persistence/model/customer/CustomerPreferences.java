/********************************************************************
 * HYNIVA
 * All Rights Reserved 
 *
 * File: CustomerPreferences.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   June 20, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.urt.persistence.model.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.Role;


@Entity
@Table(name = "customerPreferences")
public class CustomerPreferences extends PersistentObject{
	
	private static final long serialVersionUID = 1L;
	private String parentMobileNoVisibleToTeacher ="Y";
	private String particularWisePartialAmount="Y";
	private String feeBalanceAmountInPaymnetSMS ="Y";
	private String feePaymentNotificationToManagement ="N";
	private String visibleFeeInfoToParent ="Y";
	private String visibleTermWiseBalanceAmount="Y";
	private String approvalRequiredForClassTeacherCreatedSMS="N";
	

	private Set<Role> roles;
	private boolean feeRefund;
	

	/*If user select Y we will enable partial payment particular wise. If user select N we will disable partial payment particular wise .*/
	@Column(name = "particularWisePartialAmount", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getParticularWisePartialAmount() {
		return particularWisePartialAmount;
	}

	public void setParticularWisePartialAmount(String particularWisePartialAmount) {
		this.particularWisePartialAmount = particularWisePartialAmount;
	}

	/**
	 * @return the parentMobileNoVisibleToTeacher
	 */
	@Column(name = "parentMobileNoVisibleToTeacher", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getParentMobileNoVisibleToTeacher() {
		return parentMobileNoVisibleToTeacher;
	}

	/**
	 * @param parentMobileNoVisibleToTeacher the parentMobileNoVisibleToTeacher to set
	 */
	public void setParentMobileNoVisibleToTeacher(
			String parentMobileNoVisibleToTeacher) {
		this.parentMobileNoVisibleToTeacher = parentMobileNoVisibleToTeacher;
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

	/**
	 * @return the feeBalanceAmountInPaymnetSMS
	 */
	@Column(name = "feeBalanceAmountInPaymnetSMS", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getFeeBalanceAmountInPaymnetSMS() {
		return feeBalanceAmountInPaymnetSMS;
	}

	/**
	 * @param feeBalanceAmountInPaymnetSMS the feeBalanceAmountInPaymnetSMS to set
	 */
	public void setFeeBalanceAmountInPaymnetSMS(String feeBalanceAmountInPaymnetSMS) {
		this.feeBalanceAmountInPaymnetSMS = feeBalanceAmountInPaymnetSMS;
	}

	/**
	 * @return the feePaymentNotificationToManagement
	 */
	@Column(name = "feePaymentNotificationToManagement", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getFeePaymentNotificationToManagement() {
		return feePaymentNotificationToManagement;
	}

	/**
	 * @param feePaymentNotificationToManagement the feePaymentNotificationToManagement to set
	 */
	public void setFeePaymentNotificationToManagement(
			String feePaymentNotificationToManagement) {
		this.feePaymentNotificationToManagement = feePaymentNotificationToManagement;
	}

	

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "PaymentNotificationRoles",
       joinColumns = { @JoinColumn(name = "preferenceId") },
       inverseJoinColumns = { @JoinColumn(name = "roleId") })

    public Set<Role> getRoles() {
        if(roles == null)
        {
            roles = new HashSet<Role>();
        }
        return roles;
    }
    /**
     * Adds a role for the user
     * @param role
     */
    public void addRole(Role role) {
      
            this.getRoles().add(role);
        
    }
  /*  public void addNewRole(Role role) {
        getRoles().add(role);
    }*/
    
    /**
     * Adds a role for the user
     * @param role
     */
    public void removeRole(Role role) {
        this.getRoles().remove(role);
    }
    
    public void removeAllRoles(Set<Role> roles) {
        this.getRoles().removeAll(roles);
    }

	/**
	 * @return the visibleFeeInfoToParent
	 */
	@Column(name = "visibleFeeInfoToParent", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getVisibleFeeInfoToParent() {
		return visibleFeeInfoToParent;
	}

	/**
	 * @param visibleFeeInfoToParent the visibleFeeInfoToParent to set
	 */
	public void setVisibleFeeInfoToParent(String visibleFeeInfoToParent) {
		this.visibleFeeInfoToParent = visibleFeeInfoToParent;
	}
    @Column(name="visibleTermWiseBalanceAmount",nullable=true,length =1,columnDefinition="char(1) default 'Y'")
	public String getVisibleTermWiseBalanceAmount() {
		return visibleTermWiseBalanceAmount;
	}

	public void setVisibleTermWiseBalanceAmount(String visibleTermWiseBalanceAmount) {
		this.visibleTermWiseBalanceAmount = visibleTermWiseBalanceAmount;
	}

	@Column(name = "feeRefund", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	public boolean isFeeRefund() {
		return feeRefund;
	}

	public void setFeeRefund(boolean feeRefund) {
		this.feeRefund = feeRefund;
	}
	@Column(name = "approvalForTeacherSMS", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getApprovalRequiredForClassTeacherCreatedSMS() {
		return approvalRequiredForClassTeacherCreatedSMS;
	}

	public void setApprovalRequiredForClassTeacherCreatedSMS(
			String approvalRequiredForClassTeacherCreatedSMS) {
		this.approvalRequiredForClassTeacherCreatedSMS = approvalRequiredForClassTeacherCreatedSMS;
	}
	 
}

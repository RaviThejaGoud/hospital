/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: CustomerPreferencesVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   July 14, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.ws.vo;




public class CustomerPreferencesVO {
	
	private String emailServiceEnabled;
	private String smsServiceEnabled;
	private String paymentSMSServiceEnabled;
	private String parentMobileNoVisibleToTeacher;
	private String feeBalanceAmountInPaymnetSMS ;
	private String feePaymentNotificationToManagement ;
	private String visibleFeeInfoToParent ;
	private String paymnetNotificationRoleIds;
	/**
	 * @return the parentMobileNoVisibleToTeacher
	 */
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

	/**
	 * @return the feeBalanceAmountInPaymnetSMS
	 */
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
	 * @return the visibleFeeInfoToParent
	 */
	public String getVisibleFeeInfoToParent() {
		return visibleFeeInfoToParent;
	}
	/**
	 * @param visibleFeeInfoToParent the visibleFeeInfoToParent to set
	 */
	public void setVisibleFeeInfoToParent(String visibleFeeInfoToParent) {
		this.visibleFeeInfoToParent = visibleFeeInfoToParent;
	}
	/**
	 * @return the paymnetNotificationRoleIds
	 */
	public String getPaymnetNotificationRoleIds() {
		return paymnetNotificationRoleIds;
	}
	/**
	 * @param paymnetNotificationRoleIds the paymnetNotificationRoleIds to set
	 */
	public void setPaymnetNotificationRoleIds(String paymnetNotificationRoleIds) {
		this.paymnetNotificationRoleIds = paymnetNotificationRoleIds;
	}
	/**
	 * @return the emailServiceEnabled
	 */
	public String getEmailServiceEnabled() {
		return emailServiceEnabled;
	}
	/**
	 * @param emailServiceEnabled the emailServiceEnabled to set
	 */
	public void setEmailServiceEnabled(String emailServiceEnabled) {
		this.emailServiceEnabled = emailServiceEnabled;
	}
	/**
	 * @return the smsServiceEnabled
	 */
	public String getSmsServiceEnabled() {
		return smsServiceEnabled;
	}
	/**
	 * @param smsServiceEnabled the smsServiceEnabled to set
	 */
	public void setSmsServiceEnabled(String smsServiceEnabled) {
		this.smsServiceEnabled = smsServiceEnabled;
	}
	/**
	 * @return the paymentSMSServiceEnabled
	 */
	public String getPaymentSMSServiceEnabled() {
		return paymentSMSServiceEnabled;
	}
	/**
	 * @param paymentSMSServiceEnabled the paymentSMSServiceEnabled to set
	 */
	public void setPaymentSMSServiceEnabled(String paymentSMSServiceEnabled) {
		this.paymentSMSServiceEnabled = paymentSMSServiceEnabled;
	}
	

}

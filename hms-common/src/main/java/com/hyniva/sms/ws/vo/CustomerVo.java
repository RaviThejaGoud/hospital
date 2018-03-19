package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;




public class CustomerVo extends SMSBaseVO{
	
	protected long id;
	protected String custEmail;                  
    protected String customerName;  
    protected String customerShortName;
    //protected String customerStatus;
    protected String firstName;
    protected String lastName;
    protected String contactNumber;
    protected String transportModuleStatus;
    protected String hostelModuleStatus;
    protected String schoolName;
    protected String subscriptionType;
    protected String parentPermissionStatus;
    protected String staffPermissionStatus;
    protected MasterCustomerVo masterCustomerVo;
    protected List<SchoolAreaVO> schoolAreaVoList;
	private List<AcademicYearVo> academicYearVoList;
	private boolean status;
	private String standardType ;
	private String recognisedBy;
	private String societyName;
	
	private CustomerPreferencesVO preferences;
	
	public String getRecognisedBy() {
		return recognisedBy;
	}
	public void setRecognisedBy(String recognisedBy) {
		this.recognisedBy = recognisedBy;
	}
	public String getSocietyName() {
		return societyName;
	}
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getParentPermissionStatus() {
		return parentPermissionStatus;
	}
	public void setParentPermissionStatus(String parentPermissionStatus) {
		this.parentPermissionStatus = parentPermissionStatus;
	}
	public String getStaffPermissionStatus() {
		return staffPermissionStatus;
	}
	public void setStaffPermissionStatus(String staffPermissionStatus) {
		this.staffPermissionStatus = staffPermissionStatus;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerShortName() {
		return customerShortName;
	}
	public void setCustomerShortName(String customerShortName) {
		this.customerShortName = customerShortName;
	}
	/*public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}*/
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getTransportModuleStatus() {
		return transportModuleStatus;
	}
	public void setTransportModuleStatus(String transportModuleStatus) {
		this.transportModuleStatus = transportModuleStatus;
	}
	public String getHostelModuleStatus() {
		return hostelModuleStatus;
	}
	public void setHostelModuleStatus(String hostelModuleStatus) {
		this.hostelModuleStatus = hostelModuleStatus;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public MasterCustomerVo getMasterCustomerVo() {
		return masterCustomerVo;
	}
	public void setMasterCustomerVo(MasterCustomerVo masterCustomerVo) {
		this.masterCustomerVo = masterCustomerVo;
	}
	
	public List<AcademicYearVo> getAcademicYearVoList() {
		if(ObjectFunctions.isNullOrEmpty(this.academicYearVoList))
		{
			this.academicYearVoList = new ArrayList<AcademicYearVo>(); 
		}
		return academicYearVoList;
	}
	public void setAcademicYearVoList(List<AcademicYearVo> academicYearVoList) {
		this.academicYearVoList = academicYearVoList;
	}
    
	public List<SchoolAreaVO> getSchoolAreaVoList() {
		if(ObjectFunctions.isNullOrEmpty(this.schoolAreaVoList))
		{
			this.schoolAreaVoList = new ArrayList<SchoolAreaVO>(); 
		}
		return schoolAreaVoList;
	}
	public void setSchoolAreaVoList(List<SchoolAreaVO> schoolAreaVoList) {
		this.schoolAreaVoList = schoolAreaVoList;
	}
	/**
	 * @return the standardType
	 */
	public String getStandardType() {
		return standardType;
	}
	/**
	 * @param standardType the standardType to set
	 */
	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}
	/**
	 * @return the preferences
	 */
	public CustomerPreferencesVO getPreferences() {
		return preferences;
	}
	/**
	 * @param preferences the preferences to set
	 */
	public void setPreferences(CustomerPreferencesVO preferences) {
		this.preferences = preferences;
	}
    
}

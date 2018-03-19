/*************************************************************************
* Copyright (C) 2015
* ACG
* All Rights Reserved 
*
* File: UserVO.java  
*************************************************************************
* Ver     Date          Name                      Description
* --- ------- ------------ ----------------------------------------------
* 0.1   June 18, 2015    Sreeram		       Created
**************************************************************************/
package com.hyniva.sms.ws.vo;

import java.util.HashSet;
import java.util.Set;

import com.hyniva.sms.ws.vo.base.KeyIdentifierVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.MappedExcelObject;
import com.urt.util.excel.parser.ParseType;

@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class UserVO extends SMSBaseVO {
	
	public String username;
	public String firstName;
	public String lastName;
	public String address;
	public String role;
	public String mobile;
	public long id;
	public String fullName;
	public int version;
	
	//User Variables
	
    public String confirmUserName;                    // required
    public String password;                    // required
    public String confirmPassword;
    public String tempPassword;
    public String passwordHint;
    
    public boolean enabled;
    public boolean accountExpired;
    public boolean accountLocked;
    public boolean credentialsExpired ;
    public boolean sharePrivacy;
    public boolean isUserOnlineNow;
    public long parentId;
    public long custId;
    public int bioMetricId; 
    @ExcelField(position = 2)
    public String admissionNumber; 
    public String barcode;
    @MappedExcelObject
    public PersonVO personVo;
    
    @MappedExcelObject
    public AddressVO primaryAddressVo;
    public AddressVO communicationAddressVo;
    public AddressVO tempararyAddressVo;
    
    public UserImageVO profileImageVo;
    public UserImageVO profileStuImageVo;
    public String staffNumber;
    
    public RoleVo rolesVo;
    
    public Set<RoleVo> rolesVoSet;
    public long userImageId;
    public String imageUrl;
    @ExcelField(position = 76)
    public String enrollmentCode;
    public String secondaryUsername;
    
    
    
    
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSecondaryUsername() {
		return secondaryUsername;
	}
	public void setSecondaryUsername(String secondaryUsername) {
		this.secondaryUsername = secondaryUsername;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getEnrollmentCode() {
		return enrollmentCode;
	}
	public void setEnrollmentCode(String enrollmentCode) {
		this.enrollmentCode = enrollmentCode;
	}
	public long getUserImageId() {
		return userImageId;
	}
	public void setUserImageId(long userImageId) {
		this.userImageId = userImageId;
	}
	public RoleVo getRolesVo() {
		return rolesVo;
	}
	public void setRolesVo(RoleVo rolesVo) {
		this.rolesVo = rolesVo;
	}
	public void addRole(RoleVo role) {
       // if(!hasRoleAlready(role))
        	getRolesVoSet().add(role);
        
    }
    public void addNewRole(RoleVo role) {
    	getRolesVoSet().add(role);
    }
    
    
	public Set<RoleVo> getRolesVoSet() {
		
		if(rolesVoSet == null)
        {
			rolesVoSet = new HashSet<RoleVo>();
        }
		return rolesVoSet;
	}
	public void setRolesVoSet(Set<RoleVo> rolesVoSet) {
		this.rolesVoSet = rolesVoSet;
	}
	public String getStaffNumber() {
		return staffNumber;
	}
	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
	public PersonVO getPersonVo() {
		return personVo;
	}
	public void setPersonVo(PersonVO personVo) {
		this.personVo = personVo;
	}
	public String getConfirmUserName() {
		return confirmUserName;
	}
	public void setConfirmUserName(String confirmUserName) {
		this.confirmUserName = confirmUserName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getTempPassword() {
		return tempPassword;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	public String getPasswordHint() {
		return passwordHint;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isAccountExpired() {
		return accountExpired;
	}
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
	public boolean isAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	public boolean isSharePrivacy() {
		return sharePrivacy;
	}
	public void setSharePrivacy(boolean sharePrivacy) {
		this.sharePrivacy = sharePrivacy;
	}
	public boolean isUserOnlineNow() {
		return isUserOnlineNow;
	}
	public void setUserOnlineNow(boolean isUserOnlineNow) {
		this.isUserOnlineNow = isUserOnlineNow;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public int getBioMetricId() {
		return bioMetricId;
	}
	public void setBioMetricId(int bioMetricId) {
		this.bioMetricId = bioMetricId;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void createIdentifier(long accountId, long custId, long academicYearId)
	{
		super.setIdentifier(new KeyIdentifierVO(accountId, custId, academicYearId));
	}
	public void createIdentifier(long accountId, long custId, long academicYearId, String channel)
	{
		super.setIdentifier(new KeyIdentifierVO(accountId, custId, academicYearId, channel));
	}
	public AddressVO getPrimaryAddressVo() {
		return primaryAddressVo;
	}
	public void setPrimaryAddressVo(AddressVO primaryAddressVo) {
		this.primaryAddressVo = primaryAddressVo;
	}
	public AddressVO getCommunicationAddressVo() {
		return communicationAddressVo;
	}
	public void setCommunicationAddressVo(AddressVO communicationAddressVo) {
		this.communicationAddressVo = communicationAddressVo;
	}
	public AddressVO getTempararyAddressVo() {
		return tempararyAddressVo;
	}
	public void setTempararyAddressVo(AddressVO tempararyAddressVo) {
		this.tempararyAddressVo = tempararyAddressVo;
	}
	public UserImageVO getProfileImageVo() {
		return profileImageVo;
	}
	public void setProfileImageVo(UserImageVO profileImageVo) {
		this.profileImageVo = profileImageVo;
	}
	public UserImageVO getProfileStuImageVo() {
		return profileStuImageVo;
	}
	public void setProfileStuImageVo(UserImageVO profileStuImageVo) {
		this.profileStuImageVo = profileStuImageVo;
	}
	
	
}
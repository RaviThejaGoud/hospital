package com.urt.persistence.model.jrexception;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;


@Entity
@Table(name = "vw_userJRExceptionDetails")
public class ViewUserJRExceptionDetails {
  
	private static final long serialVersionUID = 1567864261885107076L;
	
	private long jrExceptionId;
	private String exceptionName;
    private String methodName;
    private String className;
    private String fileName;
    private String exceptionLineNumber;
    private String cause;
    private String status; // P - Pending,  R - Resolved, I - Ignored, D - Permanently Ignored
    private Date exceptionDate;
    private Date exceptionDateWithoutTime;
    private long userId;
    private int custId;
    private int academicYearId;
    private String ipAddress;
    private String computerName;
    private String computerUsername;
    private String exceptionDescription;
    //Customer Details
    private String organization;
	private String custFirstName;
	private String custLastName;
	private String custFullName;
	private String custEmail;
	private String accountType;
	//User Details
	private String username;
	private long personId;
	private String firstName;
	private String lastName;
	private String fullName;
	// Customer Address
	private long addressId;
	private String addressLine1;
	private String streetName;
	private String city;
	private String stateCode;
	private String custAddress;
	//User role details
	private String roleId;
	private String roleName;
	private String roleDescription;
    
    
	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getExceptionDateWithoutTime() {
		return exceptionDateWithoutTime;
	}
	public void setExceptionDateWithoutTime(Date exceptionDateWithoutTime) {
		this.exceptionDateWithoutTime = exceptionDateWithoutTime;
	}
	/**
	 * @return the custFullName
	 */
	public String getCustFullName() {
		return custFullName;
	}
	/**
	 * @param custFullName the custFullName to set
	 */
	public void setCustFullName(String custFullName) {
		this.custFullName = custFullName;
	}
	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}
	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	/**
	 * @return the exceptionDescription
	 */
	public String getExceptionDescription() {
		return exceptionDescription;
	}
	/**
	 * @param exceptionDescription the exceptionDescription to set
	 */
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}
	/**
	 * @return the custFirstName
	 */
	public String getCustFirstName() {
		return custFirstName;
	}
	/**
	 * @param custFirstName the custFirstName to set
	 */
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}
	/**
	 * @return the custLastName
	 */
	public String getCustLastName() {
		return custLastName;
	}
	/**
	 * @param custLastName the custLastName to set
	 */
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	/**
	 * @return the custEmail
	 */
	public String getCustEmail() {
		return custEmail;
	}
	/**
	 * @param custEmail the custEmail to set
	 */
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
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
	 * @return the personId
	 */
	public long getPersonId() {
		return personId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(long personId) {
		this.personId = personId;
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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the addressId
	 */
	public long getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the custAddress
	 */
	public String getCustAddress() {
		return custAddress;
	}
	/**
	 * @param custAddress the custAddress to set
	 */
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}
	/**
	 * @param roleDescription the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	/**
	 * @return the jrExceptionId
	 */
	@Id
	public long getJrExceptionId() {
		return jrExceptionId;
	}
	/**
	 * @param jrExceptionId the jrExceptionId to set
	 */
	public void setJrExceptionId(long jrExceptionId) {
		this.jrExceptionId = jrExceptionId;
	}
	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}
	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
	/**
	 * @return the exceptionLineNumber
	 */
	public String getExceptionLineNumber() {
		return exceptionLineNumber;
	}
	/**
	 * @param exceptionLineNumber the exceptionLineNumber to set
	 */
	public void setExceptionLineNumber(String exceptionLineNumber) {
		this.exceptionLineNumber = exceptionLineNumber;
	}
	/**
	 * @return the computerName
	 */
	public String getComputerName() {
		return computerName;
	}
	/**
	 * @param computerName the computerName to set
	 */
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	/**
	 * @return the computerUsername
	 */
	public String getComputerUsername() {
		return computerUsername;
	}
	/**
	 * @param computerUsername the computerUsername to set
	 */
	public void setComputerUsername(String computerUsername) {
		this.computerUsername = computerUsername;
	}
	
	public int getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
	}
	/**
	 * @return the exceptionName
	 */
	public String getExceptionName() {
		return exceptionName;
	}
	/**
	 * @param exceptionName the exceptionName to set
	 */
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	/**
	 * @return the exceptionDate
	 */
	public Date getExceptionDate() {
		return exceptionDate;
	}
	/**
	 * @param exceptionDate the exceptionDate to set
	 */
	public void setExceptionDate(Date exceptionDate) {
		this.exceptionDate = exceptionDate;
	}
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @return the custId
	 */
	public int getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	@Transient
	public String getExceptionDateStr() {
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, this.getExceptionDate());
	}
	
}
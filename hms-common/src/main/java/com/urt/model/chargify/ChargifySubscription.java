package com.urt.model.chargify;


public class ChargifySubscription {
	
	private String customerId;   
	private String address;
    private String address_2;
    private String city;
    private String country;
    private String email;    
    private String first_name;    
    private String last_name;
    private String organization;
    private String phone;
    private String state;
    private String zip;  
    private String subId;
    private String subExpiryDate;
    private String subCreatedDate;    
    private String subCurrentState;
    private String subPreviousState;
    private String subActivatedDate;
    private String subNextAssessmentDate;
    private String subTrialStartDate;
    private String subTrialEndDate;
    private String subCurrentPeriodStartDate;
    private String subCurrentPeriodEndDate;
    private ChargifyProduct product;
    
	public String getSubCurrentPeriodStartDate() {
		return subCurrentPeriodStartDate;
	}
	public void setSubCurrentPeriodStartDate(String subCurrentPeriodStartDate) {
		this.subCurrentPeriodStartDate = subCurrentPeriodStartDate;
	}
	public String getSubCurrentPeriodEndDate() {
		return subCurrentPeriodEndDate;
	}
	public void setSubCurrentPeriodEndDate(String subCurrentPeriodEndDate) {
		this.subCurrentPeriodEndDate = subCurrentPeriodEndDate;
	}   
    
    public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getSubExpiryDate() {
		return subExpiryDate;
	}
	public void setSubExpiryDate(String subExpiryDate) {
		this.subExpiryDate = subExpiryDate;
	}
	public String getSubCreatedDate() {
		return subCreatedDate;
	}
	public void setSubCreatedDate(String subCreatedDate) {
		this.subCreatedDate = subCreatedDate;
	}
	public String getSubCurrentState() {
		return subCurrentState;
	}
	public void setSubCurrentState(String subCurrentState) {
		this.subCurrentState = subCurrentState;
	}
	public String getSubPreviousState() {
		return subPreviousState;
	}
	public void setSubPreviousState(String subPreviousState) {
		this.subPreviousState = subPreviousState;
	}
	public String getSubActivatedDate() {
		return subActivatedDate;
	}
	public void setSubActivatedDate(String subActivatedDate) {
		this.subActivatedDate = subActivatedDate;
	}
	public String getSubNextAssessmentDate() {
		return subNextAssessmentDate;
	}
	public void setSubNextAssessmentDate(String subNextAssessmentDate) {
		this.subNextAssessmentDate = subNextAssessmentDate;
	}
	public String getSubTrialStartDate() {
		return subTrialStartDate;
	}
	public void setSubTrialStartDate(String subTrialStartDate) {
		this.subTrialStartDate = subTrialStartDate;
	}
	public String getSubTrialEndDate() {
		return subTrialEndDate;
	}
	public void setSubTrialEndDate(String subTrialEndDate) {
		this.subTrialEndDate = subTrialEndDate;
	}
	
	
	public ChargifyProduct getProduct() {
		return product;
	}
	public void setProduct(ChargifyProduct product) {
		this.product = product;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String firstName) {
		first_name = firstName;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String lastName) {
		last_name = lastName;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
   
  
}

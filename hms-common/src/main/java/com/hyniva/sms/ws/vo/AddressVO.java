package com.hyniva.sms.ws.vo;

import java.util.Date;

import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class AddressVO {
	
	public long id;
	public String addressLine1 ="";
	public String addressLine2="";
	@ExcelField(position = 19)
	public String city="";
	@ExcelField(position = 20)
	public String state="";
	@ExcelField(position = 21)
	public String postalCode="";
	@ExcelField(position = 18)
	public String streetName="";
	public String stateName="";
	
	/*for residence address*/
	public String r_StreetName="";
	public String r_City="";
	public String r_StateShortName="";
	public String r_Pincode="";
	
	 
	public String c_StreetName="";
	public String c_City="";
	public String c_StateShortName="";
	public String c_Pincode="";
	
    public String addressLine3="";
    public String apartmentNumber="";
    public String boxNumber="";
    public String streetNumber="";
    public String streetPostDirection="";
    public String streetType="";
    public String suiteNumber="";
    public String addressType="";
    public String addressUsage="";
    public String country="";
    public Date duration;
    public Date effectiveDate;
    public Date endDate;
    public String ruralRoute="";
    public String zipCodeSupplement="";
    public String latitude="";
    public String longitude="";
    public String accuracy="";
    public String distance="";
    public String timeDuration="";
    public long stateId;
    public long districtId;
    public long mandalId;
    public long villageId;

    public String email="";
    public String url="";
    public String districtName;
	public Long houseTypeId;
		
	 
    
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public String getBoxNumber() {
		return boxNumber;
	}
	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreetPostDirection() {
		return streetPostDirection;
	}
	public void setStreetPostDirection(String streetPostDirection) {
		this.streetPostDirection = streetPostDirection;
	}
	public String getStreetType() {
		return streetType;
	}
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	public String getSuiteNumber() {
		return suiteNumber;
	}
	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getAddressUsage() {
		return addressUsage;
	}
	public void setAddressUsage(String addressUsage) {
		this.addressUsage = addressUsage;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getDuration() {
		return duration;
	}
	public void setDuration(Date duration) {
		this.duration = duration;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRuralRoute() {
		return ruralRoute;
	}
	public void setRuralRoute(String ruralRoute) {
		this.ruralRoute = ruralRoute;
	}
	public String getZipCodeSupplement() {
		return zipCodeSupplement;
	}
	public void setZipCodeSupplement(String zipCodeSupplement) {
		this.zipCodeSupplement = zipCodeSupplement;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}
	public long getStateId() {
		return stateId;
	}
	public void setStateId(long stateId) {
		this.stateId = stateId;
	}
	public long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}
	public long getMandalId() {
		return mandalId;
	}
	public void setMandalId(long mandalId) {
		this.mandalId = mandalId;
	}
	public long getVillageId() {
		return villageId;
	}
	public void setVillageId(long villageId) {
		this.villageId = villageId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getR_StreetName() {
		return r_StreetName;
	}
	public void setR_StreetName(String r_StreetName) {
		this.r_StreetName = r_StreetName;
	}
	public String getR_City() {
		return r_City;
	}
	public void setR_City(String r_City) {
		this.r_City = r_City;
	}
	public String getR_StateShortName() {
		return r_StateShortName;
	}
	public void setR_StateShortName(String r_StateShortName) {
		this.r_StateShortName = r_StateShortName;
	}
	public String getR_Pincode() {
		return r_Pincode;
	}
	public void setR_Pincode(String r_Pincode) {
		this.r_Pincode = r_Pincode;
	}
	public String getC_StreetName() {
		return c_StreetName;
	}
	public void setC_StreetName(String c_StreetName) {
		this.c_StreetName = c_StreetName;
	}
	public String getC_City() {
		return c_City;
	}
	public void setC_City(String c_City) {
		this.c_City = c_City;
	}
	public String getC_StateShortName() {
		return c_StateShortName;
	}
	public void setC_StateShortName(String c_StateShortName) {
		this.c_StateShortName = c_StateShortName;
	}
	public String getC_Pincode() {
		return c_Pincode;
	}
	public void setC_Pincode(String c_Pincode) {
		this.c_Pincode = c_Pincode;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return the houseType
	 *//*
	public String getHouseType() {
		return houseType;
	}
	*//**
	 * @param houseType the houseType to set
	 *//*
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}*/
	/**
	 * @return the houseTypeId
	 */
	public Long getHouseTypeId() {
		return houseTypeId;
	}
	/**
	 * @param houseTypeId the houseTypeId to set
	 */
	public void setHouseTypeId(Long houseTypeId) {
		this.houseTypeId = houseTypeId;
	}
	
}

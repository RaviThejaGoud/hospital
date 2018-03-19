package com.urt.persistence.model.common;

import java.util.Date;
import java.util.StringTokenizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.AddressVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.util.excel.AddressExcelRow;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Address.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Sep 3, 2006        Sreeram J           Created
/********************************************************************/

@Entity
@Table(name = "Address")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class Address  extends PersistentObject {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private String addressLine1;
     private String addressLine2;
     private String addressLine3;
     private String apartmentNumber;
     private String boxNumber;
     @ExcelField(position = 18)
     private String streetName;
     private String streetNumber;
     private String streetPostDirection;
     private String streetType;
     private String suiteNumber;
     private String addressType;
     private String addressUsage;
     @ExcelField(position = 19)
     private String city;
     private String country;
     private Date duration;
     private Date effectiveDate;
     private Date endDate;
     @ExcelField(position = 20)
     private String state;
     @ExcelField(position = 21)
     private String postalCode;
     private String ruralRoute;
     private String zipCodeSupplement;
     protected String latitude;
     protected String longitude;
     protected String accuracy;
     protected String distance;
     protected String timeDuration;
     protected long stateId;
     protected long districtId;
     protected long mandalId;
     protected long villageId;

     private String email;
     private String url;
     
     protected long countryId;
     private String districtName;
     private String taluka;
     private HouseType houseType;//holds the house type either Blue,Green,Red or Yellow 
     
     
     
     
    public String getTaluka() {
		return taluka;
	}

	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
     * @return Returns the email.
     */
     @Column(name = "email", nullable = true, length = 128)
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the url.
     */
    @Column(name = "url", nullable = true, length = 256)
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Address()
    {
        super();
    }
    /** default constructor */
    public Address(String address)
    {
        super();
        parseAddress(address);
    }
    private void parseAddress(String address)
    {
        //Address Line1, city, state, zip
        StringTokenizer strTokenizer = new StringTokenizer(address,",",false);
        setAddressLine1(strTokenizer.nextToken());
        setCity(strTokenizer.nextToken());
        setState(strTokenizer.nextToken());
        setPostalCode(strTokenizer.nextToken());
    }

	/** minimal constructor */
    public Address(String state, String postalCode, String zipCodeSupplement) {
        this.state = state;
        this.postalCode = postalCode;
        this.zipCodeSupplement = zipCodeSupplement;
    }
    
   
    public Address(String streetName, String city, String state,
			String postalCode) {
		super();
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	/**
     * creates a domestic postal address with one address line (which will be parsed).
     * If the zip code has 9 or 10 digits the first 5 digits will
     * be the zip code and the last four digits will be the zip
     * code supplement.  The country will be set to USA, and the
     * type will be set to domestic.
     * 
     * The usage will NOT be defaulted to anything.
     * The address 
     * @param street
     * @param city
     * @param state
     * @param zipCode
     * @param street2
     * @return Address
     */
    public static Address createUSAddress(
            String addressLine1,
            String addressLine2,
            String city, 
            String state,
            String postalCode,
            String email,
            String usage
           )
    {

        Address address = new Address();
/*        Collection strings = new ArrayList(4);

        strings.add(addressLine1);
        strings.add(city);
        strings.add(state);
        strings.add(postalCode);
*/
        //if(StringFunctions.checkNotEmpty(strings))

        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setCity(city);
        address.setState(state);
        address.setCountry("USA");
        address.setPostalCode(postalCode);
        address.setEmail(email);
        address.setAddressUsage(usage);
        
        //if(!(addressId == null))
          //  address.setAddressId(addressId);
        
        return address;
    }

    @Column(name = "addressLine1", nullable = true, length = 512)
    public String getAddressLine1() {
        return this.addressLine1;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Column(name = "addressLine2", nullable = true, length = 128)
    public String getAddressLine2() {
        return this.addressLine2;
    }
    
    @Transient
    public String getAddressLine2Desc() {
        if(StringFunctions.isNullOrEmpty(this.addressLine2))
        {
            return "";
        }
        else
        {
            return ", " + this.addressLine2;
        }
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Column(name = "addressLine3", nullable = true, length = 64)
    public String getAddressLine3() {
        return this.addressLine3;
    }
    
    @Transient
    public String getAddressLine3Desc() {
        if(StringFunctions.isNullOrEmpty(this.addressLine3))
        {
            return "";
        }
        else
        {
            return ", " + this.addressLine3;
        }
    }
    
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @Column(name = "apartmentNumber", nullable = true, length = 10)
    public String getApartmentNumber() {
        return this.apartmentNumber;
    }
    
    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Column(name = "boxNumber", nullable = true, length = 10)
    public String getBoxNumber() {
        return this.boxNumber;
    }
    
    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    @Column(name = "streetName", nullable = true, length = 200)
    public String getStreetName() {
        return this.streetName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Column(name = "streetNumber", nullable = true, length = 10)
    public String getStreetNumber() {
        return this.streetNumber;
    }
    
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Column(name = "streetPostDirection", nullable = true, length = 256)
    public String getStreetPostDirection() {
        return this.streetPostDirection;
    }
    @Transient
    public String getStreetPostDirectionDesc() {
        if(StringFunctions.isNullOrEmpty(this.streetPostDirection))
        {
            return "";
        }
        else
        {
            return this.streetPostDirection;
        }
    }
    
    public void setStreetPostDirection(String streetPostDirection) {
        this.streetPostDirection = streetPostDirection;
    }

    @Column(name = "streetType", nullable = true, length = 64)
    public String getStreetType() {
        return this.streetType;
    }
    
    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    @Column(name = "suiteNumber", nullable = true, length = 64)
    public String getSuiteNumber() {
        return this.suiteNumber;
    }
    
    public void setSuiteNumber(String suiteNumber) {
        this.suiteNumber = suiteNumber;
    }

    @Column(name = "addressType", nullable = true, length = 1)
    public String getAddressType() {
        return this.addressType;
    }
    @Transient
    public String getAddressTypeDesc() {
        if("C".equalsIgnoreCase(this.addressType))
            return "Church";
        else if("P".equalsIgnoreCase(this.addressType))
            return "Leader Address";
        else
            return "Other";
    }
    
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Column(name = "addressUsage", nullable = true, length = 16)
    public String getAddressUsage() {
        return this.addressUsage;
    }
    
    public void setAddressUsage(String addressUsage) {
        this.addressUsage = addressUsage;
    }

    @Column(name = "city", nullable = true, length = 64)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "country", nullable = true, length = 64)
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

   
    @Column(name = "duration", nullable = true, length = 12)
    public Date getDuration() {
        return this.duration;
    }
    
    public void setDuration(Date duration) {
        this.duration = duration;
    }

    @Column(name = "effectiveDate", nullable = true, length = 12)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    @Column(name = "endDate", nullable = true, length = 12)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "state", nullable = true, length = 3)
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "postalCode", nullable = true, length = 12)
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "ruralRoute", nullable = true, length = 64)
    public String getRuralRoute() {
        return this.ruralRoute;
    }
    
    public void setRuralRoute(String ruralRoute) {
        this.ruralRoute = ruralRoute;
    }

    @Column(name = "zipCodeSupplement", nullable = true, length = 6)
    public String getZipCodeSupplement() {
        return this.zipCodeSupplement;
    }

	public void setZipCodeSupplement(String zipCodeSupplement) {
        this.zipCodeSupplement = zipCodeSupplement;
    }
	
	@Column(name = "accuracy", nullable = true, length = 32)
    public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	
	@Column(name = "distance", nullable = true, length = 32)
    public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	@Column(name = "timeDuration", nullable = true, length = 32)
	public String getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        final Address address1 = (Address) o;

        if (addressLine1 != null ? !addressLine1.equals(address1.addressLine1) : address1.addressLine1 != null) return false;
        if (city != null ? !city.equals(address1.city) : address1.city != null) return false;
        if (country != null ? !country.equals(address1.country) : address1.country != null) return false;
        if (postalCode != null ? !postalCode.equals(address1.postalCode) : address1.postalCode != null) return false;
        if (state != null ? !state.equals(address1.state) : address1.state != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (addressLine1 != null ? addressLine1.hashCode() : 0);
        result = 29 * result + (city != null ? city.hashCode() : 0);
        result = 29 * result + (state != null ? state.hashCode() : 0);
        result = 29 * result + (country != null ? country.hashCode() : 0);
        result = 29 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }
   
    /**
     * @see java.lang.Object#toString()
     * 
     * Domestic Address Formatted as
     *  addressLine1; addressLine2; city, state   zipCode[-zipCodeSupplement]
     * 
     * Military Address Formatted as
     *  addressLine1; addressLine2; city postalCode;
     * @Override
     */
    
    public String toString() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        buffer
            .append("Address Object => ")
            .append(" Id: ")
            .append(getId())
            .append(" Version: ")
            //
            .append(" Addressline 1: ")
            .append(getAddressLine1())
            .append(" City : ")
            .append(getCity())
            .append(" State : ")
            .append(getState())
            .append(" Zip : ")
            .append(getPostalCode());
            
        return buffer.toString();
    }
    
    @Transient
    public String getStrAddress() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        
        if(!StringFunctions.isNullOrEmpty(this.addressLine1))
        {
            buffer.append(getAddressLine1()).append(", ");
        }
        if(!StringFunctions.isNullOrEmpty(this.addressLine2))
        {
            buffer.append(getAddressLine2()).append(", ");
        }
        if(!StringFunctions.isNullOrEmpty(this.addressLine3))
        {
            buffer.append(getAddressLine3()).append(", ");
        }
       
        if(!StringFunctions.isNullOrEmpty(this.city))
        {
            buffer.append(getCity()).append(", ");
        }
        if(!StringFunctions.isNullOrEmpty(this.state))
        {
            buffer.append(getState());
        }
        if(!StringFunctions.isNullOrEmpty(this.postalCode))
        {
            buffer.append(", ").append(getPostalCode());
        }
           
        return buffer.toString();
    }
    /**
     * @Description: Do not modify, this is used for Map
     * @return string
     */
    
    @Transient
    public String getAddressForMap() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        
        if(!StringFunctions.isNullOrEmpty(this.addressLine1))
        {
            buffer.append(getAddressLine1());
        }
        if(!StringFunctions.isNullOrEmpty(this.addressLine2))
        {
            buffer.append(", ").append(getAddressLine2());
        }
        if(!StringFunctions.isNullOrEmpty(this.city))
        {
            buffer.append(", ").append(getCity());
        }
        if(!StringFunctions.isNullOrEmpty(this.state))
        {
            buffer.append(", ").append(getState());
        }
        if(!StringFunctions.isNullOrEmpty(this.postalCode))
        {
            buffer.append(", ").append(getPostalCode());
        }
           
        return buffer.toString();
    }
    @Transient
    public String getStreetAddress() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        if(!StringFunctions.isNullOrEmpty(this.addressLine1))
        {
            buffer.append(getAddressLine1());
        }
        if(!StringFunctions.isNullOrEmpty(this.addressLine2))
        {
            buffer.append(", ").append(getAddressLine2());
        }
        if(!StringFunctions.isNullOrEmpty(this.addressLine3))
        {
            buffer.append(", ").append(getAddressLine3());
        }
        return buffer.toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        Address myClass = (Address) object;
        return new CompareToBuilder().append(this.zipCodeSupplement,
                myClass.zipCodeSupplement).append(this.addressLine2,
                myClass.addressLine2).append(this.postalCode,
                myClass.postalCode).append(this.streetName, myClass.streetName)
                .append(this.url, myClass.url).append(this.city, myClass.city)
                .append(this.addressLine1, myClass.addressLine1).append(
                        this.addressUsage, myClass.addressUsage).append(
                        this.streetNumber, myClass.streetNumber).append(
                        this.effectiveDate, myClass.effectiveDate).append(
                        this.addressType, myClass.addressType).append(
                        this.addressLine3, myClass.addressLine3).append(
                        this.apartmentNumber, myClass.apartmentNumber).append(
                        this.country, myClass.country).append(
                        this.streetPostDirection, myClass.streetPostDirection)
                .append(this.streetType, myClass.streetType).append(this.state,
                        myClass.state).append(this.email, myClass.email)
                .append(this.boxNumber, myClass.boxNumber).append(
                        this.endDate, myClass.endDate).append(this.suiteNumber,
                        myClass.suiteNumber).append(this.duration,
                        myClass.duration).append(this.ruralRoute,
                        myClass.ruralRoute).toComparison();
    }
    
    public void copyFrom(Address anAddress)
    {
        this.addressLine1 = anAddress.getAddressLine1();
        this.addressLine2=anAddress.getAddressLine2();
        this.addressLine3=anAddress.getAddressLine3();
        this.streetName=anAddress.getStreetName();
        this.streetPostDirection=anAddress.getStreetPostDirection();
        this.city=anAddress.getCity();
        this.state=anAddress.getState();
        this.stateId = anAddress.getStateId();
        this.postalCode=anAddress.getPostalCode();   
        this.addressType=anAddress.getAddressType();
        this.addressUsage=anAddress.getAddressUsage();
        this.latitude=anAddress.getLatitude();
        this.longitude=anAddress.getLongitude();
        this.accuracy=anAddress.getAccuracy();
        this.distance=anAddress.getDistance();
        this.timeDuration=anAddress.getTimeDuration();
        this.email=anAddress.getEmail();
        this.lastUpdatedDate = new Date();
        this.districtName=anAddress.getDistrictName();
        this.taluka=anAddress.getTaluka();
    }
    
    @Transient
    public String getCityState()
    {
      return getCity()+", "+getState();
    }

	/**
	 * @return the latitude
	 */
    @Column(name = "latitude", nullable = true, length = 32)
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	@Column(name = "longitude", nullable = true, length = 32)
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Address copyBeans(Address newAddress, Address oldAddress)
    {
		oldAddress.setStreetName(newAddress.getStreetName());
		oldAddress.setAddressLine1(newAddress.getAddressLine1());
		oldAddress.setCity(newAddress.getCity());
		oldAddress.setState(newAddress.getState());
		oldAddress.setPostalCode(newAddress.getPostalCode());
		oldAddress.setStateId(newAddress.getStateId());
		oldAddress.setLastUpdatedDate(new Date());
	     
		return oldAddress;
		
    }
	@Column(name = "stateId", nullable = true, columnDefinition="bigint(20) default 0")
	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}
	@Column(name = "districtId", nullable = true,columnDefinition="bigint(20) default 0")
	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}
	@Column(name = "mandalId", nullable = true, columnDefinition="bigint(20) default 0")
	public long getMandalId() {
		return mandalId;
	}

	public void setMandalId(long mandalId) {
		this.mandalId = mandalId;
	}
	@Column(name = "villageId", nullable = true, columnDefinition="bigint(20) default 0")
	public long getVillageId() {
		return villageId;
	}

	public void setVillageId(long villageId) {
		this.villageId = villageId;
	}

	public void copyStaffExcelData(AddressExcelRow address) {
		if (StringFunctions.isNotNullOrEmpty(address.getAddressLine1())) {
			if (address.getAddressLine1().length() <= 250) {
				this.addressLine1 = address.getAddressLine1();
			} else
				this.addressLine1 = null;
		}
		this.city = address.getCity();
		this.state = address.getState();
		this.postalCode = address.getPostalCode();
		this.email = address.getEmail();
		this.stateId = address.getStateId();
		this.lastUpdatedDate = new Date();
	}
	
	
	public Address copyFromVoToEntity(Address address, AddressVO addressVO)
	{
		if(address.getId() == 0)
			address.id = addressVO.id;
		address.addressLine1 = addressVO.addressLine1;
		address.addressLine2 = addressVO.addressLine2;
		address.addressLine3 = addressVO.addressLine3;
		address.apartmentNumber = addressVO.apartmentNumber;
		address.boxNumber = addressVO.boxNumber;
		address.streetName = addressVO.streetName;
		address.streetNumber = addressVO.streetNumber;
		address.streetPostDirection = addressVO.streetPostDirection;
		address.streetType = addressVO.streetType;
		address.suiteNumber = addressVO.suiteNumber;
		address.addressType = addressVO.addressType;
		address.addressUsage = addressVO.addressUsage;
		address.city = addressVO.city;
		address.country = addressVO.country;
		address.duration = addressVO.duration;
		address.effectiveDate = addressVO.effectiveDate;
		address.endDate = addressVO.endDate;
		address.state = addressVO.state;
		address.postalCode = addressVO.postalCode;
		address.ruralRoute = addressVO.ruralRoute;
		address.zipCodeSupplement = addressVO.zipCodeSupplement;
		address.latitude = addressVO.latitude;
		address.longitude = addressVO.longitude;
		address.accuracy = addressVO.accuracy;
		address.distance = addressVO.distance;
		address.timeDuration = addressVO.timeDuration;
		address.stateId = addressVO.stateId;
		address.districtId = addressVO.districtId;
		address.mandalId = addressVO.mandalId;
		address.villageId = addressVO.villageId;
		address.email = addressVO.email;
		address.url = addressVO.url;
		address.districtName = addressVO.districtName;
		
		return address;
	}
	
	public AddressVO copyFromEntityToVo(Address address)
	{
		AddressVO addressVO = new AddressVO();
		addressVO.addressLine1 = address.addressLine1;
		addressVO.addressLine2 = address.addressLine2;
		addressVO.addressLine3 = address.addressLine3;
		addressVO.apartmentNumber = address.apartmentNumber;
		addressVO.boxNumber = address.boxNumber;
		addressVO.streetName = address.streetName;
		addressVO.streetNumber = address.streetNumber;
		addressVO.streetPostDirection = address.streetPostDirection;
		addressVO.streetType = address.streetType;
		addressVO.suiteNumber = address.suiteNumber;
		addressVO.addressType = address.addressType;
		addressVO.addressUsage = address.addressUsage;
		addressVO.city = address.city;
		addressVO.country = address.country;
		addressVO.duration = address.duration;
		addressVO.effectiveDate = address.effectiveDate;
		addressVO.endDate = address.endDate;
		addressVO.state = address.state;
		addressVO.postalCode = address.postalCode;
		addressVO.ruralRoute = address.ruralRoute;
		addressVO.zipCodeSupplement = address.zipCodeSupplement;
		addressVO.latitude = address.latitude;
		addressVO.longitude = address.longitude;
		addressVO.accuracy = address.accuracy;
		addressVO.distance = address.distance;
		addressVO.timeDuration = address.timeDuration;
		addressVO.stateId = address.stateId;
		addressVO.districtId = address.districtId;
		addressVO.mandalId = address.mandalId;
		addressVO.villageId = address.villageId;
		addressVO.email = address.email;
		addressVO.url = address.url;
		addressVO.districtName = address.districtName;
		  if(!ObjectFunctions.isNullOrEmpty(address.getHouseType())){
			  addressVO.setHouseTypeId(address.getHouseType().getId());
		  }
		
		return addressVO;
	}
	@Column(name = "countryId", nullable = true,columnDefinition="bigint(20) default 0")
	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	
	 @Transient
	    public String getFormatAddress() {

	        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
	        
	        if(!StringFunctions.isNullOrEmpty(this.streetName))
	        {
	            buffer.append(getStreetName()).append(",");
	        }
	        if(!StringFunctions.isNullOrEmpty(this.city))
	        {
	            buffer.append(getCity()).append(", ");
	        }
	        if(!StringFunctions.isNullOrEmpty(this.taluka))
	        {
	            buffer.append(getTaluka()).append(",");
	        }
	        if(!StringFunctions.isNullOrEmpty(this.districtName))
	        {
	            buffer.append(getDistrictName()).append(",");
	        }
	        if(!StringFunctions.isNullOrEmpty(this.state))
	        {
	            buffer.append(getState());
	        }
	        if(!StringFunctions.isNullOrEmpty(this.postalCode))
	        {
	            buffer.append(", ").append(getPostalCode());
	        }
	           
	        return buffer.toString();
	    }

	/**
	 * @return the houseType
	 */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="houseTypeId", insertable=true, updatable=true) 
		
	public HouseType getHouseType() {
		return houseType;
	}

	/**
	 * @param houseType the houseType to set
	 */
	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}

	
	/**
	 * @return the houseType
	 *//*
	 @Column(name = "houseType", nullable = true, length = 20)
	public String getHouseType() {
		return houseType;
	}

	*//**
	 * @param houseType the houseType to set
	 *//*
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}*/
}
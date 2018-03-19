package com.urt.persistence.model.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "Country")
public class Country extends PersistentObject {
	
   private static final long serialVersionUID = 1L;
	
   private String countryCode;
   private String countryName;
   private String status;
   private long noOfStates;
   private String phoneCode;
   private CountryCurrency countryCurrency;
   
   
	
   public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(String countryCode, String countryName, String status,
			 long noOfStates,String phoneCode) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.status = status;
		//this.id = countryId;
		this.noOfStates = noOfStates;
		this.phoneCode = phoneCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getNoOfStates() {
		return noOfStates;
	}
	public void setNoOfStates(long noOfStates) {
		this.noOfStates = noOfStates;
	}
	
	 public int compareTo(Object object) {
   	Country target = (Country) object;
       if (target.getCountryName() != null && this.getCountryName()!= null)
       {
          return this.getCountryName().compareToIgnoreCase(target.getCountryName());
       }
       return 0;
   }
	
	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode 
				+ ", countryName=" + countryName + ", noOfStates="
				+ noOfStates + ", status=" + status + "]";
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
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="countryCurrencyId", insertable=true, updatable=true)
	public CountryCurrency getCountryCurrency() {
		return countryCurrency;
	}
	public void setCountryCurrency(CountryCurrency countryCurrency) {
		this.countryCurrency = countryCurrency;
	}
}
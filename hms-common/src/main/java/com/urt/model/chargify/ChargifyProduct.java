package com.urt.model.chargify;

import java.text.DecimalFormat;

public class ChargifyProduct {
	
    private String id;   
	private String priceInCents;
    private String name;
    private String description;
    private String productFamilyName;
    private String productFamilyId;
    private String productFamilyDescription;
    private String accountingCode;    
    private String intervalUnit;
    private String interval;
    private int userLimit;
   
    
    /**
	 * @return the userLimit
	 */
	public int getUserLimit() {
		return userLimit;
	}
	/**
	 * @param userLimit the userLimit to set
	 */
	public void setUserLimit(int userLimit) {
		this.userLimit = userLimit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPriceInCents() {
		return priceInCents;
	}
	public void setPriceInCents(String priceInCents) {
		this.priceInCents = priceInCents;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductFamilyName() {
		return productFamilyName;
	}
	public void setProductFamilyName(String productFamilyName) {
		this.productFamilyName = productFamilyName;
	}
	public String getProductFamilyId() {
		return productFamilyId;
	}
	public void setProductFamilyId(String productFamilyId) {
		this.productFamilyId = productFamilyId;
	}
	public String getProductFamilyDescription() {
		return productFamilyDescription;
	}
	public void setProductFamilyDescription(String productFamilyDescription) {
		this.productFamilyDescription = productFamilyDescription;
	}
	public String getAccountingCode() {
		return accountingCode;
	}
	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}
	public String getIntervalUnit() {
		return intervalUnit;
	}
	public void setIntervalUnit(String intervalUnit) {
		this.intervalUnit = intervalUnit;  
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
     
	public String getPricePlan(){
		try{
			double price=Double.parseDouble(getPriceInCents())/100;
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			price=Double.valueOf(twoDForm.format(price));			
			if(Integer.parseInt(getInterval())==1)
				return "$"+String.format("%.2f", price)+"/"+getIntervalUnit();
			else
				return "$"+String.format("%.2f", price)+"/"+getInterval()+getIntervalUnit()+"s";
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
	public String getPlanAmount(){
		try{
			double price=Double.parseDouble(getPriceInCents())/100;
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			price=Double.valueOf(twoDForm.format(price));
			return "$"+String.format("%.2f", price);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
  
}

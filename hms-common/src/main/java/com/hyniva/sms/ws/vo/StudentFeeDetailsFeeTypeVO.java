package com.hyniva.sms.ws.vo;


public class StudentFeeDetailsFeeTypeVO {

	protected long id;
	protected String name;
	protected String amount;
	protected String paidStatus;
	protected String paidAmount;
	protected String discountAmount;
	protected String concessionAmount;
	
	public long getId(){
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getAmount(){
		return amount;
	}
	public void setAmount(String amount){
		this.amount = amount;
	}
	public String getPaidStatus() {
		return paidStatus;
	}
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getConcessionAmount() {
		return concessionAmount;
	}
	public void setConcessionAmount(String concessionAmount) {
		this.concessionAmount = concessionAmount;
	}
	
}


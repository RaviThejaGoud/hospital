package com.urt.persistence.model.subscription;


/**
 * This class is used to represent available roles in the database.</p>
 *
 * <p><a href="Role.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Version by Dan Kibler dan@getrolling.com
 *  Extended to implement Acegi GrantedAuthority interface 
 *  	by David Carter david@carter.net
 *
 * @struts.form extends="BaseForm"
 * @hibernate.class table="role"
 */
public class ProductLineItem {   
    
	String name;
	String description;
	String amount;
	String number;
	String quantity;
	String taxAmt;
	/*String itemWeightValue;
	String itemWeightUnit;
	String itemLengthValue;
	String itemLengthUnit;
	String itemWidthValue;
	String itemWidthUnit;
	String itemHeightValue;
	String itemHeightUnit;
	
	L_NAMEn
	L_DESCn
	L_AMTn
	L_NUMBERn
	L_QTYn
	L_TAXAMTn
	L_ITEMWEIGHTVALUEn
	L_ITEMWEGHTUNITn
	L_ITEMLENGTHVALUEn
	L_ITEMLENGTHUNITn
	L_ITEMWIDTHVALUEn
	L_ITEMWIDTHUNITn
	L_ITEMHEIGHTVALUEn
	L_ITEMHEIGHTUNITn
	
	*
	*/	
   
    public ProductLineItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the taxAmt
	 */
	public String getTaxAmt() {
		return taxAmt;
	}

	/**
	 * @param taxAmt the taxAmt to set
	 */
	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}
    
   

}

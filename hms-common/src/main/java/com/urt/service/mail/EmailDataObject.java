/**
 * 
 */
package com.urt.service.mail;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * @author Sreeram
 *
 */
public class EmailDataObject {

	private String subject;
	private String body;
	private String signature;
	private String footer;
	private String fromAddress;
	private String[] fromAddresses;
	private String toAddress;
	private String[] toAddresses;
	private String commonFooter;
	
	public String getCommonFooter() {
		return commonFooter;
	}

	public void setCommonFooter(String commonFooter) {
		this.commonFooter = commonFooter;
	}

	/**
	 * 
	 */
	public EmailDataObject() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String[] getFromAddresses() {
		return fromAddresses;
	}

	public void setFromAddresses(String[] fromAddresses) {
		this.fromAddresses = fromAddresses;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String[] getToAddresses() {
		return toAddresses;
	}

	public void setToAddresses(String[] toAddresses) {
		this.toAddresses = toAddresses;
	}
	
	 /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("subject", this.subject)
                .append("body", this.body)
                .append("footer", this.footer)
                .append("signature", this.signature)
                .append("commonFooter", this.commonFooter)
                .append("fromAddress", this.fromAddress)
               .toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    
    public int compareTo(Object object) {
        return 0;
    }

}

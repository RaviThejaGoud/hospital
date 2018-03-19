package com.hyniva.sms.ws.vo;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class CircularMessagesVO extends SMSBaseVO{
	
	//protected ViewCircularUsersVO viewCircularUsersVO;
	protected String circularDescription;
	protected String alertType;
	protected String to;
	protected String classSectionIds;
	
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getClassSectionIds() {
		return classSectionIds;
	}
	public void setClassSectionIds(String classSectionIds) {
		this.classSectionIds = classSectionIds;
	}
	public String getCircularDescription() {
		return circularDescription;
	}
	public void setCircularDescription(String circularDescription) {
		this.circularDescription = circularDescription;
	}
	

}

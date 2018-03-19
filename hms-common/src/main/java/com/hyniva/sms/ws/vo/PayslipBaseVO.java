package com.hyniva.sms.ws.vo;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class PayslipBaseVO extends SMSBaseVO{
	
	private List<PayslipVO> paySlips;

	 private int month;
	 private int year;
	public List<PayslipVO> getPaySlips() {
		return paySlips;
	}
	public void setPaySlips(List<PayslipVO> paySlips) {
		this.paySlips = paySlips;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}
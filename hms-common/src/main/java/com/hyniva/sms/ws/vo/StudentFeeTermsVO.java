package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;

public class StudentFeeTermsVO {

	protected long id;
	protected String feeTerm;
	protected List<StudentFeeDetailsFeeTypeVO> feeTypes;
	protected String dueDate;
	protected String fromDate;
	protected String toDate;
	protected String feeSettingName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFeeTerm() {
		return feeTerm;
	}
	public void setFeeTerm(String feeTerm) {
		this.feeTerm = feeTerm;
	}
	public List<StudentFeeDetailsFeeTypeVO> getFeeTypes() {
		if(ObjectFunctions.isNullOrEmpty(this.feeTypes))
			this.feeTypes = new ArrayList<StudentFeeDetailsFeeTypeVO>(); 

		return feeTypes;
	}
	public void setFeeTypes(List<StudentFeeDetailsFeeTypeVO> feeTypes) {
		this.feeTypes = feeTypes;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFeeSettingName() {
		return feeSettingName;
	}
	public void setFeeSettingName(String feeSettingName) {
		this.feeSettingName = feeSettingName;
	}
	
}
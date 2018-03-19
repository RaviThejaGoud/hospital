package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;

public class StudentFeeDetailsVO {

	protected long studentId;
	protected String studentName;
	protected List<StudentFeeTermsVO> feeTerms;
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public List<StudentFeeTermsVO> getFeeTerms() {
		if(ObjectFunctions.isNullOrEmpty(this.feeTerms))
			this.feeTerms = new ArrayList<StudentFeeTermsVO>(); 

		return feeTerms;
	}
	public void setFeeTerms(List<StudentFeeTermsVO> feeTerms) {
		this.feeTerms = feeTerms;
	}
}
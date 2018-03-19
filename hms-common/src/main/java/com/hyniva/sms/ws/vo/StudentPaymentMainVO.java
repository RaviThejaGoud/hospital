package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentPaymentMainVO extends SMSBaseVO{

	protected List<StudentPaymentVO> studentPaymentVOs;
	protected List<StudentFeePaidDetailsVO> studentFeePaidDetailsVOs;
	protected List<ExcessPaymentVO> excessPaymentVOs;
	
	public List<StudentPaymentVO> getStudentPaymentVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.studentPaymentVOs))
		{
			this.studentPaymentVOs = new ArrayList<StudentPaymentVO>(); 
		}
		return studentPaymentVOs;
	}

	public void setStudentPaymentVOs(List<StudentPaymentVO> studentPaymentVOs) {
		this.studentPaymentVOs = studentPaymentVOs;
	}

	public List<StudentFeePaidDetailsVO> getStudentFeePaidDetailsVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.studentFeePaidDetailsVOs))
		{
			this.studentFeePaidDetailsVOs = new ArrayList<StudentFeePaidDetailsVO>(); 
		}
		return studentFeePaidDetailsVOs;
	}

	public void setStudentFeePaidDetailsVOs(List<StudentFeePaidDetailsVO> studentFeePaidDetailsVOs) {
		
		this.studentFeePaidDetailsVOs = studentFeePaidDetailsVOs;
	}

	public List<ExcessPaymentVO> getExcessPaymentVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.excessPaymentVOs))
		{
			this.excessPaymentVOs = new ArrayList<ExcessPaymentVO>(); 
		}
		return excessPaymentVOs;
	}

	public void setExcessPaymentVOs(List<ExcessPaymentVO> excessPaymentVOs) {
		this.excessPaymentVOs = excessPaymentVOs;
	}
	
	
}

package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewStudentFeePaymentDetailsMainVO extends SMSBaseVO{
	protected List<ViewStudentFeePaymentDetailsVO> viewStudentFeePaymentDetailsVOList;

	public List<ViewStudentFeePaymentDetailsVO> getViewStudentFeePaymentDetailsVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.viewStudentFeePaymentDetailsVOList))
		{
			this.viewStudentFeePaymentDetailsVOList = new ArrayList<ViewStudentFeePaymentDetailsVO>(); 
		}
		return viewStudentFeePaymentDetailsVOList;
	}

	public void setViewStudentFeePaymentDetailsVOList(List<ViewStudentFeePaymentDetailsVO> viewStudentFeePaymentDetailsVOList) {
		this.viewStudentFeePaymentDetailsVOList = viewStudentFeePaymentDetailsVOList;
	}
	
}

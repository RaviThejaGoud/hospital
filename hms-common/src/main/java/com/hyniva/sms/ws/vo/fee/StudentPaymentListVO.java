/********************************************************************
 * HYNIVA (C) 2017
 * All Rights Reserved 
 *
 * File: StudentPaymentListVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   August 22, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.ws.vo.fee;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentPaymentListVO extends SMSBaseVO{

	private List<StudentFeePaymentVO> studentPayments;

	/**
	 * @return the studentPayments
	 */
	public List<StudentFeePaymentVO> getStudentPayments() {
		return studentPayments;
	}

	/**
	 * @param studentPayments the studentPayments to set
	 */
	public void setStudentPayments(List<StudentFeePaymentVO> studentPayments) {
		this.studentPayments = studentPayments;
	}
	
}

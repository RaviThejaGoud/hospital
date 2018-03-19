/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: StudentAttendanceListVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   July 14, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.ws.vo.attendance;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class StudentAttendanceListVO  extends SMSBaseVO{
	
	private Long studyClassId;
	List<StudentAttendanceVO> studentAttendance ;

	/**
	 * @return the studentAttendance
	 */
	public List<StudentAttendanceVO> getStudentAttendance() {
		return studentAttendance;
	}

	/**
	 * @param studentAttendance the studentAttendance to set
	 */
	public void setStudentAttendance(List<StudentAttendanceVO> studentAttendance) {
		this.studentAttendance = studentAttendance;
	}

	/**
	 * @return the studyClassId
	 */
	public Long getStudyClassId() {
		return studyClassId;
	}

	/**
	 * @param studyClassId the studyClassId to set
	 */
	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}
}

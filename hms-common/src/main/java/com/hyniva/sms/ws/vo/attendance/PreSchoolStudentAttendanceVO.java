/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: PreSchoolStudentAttendanceVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   July 14, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.ws.vo.attendance;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class PreSchoolStudentAttendanceVO extends SMSBaseVO {
	
	private Long studyClassId;
	
	StudentAttendanceVO studentAttendance;

	/**
	 * @return the studentAttendance
	 */
	public StudentAttendanceVO getStudentAttendance() {
		return studentAttendance;
	}

	/**
	 * @param studentAttendance the studentAttendance to set
	 */
	public void setStudentAttendance(StudentAttendanceVO studentAttendance) {
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

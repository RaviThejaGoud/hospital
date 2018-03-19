package com.hyniva.sms.ws.vo;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class AdmissionSettingsVO extends SMSBaseVO {
    protected AcademicYearVo academicYear;

	public AcademicYearVo getAcademicYear() {
		if(ObjectFunctions.isNullOrEmpty(this.academicYear)){
			this.academicYear = new AcademicYearVo();
		}
		return academicYear;
	}

	public void setAcademicYear(AcademicYearVo academicYear) {
		this.academicYear = academicYear;
	}
}

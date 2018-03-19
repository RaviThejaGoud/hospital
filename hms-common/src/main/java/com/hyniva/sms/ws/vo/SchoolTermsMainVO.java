package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class SchoolTermsMainVO extends SMSBaseVO{

	protected List<SchoolTermsVO> schoolTermsVOs;

	public List<SchoolTermsVO> getSchoolTermsVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.schoolTermsVOs))
		{
			this.schoolTermsVOs = new ArrayList<SchoolTermsVO>(); 
		}
		return schoolTermsVOs;
	}

	public void setSchoolTermsVOs(List<SchoolTermsVO> schoolTermsVOs) {
		this.schoolTermsVOs = schoolTermsVOs;
	}
	
	
}

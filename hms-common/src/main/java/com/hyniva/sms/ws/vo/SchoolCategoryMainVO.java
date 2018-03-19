package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class SchoolCategoryMainVO extends SMSBaseVO {

	protected List<SchoolCategoryVO> schoolCategoryVOs;

	public List<SchoolCategoryVO> getSchoolCategoryVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.schoolCategoryVOs))
		{
			this.schoolCategoryVOs = new ArrayList<SchoolCategoryVO>(); 
		}
		return schoolCategoryVOs;
	}

	public void setSchoolCategoryVOs(List<SchoolCategoryVO> schoolCategoryVOs) {
		this.schoolCategoryVOs = schoolCategoryVOs;
	}
	
	
}

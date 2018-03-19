package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class SchoolHolidaysMainVO extends SMSBaseVO{
	
	private List<SchoolHolidaysVO> schoolHolidaysVOList;

	public List<SchoolHolidaysVO> getSchoolHolidaysVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.schoolHolidaysVOList))
		{
			this.schoolHolidaysVOList = new ArrayList<SchoolHolidaysVO>(); 
		}
		return schoolHolidaysVOList;
	}

	public void setSchoolHolidaysVOList(List<SchoolHolidaysVO> schoolHolidaysVOList) {
		this.schoolHolidaysVOList = schoolHolidaysVOList;
	}
	

	 

}

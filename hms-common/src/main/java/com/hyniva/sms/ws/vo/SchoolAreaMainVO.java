package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.KeyIdentifierVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class SchoolAreaMainVO extends SMSBaseVO{
	private KeyIdentifierVO identifier;
	private List<SchoolAreaVO> schoolAreaVO;
	
	
	public List<SchoolAreaVO> getSchoolAreaVO() {
		if(ObjectFunctions.isNullOrEmpty(this.schoolAreaVO))
		{
			this.schoolAreaVO = new ArrayList<SchoolAreaVO>(); 
		}
		return schoolAreaVO;
	}

	public void setSchoolAreaVO(List<SchoolAreaVO> schoolAreaVO) {
		this.schoolAreaVO = schoolAreaVO;
	}

	public KeyIdentifierVO getIdentifier() {
		return identifier;
	}
	
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(KeyIdentifierVO identifier) {
		this.identifier = identifier;
	}
}

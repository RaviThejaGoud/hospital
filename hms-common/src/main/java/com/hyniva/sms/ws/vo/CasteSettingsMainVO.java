package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class CasteSettingsMainVO extends SMSBaseVO{

	private List<CasteSettingsVO> casteSettingsVOList;

	public List<CasteSettingsVO> getCasteSettingsVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.casteSettingsVOList))
		{
			this.casteSettingsVOList = new ArrayList<CasteSettingsVO>(); 
		}
		return casteSettingsVOList;
	}

	public void setCasteSettingsVOList(List<CasteSettingsVO> casteSettingsVOList) {
		this.casteSettingsVOList = casteSettingsVOList;
	}
	
}

package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class CommonTypeMainVO extends SMSBaseVO{
 
	private List<CommonTypeVO> commonTypeVOList;
	
	public List<CommonTypeVO> getCommonTypeVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.commonTypeVOList))
		{
			this.commonTypeVOList = new ArrayList<CommonTypeVO>(); 
		}
		return commonTypeVOList;
	}

	public void setCommonTypeVOList(List<CommonTypeVO> commonTypeVOList) {
		this.commonTypeVOList = commonTypeVOList;
	}

}

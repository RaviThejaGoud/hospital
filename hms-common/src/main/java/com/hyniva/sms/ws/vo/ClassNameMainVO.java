package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ClassNameMainVO extends SMSBaseVO{
	protected List<ClassNameVO> classNameVOs;

	public List<ClassNameVO> getClassNameVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.classNameVOs))
		{
			this.classNameVOs = new ArrayList<ClassNameVO>(); 
		}
		return classNameVOs;
	}

	public void setClassNameVOs(List<ClassNameVO> classNameVOs) {
		this.classNameVOs = classNameVOs;
	}
	
	
}

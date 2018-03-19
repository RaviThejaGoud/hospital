package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class ClassTeacherMainVO extends SMSBaseVO{
	
	protected List<ClassTeacherVO> classTeacherVOs;

	public List<ClassTeacherVO> getClassTeacherVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.classTeacherVOs))
		{
			this.classTeacherVOs = new ArrayList<ClassTeacherVO>(); 
		}
		return classTeacherVOs;
	}

	public void setClassTeacherVOs(List<ClassTeacherVO> classTeacherVOs) {
		this.classTeacherVOs = classTeacherVOs;
	}
	
	
}

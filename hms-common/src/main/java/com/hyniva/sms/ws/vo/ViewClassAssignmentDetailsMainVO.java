package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewClassAssignmentDetailsMainVO  extends SMSBaseVO{

	protected List<ViewClassAssignmentDetailsVO> classAssignmentVO;

	/**
	 * @return the classAssignmentVO
	 */
	public List<ViewClassAssignmentDetailsVO> getClassAssignmentVO() {
		if(ObjectFunctions.isNullOrEmpty(this.classAssignmentVO))
		{
			this.classAssignmentVO = new ArrayList<ViewClassAssignmentDetailsVO>(); 
		}
		return classAssignmentVO;
	}

	/**
	 * @param classAssignmentVO the classAssignmentVO to set
	 */
	public void setClassAssignmentVO(
			List<ViewClassAssignmentDetailsVO> classAssignmentVO) {
		this.classAssignmentVO = classAssignmentVO;
	}

	
}

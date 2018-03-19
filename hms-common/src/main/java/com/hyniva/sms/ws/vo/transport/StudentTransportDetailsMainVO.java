package com.hyniva.sms.ws.vo.transport;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class StudentTransportDetailsMainVO extends SMSBaseVO{

	protected List<StudentTransportDetailsVo> studentTransportDetails;

	public List<StudentTransportDetailsVo> getStudentTransportDetails() {
		if(ObjectFunctions.isNullOrEmpty(this.studentTransportDetails))
		{
			this.studentTransportDetails = new ArrayList<StudentTransportDetailsVo>(); 
		}
		return studentTransportDetails;
	}

	public void setStudentTransportDetails(List<StudentTransportDetailsVo> studentTransportDetails) {
		this.studentTransportDetails = studentTransportDetails;
	}
}

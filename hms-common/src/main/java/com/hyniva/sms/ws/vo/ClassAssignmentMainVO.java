package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.KeyIdentifierVO;

public class ClassAssignmentMainVO {
	protected List<ClassAssignmentVO> classAssignmentVOList;
	private KeyIdentifierVO identifier;

	public KeyIdentifierVO getIdentifier() {
		return identifier;
	}

	public void setIdentifier(KeyIdentifierVO identifier) {
		this.identifier = identifier;
	}
	
	public List<ClassAssignmentVO> getClassAssignmentVOList() {
		if (ObjectFunctions.isNullOrEmpty(this.classAssignmentVOList)) {
			this.classAssignmentVOList = new ArrayList<ClassAssignmentVO>();
		}
		return classAssignmentVOList;
	}

	public void setClassAssignmentVOList(List<ClassAssignmentVO> classAssignmentVOList) {
		this.classAssignmentVOList = classAssignmentVOList;
	}
}

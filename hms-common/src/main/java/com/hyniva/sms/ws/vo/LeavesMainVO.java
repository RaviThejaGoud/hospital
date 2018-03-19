package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.KeyIdentifierVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class LeavesMainVO extends SMSBaseVO{
	protected List<LeavesVO> leavesList;
	private KeyIdentifierVO identifier;

	public KeyIdentifierVO getIdentifier() {
		return identifier;
	}

	public void setIdentifier(KeyIdentifierVO identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the leavesList
	 */
	public List<LeavesVO> getLeavesList() {
		if(ObjectFunctions.isNullOrEmpty(this.leavesList))
		{
			this.leavesList = new ArrayList<LeavesVO>(); 
		}
		return leavesList;
	}

	/**
	 * @param leavesList the leavesList to set
	 */
	public void setLeavesList(List<LeavesVO> leavesList) {
		this.leavesList = leavesList;
	}

	

}

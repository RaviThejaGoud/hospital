package com.hyniva.sms.ws.vo;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewRouteVehiclesMainVO extends SMSBaseVO{

	protected List<ViewAssignedVehiclestoRoutesWithBoardingPointsVo> vehiclesList;

	public List<ViewAssignedVehiclestoRoutesWithBoardingPointsVo> getVehiclesList() {
		return vehiclesList;
	}

	public void setVehiclesList(
			List<ViewAssignedVehiclestoRoutesWithBoardingPointsVo> vehiclesList) {
		this.vehiclesList = vehiclesList;
	}

	

	
	

}

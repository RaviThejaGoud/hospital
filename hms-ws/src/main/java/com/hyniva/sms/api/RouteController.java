package com.hyniva.sms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.api.base.SMSBaseController;
import com.hyniva.sms.api.constants.SMSURIConstants;
import com.hyniva.sms.api.exception.SMSAPIException;
import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.RouteMainVO;
import com.hyniva.sms.ws.vo.RouteNotifyVO;
import com.hyniva.sms.ws.vo.RouteTrackVO;
import com.hyniva.sms.ws.vo.RouteVo;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.VehicleRouteMainVO;
import com.hyniva.sms.ws.vo.ViewRouteTrackMainVO;
import com.hyniva.sms.ws.vo.ViewRouteTrackVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.admin.AdminManager;

@RestController
public class RouteController <T extends UserTokenVO> extends SMSBaseController<T> {
	
	@Autowired
	protected AdminManager adminManager;
	/**
	 * @author SUBRAMANYAM
	 *
	 * @param <long>
	 * 
	 * @return <SMSBaseVO>
	 */
	@RequestMapping(value = SMSURIConstants.GET_ROUTE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getAllRoutesForDriver(@PathVariable("accountId") long accountId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId > 0){
			RouteMainVO routeVo = adminManager.getAllRoutesForDriver(accountId);
			if(!ObjectFunctions.isNullOrEmpty(routeVo))
				return routeVo;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_SCHOOL_ROUTES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getAllRoutes(@PathVariable("custId") long custId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId > 0){
			VehicleRouteMainVO routeVo = adminManager.getAllRoutes(custId);
			if(!ObjectFunctions.isNullOrEmpty(routeVo))
				return routeVo;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}

	@RequestMapping(value = SMSURIConstants.UPDATE_ROUTE_POINTS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO updateRoutePoints(@RequestBody RouteVo routeVo)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean responseCode = adminManager.updateRoutePoints(routeVo);
		if(responseCode)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.ROUTE_NOTIFY_NEAR_BUS_STOP, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO notifyNearBusStop(@RequestBody RouteNotifyVO routeNotifyVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean responseCode = adminManager.notifyNearBusStop(routeNotifyVO);
		if(responseCode)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.ROUTE_TRACK_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO saveRouteTrack(@RequestBody RouteTrackVO routeTrackVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean responseCode = adminManager.saveRouteTrack(routeTrackVO);
		if(responseCode)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());

		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_ROUTE_VEHICLE_LOCATION, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getVehicleLastLocation(@PathVariable("driverId") long driverId, @PathVariable("routeId") long routeId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(driverId > 0 && routeId > 0){
			ViewRouteTrackVO routeVo = adminManager.getVehicleLastLocation(driverId, routeId);
			if(!ObjectFunctions.isNullOrEmpty(routeVo))
				return routeVo;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_SEND_BUS_PICKUP_DROP_NOTIFICATION, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO sendBusPickupDropNotification(@PathVariable("driverId") long driverId, @PathVariable("routeId") long routeId, @PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(driverId > 0 && routeId > 0){
			boolean responseCode = adminManager.sendBusPickupDropNotification(driverId, routeId, type);
			if(responseCode)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_ALL_ROUTE_VEHICLE_LOCATION, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getAllRouteVehiclesLocations(@PathVariable("accountId") long accountId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId > 0){
			ViewRouteTrackMainVO routeVo = adminManager.getAllRouteVehiclesLocations(accountId);
			if(!ObjectFunctions.isNullOrEmpty(routeVo))
				return routeVo;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_SCHOOL_ROUTES_BY_ACCOUNT_ID_AND_TYPE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getRoutesByAccountIdAndType(@PathVariable("accountId") long accountId, @PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId > 0 && !StringFunctions.isNullOrEmpty(type)){
			VehicleRouteMainVO routeVo = adminManager.getAllRoutesByAccountIdAndType(accountId,type);
			if(!ObjectFunctions.isNullOrEmpty(routeVo))
				return routeVo;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@Override
	protected Validator setValidator() {
		// TODO Auto-generated method stub
		return null;
	}
}

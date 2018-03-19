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
import com.hyniva.sms.api.base.SMSBaseController;
import com.hyniva.sms.api.constants.SMSURIConstants;
import com.hyniva.sms.api.exception.SMSAPIException;
import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.CustomerVo;
import com.hyniva.sms.ws.vo.SchoolAreaMainVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.user.UserManager;

@RestController
public class CustomerController<T extends UserTokenVO> extends SMSBaseController<T> {
	
	@Autowired
	protected UserManager userManager;
	
	/**
	 * @author Ganesh
	 *
	 * @param <long>
	 * 
	 * @return <customerVo>
	 */
	@RequestMapping(value = SMSURIConstants.GET_CUSTOMER_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getCustomerDetails(@PathVariable("ui") long id)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(id > 0){
			CustomerVo customerVo = userManager.getCustomerDetails(id);
			if(!ObjectFunctions.isNullOrEmpty(customerVo))
				return customerVo;
			else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			}
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
			
		}
		return smsBaseVO;
	}
	
	/**
	 * @author Ganesh
	 *
	 * @param <long>
	 * 
	 * @return <customerVo>
	 */
	@RequestMapping(value = SMSURIConstants.CUSTOMER_SCHOOL_AREA_DETAILS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getCustomerSchoolAreaDetails(@RequestBody SchoolAreaMainVO schoolAreaMainVo)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean schoolAreaStatus = userManager.createCustomerSchoolAreaDetails(schoolAreaMainVo);
		if (schoolAreaStatus)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());

		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@Override
	protected Validator setValidator() {
		// TODO Auto-generated method stub
		return null;
	}
} 
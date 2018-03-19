/********************************************************************
 * HYNIVA
 * All Rights Reserved 
 *
 * File: LoginController.java
 ********************************************************************
 *
 *  Ver   Date            Author                Description
 *  ====  ========        ============          ==================
 *  0.1   June 18, 2015   Sreeram		       Created
/********************************************************************/
package com.hyniva.sms.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
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
import com.hyniva.sms.ws.vo.ChangePasswordVO;
import com.hyniva.sms.ws.vo.ForgotPasswordVO;
import com.hyniva.sms.ws.vo.SignUpVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.user.UserManager;

@RestController
public class LoginController<T extends UserTokenVO> extends SMSBaseController<T> {
	
	private static final Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	protected UserManager userManager;

	/**
	 * Authenticate user with database credentails and security token
	 * 
	 * @param UserTokenVO
	 * @return
	 * @throws SMSAPIException
	 */

	@RequestMapping(value = SMSURIConstants.USER_AUTHENTICATE, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO createApplication(@RequestBody UserTokenVO userTokenVO) throws SMSAPIException {
		
		UserVO userVO = userManager.getUserDetails(userTokenVO);
		if (ObjectFunctions.isNullOrEmpty(userVO)) {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_PASSWORD.getErrorCode(),ERROR_CODE_ENUM.INVALID_PASSWORD.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		} else
			return userVO;

	}
	
	//{"identifier": {"custId": 0,"accountId": 0},"email": "null","mobileNumber": "9980054540"}
	@RequestMapping(value = SMSURIConstants.FORGOT_PASSWORD, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO forgotPassword(@RequestBody ForgotPasswordVO forgotPasswordVO) throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(!StringFunctions.isNullOrEmpty(forgotPasswordVO.getMobileNumber()) || !StringFunctions.isNullOrEmpty(forgotPasswordVO.getEmail())){
			boolean passwordStatus = userManager.getUserForgotPasswordDetails(forgotPasswordVO);
			if(passwordStatus){
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
			}else{
				 apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.USER_NOT_FOUND.getErrorCode(),ERROR_CODE_ENUM.USER_NOT_FOUND.getErrorDesc());
			}
		}else
			 apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.USER_NOT_FOUND.getErrorCode(),ERROR_CODE_ENUM.USER_NOT_FOUND.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.CHANGE_PASSWORD, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO changePassword(@RequestBody ChangePasswordVO changePasswordVO) throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(!ObjectFunctions.isNullOrEmpty(changePasswordVO.getUserId()) && !StringFunctions.isNullOrEmpty(changePasswordVO.getNewPassword())){
			boolean passwordStatus = userManager.changeUserPasswordDetails(changePasswordVO);
			if(passwordStatus){
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
			}else{
				 apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INCORRECT_OLD_PASSWORD.getErrorCode(),ERROR_CODE_ENUM.INCORRECT_OLD_PASSWORD.getErrorDesc());
			}
		}else
			 apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.USER_NOT_FOUND.getErrorCode(),ERROR_CODE_ENUM.USER_NOT_FOUND.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.SIGN_UP, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO signUp(@RequestBody SignUpVO signUpVO) throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		log.debug("IS staff :: "+signUpVO.getIsStaff());
		if(!StringFunctions.isNullOrEmpty(signUpVO.getMobileNumber())  && !ObjectFunctions.isNullOrEmpty(signUpVO.getRandomNumber())  && !signUpVO.getIsStaff()){
			UserVO userVO = userManager.getStudentDetailsByAdmissionAndMobileNumbers(signUpVO.getAdmissionNumber(),signUpVO.getMobileNumber(), Integer.valueOf(signUpVO.getRandomNumber()));
			if(!ObjectFunctions.isNullOrEmpty(userVO))
				return userVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}
		else if(!StringFunctions.isNullOrEmpty(signUpVO.getMobileNumber())  && !ObjectFunctions.isNullOrEmpty(signUpVO.getRandomNumber()) && signUpVO.getIsStaff()){
			UserVO userVO = userManager.getStaffDetailsByMobileNumber(signUpVO.getMobileNumber(), Integer.valueOf(signUpVO.getRandomNumber()));
			if(!ObjectFunctions.isNullOrEmpty(userVO))
				return userVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}
		else
			 apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.USER_NOT_FOUND.getErrorCode(),ERROR_CODE_ENUM.USER_NOT_FOUND.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}

	@Override
	protected Validator setValidator() {
		// TODO Auto-generated method stub
		return null;
	}

}

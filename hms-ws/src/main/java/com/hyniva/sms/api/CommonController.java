package com.hyniva.sms.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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
import com.hyniva.sms.ws.vo.CasteSettingsMainVO;
import com.hyniva.sms.ws.vo.CommonTypeMainVO;
import com.hyniva.sms.ws.vo.MessagesMainVO;
import com.hyniva.sms.ws.vo.UserAddressChangeVO;
import com.hyniva.sms.ws.vo.UserImageVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.hyniva.sms.ws.vo.common.SMSVO;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.student.StudentManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;

@RestController
public class CommonController<T extends UserTokenVO> extends SMSBaseController<T> {

	@Autowired
	protected AdminManager adminManager;
	
	@Autowired
	protected StudentManager studentManager;
	
	@Autowired
	protected CommunicationManager communicationManager;
	
	
	/*http://dev.eazyschool.in/api/commonType/46/RELIGION*/
	@RequestMapping(value = SMSURIConstants.GET_COMMONTYPES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getCommonTypes(@PathVariable("custId") long custId, @PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId > 0){
			CommonTypeMainVO commonTypeMainVO = adminManager.getCommonTypes(custId,type);
			if(!ObjectFunctions.isNullOrEmpty(commonTypeMainVO))
				return commonTypeMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
	
	/*http://dev.eazyschool.in/api/casteSettings/46*/
	@RequestMapping(value = SMSURIConstants.GET_CASTE_SETTINGS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getCommonTypes(@PathVariable("custId") long custId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId > 0){
			CasteSettingsMainVO casteSettingsMainVO = adminManager.getCasteSettings(custId);
			if(!ObjectFunctions.isNullOrEmpty(casteSettingsMainVO))
				return casteSettingsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
	
	/*http://dev.eazyschool.in/api/sendSMS/submit*/
	@RequestMapping(value = SMSURIConstants.SEND_SCHOOLWIDE_MESSAGES, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO saveSMSDetailsAppToWebApplication(@RequestBody MessagesMainVO messagesMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		int messages = communicationManager.sendSMSForSchoolWideMessages(messagesMainVO);
		if (messages == 2)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_SENDSMS.getErrorCode(),ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_SENDSMS.getErrorDesc());
		else if (messages == 0)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else if (messages == 3)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.EMAIL_NOT_DELIVERED.getErrorCode(),ERROR_CODE_ENUM.EMAIL_NOT_DELIVERED.getErrorDesc());
		else if (messages == 4)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.WRONG_CREDENTIALS.getErrorCode(),ERROR_CODE_ENUM.WRONG_CREDENTIALS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	/*http://dev.eazyschool.in/api/changeUserPrimaryAddress*/
	/*{"identifier":{"accountId":16228,"custId":69,"academicYearId":0,"channel":null,"status":null},"primaryAddressVo":{"addressLine1":"F NO.B-401, P NO.14 N 15 SHIV COMPLEX SECTOR-7","addressLine2":"","city":"Bangalore","state":"KA","postalCode":"560043"}}*/

	@RequestMapping(value = SMSURIConstants.CHANGE_USER_PRIMARY_ADDRESS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO changeUserPrimaryAddress(@RequestBody UserAddressChangeVO userAddressChangeVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean messages = adminManager.changeUserPrimaryAddress(userAddressChangeVO);
		if (messages)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		smsBaseVO.setIdentifier(userAddressChangeVO.getIdentifier());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	/*http://dev.eazyschool.in/api/changeUserMobileNumber*/
	/*{"identifier":{"accountId":16228,"custId":69,"academicYearId":0,"channel":null,"status":null},"mobileNumber":"9234567890"}*/

	@RequestMapping(value = SMSURIConstants.CHANGE_USER_MOBILE_NUMBER, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO changeUserMobileNumber(@RequestBody UserAddressChangeVO userAddressChangeVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean messages = adminManager.changeUserMobileNumber(userAddressChangeVO);
		if (messages)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		
		smsBaseVO.setIdentifier(userAddressChangeVO.getIdentifier());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.MOVE_ATTACHMENTS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO moveAttachments()throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean messages = adminManager.moveImageFiles();
		if (messages)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.MOVE_USER_IMAGES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO moveUserImages()throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean messages = adminManager.moveUserImageFiles();
		if (messages)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	@RequestMapping(value = SMSURIConstants.USER_IMAGE_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO updateUserImage(@RequestBody UserImageVO userImageVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO  = studentManager.updateUserImage(userImageVO);
		return smsBaseVO;
	}
	
	
	
	@RequestMapping(value = SMSURIConstants.MOVE_PAYSLIPS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO movePayslips()throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean messages = adminManager.movePayslips();
		if (messages)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	@RequestMapping(value = SMSURIConstants.MOVE_TIMETABLES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO moveTimetables()throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean messages = adminManager.moveTimetables();
		if (messages)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.MOVE_CERTIFICATES_DOCUMENTS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO moveCertificatesAndDocuments()throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean messages = adminManager.moveCertificatesAndDocuments();
		if (messages)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	/**
	 * This API used to send SMS message
	 * @param smsVO
	 * @param result
	 * @param req
	 * @return
	 * @throws SMSAPIException
	 */
	@RequestMapping(value = SMSURIConstants.SEND_SMS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO sendSMS(@RequestBody SMSVO smsVO,BindingResult result, HttpServletRequest req)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		smsBaseVO.setIdentifier(smsVO.getIdentifier());
		int status = adminManager.sendSMS(smsVO);
		if (status == 0){
			apistatusVO = new APIStatusVO();
			smsBaseVO.getIdentifier().setStatus(ERROR_CODE_ENUM.SMS_SUCCESS_MESSAGE.getErrorDesc());
		}else if(status == 1){
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SMS_ERROR_MESSAGE.getErrorCode(),ERROR_CODE_ENUM.SMS_ERROR_MESSAGE.getErrorDesc());
		}else if (status == 2){
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_SENDSMS.getErrorCode(),ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_SENDSMS.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	@Override
	protected Validator setValidator() {
		return null;
	}
	
}

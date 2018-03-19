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
import com.hyniva.sms.ws.vo.AndroidMobileUsersVO;
import com.hyniva.sms.ws.vo.CircularMessagesVO;
import com.hyniva.sms.ws.vo.MessagesMainVO;
import com.hyniva.sms.ws.vo.ReplyScrapMessageMainVO;
import com.hyniva.sms.ws.vo.RunCCReportsVO;
import com.hyniva.sms.ws.vo.ScrapMessageVO;
import com.hyniva.sms.ws.vo.SendMessageVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.staff.StaffManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;


@RestController
public class MessagesController<T extends UserTokenVO> extends SMSBaseController<T>{
	@Autowired
	protected AdminManager adminManager;
	@Autowired
	protected StaffManager staffManager;
	@Autowired
	protected CommunicationManager communicationManager;
	
	@RequestMapping(value = SMSURIConstants.GET_MESSAGES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getMessages(@PathVariable("academicYearId") long academicYearId, @PathVariable("accountId") long accountId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId > 0 && accountId > 0){
			MessagesMainVO messagesMainVO = adminManager.getMessages(academicYearId,accountId);
			if (!ObjectFunctions.isNullOrEmpty(messagesMainVO))
				return messagesMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.ANDROID_REGISTRATION, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO androidRegistration(@RequestBody AndroidMobileUsersVO androidMobileUsersVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(!ObjectFunctions.isNullOrEmpty(androidMobileUsersVO)){
			boolean androidRegStatus = adminManager.androidRegistration(androidMobileUsersVO);
			if (androidRegStatus)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.UPDATE_MESSAGE_DETAILS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO updateMessage(@RequestBody ScrapMessageVO scrapMessageVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(!ObjectFunctions.isNullOrEmpty(scrapMessageVO)){
			boolean messageStatus = staffManager.updateMessage(scrapMessageVO.getId());
			apistatusVO = messageStatus?new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc()):new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	@RequestMapping(value = SMSURIConstants.SUBMIT_REPLY_MESSAGES, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO submitReplyMessage(@RequestBody ReplyScrapMessageMainVO replyScrapMessageMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		int responseCode  = staffManager.submitReplyMessage(replyScrapMessageMainVO);
		if(responseCode == 0)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else if(responseCode == 1)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.MESSAGE_ERROR.getErrorCode(),ERROR_CODE_ENUM.MESSAGE_ERROR.getErrorDesc());
		else if(responseCode == 2)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorCode(),ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorDesc());
		else if(responseCode == 4)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.USER_ERROR.getErrorCode(),ERROR_CODE_ENUM.USER_ERROR.getErrorDesc());
		else if(responseCode == 5)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SCRAPMESSAGE_ERROR.getErrorCode(),ERROR_CODE_ENUM.SCRAPMESSAGE_ERROR.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.RUN_CCE_REPORTS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO runCCEReports(@RequestBody RunCCReportsVO runCCReportsVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean ccReportsStatus  = adminManager.runCCEReports(runCCReportsVO);
		if(ccReportsStatus)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.SUBMIT_MESSAGE, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO submitParentMessage(@RequestBody SendMessageVO sendMessageVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		int responseCode  = staffManager.submitParentMessage(sendMessageVO);
		if(responseCode == 0)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else if(responseCode == 1)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.MESSAGE_ERROR.getErrorCode(),ERROR_CODE_ENUM.MESSAGE_ERROR.getErrorDesc());
		else if(responseCode == 2)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorCode(),ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.SUBMIT_CIRCULAR_MESSAGE, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO submitCircularMessage(@RequestBody CircularMessagesVO circularMessagesVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(!ObjectFunctions.isNullOrEmpty(circularMessagesVO)){
			int responseCode  = communicationManager.submitCircularMessage(circularMessagesVO);
			int availableSmsCount= communicationManager.getAvailableSmsCount(circularMessagesVO.getIdentifier().getCustId(),circularMessagesVO.getIdentifier().getAcademicYearId());
			if(availableSmsCount == 0  && ("S".equalsIgnoreCase(circularMessagesVO.getAlertType()) || "SE".equalsIgnoreCase(circularMessagesVO.getAlertType())))
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_CIRCULAR.getErrorCode(),ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_CIRCULAR.getErrorDesc());
			else if(responseCode == 0)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
			else if(responseCode == 1)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			else if(responseCode == 2)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorCode(),ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorDesc());
			else if(responseCode == 3)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.USER_ERROR.getErrorCode(),ERROR_CODE_ENUM.USER_ERROR.getErrorDesc());
			else if(responseCode == 4)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());
			}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@Override
	protected Validator setValidator() {
		// TODO Auto-generated method stub
		return null;
	}
} 
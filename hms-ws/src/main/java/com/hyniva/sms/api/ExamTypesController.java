package com.hyniva.sms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.api.base.SMSBaseController;
import com.hyniva.sms.api.constants.SMSURIConstants;
import com.hyniva.sms.api.exception.SMSAPIException;
import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.ExamSchedulesMainVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.admin.AdminManager;

@RestController
public class ExamTypesController<T extends UserTokenVO> extends SMSBaseController<T> {
	@Autowired
	protected AdminManager adminManager;
	
	/*http: http://dev.eazyschool.in/api/examSchedule/55*/
	@RequestMapping(value = SMSURIConstants.GET_EXAMSCHEDULES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getExamSchedule(@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId>0){
			ExamSchedulesMainVO examSchedulesMainVO = adminManager.getExamSchedules(academicYearId,0,"E"); //E means empty
			if(!ObjectFunctions.isNullOrEmpty(examSchedulesMainVO))
				return examSchedulesMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	/*http: http://dev.eazyschool.in/api/examSchedule/55*/
	@RequestMapping(value = SMSURIConstants.GET_EXAMSCHEDULES_BY_ACCOUNTID, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getExamScheduleByAccountId(@PathVariable("academicYearId") long academicYearId,@PathVariable("accountId") long accountId,@PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId>0){
			ExamSchedulesMainVO examSchedulesMainVO = adminManager.getExamSchedules(academicYearId,accountId,type);
			if(!ObjectFunctions.isNullOrEmpty(examSchedulesMainVO))
				return examSchedulesMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
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

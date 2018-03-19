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
import com.hyniva.sms.ws.vo.ClassNameMainVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.admin.AdminManager;

@RestController
public class ClassController<T extends UserTokenVO> extends SMSBaseController<T> {
	
	@Autowired
	protected AdminManager adminManager;

	/**
	 * @author Sunanda
	 *
	 * @param <long>
	 * 
	 * @return <classNameMainVo>
	 */
	@RequestMapping(value = SMSURIConstants.GET_CLASS_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getClassDetails(@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId > 0){
			ClassNameMainVO classNameMainVo = adminManager.getClassDetails(academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(classNameMainVo))
				return classNameMainVo;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_STUDY_CLASS_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudyClassesDetailsRespectedPerson(@PathVariable("accountId") long accountId, @PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(type.length() > 0 && accountId > 0){
			ClassNameMainVO studyClassVo = adminManager.getStudyClassesDetailsRespectedPerson(accountId,type);
			if(!ObjectFunctions.isNullOrEmpty(studyClassVo))
				return studyClassVo;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
	@Override
	protected Validator setValidator() {
		// TODO Auto-generated method stub
		return null;
	}
} 
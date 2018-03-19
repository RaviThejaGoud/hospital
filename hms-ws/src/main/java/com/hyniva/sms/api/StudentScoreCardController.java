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
import com.hyniva.sms.ws.vo.StudentScoreCardVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.admin.AdminManager;

@RestController
public class StudentScoreCardController <T extends UserTokenVO> extends SMSBaseController<T>{
	
	@Autowired
	protected AdminManager adminManager;
	
	@RequestMapping(value = SMSURIConstants.GET_SCORECARDWITH_CLASSID, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO scoreCardDetailWithStudentAndStudyClassIdWise(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId,@PathVariable("studentId") long studentId,@PathVariable("studyClassId") long studyClassId,@PathVariable("examTypeId") long examTypeId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if (custId > 0  && academicYearId > 0 && studyClassId > 0 && examTypeId >0) {
			StudentScoreCardVO studentScoreCardVOs = adminManager.scoreCardDetailWithStudentAndStudyClassIdWise(custId,academicYearId,studentId,studyClassId,examTypeId);
			if(!ObjectFunctions.isNullOrEmpty(studentScoreCardVOs))
				return studentScoreCardVOs;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}
		else
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
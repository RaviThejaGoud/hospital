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
import com.hyniva.sms.ws.vo.StudentMarksMainVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.student.StudentManager;

@RestController
public class StudentsMarksController<T extends UserTokenVO> extends SMSBaseController<T> {

	@Autowired
	protected StudentManager studentManager;
	
	/*http://localhost:8080/sms-web/api/marks/46/6*/
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_MARKS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentMarks(@PathVariable("academicYearId") long academicYearId,@PathVariable("studyClassId") long studyClassId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId > 0 && studyClassId > 0){
			StudentMarksMainVO studentMarksMainVO = studentManager.getStudentMarks(academicYearId,studyClassId);
			if(!ObjectFunctions.isNullOrEmpty(studentMarksMainVO))
				return studentMarksMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_MARKS_BY_ACCOUNTID_TYPE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentMarks(@PathVariable("accountId") long accountId, @PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId > 0 && type != null){
			StudentMarksMainVO studentMarksMainVO = studentManager.getStudentMarksByAccountIdAndType(accountId, type);
			if(!ObjectFunctions.isNullOrEmpty(studentMarksMainVO)) 
				return studentMarksMainVO;
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

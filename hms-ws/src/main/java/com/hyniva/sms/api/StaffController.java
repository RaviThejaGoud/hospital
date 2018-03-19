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
import com.hyniva.sms.ws.vo.ClassTeacherMainVO;
import com.hyniva.sms.ws.vo.PayslipBaseVO;
import com.hyniva.sms.ws.vo.RunCCReportsVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.ViewAllUsersMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPersonAccountDetailsMainVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.admin.AdminManager;

@RestController
public class StaffController <T extends UserTokenVO> extends SMSBaseController<T> {
	
	@Autowired
	protected AdminManager adminManager;
	
	@RequestMapping(value = SMSURIConstants.GET_STAFF_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO  getStaffDetails(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId > 0 && academicYearId>0){
			ViewStaffPersonAccountDetailsMainVO  staffPersonAccountDetailsMainVO= adminManager.getStaffDetails(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(staffPersonAccountDetailsMainVO))
				return staffPersonAccountDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_TEACHING_STAFF_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getTeachingStaffDetails(@PathVariable("accountId") long accountId,@PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId > 0 && !StringFunctions.isNullOrEmpty(type)){
			ViewStaffPersonAccountDetailsMainVO  staffPersonAccountDetailsMainVO= adminManager.getTeachingStaffDetails(accountId, type);
			if(!ObjectFunctions.isNullOrEmpty(staffPersonAccountDetailsMainVO))
				return staffPersonAccountDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_DESKTOP_USERS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO  getDesktopUsers(@PathVariable("custId") long custId,@PathVariable("webAccountId") long webAccountId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId > 0){
			ViewAllUsersMainVO  viewAllUsersMainVO= adminManager.getUserDetails(custId,webAccountId);
			if(!ObjectFunctions.isNullOrEmpty(viewAllUsersMainVO))
				return viewAllUsersMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());

		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.RUN_TIME_TABLE_REPORTS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO runTimeTableReports(@RequestBody RunCCReportsVO runCCReportsVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean runTimeTableReportsStatus  = adminManager.runTimeTableReports(runCCReportsVO);
		if(runTimeTableReportsStatus)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_RUN_PAYSLIPS_REPORTS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO runPaySlipsReports(@RequestBody PayslipBaseVO payslipBaseVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean runTimeTableReportsStatus  = adminManager.runPaySlipsReports(payslipBaseVO);
		if(runTimeTableReportsStatus)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_STAFF_DETAILS_DESKTOP, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO  getStaffDetailsForDesktop(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId > 0 && academicYearId>0){
			ViewStaffPersonAccountDetailsMainVO  staffPersonAccountDetailsMainVO= adminManager.getStaffDetailsForDesktop(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(staffPersonAccountDetailsMainVO))
				return staffPersonAccountDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_STAFF_CLASS_TEACHER_DESKTOP, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO  getStaffClassTeacherDetailsForDesktop(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId > 0 && academicYearId>0){
			ClassTeacherMainVO  classTeacherMainVO= adminManager.getStaffClassTeacherDetailsForDesktop(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(classTeacherMainVO))
				return classTeacherMainVO;
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
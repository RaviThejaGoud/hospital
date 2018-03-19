package com.hyniva.sms.api;

import java.text.ParseException;

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
import com.hyniva.sms.ws.vo.LeavesMainVO;
import com.hyniva.sms.ws.vo.LeavesVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.staff.StaffManager;
import com.urt.service.manager.interfaces.student.StudentManager;

	@RestController
	public class LeaveController<T extends UserTokenVO> extends SMSBaseController<T> {

		@Autowired
		protected AdminManager adminManager;
		
		@Autowired
		protected StudentManager studentManager;
		
		@Autowired
		protected StaffManager staffManager;
		
		/*http://localhost:8080/sms-web/api/leaves/2/120*/
		@RequestMapping(value = SMSURIConstants.GET_LEAVES, method = RequestMethod.GET, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO getSchoolHolidays(@PathVariable("type") String type, @PathVariable("accountId") long accountId)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(accountId>0){
				 LeavesMainVO leavesMainVO = adminManager.getLeaves(type,accountId);
				if(!ObjectFunctions.isNullOrEmpty(leavesMainVO))
					return leavesMainVO;
				else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc()); 
			}else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			}
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		}

		/*http://localhost:8080/sms-web/api/leaves/submit*/
		/*@RequestMapping(value = SMSURIConstants.UPDATE_LEAVES, method = RequestMethod.POST, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO upadateLeaves(@RequestBody LeavesMainVO leavesMainVO)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(!ObjectFunctions.isNullOrEmpty(leavesMainVO)){
				int responseCode = staffManager.submitLeaves(leavesMainVO);
				if(responseCode == 0)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				else if(responseCode == 1)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.LEAVE_STATUS.getErrorCode(),ERROR_CODE_ENUM.LEAVE_STATUS.getErrorDesc());
				else if(responseCode == 2)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
				else if(responseCode == 3)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.LEAVE_STATUS_HOLIDAY.getErrorCode(),ERROR_CODE_ENUM.LEAVE_STATUS_HOLIDAY.getErrorDesc());
				else if(responseCode == 4)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorCode(),ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorDesc());
				else if(responseCode == 5)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.USER_ERROR.getErrorCode(),ERROR_CODE_ENUM.USER_ERROR.getErrorDesc());
				else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		}*/
		
		/*@RequestMapping(value = SMSURIConstants.APPROVE_REJECT_LEAVES, method = RequestMethod.POST, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO approveOrRejectLeaves(@RequestBody LeavesVO leavesVO)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(!ObjectFunctions.isNullOrEmpty(leavesVO.getLeaveStatus()) && leavesVO.getId() > 0){
				int responseCode = staffManager.approveOrRejectLeaves(leavesVO);
				if(responseCode == 0)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				else if(responseCode == 1)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.LEAVE_NOT_FOUND.getErrorCode(),ERROR_CODE_ENUM.LEAVE_NOT_FOUND.getErrorDesc());
				else if(responseCode == 2)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
			}else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		}*/
		
		@RequestMapping(value = SMSURIConstants.DELETE_LEAVE_INFO, method = RequestMethod.DELETE, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO removeLeaveByLeaveId(@PathVariable("leaveId") long leaveId)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(leaveId>0){
				int responseCode = staffManager.deleteLeaveInfo(leaveId);
				if(responseCode == 0){
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DELETE_ERROR.getErrorCode(),ERROR_CODE_ENUM.DELETE_ERROR.getErrorDesc()); 
				}else
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
		
		@RequestMapping(value = SMSURIConstants.UPDATE_LEAVES, method = RequestMethod.POST, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO upadateLeaves(@RequestBody LeavesVO leavesVO)throws SMSAPIException, ParseException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(!ObjectFunctions.isNullOrEmpty(leavesVO)){
				int responseCode = staffManager.leaveVoFromApp(leavesVO);
				if(responseCode == 7)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_LEAVE.getErrorCode(),ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_LEAVE.getErrorDesc());
				else if(responseCode == 0)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				else if(responseCode == 1)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.LEAVE_STATUS.getErrorCode(),ERROR_CODE_ENUM.LEAVE_STATUS.getErrorDesc());
				else if(responseCode == 2)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
				else if(responseCode == 3)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.LEAVE_STATUS_HOLIDAY.getErrorCode(),ERROR_CODE_ENUM.LEAVE_STATUS_HOLIDAY.getErrorDesc());
				else if(responseCode == 4)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorCode(),ERROR_CODE_ENUM.ACADEMICYEAR_ERROR.getErrorDesc());
				else if(responseCode == 5)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.USER_ERROR.getErrorCode(),ERROR_CODE_ENUM.USER_ERROR.getErrorDesc());
				else if(responseCode == 6)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		}
		@RequestMapping(value = SMSURIConstants.APPROVE_REJECT_LEAVES, method = RequestMethod.POST, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO approveOrRejectLeaves(@RequestBody LeavesVO leavesVO)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(!ObjectFunctions.isNullOrEmpty(leavesVO.getLeaveStatus()) && leavesVO.getId() > 0 && StringFunctions.isNotNullOrEmpty(leavesVO.getIdentifier().getChannel()) && StringFunctions.isNotNullOrEmpty(leavesVO.getApprovalsComment()) && leavesVO.getSupervisorId() >0){
				String forStaffOrStudent=leavesVO.getIdentifier().getChannel();
				User userObj = (User) staffManager.get(User.class, leavesVO.getSupervisorId());
				if(!ObjectFunctions.isNullOrEmpty(userObj)){
					int responseCode =staffManager.approveOrRejectLeaves(userObj,leavesVO.getLeaveStatus(),leavesVO.getApprovalsComment(),leavesVO.getId(),"APP",forStaffOrStudent);
					if(responseCode == 4)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_SENDSMS.getErrorCode(),ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_SENDSMS.getErrorDesc());
					else if(responseCode == 0 || responseCode == 1)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
					else if(responseCode == 2)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.LEAVE_NOT_FOUND.getErrorCode(),ERROR_CODE_ENUM.LEAVE_NOT_FOUND.getErrorDesc());
					else if(responseCode == 3)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());
				}else{
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
				}
			}else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		}
}

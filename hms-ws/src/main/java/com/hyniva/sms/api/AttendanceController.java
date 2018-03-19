package com.hyniva.sms.api;

import java.util.Map;

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
import com.hyniva.sms.ws.vo.StudentMonthlyAttendanceMainVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.attendance.ClassAttendanceMainVO;
import com.hyniva.sms.ws.vo.attendance.PreSchoolStudentAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.StaffDailyAttendanceMainVO;
import com.hyniva.sms.ws.vo.attendance.StudentAndStaffDailyAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.StudentDailyAttendanceMainVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.staff.StaffManager;
import com.urt.service.manager.interfaces.student.StudentManager;

@RestController
public class AttendanceController <T extends UserTokenVO> extends SMSBaseController<T> {
	
	@Autowired
	protected AdminManager adminManager;
	@Autowired
	protected StudentManager studentManager;
	@Autowired
	protected StaffManager staffManager;
	/**
	 * @author Ganesh
	 *
	 * @param <long>
	 * 
	 * @return <customerVo>
	 */
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_MONTH_ATTENDANCE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentMonthlyAttendance(@PathVariable("academicYearId") long academicYearId,@PathVariable("accountId") long accountId,@PathVariable("type") String type,@PathVariable("standardType") String standardType  )throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId > 0 && accountId >0 && StringFunctions.isNotNullOrEmpty(type)&& StringFunctions.isNotNullOrEmpty(standardType)){
			StudentMonthlyAttendanceMainVO monthlyAttendanceMainVO = adminManager.getMonthlyAttendance(academicYearId,accountId,type,standardType);
			if(!ObjectFunctions.isNullOrEmpty(monthlyAttendanceMainVO))
				return monthlyAttendanceMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_DAILY_ATTENDANCE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentDailyAttendance(@PathVariable("academicYearId") long academicYearId,@PathVariable("studyClassId") long studyClassId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId > 0 && studyClassId >0){
			StudentDailyAttendanceMainVO dailyAttendanceMainVO= adminManager.getDailyAttendanceByStudyClassId(academicYearId,studyClassId);
			if(!ObjectFunctions.isNullOrEmpty(dailyAttendanceMainVO))
				return dailyAttendanceMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.STUDENT_DAILY_ATTENDANCE_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO studentAttendanceSubmit(@RequestBody ClassAttendanceMainVO classAttendanceMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		//classAttendanceMainVO.setSMS(classAttendanceMainVO.getIdentifier().getChannel());
		int responseCode = studentManager.addOrUpdateStudentAttendance(classAttendanceMainVO);
		if(responseCode == 4)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_ATTENDANCE.getErrorCode(),ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_ATTENDANCE.getErrorDesc());
		else if(responseCode == 0)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else if(responseCode == 1)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SYSTEM_ERROR.getErrorCode(),ERROR_CODE_ENUM.SYSTEM_ERROR.getErrorDesc());
		else if(responseCode == 2)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.HOLIDAY_ERROR.getErrorCode(),ERROR_CODE_ENUM.HOLIDAY_ERROR.getErrorDesc());
		else if(responseCode == 3)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ATTENDANCE_TYPE_ERROR.getErrorCode(),ERROR_CODE_ENUM.ATTENDANCE_TYPE_ERROR.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());

		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@Override
	protected Validator setValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = SMSURIConstants.GET_STUDENT_ATTENDANCE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentAttendance(@PathVariable("parentAccountId") long parentAccountId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(parentAccountId >0){
			StudentDailyAttendanceMainVO dailyAttendanceMainVO= adminManager.getAttendanceByParentAccountId(parentAccountId);
			if(!ObjectFunctions.isNullOrEmpty(dailyAttendanceMainVO))
				return dailyAttendanceMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_STAFF_DAILY_ATTENDANCE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentAndStaffDailyAttendance(@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId > 0){
			StudentAndStaffDailyAttendanceVO studentAndStaffDailyAttendanceVO= adminManager.getStudentAndStaffDailyAttendance(academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(studentAndStaffDailyAttendanceVO))
				return studentAndStaffDailyAttendanceVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.STAFF_DAILY_ATTENDANCE_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO staffAttendanceSubmit(@RequestBody StaffDailyAttendanceMainVO staffDailyAttendanceMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		int responseCode = staffManager.addOrUpdateStaffAttendanceFromApp(staffDailyAttendanceMainVO);
		if(responseCode == 4)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_ATTENDANCE.getErrorCode(),ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_ATTENDANCE.getErrorDesc());
		else if(responseCode == 0)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else if(responseCode == 1)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SYSTEM_ERROR.getErrorCode(),ERROR_CODE_ENUM.SYSTEM_ERROR.getErrorDesc());
		else if(responseCode == 2)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.HOLIDAY_ERROR.getErrorCode(),ERROR_CODE_ENUM.HOLIDAY_ERROR.getErrorDesc());
		else if(responseCode == 3)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ATTENDANCE_TYPE_ERROR.getErrorCode(),ERROR_CODE_ENUM.ATTENDANCE_TYPE_ERROR.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	/**
	 * This Method used to  submit pre school student attendance. 
	 * @param preSchoolStudentAttendanceVO
	 * @return
	 * @throws SMSAPIException
	 */
	@RequestMapping(value = SMSURIConstants.PRE_SCHOOL_STUDENT_DAILY_ATTENDANCE_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO preSchoolStudentAttendanceSubmit(@RequestBody PreSchoolStudentAttendanceVO preSchoolStudentAttendanceVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		
		Map<String,String> result = studentManager.addOrUpdateStudentAttendance(preSchoolStudentAttendanceVO); 
		smsBaseVO.setIdentifier(preSchoolStudentAttendanceVO.getIdentifier());
		smsBaseVO.getIdentifier().setStatus(result.get("0"));
		return smsBaseVO;
	}
	
	
	/**
	 * 
	 * @param academicYearId
	 * @param studyClassId
	 * @return
	 * @throws SMSAPIException
	 */
	@RequestMapping(value = SMSURIConstants.GET_PRE_SCHOOLSTUDENT_DAILY_ATTENDANCE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getPreschoolStudentDailyAttendance(@PathVariable("academicYearId") long academicYearId,@PathVariable("studyClassId") long studyClassId)throws SMSAPIException {
		if(academicYearId > 0 && studyClassId >0){

			return studentManager.getPreschoolStudentDailyAttendance(academicYearId,studyClassId);
		}
		return null;
		
	}
	
	
}

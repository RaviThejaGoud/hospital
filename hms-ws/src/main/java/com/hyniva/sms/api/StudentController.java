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
import com.hyniva.sms.ws.vo.ClassAssignmentMainVO;
import com.hyniva.sms.ws.vo.OnlineApplicationDetailsMainVO;
import com.hyniva.sms.ws.vo.StudentDetailsListVO;
import com.hyniva.sms.ws.vo.StudentLibraryBooksVO;
import com.hyniva.sms.ws.vo.StudentMarksMainVO;
import com.hyniva.sms.ws.vo.StudentTimetableVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.ViewClassAssignmentDetailsMainVO;
import com.hyniva.sms.ws.vo.ViewRouteBoardingPointsMainVO;
import com.hyniva.sms.ws.vo.ViewRouteVehiclesMainVO;
import com.hyniva.sms.ws.vo.ViewRoutesMainVO;
import com.hyniva.sms.ws.vo.ViewStudentPersonAccountDetailsMainVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.student.StudentManager;

@RestController
public class StudentController<T extends UserTokenVO> extends SMSBaseController<T> {
	@Autowired
	protected StudentManager studentManager;
	@Autowired
	protected AdminManager adminManager;
	
	/*http: http://dev.eazyschool.in/api/studentsDetails/58*/
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_LIST_BY_ACADEMICYEARID, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentsByAcademicYearId(@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId>0){
			ViewStudentPersonAccountDetailsMainVO viewStudentPersonAccountDetailsMainVO = studentManager.getStudentDetails(academicYearId,0,0,null);
			if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetailsMainVO))
				return viewStudentPersonAccountDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	/*http://dev.eazyschool.in/api/api/studentsDetails/55/1043*/
	/*@RequestMapping(value = SMSURIConstants.GET_STUDENT_LIST_BY_STUDYCLASSId, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentsByStudyClassId(@PathVariable("academicYearId") long academicYearId, @PathVariable("studyClassId") long studyClassId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(studyClassId>0){
			StudentDetailsListVO viewStudentPersonAccountDetailsMainVO = studentManager.getStudentDetailsVO(academicYearId,studyClassId,0,null);
			if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetailsMainVO))
				return viewStudentPersonAccountDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}*/
	
	/*http://localhost:8080/sms-web/api/studentsDetails/58/5785/P*/
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_LIST_BY_ACCOUNTID_TYPE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentsByAccountIdAndType(@PathVariable("accountId") long accountId, @PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId>0){
			StudentDetailsListVO viewStudentPersonAccountDetailsMainVO = studentManager.getStudentDetailsVO(accountId,type);
			if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetailsMainVO))
				return viewStudentPersonAccountDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}

	/*http://localhost:8080/sms-web/api/assignment/58*/
	@RequestMapping(value = SMSURIConstants.GET_ASSIGNMENT_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO assignment(@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId>0){
			ViewClassAssignmentDetailsMainVO viewClassAssignmentDetailsMainVO = adminManager.getAssignmentDetails(academicYearId, false);
			if(!ObjectFunctions.isNullOrEmpty(viewClassAssignmentDetailsMainVO))
				return viewClassAssignmentDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	//*http://localhost:8080/sms-web/api/class/assignment/58*/
	@RequestMapping(value = SMSURIConstants.GET_CLASS_ASSIGNMENT_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO classAssignment(@PathVariable("studyClassId") long studyClassId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(studyClassId>0){
			ViewClassAssignmentDetailsMainVO viewClassAssignmentDetailsMainVO = adminManager.getAssignmentDetails(studyClassId, true);
			if(!ObjectFunctions.isNullOrEmpty(viewClassAssignmentDetailsMainVO))
				return viewClassAssignmentDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	/*http://localhost:8080/sms-web/api/studentMarks*/
	@RequestMapping(value = SMSURIConstants.UPDATE_STUDENT_MARKS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentMarksDetails(@RequestBody StudentMarksMainVO studentMarks)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
			boolean marksStatus = adminManager.submitStudentMarks(studentMarks);
			if (marksStatus)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
			else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
			}
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	/*http://localhost:8080/sms-web/api/assignments/submit*/
	@RequestMapping(value = SMSURIConstants.UPDATE_CLASS_ASSIGNMENT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO upadateAssignments(@RequestBody ClassAssignmentMainVO classAssignmentMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(!ObjectFunctions.isNullOrEmpty(classAssignmentMainVO)){
			boolean ClassAssignment = studentManager.updateClassAssignment(classAssignmentMainVO);
			if(ClassAssignment)
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
			else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
			}
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	/*http://localhost:8080/sms-web/api/addAdmissions/submit*/
	@RequestMapping(value = SMSURIConstants.CREATE_ADMISSIONS_FOR_STUDENTS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO createAdmissionsAppliedStudents(@RequestBody OnlineApplicationDetailsMainVO onlineApplicationDetailsMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetailsMainVO)){
			OnlineApplicationDetailsMainVO onlineApplicationDetailsMain = studentManager.createAdmissionsAppliedStudents(onlineApplicationDetailsMainVO);
			if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetailsMain)){
				//apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				return onlineApplicationDetailsMain;
			}else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
			}
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	/*http://localhost:8080/sms-web/api/routes/{custId}/{academicYearId}*/
	@RequestMapping(value = SMSURIConstants.GET_ROUTES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getRoutesList(@PathVariable("custId") long custId, @PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId>0 && custId > 0){
			ViewRoutesMainVO viewRoutesMainVO = studentManager.getRoutesListByAcademicYearIdAndCustId(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(viewRoutesMainVO))
				return viewRoutesMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	/*http://localhost:8080/sms-web/api/routeBoardingPoints/{routeId}*/
	@RequestMapping(value = SMSURIConstants.GET_ROUTE_BOARDING_POINTS_BY_ROUTEID, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getRouteBoardingPointsList(@PathVariable("routeId") long routeId) throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(routeId > 0 ){
			ViewRouteBoardingPointsMainVO viewRouteBoardingPointsMainVO = studentManager.getRouteBoardingPointsListByRouteId(routeId);
			if(!ObjectFunctions.isNullOrEmpty(viewRouteBoardingPointsMainVO))
				return viewRouteBoardingPointsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	/*http://localhost:8080/sms-web/api/vehicles/{routeBoardingPointId}*/
	@RequestMapping(value = SMSURIConstants.GET_VEHICLES_BY_ROUTE_BOARDING_POINTID, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getvehicles(@PathVariable("routeBoardingPointId") long routeBoardingPointId) throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(routeBoardingPointId > 0 ){
			ViewRouteVehiclesMainVO viewRouteVehiclesMainVO = adminManager.getRouteVehiclesByRouteBoardingPointId(routeBoardingPointId);
			if(!ObjectFunctions.isNullOrEmpty(viewRouteVehiclesMainVO))
				return viewRouteVehiclesMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_STUDNET_TIMETABLE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentTimetable(@PathVariable("accountId") long accountId, @PathVariable("type") String type) throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId > 0 ){
			StudentTimetableVO studentTimetableVO = adminManager.getStudentTimetableDetails(accountId, type);
			if(!ObjectFunctions.isNullOrEmpty(studentTimetableVO))
				return studentTimetableVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_STUDNET_LIBRARY_BOOKS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentLibraryBooks(@PathVariable("accountId") long accountId, @PathVariable("type") String type) throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId > 0 ){
			StudentLibraryBooksVO studentLibraryBooksVO = adminManager.getStudentLibraryBooksDetails(accountId, type);
			if(!ObjectFunctions.isNullOrEmpty(studentLibraryBooksVO))
				return studentLibraryBooksVO;
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
		return null;
	}
	/**
	 * 
	 * @param studyClassId
	 * @return
	 * @throws SMSAPIException
	 */
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_ASSIGNMENT_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO studentAssignmentDetails(@PathVariable("studentId") long studentId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(studentId>0){
			ViewClassAssignmentDetailsMainVO viewClassAssignmentDetailsMainVO = adminManager.studentAssignmentDetails(studentId);
			if(!ObjectFunctions.isNullOrEmpty(viewClassAssignmentDetailsMainVO))
				return viewClassAssignmentDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
} 
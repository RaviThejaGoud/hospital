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
import com.hyniva.sms.ws.vo.StaffPermissionRequestsMainVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.ViewPermissionSettingsMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPermissionRequestsMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPermissionsSettingsMainVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.student.StudentManager;

	@RestController
	public class PermissionsController<T extends UserTokenVO> extends SMSBaseController<T> {
		
		@Autowired
		protected StudentManager studentManager;
		 
		/*http://localhost:8080/sms-web/api/studentPermissions/submit*/
		@RequestMapping(value = SMSURIConstants.GET_STUDENT_PERMISSIONS, method = RequestMethod.POST, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO getStudentPermissionsDetails(@RequestBody ViewPermissionSettingsMainVO viewPermissionSettingsMainVO)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(!ObjectFunctions.isNullOrEmpty(viewPermissionSettingsMainVO)){
				int responseCode = studentManager.saveStudentPermissions(viewPermissionSettingsMainVO);
				if(responseCode == 0)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				else if(responseCode == 1)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.STUDENT_PERMISSION_CORD.getErrorCode(),ERROR_CODE_ENUM.STUDENT_PERMISSION_CORD.getErrorDesc());
				else if(responseCode == 2)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
				else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		}
		
		/*http://localhost:8080/sms-web/api/staffRequests/submit*/
		@RequestMapping(value = SMSURIConstants.GET_STAFF_PERMISSION_REQUESTS, method = RequestMethod.POST, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO saveStaffPermissionsRequest(@RequestBody StaffPermissionRequestsMainVO staffPermissionRequestsMainVO)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(!ObjectFunctions.isNullOrEmpty(staffPermissionRequestsMainVO)){
				int responseCode = studentManager.saveStaffPermissionsRequests(staffPermissionRequestsMainVO);
				if(responseCode == 0)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				else if(responseCode == 2)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.STUDENT_PERMISSION_CORD.getErrorCode(),ERROR_CODE_ENUM.STUDENT_PERMISSION_CORD.getErrorDesc());
				else if(responseCode == 1)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
			}else{
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
			}
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		}
	 
		/*http://localhost:8080/sms-web/api/permissionsDetails/46/55/5787/S ---- http://localhost:8080/sms-web/api/permissionsDetails/46/55/14401/P*/
		@RequestMapping(value = SMSURIConstants.GET_STUDENT_OR_STAFF_PERMISSIONS_DETAILS, method = RequestMethod.GET, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO getStudentPermissionsByCustId(@PathVariable("custId") long custId, @PathVariable("academicYearId") long academicYearId, @PathVariable("accountId") long accountId, @PathVariable("type") String type)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(academicYearId > 0 && accountId > 0 && !StringFunctions.isNullOrEmpty(type)){
				if("S".equalsIgnoreCase(type)){
					ViewStaffPermissionRequestsMainVO viewStaffPermissionRequestsMainVO = studentManager.getStaffAllPermissionsByAccountId(custId,academicYearId,accountId);
					if (!ObjectFunctions.isNullOrEmpty(viewStaffPermissionRequestsMainVO))
						return viewStaffPermissionRequestsMainVO;
					else
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
				}else{
					ViewPermissionSettingsMainVO viewPermissionSettingsMainVO = studentManager.getAllPermissionsByAccountId(custId,academicYearId,accountId);
					if (!ObjectFunctions.isNullOrEmpty(viewPermissionSettingsMainVO))
						return viewPermissionSettingsMainVO;
					else
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
				}
				
			}else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
			return smsBaseVO;
		}
		

		/*http://dev.eazyschool.in//api/staffPermissionsSettings/{custId}/{academicYearId}*/ 
		@RequestMapping(value = SMSURIConstants.GET_STAFF_PERMISSIONS_SETTINGS, method = RequestMethod.GET, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public SMSBaseVO getStaffPermissionsByCustId(@PathVariable("custId") long custId, @PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
			SMSBaseVO smsBaseVO = new SMSBaseVO();
			APIStatusVO apistatusVO = null;
			if(academicYearId > 0 && custId > 0){
				ViewStaffPermissionsSettingsMainVO viewStaffPermissionsSettingsMainVO = studentManager.getStaffPermissionSettings(custId,academicYearId);
				if (!ObjectFunctions.isNullOrEmpty(viewStaffPermissionsSettingsMainVO))
					return viewStaffPermissionsSettingsMainVO;
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

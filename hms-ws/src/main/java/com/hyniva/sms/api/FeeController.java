package com.hyniva.sms.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.api.base.SMSBaseController;
import com.hyniva.sms.api.constants.SMSURIConstants;
import com.hyniva.sms.api.exception.SMSAPIException;
import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.FeeMainVO;
import com.hyniva.sms.ws.vo.FeeTypeMainVO;
import com.hyniva.sms.ws.vo.SchoolCategoryMainVO;
import com.hyniva.sms.ws.vo.SchoolTermsMainVO;
import com.hyniva.sms.ws.vo.StudentFeeConcessionMainVO;
import com.hyniva.sms.ws.vo.StudentFeeVO;
import com.hyniva.sms.ws.vo.StudentPaymentMainVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.ViewStudentFeePaymentDetailsMainVO;
import com.hyniva.sms.ws.vo.account.FinCategoryMainVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.hyniva.sms.ws.vo.fee.StudentPaymentListVO;
import com.hyniva.sms.ws.vo.transport.StudentTransportDetailsMainVO;
import com.hyniva.sms.ws.vo.transport.StudentTransportDetailsVo;
import com.urt.service.manager.interfaces.account.AccountManager;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.transport.TransportManager;

@RestController
public class FeeController<T extends UserTokenVO> extends SMSBaseController<T> {
	
	@Autowired
	protected AdminManager adminManager;
	
	@Autowired
	protected AccountManager accountManager;
	
	@Autowired
	protected TransportManager transportManager;
	
	/*http: http://dev.eazyschool.in/api/fee/55*/
	@RequestMapping(value = SMSURIConstants.GET_FEE, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getFee(@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(academicYearId>0){
			ViewStudentFeePaymentDetailsMainVO studentFeePaymentDetailsMainVO = adminManager.getFeeDetails(academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(studentFeePaymentDetailsMainVO))
				return studentFeePaymentDetailsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_FEE_PARTICULARS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO feeParticulars(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId >0 && academicYearId>0){
			FeeTypeMainVO feeTypeMainVO = adminManager.getFeeParticulars(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(feeTypeMainVO))
				return feeTypeMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_CATEGORIES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO studentCategoryList(@PathVariable("custId") long custId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId>0){
			SchoolCategoryMainVO categoryMainVO = adminManager.studentCategoryList(custId);
			if(!ObjectFunctions.isNullOrEmpty(categoryMainVO))
				return categoryMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_FEE_TERMS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO schoolFeeTermsList(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId>0 && academicYearId>0){
			SchoolTermsMainVO schoolTermsMainVO = adminManager.schoolFeeTermsList(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(schoolTermsMainVO))
				return schoolTermsMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_CLASS_FEES, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO classFeeList(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId>0 && academicYearId>0){
			FeeMainVO feeMainVO = adminManager.classFeeList(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(feeMainVO))
				return feeMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_WEB_TO_DESKTOP_PAYMENT_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO createStudentPaymentSyncFromWebToDesktop(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId>0 && academicYearId>0){
			StudentPaymentMainVO studentPaymentMainVO = adminManager.createStudentPaymentSyncFromWebToDesktop(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(studentPaymentMainVO))
				return studentPaymentMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.UPDATE_PAYMENTS_FROM_DESKTOP_WEB, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO updateStaffAttenByBioMetric(@RequestBody StudentPaymentMainVO studentPaymentMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		boolean responseCode = adminManager.createStudentPaymentSyncFromDesktopToWeb(studentPaymentMainVO);
		if(responseCode)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.Exception.getErrorCode(),ERROR_CODE_ENUM.Exception.getErrorDesc());

		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	/*http: http://dev.eazyschool.in/api/fee/109/157 */
	@RequestMapping(value = SMSURIConstants.GET_STUDNET_FEE_CONCESSION, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentFeeConcessionDetails(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId>0 && academicYearId>0){
			StudentFeeConcessionMainVO studentFeeConcessionMainVO = adminManager.getStudentFeeConcessionDetails(custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(studentFeeConcessionMainVO))
				return studentFeeConcessionMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	@RequestMapping(value = SMSURIConstants.GET_STUDNET_FEE_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentFeeDetails(@PathVariable("parentAccountId") long parentAccountId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(parentAccountId>0){
			StudentFeeVO studentFeeVO = adminManager.getStudentFeeDetails(parentAccountId);
			if(!ObjectFunctions.isNullOrEmpty(studentFeeVO))
				return studentFeeVO;
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
	/**
	 * This Method is used to get the all student payment details based on the paymentId send by the desktop application.
	 * @param custId
	 * @param academicYearId
	 * @param paymentId
	 * @return
	 * @throws SMSAPIException
	 */
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_PAYMENT_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentPaymentDetails(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId,@PathVariable("paymentId") long paymentId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId>0 && academicYearId>0){
			StudentPaymentListVO studentPaymentListVO = adminManager.getStudentPaymentDetails(custId,academicYearId,paymentId);
			if(!ObjectFunctions.isNullOrEmpty(studentPaymentListVO))
				return studentPaymentListVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_STUDENT_TRANSPORT_ASSIGN_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getStudentTransportAssigntDetails(@PathVariable("custId") long custId,@PathVariable("academicYearId") long academicYearId,@PathVariable("webId") long webId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		Date webCreatedDate=null;
		if(webId >0)
			webCreatedDate= accountManager.getLastCreatedDate(webId,"studentTransportDetails");
		if(custId > 0){
			StudentTransportDetailsMainVO studentTransportDetails = transportManager.getStudentTransportAssigntDetails(custId,academicYearId,ObjectFunctions.isNullOrEmpty(webCreatedDate)? null : DateFunctions.convertDateToString(webCreatedDate));
			if(!ObjectFunctions.isNullOrEmpty(studentTransportDetails))
				return studentTransportDetails;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
}

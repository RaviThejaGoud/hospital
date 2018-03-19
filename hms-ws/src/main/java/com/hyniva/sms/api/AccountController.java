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
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.account.CustomerBankAccountDetailsVO;
import com.hyniva.sms.ws.vo.account.FinAccountMainVO;
import com.hyniva.sms.ws.vo.account.FinAccountWebAndClientIdsMainVO;
import com.hyniva.sms.ws.vo.account.FinCashbookMainVO;
import com.hyniva.sms.ws.vo.account.FinCategoryMainVO;
import com.hyniva.sms.ws.vo.account.FinWebAndClientIdsMainVO;
import com.hyniva.sms.ws.vo.account.FinancialParticaularAssociationMainVO;
import com.hyniva.sms.ws.vo.account.FinancialYearMainVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.service.manager.interfaces.account.AccountManager;

@RestController
public class AccountController<T extends UserTokenVO> extends SMSBaseController<T> {
	
	@Autowired
	protected AccountManager accountManager;

	@RequestMapping(value = SMSURIConstants.GET_FINANCIAL_YEAR_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getFinancialYearDetails(@PathVariable("webId") long webId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		Date webCreatedDate=null;
		if(webId >0)
			webCreatedDate= accountManager.getLastCreatedDate(webId,"financialYear");
		FinancialYearMainVO financialYearMainVO = accountManager.getFinancialYearDetails(ObjectFunctions.isNullOrEmpty(webCreatedDate)? null : DateFunctions.convertDateToString(webCreatedDate));
		if(!ObjectFunctions.isNullOrEmpty(financialYearMainVO))
			return financialYearMainVO;
		else
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_ACCOUNT_CATEGORY_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getAccountCategoryDetails(@PathVariable("custId") long custId,@PathVariable("webId") long webId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		Date webCreatedDate=null;
		if(webId >0)
			webCreatedDate= accountManager.getLastCreatedDate(webId,"finAccountCategory");
		if(custId > 0){
			FinCategoryMainVO finCategoryMainVO = accountManager.getAccountCategoryDetails(custId,ObjectFunctions.isNullOrEmpty(webCreatedDate)? null : DateFunctions.convertDateToString(webCreatedDate));
			if(!ObjectFunctions.isNullOrEmpty(finCategoryMainVO))
				return finCategoryMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_ACCOUNT_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getAccountDetails(@PathVariable("custId") long custId,@PathVariable("webId") long webId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		Date webCreatedDate=null;
		if(webId >0)
			webCreatedDate= accountManager.getLastCreatedDate(webId,"finAccountDetails");
		if(custId > 0){
			FinAccountMainVO finAccountMainVO = accountManager.getAccountDetails(custId,ObjectFunctions.isNullOrEmpty(webCreatedDate)? null : DateFunctions.convertDateToString(webCreatedDate));
			if(!ObjectFunctions.isNullOrEmpty(finAccountMainVO))
				return finAccountMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_CASHBOOK_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getCashbookDetails(@PathVariable("custId") long custId,@PathVariable("financialYearId") long financialYearId,@PathVariable("webId") long webId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		Date webCreatedDate=null;
		if(webId >0)
			webCreatedDate= accountManager.getLastCreatedDate(webId,"finCashBook");
		if(custId > 0 && financialYearId > 0){
			FinCashbookMainVO finCashbookMainVO = accountManager.getCashbookDetails(custId,financialYearId,ObjectFunctions.isNullOrEmpty(webCreatedDate)? null : DateFunctions.convertDateToString(webCreatedDate));
			if(!ObjectFunctions.isNullOrEmpty(finCashbookMainVO))
				return finCashbookMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.GET_PARTICAULAR_ASSOCIATION, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getFinancialParticaularAssociation(@PathVariable("custId") long custId,@PathVariable("webId") long webId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		Date webCreatedDate=null;
		if(webId >0)
			webCreatedDate= accountManager.getLastCreatedDate(webId,"finFeeParticularAssociation");
		if(custId > 0 ){
			FinancialParticaularAssociationMainVO  particaularAssociationMainVO= accountManager.getFinancialParticaularAssociation(custId,ObjectFunctions.isNullOrEmpty(webCreatedDate)? null : DateFunctions.convertDateToString(webCreatedDate));
			if(!ObjectFunctions.isNullOrEmpty(particaularAssociationMainVO))
				return particaularAssociationMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
			smsBaseVO.setApiStatus(apistatusVO);
		}
		return smsBaseVO;
	}
	
	@RequestMapping(value = SMSURIConstants.FINANCIAL_YEAR_DETAILS_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO addOrUpdateFinAccountMainVO(@RequestBody FinancialYearMainVO financialYearMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;

		FinWebAndClientIdsMainVO webAndClientIdsMainVO = accountManager.addOrUpdateFinAccountMainVO(financialYearMainVO);
		
		smsBaseVO.setApiStatus(apistatusVO);
		return webAndClientIdsMainVO;
	}
	
	@RequestMapping(value = SMSURIConstants.ACCOUNT_CATEGORY_DETAILS_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO addOrUpdateAccountCategoryDetails(@PathVariable("custId") long custId,@RequestBody FinCategoryMainVO finCategoryMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;

		FinWebAndClientIdsMainVO webAndClientIdsMainVO = accountManager.addOrUpdateAccountCategoryDetails(finCategoryMainVO,custId);
		
		smsBaseVO.setApiStatus(apistatusVO);
		return webAndClientIdsMainVO;
	}
	
	@RequestMapping(value = SMSURIConstants.ACCOUNT_DETAILS_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO addOrUpdateAccountDetails(@PathVariable("custId") long custId,@PathVariable("financialYearId") long financialYearId,@RequestBody FinAccountMainVO finAccountMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;

		FinAccountWebAndClientIdsMainVO accountWebAndClientIdsMainVO = accountManager.addOrUpdateAccountDetailsClient(finAccountMainVO,custId,financialYearId);
		
		smsBaseVO.setApiStatus(apistatusVO);
		return accountWebAndClientIdsMainVO;
	}
	
	@RequestMapping(value = SMSURIConstants.CASHBOOK_DETAILS_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO addOrUpdateCashbook(@PathVariable("custId") long custId,@PathVariable("financialYearId") long financialYearId,@RequestBody FinCashbookMainVO finCashbookMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;

		FinWebAndClientIdsMainVO webAndClientIdsMainVO = accountManager.addOrUpdateCashbook(finCashbookMainVO,custId,financialYearId);
		
		smsBaseVO.setApiStatus(apistatusVO);
		return webAndClientIdsMainVO;
	}
	
	@RequestMapping(value = SMSURIConstants.PARTICULAR_ASSOCIATION_SUBMIT, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO particaularAssociationSubmit(@PathVariable("custId") long custId,@RequestBody FinancialParticaularAssociationMainVO particaularAssociationMainVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;

		FinWebAndClientIdsMainVO webAndClientIdsMainVO = accountManager.particaularAssociationSubmit(particaularAssociationMainVO,custId);
		
		smsBaseVO.setApiStatus(apistatusVO);
		return webAndClientIdsMainVO;
	}
	
	
	@RequestMapping(value = SMSURIConstants.CUSTOMER_BANK_ACCOUNT_DETAILS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getCustomerBankAccountDetails(@PathVariable("custId") long custId)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(custId > 0 ){
			CustomerBankAccountDetailsVO customerBankAccountDetailsVO=accountManager.getCustomerBankAccountDetailsVo(custId);
			if(!ObjectFunctions.isNullOrEmpty(customerBankAccountDetailsVO))
				return customerBankAccountDetailsVO;
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
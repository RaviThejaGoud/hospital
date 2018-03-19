/********************************************************************
* HYNIVA
* All Rights Reserved 
*
* File: SMSBaseController.java
********************************************************************
*
*  Ver   Date            Author                Description
*  ====  ========        ============          ==================
*  0.1   June 18, 2015    Sreeram		       Created
/********************************************************************/
package com.hyniva.sms.api.base;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.hyniva.sms.ws.vo.base.KeyIdentifierVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public abstract class SMSBaseController<T extends SMSBaseVO> {
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(setValidator());  
	}

	protected abstract Validator setValidator() ;
	
	/**
	 * Validates the key identifier
	 * @param inputData
	 * @param result
	 */
	protected BindingResult validateKeyIdentiferData(KeyIdentifierVO inputData,
			BindingResult result) {

		/*if (com.acg.bank.los.common.utils.Validator.isEmpty(inputData.getUi())) {
			result.rejectValue("ui", "identifier.required.ui");
		}
		if (com.acg.bank.los.common.utils.Validator.isEmpty(inputData.getTranId())) {
			result.rejectValue("tranId", "identifier.required.tranId");
		}
		if (com.acg.bank.los.common.utils.Validator.isEmpty(inputData.getChannel())) {
			result.rejectValue("channel", "identifier.required.channel");
		}
		if((inputData.getUi()).length() >10){
			result.addError(new ObjectError("ui","identifier.ui.length"));
			
		}*/
		return result;
	}
}
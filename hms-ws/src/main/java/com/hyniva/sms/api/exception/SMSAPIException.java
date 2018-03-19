package com.hyniva.sms.api.exception;
/********************************************************************
* HYNIVA
* All Rights Reserved 
*
* File: SMSAPIException.java
********************************************************************
*
*  Ver   Date            Author                Description
*  ====  ========        ============          ==================
*  0.1   June 18, 2015   Sreeram		       Created
/********************************************************************/

import java.util.List;

import org.springframework.validation.BindingResult;

import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.base.ErrorDetailVO;


@SuppressWarnings("serial")
public class SMSAPIException extends Exception {
	
	private ERROR_CODE_ENUM errorCode;
	private Long id;
	private String ui;
	private String status;
	private String channel;
	private BindingResult result;
	private List<ErrorDetailVO> errors;
	private String field;
	private Integer code;
	
	public SMSAPIException(Integer code, String field ,String desc, List<ErrorDetailVO> errors) {
		// TODO Auto-generated constructor stub
		super();
		this.code = code;
		this.field = field;
		this.status = desc;
		this.errors = errors;
	}
	public SMSAPIException(BindingResult result){
		super();

		this.result = result;
		
	}
	
	public SMSAPIException(Exception ex,ERROR_CODE_ENUM errorCode,Long id){
		super(ex);
		this.errorCode = errorCode;
		this.id = id;		
	}
	public SMSAPIException(Exception ex,ERROR_CODE_ENUM errorCode,String ui){
		super(ex);
		this.errorCode = errorCode;
		this.ui = ui;		
	}
	public SMSAPIException(Throwable ex,ERROR_CODE_ENUM errorCode,String ui){
		super(ex);
		this.errorCode = errorCode;
		this.ui = ui;
		
	}
	public SMSAPIException(Exception ex,ERROR_CODE_ENUM errorCode,String ui,String status){
		super(ex);
		this.errorCode = errorCode;
		this.ui = ui;
		this.status=status;		
	}
	public SMSAPIException(Exception ex,ERROR_CODE_ENUM errorCode,String ui,Long appId){
		super(ex);
		this.errorCode = errorCode;
		this.ui = ui;
		this.id=appId;		
	}
	public SMSAPIException(Exception ex,ERROR_CODE_ENUM errorCode,String ui,Long appId,String channel){
		super(ex);
		this.errorCode = errorCode;
		this.ui = ui;
		this.id=appId;
		this.channel = channel;		
	}
	public SMSAPIException(ERROR_CODE_ENUM errorCode,String ui,Long appId,String channel,List<ErrorDetailVO> errors){
		super();
		this.errorCode = errorCode;
		this.ui = ui;
		this.id=appId;
		this.channel = channel;	
		this.errors = errors;
	}
	public SMSAPIException(Throwable ex,ERROR_CODE_ENUM errorCode,Long id){
		super(ex);
		this.errorCode = errorCode;
		this.id = id;		
	}
	public SMSAPIException(BindingResult result,String ui){
		super();
		this.errorCode = errorCode;
		this.ui = ui;
		this.result = result;
		
	}
	public SMSAPIException(ERROR_CODE_ENUM errorCode,String ui,List<ErrorDetailVO> errors){
		super();
		this.errorCode = errorCode;
		this.ui = ui;
		this.errors = errors;
		
	}
	public SMSAPIException(ERROR_CODE_ENUM errorCode,Long id,List<ErrorDetailVO> errors){
		super();
		this.errorCode = errorCode;
		this.id = id;	
		this.errors = errors;
	}
	public SMSAPIException(List<ErrorDetailVO> errors){
		super();
		this.errors = errors;
	}
	public SMSAPIException(Exception ex){
		super(ex);
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the errorCode
	 */
	public ERROR_CODE_ENUM getErrorCode() {
		return errorCode;
	}
	/**
	 * @return the ui
	 */
	public String getUi() {
		return ui;
	}
	/**
	 * @param ui the ui to set
	 */
	public void setUi(String ui) {
		this.ui = ui;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * @return the result
	 */
	public BindingResult getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(BindingResult result) {
		this.result = result;
	}
	/**
	 * @return the errors
	 */
	public List<ErrorDetailVO> getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<ErrorDetailVO> errors) {
		this.errors = errors;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
}

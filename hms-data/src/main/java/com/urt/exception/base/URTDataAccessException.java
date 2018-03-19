package com.urt.exception.base;

import org.springframework.dao.DataAccessException;

public class URTDataAccessException extends DataAccessException {

	public URTDataAccessException(String msg) {
		super(msg);
		throw new URTUniversalException(msg);
	}
	
	public URTDataAccessException(String msg, Throwable t) {
		super(msg, t);
		throw new URTUniversalException(msg,t);
	}
	
}

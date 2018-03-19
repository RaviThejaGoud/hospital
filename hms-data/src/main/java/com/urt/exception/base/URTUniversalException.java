package com.urt.exception.base;

import org.springframework.dao.DataAccessException;

public class URTUniversalException extends DataAccessException {

	public URTUniversalException(String msg) {
		super(msg);
	}
	
	public URTUniversalException(String msg, Throwable t) {
		super(msg, t);
	}
	
}

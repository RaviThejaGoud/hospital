package com.urt.exception.base;


public class DuplicateEntryException extends URTUniversalException {

	public DuplicateEntryException(String msg) {
		super(msg);
	}
	
	public DuplicateEntryException(String msg, Throwable t) {
		super(msg, t);
	}
	
}

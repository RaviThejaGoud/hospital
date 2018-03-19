package com.urt.util.excel.parser;

public class ExcelParsingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6380524862339927352L;

	public ExcelParsingException(String message) {
		super(message);
	}

	public ExcelParsingException(String message, Exception exception) {
		super(message, exception);
	}

}

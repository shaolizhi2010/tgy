package com.tgy.exception;

public class BaseException extends Exception {

	public BaseException(Object source, String msg) {
		super(msg);
	}

	public BaseException(Object source, String msg, Exception e) {
		super(msg, e);
	}

}

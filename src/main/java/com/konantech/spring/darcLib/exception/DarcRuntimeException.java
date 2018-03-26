package com.konantech.spring.darcLib.exception;


public class DarcRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3653229594190051471L;
	
	private int errorCode = -1;
	public DarcRuntimeException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}
	public DarcRuntimeException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	public DarcRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	public DarcRuntimeException(Throwable cause) {
		super(cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

}

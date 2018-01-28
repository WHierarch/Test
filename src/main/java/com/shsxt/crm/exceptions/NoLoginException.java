package com.shsxt.crm.exceptions;

public class NoLoginException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg="用户未登录";
	private Integer errorCode=400;
	
	
	
	
	
	public NoLoginException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	public NoLoginException(String errorMsg, Integer errorCode) {
		super(errorMsg);
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
}

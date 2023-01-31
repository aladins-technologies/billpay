package com.example.billpay.Exception;

public class BillPayException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public BillPayException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}
	
	public BillPayException() {
		super();
	}
}

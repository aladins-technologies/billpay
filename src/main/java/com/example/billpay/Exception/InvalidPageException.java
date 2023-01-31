package com.example.billpay.Exception;

public class InvalidPageException extends BillPayException{

	private static final long serialVersionUID = 1L;

	public InvalidPageException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

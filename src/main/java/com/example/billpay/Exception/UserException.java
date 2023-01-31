package com.example.billpay.Exception;

public class UserException extends BillPayException {

	private static final long serialVersionUID = 1L;

	public UserException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

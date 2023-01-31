package com.example.billpay.Exception;

public class UserNotFoundException extends BillPayException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

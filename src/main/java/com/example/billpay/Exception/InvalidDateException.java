package com.example.billpay.Exception;

public class InvalidDateException extends BillPayException {

	private static final long serialVersionUID = 1L;

	public InvalidDateException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

package com.example.billpay.Exception;

public class UnequeValueException extends BillPayException {

	private static final long serialVersionUID = 1L;

	public UnequeValueException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

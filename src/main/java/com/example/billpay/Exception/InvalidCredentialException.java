package com.example.billpay.Exception;

public class InvalidCredentialException extends BillPayException {

	private static final long serialVersionUID = 1L;

	public InvalidCredentialException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

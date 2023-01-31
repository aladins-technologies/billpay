package com.example.billpay.Exception;

public class InvalidRoleException extends BillPayException {

	private static final long serialVersionUID = 1L;

	public InvalidRoleException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

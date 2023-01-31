package com.example.billpay.Exception;

public class InvalidIdException extends BillPayException {

	private static final long serialVersionUID = 1L;
	
	public InvalidIdException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

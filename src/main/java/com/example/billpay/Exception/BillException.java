package com.example.billpay.Exception;

public class BillException extends BillPayException{

	private static final long serialVersionUID = 1L;

	public BillException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

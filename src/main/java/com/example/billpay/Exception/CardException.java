package com.example.billpay.Exception;

public class CardException extends BillPayException{
	
	private static final long serialVersionUID = 1L;

	public CardException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
}

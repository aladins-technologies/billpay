package com.example.billpay.Dto;

public class PaymentDto {
	
	private int userID;
	
	private int billID;
	
	private int cardID;
	
	private double paymentAmount;
	
	private String cvv;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

}

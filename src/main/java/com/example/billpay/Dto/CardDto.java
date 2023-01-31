package com.example.billpay.Dto;

public class CardDto {
	
	private int cardID;
	
	private String nameOnCard;
	
    private String number;
    
    private String cvv;
    
    private String expDate;
    
    private double balanceAmt;
    
    private boolean isActive;
    
    private int userID;

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public double getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(double balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
    
}

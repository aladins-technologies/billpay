package com.example.billpay.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
public class Card {
	@Id
	@TableGenerator(name="sequence", initialValue=10000, allocationSize=50)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="sequence")
    private int cardID;
	
	private String nameOnCard;

	@Column(unique = true)
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

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(int cardID, String nameOnCard, String number, String cvv, String expDate, double balanceAmt,
			boolean isActive, int userID) {
		super();
		this.cardID = cardID;
		this.nameOnCard = nameOnCard;
		this.number = number;
		this.cvv = cvv;
		this.expDate = expDate;
		this.balanceAmt = balanceAmt;
		this.isActive = isActive;
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Card [cardID=" + cardID + ", nameOnCard=" + nameOnCard + ", number=" + number + ", cvv=" + cvv
				+ ", expDate=" + expDate + ", balanceAmt=" + balanceAmt + ", isActive=" + isActive + ", userID="
				+ userID + "]";
	}
    
}

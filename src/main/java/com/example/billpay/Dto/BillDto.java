package com.example.billpay.Dto;

import java.sql.Timestamp;

public class BillDto {

	private int billID;

    private String number;
	
	private String description;

    private double amount;

    private Timestamp billDate;
    
    private Timestamp dueDate;
    
    private boolean isPaid;
    
    private int userID;

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getBillDate() {
		return billDate;
	}

	public void setBillDate(Timestamp billDate) {
		this.billDate = billDate;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
    
}

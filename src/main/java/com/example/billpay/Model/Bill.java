package com.example.billpay.Model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
public class Bill {
	@Id
	@TableGenerator(name="sequence", initialValue=10000, allocationSize=50)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="sequence")
    private int billID;

	@Column(unique = true)
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
    
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bill(int billID, String number, String description, double amount, Timestamp billDate, Timestamp dueDate,
			boolean isPaid, int userID) {
		super();
		this.billID = billID;
		this.number = number;
		this.description = description;
		this.amount = amount;
		this.billDate = billDate;
		this.dueDate = dueDate;
		this.isPaid = isPaid;
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Bill [billID=" + billID + ", number=" + number + ", description=" + description + ", amount=" + amount
				+ ", billDate=" + billDate + ", dueDate=" + dueDate + ", isPaid=" + isPaid + ", userID=" + userID + "]";
	}
    
}

package com.example.billpay.Dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDto {

	private int billID;

    private String number;
	
	private String description;

    private double amount;

    private Timestamp billDate;
    
    private Timestamp dueDate;
    
    private boolean isPaid;
    
    private int userID;
}

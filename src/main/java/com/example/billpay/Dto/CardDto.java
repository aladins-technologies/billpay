package com.example.billpay.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
	
	private int cardID;
	
	private String nameOnCard;
	
    private String number;
    
    private String cvv;
    
    private String expDate;
    
    private double balanceAmt;
    
    private boolean isActive;
    
    private int userID;
}

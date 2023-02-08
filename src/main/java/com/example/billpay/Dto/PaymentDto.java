package com.example.billpay.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
	
	private int userID;
	
	private int billID;
	
	private int cardID;
	
	private double paymentAmount;
	
	private String cvv;
}

package com.example.billpay.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
}

package com.example.billpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BillpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillpayApplication.class, args);
	}

}

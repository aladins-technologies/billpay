package com.example.billpay.Service;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.billpay.Dto.BillDto;
import com.example.billpay.Dto.PaymentDto;
import com.example.billpay.Exception.BillException;
import com.example.billpay.Exception.BillPayException;
import com.example.billpay.Exception.CardException;
import com.example.billpay.Exception.InvalidDateException;
import com.example.billpay.Exception.InvalidIdException;
import com.example.billpay.Exception.UserException;
import com.example.billpay.Model.Bill;
import com.example.billpay.Model.Card;
import com.example.billpay.Model.User;
import com.example.billpay.Repository.BillRepository;
import com.example.billpay.Repository.CardRepository;
import com.example.billpay.Repository.UserRepository;
import com.example.billpay.Util.StaticMethods;


@Service
public class BillService {

	@Autowired
	private BillRepository billRepo;
	
	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmailSenderService emailSender;
	
	public Bill saveBill(BillDto bill) {
		if(bill.getNumber() == null) {
			throw new BillException("Please provide a valid Bill number");
			
		} else if(bill.getDescription() == null || bill.getDescription().length() < 5) {
			throw new BillException("Please provide a valid Description, Description must contain atleast five chars");
			
		} else if(bill.getAmount() <= 0) {
			throw new BillException("Please enter a valid Amount");
			
		} 
		
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		if(bill.getBillDate() == null) {
			//if null set current date
			bill.setBillDate(currentTime);
			//throw new InvalidDateException("Please provide a valid Bill date");
			
		} else if(bill.getBillDate().after(currentTime)) {
			throw new InvalidDateException("Bill date must be a past date or current date");
		}
		
		if(bill.getDueDate() == null) {
			//if null set current date
			bill.setDueDate(currentTime);
			//throw new InvalidDateException("Please provide a valid Due date");
			
		} else if(bill.getBillDate().after(bill.getDueDate())) {
			throw new InvalidDateException("Due date must be after or equal to Bill date");
		}
		
		if(bill.getUserID() <= 0) {
			throw new UserException("Please select a valid User");
		} else {
			User user = userRepo.findById(bill.getUserID()).orElse(null);
			if(user == null ) {
				throw new UserException("Please select a valid User");
			}
		}
		
		Bill newBill = new Bill();
		newBill = modelMapper.map(bill, Bill.class);
		billRepo.save(newBill);
		if(newBill.getBillID() <= 0) {
			throw new CardException("Unable to save new Bill " + newBill.getNumber());
		}
		
		return newBill;
	}
	
	public Page<Bill> getBill(int pageNumber, Integer billID, Integer userID, Boolean isPaid, Timestamp billDate, Timestamp dueDate, String number){
		pageNumber = pageNumber >= 0 ? pageNumber : 0;										//fallback to first page
		
		if(billID == null) {
			billID = -1;
		}
		int isPaidInt = -1;
		if(isPaid != null) {
			isPaidInt = StaticMethods.intValue(isPaid);
		}
		String billDateString = null;
		if(billDate != null) {
			billDateString = StaticMethods.getDate(billDate);
		}
		String dueDateString = null;
		if(dueDate != null) {
			dueDateString = StaticMethods.getDate(dueDate);
		}
		
		Pageable page = PageRequest.of(pageNumber, 10);
		Page<Bill> pages = billRepo.getBill(billID, userID, isPaidInt, billDateString, dueDateString, number, page);
		return pages;
	}
	
	public String deleteById(int billID) {
		Bill bill = billRepo.findById(billID).orElse(null);
		if(bill == null) {
			throw new InvalidIdException("Requested Bill not found in server");
		}
		billRepo.deleteById(billID);
    	return "Bill " + bill.getNumber() + " is deleted successfully!";
    }
	
	@Transactional
	public String makePayment(PaymentDto paymentDto) {
		Bill bill = billRepo.findById(paymentDto.getBillID()).orElse(null);
		if(bill == null) {
			throw new InvalidIdException("Requested Bill not found in server");
		}
		if(paymentDto.getUserID() != bill.getUserID()) {
			throw new UserException("Invalid User");
		}
		Card card = cardRepo.findById(paymentDto.getCardID()).orElse(null);
		if(card == null) {
			throw new CardException("Selected Card not found in server");
		}
		if(!paymentDto.getCvv().equals(card.getCvv())) {
			throw new CardException("Invalid CVV");
		}
		if(paymentDto.getPaymentAmount() != bill.getAmount()) {
			throw new BillPayException("Please enter correct amount");
		}
		double newBalance = card.getBalanceAmt() - paymentDto.getPaymentAmount();
		if(newBalance < 0) {
			throw new BillPayException("Insufficient balance");
		}
		
		card.setBalanceAmt(newBalance);
		cardRepo.save(card);
		
		bill.setPaid(true);
		billRepo.save(bill);
		
		StringBuilder msg = new StringBuilder("Bill ").append(bill.getNumber()).append(" with amount ").append(bill.getAmount()).append(" is paid successfully");
		User user = userRepo.findById(paymentDto.getUserID()).orElse(null);
		if(user != null){
			emailSender.sendMail(StaticMethods.APP_MAIL_ID, user.getEmail(), "Payment Successfull",
			msg.toString());
		}
		return msg.toString();
	}
}

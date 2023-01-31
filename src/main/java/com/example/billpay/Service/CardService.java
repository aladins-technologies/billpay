package com.example.billpay.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.billpay.Dto.CardDto;
import com.example.billpay.Exception.CardException;
import com.example.billpay.Exception.InvalidDateException;
import com.example.billpay.Exception.InvalidIdException;
import com.example.billpay.Exception.UserException;
import com.example.billpay.Model.Card;
import com.example.billpay.Model.User;
import com.example.billpay.Repository.CardRepository;
import com.example.billpay.Repository.UserRepository;
import com.example.billpay.Util.StaticMethods;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Card saveCard(CardDto card) {		
		if(card.getNumber() == null || card.getNumber().length() < 16) {
			throw new CardException("Please provide a valid Card number");
		} else {
			String number = card.getNumber();
			String regex = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|"
					+ "3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(number);
			if(!matcher.matches()) {
				throw new CardException("Please provide a valid Card number");
			}
		}
		
		if(card.getCvv() == null || card.getCvv().length() < 3){
			throw new CardException("Please provide a valid CVV");
		} else {
			String cvv = card.getCvv();
			String regex = "^[0-9]{3,4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(cvv);
			if(!matcher.matches()) {
				throw new CardException("Please provide a valid CVV");
			}
		}
		
		if(card.getExpDate() == null || card.getExpDate().length() < 4){
			throw new InvalidDateException("Please provide a valid Exp Date");
		} else {
			String expDate = card.getExpDate();
			String regex = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(expDate);
			if(!matcher.matches()) {
				throw new InvalidDateException("Please provide a valid Exp Date");
			}
		}
		
		//Check for User
		if(card.getUserID() <= 0) {
			throw new UserException("Please select a valid User");
		} else {
			User user = userRepo.findById(card.getUserID()).orElse(null);
			if(user == null ) {
				throw new UserException("Please select a valid User");
			}
		}
		
		String nameOnCard = card.getNameOnCard() != null ? card.getNameOnCard() : "";
		Card newCard = new Card(0, nameOnCard, card.getNumber(), card.getCvv(), card.getExpDate(), card.getBalanceAmt(), card.isActive(), card.getUserID());
		cardRepo.save(newCard);
		
		if(newCard.getCardID() <= 0) {
			throw new CardException("Unable to save new Card " + card.getNameOnCard());
		}
		return newCard;
	}
	
	public Page<Card> getCards(int pageNumber, Integer cardID, Integer userID, Boolean isActive, String expDate, String number, String name){
		pageNumber = pageNumber >= 0 ? pageNumber : 0;										//fallback to first page
		
		if(cardID == null) {
			cardID = -1;
		}
		int isActiveInt = -1;
		if(isActive != null) {
			isActiveInt = StaticMethods.intValue(isActive);
		}
		
		Pageable page = PageRequest.of(pageNumber, 10);
		Page<Card> pages = cardRepo.getCards(cardID, userID, isActiveInt, expDate, number, name, page);
		return pages;
	}
	
	/**
	 * userID cannot be updated
	 * @param cardDto
	 * @return Card
	 */
	public Card updateCard(CardDto cardDto) {
		Card card = cardRepo.findById(cardDto.getCardID()).orElse(null);
		if(card == null) {
			throw new InvalidIdException("Requested Card not found in server");
		}
		if(card.getUserID() != cardDto.getUserID()) {
			throw new UserException("You are trying to update card of another User");
		}
		
		boolean updated = false;
		if(card.isActive() != cardDto.isActive()) {
			card.setActive(cardDto.isActive());
			updated = true;
		}
		if(!card.getCvv().equals(cardDto.getCvv())) {
			card.setCvv(cardDto.getCvv());
			updated = true;
		}
		if(!card.getExpDate().equals(cardDto.getExpDate())) {
			card.setExpDate(cardDto.getExpDate());
			updated = true;
		}
		if(card.getBalanceAmt() != cardDto.getBalanceAmt()) {
			card.setBalanceAmt(cardDto.getBalanceAmt());
			updated = true;
		}
		if(!card.getNumber().equals(cardDto.getNumber())) {
			card.setNumber(cardDto.getNumber());
			updated = true;
		}
		if(!card.getNameOnCard().equals(cardDto.getNameOnCard())) {
			card.setNameOnCard(cardDto.getNameOnCard());
			updated = true;
		}
		
		if(!updated) {
			throw new CardException("Nothing to update");
		}
		return cardRepo.save(card);
	}
	
	public String deleteById(int cardID) {
		Card card = cardRepo.findById(cardID).orElse(null);
		if(card == null) {
			throw new CardException("Requested Card not found in server");
		}
		cardRepo.deleteById(cardID);
    	return "Card " + card.getNumber() + " is deleted successfully!";
    }
}

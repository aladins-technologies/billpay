package com.example.billpay.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.billpay.Dto.CardDto;
import com.example.billpay.Exception.BillPayException;
import com.example.billpay.Exception.CardException;
import com.example.billpay.Exception.InvalidPageException;
import com.example.billpay.Model.Card;
import com.example.billpay.Service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardService cardService;
	
	@PostMapping("/saveCard")
	public ResponseEntity<?> saveCard(@RequestBody CardDto card) {
		Card newCard = null;
		try {
			newCard = cardService.saveCard(card);
		} catch(BillPayException e) {
			throw e;
		} catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getRootCause().toString(), HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Unable to save new Card", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(newCard, HttpStatus.CREATED);
	}
	
	@GetMapping("/getCards")
    public ResponseEntity<?> getCards(@RequestParam int pageNumber,
    		@RequestParam(required = false) Integer cardID,
    		@RequestParam(required = true) Integer userID,
    		@RequestParam(required = false) Boolean isActive,
    		@RequestParam(required = false) String expDate,
    		@RequestParam(required = false) String number,
    		@RequestParam(required = false) String name) {
		Page<Card> cardPage = null;
		try {
			cardPage = cardService.getCards(pageNumber, cardID, userID, isActive, expDate, number, name);
			if(cardPage != null && cardPage.getNumberOfElements() <= 0) {					//elements in selected page
				if(cardPage.getTotalElements() > 0) {
					throw new InvalidPageException("Requested page not available");
				}
				throw new CardException("No Cards available");
			}
			
		} catch(BillPayException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Unable to find available Cards", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(cardPage, HttpStatus.OK);
    }
	
	@PutMapping("/updateCard")
	public ResponseEntity<?> updateCard(@RequestBody CardDto updateDto) {
		Card newCard = null;
		try {
			newCard = cardService.updateCard(updateDto);
		} catch(BillPayException e) {
			throw e;
		} catch(Exception e) {
			return new ResponseEntity<>("Unable to update Card", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(newCard, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCard")
	public ResponseEntity<?> deleteCard(@RequestParam(required = true) Integer cardID) {
		String msg = "";
		try {
			msg = cardService.deleteById(cardID);
		} catch(BillPayException e) {
			throw e;
		} catch(Exception e) {
			return new ResponseEntity<>("Unable to delete Card", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}

package com.example.billpay.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BillPayExceptionHandler {
	
	@ExceptionHandler(BillPayException.class)
	public ResponseEntity<?> billPayExceptionHandler(BillPayException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<?> invalidCredentialExceptionHandler(InvalidCredentialException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<?> invalidJobIdExceptionHandler(InvalidIdException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<?> invalidDateExceptionHandler(InvalidDateException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidRoleException.class)
	public ResponseEntity<?> invalidRoleExceptionHandler(InvalidRoleException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnequeValueException.class)
	public ResponseEntity<?> unequeValueExceptionHandler(UnequeValueException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<?> userExceptionHandler(UserException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BillException.class)
	public ResponseEntity<?> billExceptionHandler(BillException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CardException.class)
	public ResponseEntity<?> cardExceptionHandler(CardException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidPageException.class)
	public ResponseEntity<?> invalidPageExceptionHandler(InvalidPageException e){
		return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.BAD_REQUEST);
	}
}

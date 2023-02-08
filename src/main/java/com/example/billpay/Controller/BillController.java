package com.example.billpay.Controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.billpay.Dto.BillDto;
import com.example.billpay.Dto.PaymentDto;
import com.example.billpay.Exception.BillPayException;
import com.example.billpay.Exception.CardException;
import com.example.billpay.Exception.InvalidPageException;
import com.example.billpay.Model.Bill;
import com.example.billpay.Service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

	@Autowired
	private BillService billService;

	@PostMapping("/saveBill")
	public ResponseEntity<?> saveBill(@RequestBody BillDto bill) {
		Bill newBill = null;
		try {
			newBill = billService.saveBill(bill);
		} catch (BillPayException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getRootCause().toString(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Unable to save new Bill", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(newBill, HttpStatus.CREATED);
	}

	@GetMapping("/getBills")
	public ResponseEntity<?> getBills(@RequestParam int pageNumber,
			@RequestParam(required = false) Integer billID,
			@RequestParam(required = true) Integer userID,
			@RequestParam(required = false) Boolean isPaid,
			@RequestParam(required = false) Timestamp billDate,
			@RequestParam(required = false) Timestamp dueDate,
			@RequestParam(required = false) String number) {
		Page<Bill> billPage = null;
		try {
			billPage = billService.getBill(pageNumber, billID, userID, isPaid, billDate, dueDate, number);
			if (billPage != null && billPage.getNumberOfElements() <= 0) { // elements in selected page
				if (billPage.getTotalElements() > 0) {
					throw new InvalidPageException("Requested page not available");
				}
				throw new CardException("No Bills available");
			}

		} catch (BillPayException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Unable to find available Bills", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(billPage, HttpStatus.OK);
	}

	@DeleteMapping("/deleteBill")
	public ResponseEntity<?> deleteBill(@RequestParam(required = true) Integer billID) {
		String msg = "";
		try {
			msg = billService.deleteById(billID);
		} catch (BillPayException e) {
			throw e;
		} catch (Exception e) {
			return new ResponseEntity<>("Unable to delete Bill", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PostMapping("/makePayment")
	public ResponseEntity<?> makePayment(@RequestBody PaymentDto paymentDto) {
		String msg = "";
		try {
			msg = billService.makePayment(paymentDto);
		} catch (BillPayException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Unable to make payment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

}

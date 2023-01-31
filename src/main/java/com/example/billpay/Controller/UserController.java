package com.example.billpay.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billpay.Exception.BillPayException;
import com.example.billpay.Model.User;
import com.example.billpay.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		User newUser = null;
		try {
			newUser = userService.saveUser(user);
		} catch(BillPayException e) {
			throw e;
		} catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getRootCause().toString(), HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Unable to save new User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
}

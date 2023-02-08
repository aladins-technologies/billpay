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
import com.example.billpay.Service.EmailSenderService;
import com.example.billpay.Service.UserService;
import com.example.billpay.Util.StaticMethods;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailSenderService emailSender;

	@PostMapping("/saveUser")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		User newUser = null;
		try {
			newUser = userService.saveUser(user);
		} catch (BillPayException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getRootCause().toString(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Unable to save new User", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		StringBuilder msg = new StringBuilder("Name : ").append(newUser.getName()).append("\n")
				.append("Email : ").append(newUser.getEmail()).append("\n")
				.append("Mobile Number : ").append(newUser.getMobile()).append("\n");
		emailSender.sendMail(StaticMethods.APP_MAIL_ID, newUser.getEmail(), "User Registration Successfull", msg.toString());

		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
}

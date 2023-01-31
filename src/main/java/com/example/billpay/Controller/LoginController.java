package com.example.billpay.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billpay.Model.User;
import com.example.billpay.Service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/loggedUser")
	public User getUserDetailsAfterLogin(Authentication authentication) {
		User user = userService.getUserDetails(authentication.getName());
		return user;
	}

}

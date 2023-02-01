package com.example.billpay.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.billpay.Exception.InvalidRoleException;
import com.example.billpay.Exception.UserException;
import com.example.billpay.Model.Authority;
import com.example.billpay.Model.User;
import com.example.billpay.Repository.AuthorityRepository;
import com.example.billpay.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Transactional
	public User saveUser(User user) {				
		if(user.getName() == null || user.getName().length() < 3) {
			throw new UserException("User name must have 3 chars");
		} else if(user.getPwd() == null || user.getPwd().length() < 5){
			throw new UserException("User password must have 5 chars");
		} else if(user.getRole() == null || (!user.getRole().equalsIgnoreCase("ADMIN") && !user.getRole().equalsIgnoreCase("USER"))) {
			throw new InvalidRoleException("Please provide a valid Role");
		}
		
		if(user.getMobile() == null || user.getMobile().length() < 10) {
			throw new UserException("Please provide a valid Phone number");
		} else {
			String phone = user.getMobile();
			String regex = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(phone);
			if(!matcher.matches()) {
				throw new UserException("Please provide a valid Phone number");
			}
		}
		
		if(user.getEmail() == null || user.getEmail().length() < 5){
			throw new UserException("Please provide a valid Email address");
		} else {
			String email = user.getEmail();
			String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
					+ "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]"
					+ "|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:"
					+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			if(!matcher.matches()) {
				throw new UserException("Please provide a valid Email address");
			}
		}
		
		String hashPwd = passwordEncoder.encode(user.getPwd());
		user.setPwd(hashPwd);
		User newUser = userRepo.save(user);
		if(newUser.getUserID() <= 0) {
			throw new UserException("Unable to save new User " + user.getName());
		} else {
			Authority auth = new Authority("ROLE_" + newUser.getRole(), newUser);
			authRepo.save(auth);
		}
		
		return newUser;
	}
	
	public User getUserDetails(String email) {
        List<User> users = userRepo.findByEmail(email);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }
}

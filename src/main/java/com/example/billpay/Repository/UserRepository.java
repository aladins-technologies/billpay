package com.example.billpay.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billpay.Model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByName(String name);
	
	List<User> findByEmail(String email);
	
}

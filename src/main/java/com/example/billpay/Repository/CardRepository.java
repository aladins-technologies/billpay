package com.example.billpay.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.billpay.Model.Card;


public interface CardRepository extends JpaRepository<Card, Integer>{
	
	@Query(value = "SELECT * FROM card WHERE "
			+ "CASE WHEN :cardID >= 0 THEN cardid = :cardID ELSE 1 = 1 END "
			+ "AND CASE WHEN :userID >= 0 THEN userid = :userID ELSE 1 = 1 END "
			+ "AND CASE WHEN :isActive >= 0 THEN is_active = :isActive ELSE 1 = 1 END "
			+ "AND CASE WHEN :expDate IS NOT NULL THEN exp_date = :expDate ELSE 1 = 1 END "
			+ "AND CASE WHEN :number IS NOT NULL THEN number = :number ELSE 1 = 1 END "
			+ "AND CASE WHEN :name IS NOT NULL THEN name_on_card = :name ELSE 1 = 1 END "
			+ "ORDER BY cardid desc", nativeQuery = true)
	Page<Card> getCards(@Param("cardID") int cardID, 
			@Param("userID") int userID, 
			@Param("isActive") int isActive, 
			@Param("expDate") String expDate, 
			@Param("number") String number, 
			@Param("name") String name,
			Pageable pageable);
	
}

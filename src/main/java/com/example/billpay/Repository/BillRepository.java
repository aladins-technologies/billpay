package com.example.billpay.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.billpay.Model.Bill;


public interface BillRepository extends JpaRepository<Bill, Integer>{

	@Query(value = "SELECT * FROM bill WHERE "
			+ "CASE WHEN :billID >= 0 THEN billid = :billID ELSE 1 = 1 END "
			+ "AND CASE WHEN :userID >= 0 THEN userid = :userID ELSE 1 = 1 END "
			+ "AND CASE WHEN :isPaidInt >= 0 THEN is_paid = :isPaidInt ELSE 1 = 1 END "
			+ "AND CASE WHEN :billDate IS NOT NULL THEN CAST(bill_date AS DATE) = :billDate ELSE 1 = 1 END "
			+ "AND CASE WHEN :dueDate IS NOT NULL THEN CAST(due_date AS DATE) = :dueDate ELSE 1 = 1 END "
			+ "AND CASE WHEN :number IS NOT NULL THEN number = :number ELSE 1 = 1 END "
			+ "ORDER BY due_date ", nativeQuery = true)
	Page<Bill> getBill(@Param("billID") int billID, 
			@Param("userID") int userID, 
			@Param("isPaidInt") int isPaidInt, 
			@Param("billDate") String billDate, 
			@Param("dueDate") String dueDate,
			@Param("number") String number, 
			Pageable pageable);
}

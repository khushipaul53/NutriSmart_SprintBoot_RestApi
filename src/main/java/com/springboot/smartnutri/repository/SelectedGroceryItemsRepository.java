package com.springboot.smartnutri.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.smartnutri.entity.SelectedGroceryItems;


public interface SelectedGroceryItemsRepository extends JpaRepository<SelectedGroceryItems, Long> {
	List<SelectedGroceryItems> findAllByUserEmail(String userEmail);
	List<SelectedGroceryItems> findAllByUserEmailAndExpiryDateBeforeOrExpiryDate(String userEmail, Date expiryDate, Date expiredToday);
	List<SelectedGroceryItems> findAllByUserEmailAndExpiryDateAfter(String userEmail, Date todayDate);
	List<SelectedGroceryItems> findAllByUserEmailAndExpiryDateBefore(String userEmail, Date todayDate);
	List<SelectedGroceryItems> findAllByUserEmailAndExpiryDateGreaterThan(String userEmail, Date todayDate);
}

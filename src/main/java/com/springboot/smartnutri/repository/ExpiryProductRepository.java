package com.springboot.smartnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.smartnutri.entity.ExpiredItems;

public interface ExpiryProductRepository extends JpaRepository<ExpiredItems, Long> {
	List<ExpiredItems> findAllByUserEmail(String userEmail);
}

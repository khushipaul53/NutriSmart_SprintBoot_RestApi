package com.springboot.smartnutri.service.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.smartnutri.entity.ExpiredItems;
import com.springboot.smartnutri.entity.SelectedGroceryItems;
import com.springboot.smartnutri.entity.User;
import com.springboot.smartnutri.repository.ExpiryProductRepository;
import com.springboot.smartnutri.repository.SelectedGroceryItemsRepository;
import com.springboot.smartnutri.repository.UserRepository;
import com.springboot.smartnutri.service.ExpiryProductService;

@Service
public class ExpiryProductServiceImpl implements ExpiryProductService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ExpiryProductRepository expiredProductRepo;
	
	@Autowired
	SelectedGroceryItemsRepository groceryItemsRepo;
	
	private static final Date TWO_DAY_FROM_EXPIRY_DATE = addDaysToCurrentDay(2);
	
	@Override
	public List<?> checkIfProductExpired() {
		
		List<User> userList = userRepo.findAll();
		userList.forEach(user -> {
			List<SelectedGroceryItems> selectedGrocery = groceryItemsRepo.findAllByUserEmailAndExpiryDateBeforeOrExpiryDate(user.getEmail(), TWO_DAY_FROM_EXPIRY_DATE, TWO_DAY_FROM_EXPIRY_DATE);
			System.out.println(selectedGrocery.toString());
			expiredProductRepo.deleteAll();
			selectedGrocery.forEach(item -> {
				ExpiredItems expiredItem = new ExpiredItems();
				expiredItem.setId(item.getSNo());
				expiredItem.setExpiryDate(item.getExpiryDate());
				expiredItem.setFood(item.getFood());
				expiredItem.setPurchaseDate(item.getPurchaseDate());
				expiredItem.setUserEmail(user.getEmail());
				expiredProductRepo.save(expiredItem);
			});
			
		});
		
		return null;
	}

	private static Date addDaysToCurrentDay(int numberOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, numberOfDays);
		return new Date(cal.getTime().getTime());
	}
}

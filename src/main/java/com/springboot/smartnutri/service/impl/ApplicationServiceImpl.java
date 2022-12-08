package com.springboot.smartnutri.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.smartnutri.entity.SelectedGroceryItems;
import com.springboot.smartnutri.payload.MacroNutrient;
import com.springboot.smartnutri.payload.Meal;
import com.springboot.smartnutri.repository.ExpiryProductRepository;
import com.springboot.smartnutri.repository.SelectedGroceryItemsRepository;
import com.springboot.smartnutri.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	ExpiryProductRepository expiredProductRepo;
	
	@Autowired
	SelectedGroceryItemsRepository selectedGroceryItemsRepository;
	
	@Override
	public List<?> getExpiredItemsList(String email) {
		if(email==null || email.trim().isEmpty())
			return expiredProductRepo.findAll();
		return expiredProductRepo.findAllByUserEmail(email);
	}

	@Override
	public MacroNutrient macroCalculation(String gender,double weight, double height, double age) {
		MacroNutrient macroNutrient = new MacroNutrient();
		if(gender.equalsIgnoreCase("male")) {
			macroNutrient.setCalorie(menCalorieRequired(weight, height, age));
			macroNutrient.setProteinNeeded();
			macroNutrient.setCarbsNeeded();
			macroNutrient.setFatsNeeded();
		}
		if(gender.equalsIgnoreCase("female")) {
			macroNutrient.setCalorie(womenCalorieRequired(weight, height, age));
			macroNutrient.setProteinNeeded();
			macroNutrient.setCarbsNeeded();
			macroNutrient.setFatsNeeded();
		}
		macroNutrient.setbMI((weight/Math.pow(height, 2))*10000);
		return macroNutrient;
	}
	
	/**
	 * 
	 * @param weight in KG
	 * @param height in CM
	 * @param age in INTEGER
	 * @return calorie
	 */
	public double menCalorieRequired(double weight, double height, double age) {
		return 66.5 + (13.8 * (weight)) +( 5 * (height) )/ (6.8 *age);
	}
	
	/**
	 * 
	 * @param weight in KG
	 * @param height in CM
	 * @param age in INTEGER
	 * @return calorie
	 */
	public double womenCalorieRequired(double weight, double height, double age) {
		return 655.1 + (9.6 * (weight)) +( 1.9 * (height) )/ (4.7 *age);
	}

	@Override
	public List<?> getListOfMeals(String email) {
		List<SelectedGroceryItems> groceryItemsList = selectedGroceryItemsRepository.findAllByUserEmailAndExpiryDateAfter(email, currentDay());
		List<Meal> meals= new ArrayList<>();
		int max=0, min=0, r=0, count=0;
		
		max= groceryItemsList.size();
		if(max == 0) {
			return null;
		}
		else if(max == 1 || max == 2) {
			Meal meal= new Meal();
			List<String> foodItems= new ArrayList<>();
			foodItems.add(groceryItemsList.get(0).getFood());
			meal.setFoodItems(foodItems);
			meals.add(meal);
			if(max == 2) {
				foodItems.add(groceryItemsList.get(1).getFood());
			}
			return meals;
		}
		else {
			for(int i=0; i<(max/3) && i<2; i++) {
				List<Integer> randomIntg = new ArrayList<Integer>();
				randomIntg.add( (int)Math.random() * (max - min + 1) + min);
				do {
					r= (int)(Math.random() * (max - min + 1) + min);
					if(r==max)
						r--;
				}while(randomIntg.contains(r) && count++ < 10 );
				randomIntg.add(r);
				
				count=0;
				do {
					r= (int)(Math.random() * (max - min + 1) + min);
					if(r==max)
						r--;
				}while(randomIntg.contains(r) && count++ < 10 );
				randomIntg.add(r);
				List<String> foodItems= new ArrayList<>();
				randomIntg.forEach(number -> {
					foodItems.add(groceryItemsList.get(number).getFood());
				});
				Meal meal= new Meal();
				meal.setFoodItems(foodItems);
				meals.add(meal);
			}
			return meals;
		}
		
	}
	
	private static Date currentDay() {
		Calendar cal = Calendar.getInstance();
		return new Date(cal.getTime().getTime());
	}
}

package com.springboot.smartnutri.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.smartnutri.entity.SelectedGroceryItems;
import com.springboot.smartnutri.payload.GroceryItemsDTO;
import com.springboot.smartnutri.payload.ResponseDTO;
import com.springboot.smartnutri.repository.SelectedGroceryItemsRepository;
import com.springboot.smartnutri.service.ApplicationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Application controller provides all APIs related to application")
@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {
	
	@Autowired
	SelectedGroceryItemsRepository selectedGroceryItemsRepository;
	
	@Autowired
	ApplicationService applicationServiceImpl;
	
	@ApiOperation("Api calculates Body Mass Index of user. Mass is in Killogram, Height in Centimeters, age is integer and Gender can only be Male or Female")
	@GetMapping("/bmiCalc/{mass}/{height}/{age}/{gender}")
	public ResponseEntity<?> calculateBMI(@PathVariable(required = true) int mass, @PathVariable int height,@PathVariable int age,@PathVariable String gender) {
		if(mass <= 0 || height <=0 || age<=0 || gender==null || gender.trim().isEmpty() || !(gender.trim().equalsIgnoreCase("male") || gender.trim().equalsIgnoreCase("female")) )
			return new ResponseEntity<>( new ResponseDTO("Please provide correct value for Mass or Height or Age. Must be greater than 0.\n Gender should be male or female."), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>( new ResponseDTO(applicationServiceImpl.macroCalculation(gender, height, height, age),"success"), HttpStatus.OK);
	}
	
	@ApiOperation("Api to save grocery items and to fetch grocery items")
	@PostMapping("/selectedgrocery")
	public ResponseEntity<?> saveGroceryItems(@RequestBody GroceryItemsDTO groceryItems){

		if(!Objects.isNull(groceryItems) && ( groceryItems.getItems().size() >0 ) ) {
			groceryItems.getItems().forEach(item -> {
				SelectedGroceryItems selectedItems = new SelectedGroceryItems();
				selectedItems.setFood(item.getFood());
				selectedItems.setCalories(item.getCalories());
				selectedItems.setCarbs(item.getCarbs());
				selectedItems.setCategory(item.getCategory());
				selectedItems.setFat(item.getFat());
				selectedItems.setFibers(item.getFibers());
				selectedItems.setGrams(item.getGrams());
				selectedItems.setMeasure(item.getMeasure());
				selectedItems.setMeasuringUnit(item.getMeasuringUnit());
				selectedItems.setProtein(item.getProtein());
				selectedItems.setSaturatedFat(item.getSaturatedFat());
				selectedItems.setSNo(item.getSNo());
				selectedItems.setPurchaseDate(groceryItems.getPurchaseDate());
				selectedItems.setExpiryDate(item.getExpiryDate());
				selectedItems.setUserEmail(groceryItems.getUserEmail());
				selectedGroceryItemsRepository.save(selectedItems);
				});
			return new ResponseEntity<>(groceryItems, HttpStatus.CREATED);
		}
		else if(!Objects.isNull(groceryItems) && ( groceryItems.getItems().size() == 0 )) {
			List<com.springboot.smartnutri.payload.SelectedGroceryItems> listOfSelectedItems = new ArrayList<>();
			selectedGroceryItemsRepository.findAllByUserEmail(groceryItems.getUserEmail()).forEach(item -> {
				com.springboot.smartnutri.payload.SelectedGroceryItems selectedItems = new com.springboot.smartnutri.payload.SelectedGroceryItems();

				selectedItems.setFood(item.getFood());
				selectedItems.setCalories(item.getCalories());
				selectedItems.setCarbs(item.getCarbs());
				selectedItems.setCategory(item.getCategory());
				selectedItems.setFat(item.getFat());
				selectedItems.setFibers(item.getFibers());
				selectedItems.setGrams(item.getGrams());
				selectedItems.setMeasure(item.getMeasure());
				selectedItems.setMeasuringUnit(item.getMeasuringUnit());
				selectedItems.setProtein(item.getProtein());
				selectedItems.setSaturatedFat(item.getSaturatedFat());
				selectedItems.setSNo(item.getSNo());
				groceryItems.setPurchaseDate(item.getPurchaseDate());
				selectedItems.setExpiryDate(item.getExpiryDate());
				groceryItems.setUserEmail(item.getUserEmail());
				
				listOfSelectedItems.add(selectedItems);
			});
			groceryItems.setItems(listOfSelectedItems);
			return new ResponseEntity<>(groceryItems, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(groceryItems, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getExpiredItems")
	public ResponseEntity<?> getExpiredItems(@RequestParam("userEmail") String email){
		return new ResponseEntity<>(new ResponseDTO(applicationServiceImpl.getExpiredItemsList(email), null), HttpStatus.OK);
	}
	
	@GetMapping("/recommendMeal")
	public ResponseEntity<?> getMeals(@RequestParam("userEmail") String email){
		List<?> data = applicationServiceImpl.getListOfMeals(email);
		return new ResponseEntity<>(new ResponseDTO(data, data==null ? "No Data" : "success"), HttpStatus.OK);
	}
}

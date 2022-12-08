package com.springboot.smartnutri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.smartnutri.payload.ResponseDTO;
import com.springboot.smartnutri.repository.FoodNutrientsRepository;

import io.swagger.annotations.Api;

@Api(value = "Master Data controller provides all master tables data")
@RestController
@RequestMapping("/api/v1/master")
public class MasterDataController {
	@Autowired
	private FoodNutrientsRepository foodNutrientRepository;
	
	@GetMapping("/getFoods")
	public ResponseEntity<?> getFoods() {
		return new ResponseEntity<>(new ResponseDTO(foodNutrientRepository.findAll(),null), HttpStatus.OK);
	}
}

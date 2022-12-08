
package com.springboot.smartnutri.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "foodnutrients")
public class FoodNutrients {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sNo;
	
	private String food;
	private String measure;
	private String measuringUnit;
	private String grams;
	private String calories;
	private String protein;
	private String fat;
	private String saturatedFat;
	private String fibers;
	private String carbs;
	private String category;
}

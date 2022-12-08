package com.springboot.smartnutri.payload;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedGroceryItems {
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
	private Date expiryDate;
}

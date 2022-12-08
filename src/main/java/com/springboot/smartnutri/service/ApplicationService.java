package com.springboot.smartnutri.service;

import java.util.List;

import com.springboot.smartnutri.payload.MacroNutrient;

public interface ApplicationService {
	public List<?> getExpiredItemsList(String email);
	public MacroNutrient macroCalculation(String gender,double weight, double height, double age);
	public List<?> getListOfMeals(String email);
}

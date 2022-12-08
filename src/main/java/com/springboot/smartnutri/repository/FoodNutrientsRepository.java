package com.springboot.smartnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.smartnutri.entity.FoodNutrients;

public interface FoodNutrientsRepository extends JpaRepository<FoodNutrients, Long>{

}

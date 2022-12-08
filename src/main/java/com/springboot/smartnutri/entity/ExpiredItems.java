package com.springboot.smartnutri.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "expireditems")
public class ExpiredItems {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long insertionNumber;
    
    @Id
	private long id;
	
	private String food;
//	private String measure;
//	private String measuringUnit;
//	private String grams;
//	private String calories;
//	private String protein;
//	private String fat;
//	private String saturatedFat;
//	private String fibers;
//	private String carbs;
//	private String category;
	
	private String userEmail;
	private Date purchaseDate;
	private Date expiryDate;
}

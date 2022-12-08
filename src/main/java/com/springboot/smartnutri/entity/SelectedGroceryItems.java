package com.springboot.smartnutri.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SelectedGroceryItems")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserSelectedItem.class)
public class SelectedGroceryItems {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long insertionNumber;
    
    @Id
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
	
	@Id
	private String userEmail;
	private Date purchaseDate;
	private Date expiryDate;
}

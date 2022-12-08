package com.springboot.smartnutri.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserSelectedItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5699415254789294109L;
	private long sNo;
	private String userEmail;
}

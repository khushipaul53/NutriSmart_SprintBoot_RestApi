package com.springboot.smartnutri.exception;

import org.springframework.http.HttpStatus;

public class NutriSmartAPIException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3950441173896055798L;
	
	private HttpStatus status;
    private String message;

    public NutriSmartAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public NutriSmartAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.springboot.smartnutri.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
public class ResponseDTO {
    private Object data;
    private String message;
    
    public ResponseDTO(String message) {
        this.message = message;
    }

}

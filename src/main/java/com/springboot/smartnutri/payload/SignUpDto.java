package com.springboot.smartnutri.payload;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Valid
public class SignUpDto {
    private String name;
    private String username;
    @Pattern(message = "Enter correct email address", regexp =  "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    private String password;
    private String gender;
    private String height;
    private int age;
    private String weight;
    private String activitylevel;
}

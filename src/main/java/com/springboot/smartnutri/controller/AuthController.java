package com.springboot.smartnutri.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.smartnutri.entity.Role;
import com.springboot.smartnutri.entity.User;
import com.springboot.smartnutri.payload.ErrorDetails;
import com.springboot.smartnutri.payload.JWTAuthResponse;
import com.springboot.smartnutri.payload.LoginDto;
import com.springboot.smartnutri.payload.ResponseDTO;
import com.springboot.smartnutri.payload.SignUpDto;
import com.springboot.smartnutri.repository.RoleRepository;
import com.springboot.smartnutri.repository.UserRepository;
import com.springboot.smartnutri.security.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Authentication controller exposes siginin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation(value = "REST API to Signin or Login user to NutriSmart app")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @ApiOperation(value = "REST API to Register or Signup user to NutriSmart app")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>(new ErrorDetails("Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>(new ErrorDetails("Email is already taken!"), HttpStatus.BAD_REQUEST);
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<SignUpDto>> violations = validator.validate(signUpDto);
        if(!violations.isEmpty()) {
        	List<String> list= new ArrayList<String>();
        	for(ConstraintViolation<?> i : violations) {
        		list.add(i.getConstraintDescriptor().getMessageTemplate());
        	}
        	return new ResponseEntity<>(list.toString(), HttpStatus.BAD_REQUEST);
        }
        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getEmail());
        user.setEmail(signUpDto.getEmail());
        user.setAge(signUpDto.getAge());
        user.setGender(signUpDto.getGender());
        user.setHeight(signUpDto.getHeight());
        user.setWeight(signUpDto.getWeight());
        user.setActivitylevel(signUpDto.getActivitylevel());
        
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);
        
        return new ResponseEntity<>(new ResponseDTO("User registered successfully"), HttpStatus.OK);

    }
    
    @ApiOperation(value = "REST API to list all registered user in app")
    @GetMapping("/list")
    public ResponseEntity<?> listUsers(){
		return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }
}

package com.example.realestate.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realestate.dto.LoginDto;
import com.example.realestate.entity.User;
import com.example.realestate.exceptions.UserException;
import com.example.realestate.service.UserServiceImpl;

@RestController
@RequestMapping("/api/login")
//@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class LoginController {
	
	@Autowired
	private UserServiceImpl userService;
	
	//login
	
		@PostMapping("/loginUser")	//http://localhost:1234/api/login/loginUser
		public User loginUser(@Validated @RequestBody LoginDto loginDto) throws UserException
		{
			Optional<User> user =userService.loginUser(loginDto);
			if (user.isPresent())
				return  user.get();
			else
				throw new UserException("Login failure");
		
			
		}

}

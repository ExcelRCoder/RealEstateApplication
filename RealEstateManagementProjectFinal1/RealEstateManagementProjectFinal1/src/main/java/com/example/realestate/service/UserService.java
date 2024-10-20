package com.example.realestate.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.realestate.dto.LoginDto;
import com.example.realestate.entity.User;
import com.example.realestate.exceptions.UserException;


public interface UserService {
	List<User> getAllUsers();
	Optional<User> getUserById(Long id);
	User  addUser(User user) throws UserException ;
	User updateUser(User userDetails,Long id,MultipartFile file)throws IOException  ;
	void deleteUser(Long id);
	Optional<User>  findByEmail(String email);
	Optional<User>  loginUser(LoginDto loginDto) throws UserException;
	
	

}

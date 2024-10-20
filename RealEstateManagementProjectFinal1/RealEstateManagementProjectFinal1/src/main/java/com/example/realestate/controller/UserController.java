package com.example.realestate.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.realestate.entity.User;
import com.example.realestate.exceptions.UserException;
import com.example.realestate.service.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
// Adjust based on frontend URL
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	// getAllUsers
	@GetMapping("/getAllUsers") // http://localhost:1234/api/user/getAllUsers
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// getUserDetailsById
	@GetMapping("/{id}") // http://localhost:1234/api/user/id
	public User getUserById(@PathVariable("id") Long id) throws UserException {
		Optional<User> user = userService.getUserById(id);
		if (user.isPresent())
			return user.get();
		else
			throw new UserException("User not found with given id");
	}

	// getUserDetailsByEmail
	@GetMapping("/userDetailsByEmail/{email}")
	public User getUserByEmail(@PathVariable("email") String email) throws UserException {
		// System.out.println("herre ----------- " + email);
		Optional<User> user = userService.findByEmail(email);
		if (user.isPresent())
			return user.get();
		else
			throw new UserException("User  not found with given email");
	}

	// createUser
	@PostMapping("/addUser") // http://localhost:1234/api/user/addUser
	public ResponseEntity<String> userCreation(@Validated @RequestBody User user) throws UserException {
		if (user != null) {
			userService.addUser(user);

			return new ResponseEntity<String>("User added sucessfully", new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Please enter all fields", new HttpHeaders(), HttpStatus.OK);
		}

	}

	// deleteUser
	@DeleteMapping("/deleteUser/{id}") // http://localhost:1234/api/user/delete/{id}
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);

		return new ResponseEntity<String>("User deleted successfully", new HttpHeaders(), HttpStatus.OK);
	}

	// updateUser
	@PutMapping("/updateUser/{id}") // http://localhost:1234/api/user/updateUser/{id}/file
	public User updateUser(@Validated @ModelAttribute User user, @PathVariable("id") Long id,
			@RequestParam("image") MultipartFile file) throws IOException {

		return userService.updateUser(user, id, file);

	}

	@GetMapping("/logout") // http://localhost:1234/api/user/getAllUsers
	public User logout() {
		return null;
	}

}

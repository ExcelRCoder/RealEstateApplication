package com.example.realestate.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.realestate.dto.LoginDto;
import com.example.realestate.entity.User;
import com.example.realestate.exceptions.UserException;
import com.example.realestate.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileUploadService fileUploadService;

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User addUser(User user) throws UserException {
		String userName = user.getUsername();
		System.out.println(userName);
		String userEmail = user.getEmail();
		System.out.println(userEmail);
		Optional<User> userNameCheck = userRepository.findByUsername(userName);
		Optional<User> userEmailCheck = userRepository.findByEmail(userEmail);
		System.out.println(userNameCheck);
		if (userNameCheck.isEmpty()) {

			if (userEmailCheck.isEmpty()) {
				return userRepository.save(user);
			} else {
				throw new UserException("user email already exist");
			}

		}

		else {
			throw new UserException("UserName already exist");
		}
	}

	/*
	 * if (userRepository.findByUsername(user.getUsername()).isPresent()) {
	 * System.out.println("userName "); throw new
	 * UserException("Username is already taken"); } if
	 * (userRepository.findByEmail(user.getEmail()).isPresent()) {
	 * 
	 * throw new UserException("Email is already in use"); }
	 * 
	 * if ((user.getUsername().isEmpty()) || (user.getEmail().isEmpty()) ||
	 * (user.getPassword().isEmpty())) { throw new
	 * UserException("All fields are mandatory"); } else { return
	 * userRepository.save(user);
	 * 
	 * }
	 */

	@Override
	public User updateUser(User userDetails, Long id, MultipartFile file) throws IOException {

		// Handle image upload

		return userRepository.findById(id).map(user -> {
			user.setUsername(userDetails.getUsername());
			user.setEmail(userDetails.getEmail());
			user.setPassword(userDetails.getPassword());
			// user.setImageUrl(userDetails.getImageUrl());
			if (file != null && !file.isEmpty()) {
				String fileName;
				try {
					fileName = fileUploadService.saveFile(file);
					user.setImageUrl(fileName);
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
			return userRepository.save(user);
		}).orElseThrow(() -> new RuntimeException("User not found with id " + id));
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}

	/*
	 * @Override public Optional<UserDto> findByEmail(String email) { return
	 * userDtoRepository.findByEmail(email); }
	 */

	@Override
	public Optional<User> loginUser(LoginDto loginDto) throws UserException {

		String userName = loginDto.getUsername();
		System.out.println(userName);
		System.out.println(loginDto.getPassword());
		Optional<User> userData = userRepository.findByUsername(userName);
		// Optional<UserDto> user=userDtoRepository.findByEmail(userDto.getEmail());
		if (userData != null) {

			Optional<User> userData1 = userRepository.findByUsernameAndPassword(loginDto.getUsername(),
					loginDto.getPassword());
			System.out.println(userData1);
			if (!userData1.isEmpty()) {

				return userData1;

			} else {
				throw new UserException("Username/Password incorrect");
			}

		} else {
			throw new UserException("UserName not found");
		}

	}

	@Override
	public Optional<User> findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

}

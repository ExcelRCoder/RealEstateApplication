package com.example.realestate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.realestate.dto.LoginDto;
import com.example.realestate.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	 Optional<User> findByEmail(String email);
	 Optional<User> findByUsername(String username);
	 Optional<User> findByUsernameAndPassword(String username,String password);
}

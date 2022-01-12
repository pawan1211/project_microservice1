package com.spring.olx_login.service;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;

import com.spring.olx_login.dto.Users;



public interface UserService {

	Users userRegis(Users user);
	boolean logoutUser(String authToken);
	ResponseEntity<?> findByUsername(String username);
	public ResponseEntity<?> findall();

	
}

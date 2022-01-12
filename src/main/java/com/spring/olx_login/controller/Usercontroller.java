package com.spring.olx_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.spring.olx_login.dto.AuthenticationRequest;
import com.spring.olx_login.dto.Users;
import com.spring.olx_login.exception.InvalidAuthorizationTokenException;
import com.spring.olx_login.exception.InvalidloginException;
import com.spring.olx_login.service.UserService;
import com.spring.olx_login.util.Jwtutil;


@RestController
public class Usercontroller {
	
	@Autowired
	 UserService userService;

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	Jwtutil jwtUtils;

	
	@PostMapping("/user/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authrequest) {
		try {
			
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
																													
		} catch (BadCredentialsException ex) {
			throw new BadCredentialsException(authrequest.getUsername());
		}

		String jwtToken = jwtUtils.generateToken(authrequest.getUsername());
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);

	}
	
	@GetMapping("/token/validate") 
	public ResponseEntity<Boolean>isTokenValid(@RequestHeader("Authorization") String jwtToken)
	{
jwtToken = jwtToken.substring(7, jwtToken.length());
		
		//2) Extract username from the jwtToken using jwtUtils.extractUsername(jwtToken)
		String username = jwtUtils.extractUsername(jwtToken);
		
		//3) Extract UserDetails using UserDetailsService.loadUserByUsername(username)
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		//4) Validate the token using jwtUtils.validateToken(jwtToken, userDetails);
		boolean isTokenValid = jwtUtils.validateToken(jwtToken, userDetails);
		if(isTokenValid)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}


	
	@PostMapping(value = "/user")
	public ResponseEntity<Users> userRegistration(@RequestBody Users user) {
		
		 user =userService.userRegis(user);
		return new ResponseEntity<Users>(user, HttpStatus.OK);

	}

	
	@GetMapping(value = "/getuser")
	public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authToken) {

		authToken = authToken.substring(7, authToken.length());
		
		//2) Extract username from the jwtToken using jwtUtils.extractUsername(jwtToken)
		String username = jwtUtils.extractUsername(authToken);
		
		//3) Extract UserDetails using UserDetailsService.loadUserByUsername(username)
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		//4) Validate the token using jwtUtils.validateToken(jwtToken, userDetails);
		boolean isTokenValid = jwtUtils.validateToken(authToken, userDetails);
		if(isTokenValid)
			return userService.findByUsername(username);
		else
			throw new InvalidAuthorizationTokenException("invalid token");

	//	return userService.findall();

	}

	
	@DeleteMapping(value = "/user/logout")
	public ResponseEntity<Boolean> logoutUser(@RequestHeader("Authorization") String authToken) {
		
authToken = authToken.substring(7, authToken.length());
		
		//2) Extract username from the jwtToken using jwtUtils.extractUsername(jwtToken)
		String username = jwtUtils.extractUsername(authToken);
		
		//3) Extract UserDetails using UserDetailsService.loadUserByUsername(username)
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		//4) Validate the token using jwtUtils.validateToken(jwtToken, userDetails);
		boolean isTokenValid = jwtUtils.validateToken(authToken, userDetails);
		if(isTokenValid)
			return new ResponseEntity<Boolean>(userService.logoutUser(authToken), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		
	}	
	
	}

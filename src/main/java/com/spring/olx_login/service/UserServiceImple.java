package com.spring.olx_login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.olx_login.dto.Users;
import com.spring.olx_login.entity.UserEntity;
import com.spring.olx_login.repo.UserRepository;





@Service
public class UserServiceImple implements UserService{

	@Autowired
	UserRepository userrepository;
	
	@Autowired
	UserServiceDetailsImple userServiceDetailsImple;
	
	@Override
	public Users userRegis(Users user) {
		UserEntity userEntity = new UserEntity(user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone());
		userEntity = userrepository.save(userEntity);
		user = new Users(userEntity.getId(),userEntity.getFirstName(),userEntity.getLastName(),userEntity.getUsername(),userEntity.getPassword(),userEntity.getEmail(),userEntity.getPhone());
		return user;
	}

	@Override
	public boolean logoutUser(String authToken) {
		
		return true;
	}


	public ResponseEntity<?> findByUsername(String username) {
	
		List<UserEntity> userEntityList = userrepository.findByUsername(username);
		List<Users> userList = new ArrayList();
		for( UserEntity userEntity:userEntityList)
		{
			Users user = new Users(userEntity.getId(),userEntity.getFirstName(),userEntity.getLastName(),userEntity.getUsername(),userEntity.getPassword(),userEntity.getEmail(),userEntity.getPhone());
			userList.add(user);
		}
		return ResponseEntity.ok(userList);
	}

	public ResponseEntity<?> findall() {
	
		List<UserEntity> userEntityList = userrepository.findAll();
		List<Users> userList = new ArrayList();
		for( UserEntity userEntity:userEntityList)
		{
			Users user = new Users(userEntity.getId(),userEntity.getFirstName(),userEntity.getLastName(),userEntity.getUsername(),userEntity.getPassword(),userEntity.getEmail(),userEntity.getPhone());
			userList.add(user);
		}
		return ResponseEntity.ok(userList);
	}

}

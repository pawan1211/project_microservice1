package com.spring.olx_login.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {
	
	
	
	@Id
	
	private int id;

	public UserEntity( String firstName, String lastName,String username,String password, String email, int phone) {
		super();
		
		this.firstName = firstName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.lastName = lastName;
		this.username = username;
	
	}




	public UserEntity()
	{
		
	}

	private String firstName;
	private String password;
	private String email;
	private int phone;
	private String lastName;
	private String username;
	private String role;
	

	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}






	

	



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	

	
	
	

	

}

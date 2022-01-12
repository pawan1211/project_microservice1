package com.spring.olx_login.exception;

public class InvalidAuthorizationTokenException extends RuntimeException{

	
	private String msg;
	public InvalidAuthorizationTokenException()
	{
		this.msg="";
	}
	public InvalidAuthorizationTokenException(String msg)
	{
		this.msg= msg;
	}
	
	@Override
	public String toString() {
		return "Invalid Token Id:" + msg;
		
	}
}

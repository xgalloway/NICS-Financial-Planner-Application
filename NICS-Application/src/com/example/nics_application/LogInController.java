package com.example.nics_application;

public class LogInController {
	
	private String username;
	private String password;

	public LogInController(String username, String password) {
		this.username = username;
		this.password = password;
	}
	/*
	 * Todo: access database instead of defaulting to password
	 */
	public boolean isValid() {
		if (username.equals("admin") && password.equals("pass123")) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String s) {
		this.username = s;
	}
	
	public void setPassword(String s) {
		this.password = s;
	}
}

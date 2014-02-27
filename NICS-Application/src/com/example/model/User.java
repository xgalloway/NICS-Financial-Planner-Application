package com.example.model;

import java.util.ArrayList;
import java.util.List;
	
public class User {
	private String username;
	private String password;
	private String[] accounts;
	private List<UserAccount> a = new ArrayList<UserAccount>();
	
	public User(String name, String password) {
		this.username = name;
		this.password = password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String[] getAccounts(){
		return accounts;
	}
	
	public void addAccount(UserAccount account){
		a.add(account);
	}
}

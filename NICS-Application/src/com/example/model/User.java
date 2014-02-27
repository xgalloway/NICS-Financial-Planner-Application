package com.example.model;

import java.util.ArrayList;
import java.util.List;
	
public class User {
	private String username;
	private String password;
	//private String[] accounts;
	private List<UserAccount> accounts;
	
	public User(String name, String password) {
		this.username = name;
		this.password = password;
		accounts = new ArrayList<UserAccount>();
	}
	
	/*public void setPassword(String password) {
		this.password = password;
	}*/
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public List<UserAccount> getAccounts(){
		return accounts;
	}
	
	public void addAccount(UserAccount account){
		accounts.add(account);
	}
}

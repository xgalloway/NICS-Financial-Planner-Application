package com.example.model;

import java.util.ArrayList;
import java.util.List;
	
public class User {
	private String username;
	private int password;
	//private String[] accounts;
	private List<UserAccount> accounts;
	
	public User(String name, int password) {
		this.username = name;
		this.password = password;
		accounts = new ArrayList<UserAccount>();
	}
	
	/*public void setPassword(String password) {
		this.password = password;
	}*/
	
	public int getPassword() {
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

package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Team 16
 *
 */

public class User {
	private String username;
	private int password;
	//private String[] accounts;
	private List<UserAccount> accounts;
	private UserAccount current;
	private Report currentReport;
	
	private long id;
	
	public User(String name, int password) {
		this.username = name;
		this.password = password;
		accounts = new ArrayList<UserAccount>();
		accounts.add(new UserAccount("Default", "Default", 500, .01));
		accounts.add(new UserAccount("Suntrust", "turnup", 10000, .1));
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
	
	public UserAccount getAccount(String s) {
		return accounts.get(0);
	}
	
	public UserAccount getCurrent() {
		return current;
	}
	
	public void setCurrentAccount(UserAccount account) {
		current = account;
	}
	
	public void setCurrentReport(Report report) {
		currentReport = report;
	}
	
	public Report getCurrentReport() {
		return currentReport;
	}
	
	public void setId(long pid) {
		this.id = pid;
	}
}

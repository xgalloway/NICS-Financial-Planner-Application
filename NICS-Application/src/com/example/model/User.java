package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Team 16
 * 
 * Sets up methods associated with the user
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
	
	/**
	 * Creates a new user
	 * 
	 * @param name Name of user
	 * @param password User password
	 */
	
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
	
	/**
	 * Gets user password
	 * 
	 * @return User password
	 */
	
	public int getPassword() {
		return password;
	}
	
	/**
	 * Gets username
	 * 
	 * @return Username
	 */
	
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets all user accounts
	 * 
	 * @return All users associated with the application
	 */
	
	public List<UserAccount> getAccounts(){
		return accounts;
	}
	
	/**
	 * Adds accounts to list of accounts
	 * 
	 * @param account User account
	 */
	
	public void addAccount(UserAccount account){
		accounts.add(account);
	}
	
	/**
	 * @param s String
	 * @return Accounts
	 */
	
	public UserAccount getAccount(String s) {
		return accounts.get(0);
	}
	
	/**
	 * Get current account
	 * 
	 * @return Account in use
	 */
	
	public UserAccount getCurrent() {
		return current;
	}
	
	/**
	 * Sets current account to user account in use
	 * 
	 * @param account Current account
	 */
	
	public void setCurrentAccount(UserAccount account) {
		current = account;
	}
	
	/**
	 * Sets current report based on the user
	 * 
	 * @param report Report being generated
	 */
	
	public void setCurrentReport(Report report) {
		currentReport = report;
	}
	
	/**
	 * Gets current report based on the user
	 * 
	 * @return Report generated
	 */
	
	public Report getCurrentReport() {
		return currentReport;
	}
	
	/**
	 * @param pid
	 */
	
	public void setId(long pid) {
		this.id = pid;
	}
}

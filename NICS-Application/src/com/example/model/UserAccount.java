package com.example.model;
public class UserAccount{
	private String name;
	private String accountName;
	private double balance;
	private double rate;
	
	public UserAccount(String display, String full, double balance, double interest){
		this.name = display;
		this.accountName = full;
		this.balance = balance;
		this.rate = interest;
	}
	public String getName() {
		return name;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double getRate() {
		return rate;
	}
	
}
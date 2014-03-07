package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccount{
	private String name;
	private String accountName;
	private double balance;
	private double rate;
	
	List<Transaction> transactionList = new ArrayList<Transaction>();
	
	public UserAccount(String display, String full, double balance, double interest){
		this.name = display;
		this.accountName = full;
		this.balance = balance;
		transactionList.add(new Transaction(balance, new Date(System.currentTimeMillis()), "deposit", "Starting balance"));
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
	
	public List<Transaction> getTransactions() {
		return transactionList;
	}
	
	public void makeTransaction(double amount, Date date, String type, String comments) {
		double finalAmount = Math.abs(amount);
		Transaction t = new Transaction(finalAmount, date, type, comments);
		transactionList.add(t);
		if (type.equals("deposit")) {
			balance = balance + finalAmount;
		} else if (type.equals("withdrawal")) {
			balance = balance - amount;
		}
	}
	
	public String toString() {
		return "" + name + "\nBalance: " + balance;
	}
	
}
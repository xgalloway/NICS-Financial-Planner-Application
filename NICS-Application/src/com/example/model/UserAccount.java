package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccount{
	private String name;
	private String accountName;
	private double balance;
	private double rate;
	private String parent;
	
	List<Transaction> transactionList = new ArrayList<Transaction>();
	
	public UserAccount(String display, String full, double balance, double interest){
		this.name = display;
		this.accountName = full;
		this.balance = balance;
		Date date = new Date(System.currentTimeMillis());
		//transactionList.add(new Transaction(balance, date, "deposit", "Starting balance"));
		this.rate = interest;
	}
	
	public UserAccount(String name, double balance, double interest, String parent) {
		this.name = name;
		this.balance = balance;
		this.rate = interest;
		this.parent = parent;
		
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
	
	public void setBalance(Double amount) {
		balance = amount;
	}
	
	public double getRate() {
		return rate;
	}
	
	public String getParent() {
		return parent;
	}
	
	public List<Transaction> getTransactions() {
		return transactionList;
	}
	
	public List<Transaction> getDeposits() {
		List<Transaction> deposits = new ArrayList();
		for (int i = 0; i < transactionList.size(); ++i) {
			if (transactionList.get(i).getType().equals("deposit")) {
				deposits.add(transactionList.get(i));
			}
		}
		return deposits;
	}
	
	public List<Transaction> getWithdrawals() {
		List<Transaction> withdrawals = new ArrayList();
		for (int i = 0; i < transactionList.size(); ++i) {
			if (transactionList.get(i).getType().equals("withdrawal")) {
				withdrawals.add(transactionList.get(i));
			}
		}
		
		return withdrawals;
	}
	
	public void makeTransaction(double amount, Date date, String type, String comments) {
		double finalAmount = Math.abs(amount);
		//Transaction t = new Transaction(finalAmount, date, type, comments);
		//transactionList.add(t);
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
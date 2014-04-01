package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Team 16
 *
 */

public class UserAccount{
	private String name;
	private String accountName;
	private double balance;
	private double rate;
	private String parent;
	
	List<Transaction> transactionList = new ArrayList<Transaction>();
	
	/**
	 * Instantiates name, accountName, balance and rate
	 * @param display
	 * @param full
	 * @param balance
	 * @param interest
	 */
	
	public UserAccount(String display, String full, double balance, double interest){
		this.name = display;
		this.accountName = full;
		this.balance = balance;
		Date date = new Date(System.currentTimeMillis());
		//transactionList.add(new Transaction(balance, date, "deposit", "Starting balance"));
		this.rate = interest;
	}
	
	/**
	 * Instantiates name, balance, rate and parent
	 * @param name
	 * @param balance
	 * @param interest
	 * @param parent
	 */
	
	public UserAccount(String name, double balance, double interest, String parent) {
		this.name = name;
		this.balance = balance;
		this.rate = interest;
		this.parent = parent;
		
	}
	
	/**
	 * Gets name of user
	 * @return
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Gets name of account
	 * @return
	 */
	
	public String getAccountName() {
		return accountName;
	}
	
	/**
	 * Gets balance from account
	 * @return
	 */
	
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Sets account balance
	 * @param amount
	 */
	
	public void setBalance(Double amount) {
		balance = amount;
	}
	
	/**
	 * Gets interest rate
	 * @return
	 */
	
	public double getRate() {
		return rate;
	}
	
	/**
	 * Gets name on main account
	 * @return
	 */
	
	public String getParent() {
		return parent;
	}
	
	/**
	 * Gets list of all transactions
	 * @return
	 */
	
	public List<Transaction> getTransactions() {
		return transactionList;
	}
	
	/**
	 * Puts deposits into a list
	 */
	
	public List<Transaction> getDeposits() {
		List<Transaction> deposits = new ArrayList();
		for (int i = 0; i < transactionList.size(); ++i) {
			if (transactionList.get(i).getType().equals("deposit")) {
				deposits.add(transactionList.get(i));
			}
		}
		return deposits;
	}
	
	/**
	 * Puts withdrawals into a list
	 */
	
	public List<Transaction> getWithdrawals() {
		List<Transaction> withdrawals = new ArrayList();
		for (int i = 0; i < transactionList.size(); ++i) {
			if (transactionList.get(i).getType().equals("withdrawal")) {
				withdrawals.add(transactionList.get(i));
			}
		}
		
		return withdrawals;
	}
	
	/**
	 * Creates a transaction
	 * @param amount
	 * @param date
	 * @param type
	 * @param comments
	 */
	
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
	
	/**
	 * Creates a string about an account's balance
	 */
	
	public String toString() {
		return "" + name + "\nBalance: " + balance;
	}
	
}
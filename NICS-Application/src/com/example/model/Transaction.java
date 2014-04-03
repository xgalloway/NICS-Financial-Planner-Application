package com.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Team 16
 *
 * Creates the basics for the transaction
 */

public class Transaction {

	private double amount;
	private Date date;
	private String comments;
	private String type;
	private String parent;
	
	/**
	 * Instantiates variables
	 * 
	 * @param amount Transaction amount
	 * @param date Transaction date
	 * @param type Transaction type
	 * @param comments Transaction comments
	 * @param parent Transaction account holder
	 */
	
	public Transaction(double amount, Date date, String type, String comments, String parent) {
		this.amount = amount;
		this.date = date;
		this.comments = comments;
		this.type = type;
		this.parent = parent;
	}

	/**
	 * Gets amount
	 * 
	 * @return Amount associated with transaction
	 */
	
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Sets amount associated with transaction
	 * 
	 * @param num Amount of transaction
	 */
	
	public void setAmount(double num) {
		amount = num;
	}
	
	/**
	 * Gets comments associated with transaction
	 * 
	 * @return Comments associated with transaction
	 */
	
	public String getComments() {
		return comments;
	}
	
	/**
	 * Gets date associated with transaction
	 * 
	 * @return Date associated with transaction
	 */
	
	public Date getDate() {
		return date;
	}
	
	/** 
	 * Gets parent account associated with transaction
	 * 
	 * @return Main account holder
	 */
	
	public String getParent() {
		return parent;
	}
	
	/**
	 * Sets date associated with transaction
	 * 
	 * @param day Day number of transaction
	 * @param month Month of transaction
	 * @param year Year of transaction
	 */
	
	public void setDate(int day, int month, int year) {
		date = new Date(year, month, day);
	}
	
	/**
	 * Creates a string for transaction amount
	 * 
	 * @return String
	 */
	
	public String toString() {
		if (type.equals("deposit")) {
			return "+ " + getDateString() + "\nAmount: " + amount;
		} else {
			return "- " + getDateString() + "\nAmount: " + amount;
		}
	}
	
	/**
	 * Gets type of account
	 * 
	 * @return Account type
	 */
	
	public String getType() {
		return type;
	}
	
	/**
	 * Creates a string for the date
	 * 
	 * @return Date in string format
	 */
	
	public String getDateString() {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = sdf.format(date);
		return dateString;
	}
}

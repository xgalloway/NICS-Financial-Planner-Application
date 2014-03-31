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
	
	public Transaction(double amount, Date date, String type, String comments, String parent) {
		this.amount = amount;
		this.date = date;
		this.comments = comments;
		this.type = type;
		this.parent = parent;
	}

	/**
	 * @return amount associated with transaction
	 */
	
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Sets amount associated with transaction
	 * @param num
	 */
	
	public void setAmount(double num) {
		amount = num;
	}
	
	/**
	 * @return comments associated with transaction
	 */
	
	public String getComments() {
		return comments;
	}
	
	/**
	 * @return date associated with transaction
	 */
	
	public Date getDate() {
		return date;
	}
	
	/** 
	 * @return
	 */
	
	public String getParent() {
		return parent;
	}
	
	/**
	 * \
	 * @param day
	 * @param month
	 * @param year
	 */
	
	public void setDate(int day, int month, int year) {
		date = new Date(year, month, day);
	}
	
	/**
	 * 
	 */
	
	public String toString() {
		if (type.equals("deposit")) {
			return "+ " + getDateString() + "\nAmount: " + amount;
		} else {
			return "- " + getDateString() + "\nAmount: " + amount;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getType() {
		return type;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getDateString() {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = sdf.format(date);
		return dateString;
	}
}

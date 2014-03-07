package com.example.model;

import java.util.Date;

public class Transaction {

	private double amount;
	private Date date;
	private String comments;
	private String type;
	
	public Transaction(double amount, Date date, String type, String comments) {
		this.amount = amount;
		this.date = date;
		this.comments = comments;
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double num) {
		amount = num;
	}
	
	public String getComments() {
		return comments;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(int day, int month, int year) {
		date = new Date(year, month, day);
	}
	
	public String toString() {
		return "" + date.toString() +"\nAmount: " + amount;
	}
}

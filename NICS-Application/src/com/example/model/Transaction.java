package com.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public String getParent() {
		return parent;
	}
	
	public void setDate(int day, int month, int year) {
		date = new Date(year, month, day);
	}
	
	public String toString() {
		if (type.equals("deposit")) {
			return "+ " + getDateString() + "\nAmount: " + amount;
		} else {
			return "- " + getDateString() + "\nAmount: " + amount;
		}
	}
	
	public String getType() {
		return type;
	}
	
	public String getDateString() {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = sdf.format(date);
		return dateString;
	}
}

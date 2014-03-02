package com.example.model;

import java.util.Date;

public class Transaction {

	private double amount;
	private Date date;
	
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double num) {
		amount = num;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(int day, int month, int year) {
		date = new Date(year, month, day);
	}
}

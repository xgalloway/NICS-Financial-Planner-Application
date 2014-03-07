package com.example.view;

import java.util.Date;

public interface TransactionView {
	void makeTransaction();
	String getAmount();
	Date getDate();
	String getComments();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void displayAlertDialog();
}

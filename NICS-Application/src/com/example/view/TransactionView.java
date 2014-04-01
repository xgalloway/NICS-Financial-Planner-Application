package com.example.view;

import java.util.Date;

import com.example.support.ClickListener;

/**
 * 
 * @author Team 16
 *
 * Creates interface for transaction
 */

public interface TransactionView {
	void makeTransaction();
	String getAmount();
	Date getDate();
	String getComments();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void displayAlertDialog();
}

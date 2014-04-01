package com.example.view;

import java.util.Date;

import com.example.support.ClickListener;

/**
 * 
 * @author Team 16
 * 
 * Creates interface for account
 *
 */

public interface AccountView {
	void addTransaction();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void leaveTransactionScreen();
	
}
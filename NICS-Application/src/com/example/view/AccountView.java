package com.example.view;



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
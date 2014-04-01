package com.example.view;

import com.example.support.ClickListener;

/**
 * 
 * @author Team 16
 * 
 * Creates interface for account list view
 *
 */
public interface AccountListView {
	void createAccount();
	void viewAccount();
	void viewReport();
	void addSearchRequestNotifyCallback(ClickListener listener);
}
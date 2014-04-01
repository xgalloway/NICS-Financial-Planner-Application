package com.example.view;

import com.example.support.ClickListener;

/**
 * 
 * @author Team 16
 *
 * Creates interface for login
 */

public interface LogInView {
	void acceptLogin();
	String getUsername();
	String getPassword();
	void displayAlertDialog();
	void addSearchRequestNotifyCallback(ClickListener listener);
}

package com.example.view;

import support.ClickListener;

public interface LogInView {
	void acceptLogin();
	String getUsername();
	String getPassword();
	void displayAlertDialog();
	void addSearchRequestNotifyCallback(ClickListener listener);
}

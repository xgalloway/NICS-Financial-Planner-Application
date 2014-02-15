package com.example.view;

public interface LogInView {
	void acceptLogin();
	String getUsername();
	String getPassword();
	void displayAlertDialog();
	void addSearchRequestNotifyCallback(ClickListener listener);
}

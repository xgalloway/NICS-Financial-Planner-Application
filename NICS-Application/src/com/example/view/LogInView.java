package com.example.view;

import com.example.support.ClickListener;

public interface LogInView {
	void acceptLogin();
	String getUsername();
	String getPassword();
	void displayAlertDialog();
	void addSearchRequestNotifyCallback(ClickListener listener);
}

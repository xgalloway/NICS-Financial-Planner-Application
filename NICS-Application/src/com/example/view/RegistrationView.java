package com.example.view;

import com.example.support.ClickListener;

public interface RegistrationView {
	void acceptRegistration();
	String getUsername();
	String getPassword();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void displayAlertDialog();
	void displayAlertDialog2();
}

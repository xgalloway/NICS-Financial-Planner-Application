package com.example.view;

public interface RegistrationView {
	void acceptRegistration();
	String getUsername();
	String getPassword();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void displayAlertDialog();
}

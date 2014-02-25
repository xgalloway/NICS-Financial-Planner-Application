package com.example.view;

public interface AccountSetupView {
	void acceptAccount();
	String getType();
	String getName();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void displayAlertDialog();
}
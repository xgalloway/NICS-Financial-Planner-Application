package com.example.view;

public interface AccountSetupView {
	void acceptAccount();
	String getDisplayName();
	String getName();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void displayAlertDialog();
}
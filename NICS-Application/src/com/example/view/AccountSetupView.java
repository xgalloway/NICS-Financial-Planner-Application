package com.example.view;

public interface AccountSetupView {
	void acceptAccount();
	String getdisplayName();
	String getName();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void displayAlertDialog();
}
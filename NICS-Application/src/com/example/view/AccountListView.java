package com.example.view;

import support.ClickListener;

public interface AccountListView {
	void createAccount();
	void viewAccount();
	void viewReport();
	void addSearchRequestNotifyCallback(ClickListener listener);
}
package com.example.view;

import java.util.Date;

public interface AccountView {
	void addTransaction();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void leaveTransactionScreen();
	
}
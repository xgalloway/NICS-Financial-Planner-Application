package com.example.view;

import java.util.Date;

import com.example.support.ClickListener;


public interface AccountView {
	void addTransaction();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void leaveTransactionScreen();
	
}
package com.example.view;

import java.util.Date;

public interface AccountView {
	void addTransaction();
	String getAmount();
	Date getDate();
	void addSearchRequestNotifyCallback(ClickListener listener);
	
}
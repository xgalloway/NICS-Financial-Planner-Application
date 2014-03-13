package com.example.view;

import java.util.Date;

import support.ClickListener;

public interface SpendingReportView{
	Date getDate();
	void goBack();
	void addSearchRequestNotifyCallback(ClickListener listener);
}
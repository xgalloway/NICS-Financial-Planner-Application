package com.example.view;

import java.util.Date;

import com.example.support.ClickListener;


public interface SpendingReportView{
	Date getDate();
	void goBack();
	void addSearchRequestNotifyCallback(ClickListener listener);
}
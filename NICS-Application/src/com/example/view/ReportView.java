package com.example.view;

import java.util.Date;

import com.example.support.ClickListener;


public interface ReportView{
	void acceptRange();
	Date getStartDate();
	Date getEndDate();
	void displayAlertDialog();
	void addSearchRequestNotifyCallback(ClickListener listener);
}
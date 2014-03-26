package com.example.view;

import java.util.Date;

import com.example.support.ClickListener;


public interface ReportView{
	void acceptRange();
	String getStartDate();
	String getEndDate();
	void displayAlertDialog();
	void displayDateDialog(String startOrEnd);
	void addSearchRequestNotifyCallback(ClickListener listener);
}
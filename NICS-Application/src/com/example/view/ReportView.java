package com.example.view;

public interface ReportView{
	void acceptRange();
	String getStartDate();
	String getEndDate();
	void displayAlertDialog();
	void addSearchRequestNotifyCallback(ClickListener listener);
}
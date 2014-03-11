package com.example.view;

import java.util.Date;

public interface SpendingReportView{
	Date getDate();
	void goBack();
	void addSearchRequestNotifyCallback(ClickListener listener);
}
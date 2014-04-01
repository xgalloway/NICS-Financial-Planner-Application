package com.example.view;

import java.util.Date;

import com.example.support.ClickListener;

/**
 * 
 * @author Team 16
 *
 * Creates interface for spending report
 */

public interface SpendingReportView{
	Date getDate();
	void goBack();
	void addSearchRequestNotifyCallback(ClickListener listener);
}
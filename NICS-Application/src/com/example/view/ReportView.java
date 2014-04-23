package com.example.view;

import java.util.Date;

import com.example.support.ClickListener;

/**
 * 
 * @author Team 16
 *
 * Creates interface for report
 */

public interface ReportView{
	void acceptRange();
	String getStartDate();
	String getEndDate();
	void displayAlertDialog();
	void displayDateDialog(String startOrEnd);
	void addSearchRequestNotifyCallback(ClickListener listener);
    void acceptIncomeRange();
}
package com.example.view;

import com.example.support.ClickListener;

/**
 * 
 * @author Team 16
 * 
 * Creates interface for account setup
 *
 */
public interface AccountSetupView {
	void acceptAccount();
	String getDisplayName();
	String getName();
	String getRate();
	String getBalance();
	void addSearchRequestNotifyCallback(ClickListener listener);
	void displayAlertDialog();
	void initiateButtonsAndEditTexts();
	void cancelAccountCreation();
}
package com.example.view;

import com.example.support.ClickListener;

/**
 * 
 * @author Team 16
 * 
 * Creates interface for welcome screen
 *
 */

public interface WelcomeView {
	void startLogin();
	void startRegistration();
	void addSearchRequestNotifyCallback(ClickListener listener);
}

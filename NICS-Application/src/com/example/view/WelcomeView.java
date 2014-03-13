package com.example.view;

import support.ClickListener;

public interface WelcomeView {
	void startLogin();
	void startRegistration();
	void addSearchRequestNotifyCallback(ClickListener listener);
}

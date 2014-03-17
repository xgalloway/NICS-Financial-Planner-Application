package com.example.view;

import com.example.support.ClickListener;

public interface WelcomeView {
	void startLogin();
	void startRegistration();
	void addSearchRequestNotifyCallback(ClickListener listener);
}

package com.example.presenter;

import support.ClickListener;

import com.example.nics_application.R;
import com.example.nics_application.R.id;
import com.example.nics_application.R.layout;
import com.example.nics_application.R.menu;

import android.view.View;

import com.example.model.Model;
import com.example.view.WelcomeView;

public class WelcomeViewPresenter implements ClickListener {

	private WelcomeView view;
	//private Model model;
	
	public WelcomeViewPresenter(WelcomeView v) {
		this.view = v;
		//this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.loginButton:
				view.startLogin();
				break;
			case R.id.goButton:
				view.startRegistration();
				break;
		}
	}

}

package com.example.presenter;


import com.example.nics_application.R;
import com.example.nics_application.R.id;
import com.example.nics_application.R.layout;
import com.example.nics_application.R.menu;

import android.view.View;

import com.example.model.Model;
import com.example.support.ClickListener;
import com.example.view.WelcomeView;

/**
 * 
 * @author Team 16
 *
 */

public class WelcomeViewPresenter implements ClickListener {

	private WelcomeView view;
	//private Model model;
	
	/**
	 * 
	 * @param v
	 */
	
	public WelcomeViewPresenter(WelcomeView v) {
		this.view = v;
		//this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * Directs user to the login in screen if login button is pressed
	 * Directs user to the registration screen if go button is pressed
	 * 
	 * @param v
	 */
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

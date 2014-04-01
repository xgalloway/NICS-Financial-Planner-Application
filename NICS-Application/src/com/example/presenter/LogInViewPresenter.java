package com.example.presenter;

import android.view.View;


import com.example.model.Model;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.LogInView;

/**
 * 
 * @author Team 16
 *
 */

public class LogInViewPresenter implements ClickListener {
	
	private LogInView view;
	private final Model model;

	/**
	 * Instantiates variables
	 * @param v
	 * @param m
	 */
	
	public LogInViewPresenter(LogInView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * Tells the user if the login has been accepted or not
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.goButton:
				String name = view.getUsername();
				String password = view.getPassword();
				if (model.acceptCredentials(name, password.hashCode())) {
					view.acceptLogin();
				} else {
					view.displayAlertDialog();
				}
				break;
		}

	}

}

package com.example.presenter;

import android.view.View;



import com.example.model.Model;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.RegistrationView;

/**
 * 
 * @author Team 16
 *
 */

public class RegistrationViewPresenter implements ClickListener {

	private RegistrationView view;
	private final Model model;
	
	/**
	 * Instantiates variables
	 * @param v
	 * @param m
	 */
	
	public RegistrationViewPresenter(RegistrationView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * Tells user if registration has been accepted or not
	 * @param v
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.goButton:
				String username = view.getUsername();
				String password = view.getPassword();
				if (username.length() < 5 || password.length() < 5) {
					view.displayAlertDialog2();
				} else if (model.isValidUser(username)) {
					view.displayAlertDialog();
				} else {
					model.addUser(username, password.hashCode());
					view.acceptRegistration();
				}
		}

	}

}
